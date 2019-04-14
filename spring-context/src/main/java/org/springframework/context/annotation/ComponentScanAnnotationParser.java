/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AspectJTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * Parser for the @{@link ComponentScan} annotation.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 3.1
 * @see ClassPathBeanDefinitionScanner#scan(String...)
 * @see ComponentScanBeanDefinitionParser
 */
class ComponentScanAnnotationParser {

	private final Environment environment;

	private final ResourceLoader resourceLoader;

	private final BeanNameGenerator beanNameGenerator;

	private final BeanDefinitionRegistry registry;


	public ComponentScanAnnotationParser(Environment environment, ResourceLoader resourceLoader,
										 BeanNameGenerator beanNameGenerator, BeanDefinitionRegistry registry) {

		this.environment = environment;
		this.resourceLoader = resourceLoader;
		this.beanNameGenerator = beanNameGenerator;
		this.registry = registry;
	}

	//这里自定义了一个scanner，因此之前初始化的scanner其实没什么卵用
	//1、初始化scanner，解析包名，将懒加载、代理模式、过滤器之类的设置到scanner中
	//2、调用scanner.doScan开始扫描包，获取元数据资源，扫描出交给Spring的基础类（Service Component等，包括配置类本身），然后初始化bd的默认值，
	//将类上面的基础注解@Lazy  @Primary之类的数据注入到bd中，然后把bd放到工厂中
	public Set<BeanDefinitionHolder> parse(AnnotationAttributes componentScan, final String declaringClass) {
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(this.registry,
				componentScan.getBoolean("useDefaultFilters"), this.environment, this.resourceLoader);

		//判断是否是同一个类型
		Class<? extends BeanNameGenerator> generatorClass = componentScan.getClass("nameGenerator");
		boolean useInheritedGenerator = (BeanNameGenerator.class == generatorClass);
		scanner.setBeanNameGenerator(useInheritedGenerator ? this.beanNameGenerator :
				BeanUtils.instantiateClass(generatorClass));
		//判断scopeProxyMode
		ScopedProxyMode scopedProxyMode = componentScan.getEnum("scopedProxy");
		if (scopedProxyMode != ScopedProxyMode.DEFAULT) {
			scanner.setScopedProxyMode(scopedProxyMode);
		}
		else {
			Class<? extends ScopeMetadataResolver> resolverClass = componentScan.getClass("scopeResolver");
			scanner.setScopeMetadataResolver(BeanUtils.instantiateClass(resolverClass));
		}

		scanner.setResourcePattern(componentScan.getString("resourcePattern"));
		//处理filter
		for (AnnotationAttributes filter : componentScan.getAnnotationArray("includeFilters")) {
			for (TypeFilter typeFilter : typeFiltersFor(filter)) {
				scanner.addIncludeFilter(typeFilter);
			}
		}
		for (AnnotationAttributes filter : componentScan.getAnnotationArray("excludeFilters")) {
			for (TypeFilter typeFilter : typeFiltersFor(filter)) {
				scanner.addExcludeFilter(typeFilter);
			}
		}
		//懒加载
		boolean lazyInit = componentScan.getBoolean("lazyInit");
		if (lazyInit) {
			scanner.getBeanDefinitionDefaults().setLazyInit(true);
		}

		Set<String> basePackages = new LinkedHashSet<>();
		//获取Component的基础包名放入basePackages，去重
		String[] basePackagesArray = componentScan.getStringArray("basePackages");
		for (String pkg : basePackagesArray) {
			String[] tokenized = StringUtils.tokenizeToStringArray(this.environment.resolvePlaceholders(pkg),
					ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
			Collections.addAll(basePackages, tokenized);
		}
		for (Class<?> clazz : componentScan.getClassArray("basePackageClasses")) {
			basePackages.add(ClassUtils.getPackageName(clazz));
		}
		if (basePackages.isEmpty()) {
			basePackages.add(ClassUtils.getPackageName(declaringClass));
		}

		scanner.addExcludeFilter(new AbstractTypeHierarchyTraversingFilter(false, false) {
			@Override
			protected boolean matchClassName(String className) {
				return declaringClass.equals(className);
			}
		});
		//上面都是在解析包名，然后将懒加载、过滤器和代理模式之类的注入到scanner中
		//这里开始扫描包名，获取元数据资源，扫描出交给Spring的基础类（Service Component等，包括配置类本身），然后初始化bd的默认值，
		//将类上面的基础注解@Lazy  @Primary之类的数据注入到bd中，然后把bd放到工厂中
		return scanner.doScan(StringUtils.toStringArray(basePackages));
	}

	private List<TypeFilter> typeFiltersFor(AnnotationAttributes filterAttributes) {
		List<TypeFilter> typeFilters = new ArrayList<>();
		FilterType filterType = filterAttributes.getEnum("type");

		for (Class<?> filterClass : filterAttributes.getClassArray("classes")) {
			switch (filterType) {
				case ANNOTATION:
					Assert.isAssignable(Annotation.class, filterClass,
							"@ComponentScan ANNOTATION type filter requires an annotation type");
					@SuppressWarnings("unchecked")
					Class<Annotation> annotationType = (Class<Annotation>) filterClass;
					typeFilters.add(new AnnotationTypeFilter(annotationType));
					break;
				case ASSIGNABLE_TYPE:
					typeFilters.add(new AssignableTypeFilter(filterClass));
					break;
				case CUSTOM:
					Assert.isAssignable(TypeFilter.class, filterClass,
							"@ComponentScan CUSTOM type filter requires a TypeFilter implementation");
					TypeFilter filter = BeanUtils.instantiateClass(filterClass, TypeFilter.class);
					ParserStrategyUtils.invokeAwareMethods(
							filter, this.environment, this.resourceLoader, this.registry);
					typeFilters.add(filter);
					break;
				default:
					throw new IllegalArgumentException("Filter type not supported with Class value: " + filterType);
			}
		}

		for (String expression : filterAttributes.getStringArray("pattern")) {
			switch (filterType) {
				case ASPECTJ:
					typeFilters.add(new AspectJTypeFilter(expression, this.resourceLoader.getClassLoader()));
					break;
				case REGEX:
					typeFilters.add(new RegexPatternTypeFilter(Pattern.compile(expression)));
					break;
				default:
					throw new IllegalArgumentException("Filter type not supported with String pattern: " + filterType);
			}
		}

		return typeFilters;
	}

}

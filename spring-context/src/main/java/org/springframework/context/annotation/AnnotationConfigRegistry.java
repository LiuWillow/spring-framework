/*
 * Copyright 2002-2014 the original author or authors.
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

/**
 * 提供register和scan方法
 */
public interface AnnotationConfigRegistry {

	/**
	 * 注册一个或多个要处理的注解类，调用register方法是幂等的
	 * register同一个注解类多次不会有其他影响
	 * e.g. {@link Configuration @Configuration} classes
	 */
	void register(Class<?>... annotatedClasses);

	/**
	 * 在指定的基本包中执行扫描
	 * @param basePackages the packages to check for annotated classes
	 */
	void scan(String... basePackages);

}

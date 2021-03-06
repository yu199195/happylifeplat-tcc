/*
 * Copyright 2017-2021 Dromara.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.hmily.core.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * The HmilyTransactionInterceptor.
 *
 * @author xiaoyu
 */
@FunctionalInterface
public interface HmilyTransactionInterceptor {
    
    /**
     * Invoke hmily interceptor.
     *
     * @param pjp tcc point cut.
     * @return Object object
     * @throws Throwable Throwable
     */
    Object invoke(ProceedingJoinPoint pjp) throws Throwable;
}

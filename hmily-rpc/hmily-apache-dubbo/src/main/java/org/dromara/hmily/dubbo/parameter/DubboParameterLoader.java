/*
 * Copyright 2017-2021 Dromara.org

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

package org.dromara.hmily.dubbo.parameter;

import org.apache.dubbo.rpc.RpcContext;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.dromara.hmily.core.context.HmilyTransactionContext;
import org.dromara.hmily.core.mediator.RpcMediator;
import org.dromara.hmily.core.mediator.RpcParameterLoader;
import org.dromara.hmily.spi.HmilySPI;

import java.util.Optional;

/**
 * The type Dubbo parameter loader.
 *
 * @author xiaoyu
 */
@HmilySPI(value = "dubbo")
public class DubboParameterLoader implements RpcParameterLoader {

    @Override
    public HmilyTransactionContext load() {
        return Optional.ofNullable(RpcMediator.getInstance().acquire(RpcContext.getContext()::getAttachment)).orElse(HmilyContextHolder.get());
    }
}

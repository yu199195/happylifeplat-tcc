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

package org.dromara.hmily.tac.sqlrevert.spi;

import org.dromara.hmily.repository.spi.entity.HmilyParticipantUndo;
import org.dromara.hmily.tac.sqlrevert.spi.exception.SQLRevertException;

/**
 * The interface Hmily SQL revert engine.
 *
 * @author xiaoyu
 * @author zhaojun
 */
public interface HmilySQLRevertEngine {
    
    /**
     * Revert hmily undo invocation.
     *
     * @param participantUndo  hmily participant undo
     * @return true or false
     * @throws SQLRevertException the sql revert exception
     */
    boolean revert(HmilyParticipantUndo participantUndo) throws SQLRevertException;
}

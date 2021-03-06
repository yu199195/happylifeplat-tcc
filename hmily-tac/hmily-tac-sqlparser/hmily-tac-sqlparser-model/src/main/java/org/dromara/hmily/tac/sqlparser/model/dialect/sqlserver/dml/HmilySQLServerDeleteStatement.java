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

package org.dromara.hmily.tac.sqlparser.model.dialect.sqlserver.dml;

import lombok.Setter;
import lombok.ToString;
import org.dromara.hmily.tac.sqlparser.model.common.segment.generic.HmilyOutputSegment;
import org.dromara.hmily.tac.sqlparser.model.common.segment.generic.HmilyWithSegment;
import org.dromara.hmily.tac.sqlparser.model.common.statement.dml.HmilyDeleteStatement;
import org.dromara.hmily.tac.sqlparser.model.dialect.sqlserver.HmilySQLServerStatement;

import java.util.Optional;

/**
 * SQLServer delete statement.
 */
@Setter
@ToString
public final class HmilySQLServerDeleteStatement extends HmilyDeleteStatement implements HmilySQLServerStatement {

    private HmilyWithSegment withSegment;
    
    private HmilyOutputSegment outputSegment;

    /**
     * Get with segment.
     *
     * @return with segment.
     */
    public Optional<HmilyWithSegment> getWithSegment() {
        return Optional.ofNullable(withSegment);
    }
    
    /**
     * Get output segment.
     *
     * @return output segment.
     */
    public Optional<HmilyOutputSegment> getOutputSegment() {
        return Optional.ofNullable(outputSegment);
    }
}

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

package org.dromara.hmily.tac.sqlparser.calcite;

import org.dromara.hmily.spi.HmilySPI;
import org.dromara.hmily.tac.common.database.type.DatabaseType;
import org.dromara.hmily.tac.sqlparser.model.common.statement.HmilyStatement;
import org.dromara.hmily.tac.sqlparser.spi.HmilySqlParserEngine;
import org.dromara.hmily.tac.sqlparser.spi.exception.SqlParserException;

/**
 * The type Calcite sql parser engine.
 *
 * @author xiaoyu
 */
@HmilySPI("calcite")
public class CalciteSqlParserEngine implements HmilySqlParserEngine {
    
    @Override
    public HmilyStatement parser(final String sql, final DatabaseType databaseType) throws SqlParserException {
        return null;
    }
}

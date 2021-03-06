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

package org.dromara.hmily.tac.common.database.dialect;

import java.sql.Connection;
import java.sql.SQLException;
import org.dromara.hmily.tac.common.enums.QuoteCharacter;

/**
 * Database meta data dialect handler.
 */
public interface DatabaseMetaDataDialectHandler {
    
    /**
     * Get schema.
     *
     * @param connection connection
     * @return schema
     */
    default String getSchema(final Connection connection) {
        try {
            return connection.getSchema();
        } catch (final SQLException ignored) {
            return null;
        }
    }
    
    /**
     * Format table name pattern.
     *
     * @param tableNamePattern table name pattern
     * @return formatted table name pattern
     */
    default String formatTableNamePattern(final String tableNamePattern) {
        return tableNamePattern;
    }
    
    /**
     * Get quote character.
     *
     * @return quote character
     */
    default QuoteCharacter getQuoteCharacter() {
        return QuoteCharacter.NONE;
    }
}

/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.dromara.hmily.xa.p6spy;

import org.dromara.hmily.xa.core.TransactionImpl;
import org.dromara.hmily.xa.core.TransactionManagerImpl;

import javax.sql.XAConnection;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.xa.XAResource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * HmilyXaStatement .
 *
 * @author sixh chenbin
 */
public class HmilyXaStatement implements Statement {

    /**
     * The Statement.
     */
    private final Statement statement;

    private final Connection connection;

    private final XAConnection xaConnection;

    /**
     * Instantiates a new Hmily xa statement.
     *
     * @param connection the connection
     * @param statement  the statement
     */
    public HmilyXaStatement(final Connection connection, final XAConnection xaConnection, final Statement statement) {
        this.statement = statement;
        this.connection = connection;
        this.xaConnection = xaConnection;
    }

    @Override
    public ResultSet executeQuery(final String sql) throws SQLException {
        return associateXa(() -> statement.executeQuery(sql));
    }

    @Override
    public int executeUpdate(final String sql) throws SQLException {
        return associateXa(() -> statement.executeUpdate(sql));
    }

    @Override
    public void close() throws SQLException {
        statement.close();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return statement.getMaxFieldSize();
    }

    @Override
    public void setMaxFieldSize(final int max) throws SQLException {
        statement.setMaxFieldSize(max);
    }

    @Override
    public int getMaxRows() throws SQLException {
        return statement.getMaxRows();
    }

    @Override
    public void setMaxRows(final int max) throws SQLException {
        statement.setMaxRows(max);
    }

    @Override
    public void setEscapeProcessing(final boolean enable) throws SQLException {
        statement.setEscapeProcessing(enable);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return statement.getQueryTimeout();
    }

    @Override
    public void setQueryTimeout(final int seconds) throws SQLException {
        statement.setQueryTimeout(seconds);
    }

    @Override
    public void cancel() throws SQLException {
        statement.cancel();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return statement.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        statement.clearWarnings();
    }

    @Override
    public void setCursorName(final String name) throws SQLException {
        statement.setCursorName(name);
    }

    @Override
    public boolean execute(final String sql) throws SQLException {
        return statement.execute(sql);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return statement.getResultSet();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return statement.getUpdateCount();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return statement.getMoreResults();
    }

    @Override
    public void setFetchDirection(final int direction) throws SQLException {
        statement.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return statement.getFetchDirection();
    }

    @Override
    public void setFetchSize(final int rows) throws SQLException {
        statement.setFetchSize(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return statement.getFetchSize();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return statement.getResultSetConcurrency();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return statement.getResultSetType();
    }

    @Override
    public void addBatch(final String sql) throws SQLException {
        statement.addBatch(sql);
    }

    @Override
    public void clearBatch() throws SQLException {
        statement.clearBatch();
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return associateXa(statement::executeBatch);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return statement.getConnection();
    }

    @Override
    public boolean getMoreResults(final int current) throws SQLException {
        return statement.getMoreResults(current);
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return statement.getGeneratedKeys();
    }

    @Override
    public int executeUpdate(final String sql, final int autoGeneratedKeys) throws SQLException {
        return associateXa(() -> statement.executeUpdate(sql, autoGeneratedKeys));
    }

    @Override
    public int executeUpdate(final String sql, final int[] columnIndexes) throws SQLException {
        return associateXa(() -> statement.executeUpdate(sql, columnIndexes));
    }

    @Override
    public int executeUpdate(final String sql, final String[] columnNames) throws SQLException {
        return associateXa(() -> statement.executeUpdate(sql, columnNames));
    }

    @Override
    public boolean execute(final String sql, final int autoGeneratedKeys) throws SQLException {
        return associateXa(() -> statement.execute(sql, autoGeneratedKeys));
    }

    @Override
    public boolean execute(final String sql, final int[] columnIndexes) throws SQLException {
        return associateXa(() -> statement.execute(sql, columnIndexes));
    }

    @Override
    public boolean execute(final String sql, final String[] columnNames) throws SQLException {
        return associateXa(() -> statement.execute(sql, columnNames));
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return statement.getResultSetHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return statement.isClosed();
    }

    @Override
    public void setPoolable(final boolean poolable) throws SQLException {
        statement.setPoolable(poolable);
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return statement.isPoolable();
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        statement.closeOnCompletion();
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        return statement.isCloseOnCompletion();
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        return statement.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return statement.isWrapperFor(iface);
    }

    /**
     * Associate xa tm.
     *
     * @param <T>  the type parameter
     * @param call the call
     * @return the t
     * @throws SQLException the sql exception
     */
    public <T> T associateXa(final Call<T> call) throws SQLException {
        Transaction tx = TransactionManagerImpl.INST.getTransaction();
        if (tx != null) {
            XAConnection xaConnection = getXaConnection();
            try {
                if (tx instanceof TransactionImpl) {
                    ((TransactionImpl) tx).doEnList(xaConnection.getXAResource(), XAResource.TMJOIN);
                }
            } catch (RollbackException | SystemException | SQLException e) {
                e.printStackTrace();
            }
        }
        return call.call();
    }

    /**
     * Gets xa connection.
     *
     * @return the xa connection
     */
    public XAConnection getXaConnection() {
        if (xaConnection == null) {
            throw new IllegalArgumentException("connection not implements XAConnection");
        }
        return xaConnection;
    }

    /**
     * The interface Call.
     *
     * @param <T> the type parameter
     */
    @FunctionalInterface
    interface Call<T> {
        /**
         * Call t.
         *
         * @return the t
         * @throws SQLException the sql exception
         */
        T call() throws SQLException;
    }
}
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

package org.dromara.hmily.xa.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;

/**
 * Coordinator .
 *
 * @author sixh chenbin
 */
public class Coordinator implements Remote {

    private final Logger logger = LoggerFactory.getLogger(Coordinator.class);

    /**
     * all SubCoordinator.
     */
    private final Vector<Remote> coordinators = new Vector<>();

    private final XIdImpl xid;

    private XaState state = XaState.STATUS_ACTIVE;

    /**
     * Instantiates a new Coordinator.
     *
     * @param xid the xid
     */
    public Coordinator(final XIdImpl xid) {
        this.xid = xid;
        coordinators.add(this);
    }

    @Override
    public Result prepare() {
        doPrepare();
        return Result.READONLY;
    }

    @Override
    public void rollback() {
        switch (state) {
            case STATUS_ACTIVE:
            case STATUS_MARKED_ROLLBACK:
                logger.warn("statue == STATUS_ACTIVE OR STATUS_MARKED_ROLLBACK");
                break;
            case STATUS_ROLLEDBACK:
                logger.warn("statue == STATUS_ROLLED_BACK");
                return;
            default:
                break;
        }
        doRollback();
    }

    @Override
    public void commit() {
        switch (state) {
            case STATUS_ACTIVE:
                break;
            case STATUS_COMMITTED:
                logger.warn("commit done");
                return;
            case STATUS_ROLLEDBACK:
                logger.warn("commit state == STATUS_ROLLEDBACK");
                return;
            case STATUS_MARKED_ROLLBACK:
                doRollback();
                return;
            default:
                break;
        }
        //Start 1 pc.
        onePhaseCommit();

        //Start 2 pc.
        doCommit();
    }

    @Override
    public void onePhaseCommit() {

    }

    private void doPrepare() {

    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public XaState getState() {
        return state;
    }

    /**
     * Add coordinators boolean.
     *
     * @param remote the remote
     * @return the boolean
     */
    public synchronized boolean addCoordinators(final Remote remote) {
        if (coordinators.contains(remote)) {
            return true;
        }
        return this.coordinators.add(remote);
    }

    /**
     * Gets sub xid.
     *
     * @return the sub xid
     */
    public XIdImpl getSubXid() {
        return this.xid.newBranchId();
    }

    private void doRollback() {
        state = XaState.STATUS_ROLLEDBACK;
        for (Remote remote : this.coordinators) {
            if (remote != null) {
                remote.rollback();
            }
        }
    }

    private void doCommit() {
        state = XaState.STATUS_COMMITTED;
    }
}

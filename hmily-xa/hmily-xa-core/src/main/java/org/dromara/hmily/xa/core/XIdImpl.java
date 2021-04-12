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

import com.google.common.base.Splitter;
import org.dromara.hmily.common.utils.NetUtils;

import javax.transaction.xa.Xid;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * XaId .
 *
 * @author sixh chenbin
 */
public class XIdImpl implements Xid {

    private static final Integer DEF_ID = 8808;

    private static final AtomicLong XID = new AtomicLong(10000);

    /**
     * Process branch Id;
     */
    private static final AtomicLong BXID = new AtomicLong(1);

    private final String globalId;

    private final String branchId;

    private final byte[] globalIdByte;

    private final byte[] branchIdByte;

    public XIdImpl() {
        Long id = XID.getAndIncrement();
        String bid = "0";
        String newId = id + "-" + bid + "-" + NetUtils.getLocalIp();
        this.globalId = newId;
        this.branchId = newId;
        this.branchIdByte = newId.getBytes();
        this.globalIdByte = newId.getBytes();
    }

    public XIdImpl(XIdImpl xId) {
        String bid;
        String gid;
        List<String> xxIdx = Splitter.on("-").splitToList(xId.getGlobalId());
        gid = xxIdx.get(0);
        bid = String.valueOf(BXID.getAndIncrement());
        String newId = gid + "-" + bid + "-" + NetUtils.getLocalIp();
        this.branchId = newId;
        this.branchIdByte = newId.getBytes();
        this.globalId = xId.getGlobalId();
        this.globalIdByte = xId.globalIdByte;
    }

    public XIdImpl(XIdImpl xId, Integer index) {
        String bid;
        String gid;
        List<String> xxIdx = Splitter.on("-").splitToList(xId.getGlobalId());
        gid = xxIdx.get(0);
        bid = String.valueOf(BXID.getAndIncrement());
        String newId = gid + "-" + bid + "-" + NetUtils.getLocalIp() + "-" + index;
        this.branchId = newId;
        this.branchIdByte = newId.getBytes();
        this.globalId = xId.getGlobalId();
        this.globalIdByte = xId.globalIdByte;
    }


    public String getGlobalId() {
        return globalId;
    }

    public String getBranchId() {
        return branchId;
    }

    @Override
    public int getFormatId() {
        return DEF_ID;
    }

    @Override
    public byte[] getGlobalTransactionId() {
        return this.globalIdByte;
    }

    @Override
    public byte[] getBranchQualifier() {
        return this.branchIdByte;
    }

    public XIdImpl newBranchId() {
        return new XIdImpl(this);
    }

    public XIdImpl newResId(int index) {
        return new XIdImpl(this, index);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}


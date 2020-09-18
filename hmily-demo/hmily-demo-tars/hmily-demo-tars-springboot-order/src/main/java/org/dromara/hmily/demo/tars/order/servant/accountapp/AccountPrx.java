// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.7.2.
// **********************************************************************

package org.dromara.hmily.demo.tars.order.servant.accountapp;

import com.qq.tars.protocol.annotation.*;
import com.qq.tars.protocol.tars.annotation.*;
import com.qq.tars.common.support.Holder;
import java.util.concurrent.CompletableFuture;

@Servant
public interface AccountPrx {

	 void payment(@TarsMethodParameter(name="userId")String userId, @TarsMethodParameter(name="amount")double amount);

	 void payment(@TarsMethodParameter(name="userId")String userId, @TarsMethodParameter(name="amount")double amount, @TarsContext java.util.Map<String, String> ctx);

	 void async_payment(@TarsCallback AccountPrxCallback callback, @TarsMethodParameter(name="userId")String userId, @TarsMethodParameter(name="amount")double amount);

	 void async_payment(@TarsCallback AccountPrxCallback callback, @TarsMethodParameter(name="userId")String userId, @TarsMethodParameter(name="amount")double amount, @TarsContext java.util.Map<String, String> ctx);

	 CompletableFuture<Void>  promise_payment(@TarsMethodParameter(name="userId")String userId, @TarsMethodParameter(name="amount")double amount);

	 CompletableFuture<Void>  promise_payment(@TarsMethodParameter(name="userId")String userId, @TarsMethodParameter(name="amount")double amount, @TarsContext java.util.Map<String, String> ctx);
}

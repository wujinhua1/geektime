package org.example.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;

/**
 * @author wujinhua
 */
@Slf4j
@Activate
public class DubboFilter implements org.apache.dubbo.rpc.Filter {
    @Override
    public org.apache.dubbo.rpc.Result invoke(org.apache.dubbo.rpc.Invoker<?> invoker, Invocation invocation) throws org.apache.dubbo.rpc.RpcException {
        log.info("[ globalDubboFilter successfully!:{}]", JSON.toJSONString(invocation.getMethodName()));
        log.info("[ globalDubboFilter successfully!:{}]", JSON.toJSONString(invocation.getAttributes()));
        System.out.println(invoker.getUrl().getAddress());

        return invoker.invoke(invocation);
    }
}

package com.jpa.advanced.app.v1;

import com.jpa.advanced.trace.TraceStatus;
import com.jpa.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepositoryV1;
    private final HelloTraceV1 trace;
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderServiceV1.orderItem()");
            orderRepositoryV1.save(itemId);
            trace.end(status);

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;  //예외를 꼭 다시 던저 주어야 한다.
        }
    }
}

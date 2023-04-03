package com.jpa.advanced.app.v2;

import com.jpa.advanced.trace.TraceId;
import com.jpa.advanced.trace.TraceStatus;
import com.jpa.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepositoryV2;
    private final HelloTraceV2 trace;
    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderServiceV1.orderItem()");
            orderRepositoryV2.save(status.getTraceId(), itemId);
            trace.end(status);

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;  //예외를 꼭 다시 던저 주어야 한다.
        }
    }
}

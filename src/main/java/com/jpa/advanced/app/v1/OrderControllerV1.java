package com.jpa.advanced.app.v1;

import com.jpa.advanced.trace.TraceStatus;
import com.jpa.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderControllerV1 {
    private final OrderServiceV1 orderServiceV1;
    private final HelloTraceV1 trace;
    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderServiceV1.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;  //예외를 꼭 다시 던저 주어야 한다.
        }
    }
}

package cc.davelee.trade.engine.controller;

import cc.davelee.trade.engine.dto.OrderDto;
import cc.davelee.trade.engine.dto.OrdersDto;
import cc.davelee.trade.engine.service.OrderService;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final static int TIME_OUT_MS = 50;

    @SuppressWarnings("UnstableApiUsage")
    RateLimiter rateLimiter = RateLimiter.create(50);
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderDto>> getOrder(@PathVariable String id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @SuppressWarnings("UnstableApiUsage")
    @PostMapping("/buy")
    public ResponseEntity<OrderDto> createBuyOrder(@RequestBody OrderDto newBuyOrder) {
        boolean isLimited = rateLimiter.tryAcquire(TIME_OUT_MS, TimeUnit.MILLISECONDS);
        if(!isLimited)
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        return new ResponseEntity<>(orderService.submitBuyOrder(newBuyOrder), HttpStatus.OK);
    }

    @PostMapping("/sell")
    @SuppressWarnings("UnstableApiUsage")

    public ResponseEntity<OrderDto> createSellOrder(@RequestBody OrderDto newSellOrder) {
        boolean isLimited = rateLimiter.tryAcquire(TIME_OUT_MS, TimeUnit.MILLISECONDS);
        if(!isLimited)
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        return new ResponseEntity<>(orderService.submitSellOrder(newSellOrder), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<OrdersDto> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
}

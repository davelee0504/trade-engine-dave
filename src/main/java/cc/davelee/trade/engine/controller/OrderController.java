package cc.davelee.trade.engine.controller;

import cc.davelee.trade.engine.dto.OrderDto;
import cc.davelee.trade.engine.dto.OrdersDto;
import cc.davelee.trade.engine.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderDto>> getOrder(@PathVariable String id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity<OrderDto> createBuyOrder(@RequestBody OrderDto newBuyOrder) {
        return new ResponseEntity<>(orderService.submitBuyOrder(newBuyOrder), HttpStatus.OK);
    }

    @PostMapping("/sell")
    public ResponseEntity<OrderDto> createSellOrder(@RequestBody OrderDto newSellOrder) {
        return new ResponseEntity<>(orderService.submitSellOrder(newSellOrder), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<OrdersDto> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
}

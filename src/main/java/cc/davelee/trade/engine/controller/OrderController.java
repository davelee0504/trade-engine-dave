package cc.davelee.trade.engine.controller;

import cc.davelee.trade.engine.dto.OrderDto;
import cc.davelee.trade.engine.dto.OrdersDto;
import cc.davelee.trade.engine.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String id) {
        orderService.getOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity<OrderDto> createBuyOrder(@RequestBody OrderDto newBuyOrder) {
        orderService.submitBuyOrder(newBuyOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sell")
    public ResponseEntity<OrderDto> createSellOrder(@RequestBody OrderDto newSellOrder) {
        orderService.submitSellOrder(newSellOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<OrdersDto> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }
}

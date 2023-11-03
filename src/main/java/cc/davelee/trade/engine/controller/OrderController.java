package cc.davelee.trade.engine.controller;

import cc.davelee.trade.engine.dto.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity<OrderDto> createBuyOrder() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sell")
    public ResponseEntity<OrderDto> createSellOrder() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

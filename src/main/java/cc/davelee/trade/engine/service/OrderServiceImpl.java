package cc.davelee.trade.engine.service;

import cc.davelee.trade.engine.dto.OrderDto;
import cc.davelee.trade.engine.dto.OrdersDto;
import cc.davelee.trade.engine.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {

    private final TradeEngine tradeEngine;

    @Autowired
    public OrderServiceImpl(TradeEngine tradeEngine) {
        this.tradeEngine = tradeEngine;
    }

    // submit order to trade engine
    public OrderDto submitBuyOrder(OrderDto orderDto) {
        Order order = new Order(orderDto.getPrice(), orderDto.getQuantity(), orderDto.getType(), orderDto.getTime(), false);
        tradeEngine.submitBuyOrder(order);
        return new OrderDto(order.getId(), order.getPrice(), order.getQuantity(), order.getType(), order.getTime());
    }

    public OrderDto submitSellOrder(OrderDto orderDto) {
        Order order = new Order(orderDto.getPrice(), orderDto.getQuantity(), orderDto.getType(), orderDto.getTime(), false);
        tradeEngine.submitSellOrder(order);
        return new OrderDto(order.getId(), order.getPrice(), order.getQuantity(), order.getType(), order.getTime());
    }

    public Optional<OrderDto> getOrder(String id) {
        return Stream.concat(tradeEngine.getSellOrders().stream(), tradeEngine.getBuyOrders().stream())
                .filter(order -> UUID.fromString(id).equals(order.getId()))
                .findFirst()
                .map(o -> new OrderDto(o.getId(), o.getPrice(), o.getQuantity(), o.getType(), o.getTime()));
    }

    public OrdersDto getAllOrders() {
        OrdersDto result = new OrdersDto();
        List<OrderDto> sellOrders = tradeEngine.getSellOrders().stream()
                .map(o -> new OrderDto(o.getId(), o.getPrice(), o.getQuantity(), o.getType(), o.getTime()))
                .toList();
        List<OrderDto> buyOrders = tradeEngine.getBuyOrders().stream()
                .map(o -> new OrderDto(o.getId(), o.getPrice(), o.getQuantity(), o.getType(), o.getTime()))
                .toList();
        result.setSellOrders(sellOrders);
        result.setBuyOrders(buyOrders);

        return result;
    }
}

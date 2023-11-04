package cc.davelee.trade.engine.service;

import cc.davelee.trade.engine.dto.OrderDto;
import cc.davelee.trade.engine.dto.OrdersDto;
import cc.davelee.trade.engine.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final TradeEngine tradeEngine;

    public void deleteOrder() {

    }

    @Autowired
    public OrderService(TradeEngine tradeEngine) {
        this.tradeEngine = tradeEngine;
    }

    // submit order to trade engine
    public void submitBuyOrder(OrderDto orderDto) {
        Order order = new Order(orderDto.getPrice(), orderDto.getQuantity(), orderDto.getType(), orderDto.getTime(), false);
        tradeEngine.submitBuyOrder(order);
    }

    public void submitSellOrder(OrderDto orderDto) {
        Order order = new Order(orderDto.getPrice(), orderDto.getQuantity(), orderDto.getType(), orderDto.getTime(), false);
        tradeEngine.submitSellOrder(order);
    }

    public OrderDto getOrder(String id) {
       return null;
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

package cc.davelee.trade.engine.service;

import cc.davelee.trade.engine.dto.OrderDto;
import cc.davelee.trade.engine.dto.OrdersDto;
import cc.davelee.trade.engine.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    // submit order to trade engine
    OrderDto submitBuyOrder(OrderDto orderDto);

    OrderDto submitSellOrder(OrderDto orderDto);

    Optional<OrderDto> getOrder(String id);

    OrdersDto getAllOrders();
}

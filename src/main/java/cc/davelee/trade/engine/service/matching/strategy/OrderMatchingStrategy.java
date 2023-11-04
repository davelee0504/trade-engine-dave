package cc.davelee.trade.engine.service.matching.strategy;

import cc.davelee.trade.engine.entity.Order;
import cc.davelee.trade.engine.entity.OrderType;
import cc.davelee.trade.engine.service.MatchResult;

import java.util.Objects;

public interface OrderMatchingStrategy {
    MatchResult match(Order sellOrder, Order buyOrder);

    default boolean checkOrder(Order sellOrder, Order buyOrder) {
        boolean isOk = true;

        if (sellOrder == null || buyOrder == null)
            return false;

        // for the LIMIT order, the buy order price < sell order price
        if (OrderType.LIMIT.equals(buyOrder.getType()) && sellOrder.getPrice().compareTo(buyOrder.getPrice()) > 0) {
            return false;
        }

        return isOk;
    }
}

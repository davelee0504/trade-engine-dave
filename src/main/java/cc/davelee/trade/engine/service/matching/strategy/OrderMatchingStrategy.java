package cc.davelee.trade.engine.service.matching.strategy;

import cc.davelee.trade.engine.entity.Order;
import cc.davelee.trade.engine.service.MatchResult;

import java.util.Objects;

public interface OrderMatchingStrategy {
    MatchResult match(Order sellOrder, Order buyOrder);

    default boolean checkOrder(Order sellOrder, Order buyOrder) {
        boolean isOk = true;

        if(sellOrder == null || buyOrder == null)
            return false;

        // buy price < sell price
        if (buyOrder.getPrice().compareTo(sellOrder.getPrice()) < 0) {
            return false;
        }

        return isOk;
    }
}

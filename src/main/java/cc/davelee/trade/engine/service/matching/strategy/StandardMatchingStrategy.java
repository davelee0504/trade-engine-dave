package cc.davelee.trade.engine.service.matching.strategy;

import cc.davelee.trade.engine.entity.Order;
import cc.davelee.trade.engine.service.MatchResult;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@ConditionalOnProperty(name = "trade.engine.matching.strategy", havingValue = "STD", matchIfMissing = true)
public class StandardMatchingStrategy implements OrderMatchingStrategy {
    @Override
    public MatchResult match(Order sellOrder, Order buyOrder) {
        if (!checkOrder(sellOrder, buyOrder))
            return MatchResult.NO_MATCHED_RESULT;

        // calculate quantity and price for matching order
        int quantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
        BigDecimal price = buyOrder.getPrice().add(sellOrder.getPrice()).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);

        return new MatchResult(true, quantity, price);
    }
}

package cc.davelee.trade.engine.service.matching.strategy;

import cc.davelee.trade.engine.entity.Order;
import cc.davelee.trade.engine.service.MatchResult;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "trade.engine.matching.strategy", havingValue = "FULL")
public class FullMatchingStrategy implements OrderMatchingStrategy {
    @Override
    public MatchResult match(Order sellOrder, Order buyOrder) {
        if (!checkOrder(sellOrder, buyOrder))
            return MatchResult.NO_MATCHED_RESULT;

        // we only want full match in this strategy here
        if (buyOrder.getQuantity().compareTo(sellOrder.getQuantity()) != 0) {
            return MatchResult.NO_MATCHED_RESULT;
        }

        return new MatchResult(true, sellOrder.getQuantity(), sellOrder.getPrice());
    }
}

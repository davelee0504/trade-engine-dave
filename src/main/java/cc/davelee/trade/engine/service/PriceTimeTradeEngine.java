package cc.davelee.trade.engine.service;

import cc.davelee.trade.engine.entity.Order;
import cc.davelee.trade.engine.service.matching.strategy.OrderMatchingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

@Component
@Slf4j
public class PriceTimeTradeEngine extends TradeEngine {

    // price desc, time asc
    Comparator<Order> buyOrderComparator = (o1, o2) -> {
        BigDecimal o1Price = o1.getPrice();
        BigDecimal o2Price = o2.getPrice();

        if (!o1Price.equals(o2Price))
            return o2Price.compareTo(o1Price);
        else
            return o1.getTime().compareTo(o2.getTime());
    };

    // price asc, time asc
    Comparator<Order> sellOrderComparator = (o1, o2) -> {
        BigDecimal o1Price = o1.getPrice();
        BigDecimal o2Price = o2.getPrice();

        if (!o1Price.equals(o2Price))
            return o1Price.compareTo(o2Price);
        else
            return o1.getTime().compareTo(o2.getTime());
    };

    public PriceTimeTradeEngine(OrderMatchingStrategy matchingStrategy) {
        super(matchingStrategy);
        sellOrders = new PriorityBlockingQueue<>(MAX_ORDER_SIZE, sellOrderComparator);
        buyOrders = new PriorityBlockingQueue<>(MAX_ORDER_SIZE, buyOrderComparator);
    }

    @Override
    public void processOrders() {
        log.info("Start processing order...");

        Order buyOrder, sellOrder;

        try {
            buyOrder = buyOrders.take();
            log.debug("BUY order taken " + buyOrder);
            sellOrder = sellOrders.take();
            log.debug("SELL order taken " + sellOrder);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //proceed only if the buy order and the sell order not null. Otherwise blocked
        MatchResult result = matchingStrategy.match(sellOrder, buyOrder);
        log.info(result.toString());

        updateOrder(sellOrder, getSellOrders(), result);
        updateOrder(buyOrder, getBuyOrders(), result);

        log.info("End of processing order.");
    }

    private void updateOrder(Order order, PriorityBlockingQueue<Order> orders, MatchResult result) {

        if (result.isMatched()) {
            // fully matched
            if (order.getQuantity() == result.getFilledQuantity()) {
                order.setFilled(true);
                orders.remove(order);
                log.info(order + "is fully filled");
            } else {
                //partially matched
                order.setQuantity(order.getQuantity() - result.getFilledQuantity());
                orders.put(order);
                log.info(order + "is partially(" + result.getFilledQuantity() + ") filled");
            }
        } else {
            orders.put(order);
            log.info(order + "is not filled");
        }
    }
}

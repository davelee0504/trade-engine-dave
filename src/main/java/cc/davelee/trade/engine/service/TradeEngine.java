package cc.davelee.trade.engine.service;

import cc.davelee.trade.engine.entity.Order;
import cc.davelee.trade.engine.service.matching.strategy.OrderMatchingStrategy;
import lombok.Getter;

import java.util.concurrent.PriorityBlockingQueue;

public abstract class TradeEngine {

    protected final static int MAX_ORDER_SIZE = 100;

    @Getter
    protected PriorityBlockingQueue<Order> sellOrders;

    @Getter
    protected PriorityBlockingQueue<Order> buyOrders;

    OrderMatchingStrategy matchingStrategy;

    public TradeEngine(OrderMatchingStrategy matchingStrategy) {
        this.matchingStrategy = matchingStrategy;
    }

    public boolean submitBuyOrder(Order order) {
        return buyOrders.offer(order);
    }

    public boolean submitSellOrder(Order order) {
        return sellOrders.offer(order);
    }

    abstract public void processOrders();

}

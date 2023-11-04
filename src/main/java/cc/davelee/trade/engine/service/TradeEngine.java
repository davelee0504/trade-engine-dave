package cc.davelee.trade.engine.service;

import cc.davelee.trade.engine.entity.Order;
import cc.davelee.trade.engine.service.matching.strategy.OrderMatchingStrategy;
import lombok.Getter;

import java.util.List;
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

    public void submitBuyOrder(List<Order> orders) {
        buyOrders.addAll(orders);
    }

    public void submitBuyOrder(Order order) {
        buyOrders.offer(order);
    }

    public void submitSellOrder(Order order) {
        sellOrders.offer(order);
    }

    abstract public void processOrders();

}

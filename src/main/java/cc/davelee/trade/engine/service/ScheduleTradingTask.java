package cc.davelee.trade.engine.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduleTradingTask {

    TradeEngine engine;
    @Autowired
    public ScheduleTradingTask(TradeEngine tradeEngine) {
        this.engine = tradeEngine;
    }

    @Scheduled(fixedRate = 2000, initialDelay = 20000)
    public void matching() {
        log.info("Start matching orders...");
        engine.processOrders();
    }

    //TODO: delete expired order
    @Scheduled(fixedRate = 2000)
    public void removeExpiredOrder() {

    }
}

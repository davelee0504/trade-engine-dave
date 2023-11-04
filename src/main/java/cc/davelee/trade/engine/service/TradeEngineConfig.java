package cc.davelee.trade.engine.service;

import cc.davelee.trade.engine.service.matching.strategy.FullMatchingStrategy;
import cc.davelee.trade.engine.service.matching.strategy.StandardMatchingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TradeEngineConfig {

    @Value("${trade.engine.name}")
    String engineName;

    @Bean(name = "tradeEngine")
    public TradeEngine tradeEngine(){
        if("basicTradeEngine".equals(engineName)) {
//            return new PriceTimeTradeEngine(new FullMatchingStrategy());
            return new PriceTimeTradeEngine(new StandardMatchingStrategy());
        }
        else {
            throw new IllegalArgumentException("Unexpected Trade Engine Name!");
        }
    }

}

package cc.davelee.trade.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeEngineDaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeEngineDaveApplication.class, args);
	}

}

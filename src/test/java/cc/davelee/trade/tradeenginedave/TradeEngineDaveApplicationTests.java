package cc.davelee.trade.tradeenginedave;

import cc.davelee.trade.engine.entity.Order;
import cc.davelee.trade.engine.entity.OrderType;
import cc.davelee.trade.engine.service.PriceTimeTradeEngine;
import cc.davelee.trade.engine.service.TradeEngine;
import cc.davelee.trade.engine.service.matching.strategy.FullMatchingStrategy;
import cc.davelee.trade.engine.service.matching.strategy.StandardMatchingStrategy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;


class TradeEngineDaveApplicationTests {

	TradeEngine engine;

	static final Order SELL_ORDER_1 = new Order(new BigDecimal("20.30"), 100, OrderType.MARKET);
	static final Order SELL_ORDER_2 = new Order(new BigDecimal("20.25"), 100, OrderType.MARKET);
	static final Order SELL_ORDER_3 = new Order(new BigDecimal("20.30"), 100, OrderType.MARKET);

	static final Order BUY_ORDER_1 = new Order(new BigDecimal("20.15"), 100, OrderType.MARKET);
	static final Order BUY_ORDER_2 = new Order(new BigDecimal("20.20"), 100, OrderType.MARKET);
	static final Order BUY_ORDER_3 = new Order(new BigDecimal("20.15"), 100, OrderType.MARKET);

	static final Order BUY_ORDER_4 = new Order(new BigDecimal("20.15"), 90, OrderType.MARKET);


	@Test
	public void testSellOrders() throws Exception {
		engine = new PriceTimeTradeEngine(new FullMatchingStrategy());

		engine.getSellOrders().put(SELL_ORDER_1); Thread.sleep(100);
		engine.getSellOrders().put(SELL_ORDER_2); Thread.sleep(100);
		engine.getSellOrders().put(SELL_ORDER_3);

		assertEquals("expected order o2", SELL_ORDER_2, engine.getSellOrders().poll());
		assertEquals("expected order o1", SELL_ORDER_1, engine.getSellOrders().poll());
		assertEquals("expected order o3", SELL_ORDER_3, engine.getSellOrders().poll());
		assertTrue("order book not empty", engine.getSellOrders().peek() == null);
	}

	@Test
	public void testBuyOrders() throws Exception {
		engine = new PriceTimeTradeEngine(new FullMatchingStrategy());

		engine.getBuyOrders().put(BUY_ORDER_1); Thread.sleep(100);
		engine.getBuyOrders().put(BUY_ORDER_2); Thread.sleep(100);
		engine.getBuyOrders().put(BUY_ORDER_3);

		assertEquals("expected order o2", BUY_ORDER_2, engine.getBuyOrders().poll());
		assertEquals("expected order o1", BUY_ORDER_1, engine.getBuyOrders().poll());
		assertEquals("expected order o3", BUY_ORDER_3, engine.getBuyOrders().poll());
		assertTrue("order book not empty", engine.getBuyOrders().peek() == null);
	}


	@Test
	public void test_standard_match() {
		engine = new PriceTimeTradeEngine(new StandardMatchingStrategy());

		engine.getBuyOrders().put(BUY_ORDER_4);

		final Order BUY_ORDER_Q_30 = new Order(BUY_ORDER_4.getPrice(), 30, OrderType.MARKET);
		engine.getSellOrders().put(BUY_ORDER_Q_30);
		engine.processOrders();
		assertEquals("expected order has 60 quantity left", 60, engine.getBuyOrders().peek().getQuantity());
		engine.getSellOrders().put(BUY_ORDER_Q_30);
		engine.processOrders();
		assertEquals("expected order has 30 quantity left", 30, engine.getBuyOrders().peek().getQuantity());
		engine.getSellOrders().put(BUY_ORDER_Q_30);
		engine.processOrders();
		assertTrue("buy order should be empty", engine.getBuyOrders().peek() == null);
	}

	@Test
	public void test_full_match() {
		engine = new PriceTimeTradeEngine(new FullMatchingStrategy());

		engine.getBuyOrders().put(BUY_ORDER_4);
		final Order BUY_ORDER_Q_30 = new Order(BUY_ORDER_4.getPrice(), 30, OrderType.MARKET);
		engine.getSellOrders().put(BUY_ORDER_Q_30);
		engine.processOrders();
		assertEquals("expected order still has 90 quantity left", 90, engine.getBuyOrders().peek().getQuantity());

		// decrease one dollar to make order matched
		final Order BUY_ORDER_Q_90 = new Order(BUY_ORDER_4.getPrice().min(BigDecimal.ONE), 90, OrderType.MARKET);
		engine.getSellOrders().put(BUY_ORDER_Q_90);
		engine.processOrders();
		assertTrue("Buy order should be empty", engine.getBuyOrders().peek() == null);
	}
}

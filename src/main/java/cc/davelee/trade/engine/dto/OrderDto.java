package cc.davelee.trade.engine.dto;

import cc.davelee.trade.engine.entity.OrderType;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class OrderDto {

    UUID id;
    BigDecimal price;
    Integer quantity;
    OrderType type;
    LocalDateTime time;
}

package cc.davelee.trade.engine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Order {

    @Id
    UUID id;
    BigDecimal price;
    Integer quantity;
    OrderType type;
    LocalDateTime time;
}

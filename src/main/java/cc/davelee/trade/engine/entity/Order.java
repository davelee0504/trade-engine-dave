package cc.davelee.trade.engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    UUID id = UUID.randomUUID();
    BigDecimal price;
    Integer quantity;
    OrderType type;
    LocalDateTime time = LocalDateTime.now();

    @JsonIgnore
    boolean isFilled;

    public Order(BigDecimal price, Integer quantity, OrderType type) {
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public Order(BigDecimal price, Integer quantity, OrderType type, LocalDateTime time, boolean isFilled) {
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.time = time;
        this.isFilled = isFilled;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", type=" + type +
                ", time=" + time +
                ", isFilled=" + isFilled +
                '}';
    }
}

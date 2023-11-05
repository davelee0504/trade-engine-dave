package cc.davelee.trade.engine.dto;

import cc.davelee.trade.engine.entity.OrderType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    UUID id;
    BigDecimal price;
    Integer quantity;
    OrderType type;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime time = LocalDateTime.now();
}

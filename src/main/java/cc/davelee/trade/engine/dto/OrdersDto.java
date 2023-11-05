package cc.davelee.trade.engine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {
    List<OrderDto> sellOrders = new ArrayList<>();

    List<OrderDto> buyOrders = new ArrayList<>();
}

package cc.davelee.trade.engine.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MatchResult {
    public static final MatchResult NO_MATCHED_RESULT = new MatchResult();

    private boolean isMatched = false;

    private int filledQuantity = 0;

    private BigDecimal fillPrice = BigDecimal.ZERO;

    @Override
    public String toString() {
        return "MatchResult{" +
                "isMatched=" + isMatched +
                ", filledQuantity=" + filledQuantity +
                ", fillPrice=" + fillPrice +
                '}';
    }
}

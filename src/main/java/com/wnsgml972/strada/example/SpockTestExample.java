package com.wnsgml972.strada.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpockTestExample {
    public static long calculate(long amount, float rate, RoundingMode roundingMode) {
        return BigDecimal.valueOf(amount * rate * 0.01)
                .setScale(0, roundingMode).longValue();
    }
}

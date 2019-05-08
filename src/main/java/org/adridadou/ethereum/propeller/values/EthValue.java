package org.adridadou.ethereum.propeller.values;


import org.adridadou.ethereum.propeller.exception.EthereumApiException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Created by davidroon on 06.11.16.
 * This code is released under Apache 2 license
 */
public class EthValue implements Comparable<EthValue> {
    private static final BigDecimal ETHER_CONVERSION = BigDecimal.valueOf(1_000_000_000_000_000_000L);
    private final BigDecimal value;

    public EthValue(BigInteger value) {
        if (value.signum() == -1) throw new EthereumApiException("a value cannot be negative");
        this.value = new BigDecimal(value);
    }

    public static EthValue ether(final BigInteger value) {
        return wei(value.multiply(ETHER_CONVERSION.toBigInteger()));
    }

    public static EthValue ether(final Double value) {
        return ether(BigDecimal.valueOf(value));
    }

    public static EthValue ether(final BigDecimal value) {
        return wei(ETHER_CONVERSION.multiply(value).toBigInteger());
    }

    public static EthValue ether(final long value) {
        return ether(BigInteger.valueOf(value));
    }

    public static EthValue wei(final int value) {
        return wei(BigInteger.valueOf(value));
    }

    public static EthValue wei(final long value) {
        return wei(BigInteger.valueOf(value));
    }

    public static EthValue wei(final BigInteger value) {
        return new EthValue(value);
    }

    public BigInteger inWei() {
        return value.toBigInteger();
    }

    public BigDecimal inEth() {
        return value
                .divide(ETHER_CONVERSION, RoundingMode.HALF_DOWN);
    }

    public boolean isZero() {
        return inWei().signum() != 1;
    }

    public EthValue plus(EthValue value) {
        return new EthValue(this.value.add(value.value).toBigInteger());
    }

    public EthValue minus(EthValue value) {
        return new EthValue(this.value.subtract(value.value).toBigInteger());
    }

    @Override
    public int compareTo(EthValue o) {
        return value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object o) {
        return o != null && Objects.equals(value, ((EthValue) o).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value + " Wei";
    }
}

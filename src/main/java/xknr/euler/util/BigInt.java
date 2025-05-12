package xknr.util;

import java.math.BigInteger;

public class BigInt {
  public static final BigInteger B(String n) {
    return new BigInteger(n);
  }
  public static final BigInteger B(long n) {
    return BigInteger.valueOf(n);
  }

  public static final boolean less(BigInteger a, BigInteger b) {
    return a.compareTo(b) < 0;
  }

  public static final BigInteger B0 = BigInteger.ZERO;
  public static final BigInteger B1 = BigInteger.ONE;
  public static final BigInteger B2 = B(2);
}

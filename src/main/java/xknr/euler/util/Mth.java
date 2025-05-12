package xknr.euler.util;

public class Mth {

  public static long gcd(long a, long b) {
    while (b != 0) {
      long r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  public static int count2(long n) {
    int result = 0;
    for(; n % 2 == 0; n /= 2) 
      ++result;
    return result;
  }

  public static int getPowerCount(long n, long p) {
    int cnt = 0;
    for(; n % p == 0; n /= p) 
      ++cnt;
    return n == 1 ? cnt : 0;
  }

  public static long factorial(long n) {
    assert n >= 0;
    long result = 1;
    for(long i = 2; i <= n; i++)
      result *= i;
    return result;
  }

  public static boolean isPowerOf2(long n) {
    if (n <= 0)
      return false;
    long pattern = n & (n - 1);
    return pattern == 0;
  }

  public static long pow(long base, int power) {
      if (power < 0) {
          throw new IllegalArgumentException("Power must be non-negative.");
      }
      long result = 1;
      while (power > 0) {
          if ((power & 1) == 1) { // If power is odd
              result *= base;
          }
          base *= base;
          power >>= 1; // Divide power by 2
      }
      return result;
  }

}

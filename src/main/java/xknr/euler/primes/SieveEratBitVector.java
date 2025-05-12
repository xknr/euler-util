package xknr.euler.primes;

public class SieveEratBitVector 
{
  private final long[] bitVector;
  private final long limit;

  public long limit() {
    return limit;
  }

  public SieveEratBitVector(long limit) {
      this.limit = limit;
      int size = (int)((limit / 2 + 63) / 64); // Only odd numbers, 1 bit per odd number
      bitVector = new long[size];
      sieve();
  }
  
  private void sieve() {
    long sqrtLimit = (long) Math.sqrt(limit);
    for (long i = 3; i <= sqrtLimit; i += 2) {
        if (isPrime(i)) {
            for (long j = i * i; j <= limit; j += i * 2) {
              final long index = (j >>> 1) - 1;
              bitVector[(int)(index >>> 6)] |= (1L << (index & 63));
            }
        }
    }
  }

  public boolean isPrime(long n) {
    if (n <= 2) {
      // negative, 0, 1 is not prime
      // 2 is prime
      return n == 2;
    }

    // Even numbers (above 2) are not prime.
    if ((n & 1) == 0) 
      return false;

    if (n > limit) 
      throw new IllegalArgumentException(String.format("n is out of range: %d > %d", n, limit));

    long index = (n >>> 1) - 1;
    return (bitVector[(int)(index >>> 6)] & (1L << (index & 63))) == 0;
  }
  
  public static void main() {
    long limit = 7_000_000_000L;
    limit = 100_000_000L;
      SieveEratBitVector sieve = new SieveEratBitVector(limit);
      for (int i = 2; i <= 1000; i++) {
          if (sieve.isPrime(i)) {
              System.out.print(i + " ");
          }
      }
  }
}

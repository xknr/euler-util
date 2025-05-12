package xknr.euler.primes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class SieveEratBitVectorTest {
    
    @Test
    public void testConstructorAndLimit() {
        long expectedLimit = 1000;
        SieveEratBitVector sieve = new SieveEratBitVector(expectedLimit);
        assertEquals(expectedLimit, sieve.limit(), "Limit should match the constructor parameter");
    }
    
    @Test
    public void testSmallPrimes() {
        SieveEratBitVector sieve = new SieveEratBitVector(100);
        
        // Check known small primes
        assertTrue(sieve.isPrime(2), "2 should be prime");
        assertTrue(sieve.isPrime(3), "3 should be prime");
        assertTrue(sieve.isPrime(5), "5 should be prime");
        assertTrue(sieve.isPrime(7), "7 should be prime");
        assertTrue(sieve.isPrime(11), "11 should be prime");
        assertTrue(sieve.isPrime(13), "13 should be prime");
        assertTrue(sieve.isPrime(17), "17 should be prime");
        assertTrue(sieve.isPrime(19), "19 should be prime");
        assertTrue(sieve.isPrime(23), "23 should be prime");
        assertTrue(sieve.isPrime(29), "29 should be prime");
        assertTrue(sieve.isPrime(31), "31 should be prime");
    }
    
    @Test
    public void testNonPrimes() {
        SieveEratBitVector sieve = new SieveEratBitVector(100);
        
        // Check some non-primes
        assertFalse(sieve.isPrime(1), "1 should not be prime");
        assertFalse(sieve.isPrime(4), "4 should not be prime");
        assertFalse(sieve.isPrime(6), "6 should not be prime");
        assertFalse(sieve.isPrime(8), "8 should not be prime");
        assertFalse(sieve.isPrime(9), "9 should not be prime");
        assertFalse(sieve.isPrime(10), "10 should not be prime");
        assertFalse(sieve.isPrime(12), "12 should not be prime");
        assertFalse(sieve.isPrime(15), "15 should not be prime");
        assertFalse(sieve.isPrime(25), "25 should not be prime");
    }
    
    @Test
    public void testEdgeCases() {
        SieveEratBitVector sieve = new SieveEratBitVector(100);
        
        // Test edge cases
        assertFalse(sieve.isPrime(0), "0 should not be prime");
        assertFalse(sieve.isPrime(-1), "Negative numbers should not be prime");
        assertFalse(sieve.isPrime(-10), "Negative numbers should not be prime");
    }
    
    @ParameterizedTest(name = "Prime number {0} should be identified correctly")
    @ValueSource(longs = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97})
    public void testKnownPrimes(long prime) {
        SieveEratBitVector sieve = new SieveEratBitVector(100);
        assertTrue(sieve.isPrime(prime), prime + " should be prime");
    }
    
    @ParameterizedTest(name = "Non-prime number {0} should be identified correctly")
    @ValueSource(longs = {1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30})
    public void testKnownNonPrimes(long nonPrime) {
        SieveEratBitVector sieve = new SieveEratBitVector(100);
        assertFalse(sieve.isPrime(nonPrime), nonPrime + " should not be prime");
    }
    
    @Test
    public void testLargerLimit() {
        SieveEratBitVector sieve = new SieveEratBitVector(10000);
        
        // Test some larger primes
        assertTrue(sieve.isPrime(9973), "9973 should be prime");
        assertTrue(sieve.isPrime(7919), "7919 should be prime");
        assertTrue(sieve.isPrime(5003), "5003 should be prime");
        
        // Test some larger non-primes
        assertFalse(sieve.isPrime(9999), "9999 should not be prime");
        assertFalse(sieve.isPrime(8192), "8192 should not be prime");
        assertFalse(sieve.isPrime(10000), "10000 should not be prime");
    }
    
    @Test
    public void testPrimesNearLimitBoundary() {
        long limit = 1000;
        SieveEratBitVector sieve = new SieveEratBitVector(limit);
        
        // Test primes near the limit
        assertTrue(sieve.isPrime(997), "997 should be prime");
        
        // Test that an exception is thrown for numbers beyond the limit
        assertThrows(IllegalArgumentException.class, () -> {
            sieve.isPrime(1001);
        }, "Should throw IllegalArgumentException for numbers > limit");
    }
    @ParameterizedTest
    @CsvSource({
        "2,true", 
        "3,true", 
        "4,false", 
        "5,true", 
        "6,false", 
        "7,true", 
        "8,false", 
        "9,false", 
        "10,false", 
        "11,true"
    })
    public void testMixedNumbers(long number, boolean expected) {
        SieveEratBitVector sieve = new SieveEratBitVector(100);
        assertEquals(expected, sieve.isPrime(number), 
                     number + (expected ? " should be prime" : " should not be prime"));
    }
    
    @Test
    public void testPrimesCount() {
        // Testing against known prime counting function values
        SieveEratBitVector sieve = new SieveEratBitVector(1000);
        int primeCount = 0;
        for (int i = 2; i <= 1000; i++) {
            if (sieve.isPrime(i)) {
                primeCount++;
            }
        }
        assertEquals(168, primeCount, "There should be 168 primes up to 1000");
    }

    @Test
    public void testAgainstKnownPrimesList() {
        // Create a sieve with limit that covers all primes in FirstPrimes
        SieveEratBitVector sieve = new SieveEratBitVector(FirstPrimes.last() + 1);

        // Test all primes from FirstPrimes
        for (Integer prime : FirstPrimes.PRIMES) {
            assertTrue(sieve.isPrime(prime), prime + " should be prime according to FirstPrimes list");
        }

        // Test all numbers up to the last prime
        for (int i = 2; i <= FirstPrimes.last(); i++) {
            boolean isPrimeInList = FirstPrimes.PRIMES_SET.contains(i);
            boolean isPrimeInSieve = sieve.isPrime(i);

            assertEquals(isPrimeInList, isPrimeInSieve,
                    "For number " + i + ", FirstPrimes and SieveEratBitVector disagree on primality");
        }

        // Additional test for consecutive numbers
        for (int i = 3; i < FirstPrimes.last(); i++) {
            if (FirstPrimes.PRIMES_SET.contains(i) && FirstPrimes.PRIMES_SET.contains(i+1)) {
                fail("Found consecutive primes " + i + " and " + (i+1) +
                     " in FirstPrimes, which is mathematically impossible except for 2,3");
            }
        }
    }
}


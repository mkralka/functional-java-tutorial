# Lambda Expressions

## Solution: Define a Block-statement Lambda Expression

``` java
public LongUnaryOperator fibonacci() {
    return n -> {
        long a = 0, b = 1;
        for (long i = 0; i < n; ++i) {
            b = b + a;
            a = b - a;
        }
        return a;
    };
}
```

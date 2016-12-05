# Lambda Expressions

## Solution: Define a Block-statement Lambda Expression with Multiple Parameters

``` java
public BiFunction<String, Integer, String> stringMultiplier() {
    return (str, count) -> {
        int intCount = count;     // Unbox once.
        StringBuilder result = new StringBuilder(Math.max(intCount * str.length(), 1));
        for (int i = 0; i < intCount; ++i) {
            result.append(str);
        }
        return result.toString();
    };
}
```

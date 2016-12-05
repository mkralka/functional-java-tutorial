# Streams

## Solution: Transforming a List

``` java
public List<Integer> squareIntegerList(List<Integer> integers) {
    return integers.stream()
            .map(x -> x * x)
            .collect(Collectors.toList());
}
```

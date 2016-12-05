# Method References

## Solution: Bound Method Reference

``` java
public Function<String, Map<String, String>> mapSplitter(
        char entrySeparator,
        char keyValueSeparator) {
    Splitter.MapSplitter mapSplitter = Splitter.on(entrySeparator)
            .omitEmptyStrings()
            .trimResults()
            .withKeyValueSeparator(Splitter.on(keyValueSeparator).trimResults());
    return mapSplitter::split;
}
```

# Streams

## Exercise: Transforming a List

In this exercise we will transform the elements of a `List`, creating a new
`List` of equal length, maintaining order.

Open
[`src/main/java/functionaljava/Streaming.java`](../../src/main/java/functionaljava/Streaming.java)
and replace the body of `squareIntegerList` with one that returns a new `List`
created by squaring each element (multiplying by itself) using streams. Your
solution should create a `Stream` from the `List` using `stream()`, transforming
each element of the stream using `map()`, and collect the results into a `List`
using `collect()` and
[`Collectors.toList()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toList--).

To test your solution, run the following command:

``` bash
mvn test -Dtest=StreamingTest#testSquareIntegerList
```

[Solution](transforming_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](filtering.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

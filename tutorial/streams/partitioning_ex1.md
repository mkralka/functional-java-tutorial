# Streams

## Exercise: Partition a Stream and Reduce

In this exercise we will partition a stream in two, counting the number of
elements in each partition.

Open
[`src/main/java/functionaljava/Streaming.java`](../../src/main/java/functionaljava/Streaming.java)
and replace the body of `percentageStartedBefore` with one that returns the
percentage (from 0.0 to 1.0) of employees that started before (but not on) the
provided date. If all employees started before the provided date, 1.0 should be
returned. If all employees started on or after the provided date, 0.0 should be
returned.

[`Collectors.partitioningBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#partitioningBy-java.util.function.Predicate-java.util.stream.Collector-)
can be used along with
[`Collectors.counting`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#counting--)
to create a `Collector` to reduce the stream using
[`Stream.collect`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-).

This will produce the number of elements that started before the provided date
and the number who started on or after the provided date. These counts can be
used to calculate the percentage.

To test your solution, run the following command:

``` bash
mvn test -Dtest=StreamingTest#testPercentageStartedBefore
```

[Solution](partitioning_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](min_max.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

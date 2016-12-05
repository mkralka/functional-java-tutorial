# Streams

## Exercise: Find the Maximum Element by Group

In this exercise we will find the maximum element for those that share the same
value for an arbitrary value calculated from each element.

Open
[`src/main/java/functionaljava/Streaming.java`](../../src/main/java/functionaljava/Streaming.java)
and replace the body of `newestEmployeeByOffice` with one that finds the newest
employee (has the most recent start date) in each office. If two or more
employees share the earliest start date, any of these employees may represent
the office. All offices in the result must have a "newest" employee (i.e., only
offices found in the employee records need to be considered).

The solution to the [previous exercise](grouping_ex1.md) will come in handy; we
will just need to replace the `Collector` with one that finds the newest
employee.

To create this `Collector` we can use
[`Collectors.maxBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#maxBy-java.util.Comparator-),
which *almost* does what we need. Since a stream can be empty, a `Collector`
that reduces a stream to a single element will evaluates to an `Optional`
instead of an element from the stream. However, since a group never collects
zero elements, the `Optional` can be safely unwrapped in our case.

[`Collectors.collectingAndThen`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#collectingAndThen-java.util.stream.Collector-java.util.function.Function-)
can be used to attach a *finishing* function to the `Collector` created by
`Collectors.maxBy` that will unwrap the `Optional` it generates.

``` bash
mvn test -Dtest=StreamingTest#testNewestEmployeeByOffice
```

[Solution](grouping_ex2_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](partitioning.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

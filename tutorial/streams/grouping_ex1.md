# Streams

## Exercise: Group and Count Elements

In this exercise we will count the number of elements that share the same value
for an arbitrary value calculated from each element.

Open
[`src/main/java/functionaljava/Streaming.java`](../../src/main/java/functionaljava/Streaming.java)
and replace the body of `employeeCountByOffice` with one that returns the number
of employees who work out of each office. All entries in the result should
contain non-zero values (i.e., only offices found in the employee records need
to be considered).

[`Collectors.groupingBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-java.util.stream.Collector-)
can be used along with
[`Collectors.counting`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#counting--)
to create a `Collector` to reduce the stream using
[`Stream.collect`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-).

To test your solution, run the following command:

``` bash
mvn test -Dtest=StreamingTest#testEmployeeCountByOffice
```

[Solution](grouping_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](grouping_ex2.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

# Streams

### Bonus Exercise: Find the Minimum Elements

*This is an optional, __bonus__ exercise.*

In this exercise we will find the minimum elements of a list, based on an
arbitrary comparison operation, in a single pass.

This exercise is a variant of [Find the Minimal Elements](min_max_ex2.md).

Open
[`src/main/java/functionaljava/Streaming.java`](../../src/main/java/functionaljava/Streaming.java)
and replace the body of `mostSeniorEmployees` (the `Stream` variant) with one
that returns the employees with the earliest start date in a single pass through
the elements of the stream. If there are no employee records, an empty `Set`
should be returned.

Consider implementing a custom `Collector` that stores all the employees that
are the most senior that have been seen so far.

``` bash
mvn test -Dtest=StreamingTest#testMostSeniorEmployeesStream
```

[Solution](min_max_ex3_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](end.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

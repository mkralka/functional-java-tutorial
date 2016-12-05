# Streams

## Exercise: Find the Minimum Element

In this exercise we will find the minimum element of a list based on an
arbitrary comparison operation.

Open
[`src/main/java/functionaljava/Streaming.java`](../../src/main/java/functionaljava/Streaming.java)
and replace the body of `mostSeniorEmployee` with one that returns the employee
with the earliest start date. If there are no employee records, an empty
`Optional` should be returned. If multiple employees have the same start date,
the one that appears first in the input should be returned.

[`Comparator.comparing`](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#comparing-java.util.function.Function-)
can be used to create a suitable `Comparator<Employee>` that compares employees
by start date. This can be used with
[`Stream.min`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#min-java.util.Comparator-)
to find the minimum element of a stream.

To test your solution, run the following command:

``` bash
mvn test -Dtest=StreamingTest#testMostSeniorEmployee
```

[Solution](min_max_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](min_max_ex2.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

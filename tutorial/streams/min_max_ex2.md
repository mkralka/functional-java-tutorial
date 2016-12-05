# Streams

## Exercise: Find the Minimum Elements

In this exercise we will find all of the minimum elements of a list based on an
arbitrary comparison operation.

Open
[`src/main/java/functionaljava/Streaming.java`](../../src/main/java/functionaljava/Streaming.java)
and replace the body of `mostSeniorEmployees` with one that returns all of the
employees that share the earliest start date. If there are no employee records,
an empty `Set` should be returned.

Don't be fooled by the similarities with the [previous exercise](min_max_ex1.md)
that required finding a single element of the stream. This calls for a much
different solution.

A simple way to find all minimal elements without sorting is to make a pass
through the stream to find the minimal element then making a second pass through
the list to remove all but the minimal elements.

To test your solution, run the following command:

``` bash
mvn test -Dtest=StreamingTest#testMostSeniorEmployees
```

[Solution](min_max_ex2_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](min_max_ex3.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

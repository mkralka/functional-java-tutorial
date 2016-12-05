# Streams

## Exercise: Filtering and Transforming a List

In this exercise we will filter out elements of a `List` then transform the
remaining elements, creating a new, possibly shorter, collection.

Open
[`src/main/java/functionaljava/Streaming.java`](../../src/main/java/functionaljava/Streaming.java)
and replace the body of `employeeFamilyNamesAtOffice` with one that returns the
(unique) family names of the employees that work out of a given office. Your
solution should should create a `Stream` from the `Collection` using `stream()`,
filtering out employees not from the provided office using `filter()`,
transforming the remaining elements `map()`, and collecting (and
de-duplicating) the results into a `Set` using `collect()` and
[`Collectors.toSet()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toSet--).

To test your solution, run the following command:

``` bash
mvn test -Dtest=StreamingTest#testEmployeeFamilyNamesAtOffice
```

[Solution](filtering_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](sorting.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

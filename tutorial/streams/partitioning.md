# Streams

## Partitioning Elements

Partitioning a stream is a special case of grouping where the elements are
split into exactly two groups with keys `TRUE` and `FALSE`.

Partitioning is performed by using the
[`partitioningBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#partitioningBy-java.util.function.Predicate-)
operation (which behaves similarly to
[`groupingBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-)).

### Exercises

1. [Partition a Stream and Reduce](partitioning_ex1.md)

---

[Continue](min_max.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

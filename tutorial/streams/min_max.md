# Streams

## Calculating the Min/Max Elements

Calculating the extreme (minimum/maximum) elements in a stream can be
accomplished in a few ways. The `Stream`s itself has
[`min`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#min-java.util.Comparator-)
and
[`max`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#max-java.util.Comparator-)
operations. This is probably fine for most use cases, but can be restrictive
when combining with grouping and partitioning.

Since grouping/partitioning is a collect operation, finding the minimum/maximum
of a group/partition must be performed using a `Collector`. Fortunately,
[`minBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#minBy-java.util.Comparator-)
and
[`maxBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#maxBy-java.util.Comparator-)
`Collectors` are available to reduce a stream.

### Exercises

1. [Find the Minimum Element](min_max_ex1.md)
2. [Find the Minimum Elements](min_max_ex2.md)
3. [Find the Minimum Elements](min_max_ex3.md) (Bonus)

---

[Continue](end.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

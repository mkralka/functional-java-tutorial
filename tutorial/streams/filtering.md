# Streams

## Filtering

Not all of the elements of a stream are important, making the ability to filter
out unwanted elements a crucial feature in effective stream processing. Although
most filtering will be done using
[`filter`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-),
there are several other useful operations. The following table outlines the
supported filter-type operations:

| Operation                                                                                                               | Description                                                                              |
|:------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------|
| [`filter`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-) | Remove all elements from the stream except those that match the supplied predicate.      |
| [`distinct`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#distinct--)                         | Remove all duplicate elements from the list, ensuring each element appears exactly once. |
| [`limit`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#limit-long-)                           | Truncate the stream to contain no more than the specified number of elements.            |
| [`skip`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#skip-long-)                             | Discard the specified number of elements at the beginning of the list.                   |

*Pay careful attention to where within a stream the filter operations are
placed.  E.g., filtering before mapping will avoid transforming elements that
will later be discarded.*

### Exercises

1. [Filtering and Transforming List](filtering_ex1.md)

---

[Continue](sorting.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

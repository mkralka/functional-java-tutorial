# Streams

## Terminating Streams

It may seem counterintuitive to begin our conversation of streams by discussing
how we terminate a `Stream`. Termination is an integral part of stream
processing; without it, stream processing doesn't do much. A terminal operation,
such as 
[`collect`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-)
triggers the traversal of the stream and generation of the result.

Stream terminal operations can be split into two broad categories: consume
(no output produced) and reduce (reduce the stream to produce some output).

### Consuming a Stream

*Consume* is supported by a single operation:
[`forEach`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#forEach-java.util.function.Consumer-).
Each element of the stream will be passed to the supplied `Consumer` for
processing (e.g., publishing data to some
[pubsub](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) bus).

A slight variant of this is
[`forEachOrdered`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#forEachOrdered-java.util.function.Consumer-)
which will preserve the order elements in which they were first encountered, if
possible.

### Reducing a Stream

Reduce operations take the elements of a stream and reduce them into a single
result. This may be a collection, a single element, statistics about the stream
(such as element count), or pretty much anything. The `collect` operation is
flexible enough to reduce elements into any desired object by supplying
appropriate functions to accumulate elements and combine results.

The following table describes the supported reduce operations:

| Operation                                                                                                                      | Description                                                                                                                                                            |
|:-------------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`collect`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-)        | Collects all elements into an object using the supplied [`Collector`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html) (more on this below). |
| [`reduce`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#reduce-T-java.util.function.BinaryOperator-) | Reduce all the elements of stream into a single element (of the same type).                                                                                            |
| [`toArray`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#toArray-java.util.function.IntFunction-)    | Collects all elements into an array.                                                                                                                                   |
| [`findAny`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#findAny--)                                  | Find a random element in the stream, if any.                                                                                                                           |
| [`findFirst`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#findFirst--)                              | Find the first element in the stream, if any.                                                                                                                          |
| [`allMatch`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#allMatch-java.util.function.Predicate-)    | Evaluates to `true` if all elements in the stream match the given predicate.                                                                                           |
| [`anyMatch`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#anyMatch-java.util.function.Predicate-)    | Evaluates to `true` if at least one element in the stream matches the given predicate.                                                                                 |
| [`noneMatch`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#noneMatch-java.util.function.Predicate-)  | Evaluates to `true` if no elements in the stream match the given predicate.                                                                                            |
| [`count`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#count--)                                      | Evaluates to the number of elements in the stream.                                                                                                                     |
| [`max`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#max-java.util.Comparator-)                      | Determine the maximum element in the stream, if any.                                                                                                                   |
| [`min`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#min-java.util.Comparator-)                      | Determine the minimum element in the stream, if any.                                                                                                                   |

### Reducing with a `Collector`

[`collect`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-)
is a powerful operation. It makes it possible to perform arbitrary reductions.
The full power of this operation is well beyond the scope of this tutorial.
Instead, we'll focus on the predefined
[`Collector`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html)
implementations that support a large number of common operations.

#### To-Collection Operations

| Operation                                                                                                                                                                | Description                                                                                                                   |
|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------|
| [`toList`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toList--)                                                                          | Collect elements into a [`List`](http://docs.oracle.com/javase/8/docs/api/java/util/List.html).                               |
| [`toSet`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toSet--)                                                                            | Collect elements into a [`Set`](http://docs.oracle.com/javase/8/docs/api/java/util/Set.html).                                 |
| [`toMap`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toMap-java.util.function.Function-java.util.function.Function-)                     | Collect elements into a [`Map`](http://docs.oracle.com/javase/8/docs/api/java/util/Map.html).                                 |
| [`toConcurrentMap`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toConcurrentMap-java.util.function.Function-java.util.function.Function-) | Collect elements into a [`ConcurrentMap`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentMap.html). |
| [`toCollection`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#toCollection-java.util.function.Supplier-)                                   | Collect elements into an arbitrary [`Collection`](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html).       |

#### Grouping/Partitioning Operations

| Operation                                                                                                                                | Description                                                                                                                                                                                                                                   |
|:-----------------------------------------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`groupingBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-)       | Group elements based on an arbitrary property and collect each group separately, creating a [`Map`](http://docs.oracle.com/javase/8/docs/api/java/util/Map.html) keyed by the attribute used to group the elements.                           |
| [`partitionBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#partitioningBy-java.util.function.Predicate-) | Partition the elements into two partitions based on result of applying a predicate to each entry and collect each group separately, creating a [`Map`](http://docs.oracle.com/javase/8/docs/api/java/util/Map.html) with `TRUE`/`FALSE` keys. |

#### Reducing Operations

| Operation                                                                                                                            | Description                                                                                                                                                                                        |
|:-------------------------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`reducing`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#reducing-java.util.function.BinaryOperator-) | Reduce the elements into a single element (of the same type) with repeated applications of a [`BinaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BinaryOperator.html). |
| [`maxBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#maxBy-java.util.Comparator-)                    | Reduce the elements into a single element representing the maximum element.                                                                                                                        |
| [`minBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#minBy-java.util.Comparator-)                    | Reduce the elements into a single element representing the minimum element.                                                                                                                        |
| [`joining`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#joining--)                                    | Reduce the `String` elements into a single `String` through concatenation.                                                                                                                         |

#### Stats Operations

| Operation                                                                                                                                                | Description                                                                                |
|:---------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------|
| [`counting`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#counting--)                                                      | Count the number of elements.                                                              |
| [`averagingInt`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#averagingInt-java.util.function.ToIntFunction-)              | Calculate the arithmetic mean of the `int` elements.                                       |
| [`averagingLong`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#averagingLong-java.util.function.ToLongFunction-)           | Calculate the arithmetic mean of the `long` elements.                                      |
| [`averagingDouble`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#averagingDouble-java.util.function.ToDoubleFunction-)     | Calculate the arithmetic mean of the `double` elements.                                    |
| [`summarizingInt`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summarizingInt-java.util.function.ToIntFunction-)          | Calculate summary statistics (average, count, min, max, and sum) of the `int` elements.    |
| [`summarizingLong`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summarizingLong-java.util.function.ToLongFunction-)       | Calculate summary statistics (average, count, min, max, and sum) of the `long` elements.   |
| [`summarizingDouble`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summarizingDouble-java.util.function.ToDoubleFunction-) | Calculate summary statistics (average, count, min, max, and sum) of the `double` elements. |
| [`summingInt`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summingInt-java.util.function.ToIntFunction-)                  | Calculate the sum of the `int` elements.                                                   |
| [`summingLong`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summingLong-java.util.function.ToLongFunction-)               | Calculate the sum of the `long` elements.                                                  |
| [`summingDouble`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summingDouble-java.util.function.ToDoubleFunction-)         | Calculate the sum of the `double` elements.                                                |

#### Chaining Operations

| Operation                                                                                                                                                                   | Description                                                                                                  |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------|
| [`collectingAndThen`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#collectingAndThen-java.util.stream.Collector-java.util.function.Function-) | Apply the supplied "finishing" function to transform the reduced value produced by the supplied `Collector`. |
| [`mapping`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#mapping-java.util.function.Function-java.util.stream.Collector-)                     | Transform the elements using the supplied function before reducing using the supplied `Collector`.           |

---

[Continue](transforming.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

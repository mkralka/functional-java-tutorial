# Streams

## Stream Pipeline

At the heart of the stream API is the *stream* abstraction provided by
[`Stream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html).
A stream differs from a collection in several ways:

* No storage. Streams don't store elements. Instead, they serve as to convey
  elements through a pipeline of operations.
* Functional in nature. An operation on a stream produces a result without
  modifying the source.
* Lazily evaluated. Many stream operations are implemented lazily, allowing for
  optimizations.
* Possibly unbounded. Streams need not be bounded and can, in fact, continue on
  infinitely.
* Consumable. Elements of a stream can only be consumed once. Like an iterator,
  to processes the elements in a stream multiple times, a new stream must be
  created.

Streams can be created in multiple ways, but the most common is from a
`Collection`, using
[`Collection.stream`](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html#stream--).

A stream pipeline consists of a source (such as a `Collection`), zero or more
intermediate operations (such as filter or map), and a single terminal operation
(such as `forEach` or `reduce`).

Intermediate operations are lazily evaluated; meaning that the elements don't
pass through stages until the elements of the stream are traversed. Traversal of
a stream occurs as soon as a terminal operation of the pipeline is executed. At
this point, the result of processing the pipeline will be made available.

### A note on Streams of Native Types

In addition to the main
[`Stream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
class, custom classes exist to represent streams of `int`, `long`, and `double`.
Whenever possible, these streams should be used for native types to eliminate
the (computational) overhead associated with boxing/unboxing `Integer`, `Long`,
and `Double` objects (respectively), which can be substantial.

These custom stream classes won't be covered; more documentation can be found in
the API documentation:

* [`IntStream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html)
* [`LongStream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/LongStream.html)
* [`DoubleStream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/DoubleStream.html)

---

[Continue](terminating.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

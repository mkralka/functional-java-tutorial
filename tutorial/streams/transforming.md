# Streams

## Transforming Elements

Streams can be transformed in two basic ways: map and flat map. Both of these
operations perform a transformation on each element of a stream, the difference
is in how the result of that transformation affects the stream.

A map operation transforms each element into a new element, preserving order and
length; the transformation is a 1-for-1 replacement of elements. For example, a
map operation would be used to force all elements in a stream of `String`s to
upper case. Such an operation might look similar to the following:

``` java
List<String> normalizeStrings(List<String> input) {
    return input
            .map(String::toUpperCase)
            .collect(Collectors.toList());
}
```

A flat map operation transforms each element into a new `Stream`, flattening the
resulting `Stream<Stream<T>>` into a `Stream<T>`. Consider an employee database
where each employee has zero or more phone numbers stored with their contact
information.  To get a `Stream` of all phone numbers in the database, we can't
simply transform each employee into a phone number; we must transform each
employee into a `Stream` of phone numbers and collapse these streams into a
single stream. Such an operation might look similar to the following:

``` java
Set<PhoneNumber> getAllPhoneNumbers(List<Employee> input) {
    return input
            .flatMap(employee -> employee.getPhoneNumbers().stream())
            .collect(Collectors.toSet());
}
```

Because of the special `Stream` implementation for native types, `Stream` has
several methods for implementing map and flat map, depending on the type of data
into which the stream is being transformed. The following chart shows the
different methods for each operation and output type.

|           | [`Stream<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)                                    | [`IntStream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html)                                           | [`LongStream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/LongStream.html)                                           | [`DoubleStream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/DoubleStream.html)                                           |
|:----------|:-------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------|
| map       | [`map`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#map-java.util.function.Function-)         | [`mapToInt`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#mapToInt-java.util.function.ToIntFunction-)    | [`mapToLong`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#mapToLong-java.util.function.ToLongFunction-)   | [`mapToDouble`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#mapToDouble-java.util.function.ToDoubleFunction-) |
| flat map  | [`flatMap`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMap-java.util.function.Function-) | [`flatMapToInt`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMapToInt-java.util.function.Function-) | [`flatMapToLong`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMapToLong-java.util.function.Function-) | [`flatMapToDouble`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#flatMapToDouble-java.util.function.Function-) |

*NOTE: `IntStream`, `LongStream`, and `DoubleStream` have slightly different
naming scheme; the `map` operation preserves the type (e.g., `IntStream.map`
produces another `IntStream`) while `mapToObj` returns the stream back to the
generic `Stream<T>`.*

### Exercises

1. [Transforming a List](transforming_ex1.md)

---

[Continue](filtering.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

# Streams

In this section, we will introduce Java Streams: a rich API for processing data
declaratively. This is one of the more interesting additions to Java 8,
requiring a significant mental shift in how one approaching data processing.

For example, given a list of arbitrary
[`String`](http://docs.oracle.com/javase/8/docs/api/java/lang/String.html)s,
how would one replace all consecutive non-alphanumeric characters with a single
underscore (`_`), dropping leading/trailing non-alphanumeric characters, and
remove all empty strings from the list. Code written in the imperative
(traditional Java) style might look similar to the following:

``` java
void normalizeList(List<String> input) {
    for (ListIterator<String> iter = input.listIterator(); iter.hasNext();) {
        String value = iter.next();
        String canonicalValue = value.replaceAll("[^a-zA-Z0-9]+", "_");
        canonicalValue = canonicalValue.replaceAll("(^_+|_+$)", "");
        canonicalValue = canonicalValue.toLowerCase();
        if (canonicalValue.isEmpty()) {
            iter.remove();
        } else if (!canonicalValue.equals(value)) {
            iter.set(canonicalValue);
        }
    }
}
```

whereas code written using streams might look similar to the following:

``` java
List<String> normalizeList(List<String> input) {
    return input.stream()
            .map(s -> s.replaceAll("[^a-zA-Z0-9]+", "_").replaceAll("(^_+|_+$)", "").toLowerCase())
            .filter(((Predicate<String>) String::isEmpty).negate())
            .collect(Collectors.toList());
}
```

Even without understanding the details of the `Stream` operations, the streaming
variant is much easier to understand what's happening at a high level. The
elements are:

1. transformed by some expression
2. filtered to exclude those  matching (or possibly not matching?) some
   predicate
3. collected into a list

Let's dive in!

## What You Will Learn

When you have completed this section of the tutorial, you should understand:

1. how to process data declaratively, rather than algorithmically, using
   `Stream`s

2. how to filter, transform (map), and sort elements of a collection

3. how to reduce elements into a single value (such as the
   [minimum](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#minBy-java.util.Comparator-)
   or
   [maximum](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#maxBy-java.util.Comparator-)
   value)

4. how to group elements (based on some arbitrary property) to be processed
   together

5. how to chain operations together to perform complex reductions

We will focus on the more common operations and will avoid some of the darker
corners whenever possible.

## Exercises

|                          |                                                                                                         |
|:-------------------------|:--------------------------------------------------------------------------------------------------------|
| Command to run all tests | `mvn test -Dtest=StreamingTest`                                                                         |
| Source File              | [`src/main/java/functionaljava/Streaming.java`](../src/main/java/functionaljava/Streaming.java)         |
| Test Source File         | [`src/test/java/functionaljava/StreamingTest.java`](../src/test/java/functionaljava/StreamingTest.java) |

All exercises in this section of the tutorial will require modifications to
[`Streaming.java`](../src/main/java/functionaljava/Streaming.java); open it in
your favorite editor now and navigate to the [base directory](..) of this
tutorial (i.e., the directory containing [`README.md`](../README.md))

Those unfamiliar with Maven or would like to run tests through an IDE, see
[Running Tests](running_tests.md) for help.

If, when you run the unit tests, you have trouble compiling your code, read over
the syntax described herein to see where you may have gone wrong. If the tests
fail, your implementation is wrong; use the parameters along with the actual and
expected results to guide you to where your implementation is wrong.

## Overview

At the heart of the stream API is the
[`Stream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
class. Many operations are defined on a stream that manipulate its elements;
they (broadly) fall into one of four categories:

| Type    | Description                                                                                     |
|:--------|:------------------------------------------------------------------------------------------------|
| filter  | Filter unwanted elements out of the stream (e.g., based on a predicate), preserving order.      |
| sort    | Change the order of the stream based on an arbitrary comparison operation.                      |
| map     | Transform elements into a different form (e.g., parse `String` to an `int`), preserving order.  |
| collect | Consume the stream, possibly producing a result.                                                |

With the exception of *collect* (which is a terminal operation), operations can
be chained together in any order to produce surprising complex transformations
on the input data.

*NOTE: These are the category of high-level operations that are supported and not
necessarily the names of the operations (methods) to invoke on `Stream`
objects.*

### Native Types

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

## Terminating Streams

It may seem counterintuitive to begin our conversation of streams by discussing
how we terminate a `Stream`. Termination is an integral part of stream
processing; without it, stream processing doesn't do much (at least, it
shouldn't).

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

At the heart of *reduce* operation is the
[`collect`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-)
operation. Every operation to reduce a stream into a single result can be
performed using `collect`. However, this can get very awkward as it would
involve a lot of boilerplate code. Fortunately, `Stream` directly supports
several common operations to help maintain the sanity of the coder.

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

## Transforming Streams

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

### Exercise: Transforming a List

In this exercise we will transform the elements of a `List`, creating a new
`List` of equal length, maintaining order.

Replace the body of `squareIntegerList` with one that returns a new `List`
created by squaring each element (multiplying by itself).

*Be sure to use the functional `Stream` API rather than directly iterating
through the input collecting the results in a mutable collection.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=StreamingTest#testSquareIntegerList
```

#### Hints
* [`Stream.collect`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-)
  is used to terminate a stream and collect (and return) the results.
* [`Collectors`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)
  contains many
  [`Collector`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html)
  implementations that accumulate entries of a stream into various `Collection`
  types.

## Filtering Collections

Not all of the elements of a stream are important, making the ability to filter
out unwanted elements a crucial feature in effective stream processing. Although
most filtering will be done using
[`filter`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-),
there are several other useful operations. The following table outlines the
supported filter-type operations:

| Operation                                                                                                               | Description                                                                              |
|:------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------|
| [`filter`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-) | Remove all elements from the stream except those that match the supplied predicate.      |
| [`distict`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#distinct--)                          | Remove all duplicate elements from the list, ensuring each element appears exactly once. |
| [`limit`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#limit-long-)                           | Truncate the stream to contain no more than the specified number of elements.            |
| [`skip`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#skip-long-)                             | Discard the specified number of elements at the beginning of the list.                   |

*Pay careful attention to where within a stream the filter operations are
placed.  E.g., filtering before mapping will avoid transforming elements that
will later be discarded.*

### Exercise: Filtering and Transforming List

In this exercise we will filter out elements of a `List` then transform the
remaining elements, creating a new, possibly shorter, collection.

Replace the body of `employeeFamilyNamesAtOffice` with one that returns the
(unique) family names of the employees that work out of a given office.

*Be sure to use the functional `Stream` API rather than directly iterating
through the input collecting the results in a mutable collection.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=StreamingTest#testEmployeeFamilyNamesAtOffice
```

#### Hints
* A stream can be filtered out by
  [supplying a predicate](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-).

## Sorting Collections

Sorting a `Stream` is a breeze with the
[`sorted`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#sorted--)
operation. Elements that implement
[`Comparable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html)
are sorted by their natural order by default. However, a
[`Comparator`](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)
can be supplied to provide a alternate ordering.

`Comparator` is a powerful interface, making it trivial to chain together
`Comparator` and transformations. This is incredibly useful when trying to
compare objects whose attributes are themselves comparable. For example, to sort
files based on creation time then path, it's trivial to create a chain together
the `Comparator` for creation time followed by the `Comparator` for path to
create a `Comparator` to handles both. For example:

``` java
input.stream()
    .sorted(Comparator.comparing(File::getCreationTime).thenComparing(File::getGetPath))
    .collect(Collectors.toList());
```

The details of the `Comparator` interface are beyond the scope of this
tutorial. See the
[API documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)
for details.

### Exercise: Sort a Collection

In this exercise we will sort the elements of a collection.

Replace the body of `sortEmployeesByName` with one that returns a `List`
containing the supplied employees, but that are sorted by family name (earlier
names appearing first). If two employees share the same last name, the one with
the earlier given name should appear first. If two or more employees share the
same given and family names, the one with the earlier start date should appear
first. If two or more employees share the same given and family names and start
date, they may appear in the output in any order. All name sorting is case
insensitive.

*Be sure to use the functional `Stream` API rather than directly iterating
through the input collecting the results in a mutable collection.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=StreamingTest#testSortEmployeesByName
```

#### Hints
* It's safe to assume
 [`String.compareIgnoreCase`](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#compareToIgnoreCase-java.lang.String-)
 will correctly order family and given names.
* A stream can easily be sorted by
  [supplying an appropriate comparator](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#sorted-java.util.Comparator-).
* Comparator has a functional-friendly API for stitching together sort-key
  extraction and comparators (e.g.,
  [`Comparator.comparing`](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#comparing-java.util.function.Function-java.util.Comparator-)
  and
  [`Comparator.thenComparing`](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html#thenComparing-java.util.function.Function-java.util.Comparator-)).

## Grouping Stream Elements

It's often useful to group together the elements of a stream that share a common
value for a given property and reduce elements in one group independently from
elements in another group (e.g., group employees together based on the office
they work from to count how many employees are in each office).

Using
[`groupingBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-),
during the collect stage, the elements can be partitioned into groups based on
an arbitrary property of each element. The elements in each group are then
collected (into a `List` by default). Grouping a `Stream` always generates a
`Map` whose keys are the values by which the elements were grouped.

Since `groupingBy` can chain with another `Collector`, it's possible to create
arbitrarily deep groupings (e.g., group employees by location, then by
department, and finally by type -- contract, full-time, part-time, hourly,
etc.), resulting in nested `Map`s (e.g.,
`Map<Office, Map<Department, Map<EmployeeType, Set<Employee>>>>`).

``` java
Map<Office, Map<Department, Map<EmployeeType, Set<Employee>>>> employeeGroups =
        employees.stream()
                .collect(
                        Collectors.groupingBy(Employee::getOffice,
                        Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(Employee::getType,
                        Collectors.toSet()))));
```

### Exercise: Group and Count Elements

In this exercise we will count the number of elements that share the same value
for an arbitrary value calculated from each element.

Replace the body of `employeeCountByOffice` with one that returns the number of
employees who work out of each office. All entries in the result should contain
non-zero values (i.e., only offices found in the employee records need to be
considered).

*Be sure to use the functional `Stream` API rather than directly iterating
through the input collecting the results in a mutable collection.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=StreamingTest#testEmployeeCountByOffice
```

#### Hints

* [`Collectors`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)
  has methods for creating a collector that first groups elements by an
  arbitrary property (e.g., office location) before applying a collector to
  the elements of each group.
* [`Collectors`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)
  contains
  [`Collector`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html)
  implementations for calculating various arithmetic operations on the elements
  of a stream (such as
  [sum](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#summingInt-java.util.function.ToIntFunction-),
  [average](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#averagingInt-java.util.function.ToIntFunction-),
  and
  [count](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#counting--)).

## Partitioning a Stream

Partitioning a stream is a special case of grouping where the elements are
split into exactly two groups with values `TRUE` and `FALSE`.

Partitioning is performed by using the
[`partitioningBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#partitioningBy-java.util.function.Predicate-)
operation (which behaves similarly to
[`groupingBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-)).

### Exercise: Find the Maximum Element by Group

In this exercise we will find the maximum element for those that share the same
value for an arbitrary value calculated from each element.

Replace the body of `newestEmployeeByOffice` with one that finds the newest
employee (has the most recent start date) in each office. If two or more
employees share the earliest start date, any of these employees may represent
the office. All offices in the result must have a "newest" employee (i.e., only
offices found in the employee records need to be considered).

*Be sure to use the functional `Stream` API rather than directly iterating
through the input collecting the results in a mutable collection.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=StreamingTest#testNewestEmployeeByOffice
```

#### Hints
* The solution to `employeeCountByOffice` will likely come in handy to group
  employees by location.
* [`Collectors`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)
  can create a Collector that finds the
  [minimum](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#minBy-java.util.Comparator-)
  or
  [maximum](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#maxBy-java.util.Comparator-)
  element in a stream, given the right comparator.
* [`Collectors.collectingAndThen`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#collectingAndThen-java.util.stream.Collector-java.util.function.Function-)
  can be used to apply a transformation to the result of collector.
* Try to find a way to create a `Map<Office, Optional<Employee>>` first, then
  augment the collector to apply a finisher to unwrap the `Optional`.
  (This can be done without materializing the intermediate
  `Map<Office, Optional<Employee>>`.)

### Exercise: Partition a Stream and Reduce

In this exercise we will partition a stream in two, counting the number of
elements in each partition.

Replace the body of `percentageStartedBefore` with one that returns the
percentage of employees that started before (but not on) the provided date.

*Be sure to use the functional `Stream` API rather than directly iterating
through the input collecting the results in a mutable object.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=StreamingTest#testPercentageStartedBefore
```

#### Hints
* Partition the stream into those employees that started before date and those
  that started on or after and count the size of each partition. Use these
  two sizes to calculate the percentage.

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

### Exercise: Find the Minimum Element

In this exercise we will find the minimum element of a list based on an
arbitrary comparison operation.

Replace the body of `mostSeniorEmployee` with one that returns the employee with
the earliest start date. If there are no employee records, an empty `Optional`
should be returned.

*Be sure to use the functional `Stream` API rather than directly iterating
through the input collecting the results in a mutable object.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=StreamingTest#testMostSeniorEmployee
```

#### Hints

* Given an appropriate
  [`Comparator`](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html),
  a stream has operations to find its
  [minimum](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#min-java.util.Comparator-)
  or
  [maximum](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#max-java.util.Comparator-)
  element.

### Exercise: Find the Minimum Elements

In this exercise we will find the minimum elements of a list based on an
arbitrary comparison operation.

Replace the body of `mostSeniorEmployees` with one that returns the employees
with the earliest start date. If there are no employee records, an empty
`Set` should be returned.

*Be sure to use the functional `Stream` API rather than directly iterating
through the input collecting the results in a mutable collection.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=StreamingTest#testMostSeniorEmployees
```

#### Hints

* Don't be fooled by the similarities with `mostSeniorEmployee`; this calls for
  a much different solution.
* This can be achieved without sorting the stream by first determining the
  earliest start date then finding the employees that started on that date.

### Bonus Exercise: Find the Minimum Elements

*This is an optional, __bonus__ exercise.*

In this exercise we will find the minimum elements of a list, based on an
arbitrary comparison operation, in a single pass.

Replace the body of `mostSeniorEmployees` (the `Stream` variant) with one that
returns the employees with the earliest start date in a single pass through the
elements of the stream. If there are no employee records, an empty `Set` should
be returned.

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=StreamingTest#testMostSeniorEmployeesStream
```

#### Hints

* Define a custom `Collector` and use it with
  [`Stream.collect`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.stream.Collector-)
  (or a supplier, accumulator, and combiner and use it with
  [`Stream.collect`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#collect-java.util.function.Supplier-java.util.function.BiConsumer-java.util.function.BiConsumer-)).
* Consider storing the most senior employees found so far in the accumulator.
  Make sure you correctly handle finding an newer, more senior employee.

## Further Reading

1. [`java.util.stream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
   package API documentation.
2. [`java.util.stream.Stream`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
   API documentation.
2. [`java.util.stream.Collectors`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)
   API documentation.

---

[Previous](optional.md) | [Up](tutorial.md) | Next

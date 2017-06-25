# Streams

## Sorting

Sorting a `Stream` is a breeze with the
[`sorted`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#sorted--)
operation. Elements that implement
[`Comparable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html)
are sorted by their natural order by default. However, a
[`Comparator`](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)
can be supplied to provide an alternate ordering.

`Comparator` is a powerful interface, making it trivial to chain together
`Comparator` and transformations. This is incredibly useful when trying to
compare objects whose attributes are themselves comparable. For example, to sort
files based on creation time then path, it's trivial to create a chain together
the `Comparator` for creation time followed by the `Comparator` for path to
create a `Comparator` to handles both. For example:

``` java
input.stream()
    .sorted(Comparator.comparing(File::getCreationTime).thenComparing(File::getPath))
    .collect(Collectors.toList());
```

The details of the `Comparator` interface are beyond the scope of this
tutorial. See the
[API documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)
for details.

### Exercises

1. [Sort a Collection](sorting_ex1.md)

---

[Continue](grouping.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

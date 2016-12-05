# Optional

## Emptying Optionals

In some cases, an `Optional` can contain a value that should be treated
effectively the same as an empty `Optional`.

[`filter`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#filter-java.util.function.Predicate-)
provides a way of demoting a non-empty `Optional` that doesn't match an
arbitrary predicate. It is roughly equivalent to the following code:

``` java
<T> Optional<T> filter(Optional<T> input, Predicate<T> predicate) {
    if (input.isPresent() && predicate.test(input.get())) {
            return input;
    }
    return Optional.empty();
}
```

### Exercises

1. [Empty (filter) an Optional](emptying_ex1.md)

---

[Continue](conditional.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

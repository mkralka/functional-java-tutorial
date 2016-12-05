# Optional

## Transforming Optionals

You will often find yourself needing to transform the value stored in an
`Optional` (possibly of a different type), if a value is present in the
`Optional`.  An operation that is roughly equivalent to the following:

``` java
AddressBook addressBook = null;
if (employee != null) {
    addressBook = employee.getAddressBook;
}
```

`Optional` has native support for this operation with the
[`map`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#map-java.util.function.Function-)
and
[`flatMap`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#flatMap-java.util.function.Function-)
operations.

`map` converts the value stored in a non-empty `Optional` based on a supplied
transformation function. If the original `Optional` was empty or the
transformation function returns `null`, the new `Optional` will be empty.
Otherwise, the new optional will be non-empty and will contain the generated
value. It is roughly equivalent to the following code:

``` java
<T, U> Optional<U> map(Optional<T> input, Function<T, U> transformation) {
    if (!input.isPresent()) {
        return Optional.empty();
    }
    return Optional.ofNullable(transformation.apply(input.get()));
}
```

`flatMap` is similar to `map` except that the supplied transformation function
generates an `Optional<T>` (rather than a `T`). `flatMap` automatically flattens
what would otherwise be an `Optional<Optional<T>>` into an `Optional<T>`.

``` java
<T, U> Optional<U> flatMap(Optional<T> input, Function<T, Optional<U>> transformation) {
    if (!input.isPresent()) {
        return Optional.empty();
    }

    return transformation.apply(input.get());
}
```

### Exercises

1. [Transform (map) one `Optional` into Another](transforming_ex1.md)

2. [Transform (map) and Flatten one `Optional` into Another](transforming_ex2.md)

---
[Continue](emptying.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

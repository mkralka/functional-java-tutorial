# Optional

## Execution Condition on the State of an `Optional`

Conditionally executing code is commonly be done using `if` statements;
an imperative technique. `Optional` allows for executing arbitrary code if an
`Optional` value is present (e.g., to publish an event to a some
[pubsub](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) bus).

[`ifPresent`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#ifPresent-java.util.function.Consumer-)
invokes an arbitrary consumer if the `Optional` contains a (non-`null`) value.
It is roughly equivalent to the following:

``` java
<T> void ifPresent(Optional<T> optional, Consumer<T> consumer) {
    if (optional.isPresent()) {
        consumer.accept(optional.get());
    }
}
```

### Exercises

1. [Execute Code if and `Optional` is Present](conditional_ex1.md)

---

[Continue](unwrapping.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

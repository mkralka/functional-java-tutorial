# Optional

## Unwrapping an Optional

An `Optional` is of no use unless you can extract the value stored within.
`Optional` has a few methods for extracting the value, with slightly different
behaviors, as described in the following table:

| Operation                                                                                                                   | Description                                                                                                                                                                                                      |
|:----------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`get`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#get--)                                            | Unconditionally extract the value, throwing if empty. **Use with caution.**                                                                                                                                      |
| [`orElse`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#orElse-T-)                                     | Extract the value if present, otherwise return the provided (default) value.                                                                                                                                     |
| [`orElseGet`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#orElseGet-java.util.function.Supplier-)     | Extract the value if present, otherwise invoke the provided supplier and return the generated (default) value. This is ideal if the default needs to be generated and will ensure it's only generated if needed. |
| [`orElseThrow`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#orElseThrow-java.util.function.Supplier-) | Extract the value if present, otherwise invoke the provided supplier to generate an exception and throw it.                                                                                                      |

### Exercises:

1. [Get Value with Default](unwrapping_ex1.md)

2. [Get Value or Supplied Default](unwrapping_ex2.md)

---

[Continue](end.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

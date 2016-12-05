# Optional

## Creating Optionals

There are three methods for creating an `Optional` from a value, each with
slightly different use cases. The following table describes the methods for
creating an `Optional`.

| Method                                                                                          | Description                                                                                 |
|:------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------|
| [`of`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#of-T-)                 | Create an `Optional` from the specified non-`null` value, throwing an exception if `null`.  |
| [`ofNullable`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#ofNullable-T-) | Create an `Optional` from the specified value if non-`null`, otherwise an empty `Optional`. |
| [`empty`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#empty--)            | Returns an empty `Optional` instance. (The empty `Optional` is a singleton).                |

Optionals can also be created by transforming existing options, which is covered
[next](transforming.md).

---

[Continue](transforming.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

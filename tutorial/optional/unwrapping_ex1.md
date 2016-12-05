# Optional

## Exercise: Get Value with Default

In this exercise we will extract the value from an `Optional`, defaulting to
a specific other value if the `Optional` is empty.

Open
[`src/main/java/functionaljava/Optionals.java`](../../src/main/java/functionaljava/Optionals.java)
and replace the body of `preferredName` with one that returns the individual's
first name unless they have a nickname, which should be returned in its place.

To test your solution, run the following command:

``` bash
mvn test -Dtest=OptionalsTest#testPreferredName
```

[Solution](unwrapping_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](unwrapping_ex2.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

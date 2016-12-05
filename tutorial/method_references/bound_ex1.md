# Method References

## Exercise: Bound Method Reference

In this exercise we will supply a function that is a reference to a bound
method.

Open
[`src/main/java/functionaljava/MethodReferences.java`](../../src/main/java/functionaljava/MethodReferences.java)
and replace the body of the `mapSplitter` method with one that creates a
[`MapSplitter`](https://google.githuyyb.io/guava/releases/19.0/api/docs/com/google/common/base/Splitter.MapSplitter.html)
as follows:

``` java
Splitter.on(entrySeparator)
        .omitEmptyStrings()
        .trimResults()
        .withKeyValueSeparator(Splitter.on(keyValueSeparator).trimResults());
```

and returns a reference its
[`split`](https://google.github.io/guava/releases/19.0/api/docs/com/google/common/base/Splitter.MapSplitter.html#split%28java.lang.CharSequence%29)
method.

To test your solution, run the following command:

``` bash
mvn test -Dtest=MethodReferencesTest#testMapSplitter
```

[Solution](bound_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](unbound.md)

[Skip Back](../lambda_expressions/start.md) | [Up](../start.md) | [Skip Forward](../optional/start.md)

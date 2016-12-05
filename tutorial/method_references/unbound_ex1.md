# Method References

## Exercise: Unbound Method Reference

In this exercise we will supply a function that is a reference to an unbound
method.

Open
[`src/main/java/functionaljava/MethodReferences.java`](../../src/main/java/functionaljava/MethodReferences.java)
and replace the body of the `collectionSizer` method with one that returns a
reference to an unbound method that calculates the size of (number of elements
in) a
[`Ccollection`](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html).

Since the method reference should be unbound, it must be an instance method of
the `Collection` class.

To test your solution, run the following command:

``` bash
mvn test -Dtest=MethodReferencesTest#testCollectionSizer
```

[Solution](unbound_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](constructor.md)

[Skip Back](../lambda_expressions/start.md) | [Up](../start.md) | [Skip Forward](../optional/start.md)

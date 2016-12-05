# Method References

## Exercise: Constructor Method Reference

In this exercise we will supply a function that is a reference to a constructor.

Open
[`src/main/java/functionaljava/MethodReferences.java`](../../src/main/java/functionaljava/MethodReferences.java)
and replace the body of the `stringCreator` method with one that returns a
reference to a constructor that creates a `String` from a `byte[]`.

To test your solution, run the following command:

``` bash
mvn test -Dtest=MethodReferencesTest#testStringCreator
```

[Solution](constructor_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

### Hints
* It's safe to assume that the input is specified in your platform's default
  charset.

---

[Continue](end.md)

[Skip Back](../lambda_expressions/start.md) | [Up](../start.md) | [Skip Forward](../optional/start.md)

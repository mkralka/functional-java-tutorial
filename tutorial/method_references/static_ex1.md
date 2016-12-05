# Method References

## Exercise: Static Method Reference

In this exercise we will supply a function that is a reference to a static
method.

Open
[`src/main/java/functionaljava/MethodReferences.java`](../../src/main/java/functionaljava/MethodReferences.java)
and replace the body of the `hypotenuse` method with one that returns a
reference to
[`Math.hypot`](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#hypot-double-double-),
a method in Java's math library that calculates the length of the hypotenuse of
a [right-angled triangle](https://en.wikipedia.org/wiki/Right_triangle) from the
lengths of the legs (the sides adjacent to the right angle) of the triangle.

To test your solution, run the following command:

``` bash
mvn test -Dtest=MethodReferencesTest#testHypotenuse
```

[Solution](static_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](bound.md)

[Skip Back](../lambda_expressions/start.md) | [Up](../start.md) | [Skip Forward](../optional/start.md)

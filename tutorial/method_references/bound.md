# Method References

## Bound Method Reference

Instance (non-static) methods, unlike static methods, are invoked on an object
(provided by the compiler as the implicit, zeroth parameter named `this`).

A bound method reference is one where the implicit `this` parameter has been
bound to a specific object, so need not be supplied when invoking the function.
Put another way, all invocations of the function will result in the method being
invoked on a specific object, regardless of the parameters that are supplied.

For example, consider:

``` java
Map<String, Integer> map = new HashMap<>();
Function<String, Integer> extractor = map::get;

map.put("foo", 17);
map.put("bar", 19);

System.out.println(String.format("foo has value %d", extractor.apply("foo")));
```

Let's decompose the line with the method reference:

``` java
Function<String, Integer> extractor = map::get;
```

The context, `map`, is an object; this indicates that the method **is** bound to
a specific object. The method, `get`, is an instance method; this indicates that
the reference is to the `Map.get` and the reference has the same signature as
the formal parameters to the `Map.get` method (in this case, the same as 
[`Function<String, Integer>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)).

Since the method reference is bound, all invocations of `extractor.apply` will
be invoked on `map` (the instance of `Map`) with the same parameters that are
supplied to `apply`, producing the following output:

```
foo has value 17
```

### Exercises

1. [Exercise: Bound Method Reference](bound_ex1.md)

---

[Continue](unbound.md)

[Skip Back](../lambda_expressions/start.md) | [Up](../start.md) | [Skip Forward](../optional/start.md)

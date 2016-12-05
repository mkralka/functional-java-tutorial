# Method References

## Unbound Method Reference

Instance (non-static) methods, unlike static methods, are invoked on an object
(provided by the compiler as the implicit, zeroth parameter named `this`).

An unbound method reference is one where the implicit `this` parameter has *not*
be bound to a specific object, so must be supplied when invoking the function.
Put another way, all invocations of the function will need to explicitly provide
the `this` parameter to the unbound method.

For example, consider:

``` java
BiFunction<Map<String, Integer>, String, Integer> extractor = Map::get;
Map<String, Integer> map = new HashMap<>();

map.put("foo", 17);
map.put("bar", 19);

System.out.println(String.format("foo has value %d", extractor.apply(map, "foo")));
```

Let's decompose the line with the method reference:

```
BiFunction<Map<String, Integer>, String, Integer> extractor = Map::get;
```

The context, `Map`, is a class; this indicates that the method **is not** bound
to a specific object. The method, `get`, is an instance method (as opposed to a
static method); this means that the object on which to apply `get` must be
supplied by caller, which requires an additional parameter to the function. This
additional parameter is added before the formal parameters to the `Map.get`
function, becoming a function of arity 2 (the `this` parameter plus the formal
parameters of `Map.get`).

Since the method reference is unbound, all invocations of `extractor.apply` must
supply the `Map<String, Integer>` object on which `Map.get` will be invoked,
producing the following output:

```
foo has value 17
```

### Exercises

1. [Exercise: Unbound Method Reference](unbound_ex1.md)

---

[Continue](constructor.md)

[Skip Back](../lambda_expressions/start.md) | [Up](../start.md) | [Skip Forward](../optional/start.md)

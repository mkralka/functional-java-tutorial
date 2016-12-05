# Method References

## Constructor Method Reference

Constructors are special. Unlike other types of methods, they are not called
directly. Instead, they are invoked using the `new` keyword to initialize empty
objects newly allocated on the heap. A function that is created from a reference
to a constructor, when invoked, behaves exactly as if the constructor were
invoked with the `new` operator.

For example, consider:

``` java
IntFunction<List<String>> listCreator = ArrayList::new;
List<String> list = listCreator.apply(10);

list.add("a");
list.add("b");
list.add("c");

System.out.println(list.getClass().toString());
System.out.println(list.toString());
```

Let's decompose the line with the constructor reference:

```
IntFunction<List<String>> listCreator = ArrayList::new;
```

The constructor reference can be assigned to any function that generates a type
to which `ArrayList` can be assigned (`List` and `Collection`, for example) and
which has parameters that match formal parameters of any of it's constructors.

Array list has the following constructors:

* `ArrayList()`
* `ArrayList(Collection<? extends E> c)`
* `ArrayList(int initialCapacity)`

Aside from `IntFunction<List<String>>`, an `ArrayList` constructor could be
assigned to a `Supplier<List<String>>` or `Function<Collection<? extends
String>, List<String>>` (which would select a different constructor).

The example above generates the following output:

```
class java.util.ArrayList
[a, b, c]
```

### Exercises

1. [Exercise: Constructor Method Reference](constructor_ex1.md)

---

[Continue](end.md)

[Skip Back](../lambda_expressions/start.md) | [Up](../start.md) | [Skip Forward](../optional/start.md)

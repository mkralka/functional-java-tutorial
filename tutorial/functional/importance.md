# Functional Interfaces

## Why are Functional Interfaces Important?

[First-class functions](https://en.wikipedia.org/wiki/First-class_function) are
a necessity for programming in the functional style, where the use of
[higher-order functions](https://en.wikipedia.org/wiki/Higher-order_function)
(a function taking one or more parameters that are themselves functions) is
commonplace.

Earlier versions of Java supported higher-order functions, albeit
awkwardly, through the use of custom interfaces and
[anonymous classes](https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html).
For functional programming in Java to be practical, this awkwardness must first
be removed.

Consider a higher-order function, `map`, that takes a list of elements and a
transformation as parameters and produces another list containing the element of
the supplied list after having the transformation function applied. `map` might
have an interface that is similar to the following:

``` java
class Lists {
    interface Transformer<T, U> {
        U transform(T x);
    }

    static <T, U> List<U> map(List<T> list, Transformer<T, U> transformer) {
        // magic
    }
}
```

To convert a list of `Integer`s into a list of `String`s, one might use `map` in
a way similar to the following:

``` java
List<String> stringizedList = Lists.map(inputList, new Lists.Transformer<>() {
    String transform(Integer x) {
        return x.toString();
    }
});
```

This was problematic for a number of reasons:

1. The caller must define the body of the function in terms of a class. Although
   anonymous classes improve clarity by allowing the class to be defined inline,
   the syntax is still verbose.
2. `Lists` needs to define a special interface, `Transformer`, that describes
   the operation (or function) that `map` will perform on each element. This can
   result in many, effectively identical, interfaces being defined by each
   package/library that needs a similar operation.
3. `Transformer` is not composable; if a transformer from `T` to `U` exists
   along with a transformer from `U` to `V`, the caller must manually compose a
   `Transformer` from `T` to `V`..
4. Anonymous classes are processed by the compiler and result in `.class` files
   being generated. These class files must be loaded by the JVM and instantiated
   at runtime. Functional interfaces need not be, allowing side-effect-free
   functions to be further optimized.

*Functional interfaces* address these problems. Since they are how function
types are defined, built-in syntax for succinct function definitions is
available (through the use of
[lambda expressions](../lambda_expressions/start.md) and
[method references](../method_references/start.md)).

With the [standard functional interfaces](standard_interfaces.md) now available
in Java 8, one-off interfaces (like `Transformer`) can be replaced by a
standard, generic equivalent (e.g.,
[`Function`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)).
Standard functional interfaces also include support for composing new functions
from existing ones, simplifying the process.

To help raise functions to first-class citizen status, functional interfaces are
treated differently by the compiler. The byte code generated for their use is
much different than non-functional interfaces, since it is assumed there is no
object backing the function.

---
[Continue](standard_interfaces.md)

Skip Back | [Up](../start.md) | [Skip Forward](../lambda_expressions/start.md)

# Method References

## What You Will Learn

When you have completed this section of the tutorial, you should understand:

1. how to write method references

2. when a lambda can be replaced with a method reference

3. the differences between the different types of methods references: static,
   bound, unbound, and constructor

## Exercises

|                          |                                                                                                                       |
|:-------------------------|:----------------------------------------------------------------------------------------------------------------------|
| Command to run all tests | `mvn test -Dtest=MethodReferencesTest`                                                                                |
| Source File              | [`src/main/java/functionaljava/MethodReferences.java`](../src/main/java/functionaljava/MethodReferences.java)         |
| Test Source File         | [`src/test/java/functionaljava/MethodReferencesTest.java`](../src/test/java/functionaljava/MethodReferencesTest.java) |

All exercises in this section of the tutorial will require modifications to
[`MethodReferences.java`](../src/main/java/functionaljava/MethodReferences.java);
open it in your favorite editor now and navigate to the [base directory](..) of
this tutorial (i.e., the directory containing [`README.md`](../README.md))

Those unfamiliar with Maven or would like to run tests through an IDE, see
[Running Tests](running_tests.md) for help.

If, when you run the unit tests, you have trouble compiling your code, read over
the syntax described herein to see where you may have gone wrong. If the tests
fail, your implementation is wrong; use the parameters along with the actual and
expected results to guide you to where your implementation is wrong.

## Referencing Methods

A method reference is second of the two ways of defining a function (the first
being a [lambda expression](lambda_expressions.md). As its name suggests, a
method reference is a way of defining a function as a reference to an existing
method with the same signature. For example,
[`Math.sqrt`](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#sqrt-double-)
has the following signature:

``` java
public static double sqrt(double a);
```

As learned in a [previous section](lambda_expressions.md), a lambda expression
for calling `Math.sqrt` might be written similarly to the following:

``` java
DoubleUnaryOperator sqrt = x -> Math.sqrt(x);
```

Since the parameters and return type of
[`DoubleUnaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleUnaryOperator.html)
are identical to `Math.sqrt`, having to name the parameters in order to provide
the trivial mapping from the function to the method seems annoying at best.
Method references take this burden away.

All method references consist of two parts separated by the *scope*
operator (`::`): the context in which the method is called and the method
itself. (Much like the use of *angle brackets* for generics, the use of the
*scope* — or double-colon — operator will make C++ programmers feel right at
home!)

Our example above, rewritten as a method reference would be written as follows:

``` java
DoubleUnaryOperator sqrt = Math::sqrt;
```

Comparing a method reference to the equivalent lambda expression (see above), it
is easy to see why references are so helpful.

Java 8 supports four different types of method references, each with subtle
differences between the context in which they are used and the parameters that
are expected. The following table describes the different types of method
references:

| Type        | Form                         | Example                |
|:------------|:-----------------------------|:-----------------------|
| static      | `<class>::<staticMethod>`    | `Math::sqrt`           |
| bound       | `<object>::<instanceMethod>` | `employeesByCity::put` |
| unbound     | `<class>::<instanceMethod>`  | `Object::toString`     |
| constructor | `<class>::new`               | `Employee::new`        |

Let's explore these different method reference types!

## Static Method Reference

Static method references are the simplest type of references. Since static
methods are not invoked on an object, references to static methods can be
assigned to a function with matching signature, without any magic going on under
the covers.

Recall from the example above:

``` java
DoubleUnaryOperator sqrt = Math::sqrt;
```

The context, `Math`, is a class; this indicates that the method is not
bound to any particular object (in this case because it is static). The
method, `sqrt`, is a static method; this indicates that the reference is to the
`sqrt` method of the context (in this case, the `Math` class) and the reference
has the same signature as the `sqrt` method (i.e., a `DoubleUnaryOperator`).

### Exercise: Static Method Reference

In this exercise we will supply a binary operator using a reference to a
static method that calculates the length of hypotenuse of a
[right-angled triangle](https://en.wikipedia.org/wiki/Right_triangle) from the
lengths of the legs (the sides adjacent to the right angle) of the triangle.

Replace the body of the `hypotenuse` method with one that returns a reference to
[`Math.hypot`](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#hypot-double-double-),
a method in Java's math library that already exists to calculate hypotenuses.

*Be sure to define the operator as a method reference and not a lambda
expression.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=MethodReferencesTest#testHypotenuse
```

If the tests pass, congratulations! You've written your first method reference!

## Bound Method Reference

Instance (non-static) methods, unlike static methods, are invoked on an object
(provided by the compiler as the implicit, zeroth parameter named `this`).
Normally, the expression that precedes the dot (`.`) before a method call
specifies the object on which the method is invoked (which defaults to `this` if
omitted).

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
a specific object (in this case, `map`). The method, `get`, is an instance
method; this indicates that the reference is to the `get` method of the context
(in this case, `map` object of type `Map`) and the reference has the same
signature as the formal parameters to the `Map.get` method (i.e., a
`Function<String, Integer>`).

Since the method reference is bound, all invocations of `extractor.apply` will
invoked on `map` (the instance of `Map`) with the parameters supplied to
`apply`, producing the following output:

```
foo has value 17
```

### Exercise: Bound Method Reference

In this exercise we will supply a function that converts structured text into
a `Map<String, String>`. In order to support different character separators, the
separator characters will be provided to the method that creates the function.

The method will have the following signature:

``` java
Function<String, Map<String, String>> mapSplitter(char entrySeparator, char keyValueSeparator);
```

This method will produce a function (with arity one) that creates a `Map` from a
structured string. Entries in the input are separated by the `entrySeparator`.
Within an entry, the key and value are separated by `keyValueSeparator`.
Whitespace around (both entry and key-value) separators and at the beginning/end
of the input are ignored. Empty entries (i.e., those that contain no
non-whitespace characters) are also omitted from the result.

Let's look at a few concrete examples to see how the parameters to `maxSplitter`
affect the behavior of the returned function. Consider the following inputs:

| `entrySeparator` | `keyValueSeparator` | input                         |
|:-----------------|:--------------------|:------------------------------|
| `,`              | `=`                 | `"a=alpha,b=bravo,c=charlie"` |
| `,`              | `:`                 | `"a:alpha,b:bravo,c:charlie"` |
| `;`              | `:`                 | `"a:alpha;b:bravo;c:charlie"` |

For each of these examples, the function returned by
`maxSplitter(entrySeparator, keyValueSeparator)` will convert `input` into a
`Map` with the following contents:

| key   | value       |
|:------|:------------|
| `"a"` | `"alpha"`   |
| `"b"` | `"bravo"`   |
| `"c"` | `"charlie"` |

Replace the body of the `mapSplitter` method with one that returns a method
reference that parses strings as described above. Use guava's
[`Splitter`](https://google.github.io/guava/releases/19.0/api/docs/com/google/common/base/Splitter.html)
to create a
[`MapSplitter`](https://google.github.io/guava/releases/19.0/api/docs/com/google/common/base/Splitter.MapSplitter.html);
return a reference to the
[`split`](https://google.github.io/guava/releases/19.0/api/docs/com/google/common/base/Splitter.MapSplitter.html#split%28java.lang.CharSequence%29)
method of the constructed `MapSplitter`.

*Be sure to define the operator as a method reference and not a lambda
expression.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=MethodReferencesTest#testSplitter
```

#### Hints
* [`Splitter.on`](https://google.github.io/guava/releases/19.0/api/docs/com/google/common/base/Splitter.html#on%28char%29)
  can be used to create a splitter that will split the string on
  `entrySeparator` and
  [`Splitter.withKeyValueSeparator`](https://google.github.io/guava/releases/19.0/api/docs/com/google/common/base/Splitter.html#withKeyValueSeparator%28char%29)
  can further split each entry on `keyValueSeparator`.

## Unbound Method Reference

Instance (non-static) methods, unlike static methods, are invoked on an object
(provided by the compiler as the implicit, zeroth parameter named `this`).
Normally, the expression that precedes the dot (`.`) before a method call
specifies the object on which the method is invoked (which defaults to `this` if
omitted).

An unbound method reference is one where the implicit `this` parameter has *not*
be bound to a specific object, so must be supplied when invoking the function.
Put another way, all invocations of the function will result need to explicitly
provide the `this` parameter to the unbound method.

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

### Exercise: Unbound Method Reference

In this exercise we will supply a function using a reference to an unbound
method that calculates the size of (number of entries in) a `Collection`.

Replace the body of the `collectionSizer` method with one that returns a
reference to an unbound method that calculates the size of (number of elements
in) a collection.

*Be sure to define the operator as a method reference and not a lambda
expression.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=MethodReferencesTest#testCollectionSizer
```

#### Hints
* Since the method reference should be unbound, it must be an instance method of
  the `Collection` class.

## Constructor Method Reference

Constructors are special. Unlike other types of methods, they are not called
directly. Instead, they are invoked using the `new` keyword to initialize blank
objects newly allocated on the heap. A function that is created from a reference
to a constructor should, when invoked, behave identically to if the constructor
were invoked with the `new` operator. This is exactly how it behaves.

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

### Exercise: Constructor Method Reference

In this exercise we will supply a function using a reference to a constructor
that creates a new `String` from its raw bytes.

Replace the body of the `stringCreator` method with one that returns a
reference to a constructor that creates a `String` from an array of `byte`s.

*Be sure to define the operator as a method reference and not a lambda
expression.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=MethodReferencesTest#testStringCreator
```

#### Hints
* It's safe to assume that the input is specified in your platform's default
  charset.

---

[Previous](lambda_expressions.md) | [Up](tutorial.md) | [Next](optional.md)

# Functional Interfaces

Java 8 introduced the concept of a *functional interface*; a key feature for
functional programming (as we shall see in this section).

## What You Will Learn

When you have completed this section of the tutorial, you should understand:

1. what a functional interface is

2. why functional interfaces are important

3. the standard functional interfaces introduced in Java 8

4. how to create custom functional interfaces to be used when the standard
   interfaces are insufficient

## What is a Functional Interface?

Java 8 introduced the concept of a *functional interface* (which is defined as
any
[`interface`](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)
that has exactly one unimplemented method) and is at the heart of
functional programming in Java. This does not mean that an `interface` can have
only one method to be considered *functional*; an `interface` can have multiple
methods as long as all but one are
[default methods](https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)
(that is, have a default implementation using the `default` keyword). Although
default methods are an important part of functional programming in Java, they
are not critical to understanding basic functional expressions.

## Why are Functional Interfaces Important?

[First-class functions](https://en.wikipedia.org/wiki/First-class_function) are
a necessity for programming in the functional style, where the use of
[higher-order functions](https://en.wikipedia.org/wiki/Higher-order_function)
(a function taking one or more parameters that are themselves functions) is
commonplace. Earlier versions of Java appeared to support higher-order
functions, through the use of
[anonymous classes](https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html).

Consider a higher-order function, `map`, that takes a list and a transformation
as parameters and produces another list containing the element of the supplied
after having the transformation function applied. `map` might have an interface
that is similar to the following:

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
   anonymous classes improve clarity by allowing the class to defined inline,
   the syntax is still verbose.
2. `Lists` needs to define a special interface, `Transformer`, that describes
   the operation (or function) that `map` will perform on each element. This can
   result in many, effectively identical, interfaces being defined by each
   package/library that needs a similar operation.
3. Anonymous classes are processed by the compiler and result in `.class` files
   being generated. These class files must be loaded by the JVM and instantiated
   at runtime. Functional interfaces need not be, allowing side-effect-free
   functions to be further optimized.

To help address the awkwardness imposed on the caller, a more succinct syntax is
needed. This is only possible if the majority of the interface to be implemented
can be inferred from context (e.g., the return type along with the number and
type of parameters). Functional interfaces fit this bill perfectly, having only
a single unimplemented method. We'll explore this syntax later when we cover
[lambda expressions](lambda_expressions.md) and [method references](method_references.md).

To help avoid many similar interfaces being defined, Java 8 introduces several
standard functional interfaces that can be used. We'll discuss these in greater
detail below.

To help raise functions to first-class citizen status, functional interfaces are
treated differently by the compiler. The byte code generated for their use is
much different than non-functional interfaces, since it is assumed there is no
object backing the function.

## Standard Functional Interfaces

Java 8 bundles wide range of functional interfaces in the
[`java.util.function`](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)
package that cover many of the more common use cases that broadly fall into the
following categories:

| Category   | Description                                                                                             |
|:-----------|:--------------------------------------------------------------------------------------------------------|
| Suppliers  | Provide a value without accepting any parameters.                                                       |
| Consumers  | Consume one or more values without returning any.                                                       |
| Functions  | Convert one or more values into a single result.                                                        |
| Predicates | Convert one or more values into a `boolean` result. This is a special case of a function.               |
| Operators  | Convert one or more values into a single result of the same type. This is a special case of a function. |

Java defines several interfaces that fall into these categories that differ
based on the number of parameters and types of the parameters and return values.

|             | `()`                                                                                                   | `(T)`                                                                                                       | `(T, U)`                                                                                                          | `(int)`                                                                                                        | `(long)`                                                                                                         | `(double)`                                                                                                       |
|:------------|--------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------|
| `void`      | [`Runnable`](http://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)                         | [`Consumer<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html)                 | [`BiConsumer<T, U>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiConsumer.html)                | [`IntConsumer`](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntConsumer.html)                 | [`LongConsumer`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongConsumer.html)                 | [`DoubleConsumer`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleConsumer.html)             |
| `R`         | [`Supplier<R>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html)            | [`Function<T,R>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)               | [`BiFunction<T,U,R>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html)               | [`IntFunction<R>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntFunction.html)              | [`LongFunction<R>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongFunction.html)              | [`DoubleFunction<R>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleFunction.html)          |
| `boolean`   | [`BooleanSupplier`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BooleanSupplier.html) | [`Predicate<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html)               | [`BiPredicate<T, U>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiPredicate.html)              | [`IntPredicate`](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntPredicate.html)               | [`LongPredicate`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongPredicate.html)               | [`DoublePredicate`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoublePredicate.html)           |
| `int`       | [`IntSupplier`](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntSupplier.html)         | [`ToIntFunction<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntFunction.html)         | [`ToIntBiFunction<T,U>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToIntBiFunction.html)       | [`IntUnaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntUnaryOperator.html)       | [`LongToIntFunction`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongToIntFunction.html)       | [`DoubleToIntFunction`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleToIntFunction.html)   |
| `long`      | [`LongSupplier`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongSupplier.html)       | [`ToLongFunction<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToLongFunction.html)     | [`ToLongBiFunction<T,U>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToLongBiFunction.html)     | [`IntToLongFunction`](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntToLongFunction.html)     | [`LongUnaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongUnaryOperator.html)       | [`DoubleToLongFunction`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleToLongFunction.html) |
| `double`    | [`DoubleSupplier`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleSupplier.html)   | [`ToDoubleFunction<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToDoubleFunction.html) | [`ToDoubleBiFunction<T,U>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/ToDoubleBiFunction.html) | [`IntToDoubleFunction`](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntToDoubleFunction.html) | [`LongToDoubleFunction`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongToDoubleFunction.html) | [`DoubleUnaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleUnaryOperator.html)   |

In addition, there are some special cases of the above functional interfaces
that don't quite fit into the table.

| Interface                                                                                                        | Description                                                                                                                                                        |
|:-----------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`UnaryOperator<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/UnaryOperator.html)            | [`Function<T, R>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html) where `T` and `R` are the same type.                                |
| [`BinaryOperator<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BinaryOperator.html)          | [`BiFunction<T, U, R>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html) where `T`, `U`, and `R` are the same type.                   |
| [`IntBinaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/IntBinaryOperator.html)       | [`BinaryOperator<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BinaryOperator.html) where parameters and return types are primitive `int`s.    |
| [`LongBinaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongBinaryOperator.html)     | [`BinaryOperator<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BinaryOperator.html) where parameters and return types are primitive `long`s.   |
| [`DoubleBinaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleBinaryOperator.html) | [`BinaryOperator<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BinaryOperator.html) where parameters and return types are primitive `double`s. |
| [`ObjIntConsumer<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/ObjIntConsumer.html)          | [`BiConsumer<T, U>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiConsumer.html) where `U` is a primitive `int`.                                 |
| [`ObjLongConsumer<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/ObjLongConsumer.html)        | [`BiConsumer<T, U>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiConsumer.html) where `U` is a primitive `long`.                                |
| [`ObjDoubleConsumer<T>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/ObjDoubleConsumer.html)    | [`BiConsumer<T, U>`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiConsumer.html) where `U` is a primitive `double`.                              |

## Defining Custom Functional Interfaces

Whenever possible, the standard functional interfaces should be preferred.
Occasionally, none of the standard functional interfaces are appropriate for
your use case, necessitating a custom interface. This is just a matter of
defining an interface with exactly one unimplemented method.

Consider the need for a `IntBiPredicate`: a predicate that behaves exactly like
`IntPredicate` except that it takes two primitive
`int`s as parameters instead of one. One might expect the following definition:

``` java
@FunctionalInterface
interface IntBiPredicate {
    boolean test(int left, int right);

    default IntBiPredicate and(IntBiPredicate other) {
        Objects.requireNonNull(other);
        return (left, right) -> test(left, right) && other.test(left, right);
    }

    default IntBiPredicate negate() {
        return (left, right) -> !test(left, right);
    }

    default IntBiPredicate or(IntBiPredicate other) {
        Objects.requireNonNull(other);
        return (left, right) -> test(left, right) || other.test(left, right);
    }
}
```

In addition to the `test` method that must be implemented, `InBiPredicate` also
defines methods with default implementations for chaining predicates together
with boolean operations. Don't worry about the syntax of these methods, the key
takeaway is the fact they have default implementations, leaving only a single
unimplemented method.

Also note the
[`@FunctionalInterface`](https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html)
annotation. This is a notice to the compiler that this interface is intended to
be used a functional interface. This is **not** a requirement; it instructs the
compiler to generate an error if the definition violates any restriction of a
functional interface.

## Abstract Classes as Functional Interfaces

The astute reader may have noticed that an abstract class with a single
unimplemented method seems to satisfy all of the criteria of a *functional
interface*. *Can an abstract class be used this way?* Sadly, things are not
quite that simple. In short, functional interfaces are intended to be supplied
by [lambda expressions](lambda_expressions.md), which should be seen as a *function* rather
than an object; an instance of an abstract class is an object. Brian Goetz, the
chief Java Language architect, explains it in greater detail on Java's
[java mailing list](http://mail.openjdk.java.net/pipermail/lambda-dev/2013-March/008441.html).

---

[Previous](start.md) | [Up](start.md) | [Next](lambda_expressions.md)

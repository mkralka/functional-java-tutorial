# Functional Interfaces

## What is a Functional Interface?

A necessary feature of any functional programming language is the concept of
[first-class functions](https://en.wikipedia.org/wiki/First-class_function). In
short, this means that functions support operations that are generally available
to other entities (e.g., being assigned to variables, returned from other
functions, and passed as parameters to other functions).

Functional languages (e.g., [Scala](http://scala-lang.org/) and
[Haskell](https://www.haskell.org/)) typically have a syntax with built-in
support for describing a type that is a function. For example, in Scala, a
binary integer operator is defined as follows:

``` scala
(Int, Int) => Int
```

In lieu of built-in syntax support for functions, Java 8 introduced the concept
of a *functional interface*. Types that satisfy the criteria of a *functional
interface* can be used in contexts where function are expected (such as with
[Lambda Expressions](../lambda_expressions/start.md) and
[Method References](../method_references/start.md)).

A *functional interface* is any
[`interface`](https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html)
that has exactly one abstract (unimplemented) method. This does not mean that an
`interface` must have only one method to be considered *functional*; an
`interface` can have multiple methods provided all but one are
[default methods](https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)
(that is, have a default implementation using the `default` keyword). Although
default methods are an important part of functional programming in Java, they
are not critical to understanding basic functional expressions.

For example, consider the following definition of `Bijection`:

``` java
interface Bijection<T, U> {
    U apply(T t);

    T invert(U u);
}
```

`Bijection` is *not* a functional interface because it has two unimplemented
(abstract) methods; it cannot be used as a function.

Now, consider the definition of
[`Predicate`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html):

``` java
interface Predicate<T> {
    bool test(T t);

    default Predicate<T> and(Predicate<? super T> other) {
        return t -> test(t) && other.test(t);
    }

    default Predicate<T> negate() {
        return t -> !test(t);
    }

    default Predicate<T> or(Predicate<? super T> other) {
        return t -> test(t) || other.test(t);
    }
}
```

This *is* a functional interface. Although it has multiple methods, only one is
abstract (the others have a default implementation identified by the `default`
keyword). (Don't worry too much about the syntax of the default implementations;
we'll get to that [later](../lambda_expressions/start.md).)

### Abstract Classes as Functional Interfaces

The astute reader may have noticed that an abstract class with a single abstract
method seems to satisfy all of the criteria of a *functional interface*.

*Can an abstract class be used this way?*

Sadly, things are not quite that simple. In short, functional interfaces are
intended to be supplied by [lambda expressions](../lambda_expressions/start.md)
and [method references](../method_references/start.md), which should be seen as
a *function* (without side effects or state) rather than an object. By contrast,
an instance of an abstract class is an object; a much heavier weight entity.
Brian Goetz, the chief Java Language architect, explains it in greater detail on
Java's
[java mailing list](http://mail.openjdk.java.net/pipermail/lambda-dev/2013-March/008441.html).

---
[Continue](importance.md)

Skip Back | [Up](../start.md) | [Skip Forward](../lambda_expressions/start.md)

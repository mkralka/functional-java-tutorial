# Method References

## Overview

### What is Method Reference?

A method reference is second of the two ways of defining a function (the first
being a [lambda expression](../lambda_expressions/start.md). As its name
suggests, a method reference is a way of defining a function by referring to
an existing method with an appropriate signature.

Consider the following method:

``` java
long reduce(LongBinaryOperator reducer);
```

> Recall that
> [`LongBinaryOperator`](http://docs.oracle.com/javase/8/docs/api/java/util/function/LongBinaryOperator.html)
> describes a function with the following signature:
> 
> ``` java
> long apply(long left, long right);
> ```

Suppose we wish to supply
[`Math.max`](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#max-long-long-)
as the `reduce` parameter. As learned in a [previous
section](../lambda_expressions/start.md), a lambda expression for calling
`Math.max` might be written similarly to the following:

``` java
long max = data.reduce((a, b) -> Math.max(a, b));
```

Notice that the lambda expression is a single statement that evaluates to the
return value of `Math.max` and that the parameters to the lambda expression are
the same as the parameters supplied to `Math.max` (order included).

Notice how the signatures for `reducer` and `Math.max` are actually same? It
would be difficult to claim that functions in java were first class citizens if
`Math.max` could not be directly assigned to `reducer` since they both have the
same signature. Method references makes this possible.

This is all great, but how does one supply a method reference to a function?

### Syntax

A method reference consists of two parts separated by the *scope*
operator (`::`): the context in which the method is called and the method
itself.

> Much like the use of *angle brackets* for generics, the use of the
> *scope* — or double-colon — operator will make C++ programmers feel right at
> home!

In the example above, the context is the `Math` class (don't worry too much
about this now — it will be covered [later](static.md)) and `max` is the method.
Joining them together with `::` results in the following:

``` java
long max = data.reduce(Math::max);
```

As you can see, this is much cleaner and succinct compared to the equivalent
lambda expression.

### Method Reference Types

Java 8 supports four different types of method references, each with subtle
differences between the context in which they are used and the parameters that
are expected. The following table describes the different types of method
references:

| Type                          | Form                         | Example                |
|:------------------------------|:-----------------------------|:-----------------------|
| [static](static.md)           | `<class>::<staticMethod>`    | `Math::sqrt`           |
| [bound](bound.md)             | `<object>::<instanceMethod>` | `employeesByCity::put` |
| [unbound](unbound.md)         | `<class>::<instanceMethod>`  | `Object::toString`     |
| [constructor](constructor.md) | `<class>::new`               | `Employee::new`        |

Let's explore these different method reference types!

---

[Continue](static.md)

[Skip Back](../lambda_expressions/start.md) | [Up](../start.md) | [Skip Forward](../optional/start.md)

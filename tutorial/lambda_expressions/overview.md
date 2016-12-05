# Lambda Expressions

## Overview

A lambda expression is the first of the two ways of defining a function (the
second being a [method reference](../method_references/start.md)). It is a way of
succinctly describing the relationship between the functions input and output.

## Writing Lambda Expressions

Although the syntax may seem to vary wildly, all lambda expressions contain the
same two parts separated with the *arrow* operator (`->`): the parameter list
and body. For example:

``` java
IntBinaryOperator multiplier = (int lhs, int rhs) -> {
    return lhs * rhs;
};
```
Decomposing this example, we can see that the lambda expression:
* has two parameters (`lhs` and `rhs`), both of type `int`
* has a body that consists of a single `return` statement that evaluates to the
  product of the supplied parameters

This example shows the most verbose way to define a lambda expression. Java
allows for the following optimizations:

1. If *all* parameter types can be inferred, they may *all* be omitted.

   *Note: Either all are present or all are absent; it is not valid syntax to
   supply only some of the parameter types.*

2. If there is a single parameter with an inferred type, the parentheses around
   the parameter may be omitted.

3. If the body consists of a single statement, the braces, `return` keyword, and
   trailing semicolon (`;`) may be omitted.

   *Note: Either they all appear or none of them appear.*

Using these rules, a more succinct version of the above example may look similar
to the following:
``` java
IntBinaryOperator multiplier = (lhs, rhs) -> lhs * rhs;
```

---
[Continue](ex001.md)

[Skip Back](../functional/start.md) | [Up](../start.md) | [Skip Forward](../method_references/start.md)

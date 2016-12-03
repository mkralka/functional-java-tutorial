# Lambda Expressions

## What You Will Learn

When you have completed this section of the tutorial, you should understand:

1. how to write single-statement lambda expressions

2. how to write multi-statement lambda expressions

2. that its possible to write succinct lambda expressions by relying on the
   compiler's ability to infer types

## Exercises

|                          |                                                                                                                         |
|:-------------------------|:------------------------------------------------------------------------------------------------------------------------|
| Command to run all tests | `mvn test -Dtest=LambdaExpressionsTest`                                                                                 |
| Source File              | [`src/main/java/functionaljava/LambdaExpressions.java`](../src/main/java/functionaljava/LambdaExpressions.java)         |
| Test Source File         | [`src/test/java/functionaljava/LambdaExpressionsTest.java`](../src/test/java/functionaljava/LambdaExpressionsTest.java) |

All exercises in this section of the tutorial will require modifications to
[`LambdaExpressions.java`](../src/main/java/functionaljava/LambdaExpressions.java);
open it in your favorite editor now and navigate to the [base directory](..) of
this tutorial (i.e., the directory containing [`README.md`](../README.md))

Those unfamiliar with Maven or would like to run tests through an IDE, see
[Running Tests](running_tests.md) for help.

If, when you run the unit tests, you have trouble compiling your code, read over
the syntax described herein to see where you may have gone wrong. If the tests
fail, your implementation is wrong; use the parameters along with the actual and
expected results to guide you to where your implementation is wrong.

## Writing Lambda Expressions

A lambda expression is the first of the two ways of defining a function (the
second being a [method reference](method_references.md)). It is a way of
succinctly describing the relationship between the functions input and output.

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

### Exercise: Define a Unary Operator

For the first exercise, we will define a lambda expression for a unary operator,
which is a function that produces a single output from a single input of the
same type. This will probably be one of the simplest lambdas you will ever
define.

Replace the body of the `squarer` method with one that returns a unary operator
that squares its input.

*Be sure to define the operator as a lambda expression and not an anonymous
class that implements
[`LongUnaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongUnaryOperator.html).*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=LambdaExpressionsTest#testSquarer
```

If the tests pass, congratulations! You've written your first lambda expression!

### Exercise: Define a Binary Operator

In this exercise we will define a lambda expression for a binary operator,
which is a function that produces a single output from two inputs of the same
type. Like the first exercise, this lambda will be defined as a single
mathematical expression.

Replace the body of the `triangleArea` method with one that returns a binary
operator that calculates the area of a triangle from the lengths of its base and
height (in that order).

*Be sure to define the operator as a lambda expression and not an anonymous
class that implements
[`DoubleBinaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/DoubleBinaryOperator.html).*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=LambdaExpressionsTest#testTriangleArea
```

#### Hints
* [Computing the area of a
triangle](https://en.wikipedia.org/wiki/Triangle#Computing_the_area_of_a_triangle)

### Exercise: Unary Operator with Statement Block Body

In this exercise we will define a lambda expression for a unary operator with a
non-trivial body. In previous exercises, the body of the lambda expression
consisted of a single expression, allowing the braces and return statement to be
omitted. This operator is slightly more complicated, requiring a statement
block.

Replace the body of the `fibonacci` method with one that returns a unary
operator that calculates the nth Fibonacci number. Recall that the Fibonacci
sequence is defined as:
* f(0) = 0
* f(1) = 1
* f(n) = f(n - 1) + f(n - 2), for n > 1

***It may be tempting to write this recursively. The naïve recursive solution is
prohibitively expensive (computationally). You will need to provide an iterative
(or tail recursive) solution.***

*Be sure to define the operator as a lambda expression and not an anonymous
class that implements
[`LongUnaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/LongUnaryOperator.html).*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=LambdaExpressionsTest#testFibonacci
```

#### Hints
* [The first 21 Fibonacci numbers](https://en.wikipedia.org/wiki/Fibonacci_number#List_of_Fibonacci_numbers)
* [Implementation in Java](https://en.wikibooks.org/wiki/Algorithm_Implementation/Mathematics/Fibonacci_Number_Program#Java)

### Exercise: Function with Arity two and Statement Block Body

In this exercise we will define a lambda expression for a function with arity
two (where the parameters are of different types) and a non-trivial body. In
previous exercises, the body of the lambda expression consisted of a single
expression, allowing the braces and return statement to be omitted. This
operator is slightly more complicated, requiring a statement block.

Replace the body of the `stringMultiplier` method with one that returns a
function (of arity two) that returns its first parameter (a `String`) repeated
as many times as specified by the second parameter (an `Integer`).

The following table shows example inputs and the associated output:

| x       |    y | f(x, y)                            |
|:--------|-----:|:-----------------------------------|
| `"a"`   | `0`  | `""`                               |
| `"foo"` | `3`  | `"foofoofoo"`                      |
| `"bar"` | `10` | `"barbarbarbarbarbarbarbarbarbar"` |

*Be sure to define the operator as a lambda expression and not an anonymous
class that implements
[`BiFunction`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html).*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=LambdaExpressionsTest#testStringMultiplier
```

#### Hints
* The
  [`StringBuilder`](https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html)
  provides an efficient way to dynamically build `String`s without creating
  intermediate objects.

---

[Previous](functional.md) | [Up](start.md) | [Next](method_references.md)


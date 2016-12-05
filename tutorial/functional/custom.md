# Functional Interfaces

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

> Don't worry too much about the syntax used in the default method
> implementations; this is covered [later](../lambda_expressions/start.md).

In addition to the `test` method that must be implemented, `IntBiPredicate` also
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

---
[Continue](end.md)

Skip Back | [Up](../start.md) | [Skip Forward](../lambda_expressions/start.md)

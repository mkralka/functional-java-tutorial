# Method References

## Static Method Reference

Static method references are the simplest type of references. Since `static`
methods are not invoked on an object, references to `static` methods can be
assigned to a function with matching signature, without any magic going on under
the covers.

Recall our [earlier example](overview):

``` java
long max = data.reduce(Math::max);
```

The context, `Math`, is a class; this indicates that the method is not
bound to any particular object. The
method, `max`, is a `static` method; this indicates that the reference is to the
`Math.max` method and the reference has the same signature the method being
referenced (in this case, the same as
[`LongBinaryOperator`](http://docs.oracle.com/javase/8/docs/api/java/util/function/LongBinaryOperator.html)).

### Exercises

1. [Exercise: Static Method Reference](static_ex1.md)

---

[Continue](bound.md)

[Skip Back](../lambda_expressions/start.md) | [Up](../start.md) | [Skip Forward](../optional/start.md)

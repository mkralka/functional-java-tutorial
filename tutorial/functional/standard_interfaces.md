# Functional Interfaces

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

### Function Composition

One of the powerful features supported by the standard functional interfaces in
Java 8 is the ability to easily compose new functions from existing ones.

Consider we have a function that extracts the `EmergencyContact` from an
`Employee` and another function that extracts the `Name` from an
`EmergencyContact`:

``` java
Function<Employee, EmergencyContact> employeeToEmergencyContact = Employee::getEmergencyContact;
Fuction<EmergencyContact, Name> emergencyContactToName = EmergencyContact::getName;
```

> Don't worry too much about the syntax used to assign values to
> `employeeToEmergencyContact` and `emergencyContactToName`; this is covered
> [later](../method_references/start.md).

Without support for composing functions, creating a function to extract the name
of an emergency contact from an employee might be written as follows:

``` java
Function<Employee, Name> employeeToEmergencyContactName =
        employee -> emergencyContactToName(employeeToEmergencyContact(employee));
```

> Again, don't worry too much about the syntax used to assign a value to
> `employeeToEmergencyContactName`; this is covered
> [later](../lambda_expressions/start.md).

Using methods like
[`Function.andThen`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html#andThen-java.util.function.Function-),
`employeeToEmergencyContact` and `emergencyContactToName` can, instead, be
composed as follows:

``` java
Function<Employee, Name> employeeToEmergencyContactName =
        employeeToEmergencyContact.andThen(emergencyContactToName);
```

This may seem more natural because the specified in the order they are applied
rather than the reverse order.

---

[Continue](custom.md)

Skip Back | [Up](../start.md) | [Skip Forward](../lambda_expressions/start.md)

# Optional

`null`: the bane of the programmer. It seemed like such an elegant solution to
supporting the concept of an object being absent but that's not how things
turned out. Instead of being elegant, is is a source of bugs as the potential
*absentness* of an object is not apparent to the programmer. Sadly, assumptions
are made that are frequently wrong resulting in overly complicated logic for
handling a `null` that can never happen or bugs are introduced by not handing
`null` when one should.

Enter the
[`Optional`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html).
The explicit: *this object has a non-zero the probability of being absent,
deal with it.* (Cue the sunglasses,)

Although strictly speaking not a feature of functional Java, 
[`Optional`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
is used throughout the new functional Java APIs, including
[`Streams`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html),
making it an important feature to cover in this tutorial.

## What You Will Learn

When you have completed this section of the tutorial, you should understand:

1. how `Optional`s can help you be explicit about potentially absent values,
   this avoiding NPEs

2. how to transform `Optional`s

3. how to conditionally execute code based on an `Optional`'s state

## Exercises

|                          |                                                                                                         |
|:-------------------------|:--------------------------------------------------------------------------------------------------------|
| Command to run all tests | `mvn test -Dtest=OptionalsTest`                                                                         |
| Source File              | [`src/main/java/functionaljava/Optionals.java`](../src/main/java/functionaljava/Optionals.java)         |
| Test Source File         | [`src/test/java/functionaljava/OptionalsTest.java`](../src/test/java/functionaljava/OptionalsTest.java) |

All exercises in this section of the tutorial will require modifications to
[`Optionals.java`](../src/main/java/functionaljava/Optionals.java); open it in
your favorite editor now and navigate to the [base directory](..) of this
tutorial (i.e., the directory containing [`README.md`](../README.md))

Those unfamiliar with Maven or would like to run tests through an IDE, see
[Running Tests](running_tests.md) for help.

If, when you run the unit tests, you have trouble compiling your code, read over
the syntax described herein to see where you may have gone wrong. If the tests
fail, your implementation is wrong; use the parameters along with the actual and
expected results to guide you to where your implementation is wrong.

## Overview

[`Optional`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
is a way of codifying *absentness* of an object, forcing the programmer to deal
with the possibility. When used, code can be much more fluent as all parameters
and all return values will have operations that can be performed on them. Code
that would have been written similar to the following:

``` java
if (customer != null) {
    AddressBook addressBook = customer.getAddressBook();
    if (addressBook != null) {
        Address preferredAddress = addressBook.getPreferredAddess();
        if (preferredAddress != null) {
            order.setDeliveryToAddress(preferredAddress);
        }
    }
}
```

can now be written, using `Optional`s, similar to the following:

``` java
customer.getAddressBook()
        .flatMap(AddressBook::getPreferredAddress)
        .ifPresent(order::setDeliveryAddress);
```

*By Grabthar's Hammer ... What a Savings!*
[-- Dr. Lazarus](https://youtu.be/oewMbg8wFQU)

### Native Types

In addition to the main
[`Optional`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
class, custom classes exist to represent optional `int`, `long`, and `double`.
Whenever possible, these optionals should be used for native types to eliminate
the (computational) overhead associated with boxing/unboxing `Integer`, `Long`,
and `Double` objects (respectively), which can be substantial.

These custom optional classes won't be covered; more documentation can be found
in the API documentation:

* [`IntOptional`](https://docs.oracle.com/javase/8/docs/api/java/util/OptionalInt.html)
* [`LongOptional`](https://docs.oracle.com/javase/8/docs/api/java/util/OptionalLong.html)
* [`DoubleOptional`](https://docs.oracle.com/javase/8/docs/api/java/util/OptionalDouble.html)

## Creating Optionals

There are three methods for creating `Optional`s, each with slightly different
use cases. The following table describes the methods for creating an `Optional`.

| Method                                                                                          | Description                                                                                 |
|:------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------|
| [`of`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#of-T-)                 | Create an `Optional` from the specified non-`null` value, throwing an exception if `null`.  |
| [`ofNullable`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#ofNullable-T-) | Create an `Optional` from the specified value if non-`null`, otherwise an empty `Optional`. |
| [`empty`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#empty--)            | Returns an empty `Optional` instance. (the empty `Optional` is a singleton).                |

## Transforming Optionals

You will often find yourself needing to transform the value stored in an
`Optional` (possibly of a different type), if a value is present in the
`Optional`.  An operation that is roughly equivalent to the following:

``` java
AddressBook addressBook = null;
if (employee != null) {
    addressBook = employee.getAddressBook;
}
```

`Optional` has native support for this operation with the
[`map`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#map-java.util.function.Function-)
and
[`flatMap`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#flatMap-java.util.function.Function-)
operations.

`map` converts the value stored in a non-empty `Optional` based on a supplied
transformation function. If the original `Optional` was empty or the
transformation function returns `null`, the new `Optional` will be empty.
Otherwise, the new optional will be non-empty and will contain the generated
value. It is roughly equivalent to the following code:

``` java
<T, U> Optional<U> map(Optional<T> input, Function<T, U> transformation) {
    if (!input.isPresent()) {
        return Optional.empty();
    }
    return Optional.ofNullable(transformation.apply(input.get()));
}
```

`flatMap` is similar to `map` except that the supplied transformation function
generates an `Optional<T>` (rather than a `T`). `flatMap` automatically flattens
what would otherwise be an `Optional<Optional<T>>` into an `Optional<T>`.

``` java
<T, U> Optional<U> flatMap(Optional<T> input, Function<T, Optional<U>> transformation) {
    if (!input.isPresent()) {
        return Optional.empty();
    }

    return transformation.apply(input.get());
}
```

### Exercise: Transform (map) one `Optional` into Another

In this exercise we will transform (map) an `Optional` of one type into an
`Optional` of another, leaving an empty `Optional` as is.

Replace the body of `getEmergencyContactName` with one that returns the name of
an employee's emergency contact. If the employee doesn't have an emergency
contact, an empty `Optional` should be returned.

*Be sure to use the `Optional`'s functional API and not any branches or `if`
statements.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=OptionalsTest#testGetEmergencyContactName
```

#### Hints
* The employee's emergency contact can be accessed by calling
  `getEmergencyContact()`. Unfortunately, this returns an
  `Optional<EmergencyContact>` and not the `Optional<Name>` that we need.

### Exercise: Transform (map) and Flatten one `Optional` into Another.

In this exercise we will transform (map) an `Optional` of one type into an
`Optional` of another using a transformation that generates its own `Optional`.

Replace the body of `getHomePhoneNumber` with one that returns an employee's
home phone number, if available. If the employee doesn't have any contact
information or the contact information doesn't include a home phone number, an
empty `Optional` should be returned.

*Be sure to use the `Optional`'s functional API and not any branches or `if`
statements.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=OptionalsTest#testGetHomePhoneNumber
```

## Emptying Optionals

In some cases, an `Optional` can contain a value that should be treated
effectively the same as an empty `Optional`.

[`filter`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#filter-java.util.function.Predicate-)
provides a way of demoting a non-empty `Optional` that doesn't match an
arbitrary predicate. It is roughly equivalent to the following code:

``` java
<T> Optional<T> filter(Optional<T> input, Predicate<T> predicate) {
    if (input.isPresent() && predicate.test(input.get())) {
            return input;
    }
    return Optional.empty();
}
```

### Exercise: Empty (filter) an Optional

In this exercise we will empty (filter) an `Optional` if the content of the
`Optional` matches some predicate.

Replace the body of `getContactableEmergencyContact` with one that returns an
employee's emergency contact, if said contact is contactable. If the employee
has no emergency contact or that contact as no contact information, an empty
`Optional` should be returned.

*Be sure to use the `Optional`'s functional API and not any branches or `if`
statements.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=OptionalsTest#testGetContactableEmergencyContact
```

#### Hints
* `CONTACT_INFO_IS_EMPTY`, a `Predicate<ContactInformation>`, can be used to
  determine if a `ContactInformation` is absent of all contact details.

## Execution Condition on an `Optional`'s State

Conditionally executing code has traditionally be done using `if` statements;
an imperative technique. `Optional` allows for executing arbitrary code if an
`Optional` value is present (e.g., to publish an event to a some
[pubsub](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) bus).

[`ifPresent`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#ifPresent-java.util.function.Consumer-)
invokes an arbitrary consumer if the `Optional` contains a (non-`null`) value.
It is roughly equivalent to the following:

``` java
<T> void ifPresent(Optional<T> optional, Consumer<T> consumer) {
    if (optional.isPresent()) {
        consumer.accept(optional.get());
    }
}
```

### Exercise: Execute Code if and `Optional` is Present.

In this exercise we will execute code (consume an `Optional`s value) if and
only if an `Optional` is non-empty.

Replace the body of `collectEmployeesWithoutEmergencyContacts` with one that
adds the employee's emergency contact to `allEmergencyContacts` if the emergency
contact is present.

*Be sure to use the `Optional`'s functional API and not any branches or `if`
statements.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=OptionalsTest#testCollectEmployeesWithoutEmergencyContacts
```

## Unwrapping an Optional

An `Optional` is of no use unless you can extract the value stored within.
`Optional` has a few methods for extracting the value, with slightly different
behaviors, as described in the following table:

| Operation                                                                                                                   | Description                                                                                                                                                                                                      |
|:----------------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`get`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#get--)                                            | Unconditionally extract the value, throwing if empty. **Use with caution.**                                                                                                                                      |
| [`orElse`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#orElse-T-)                                     | Extract the value if present, otherwise return the provided (default) value.                                                                                                                                     |
| [`orElseGet`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#orElseGet-java.util.function.Supplier-)     | Extract the value if present, otherwise invoke the provided supplier and return the generated (default) value. This is ideal if the default needs to be generated and will ensure it's only generated if needed. |
| [`orElseThrow`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#orElseThrow-java.util.function.Supplier-) | Extract the value if present, otherwise invoke the provided supplier to generate an exception and throw it.                                                                                                      |

### Exercise: Get Value with Default

In this exercise we will extract the value from an `Optional`, defaulting to
a specific other value if the `Optional` is empty.

Replace the body of `preferredName` with one that returns the individual's first
name unless they have a nickname, which should be returned in its place.

*Be sure to use the `Optional`'s functional API and not any branches or `if`
statements.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=OptionalsTest#testPreferredName
```

### Exercise: Get Value or Supplied Default

In this exercise we will extract the value from an `Optional`; however, if the
`Optional` is empty, a default value will be generated by a supplier.

Replace the body of `getEmployeeContactInformation` with one that returns the
employee's contact information. If the employee doesn't have any contact
information, use `contactInformationFetcher` to retrieve it. Since fetching
contact information is an expensive operation, only do so if needed.
fetch contact information if 

*Be sure to use the `Optional`'s functional API and not any branches or `if`
statements. Only call `contactInformationFetcher.apply` if necessary.*

To test your solution, run the predefined unit tests:

``` bash
mvn test -Dtest=OptionalsTest#testGetEmployeeContactInformation
```

---

[Previous](method_references.md) | [Up](start.md) | [Next](streams.md)

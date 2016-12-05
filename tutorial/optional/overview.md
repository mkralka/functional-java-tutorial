# Optional

## Overview

`null`: the bane of the programmer. It seemed like such an elegant solution to
supporting the concept of an object being absent but that's not how things
turned out. Instead of being elegant, is is a source of bugs as the potential
*absentness* of an object is not apparent to the programmer. Sadly, assumptions
are made that are frequently wrong, resulting in overly complicated logic for
handling a `null` that can never happen or bugs from not handing `null` when one
should.

Enter the
[`Optional`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html).
The explicit: *this object has a non-zero probability of being absent,
deal with it.*
([Cue the sunglasses](https://media.giphy.com/media/3o8doOlGO3pjQa5h28/giphy.gif)!)

> Although strictly speaking not a feature of functional Java, 
> [`Optional`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
> is used throughout the new functional Java APIs, including
> [`Streams`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html),
> making it an important feature to cover in this tutorial.

When used, code can be much more fluent as all parameters and all return values
can be non-null, allowing operations to be performed on them. Code that would
have been written similar to the following:

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

can now be written, using `Optional`, similar to the following:

``` java
customer.getAddressBook()
        .flatMap(AddressBook::getPreferredAddress)
        .ifPresent(order::setDeliveryAddress);
```

> *By Grabthar's Hammer ... What a Savings!*
> [-- Dr. Lazarus](https://youtu.be/oewMbg8wFQU)

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

---
[Continue](creating.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

# Optional

## Exercise: Transform (map) and Flatten one `Optional` into Another.

In this exercise we will transform (map) an `Optional` of one type into an
`Optional` of another using a transformation that generates its own `Optional`,
leaving an empty `Optional` as is.

Open
[`src/main/java/functionaljava/Optionals.java`](../../src/main/java/functionaljava/Optionals.java)
and replace the body of `getHomePhoneNumber` with one that returns an employee's
home phone number, if available. If the employee doesn't have any contact
information or the contact information doesn't include a home phone number, an
empty `Optional` should be returned.

To test your solution, run the following command:

``` bash
mvn test -Dtest=OptionalsTest#testGetHomePhoneNumber
```

[Solution](transforming_ex2_sltn.md) | [Exercises FAQ](../exercises.md)

### Hints:

* An employee's contact information can be retrieved using
  `Employee.getContactInformation`.
* A home phone number can be retrieved from contact information using
  `ContactInformation.getHomePhone`.

---

[Continue](emptying.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

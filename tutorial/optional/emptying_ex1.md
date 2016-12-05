# Optional

## Exercise: Empty (filter) an Optional

In this exercise we will empty (filter) an `Optional` if the content of the
`Optional` matches some predicate.

Open
[`src/main/java/functionaljava/Optionals.java`](../../src/main/java/functionaljava/Optionals.java)
and replace the body of `getContactableEmergencyContact` with one that returns
an employee's emergency contact, if said contact is contactable. If the employee
has no emergency contact or that contact as no contact information, an empty
`Optional` should be returned.

`CONTACT_INFO_IS_EMPTY`, a `Predicate<ContactInformation>`, can be used to
determine if a `ContactInformation` is absent of all contact details.

To test your solution, run the following command:

``` bash
mvn test -Dtest=OptionalsTest#testGetContactableEmergencyContact
```

[Solution](emptying_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](conditional.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

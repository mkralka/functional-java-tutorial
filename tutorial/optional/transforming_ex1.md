# Optional

## Exercise: Transform (map) one `Optional` into Another

In this exercise we will transform (map) an `Optional` of one type into an
`Optional` of another, leaving an empty `Optional` as is.

Open
[`src/main/java/functionaljava/Optionals.java`](../../src/main/java/functionaljava/Optionals.java)
and replace the body of `getEmergencyContactName` with one that returns the name
of an employee's emergency contact. If the employee doesn't have an emergency
contact, an empty `Optional` should be returned.

To test your solution, run the following command:

``` bash
mvn test -Dtest=OptionalsTest#testGetEmergencyContactName
```

[Solution](transforming_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

### Hints:

* An employee's emergency contact can be retrieved using
  `Employee.getEmergencyContact`.
* An emergency contact's name can be retrieved using `EmergencyContact.getName`.

---

[Continue](transforming_ex2.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

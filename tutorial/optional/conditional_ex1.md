# Optional

## Exercise: Execute Code if and `Optional` is Present

In this exercise we will execute code (consume an `Optional`s value) if and
only if an `Optional` is non-empty.

Open
[`src/main/java/functionaljava/Optionals.java`](../../src/main/java/functionaljava/Optionals.java)
and replace the body of `collectEmployeesWithoutEmergencyContacts` with one that
adds the employee's emergency contact to `allEmergencyContacts` if the emergency
contact is present.

To test your solution, run the following command:

``` bash
mvn test -Dtest=OptionalsTest#testCollectEmployeesWithoutEmergencyContacts
```

[Solution](conditional_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](unwrapping.md)

[Skip Back](../method_references/start.md) | [Up](../start.md) | [Skip Forward](../streams/start.md)

# Optional

## Solution: Execute Code if and `Optional` is Present

``` java
public void collectEmployeesWithoutEmergencyContacts(
        Employee employee,
        Collection<EmergencyContact> allEmergencyContacts) {
    employee.getEmergencyContact()
            .ifPresent(allEmergencyContacts::add);
}
```

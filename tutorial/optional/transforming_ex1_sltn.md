# Optional

## Solution: Transform (map) one `Optional` into Another

``` java
public Optional<Name> getEmergencyContactName(Employee employee) {
    return employee.getEmergencyContact()
            .map(EmergencyContact::getName);
}
```

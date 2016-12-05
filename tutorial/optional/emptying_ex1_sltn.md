# Optional

## Solution: Empty (filter) an Optional

``` java
public Optional<EmergencyContact> getContactableEmergencyContact(Employee employee) {
    return employee.getEmergencyContact()
            .filter(contact ->
                    contact.getContactInformation()
                            .filter(CONTACT_INFO_IS_EMPTY.negate())
                            .isPresent());
}
```

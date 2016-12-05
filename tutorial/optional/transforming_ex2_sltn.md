# Optional

## Solution: Transform (map) and Flatten one `Optional` into Another.

``` java
public Optional<PhoneNumber> getHomePhoneNumber(Employee employee) {
    return employee.getContactInformation()
            .flatMap(ContactInformation::getHomePhone);
}
```

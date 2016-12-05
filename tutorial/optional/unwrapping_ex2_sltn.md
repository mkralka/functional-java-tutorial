# Optional

## Solution: Get Value or Supplied Default

``` java
public ContactInformation getEmployeeContactInformation(
        Employee employee,
        Function<Name, ContactInformation> contactInformationFetcher) {
    return employee.getContactInformation()
            .orElseGet(() -> contactInformationFetcher.apply(employee.getName()));
}
```

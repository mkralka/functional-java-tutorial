# Streams

## Solution: Group and Count Elements

``` java
public Map<Office, Long> employeeCountByOffice(Collection<Employee> employees) {
    return employees.stream()
            .collect(Collectors.groupingBy(Employee::getLocation, Collectors.counting()));
}
```

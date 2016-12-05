# Streams

## Solution: Find the Maximum Element by Group

``` java
public Map<Office, Employee> newestEmployeeByOffice(Collection<Employee> employees) {
    return employees.stream()
            .collect(
                    Collectors.groupingBy(
                            Employee::getLocation,
                            Collectors.collectingAndThen(
                                    Collectors.maxBy(
                                            Comparator.comparing(Employee::getStartDate)),
                                    Optional::get)));
}
```

# Streams

## Solution: Find the Minimum Element

``` java
public Optional<Employee> mostSeniorEmployee(Collection<Employee> employees) {
    return employees.stream()
            .min(Comparator.comparing(Employee::getStartDate));
}
```

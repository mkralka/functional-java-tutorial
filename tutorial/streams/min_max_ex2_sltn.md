# Streams

## Solution: Find the Minimum Elements

``` java
public Set<Employee> mostSeniorEmployees(Collection<Employee> employees) {
    return employees.stream()
            .map(Employee::getStartDate)
            .min(LocalDate::compareTo)
            .map(
                    earliestStartDate ->
                            employees.stream()
                                    .filter(e -> e.getStartDate().equals(earliestStartDate))
                                    .collect(Collectors.toSet()))
            .orElse(Collections.emptySet());
}
```

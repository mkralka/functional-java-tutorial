# Streams

## Solution: Sort a Collection

``` java
public Collection<Employee> sortEmployeesByName(Collection<Employee> employees) {
    Comparator<Name> nameComparator =
            Comparator.comparing(Name::getFamilyName, String::compareToIgnoreCase)
                    .thenComparing(Name::getGivenName, String::compareToIgnoreCase);
    Comparator<Employee> employeeComparator =
            Comparator.comparing(Employee::getName, nameComparator)
                    .thenComparing(Employee::getStartDate);
    return employees.stream()
            .sorted(employeeComparator)
            .collect(Collectors.toList());
}
```

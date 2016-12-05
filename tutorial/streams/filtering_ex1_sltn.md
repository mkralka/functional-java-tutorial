# Streams

## Solution: Filtering and Transforming a List

``` java
public Set<String> employeeFamilyNamesAtOffice(Collection<Employee> employees, Office office) {
    return employees.stream()
            .filter(employee -> employee.getLocation().equals(office))
            .map(Employee::getName)
            .map(Name::getFamilyName)
            .collect(Collectors.toSet());
}
```

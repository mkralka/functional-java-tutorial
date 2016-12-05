# Streams

## Grouping Elements

It's often useful to group together the elements of a stream that share a common
value for a given property and reduce elements in one group independently from
elements in another group (e.g., group employees together based on the office
they work from to count how many employees are in each office).

Using
[`groupingBy`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html#groupingBy-java.util.function.Function-)
for the terminal operation, the elements can be partitioned into groups based on
an arbitrary property of each element. The elements in each group are then
collected (into a `List` by default). Grouping a `Stream` always generates a
`Map` whose keys are the values by which the elements were grouped.

Since `groupingBy` can chain with another `Collector`, it's possible to create
arbitrarily deep groupings (e.g., group employees by location, then by
department, and finally by type -- contract, full-time, part-time, hourly,
etc.), resulting in nested `Map`s (e.g.,
`Map<Office, Map<Department, Map<EmployeeType, Set<Employee>>>>`).

``` java
Map<Office, Map<Department, Map<EmployeeType, Set<Employee>>>> employeeGroups =
        employees.stream()
                .collect(
                        Collectors.groupingBy(Employee::getOffice,
                        Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(Employee::getType,
                        Collectors.toSet()))));
```

### Exercises

1. [Group and Count Elements](grouping_ex1.md)

---

[Continue](partitioning.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

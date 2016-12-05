# Streams

## Solution: Partition a Stream and Reduce

``` java
public double percentageStartedBefore(Collection<Employee> employees, LocalDate date) {
    Map<Boolean, Long> partitionSizes = employees.stream()
            .map(Employee::getStartDate)
            .collect(Collectors.partitioningBy(date::isAfter, Collectors.counting()));

    long numStartedBefore = partitionSizes.getOrDefault(TRUE, 0L);
    long numStartedOnOrAfter = partitionSizes.getOrDefault(FALSE, 0L);
    long total = numStartedBefore + numStartedOnOrAfter;
    return (double) numStartedBefore / (double) Math.max(total, 1L);
}
```

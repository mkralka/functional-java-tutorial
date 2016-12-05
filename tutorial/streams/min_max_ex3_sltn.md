# Streams

## Solution: Find the Minimum Elements

``` java
public Set<Employee> mostSeniorEmployees(Stream<Employee> employeeStream) {
    return employeeStream.collect(
            HashSet::new,
            (accumulator, operand) -> {
                if (accumulator.isEmpty()) {
                    // First employee, must be the most senior.
                    accumulator.add(operand);
                } else {
                    LocalDate accumulatorDate = accumulator.iterator().next().getStartDate();
                    LocalDate operandDate = operand.getStartDate();
                    if (operandDate.isBefore(accumulatorDate)) {
                        // This new employee is more senior than those seen so far;
                        // employee is now the most senior (by themselves).
                        accumulator.clear();
                        accumulator.add(operand);
                    } else if (!operandDate.isAfter(accumulatorDate)) {
                        // This new employee has the same start date as the most senior
                        // found so far; employee is included in the most senior.
                        accumulator.add(operand);
                    }
                }
            },
            (Set<Employee> accumulator, Set<Employee> operands) -> {
                if (accumulator.isEmpty()) {
                    // No existing employees yet, the new ones must be the most
                    // senior.
                    accumulator.addAll(operands);
                } else if (!operands.isEmpty()) {
                    // There are existing employees and new employees. Since
                    // all the employees in each set have the same start date,
                    // figure out which one is newer to know which to keep.
                    // and only if they are more senior.
                    LocalDate accumulatorDate = accumulator.iterator().next().getStartDate();
                    LocalDate opperandsDate = operands.iterator().next().getStartDate();
                    if (opperandsDate.isBefore(accumulatorDate)) {
                        // The new employees all more senior so should replace the
                        // existing ones.
                        accumulator.clear();
                        accumulator.addAll(operands);
                    } else if (!opperandsDate.isAfter(accumulatorDate)) {
                        // The new employees have the same seniority as the existing
                        // ones so should be added to them.
                        accumulator.addAll(operands);
                    }
                }
            });    
}
```

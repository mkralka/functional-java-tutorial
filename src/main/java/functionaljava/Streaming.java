package functionaljava;

import functionaljava.types.Employee;
import functionaljava.types.Office;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public final class Streaming {
    public static final Streaming INSTANCE = new Streaming();

    private Streaming() {
    }

    /**
     * Create a {@link List} consisting of the squares (value multiplied by itself) of the integers
     * found in {@code integers}.
     * <p>
     * The order of {@code input} is maintained in the created list.
     * </p>
     *
     * @param integers The input integers.
     * @return A list consisting of the squares of the integers found in {@code integers}.
     */
    public List<Integer> squareIntegerList(List<Integer> integers) {
        // Replace the body of this method with one that creates copy of the
        // input list but with each value squared (multiplied by itself). The
        // output list should be the same length as the input list, duplicates
        // and all.
        //
        // Your solution should use the functional Streams API rather than
        // directly iterating though the input and manually accumulating the
        // results in a mutable collection.
        //
        // HINT: Stream.collect is used to terminate a stream and collect (and
        //       return) the results.
        // HINT: The Collectors class contains many Collector implementations
        //       that accumulate entries of a stream into various Collection
        //       types.
        //       See https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
        return null;
    }

    /**
     * Create a {@link Set} consisting of all employees from {@code employees} that work out of the
     * office {@code office}.
     *
     * @param employees The employees from which to search for those working out of {@code office}.
     * @param office    The office out of which to find employees in {@code employees}.
     * @return A {@link Set} consisting of all employees from {@code employees} that work out of the
     * office {@code office}.
     */
    public Set<String> employeeFamilyNamesAtOffice(Collection<Employee> employees, Office office) {
        // Replace the body of this method with one that creates a Set
        // containing the family names of all employees working out of
        // office.
        //
        // Your solution should use the functional Streams API rather than
        // directly iterating though the input and manually accumulating the
        // results in a mutable collection.
        //
        // HINT: A stream can be filtered by supplying a predicate
        //       See https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
        return null;
    }

    /**
     * Create sorted version of {@code employees}.
     * <p>
     * Sorting is case insensitive by family name, then by given name (alphabetically earlier appear
     * first). If two employees share the same name, then the one whose start data is earlier will
     * appear first. If two employees with the same name started on the same day, their order in the
     * list doesn't matter.
     *
     * @param employees The employees to sort.
     * @return A version of {@code employees} sorted by employee name.
     */
    public Collection<Employee> sortEmployeesByName(Collection<Employee> employees) {
        // Replace the body of this method with one that sorts the input
        // employee list by name, breaking ties using the employee's start date.
        // When sorting by name, employees are first ordered by family name
        // then by given name; nicknames is not considered.
        //
        // Your solution should use the functional Streams API rather than
        // directly iterating though the input and manually accumulating the
        // results in a mutable collection.
        //
        // HINT: It's safe to assume String.compareIgnoreCase will correctly
        //       order family and given names.
        // HINT: A stream can easily be sorted by supplying an appropriate
        //       comparator.
        //       See https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
        // HINT: Comparator has a functional-friendly API for stitching together
        //       sort-key extraction and comparators. E.g., Comparator.comparing
        //       and Comparator.thenComparing.
        //       See https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
        return null;
    }

    /**
     * Determine the number of employees working out of each office.
     *
     * @param employees The employees for which to count the number of employees working in each
     *                  office.
     * @return The number of employees working out of each office.
     */
    public Map<Office, Long> employeeCountByOffice(Collection<Employee> employees) {
        // Replace the body of this method with one that counts the number of
        // employees in each office.
        //
        // Your solution should use the functional Streams API rather than
        // directly iterating though the input and manually accumulating the
        // results in a mutable collection.
        //
        // HINT: The Collectors class can create a collector that first groups
        //       elements by an arbitrary attribute (e.g., office location)
        //       before applying a collector to each group.
        //       See https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
        // HINT: The Collectors class contains Collector implementations for
        //       calculating various arithmetic operations on the elements
        //       of a stream (such as sum, average, and count).
        //       See https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
        return null;
    }

    /**
     * Determine the newest employee (the one with the most recent start date) in each office.
     *
     * @param employees The employees in which to find the newest employee for each office.
     * @return The newest employee from {@code employees} in each office.
     */
    public Map<Office, Employee> newestEmployeeByOffice(Collection<Employee> employees) {
        // Replace the body of this method with one that finds the newest
        // employee (the one with the most recent start date) in each office.
        // If two or more employees have the same start date, any of these
        // employees may be represent the office.
        //
        // Your solution should use the functional Streams API rather than
        // directly iterating though the input and manually accumulating the
        // results in a mutable collection.
        //
        // HINT: Your solution to employeeCountByOffice will likely come in
        //       handy to group employees by location.
        // HINT: The Collectors class can create a Collector that finds the
        //       min or max element in a stream, given the right comparator.
        // HINT: Collectors.collectingAndThen can be used to apply a
        //       transformation to the result of collector.
        // HINT: Try to find a way to create a Map<Office, Optional<Employee>>
        //       first, then augment the collector to unwrap the Optional.
        //       This can be done without streaming twice.
        return null;
    }

    /**
     * Determine the percentage of employees that started strictly before a given date.
     *
     * @param employees The employees for which to calculate the percentage of employees that
     *                  started before {@code data}.
     * @param date      The date for which to find the percentage of employees in {@code employees}
     *                  that started before.
     * @return The percentage (from 0.0 to 1.0, inclusive) of employees in {@code employees} that
     * started strictly before {@code date}.
     */
    public double percentageStartedBefore(Collection<Employee> employees, LocalDate date) {
        // Replace the body of this method with one that calculates the
        // percentage (from 0.0 to 1.0) of employees that started strictly
        // before the provided date. If all employees started before the
        // provided date, 1.0 should be returned. If no employees started
        // before the provided date, 0.0 should be returned.
        //
        // Your solution should use the functional Streams API rather than
        // directly iterating though the input and manually accumulating the
        // results in a mutable object.
        //
        // HINT: Partition the stream into those employees that started before
        //       date and those that started on or after and count the size of
        //       each partition.
        return -1.0d;
    }

    /**
     * Determine the most senior employee (the one with the earliest starting data), if any, found
     * in {@code employees}.
     *
     * @param employees The employees in which to find the most senior.
     * @return The most senior employee, if any, found in {@code employees}
     */
    public Optional<Employee> mostSeniorEmployee(Collection<Employee> employees) {
        // Replace the body of this method with one that finds and returns the
        // most senior employee (the employee with the earliest start data).
        // If two or more employees have the same start date, any one of these
        // employees may be returned. If no employees are provided, an empty
        // Optional is returned.
        //
        // Your solution should use the functional Streams API rather than
        // directly iterating though the input and manually accumulating the
        // results in a mutable collection.
        //
        // HINT: Given an appropriate comparator, a stream has an operation to
        //       find its minimum or maximum element.
        return null;
    }

    /**
     * Determine the most senior employees (those with the earliest start date).
     * <p>
     * If multiple employees begin on the same date, they should all be returned.
     * </p>
     *
     * @param employees The employees for which to find the most senior ones.
     * @return The most senior employees found in {@code employees}.
     */
    public Set<Employee> mostSeniorEmployees(Collection<Employee> employees) {
        // Replace the body of this method with one that finds all of the most
        // senior employees and returns the. If multiple employees share the
        // earliest start date, they should all be returned.
        //
        // Your solution should use the functional Streams API rather than
        // directly iterating though the input and manually accumulating the
        // results in a mutable collection.
        //
        // HINT: This can be achieved without sorting the stream by first
        //       determining the earliest start date then finding the
        //       employees that started on that date.
        return null;
    }

    /**
     * Determine the most senior employees (those with the earliest start date).
     * <p>
     * If multiple employees begin on the same date, they should all be returned.
     * </p>
     *
     * @param employeeStream The employees for which to find the most senior ones.
     * @return The most senior employees found in {@code employees}.
     */
    public Set<Employee> mostSeniorEmployees(Stream<Employee> employeeStream) {
        // This BONUS question is a variation of mostSeniorEmployees, except
        // that it requires a single pass through the stream.
        //
        // Replace the body of this method with one that finds all of the most
        // senior employees and returns the. If multiple employees share the
        // earliest start date, they should all be returned.
        //
        // Your solution should use the functional Streams API in a single pass,
        // without first collecting the input into a collection (e.g., List or
        // Set).
        //
        // HINT: Define a custom Collector (or supplier, accumulator, and
        //       combiner) and use it with Stream.collect.
        // HINT: Consider storing the most senior employees found so far in
        //       the accumulator.
        return mostSeniorEmployees(employeeStream.collect(toList()));
    }
}

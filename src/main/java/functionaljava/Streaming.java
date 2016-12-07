package functionaljava;

import functionaljava.types.Employee;
import functionaljava.types.Name;
import functionaljava.types.Office;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
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
        // Practice transforming the elements of a stream and collecting the
        // transformed elements into a List.
        //
        // Replace the body of this method with one that returns a new List
        // created by squaring each element (multiplying by itself). Your
        // solution should create a Stream from the list using stream(),
        // transform each element of the stream using map(), and collect the
        // results into a List using collect() and Collectors.toList().
        //
        // Verify your solution:
        //     mvn test -Dtest=StreamingTest#testSquareIntegerList
        //
        // See the solution:
        //     tutorial/streams/transforming_ex1_sltn.md
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
        // Practice filtering the elements of a stream before being transformed
        // and collected into a Set.
        //
        // Replace the body of this method with one that returns the (unique)
        // family names of the employees that work out of a given office. Your
        // solution should should create a Stream from the Collection using
        // stream(), filtering out employees not from the provided office using
        // filter(), transforming the remaining element using map(), and
        // collecting (and de-duplicating) the results into a Set using
        // collect() and Collectors.toSet().
        //
        // Verify your solution:
        //     mvn test -Dtest=StreamingTest#testEmployeeFamilyNamesAtOffice
        //
        // See the solution:
        //     tutorial/streams/filtering_ex1_sltn.md
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
        // Practice defining a Comparator and using it to sort the elements of
        // a stream before collecting them into a List.
        //
        // Replace the body of this method with one that returns a List
        // containing the supplied employees, but that is sorted by family
        // name (earlier names appearing first). If two employees share the
        // same family name, the one with the earlier given name should appear
        // first. If two or more employees share the same given and family
        // names, the one with the earlier start date should appear first. If
        // two or more employees share the same given and family names and
        // start date, they may appear in the output in any order. All name
        // sorting is case insensitive.
        //
        // The provided Comparator<Name> can be used to compare names and as a
        // guide for creating a Comparator<Employee>.
        //
        // Verify your solution:
        //     mvn test -Dtest=StreamingTest#testSortEmployeesByName
        //
        // See the solution:
        //     tutorial/streams/sorting_ex1_sltn.md
        Comparator<Name> nameComparator =
                Comparator.comparing(Name::getFamilyName, String::compareToIgnoreCase)
                        .thenComparing(Name::getGivenName, String::compareToIgnoreCase);
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
        // Practice grouping elements by a specific property and aggregating
        // the elements that belong to each group using predefined Collectors.
        //
        // Replace the body of this method with one that returns the number of
        // employees who work out of each office. All entries in the result
        // should contain non-zero values (i.e., only offices found in the
        // employee records need to be considered).
        //
        // Collectors.groupingBy() can be used along with Collectors.counting()
        // to create a Collector to reduce the stream using Stream.collect().
        //
        // Verify your solution:
        //     mvn test -Dtest=StreamingTest#testEmployeeCountByOffice
        //
        // See the solution:
        //     tutorial/streams/grouping_ex1_sltn.md
        return null;
    }

    /**
     * Determine the newest employee (the one with the most recent start date) in each office.
     *
     * @param employees The employees in which to find the newest employee for each office.
     * @return The newest employee from {@code employees} in each office.
     */
    public Map<Office, Employee> newestEmployeeByOffice(Collection<Employee> employees) {
        // Practice grouping elements by a specific property and aggregating
        // the elements that belong to each group, using a complex Collector
        // created by chaining a Collector with a finishing function.
        //
        // Replace the body of this method with one that finds the newest
        // employee (has the most recent start date) in each office. If two or
        // more employees share the earliest start date, any of these employees
        // may represent the office. All offices in the result must have a
        // "newest" employee (i.e., only offices found in the employee records
        // need to be considered).
        //
        // The solution to the employeeCountByOffice will come in handy; we
        // will just need to replace the Collector with one that finds the
        // newest employee.
        //
        // To create this Collector we can use Collectors.maxBy(), which almost
        // does what we need. Since a stream can be empty, a Collector that
        // reduces a stream to a single element will evaluates to an Optional
        // instead of an element from the stream. However, since a group never
        // collects zero elements, the Optional can be safely unwrapped in our
        // case.
        //
        // Collectors.collectingAndThen() can be used to attach a finishing
        // function to the Collector created by Collectors.maxBy() that will
        // unwrap the Optional it generates.
        //
        // Verify your solution:
        //     mvn test -Dtest=StreamingTest#testNewestEmployeeByOffice
        //
        // See the solution:
        //     tutorial/streams/grouping_ex2_sltn.md
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
        // Practice partitioning a stream in two and aggregating the elements
        // of each partition.
        //
        // Replace the body of this method with one that returns the percentage
        // (from 0.0 to 1.0) of employees that started before (but not on) the
        // provided date. If all employees started before the provided date,
        // 1.0 should be returned. If all employees started on or after the
        // provided date, 0.0 should be returned.
        //
        // Collectors.partitioningBy() can be used along with
        // Collectors.counting() to create a Collector to reduce the stream
        // using Stream.collect().
        //
        // Verify your solution:
        //     mvn test -Dtest=StreamingTest#testPercentageStartedBefore
        //
        // See the solution:
        //     tutorial/streams/partitioning_ex1_sltn.md
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
        // Practice creating a simple Comparator and using it to find the
        // minimum element of a stream.
        //
        // Replace the body of this method with one that returns the employee
        // with the earliest start date. If there are no employee records, an
        // empty Optional should be returned. If multiple employees have the
        // same start date, the one that appears first in the input should be
        // returned.
        //
        // Comparator.comparing() can be used to create a suitable
        // Comparator<Employee> that compares employees by start date. This can
        // be used with Stream.min() to find the minimum element of a stream.
        //
        // Verify your solution:
        //     mvn test -Dtest=StreamingTest#testMostSeniorEmployee
        //
        // See the solution:
        //     tutorial/streams/partitioning_ex1_sltn.md
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
        // Learn how to work around the limitations of the built in Collectors
        // to solve a problem that seems similar to problems easily solved
        // with built-in collections.
        //
        // Replace the body of this method with one that returns all of the
        // employees that share the earliest start date. If there are no
        // employee records, an empty Set should be returned.
        //
        // Don't be fooled by the similarities with mostSeniorEmployee() that
        // required finding a single element of the stream. This calls for a
        // much different solution.
        //
        // A simple way to find all minimal elements without sorting is to make
        // a pass through the stream to find the minimal element then making a
        // second pass through the list to remove all but the minimal elements.
        //
        // Verify your solution:
        //     mvn test -Dtest=StreamingTest#testMostSeniorEmployees
        //
        // See the solution:
        //     tutorial/streams/partitioning_ex1_sltn.md
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
        // This BONUS question is a variation of
        // mostSeniorEmployees(Collection<Employee>).
        //
        // Practice creating a custom Collector for performing complex
        // reductions.
        //
        // Replace the body of this method with one that returns the employees
        // with the earliest start date in a single pass through the elements
        // of the stream. If there are no employee records, an empty Set
        // should be returned.
        //
        //  Consider implementing a custom `Collector` that stores all the
        // employees that are the most senior that have been seen so far.
        //
        // Verify your solution:
        //     mvn test -Dtest=StreamingTest#testMostSeniorEmployeesStream
        //
        // See the solution:
        //     tutorial/streams/partitioning_ex1_sltn.md
        return mostSeniorEmployees(employeeStream.collect(toList()));
    }
}

# Streams

## Exercise: Sort a Collection

In this exercise we will sort the elements of a collection.

Open
[`src/main/java/functionaljava/Streaming.java`](../../src/main/java/functionaljava/Streaming.java)
and replace the body of `sortEmployeesByName` with one that returns a `List`
containing the supplied employees, but that is sorted by family name (earlier
names appearing first). If two employees share the same family name, the one
with the earlier given name should appear first. If two or more employees share
the same given and family names, the one with the earlier start date should
appear first. If two or more employees share the same given and family names and
start date, they may appear in the output in any order. All name sorting is case
insensitive.

The provided `Comparator<Name>` can be used to compare names and as a guide for
creating a `Comparator<Employee>`.

Using the provided `nameComparator`, create a similar `Comparator<Employee>`
that compares an `Employee` by name first then by start date.

`nameComparator` has the following definition:

``` java
Comparator<Name> nameComparator =
        Comparator.comparing(Name::getFamilyName, String::compareToIgnoreCase)
                .thenComparing(Name::getGivenName, String::compareToIgnoreCase);
```

To test your solution, run the following command:

``` bash
mvn test -Dtest=StreamingTest#testSortEmployeesByName
```

[Solution](sorting_ex1_sltn.md) | [Exercises FAQ](../exercises.md)

---

[Continue](grouping.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

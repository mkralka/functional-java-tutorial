# Streams

## Overview

Java 8 introduced *Streams*, a rich API for processing data declaratively. This
is one of the more interesting additions to Java 8, requiring a significant
mental shift in how one approaching data processing.

For example, how would one normalize a list of
[`String`](http://docs.oracle.com/javase/8/docs/api/java/lang/String.html)s
by replacing all consecutive non-alphanumeric characters with a single
underscore (`_`), dropping leading/trailing non-alphanumeric characters, and
remove all empty strings from the list? Code written in the imperative
(traditional Java) style might look similar to the following:

``` java
void normalizeList(List<String> input) {
    for (ListIterator<String> iter = input.listIterator(); iter.hasNext();) {
        String value = iter.next();
        String normalizedValue = value.replaceAll("[^a-zA-Z0-9]+", "_")
                .replaceAll("(^_+|_+$)", "")
                .toLowerCase();
        if (normalizedValue.isEmpty()) {
            iter.remove();
        } else if (!normalizedValue.equals(value)) {
            iter.set(normalizedValue);
        }
    }
}
```

whereas code written using streams might look similar to the following:

``` java
List<String> normalizeList(List<String> input) {
    return input.stream()
            .map(s -> s.replaceAll("[^a-zA-Z0-9]+", "_").replaceAll("(^_+|_+$)", "").toLowerCase())
            .filter(((Predicate<String>) String::isEmpty).negate())
            .collect(Collectors.toList());
}
```

Even without understanding the details of the `Stream` operations, the streaming
variant clearly defines describes the processing being performed and is not
cluttered with details related to collection creation or list traversal. One can
easily see that the elements of `input` are:

1. Transformed by some expression.
2. Filtered to exclude those matching (or possibly not matching?) some
   predicate.
3. Collected into a list.

Let's dig in!

---

[Continue](pipeline.md)

[Skip Back](../optional/start.md) | [Up](../start.md) | Skip Forward

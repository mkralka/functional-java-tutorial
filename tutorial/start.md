# Functional Programming in Java 8: A Tutorial

## Introduction

Welcome to *Functional Programming in Java 8: A Tutorial*. The goal of this
tutorial is to provide a hands-on introduction to some of the new features
introduced in Java 8 that facilitate programming in the
[functional style](https://en.wikipedia.org/wiki/Functional_programming). Java
is, and always has been, an
[imperative language](https://en.wikipedia.org/wiki/Imperative_programming),
making some of these new features a little harder to wrap one's head around than
other significant features introduced in previous major versions of the language
(such as
[generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html),
[autoboxing](https://docs.oracle.com/javase/tutorial/java/data/autoboxing.html),
and [enums](://docs.oracle.com/javase/tutorial/java/javaOO/enum.html) in
[Java 5](http://docs.oracle.com/javase/1.5.0/docs/relnotes/features.html) and
[generic type inference](http://docs.oracle.com/javase/tutorial/java/generics/genTypeInference.html)
and
[try-with-resources](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)
in
[Java 7](http://www.oracle.com/technetwork/java/javase/jdk7-relnotes-418459.html)).
Thinking functionally requires a mental shift away from *How do I change the
state?* to *How do express the output in terms of the input?*

For example, given a list of arbitrary
[`String`](http://docs.oracle.com/javase/8/docs/api/java/lang/String.html)s,
how would one replace all consecutive non-alphanumeric characters with a single
underscore (`_`), dropping leading/trailing non-alphanumeric characters, and
remove all empty strings from the list. Code written in the imperative
(traditional Java) style might look similar to the following:

``` java
void normalizeList(List<String> input) {
    for (ListIterator<String> iter = input.listIterator(); iter.hasNext();) {
        String value = iter.next();
        String normalizedValue = value.replaceAll("[^a-zA-Z0-9]+", "_");
        normalizedValue = normalizedValue.replaceAll("(^_+|_+$)", "");
        normalizedValue = normalizedValue.toLowerCase();
        if (normalizedValue.isEmpty()) {
            iter.remove();
        } else if (!normalizedValue.equals(value)) {
            iter.set(normalizedValue);
        }
    }
}
```

whereas code written in the functional (Java 8) style might look similar to the
following:

``` java
List<String> normalizeList(List<String> input) {
    return input.stream()
            .map(s -> s.replaceAll("[^a-zA-Z0-9]+", "_").replaceAll("(^_+|_+$)", "").toLowerCase())
            .filter(((Predicate<String>) String::isEmpty).negate())
            .collect(Collectors.toList());
}
```

Don't worry about the details of the functional version yet; by the end of this
tutorial you'll be familiar with the new syntax and APIs.

## Format

The tutorial is split into several sections, with each section introducing a new
concept. Although each section is self-contained (and can be each be completed
independently, in any order), most readers will find it easier to complete the
sections in order since each section builds upon those that preceded it.

Each section in the tutorial has a corresponding source file (containing a
skeleton with several unimplemented methods) and test file (containing unit
tests that will only pass when the unimplemented methods are completed). As you
progress through the tutorial, you will be instructed to implement a single
method and run the unit tests associated with the method being implemented. Unit
tests can be run directly from your IDE or the command line (although
instructions will only be given for the command line).

There is nothing special about the exercises that require them to be implemented
in the functional style; but doing so should be straightforward. If you find
yourself implementing in the imperative style, there is probably some available
method that you are missing; read the documentation linked to in the section.

## Conventions

## `Function` vs. function

When capitalized and in `fixed width` font, `Function` refers to the functional
interface
[`Function`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)
defined by the Java 8 SDK.  When lowercase or not in `fixed width` font,
*function* refers to a function in the mathematical/functional programming
sense: a variable to produces a zero or more values based only on the supplied
parameters. `Function` describes a type of function, but so does
[`BiFunction`](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html)
and
[`UnaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/UnaryOperator.html).

## Sections

1. [Functional Interfaces](functional/start.md)
2. [Lambda Expressions](lambda_expressions/start.md)
3. [Method References](method_references/start.md)
4. [Optional](optional/start.md)
5. [Streams](streams/start.md)

---

[Continue](functional/start.md)

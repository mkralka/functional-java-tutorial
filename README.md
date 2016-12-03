# Function Programming in Java 8: A Tutorial

On 18 March 2014, Oracle released Java 8 into the wild, introducing Java
developers to
[many new features](http://www.oracle.com/technetwork/java/javase/8-whats-new-2157071.html).
Of particular interest are those features added that facilitate programming in
the [functional style](https://en.wikipedia.org/wiki/Functional_programming).
Being an
[imperative language](https://en.wikipedia.org/wiki/Imperative_programming),
effective use of these new constructs can feel foreign, even to the most
seasoned Java programmer.

The aim of this tutorial is to provide a *hands-on* introduction to these new
features in a way that should feel familiar to to even the casual Java
programmer.

## Audience
The intended audience of this tutorial are Java developers, from casual to
experienced, interested in learning about the features available in Java 8 that
facilitate programming in the functional style. There is no expectation for the
reader to be familiar with anything beyond basic Java syntax and standard Java
APIs (the reader does not need to be familiar with any external libraries).

For readers that are new to Java and wonder if this tutorial is right for them,
links to important API documentation are included throughout the tutorial.

## Requirements

1. [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
   (tested with update 111 for macOS).
2. [Apache Maven](https://maven.apache.org/) (tested with version 3.2.5)
3. [git](https://git-scm.com/) (tested with version 2.9.3)
4. POSIX shell (e.g., bash) in a UNIX-like environment (e.g., Linux, macOS, BSD,
   etc.) or confidence in converting commands into your preferred environment.
5. A text editor or Java IDE, such as
   [Intellij CE](https://www.jetbrains.com/idea/) (recommended) or
   [Eclipse](https://eclipse.org/).
6. (Optional) [Markdown support](https://plugins.jetbrains.com/plugin/7793) for
   Intellij.

### A note on using an IDE

All of the exercises require writing very short snippets of Java that can easily
be completed with an ordinary text editor. Although an IDE is probably overkill
for this tutorial, the files are organized to be *IDE-friendly*. Being new to
Java 8, you may benefit from the features of an IDE (such as auto-completion and
inline-debugging), allowing you to focus on code structure.

If you prefer using an IDE but don't know which to choose, Consider trying the
free [Intellij IDEA Community Edition](https://www.jetbrains.com/idea/) from
[Jetbrains](https://www.jetbrains.com/).

## Getting Started

### Step 1: Clone the Tutorial repo

The entire tutorial, including the code skeletons to be filled in, can all be
found in this [github](https://github.com/)
[repo](https://github.com/mkralka/functional-java-tutorial).

To clone the repo, open up a terminal and navigate to the directory under which
you would like to store the tutorial:

``` bash
cd ~/dev
```

then clone the repo using `git` command.
``` bash
git clone https://github.com/mkralka/functional-java-tutorial
```

### Step 2: Pre-Download tutorial Dependencies (Optional)

In order to speed up the first compilation, you may optionally download and
cache the tutorial's dependencies. This will allow you to work offline, with no
Internet connection.

From the command line, run the following command:

``` bash
mvn dependency:resolve
```

### Step 3: Jump in, feet first!

The tutorial begins [here](tutorial/start.md).

#### A note on reading the tutorial offline.

If you would prefer reading the tutorial offline, you have several options:

1. Use your favorite editor/text reader (e.g., vim, less, etc) and open
   `tutorial/start.md`.
2. Use your favorite web browser with support for markdown preview (e.g.,
   [Chrome](https://www.google.com/chrome) with the
   [Markdown Preview Plus](https://chrome.google.com/webstore/detail/markdown-preview-plus/febilkbfcbhebfnokafefeacimjdckgl)
   extension) and open `tutorial/start.md`.
3. Use your IDE with support for markdown preview (e.g.,
   [Intellij IDEA](https://www.jetbrains.com/idea/) with the
   [Markdown support](https://plugins.jetbrains.com/plugin/7793) plugin) and
   open `tutorial/start.md`.

*Although the entire tutorial is written in markdown (making it possible to
following using your favorite plain-text reader) you may find it more satisfying
to use a markdown previewer that supports hyperlinks.*


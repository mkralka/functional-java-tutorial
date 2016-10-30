package functionaljava;

import java.util.Collection;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class MethodReferences {
    public static final MethodReferences INSTANCE = new MethodReferences();

    private MethodReferences() {
    }

    /**
     * Create a binary operator that calculates the length of hypotenuse of a right-angled triangle
     * from the length of its legs.
     *
     * @return A binary operator that calculates the length of hypotenuse of a right-angled triangle
     * from the length of its legs.
     */
    public DoubleBinaryOperator hypotenuse() {
        // Replace the body of this method with one that creates a reference to
        // a method that calculates the hypotenuse of a right-angled triangle.
        //
        // You should define this as a static method reference to Math.hypot
        // (https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#hypot-double-double-)
        // and not a lambda expression.
        return null;
    }

    /**
     * Create a function that creates a {@link Map} from a structured {@link String}.
     * <p>
     * Entries in the provided {@link String} are separated by {@code entrySeparator}. Within an
     * entry, the key and value are separated by {@code keyValueSeparator}. Whitespace around
     * (both entry and key-value) separators and the beginning/end of the input are ignored. Empty
     * entries (i.e., those that contain no non-whitespace characters) are also omitted from the
     * result.
     * </p>
     * <p>
     * For example: {@code ",, a = 1 ,b=2,c = 3,  ,"} contains three entries, if parsed with
     * {@code ','} as a {@code entrySeparator} and {@code '='} as a {@code keyValueSeparator} with
     * keys {@code "a"}, {@code "b"}, and {@code "c"} and values {@code "1"}, {@code "2"}, and
     * {@code "3"} (respectively).
     * </p>
     *
     * @return A function that creates a {@link Map} from a structured {@link String}.
     */
    public Function<String, Map<String, String>> mapSplitter(
            char entrySeparator,
            char keyValueSeparator) {
        // Replace the body of this method with one that creates a reference to
        // a method that parses a string into a Map<String, String>>. Entries
        // should be separated by entrySeparator. The key and value within an
        // entry should be separated by keyValueSeparator. Whitespace around
        // separators should be ignored as should empty (whitespace only)
        // entries.
        //
        // Use Guava's Splitter
        // (https://google.github.io/guava/releases/snapshot/api/docs/com/google/common/base/Splitter.html)
        // to create a MapSplitter
        // (https://google.github.io/guava/releases/snapshot/api/docs/com/google/common/base/Splitter.MapSplitter.html)
        // for all the the heavy lifting.
        //
        // Your answer should be defined as a bound method reference to
        // MapSplitter.split
        // (https://google.github.io/guava/releases/snapshot/api/docs/com/google/common/base/Splitter.MapSplitter.html#split-java.lang.CharSequence-)
        // and not a lambda expression.
        return null;
    }

    /**
     * Create a function that calculates the length of a {@link Collection}.
     *
     * @return A function that calculates the length of a {@link Collection}.
     */
    public <T> ToIntFunction<Collection<T>> collectionSizer() {
        // Replace the body of this method with one that creates a reference to
        // an unbound method that calculates the size of (number of elements in)
        // a collection.
        //
        // Your answer should be defined as an unbound method reference.
        //
        // HINT: Collection (https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html)
        //       already has an instance method (Collection.size) that already
        //       calculates the size of a collection.
        return null;
    }

    /**
     * Create a function that creates a new string from an array of bytes representing the
     * characters of the string.
     *
     * @return A function that creates a new string from an array of bytes representing the
     * characters of the string.
     */
    public Function<byte[], String> stringCreator() {
        // Replace the body of this method with one that returns a reference to
        // a constructor that creates an instance of a String from the bytes
        // representing its characters.
        //
        // Your answer should be defined as a constructor method reference.
        //
        // HINT: It's safe to assume that the input is specified in your
        //       platform's default charset.
        return null;
    }
}

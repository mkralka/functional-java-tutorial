package functionaljava;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.LongUnaryOperator;

public final class LambdaExpressions {
    public static final LambdaExpressions INSTANCE = new LambdaExpressions();

    private LambdaExpressions() {
    }

    /**
     * Create a unary operator that squares its input.
     *
     * @return A unary operator that squares its input.
     */
    public LongUnaryOperator squarer() {
        // Replace the body of this method with one that returns a unary
        // operator that squares its input.
        //
        // You should define this as a lambda expression and not an anonymous
        // class that implements LongUnaryOperator.
        return null;
    }

    /**
     * Create a binary operator that calculates the area of a triangle from the length of its
     * base and height.
     *
     * @return A binary operator that calculates the area of a triangle from the length of its
     * base and height.
     */
    public DoubleBinaryOperator triangleArea() {
        // Replace the body of this method with one that returns a binary
        // operator that calculates the area of a triangle from the lengths of
        // its base and height (in that order).
        //
        // You should define this as a lambda expression and not an anonymous
        // class that implements DoubleBinaryOperator.
        //
        // HINT: https://en.wikipedia.org/wiki/Triangle#Computing_the_area_of_a_triangle
        return null;
    }

    /**
     * Create a unary operator that calculates the nth Fibonacci number given input n.
     * <p>
     * The returned unary operator will, for example, return the 8th Fibonacci number (i.e. 21)
     * for the input 8. Inputs for which no Fibonacci number is defined (e.g., negative numbers)
     * will evaluate to zero.
     *
     * @return A unary operator that calculates the nth Fibonacci number given input n.
     */
    public LongUnaryOperator fibonacci() {
        // Replace the body of this method with one that returns a unary
        // operator that calculates the nth Fibonacci number. Recall that the
        // Fibonacci sequence is defined as: f(0) = 0,
        // f(1) = 1, and f(n) = f(n - 1) + f(n - 2).
        //
        // It may be tempting to write this recursively. The naive recursive
        // solution is prohibitively expensive (computationally). You will need
        // to provide an iterative (or tail recursive) solution.
        //
        // You should define this as a lambda expression and not an anonymous
        // class that implements LongUnaryOperator.
        //
        // HINT: First 21 Fibonacci numbers:
        //       https://en.wikipedia.org/wiki/Fibonacci_number#List_of_Fibonacci_numbers
        // HINT: Implementations in various languages:
        //       https://en.wikibooks.org/wiki/Algorithm_Implementation/Mathematics/Fibonacci_Number_Program#Java
        return null;
    }

    /**
     * Create a function that repeats the first parameter by the number specified by the second
     * parameter.
     *
     * @return A function that repeats the first parameter by the number specified by the second
     * parameter.
     */
    public BiFunction<String, Integer, String> stringMultiplier() {
        // Replace the body of this method with one that returns a function (of
        // arity 2) that returns a the first parameter (a String) repeated as
        // many times as specified by the second parameter (an Integer).
        //
        // You should define this as a lambda expression and not an anonymous
        // class that implements BiFunction.
        //
        // HINT: java.lang.StringBuilder provides an efficient way to
        //       dynamically build Strings without creating intermediate
        //       objects.
        return null;
    }
}

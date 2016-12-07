package functionaljava;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
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
        // Practice writing a single-statement lambda expression with a single,
        // untyped parameter.
        //
        // Replace the body of this method with one that returns a lambda
        // expression that accepts a single parameter, x, and returns the square
        // of x (i.e., x * x).
        //
        // Verify your solution:
        //     mvn test -Dtest=LambdaExpressionsTest#testSquarer
        //
        // See the solution:
        //     tutorial/lambda_expressions/ex001_sol.md
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
        // Practice writing a single-statement lambda expression with multiple
        // untyped parameters.
        //
        // Replace the body of this method with one that returns a lambda
        // expression taking two parameters that describe the dimensions of
        // a triangle, base and height, and returns the area of said triangle.
        //
        // Recall that the area of a triangle can be calculated using
        //
        //     area = 1/2 * base * height.
        //
        // Verify your solution:
        //     mvn test -Dtest=LambdaExpressionsTest#testSquarer
        //
        // See the solution:
        //     tutorial/lambda_expressions/ex002_sol.md
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
        // Practice writing a block-statement lambda expression with a single,
        // untyped parameter.
        //
        // Replace the body of this method with one that returns a lambda
        // expression taking a single parameter, n, and calculates the fib(n)
        // (the nth number in the Fibonacci sequence).
        //
        // While it might be tempting to write this recursively, the naive
        // recursive solution is stupendously inefficient. Instead, incorporate
        // the following iterative solution into your lambda expression:
        //
        //     long fib(long n) {
        //         long a = 0, b = 1;
        //         for (long i = 0; i < n; ++i) {
        //             b = b + a;
        //             a = b - a;
        //         }
        //         return a;
        //     }
        //
        // Verify your solution:
        //     mvn test -Dtest=LambdaExpressionsTest#testFibonacci
        //
        // See the solution:
        //     tutorial/lambda_expressions/ex003_sol.md
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
        // Practice writing a block-statement lambda expression with multiple
        // untyped parameters.
        //
        // Replace the body of this method with one that returns a lambda
        // expression taking two parameters, str and count, and calculates a
        // string that consists of str repeated count times.
        //
        // Verify your solution:
        //     mvn test -Dtest=LambdaExpressionsTest#testStringMultiplier
        //
        // See the solution:
        //     tutorial/lambda_expressions/ex004_sol.md
        return null;
    }
}

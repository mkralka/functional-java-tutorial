package functionaljava;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.LongUnaryOperator;

import static org.testng.Assert.assertEquals;

public final class LambdaExpressionsTest {
    private static final LambdaExpressions lambdaExpressions = LambdaExpressions.INSTANCE;

    @Test(dataProvider = "squarer")
    public void testSquarer(long n, long expected) {
        LongUnaryOperator squarer = lambdaExpressions.squarer();

        long actual = squarer.applyAsLong(n);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "squarer")
    public static Object[][] dataSquarer() {
        return new Object[][]{
                new Object[]{1L, 1L},
                new Object[]{2L, 4L},
                new Object[]{3L, 9L},
                new Object[]{4L, 16L},
                new Object[]{5L, 25L},
                new Object[]{100L, 10_000L},
                new Object[]{2_887L, 8_334_769L},
                new Object[]{35_531L, 1_262_451_961L},
                new Object[]{441_937L, 195_308_311_969L},
                new Object[]{5_243_317L, 27_492_373_162_489L},
                new Object[]{64_881_881L, 4_209_658_482_098_161L},
                new Object[]{809_882_653L, 655_909_911_630_318_409L},
                new Object[]{999_999_937L, 999_999_874_000_003_969L},
        };
    }

    @Test(dataProvider = "triangleArea")
    public void testTriangleArea(double a, double b, double c) {
        DoubleBinaryOperator triangleArea = lambdaExpressions.triangleArea();

        double actual = triangleArea.applyAsDouble(a, b);
        assertEquals(actual, c, 0.001d);
    }

    @DataProvider(name = "triangleArea")
    public static Object[][] dataTriangleArea() {
        return new Object[][]{
                new Object[]{3.0d, 4.0d, 6.0d},
                new Object[]{1.0d, 1.0d, 0.5d},
                new Object[]{7.5d, 9.0d, 33.75d},
        };
    }

    @Test(dataProvider = "fibonacci")
    public void testFibonacci(long n, long expected) {
        LongUnaryOperator fibonacci = lambdaExpressions.fibonacci();

        long actual = fibonacci.applyAsLong(n);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "fibonacci")
    public static Object[][] dataFibonacci() {
        return new Object[][]{
                new Object[]{-1L, 0L},
                new Object[]{0L, 0L},
                new Object[]{1L, 1L},
                new Object[]{2L, 1L},
                new Object[]{3L, 2L},
                new Object[]{4L, 3L},
                new Object[]{5L, 5L},
                new Object[]{6L, 8L},
                new Object[]{7L, 13L},
                new Object[]{8L, 21L},
                new Object[]{9L, 34L},
                new Object[]{10L, 55L},
                new Object[]{20L, 6_765L},
                new Object[]{30L, 832_040L},
                new Object[]{40L, 102_334_155L},
                new Object[]{50L, 12_586_269_025L},
                new Object[]{60L, 1_548_008_755_920L},
                new Object[]{70L, 190_392_490_709_135L},
                new Object[]{80L, 23_416_728_348_467_685L},
                new Object[]{90L, 2_880_067_194_370_816_120L},
                new Object[]{92L, 7_540_113_804_746_346_429L},
        };
    }

    @Test(dataProvider= "stringMultiplier")
    public void testStringMultiplier(String x, int y, String expected) {
        BiFunction<String, Integer, String> stringMultiplier = lambdaExpressions.stringMultiplier();

        String actual = stringMultiplier.apply(x, y);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "stringMultiplier")
    public static Object[][] dataStringMultiplier() {
        return new Object[][]{
                new Object[]{"a", 0, ""},
                new Object[]{"b", -1, ""},
                new Object[]{"foo", 1, "foo"},
                new Object[]{"foo", 2, "foofoo"},
                new Object[]{"foo", 3, "foofoofoo"},
                new Object[]{"bar", 10, "barbarbarbarbarbarbarbarbarbar"},
                new Object[]{"aba", 17, "abaabaabaabaabaabaabaabaabaabaabaabaabaabaabaabaaba"},
                new Object[]{"foobar", 5, "foobarfoobarfoobarfoobarfoobar"},
        };
    }
}

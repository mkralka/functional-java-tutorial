package functionaljava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import static org.testng.Assert.assertEquals;

public class MethodReferencesTest {
    private static final MethodReferences methodReferences = MethodReferences.INSTANCE;

    @Test(dataProvider = "hypotenuse")
    public void testHypotenuse(double leg1, double leg2, double expected) {
        DoubleBinaryOperator hypotenuse = methodReferences.hypotenuse();

        double actual = hypotenuse.applyAsDouble(leg1, leg2);
        assertEquals(actual, expected, 0.0001);
    }

    @DataProvider(name = "hypotenuse")
    public static Object[][] dataHypotenuse() {
        return new Object[][]{
                new Object[]{3d, 4d, 5d},
                new Object[]{5d, 12d, 13d},
                new Object[]{1293d, 475d, 1377.48829396d},
                new Object[]{1d, 1d, 1.41421356d},
                new Object[]{1d, 1.73205080d, 2d},
        };
    }

    @Test(dataProvider = "mapSplitter")
    public void testMapSplitter(
            char entrySeparator,
            char keyValueSeparator,
            String input,
            Map<String, String> expected) {
        Function<String, Map<String, String>> mapSplitter =
                methodReferences.mapSplitter(entrySeparator, keyValueSeparator);

        Map<String, String> actual = mapSplitter.apply(input);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "mapSplitter")
    public static Object[][] dataMapSplitter() {
        return new Object[][]{
                new Object[]{
                        ';',
                        '=',
                        "i=1;ii=2;iii=3;iv=4;v=5",
                        ImmutableMap.of("i", "1", "ii", "2", "iii", "3", "iv", "4", "v", "5"),
                },
                new Object[]{
                        '|',
                        ':',
                        "a:alpha|b:bravo|c:charlie|d:delta",
                        ImmutableMap.of("a", "alpha", "b", "bravo", "c", "charlie", "d", "delta")
                },
                new Object[]{
                        ',',
                        '=',
                        ",,, a = 1, b=   2  ,\t \t c \t \t =\t \t3,\t \t \t,\t \t,d=4",
                        ImmutableMap.of("a", "1", "b", "2", "c", "3", "d", "4"),
                },
        };
    }

    @Test(dataProvider = "collectionSizer")
    public <T> void testCollectionSizer(Collection<T> collection, int expected) {
        ToIntFunction<Collection<T>> collectionSizer = methodReferences.collectionSizer();

        int actual = collectionSizer.applyAsInt(collection);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "collectionSizer")
    public static Object[][] dataCollectionSizer() {
        return new Object[][]{
                new Object[]{ImmutableList.of(), 0},
                new Object[]{ImmutableList.of(1, 2, 3, 4), 4},
                new Object[]{ImmutableSet.of(), 0},
                new Object[]{ImmutableSet.of("A", "B", "C", "D", "E", "F"), 6},
                new Object[]{ImmutableMap.of().entrySet(), 0},
                new Object[]{ImmutableMap.of("A", 1, "B", 2, "C", 3).entrySet(), 3},
        };
    }

    @Test(dataProvider = "stringCreator")
    public void testStringCreator(String input) {
        Function<byte[], String> stringCreator = methodReferences.stringCreator();

        String actual = stringCreator.apply(input.getBytes());
        assertEquals(actual, input);
    }

    @DataProvider(name = "stringCreator")
    public static Object[][] dataStringCreator() {
        return new Object[][]{
                new Object[]{"test string"},
                new Object[]{"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed ..."},
                new Object[]{"... do eiusmod tempor incididunt ut labore et dolore magna aliqua."},
                new Object[]{"functional programming is fun!"},
        };
    }
}

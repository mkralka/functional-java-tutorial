package functionaljava;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Tests that will always pass to verify the setup of this tutorial.
 */
public class SetupTest {

    @Test
    public void testSetup() {
    }

    @Test(dataProvider = "testSetup")
    public void testSetup(String string) {
    }

    @DataProvider(name = "testSetup")
    public static Object[][] dataSetup() {
        return new Object[][]{
                new Object[]{"A"},
                new Object[]{"B"},
                new Object[]{"C"},
        };
    }
}

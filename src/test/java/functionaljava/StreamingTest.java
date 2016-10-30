package functionaljava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import functionaljava.types.Employee;
import functionaljava.types.Name;
import functionaljava.types.Office;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MARCH;
import static java.time.Month.MAY;
import static java.time.Month.NOVEMBER;
import static java.time.Month.SEPTEMBER;
import static org.testng.Assert.assertEquals;

public final class StreamingTest {
    private static final Streaming streaming = Streaming.INSTANCE;
    private static final Office BEL_AIR = Office.of("Bel-Air", "CA");
    private static final Office CAPITAL_CITY = Office.of("Capital City", "IL");
    private static final Office COLUMBUS = Office.of("Columbus", "OH");
    private static final Office DETROIT = Office.of("Detroit", "MI");
    private static final Office HILLVALLEY = Office.of("Hill Valley", "CA");
    private static final Office LONDON_UK = Office.of("London", "UK");
    private static final Office MIAMI = Office.of("MIAMI", "FL");
    private static final Office MISSION_CITY = Office.of("Mission City", "MN");
    private static final Office NEW_YORK = Office.of("New York", "NY");
    private static final Office SALEM_MA = Office.of("Salem", "MA");
    private static final Office SALEM_OR = Office.of("Salem", "OR");
    private static final Office SAN_FRANCISCO = Office.of("San Francisco", "CA");
    private static final Office SEATTLE = Office.of("Seattle", "CA");
    private static final Office SPRINGFIELD_AK = Office.of("Springfield", "AK");
    private static final Office SPRINGFIELD_CA = Office.of("Springfield", "CA");
    private static final Office SPRINGFIELD_IL = Office.of("Springfield", "IL");
    private static final Office SPRINGFIELD_OR = Office.of("Springfield", "OR");
    private static final Office STALLIONS_GATE = Office.of("Stallion's Gate", "NM");

    private static final Employee THOMAS_ANDERSON = Employee.builder()
            .withName(Name.of("Anderson", "Thomas", "Neo"))
            .withStartDate(LocalDate.of(1971, SEPTEMBER, 13))
            .withOfficeLocation(CAPITAL_CITY)
            .build();
    private static final Employee BLAINE_ANDERSON = Employee.builder()
            .withName(Name.of("Anderson", "Blaine"))
            .withStartDate(LocalDate.of(1985, APRIL, 5))
            .withOfficeLocation(CAPITAL_CITY)
            .build();
    private static final Employee SAM_BECKETT = Employee.builder()
            .withName(Name.of("Beckett", "Samuel"))
            .withStartDate(LocalDate.of(1953, AUGUST, 8))
            .withOfficeLocation(STALLIONS_GATE)
            .build();
    private static final Employee AXEL_FOLEY = Employee.builder()
            .withName(Name.of("Foley", "Axel"))
            .withStartDate(LocalDate.of(1959, AUGUST, 25))
            .withOfficeLocation(DETROIT)
            .build();
    private static final Employee MANDY_COOPER = Employee.builder()
            .withName(Name.of("Cooper", "Mandy"))
            .withStartDate(LocalDate.of(1984, JULY, 19))
            .withOfficeLocation(SPRINGFIELD_AK)
            .build();
    private static final Employee EMMA_COX = Employee.builder()
            .withName(Name.of("Cox", "Emma"))
            .withStartDate(LocalDate.of(1997, APRIL, 14))
            .withOfficeLocation(DETROIT)
            .build();
    private static final Employee MARTY_MCFLY = Employee.builder()
            .withName(Name.of("McFly", "Martin", "Calvin"))
            .withStartDate(LocalDate.of(1968, JUNE, 12))
            .withOfficeLocation(HILLVALLEY)
            .build();
    private static final Employee MACGYVER = Employee.builder()
            .withName(Name.of("MacGyver", "Angus"))
            .withStartDate(LocalDate.of(1951, MARCH, 23))
            .withOfficeLocation(MISSION_CITY)
            .build();
    private static final Employee ISAAC_MEYER = Employee.builder()
            .withName(Name.of("Meyer", "Isaac"))
            .withStartDate(LocalDate.of(1976, FEBRUARY, 29))
            .withOfficeLocation(SAN_FRANCISCO)
            .build();
    private static final Employee AVA_PETERSON = Employee.builder()
            .withName(Name.of("Peterson", "Ava"))
            .withStartDate(LocalDate.of(1969, DECEMBER, 31))
            .withOfficeLocation(SPRINGFIELD_CA)
            .build();
    private static final Employee LIAM_RICHARDSON = Employee.builder()
            .withName(Name.of("Richardson", "Liam"))
            .withStartDate(LocalDate.of(1989, DECEMBER, 27))
            .withOfficeLocation(SPRINGFIELD_AK)
            .build();
    private static final Employee TROY_RICHARDSON = Employee.builder()
            .withName(Name.of("Richardson", "Troy"))
            .withStartDate(LocalDate.of(1972, AUGUST, 8))
            .withOfficeLocation(SPRINGFIELD_AK)
            .build();
    private static final Employee LUCAS_SIMMONS = Employee.builder()
            .withName(Name.of("Simmons", "Lucas"))
            .withStartDate(LocalDate.of(1988, JANUARY, 28))
            .withOfficeLocation(SALEM_MA)
            .build();
    private static final Employee OLIVER_SMITH = Employee.builder()
            .withName(Name.of("Smith", "Oliver"))
            .withStartDate(LocalDate.of(1981, MARCH, 15))
            .withOfficeLocation(SPRINGFIELD_AK)
            .build();
    private static final Employee WILL_SMITH = Employee.builder()
            .withName(Name.of("Smith", "William", "Fresh Prince"))
            .withStartDate(LocalDate.of(1973, JULY, 3))
            .withOfficeLocation(BEL_AIR)
            .build();
    private static final Employee WINSTON_SMITH = Employee.builder()
            .withName(Name.of("Smith", "Winston"))
            .withStartDate(LocalDate.of(1944, MAY, 18))
            .withOfficeLocation(LONDON_UK)
            .build();
    private static final Employee NATHAN_VASQUEZ = Employee.builder()
            .withName(Name.of("Vasquez", "Nathan"))
            .withStartDate(LocalDate.of(1986, NOVEMBER, 17))
            .withOfficeLocation(SAN_FRANCISCO)
            .build();
    private static final Employee EVELYN_WATKINS = Employee.builder()
            .withName(Name.of("Watkins", "Evelyn"))
            .withStartDate(LocalDate.of(1982, SEPTEMBER, 9))
            .withOfficeLocation(MIAMI)
            .build();
    private static final Employee ADDISON_WESLEY = Employee.builder()
            .withName(Name.of("Wesley", "Addison"))
            .withStartDate(LocalDate.of(1981, MAY, 4))
            .withOfficeLocation(NEW_YORK)
            .build();
    private static final Employee CHLOE_WHEELER = Employee.builder()
            .withName(Name.of("Wheeler", "Chloe"))
            .withStartDate(LocalDate.of(1965, JUNE, 1))
            .withOfficeLocation(SAN_FRANCISCO)
            .build();


    @Test(dataProvider = "squareIntegerList")
    public void testSquareIntegerList(List<Integer> input, List<String> expected) {
        List<Integer> actual = streaming.squareIntegerList(input);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "squareIntegerList")
    public static Object[][] dataSquareIntegerList() {
        return new Object[][]{
                new Object[]{
                        ImmutableList.of(), ImmutableList.of()
                },
                new Object[]{
                        ImmutableList.of(1, 2, 3, 4, 5, 6, 7),
                        ImmutableList.of(1, 4, 9, 16, 25, 36, 49),
                },
                new Object[]{
                        ImmutableList.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29),
                        ImmutableList.of(4, 9, 25, 49, 121, 169, 289, 361, 529, 841),
                },
                new Object[]{
                        ImmutableList.of(-17, -4091, -30_916),
                        ImmutableList.of(289, 16_736_281, 955_799_056),
                },
        };
    }

    @Test(dataProvider = "employeeFamilyNamesAtOffice")
    public void testEmployeeFamilyNamesAtOffice(
            Collection<Employee> employees, Office office, Set<String> expected) {
        Set<String> actual = streaming.employeeFamilyNamesAtOffice(employees, office);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "employeeFamilyNamesAtOffice")
    public static Object[][] dataEmployeeFamilyNamesAtOffice() {
        List<Employee> employees = ImmutableList.of(
                OLIVER_SMITH,
                MANDY_COOPER,
                LIAM_RICHARDSON,
                AVA_PETERSON,
                TROY_RICHARDSON,
                EMMA_COX,
                LUCAS_SIMMONS,
                EVELYN_WATKINS,
                NATHAN_VASQUEZ,
                CHLOE_WHEELER,
                ISAAC_MEYER,
                ADDISON_WESLEY);

        return new Object[][]{
                new Object[]{employees, SPRINGFIELD_AK, ImmutableSet.of("Smith", "Cooper", "Richardson")},
                new Object[]{employees, SAN_FRANCISCO, ImmutableSet.of("Vasquez", "Wheeler", "Meyer")},
                new Object[]{employees, NEW_YORK, ImmutableSet.of("Wesley")},
        };
    }

    @Test(dataProvider = "sortEmployeesByName")
    public void testSortEmployeesByName(Collection<Employee> employees, Collection<Employee> expected) {
        Collection<Employee> actual = streaming.sortEmployeesByName(employees);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "sortEmployeesByName")
    public static Object[][] dataSortEmployeesByName() {
        // Another WILL_SMITH, but started a day later.
        Employee willSmith2 = Employee.builder()
                .withName(WILL_SMITH.getName())
                .withStartDate(WILL_SMITH.getStartDate().plusDays(1))
                .withOfficeLocation(SEATTLE)
                .build();

        return new Object[][]{
                new Object[]{
                        ImmutableList.of(
                                MARTY_MCFLY,
                                THOMAS_ANDERSON,
                                BLAINE_ANDERSON,
                                SAM_BECKETT),
                        ImmutableList.of(
                                BLAINE_ANDERSON,
                                THOMAS_ANDERSON,
                                SAM_BECKETT,
                                MARTY_MCFLY),
                },
                new Object[]{
                        ImmutableList.of(
                                willSmith2,
                                MARTY_MCFLY,
                                MACGYVER,
                                WILL_SMITH,
                                WINSTON_SMITH,
                                AXEL_FOLEY),
                        ImmutableList.of(
                                AXEL_FOLEY,
                                MACGYVER,
                                MARTY_MCFLY,
                                WILL_SMITH,
                                willSmith2,
                                WINSTON_SMITH),
                },
        };
    }

    @Test(dataProvider = "employeeCountByOffice")
    public void testEmployeeCountByOffice(
            Collection<Employee> employees,
            Map<Office, Long> expected) {
        Map<Office, Long> actual = streaming.employeeCountByOffice(employees);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "employeeCountByOffice")
    public static Object[][] dataEmployeeCountByOffice() {

        return new Object[][]{
                new Object[]{
                        ImmutableList.of(
                                OLIVER_SMITH,
                                MANDY_COOPER,
                                LIAM_RICHARDSON,
                                AVA_PETERSON,
                                TROY_RICHARDSON,
                                EMMA_COX,
                                LUCAS_SIMMONS,
                                EVELYN_WATKINS,
                                NATHAN_VASQUEZ,
                                CHLOE_WHEELER,
                                ISAAC_MEYER,
                                ADDISON_WESLEY),
                        ImmutableMap.<Office, Long>builder()
                                .put(SPRINGFIELD_AK, 4L)
                                .put(SPRINGFIELD_CA, 1L)
                                .put(DETROIT, 1L)
                                .put(SALEM_MA, 1L)
                                .put(MIAMI, 1L)
                                .put(SAN_FRANCISCO, 3L)
                                .put(NEW_YORK, 1L)
                                .build(),
                },
        };
    }

    @Test(dataProvider = "newestEmployeeByOffice")
    public void testnewestEmployeeByOffice(
            Collection<Employee> employees,
            Map<Office, Employee> expected) {
        Map<Office, Employee> actual = streaming.newestEmployeeByOffice(employees);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "newestEmployeeByOffice")
    public static Object[][] dataNewestEmployeeByOffice() {
        return new Object[][]{
                new Object[]{
                        ImmutableList.of(),
                        ImmutableMap.of(),
                },
                new Object[]{
                        ImmutableList.of(
                                OLIVER_SMITH,
                                MANDY_COOPER,
                                LIAM_RICHARDSON,
                                AVA_PETERSON,
                                TROY_RICHARDSON,
                                EMMA_COX,
                                LUCAS_SIMMONS,
                                EVELYN_WATKINS,
                                NATHAN_VASQUEZ,
                                CHLOE_WHEELER,
                                ISAAC_MEYER,
                                ADDISON_WESLEY),
                        ImmutableMap.<Office, Employee>builder()
                                .put(SPRINGFIELD_AK, LIAM_RICHARDSON)
                                .put(SPRINGFIELD_CA, AVA_PETERSON)
                                .put(DETROIT, EMMA_COX)
                                .put(SALEM_MA, LUCAS_SIMMONS)
                                .put(MIAMI, EVELYN_WATKINS)
                                .put(SAN_FRANCISCO, NATHAN_VASQUEZ)
                                .put(NEW_YORK, ADDISON_WESLEY)
                                .build(),
                },
        };
    }

    @Test(dataProvider = "percentageStartedBefore")
    public void testPercentageStartedBefore(
            Collection<Employee> employees,
            LocalDate date,
            double expected) {
        double actual = streaming.percentageStartedBefore(employees, date);
        assertEquals(actual, expected, 0.00001d);
    }

    @DataProvider(name = "percentageStartedBefore")
    public static Object[][] dataPercentageStartedBefore() {
        List<Employee> employees = ImmutableList.of(
                OLIVER_SMITH,
                MANDY_COOPER,
                LIAM_RICHARDSON,
                AVA_PETERSON,
                TROY_RICHARDSON,
                EMMA_COX,
                LUCAS_SIMMONS,
                EVELYN_WATKINS,
                NATHAN_VASQUEZ,
                CHLOE_WHEELER,
                ISAAC_MEYER,
                ADDISON_WESLEY);

        return new Object[][]{
                new Object[]{ImmutableList.of(), LocalDate.MAX, 0.0d,},
                new Object[]{ImmutableList.of(), LocalDate.MIN, 0.0d,},
                new Object[]{employees, LocalDate.MAX, 1.0d,},
                new Object[]{employees, LocalDate.MIN, 0.0d,},
                new Object[]{employees, ISAAC_MEYER.getStartDate().minusDays(1), 0.25d},
                new Object[]{employees, ISAAC_MEYER.getStartDate(), 0.25d},
                new Object[]{employees, EVELYN_WATKINS.getStartDate().minusDays(1), 0.5d},
                new Object[]{employees, EVELYN_WATKINS.getStartDate(), 0.5d},
                new Object[]{employees, LUCAS_SIMMONS.getStartDate().minusDays(1), 0.75d},
                new Object[]{employees, LUCAS_SIMMONS.getStartDate(), 0.75d},
        };
    }

    @Test(dataProvider = "mostSeniorEmployee")
    public void testMostSeniorEmployee(
            Collection<Employee> employees,
            @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<Employee> expected) {
        Optional<Employee> actual = streaming.mostSeniorEmployee(employees);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "mostSeniorEmployee")
    public static Object[][] dataMostSeniorEmployee() {
        return new Object[][]{
                new Object[]{
                        ImmutableList.of(),
                        Optional.empty(),
                },
                new Object[]{
                        ImmutableList.of(
                                MARTY_MCFLY,
                                THOMAS_ANDERSON,
                                BLAINE_ANDERSON,
                                SAM_BECKETT),
                        Optional.of(SAM_BECKETT),
                },
                new Object[]{
                        ImmutableList.of(
                                MARTY_MCFLY,
                                MACGYVER,
                                WILL_SMITH,
                                WINSTON_SMITH,
                                AXEL_FOLEY),
                        Optional.of(WINSTON_SMITH),
                },
        };
    }

    @Test(dataProvider = "mostSeniorEmployees")
    public void testMostSeniorEmployees(Collection<Employee> employees, Set<Employee> expected) {
        Set<Employee> actual = streaming.mostSeniorEmployees(employees);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "mostSeniorEmployees")
    public void testMostSeniorEmployeesStream(
            Collection<Employee> employees,
            Set<Employee> expected) {
        Set<Employee> actual = streaming.mostSeniorEmployees(employees.stream());
        assertEquals(actual, expected);
    }

    @DataProvider(name = "mostSeniorEmployees")
    public static Object[][] dataMostSeniorEmployees() {
        LocalDate earliestDate = LocalDate.of(1985, NOVEMBER, 12);
        LocalDate laterDateA = LocalDate.of(1998, JUNE, 12);
        LocalDate laterDateB = LocalDate.of(1998, NOVEMBER, 5);
        LocalDate laterDateC = LocalDate.of(2013, AUGUST, 6);
        Employee robertFletcher = Employee.builder()
                .withName(Name.of("Fletcher", "Robert"))
                .withStartDate(earliestDate)
                .withOfficeLocation(NEW_YORK)
                .build();
        Employee lukeGriffith = Employee.builder()
                .withName(Name.of("Griffith", "Luke"))
                .withStartDate(earliestDate)
                .withOfficeLocation(NEW_YORK)
                .build();
        Employee aubreyBurgess = Employee.builder()
                .withName(Name.of("Burgess", "Aubrey"))
                .withStartDate(earliestDate)
                .withOfficeLocation(NEW_YORK)
                .build();
        Employee madisonWalsh = Employee.builder()
                .withName(Name.of("Walsh", "Madison"))
                .withStartDate(laterDateA)
                .withOfficeLocation(NEW_YORK)
                .build();
        Employee miaCohen = Employee.builder()
                .withName(Name.of("Cohen", "Mia"))
                .withStartDate(laterDateB)
                .withOfficeLocation(NEW_YORK)
                .build();
        Employee aidenHubbard = Employee.builder()
                .withName(Name.of("Hubbard", "Aiden"))
                .withStartDate(laterDateC)
                .withOfficeLocation(NEW_YORK)
                .build();

        return new Object[][]{
                new Object[]{
                        ImmutableList.of(),
                        ImmutableSet.of(),
                },
                new Object[]{
                        ImmutableList.of(aidenHubbard),
                        ImmutableSet.of(aidenHubbard),
                },
                new Object[]{
                        ImmutableList.of(
                                madisonWalsh,
                                miaCohen,
                                robertFletcher,
                                lukeGriffith,
                                aubreyBurgess,
                                aidenHubbard),
                        ImmutableSet.of(robertFletcher, lukeGriffith, aubreyBurgess),
                }
        };
    }
}

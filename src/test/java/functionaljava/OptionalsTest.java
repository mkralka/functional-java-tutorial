package functionaljava;

import com.google.common.collect.ImmutableList;
import functionaljava.types.ContactInformation;
import functionaljava.types.EmergencyContact;
import functionaljava.types.Employee;
import functionaljava.types.Name;
import functionaljava.types.Office;
import functionaljava.types.PhoneNumber;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public final class OptionalsTest {

    private static final Optionals optionals = Optionals.INSTANCE;

    private static final Name MARTY_MCFLY = Name.of("McFly", "Martin", "Calvin");
    private static final Name EMMETT_BROWN = Name.of("Brown", "Emmett", "Doc");
    private static final Name SAM_BECKETT = Name.of("Beckett", "Samuel");
    private static final Name AL_CALAVICCI = Name.of("Calavicci", "Al");
    private static final Name AXEL_FOLEY = Name.of("Foley", "Axel");
    private static final Name JOHN_TAGGART = Name.of("Taggart", "John");
    private static final Name MACGYVER = Name.of("MacGyver", "Angus");
    private static final Name PETE_THORTON = Name.of("Thornton", "Peter", "Pete");
    private static final Name FRESH_PRINCE = Name.of("Smith", "William", "Fresh Prince");
    private static final Name PHILIP_BANKS = Name.of("Banks", "Philip");
    private static final Name MARTY_KAAN = Name.of("Kaan", "Marty");
    private static final Name JEANNIE_VAN_DER_HOOVEN = Name.of("van der Hooven", "Jeannie");
    private static final Name DONALD_DUNN = Name.of("Dunn", "Donald", "Jared");


    private static final Office DETROIT = Office.of("Detroit", "MI");
    private static final Office MISSION_CITY = Office.of("Mission City", "MN");
    private static final Office STALLIONS_GATE = Office.of("Stallion's Gate", "NM");
    private static final Office HILL_VALLEY = Office.of("Hill Valley", "CA");
    private static final Office BEL_AIR = Office.of("Bel Air", "CA");
    private static final Office LOS_ANGELES = Office.of("Los Angeles", "CA");

    private static final PhoneNumber PHONE_NUMBER_A = PhoneNumber.of("+1 313 555 4329");
    private static final PhoneNumber PHONE_NUMBER_B = PhoneNumber.of("+1 657 555 9820");
    private static final PhoneNumber PHONE_NUMBER_C = PhoneNumber.of("+1 410 555 8876");

    private static final EmergencyContact EMMETT_BROWN_EMERGENCY_CONTACT =
            EmergencyContact.builder()
                    .withName(EMMETT_BROWN)
                    .withContactInformation(
                            ContactInformation.builder()
                                    .withHomePhone(PHONE_NUMBER_C)
                                    .build())
                    .build();
    private static final EmergencyContact AL_CALAVICCI_EMERGENCY_CONTACT =
            EmergencyContact.builder()
                    .withName(AL_CALAVICCI)
                    .withContactInformation(
                            ContactInformation.builder()
                                    .withMobilePhone(PHONE_NUMBER_A)
                                    .build())
                    .build();

    @Test(dataProvider = "getEmergencyContactName")
    public void testGetEmergencyContactName(Employee employee, Optional<Name> expected) {
        Optional<Name> actual = optionals.getEmergencyContactName(employee);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "getEmergencyContactName")
    public static Object[][] dataGetEmergencyContactName() {
        return new Object[][]{
                // Employees without an emergency contact will not have a name
                // extracted for their emergency contact.
                new Object[]{
                        Employee.builder()
                                .withName(MARTY_MCFLY)
                                .withStartDate(LocalDate.of(1985, 10, 26))
                                .withOfficeLocation(HILL_VALLEY)
                                .build(),
                        Optional.empty(),
                },
                new Object[]{
                        Employee.builder()
                                .withName(SAM_BECKETT)
                                .withStartDate(LocalDate.of(1989, 5, 1))
                                .withOfficeLocation(STALLIONS_GATE)
                                .build(),
                        Optional.empty(),
                },

                // Employees with an emergency contact will have a name
                // extracted from their emergency contact.
                new Object[]{
                        Employee.builder()
                                .withName(AXEL_FOLEY)
                                .withStartDate(LocalDate.of(1984, 12, 1))
                                .withOfficeLocation(DETROIT)
                                .withEmergencyContact(
                                        EmergencyContact.builder()
                                                .withName(JOHN_TAGGART)
                                                .build())
                                .build(),
                        Optional.of(JOHN_TAGGART),
                },
                new Object[]{
                        Employee.builder()
                                .withName(MACGYVER)
                                .withStartDate(LocalDate.of(1985, 9, 29))
                                .withOfficeLocation(MISSION_CITY)
                                .withEmergencyContact(
                                        EmergencyContact.builder()
                                                .withName(PETE_THORTON)
                                                .build())
                                .build(),
                        Optional.of(PETE_THORTON),
                },
        };
    }

    @Test(dataProvider = "getHomePhoneNumber")
    public void testGetHomePhoneNumber(Employee employee, Optional<PhoneNumber> expected) {
        Optional<PhoneNumber> actual = optionals.getHomePhoneNumber(employee);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "getHomePhoneNumber")
    public static Object[][] dataGetHomePhoneNumber() {
        return new Object[][]{
                // Employees without a contact information will not have a home
                // phone number
                new Object[]{
                        Employee.builder()
                                .withName(MARTY_MCFLY)
                                .withStartDate(LocalDate.of(1985, 10, 26))
                                .withOfficeLocation(HILL_VALLEY)
                                .build(),
                        Optional.empty(),
                },
                new Object[]{
                        Employee.builder()
                                .withName(SAM_BECKETT)
                                .withStartDate(LocalDate.of(1989, 5, 1))
                                .withOfficeLocation(STALLIONS_GATE)
                                .build(),
                        Optional.empty(),
                },

                // Employees with a contact information that is missing a home
                // phone number will not have a home phone number.
                new Object[]{
                        Employee.builder()
                                .withName(AXEL_FOLEY)
                                .withStartDate(LocalDate.of(1984, 12, 1))
                                .withOfficeLocation(DETROIT)
                                .withContactInformation(ContactInformation.empty())
                                .build(),
                        Optional.empty(),
                },
                new Object[]{
                        Employee.builder()
                                .withName(MACGYVER)
                                .withStartDate(LocalDate.of(1985, 9, 29))
                                .withOfficeLocation(MISSION_CITY)
                                .withContactInformation(ContactInformation.empty())
                                .build(),
                        Optional.empty(),
                },

                // Employees with a contact information containing a home
                // phone number will not have a home phone number.
                new Object[]{
                        Employee.builder()
                                .withName(AXEL_FOLEY)
                                .withStartDate(LocalDate.of(1984, 12, 1))
                                .withOfficeLocation(DETROIT)
                                .withContactInformation(
                                        ContactInformation.builder()
                                                .withHomePhone(PHONE_NUMBER_A)
                                                .build())
                                .build(),
                        Optional.of(PHONE_NUMBER_A),
                },
                new Object[]{
                        Employee.builder()
                                .withName(MACGYVER)
                                .withStartDate(LocalDate.of(1985, 9, 29))
                                .withOfficeLocation(MISSION_CITY)
                                .withContactInformation(
                                        ContactInformation.builder()
                                                .withHomePhone(PHONE_NUMBER_B)
                                                .build())
                                .build(),
                        Optional.of(PHONE_NUMBER_B),
                },
        };
    }

    @Test(dataProvider = "getContactableEmergencyContact")
    public void testGetContactableEmergencyContact(
            Employee employee,
            Optional<EmergencyContact> expected) {
        Optional<EmergencyContact> actual = optionals.getContactableEmergencyContact(employee);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "getContactableEmergencyContact")
    public static Object[][] dataGetContactableEmergencyContact() {
        return new Object[][]{
                // Employees without an emergency contact don't have anyone to
                // contact in an emergency.
                new Object[]{
                        Employee.builder()
                                .withName(MARTY_MCFLY)
                                .withStartDate(LocalDate.of(1985, 10, 26))
                                .withOfficeLocation(HILL_VALLEY)
                                .build(),
                        Optional.empty(),
                },
                new Object[]{
                        Employee.builder()
                                .withName(SAM_BECKETT)
                                .withStartDate(LocalDate.of(1989, 5, 1))
                                .withOfficeLocation(STALLIONS_GATE)
                                .build(),
                        Optional.empty(),
                },

                // Employees with an emergency contact without contact
                // information don't have anyone to contact in an emergency.
                new Object[]{
                        Employee.builder()
                                .withName(AXEL_FOLEY)
                                .withStartDate(LocalDate.of(1984, 12, 1))
                                .withOfficeLocation(DETROIT)
                                .withEmergencyContact(
                                        EmergencyContact.builder()
                                                .withName(JOHN_TAGGART)
                                                .build())
                                .build(),
                        Optional.empty(),
                },
                new Object[]{
                        Employee.builder()
                                .withName(MACGYVER)
                                .withStartDate(LocalDate.of(1985, 9, 29))
                                .withOfficeLocation(MISSION_CITY)
                                .withEmergencyContact(
                                        EmergencyContact.builder()
                                                .withName(PETE_THORTON)
                                                .build())
                                .build(),
                        Optional.empty(),
                },

                // Employees with an emergency contact with empty contact
                // information don't have anyone to contact in an emergency.
                new Object[]{
                        Employee.builder()
                                .withName(FRESH_PRINCE)
                                .withStartDate(LocalDate.of(1990, 9, 10))
                                .withOfficeLocation(BEL_AIR)
                                .withEmergencyContact(
                                        EmergencyContact.builder()
                                                .withName(PHILIP_BANKS)
                                                .withContactInformation(ContactInformation.empty())
                                                .build())
                                .build(),
                        Optional.empty(),
                },
                new Object[]{
                        Employee.builder()
                                .withName(MARTY_KAAN)
                                .withStartDate(LocalDate.of(2013, 1, 8))
                                .withOfficeLocation(LOS_ANGELES)
                                .withEmergencyContact(
                                        EmergencyContact.builder()
                                                .withName(JEANNIE_VAN_DER_HOOVEN)
                                                .build())
                                .build(),
                        Optional.empty(),
                },

                // Employees with an emergency contact with a phone number
                // have someone to contact in an emergency.
                new Object[]{
                        Employee.builder()
                                .withName(MARTY_MCFLY)
                                .withStartDate(LocalDate.of(1985, 10, 26))
                                .withOfficeLocation(HILL_VALLEY)
                                .withEmergencyContact(EMMETT_BROWN_EMERGENCY_CONTACT)
                                .build(),
                        Optional.of(EMMETT_BROWN_EMERGENCY_CONTACT),
                },
                new Object[]{
                        Employee.builder()
                                .withName(SAM_BECKETT)
                                .withStartDate(LocalDate.of(1989, 5, 1))
                                .withOfficeLocation(STALLIONS_GATE)
                                .withEmergencyContact(AL_CALAVICCI_EMERGENCY_CONTACT)
                                .build(),
                        Optional.of(AL_CALAVICCI_EMERGENCY_CONTACT),
                },
        };
    }

    @Test(dataProvider = "collectEmployeesWithoutEmergencyContacts")
    public void testCollectEmployeesWithoutEmergencyContacts(
            Employee employee, List<EmergencyContact> expected) {
        List<EmergencyContact> actual = new ArrayList<>(1);
        optionals.collectEmployeesWithoutEmergencyContacts(employee, actual);

        assertEquals(actual, expected);
    }

    @DataProvider(name = "collectEmployeesWithoutEmergencyContacts")
    public static Object[][] dataCollectEmployeesWithoutEmergencyContacts() {
        return new Object[][]{
                // Employees without an emergency contact don't have anyone to
                // contact in an emergency.
                new Object[]{
                        Employee.builder()
                                .withName(MARTY_MCFLY)
                                .withStartDate(LocalDate.of(1985, 10, 26))
                                .withOfficeLocation(HILL_VALLEY)
                                .build(),
                        ImmutableList.of(),
                },
                new Object[]{
                        Employee.builder()
                                .withName(SAM_BECKETT)
                                .withStartDate(LocalDate.of(1989, 5, 1))
                                .withOfficeLocation(STALLIONS_GATE)
                                .build(),
                        ImmutableList.of(),
                },

                // Employees with an emergency contact with a phone number
                // have someone to contact in an emergency.
                new Object[]{
                        Employee.builder()
                                .withName(MARTY_MCFLY)
                                .withStartDate(LocalDate.of(1985, 10, 26))
                                .withOfficeLocation(HILL_VALLEY)
                                .withEmergencyContact(EMMETT_BROWN_EMERGENCY_CONTACT)
                                .build(),
                        ImmutableList.of(EMMETT_BROWN_EMERGENCY_CONTACT),
                },
                new Object[]{
                        Employee.builder()
                                .withName(SAM_BECKETT)
                                .withStartDate(LocalDate.of(1989, 5, 1))
                                .withOfficeLocation(STALLIONS_GATE)
                                .withEmergencyContact(AL_CALAVICCI_EMERGENCY_CONTACT)
                                .build(),
                        ImmutableList.of(AL_CALAVICCI_EMERGENCY_CONTACT),
                },
        };
    }

    @Test(dataProvider = "preferredName")
    public void testPreferredName(Name name, String expected) {
        String actual = optionals.preferredName(name);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "preferredName")
    public static Object[][] dataPreferredName() {
        return new Object[][]{
                // The preferred name of an individual with no nickname is their
                // first name.
                new Object[]{Name.of("MacGyver", "Angus"), "Angus"},
                new Object[]{Name.of("Foley", "Axel"), "Axel"},
                // The preferred name of an individual with a nickname is their
                // nickname.
                new Object[]{Name.of("McFly", "Martin", "Marty"), "Marty"},
                new Object[]{Name.of("Brown", "Emmett", "Doc"), "Doc"},
        };
    }

    @Test(dataProvider = "getEmployeeContactInformation")
    public void testGetEmployeeContactInformation(
            Employee employee,
            Function<Name, ContactInformation> contactInformationFetcher,
            ContactInformation expected) {
        ContactInformation actual =
                optionals.getEmployeeContactInformation(employee, contactInformationFetcher);

        assertEquals(actual, expected);
    }

    @DataProvider(name = "getEmployeeContactInformation")
    public static Object[][] dataGetEmployeeContactInformation() {
        Function<Name, ContactInformation> FAILING_FETCHER = x -> {
            fail();
            return ContactInformation.empty();
        };
        ContactInformation infoA = ContactInformation.builder()
                .withHomePhone(PHONE_NUMBER_A)
                .build();
        ContactInformation infoB = ContactInformation.builder()
                .withMobilePhone(PHONE_NUMBER_B)
                .build();
        ContactInformation infoC = ContactInformation.builder()
                .withHomePhone(PHONE_NUMBER_A)
                .withMobilePhone(PHONE_NUMBER_B)
                .build();

        return new Object[][]{
                // An employee with contact information will provide said
                // information and the fetcher will not be invoked.
                new Object[]{
                        Employee.builder()
                                .withName(SAM_BECKETT)
                                .withStartDate(LocalDate.of(1989, 5, 1))
                                .withOfficeLocation(STALLIONS_GATE)
                                .withContactInformation(infoA)
                                .build(),
                        FAILING_FETCHER,
                        infoA,
                },

                // An employee with no contact information will provide contact
                // information from the fetcher.
                new Object[]{
                        Employee.builder()
                                .withName(MARTY_KAAN)
                                .withStartDate(LocalDate.of(2013, 1, 8))
                                .withOfficeLocation(LOS_ANGELES)
                                .build(),
                        (Function<Name, ContactInformation>) x -> infoB,
                        infoB,
                }
        };
    }
}

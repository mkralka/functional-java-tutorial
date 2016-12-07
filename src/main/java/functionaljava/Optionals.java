package functionaljava;

import functionaljava.types.ContactInformation;
import functionaljava.types.EmergencyContact;
import functionaljava.types.Employee;
import functionaljava.types.Name;
import functionaljava.types.PhoneNumber;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Optionals {
    public static final Optionals INSTANCE = new Optionals();

    // A useful predicate for determining if a ContactInformation object is
    // empty (contains no contact information fields).
    private static final Predicate<ContactInformation> CONTACT_INFO_IS_EMPTY =
            ContactInformation.empty()::equals;

    private Optionals() {
    }

    /**
     * Get the name of the emergency contact, if any, for {@code employee}.
     *
     * @param employee The employee for which to get the first name of their emergency contact.
     * @return The name of the emergency for {@code employee}, or {@link Optional#empty()} if not
     * available.
     */
    public Optional<Name> getEmergencyContactName(Employee employee) {
        // Practice transforming one Optional into another, preserving
        // emptiness.
        //
        // Replace the body of this method with one that returns the name of an
        // employee's emergency contact. If the employee doesn't have an
        // emergency contact, an empty Optional should be returned.
        //
        // Verify your solution:
        //     mvn test -Dtest=OptionalsTest#testGetEmergencyContactName
        //
        // See the solution:
        //     tutorial/optional/transforming_ex1_sltn.md
        //
        // Hints:
        // * An employee's emergency contact can be retrieved using
        //   Employee.getEmergencyContact.
        // * An emergency contact's name can be retrieved using
        //   EmergencyContact.getName.
        return null;
    }

    /**
     * Get the home phone number, if any, for {@code employee}.
     *
     * @param employee The employee for which to get a home phone number.
     * @return {@code employee}'s home phone number, if available. {@link Optional#empty()}
     * otherwise.
     */
    public Optional<PhoneNumber> getHomePhoneNumber(Employee employee) {
        // Practice transforming one Optional into another, preserving
        // emptiness, when the transformation function returns an Optional.
        //
        // Replace the body of this method with one that returns an employee's
        // home phone number, if available. If the employee doesn't have any
        // contact information or the contact information doesn't include a
        // home phone number, an empty `Optional` should be returned.
        //
        // Verify your solution:
        //     mvn test -Dtest=OptionalsTest#testGetHomePhoneNumber
        //
        // See the solution:
        //     tutorial/optional/transforming_ex2_sltn.md
        //
        // Hints:
        // * An employee's contact information can be retrieved using
        //   Employee.getContactInformation.
        // * A home phone number can be retrieved from contact information using
        //   ContactInformation.getHomePhone.
        return null;
    }

    /**
     * Get an employee's emergency contact if said contact is contactable.
     *
     * @param employee The employee whose emergency contact to fetch.
     * @return {@code employee}'s emergency contact if said contact is contactable,
     * {@link Optional#empty()} otherwise.
     */
    public Optional<EmergencyContact> getContactableEmergencyContact(Employee employee) {
        // Practice filtering Options, creating empty Optionals from non-empty
        // ones if they don't match a particular predicate.
        //
        // Replace the body of this method with one that returns an employee's
        // emergency contact, if said contact is contactable. If the employee
        // has no emergency contact or that contact as no contact information,
        // an empty Optional should be returned.
        //
        // CONTACT_INFO_IS_EMPTY, a Predicate<ContactInformation>, can be used
        // to determine if a ContactInformation is absent of all contact
        // details.
        //
        // Verify your solution:
        //     mvn test -Dtest=OptionalsTest#testGetContactableEmergencyContact
        //
        // See the solution:
        //     tutorial/optional/emptying_ex1_sltn.md
        return null;
    }

    /**
     * Add an employee's emergency contact, if available, to {@code allEmergencyContacts}.
     *
     * @param employee             The employee whose emergency contact is to be added to
     *                             {@code allEmergencyContacts}.
     * @param allEmergencyContacts The collection to which to add {@code employee}'s emergency
     *                             contact, if present.
     */
    public void collectEmployeesWithoutEmergencyContacts(
            Employee employee,
            Collection<EmergencyContact> allEmergencyContacts) {
        // Practice conditionally executing code based on the emptiness of an
        // Optional; code is only executed if and only if the Optional is non-
        // empty.
        //
        // Replace the body of this method with one that adds the employee's
        // emergency contact to allEmergencyContacts if the emergency contact
        // is present.
        //
        // Verify your solution:
        //     mvn test -Dtest=OptionalsTest#testCollectEmployeesWithoutEmergencyContacts
        //
        // See the solution:
        //     tutorial/optional/conditional_ex1_sltn.md
    }

    /**
     * Get the preferred name of an individual.
     * <p>
     * All individuals prefer to be called by their given name. However, individuals with a nickname
     * prefer this over their given name.
     *
     * @param name The name of the individual for which to get their preferred name.
     * @return The preferred name of {@code name}.
     */
    public String preferredName(Name name) {
        // Practice unwrapping the value from an Optional, specifying a default
        // value to use if the Optional is empty.
        //
        // Replace the body of this method with one that returns the
        // individual's first name unless they have a nickname, which should be
        // returned in its place.
        //
        // Verify your solution:
        //     mvn test -Dtest=OptionalsTest#testPreferredName
        //
        // See the solution:
        //     tutorial/optional/unwrapping_ex1_sltn.md
        return null;
    }

    /**
     * Get contact information for an employee, fetching the contact information if not available.
     *
     * @param employee                  The employee for which to get contact information.
     * @param contactInformationFetcher A fetcher that can be used to retrieve contact information
     *                                  by name if it is not available in {@code employee}.
     * @return Contact information associated with {@code employee}
     */
    public ContactInformation getEmployeeContactInformation(
            Employee employee,
            Function<Name, ContactInformation> contactInformationFetcher) {
        // Practice unwrapping the value from an Optional, specifying a
        // supplier for creating the default value touse if the Optional is
        // empty.
        //
        // Replace the body of this method with one that returns the employee's
        // contact information. If the employee doesn't have any contact
        // information, use contactInformationFetcher to retrieve it. Since
        // fetching contact information is an expensive operation, only do so
        // if needed.
        //
        // Verify your solution:
        //     mvn test -Dtest=OptionalsTest#testGetEmployeeContactInformation
        //
        // See the solution:
        //     tutorial/optional/unwrapping_ex2_sltn.md
        return null;
    }
}

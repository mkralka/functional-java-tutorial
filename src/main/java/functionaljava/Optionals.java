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
        // Replace the body of this method with one that returns the name of the
        // employee's emergency contact, if one is specified. If the employee
        // doesn't have an emergency contact, an empty Optional should be
        // returned.
        //
        // Your solution should use the functional API of Optional, without
        // any branches or if statements.
        //
        // HINT: The employee's emergency contact can be accessed by calling
        //       employee.getEmergencyContact(). Unfortunately, this is an
        //       Optional<EmergencyContact> and not the needed Optional<Name>.
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
        // Replace the body of this method with one that returns the employee's
        // home phone number, if available. If the employee doesn't have a home
        // phone number, an empty Optional should be returned.
        //
        // Your solution should use the functional API of Optional, without
        // any branches or if statements.
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
        // Replace the body of this method with one that returns employee's
        // emergency contact if said contact is contactable. Otherwise, return
        // an empty optional. An emergency contact is contactable if it has
        // non-empty contact information.
        //
        // Your solution should use the functional API of Optional, without
        // any branches or if statements.
        //
        // HINT: CONTACT_INFO_IS_EMPTY is a predicate that evaluates to true
        //       if and only if the supplied ContactInformation is empty.
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
        // Replace the body of this method with one that adds employee's
        // emergency contact if one is present.
        //
        // Your solution should use the functional API of Optional, without
        // branches or if statements.
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
        // Replace the body of this method with one that returns the preferred
        // name of an individual. An individual's preferred name is their given
        // name unless they have a nickname (in which case their nickname is
        // preferred).
        //
        // Your solution should use the functional API of Optional, without
        // branches or if statements.
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
        // Replace the body of this method with one that returns the contact
        // information of an employee. If an employee doesn't have any
        // associated contact information, it can be retrieved from the
        // contactInformationFetcher. However, since this is an expensive
        // operation, it should only be invoked if needed.
        //
        // Your solution should use the functional API of Optional, without
        // branches or if statements.
        return null;
    }
}

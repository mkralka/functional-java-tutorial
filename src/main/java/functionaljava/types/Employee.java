package functionaljava.types;

import com.google.common.base.MoreObjects;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Employee {
    private final Name name;
    private final LocalDate startDate;
    private final Office location;
    private final Optional<ContactInformation> contactInformation;
    private final Optional<EmergencyContact> emergencyContact;

    private Employee(
            Name name,
            LocalDate startDate,
            Office location,
            ContactInformation contactInformation,
            EmergencyContact emergencyContact) {
        this.name = checkNotNull(name, "name is null");
        this.startDate = checkNotNull(startDate, "startDate is null");
        this.location = checkNotNull(location, "location is null");
        this.contactInformation = Optional.ofNullable(contactInformation);
        this.emergencyContact = Optional.ofNullable(emergencyContact);
    }

    public Name getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Office getLocation() {
        return location;
    }

    public Optional<ContactInformation> getContactInformation() {
        return contactInformation;
    }

    public Optional<EmergencyContact> getEmergencyContact() {
        return emergencyContact;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("startDate", startDate)
                .add("location", location)
                .add("contactInformation", contactInformation)
                .add("emergencyContact", emergencyContact)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(startDate, employee.startDate) &&
                Objects.equals(location, employee.location) &&
                Objects.equals(contactInformation, employee.contactInformation) &&
                Objects.equals(emergencyContact, employee.emergencyContact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, location, contactInformation, emergencyContact);
    }

    public static NameBuilder builder() {
        return new BuilderImpl();
    }

    public interface NameBuilder {
        StartDateBuilder withName(Name name);
    }

    public interface StartDateBuilder {
        OfficeLocationBuilder withStartDate(LocalDate startDate);
    }

    public interface OfficeLocationBuilder {
        ContactInformationBuilder withOfficeLocation(Office officeLocation);
    }

    public interface ContactInformationBuilder extends EmergencyContactBuilder {
        EmergencyContactBuilder withContactInformation(ContactInformation contactInformation);
    }

    public interface EmergencyContactBuilder extends Builder {
        Builder withEmergencyContact(EmergencyContact emergencyContact);
    }

    public interface Builder {
        Employee build();
    }

    private static final class BuilderImpl implements
            NameBuilder,
            StartDateBuilder,
            OfficeLocationBuilder,
            ContactInformationBuilder,
            EmergencyContactBuilder,
            Builder {

        private Name name;
        private LocalDate startDate;
        private Office officeLocation;
        private ContactInformation contactInformation;
        private EmergencyContact emergencyContact;

        @Override
        public StartDateBuilder withName(Name name) {
            this.name = checkNotNull(name, "name is null");
            return this;
        }

        @Override
        public OfficeLocationBuilder withStartDate(LocalDate startDate) {
            this.startDate = checkNotNull(startDate, "startDate is null");
            return this;
        }

        @Override
        public ContactInformationBuilder withOfficeLocation(Office officeLocation) {
            this.officeLocation = checkNotNull(officeLocation, "officeLocation is null");
            return this;
        }

        @Override
        public EmergencyContactBuilder withContactInformation(
                ContactInformation contactInformation) {
            this.contactInformation =
                    checkNotNull(contactInformation, "contactInformation is null");
            return this;
        }

        @Override
        public Builder withEmergencyContact(EmergencyContact emergencyContact) {
            this.emergencyContact = checkNotNull(emergencyContact, "emergencyContact is null");
            return this;
        }

        @Override
        public Employee build() {
            return new Employee(
                    name,
                    startDate,
                    officeLocation,
                    contactInformation,
                    emergencyContact);
        }
    }
}

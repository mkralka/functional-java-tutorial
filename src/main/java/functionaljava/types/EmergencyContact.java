package functionaljava.types;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public final class EmergencyContact {
    private Name name;
    private Optional<ContactInformation> contactInformation;

    private EmergencyContact(Name name, ContactInformation contactInformation) {
        this.name = checkNotNull(name, "name is null");
        this.contactInformation = Optional.ofNullable(contactInformation);
    }

    public Name getName() {
        return name;
    }

    public Optional<ContactInformation> getContactInformation() {
        return contactInformation;
    }

    public static NameBuilder builder() {
        return new BuilderImpl();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("contactInformation", contactInformation)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmergencyContact that = (EmergencyContact) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(contactInformation, that.contactInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contactInformation);
    }

    public interface NameBuilder {
        ContactInformationBuilder withName(Name name);
    }

    public interface ContactInformationBuilder extends Builder {
        Builder withContactInformation(ContactInformation contactInformation);
    }

    public interface Builder {
        EmergencyContact build();
    }

    private static class BuilderImpl implements NameBuilder, ContactInformationBuilder, Builder {
        private Name name;
        private ContactInformation contactInformation;

        private BuilderImpl() {
        }

        @Override
        public ContactInformationBuilder withName(Name name) {
            this.name = checkNotNull(name, "name is null");
            return this;
        }

        @Override
        public Builder withContactInformation(ContactInformation contactInformation) {
            this.contactInformation =
                    checkNotNull(contactInformation, "contactInformation is null");
            return this;
        }

        public EmergencyContact build() {
            return new EmergencyContact(name, contactInformation);
        }
    }
}

package functionaljava.types;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

public final class ContactInformation {
    private static final ContactInformation EMPTY =
            new ContactInformation(null, null, null, null, null, null);

    private final Optional<PhoneNumber> homePhone;
    private final Optional<PhoneNumber> mobilePhone;
    private final Optional<PhoneNumber> workPhone;
    private final Optional<PhoneNumber> otherPhone;
    private final Optional<Address> homeAddress;
    private final Optional<Address> workAddress;

    public static ContactInformation empty() {
        return EMPTY;
    }

    private ContactInformation(
            PhoneNumber homePhone,
            PhoneNumber mobilePhone,
            PhoneNumber workPhone,
            PhoneNumber otherPhone,
            Address homeAddress,
            Address workAddress) {
        this.homePhone = Optional.ofNullable(homePhone);
        this.mobilePhone = Optional.ofNullable(mobilePhone);
        this.workPhone = Optional.ofNullable(workPhone);
        this.otherPhone = Optional.ofNullable(otherPhone);
        this.homeAddress = Optional.ofNullable(homeAddress);
        this.workAddress = Optional.ofNullable(workAddress);
    }

    public Optional<PhoneNumber> getHomePhone() {
        return homePhone;
    }

    public Optional<PhoneNumber> getMobilePhone() {
        return mobilePhone;
    }

    public Optional<PhoneNumber> getWorkPhone() {
        return workPhone;
    }

    public Optional<PhoneNumber> getOtherPhone() {
        return otherPhone;
    }

    public Optional<Address> getHomeAddress() {
        return homeAddress;
    }

    public Optional<Address> getWorkAddress() {
        return workAddress;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("homePhone", homePhone)
                .add("mobilePhone", mobilePhone)
                .add("workPhone", workPhone)
                .add("otherPhone", otherPhone)
                .add("homeAddress", homeAddress)
                .add("workAddress", workAddress)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactInformation that = (ContactInformation) o;
        return Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(otherPhone, that.otherPhone) &&
                Objects.equals(homeAddress, that.homeAddress) &&
                Objects.equals(workAddress, that.workAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePhone, mobilePhone, workPhone, otherPhone, homeAddress, workAddress);
    }

    public static HomePhoneBuilder builder() {
        return new BuilderImpl();
    }

    public interface HomePhoneBuilder extends MobilePhoneBuilder {
        MobilePhoneBuilder withHomePhone(PhoneNumber phoneNumber);
    }

    public interface MobilePhoneBuilder extends WorkPhoneBuilder {
        WorkAddressBuilder withMobilePhone(PhoneNumber phoneNumber);
    }

    public interface WorkPhoneBuilder extends OtherPhoneBuilder {
        OtherPhoneBuilder withWorkPhone(PhoneNumber phoneNumber);
    }

    public interface OtherPhoneBuilder extends HomeAddressBuilder {
        HomeAddressBuilder withOtherPhone(PhoneNumber phoneNumber);
    }

    public interface HomeAddressBuilder extends WorkAddressBuilder {
        WorkAddressBuilder withHomeAddress(Address address);
    }

    public interface WorkAddressBuilder extends Builder {
        Builder withWorkAddress(Address address);
    }

    public interface Builder {
        ContactInformation build();
    }

    private static class BuilderImpl implements HomePhoneBuilder {
        private PhoneNumber homePhone;
        private PhoneNumber mobilePhone;
        private PhoneNumber workPhone;
        private PhoneNumber otherPhone;
        private Address homeAddress;
        private Address workAddress;

        @Override
        public MobilePhoneBuilder withHomePhone(PhoneNumber phoneNumber) {
            homePhone = phoneNumber;
            return this;
        }

        @Override
        public WorkPhoneBuilder withMobilePhone(PhoneNumber phoneNumber) {
            mobilePhone = phoneNumber;
            return this;
        }

        @Override
        public OtherPhoneBuilder withWorkPhone(PhoneNumber phoneNumber) {
            workPhone = phoneNumber;
            return this;
        }

        @Override
        public HomeAddressBuilder withOtherPhone(PhoneNumber phoneNumber) {
            otherPhone = phoneNumber;
            return this;
        }

        @Override
        public WorkAddressBuilder withHomeAddress(Address address) {
            homeAddress = address;
            return this;
        }

        @Override
        public Builder withWorkAddress(Address address) {
            workAddress = address;
            return this;
        }

        @Override
        public ContactInformation build() {
            if (homePhone == null
                    && mobilePhone == null
                    && workPhone == null
                    && otherPhone == null
                    && homeAddress == null
                    && workAddress == null) {
                return EMPTY;
            }

            return new ContactInformation(
                    homePhone,
                    mobilePhone,
                    workPhone,
                    otherPhone,
                    homeAddress,
                    workAddress);
        }
    }
}

package functionaljava.types;

import com.google.common.base.MoreObjects;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Address {
    private final String numberAndStreet;
    private final String city;
    private final String provinceOrState;
    private final String postalCode;
    private final String country;

    public Address(
            String numberAndStreet,
            String city,
            String provinceOrState,
            String postalCode,
            String country) {
        this.numberAndStreet = checkNotNull(numberAndStreet, "numberAndStreet is null");
        this.city = checkNotNull(city, "city is null");
        this.provinceOrState = checkNotNull(provinceOrState, "provinceOrState is null");
        this.postalCode = checkNotNull(postalCode, "postalCode is null");
        this.country = checkNotNull(country, "country is null");
    }

    public String getNumberAndStreet() {
        return numberAndStreet;
    }

    public String getCity() {
        return city;
    }

    public String getProvinceOrState() {
        return provinceOrState;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("numberAndStreet", numberAndStreet)
                .add("city", city)
                .add("provinceOrState", provinceOrState)
                .add("postalCode", postalCode)
                .add("country", country)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(numberAndStreet, address.numberAndStreet) &&
                Objects.equals(city, address.city) &&
                Objects.equals(provinceOrState, address.provinceOrState) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberAndStreet, city, provinceOrState, postalCode, country);
    }
}

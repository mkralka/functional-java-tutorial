package functionaljava.types;

import com.google.common.base.MoreObjects;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public final class PhoneNumber {
    private final String digits;

    public static PhoneNumber of(String digits) {
        return new PhoneNumber(checkNotNull(digits, "digits is null"));
    }

    private PhoneNumber(String digits) {
        this.digits = digits;
    }

    public String getDigits() {
        return digits;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("digits", digits)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(digits, that.digits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digits);
    }
}

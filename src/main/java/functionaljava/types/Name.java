package functionaljava.types;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Name {
    private final String familyName;
    private final String givenName;
    private final Optional<String> nickName;

    public static Name of(String familyName, String givenName) {
        return new Name(familyName, givenName, null);
    }

    public static Name of(String familyName, String givenName, String nickName) {
        return new Name(familyName, givenName, checkNotNull(nickName, "nickName is null"));
    }

    private Name(String familyName, String givenName, String nickName) {
        this.familyName = checkNotNull(familyName, "familyName is null");
        this.givenName = checkNotNull(givenName, "givenName is null");
        this.nickName = Optional.ofNullable(nickName);
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public Optional<String> getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("familyName", familyName)
                .add("givenName", givenName)
                .add("nickName", nickName)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(familyName, name.familyName) &&
                Objects.equals(givenName, name.givenName) &&
                Objects.equals(nickName, name.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyName, givenName, nickName);
    }
}

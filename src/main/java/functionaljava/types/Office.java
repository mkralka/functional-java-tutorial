package functionaljava.types;

import com.google.common.base.MoreObjects;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Office {
    private final String city;
    private final String state;

    public static Office of(String city, String state) {
        return new Office(city, state);
    }

    private Office(String city, String state) {
        this.city = checkNotNull(city, "city is null");
        this.state = checkNotNull(state, "state is null");
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("city", city)
                .add("state", state)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Objects.equals(city, office.city) &&
                Objects.equals(state, office.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, state);
    }
}

# Optional

## Solution: Get Value with Default

``` java
public String preferredName(Name name) {
    return name.getNickName()
            .orElse(name.getGivenName());
}
```

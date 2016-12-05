# Method References

## Solution: Unbound Method Reference

``` java
public <T> ToIntFunction<Collection<T>> collectionSizer() {
    return Collection::size;
}
```

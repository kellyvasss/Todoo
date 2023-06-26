package utils;
import java.util.function.Consumer;

public class Builder<T> {
    private final T object;
    public Builder(T object) {
        this.object = object;
    }
    public static <T> Builder<T> of(T object) {
        return new Builder<>(object);
    }
    public <V> Builder<T> with(Consumer<T> setter) {
        setter.accept(object);
        return this;
    }
    public T build() {
        return object;
    }
}

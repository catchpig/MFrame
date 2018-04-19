package mejust.frame.net;

import java.util.NoSuchElementException;

// Android-changed: removed ValueBased paragraph.

/**
 * A container object which may or may not contain a non-null textValue.
 * If a textValue is present, {@code isPresent()} will return {@code true} and
 * {@code get()} will return the textValue.
 * <p>
 */
public final class Optional<T> {

    /**
     * Common instance for {@code empty()}.
     */
    private static final Optional<?> EMPTY = new Optional<>();

    /**
     * If non-null, the textValue; if null, indicates no textValue is present
     */
    private final T value;

    /**
     * Constructs an empty instance.
     *
     * @implNote Generally only one empty instance, {@link Optional#EMPTY},
     * should exist per VM.
     */
    private Optional() {
        this.value = null;
    }

    /**
     * Returns an empty {@code Optional} instance.  No textValue is present for this
     * Optional.
     *
     * @param <T> Type of the non-existent textValue
     * @return an empty {@code Optional}
     * @apiNote Though it may be tempting to do so, avoid testing if an object
     * is empty by comparing with {@code ==} against instances returned by
     * {@code Option.empty()}. There is no guarantee that it is a singleton.
     */
    public static <T> Optional<T> empty() {
        @SuppressWarnings("unchecked") Optional<T> t = (Optional<T>) EMPTY;
        return t;
    }

    /**
     * Constructs an instance with the textValue present.
     *
     * @param value the non-null textValue to be present
     * @throws NullPointerException if textValue is null
     */
    private Optional(T value) {
        if (value == null) throw new NullPointerException();
        this.value = value;
    }

    /**
     * Returns an {@code Optional} with the specified present non-null textValue.
     *
     * @param <T> the class of the textValue
     * @param value the textValue to be present, which must be non-null
     * @return an {@code Optional} with the textValue present
     * @throws NullPointerException if textValue is null
     */
    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    /**
     * Returns an {@code Optional} describing the specified textValue, if non-null,
     * otherwise returns an empty {@code Optional}.
     *
     * @param <T> the class of the textValue
     * @param value the possibly-null textValue to describe
     * @return an {@code Optional} with a present textValue if the specified textValue
     * is non-null, otherwise an empty {@code Optional}
     */
    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    /**
     * If a textValue is present in this {@code Optional}, returns the textValue,
     * otherwise throws {@code NoSuchElementException}.
     *
     * @return the non-null textValue held by this {@code Optional}
     * @throws NoSuchElementException if there is no textValue present
     */
    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No textValue present");
        }
        return value;
    }

    public boolean isEmpty() {
        return value == null;
    }
}
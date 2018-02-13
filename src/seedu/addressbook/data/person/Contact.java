package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Contact {

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given contact.
     *
     * @throws IllegalValueException if given contact string is invalid.
     */
    public Contact(String data, boolean isPrivate, String validationRegex, String constraints) throws IllegalValueException {
        this.isPrivate = isPrivate;
        String trimmedData = data.trim();
        if (!isValid(trimmedData, validationRegex)) {
            throw new IllegalValueException(constraints);
        }
        this.value = trimmedData;
    }

    /**
     * Returns true if the given string is a valid contact of person.
     */
    public static boolean isValid(String test, String validationRegex) {
        return test.matches(validationRegex);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}

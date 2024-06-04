package com.github.jinahya.kftc.financial.institution.info;

final class _String_TestUtils {

    static String nonBlankOrNull(String string) {
        if (string == null) {
            return null;
        }
        string = string.strip();
        if (string.isEmpty() || string.isBlank()) {
            return null;
        }
        return string;
    }

    private _String_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

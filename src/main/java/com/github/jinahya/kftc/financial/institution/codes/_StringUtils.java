package com.github.jinahya.kftc.financial.institution.codes;

import java.util.Objects;

@SuppressWarnings({"java:S101"})
final class _StringUtils {

    static int half(final int c) {
        if (c >= '！' && c <= '～') {
            return c - 0xfee0;
        }
        if (c == 0x3000) {
            return 0x20;
        }
        return c;
    }

    static String half(final String string) {
        Objects.requireNonNull(string, "string is null");
        final var codePoints = string.codePoints().map(_StringUtils::half).toArray();
        return new String(codePoints, 0, codePoints.length);
    }

    private _StringUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

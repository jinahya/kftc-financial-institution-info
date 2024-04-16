package com.github.jinahya.kftc.financial.institution.codes;

import java.util.regex.Pattern;

public class Constants {

    static final String DELIMITER_REPR = "■ 대표코드";

    static final String REGEXP_CODE_NAME_PAIR = "(\\d{3})\\s(\\p{L}+)(\\s(\\d{3})\\s(\\p{L}+))?}";

    static final Pattern PATTERN_CODE_NAME_PAIR = Pattern.compile(REGEXP_CODE_NAME_PAIR);

    private Constants() {
        throw new AssertionError("instantiation is not allowed");
    }
}

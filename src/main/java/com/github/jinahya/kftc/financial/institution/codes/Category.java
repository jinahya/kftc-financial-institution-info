package com.github.jinahya.kftc.financial.institution.codes;

import java.util.Objects;

public enum Category {

    /**
     * 은행.
     */
    BANK("■ 은 행"),

    /**
     * 금융투자회사.
     */
    FIIN("■ 금융투자회사"),

    /**
     * 캐피탈사.
     */
    CAPI("■ 캐피탈사"),

    /**
     * 카드사.
     */
    CARD("■ 카드사"),

    /**
     * 보험사.
     */
    INSU("■ 보험사"),

    /**
     * 기타.
     */
    MISC("■ 기 타");

    static Category valueOfDelimiter(final String delimiter) {
        for (final Category category : Category.values()) {
            if (Objects.equals(category.delimiter, delimiter)) {
                return category;
            }
        }
        throw new IllegalArgumentException("no value for the delimiter('" + delimiter + "'");
    }

    Category(final String delimiter) {
        this.delimiter = Objects.requireNonNull(delimiter, "delimiter is null");
    }

    final String delimiter;
}

package com.github.jinahya.kftc.financial.institution.codes;

import java.util.Objects;

/**
 * Predefined categories for instance of {@link KftcFinancialInstitutionInfo} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public enum KftcFinancialInstitutionCategory {

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

    // -----------------------------------------------------------------------------------------------------------------
    static KftcFinancialInstitutionCategory valueOfDelimiter(final String delimiter) {
        for (final KftcFinancialInstitutionCategory category : KftcFinancialInstitutionCategory.values()) {
            if (Objects.equals(category.delimiter, delimiter)) {
                return category;
            }
        }
        throw new IllegalArgumentException("no value for the delimiter('" + delimiter + "')");
    }

    // -----------------------------------------------------------------------------------------------------------------
    KftcFinancialInstitutionCategory(final String delimiter) {
        this.delimiter = Objects.requireNonNull(delimiter, "delimiter is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final String delimiter;
}

package com.github.jinahya.kftc.financial.institution.codes;

/*-
 * #%L
 * kftc-financial-institution-info
 * %%
 * Copyright (C) 2024 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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

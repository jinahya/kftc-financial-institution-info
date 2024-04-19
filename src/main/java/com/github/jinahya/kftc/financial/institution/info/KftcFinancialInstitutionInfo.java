package com.github.jinahya.kftc.financial.institution.info;

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

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an information of a financial institution assigned by <a href="https://www.kftc.or.kr">KFTC</a>.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see KftcFinancialInstitutionInfoSet
 */
public final class KftcFinancialInstitutionInfo
        implements Serializable {

    private static final long serialVersionUID = 5345357074469188526L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String DELIMITER = "\u001d";

    // -----------------------------------------------------------------------------------------------------------------
    private static String string(String string) {
        if (string == null) {
            return null;
        }
        string = string.strip();
        if (string.isEmpty() || string.isBlank()) {
            return null;
        }
        return string;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    KftcFinancialInstitutionInfo() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "category=" + category +
                ",code=" + code +
                ",name=" + name +
                ",representative=" + representative +
                '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof KftcFinancialInstitutionInfo)) return false;
        KftcFinancialInstitutionInfo that = (KftcFinancialInstitutionInfo) obj;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    // -----------------------------------------------------------------------------------------------------------------
    static KftcFinancialInstitutionInfo parse(final String string) {
        final var instance = new KftcFinancialInstitutionInfo();
        final var split = string.split(DELIMITER);
        instance.category = KftcFinancialInstitutionCategory.valueOf(split[0]);
        instance.code = split[1];
        instance.name = split[2];
        instance.representative = Boolean.parseBoolean(split[3]);
        return instance;
    }

    String toLine() {
        return String.join(DELIMITER, category.toString(), code, name, Boolean.toString(representative));
    }

    // -----------------------------------------------------------------------------------------------------------------

    public KftcFinancialInstitutionCategory getCategory() {
        return category;
    }

    void setCategory(final KftcFinancialInstitutionCategory category) {
        this.category = category;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getCode() {
        return code;
    }

    void setCode(final String code) {
        this.code = string(code);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = string(name);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public boolean isRepresentative() {
        return representative;
    }

    void setRepresentative(final boolean representative) {
        this.representative = representative;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private KftcFinancialInstitutionCategory category;

    private String code;

    private String name;

    private boolean representative;
}
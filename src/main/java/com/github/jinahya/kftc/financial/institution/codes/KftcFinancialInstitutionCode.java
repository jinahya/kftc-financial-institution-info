package com.github.jinahya.kftc.financial.institution.codes;

import java.io.Serializable;
import java.util.Objects;

public final class KftcFinancialInstitutionCode
        implements Serializable {

    private static final long serialVersionUID = 7009655307428680743L;

    // -----------------------------------------------------------------------------------------------------------------
    static final String DELIMITER = "\u001d";

    // -----------------------------------------------------------------------------------------------------------------
    KftcFinancialInstitutionCode() {
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
        if (!(obj instanceof KftcFinancialInstitutionCode)) return false;
        KftcFinancialInstitutionCode that = (KftcFinancialInstitutionCode) obj;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    // -----------------------------------------------------------------------------------------------------------------
    static KftcFinancialInstitutionCode parse(final String string) {
        final var instance = new KftcFinancialInstitutionCode();
        final var split = string.split(DELIMITER);
        instance.category = Category.valueOf(split[0]);
        instance.code = split[1];
        instance.name = split[2];
        instance.representative = Boolean.parseBoolean(split[3]);
        return instance;
    }

    String toLine() {
        return String.join(DELIMITER, category.toString(), code, name, Boolean.toString(representative));
    }

    // -----------------------------------------------------------------------------------------------------------------

    public Category getCategory() {
        return category;
    }

    void setCategory(final Category category) {
        this.category = category;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public boolean isRepresentative() {
        return representative;
    }

    void setRepresentative(boolean representative) {
        this.representative = representative;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Category category;

    private String code;

    private String name;

    private boolean representative;
}

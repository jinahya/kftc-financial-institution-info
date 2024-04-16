package com.github.jinahya.kftc.financial.institution.codes;

import lombok.*;

@Setter(AccessLevel.NONE)
@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
public class KftcFinancialInstitutionCode {

    static final String DELIMITER = "\u001d";

    static KftcFinancialInstitutionCode parse(final String string) {
        final var split = string.split(DELIMITER);
        return builder()
                .category(Category.valueOf(split[0]))
                .code(split[1])
                .name(split[2])
                .representative(Boolean.parseBoolean(split[3]))
                .build();
    }

    String toLine() {
        return String.join(DELIMITER, category.toString(), code, name, Boolean.toString(representative));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Setter(AccessLevel.PACKAGE)
    private Category category;

    @EqualsAndHashCode.Include
    private String code;

    private String name;

    private boolean representative;
}

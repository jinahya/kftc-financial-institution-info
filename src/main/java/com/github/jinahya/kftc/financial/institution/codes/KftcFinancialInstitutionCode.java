package com.github.jinahya.kftc.financial.institution.codes;

import lombok.*;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter(AccessLevel.NONE)
@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder(access = AccessLevel.PACKAGE)
public class KftcFinancialInstitutionCode {

    static final String DELIMITER = "\u001d";

    static KftcFinancialInstitutionCode parse(final String string) {
        final var split = string.split(DELIMITER);
        System.out.println("split: " + Arrays.toString(split));
        return builder()
                .category(Category.valueOf(split[0]))
                .code(split[1])
                .name(split[2])
                .representative(Boolean.parseBoolean(split[3]))
                .build();
    }

    String toLine() {
        return Stream.<String>of(category.toString(), code, name, Boolean.toString(representative))
                .collect(Collectors.joining(DELIMITER));
//        return category + "\u001d" + code + "\u001d" + name + "\u001d" + representative;
    }

    @Setter(AccessLevel.PACKAGE)
    private Category category;

    @EqualsAndHashCode.Include
    private String code;

    private String name;

    private boolean representative;
}

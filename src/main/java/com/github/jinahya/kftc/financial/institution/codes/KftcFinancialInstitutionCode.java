package com.github.jinahya.kftc.financial.institution.codes;

import lombok.*;

@Setter(AccessLevel.NONE)
@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder(access = AccessLevel.PACKAGE)
public class KftcFinancialInstitutionCode {

    @Setter(AccessLevel.PACKAGE)
    private Category category;

    @EqualsAndHashCode.Include
    private String code;

    private String name;

    private boolean representative;
}

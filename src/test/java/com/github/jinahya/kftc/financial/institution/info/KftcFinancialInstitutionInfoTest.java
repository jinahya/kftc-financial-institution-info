package com.github.jinahya.kftc.financial.institution.info;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KftcFinancialInstitutionInfoTest {

    @Test
    void __toString() {
        final var string = new KftcFinancialInstitutionInfo().toString();
        assertThat(string).isNotBlank();
    }

    @Test
    void __equals() {
        EqualsVerifier
                .simple()
                .forClass(KftcFinancialInstitutionInfo.class)
                .withIgnoredFields("category", "name", "representative")
                .verify();
    }
}
package com.github.jinahya.kftc.financial.institution.info;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KftcFinancialInstitutionBranchInfoTest {

    @Test
    void __toString() {
        final var string = new KftcFinancialInstitutionBranchInfo().toString();
        assertThat(string).isNotBlank();
    }

    @Test
    void __equals() {
        EqualsVerifier
                .simple()
                .forClass(KftcFinancialInstitutionBranchInfo.class)
                .withIgnoredFields(
                        "financialInstitutionName",
                        "branchName",
                        "phoneNumber",
                        "faxNumber",
                        "postalCode",
                        "address",
                        "status",
                        "managingBranchCode"
                )
                .verify();
    }
}
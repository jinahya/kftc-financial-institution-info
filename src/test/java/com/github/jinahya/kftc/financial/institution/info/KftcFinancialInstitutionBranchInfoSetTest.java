package com.github.jinahya.kftc.financial.institution.info;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KftcFinancialInstitutionBranchInfoSetTest {

    @DisplayName("newInstance().getList()")
    @Test
    void _getList_newInstance() {
        // ------------------------------------------------------------------------------------------------------- given
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        // -------------------------------------------------------------------------------------------------------- when
        final var list = instance.getList();
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(list).isNotNull().isNotEmpty().doesNotContainNull();
    }
}
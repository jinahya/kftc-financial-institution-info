package com.github.jinahya.kftc.financial.institution.codes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ReadmeTest {

    @Test
    void __1() {
        final var info = KftcFinancialInstitutionInfoSet.getInstance().get("001");
        assert info != null;
        assert info.getCode().equals("001");
        assert info.getName().equals("한국은행");
    }

    @Test
    void __2() {
        final var info = KftcFinancialInstitutionBranchInfoSet.getInstance().get("0010003");
        assert info != null;
        assert info.getBranchCode().equals("0010003");
        assert info.getFinancialInstitutionName().equals("한국");
    }
}

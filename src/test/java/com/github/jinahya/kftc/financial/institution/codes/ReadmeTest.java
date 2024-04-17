package com.github.jinahya.kftc.financial.institution.codes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ReadmeTest {

    @Test
    void __1() {
        {
            final var code = KftcFinancialInstitutionCodes.getCodeList()
                    .stream()
                    .filter(c -> c.getCode().equals("001"))
                    .findFirst()
                    .orElse(null);
            assert code != null;
            assert code.getCode().equals("001");
            assert code.getName().equals("한국은행");
        }
        {
            final var code = KftcFinancialInstitutionCodes.getCodeList()
                    .stream()
                    .filter(c -> c.getName().equals("산업은행") && c.isRepresentative())
                    .findFirst()
                    .orElse(null);
            assert code != null;
            assert code.getCode().equals("002");
            assert code.getName().equals("산업은행");
        }
    }

    @Test
    void __2() {
        KftcFinancialInstitutionCodes.getCodeList().stream()
                .filter(c -> c.getCategory() == KftcFinancialInstitutionCategory.INSU) // 보험사
                .forEach(c -> {
                    log.debug("code: {}", c);
                });
    }
}

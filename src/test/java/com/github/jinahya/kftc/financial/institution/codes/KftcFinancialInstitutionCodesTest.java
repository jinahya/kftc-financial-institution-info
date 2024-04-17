package com.github.jinahya.kftc.financial.institution.codes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionCodesTest {

    @Test
    void applyCodeStream__() {
        KftcFinancialInstitutionCodes.applyCodeStream(s -> {
            s.forEach(c -> {
                log.debug("code: {}", c);
            });
            return null;
        });
    }

    @Test
    void getCodeList__() {
        final var list = KftcFinancialInstitutionCodes.getCodeList();
        assertThat(list)
                .isNotEmpty()
                .isSortedAccordingTo(Comparator.comparing(KftcFinancialInstitutionCode::getCode));
    }

    @Test
    void getCodeMap__() {
        final var map = KftcFinancialInstitutionCodes.getCodeMap();
    }
}

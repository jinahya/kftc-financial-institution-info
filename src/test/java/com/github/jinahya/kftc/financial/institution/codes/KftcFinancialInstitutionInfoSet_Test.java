package com.github.jinahya.kftc.financial.institution.codes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionInfoSet_Test {

    @Test
    void __() {
        final var instance = KftcFinancialInstitutionInfoSet.getInstance();
        assertThat(instance).isNotNull();
        assertThat(instance.getMap()).isNotEmpty();
        assertThat(instance.getMap().get("001")).satisfies(i -> {
            log.debug("i: {}", i);
            assertThat(i.getCode()).isEqualTo("001");
            assertThat(i.getName()).isEqualTo("한국은행");
        });
        assertThat(instance.get("001")).satisfies(i -> {
            assertThat(i.getCode()).isEqualTo("001");
            assertThat(i.getName()).isEqualTo("한국은행");
        });
    }
}

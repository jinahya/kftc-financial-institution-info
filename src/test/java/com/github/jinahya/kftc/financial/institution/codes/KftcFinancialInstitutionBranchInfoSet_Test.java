package com.github.jinahya.kftc.financial.institution.codes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionBranchInfoSet_Test {

    @Test
    void __Agency() {
        final var instance = KftcFinancialInstitutionBranchInfoSet.getInstance();
        instance.getMap().forEach((k, v) -> {
            Optional.ofNullable(v.getManagingBranchCode()).ifPresent(mbc -> {
                final var managingBranch = instance.getMap().get(mbc);
                if (managingBranch == null) {
                    log.warn("managingBranch not found: {}", mbc);
                }
            });
        });
    }

    @Test
    void __() {
        final var instance = KftcFinancialInstitutionBranchInfoSet.getInstance();
        assertThat(instance).isNotNull();
        assertThat(instance.getMap()).isNotEmpty();
        assertThat(instance.getMap().get("0010003")).satisfies(i -> {
            log.debug("i: {}", i);
            assertThat(i.getFinancialInstitutionName()).isEqualTo("한국");
            assertThat(i.getBranchName()).isEqualTo("본부총괄");
            assertThat(i.getPhoneNumber()).isEqualTo("02  759 4114");
            assertThat(i.getFaxNumber()).isEqualTo("02  759 4060");
            assertThat(i.getPostalCode()).isEqualTo("100794");
            assertThat(i.getAddress()).isEqualTo("서울특별시 중구 남대문로 39");
            assertThat(i.getStatus()).isEqualTo("정상");
            assertThat(i.getManagingBranchCode()).isNull();
        });
        assertThat(instance.get("0010003")).satisfies(i -> {
            log.debug("i: {}", i);
            assertThat(i.getFinancialInstitutionName()).isEqualTo("한국");
            assertThat(i.getBranchName()).isEqualTo("본부총괄");
            assertThat(i.getPhoneNumber()).isEqualTo("02  759 4114");
            assertThat(i.getFaxNumber()).isEqualTo("02  759 4060");
            assertThat(i.getPostalCode()).isEqualTo("100794");
            assertThat(i.getAddress()).isEqualTo("서울특별시 중구 남대문로 39");
            assertThat(i.getStatus()).isEqualTo("정상");
            assertThat(i.getManagingBranchCode()).isNull();
        });
    }
}

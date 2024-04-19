package com.github.jinahya.kftc.financial.institution.codes;

/*-
 * #%L
 * kftc-financial-institution-info
 * %%
 * Copyright (C) 2024 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
        assertThat(instance.get("0010003")).hasValueSatisfying(i -> {
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

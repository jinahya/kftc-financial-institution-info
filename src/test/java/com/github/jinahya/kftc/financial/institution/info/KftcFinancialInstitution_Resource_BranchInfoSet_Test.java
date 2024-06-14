package com.github.jinahya.kftc.financial.institution.info;

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

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitution_Resource_BranchInfoSet_Test
        extends KftcFinancialInstitution_Resource__Test {

    @Test
    void __Agency() {
        final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
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
        final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
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
        // -------------------------------------------------------------------------------------------------- branchCode
        assertThat(
                instance.getList().stream()
                        .map(KftcFinancialInstitutionBranchInfo::getBranchCode)
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v))
                    .doesNotContainPattern(KftcFinancialInstitutionBranchInfo.PATTERN_MULTIPLE_WHITESPACES)
            ;
        });
        // ------------------------------------------------------------------------------------ financialInstitutionName
        assertThat(
                instance.getList().stream()
                        .map(KftcFinancialInstitutionBranchInfo::getFinancialInstitutionName)
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v))
            ;
        });
        // -------------------------------------------------------------------------------------------------- branchName
        assertThat(
                instance.getList().stream()
                        .map(KftcFinancialInstitutionBranchInfo::getBranchName)
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v))
            ;
        });
        // ------------------------------------------------------------------------------------------------- phoneNumber
        assertThat(
                instance.getList().stream()
                        .map(KftcFinancialInstitutionBranchInfo::getPhoneNumber)
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v))
            ;
        });
        // ---------=----------------------------------------------------------------------------- phoneNumberNormalized
        assertThat(
                instance.getList().stream()
                        .map(v -> v.getPhoneNumberNormalized("-"))
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .doesNotContainAnyWhitespaces()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v))
            ;
        });
        // --------------------------------------------------------------------------------------------------- faxNumber
        assertThat(
                instance.getList().stream()
                        .map(KftcFinancialInstitutionBranchInfo::getFaxNumber)
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v));
        });
        // ----------------------------------------------------------------------------------------- faxNumberNormalized
        assertThat(
                instance.getList().stream()
                        .map(v -> v.getFaxNumberNormalized("-"))
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v))
                    .doesNotContainAnyWhitespaces()
            ;
        });
        // -------------------------------------------------------------------------------------------------- postalCode
        assertThat(
                instance.getList().stream()
                        .map(KftcFinancialInstitutionBranchInfo::getPostalCode)
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v))
            ;
        });
        // ----------------------------------------------------------------------------------------------------- address
        assertThat(
                instance.getList().stream()
                        .map(KftcFinancialInstitutionBranchInfo::getAddress)
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v))
            ;
        });
        // ------------------------------------------------------------------------------------------- addressNormalized
        assertThat(
                instance.getList().stream()
                        .map(e -> e.getAddressNormalized(" "))
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v))
                    .doesNotContain("  ")
            ;
        });
        // ------------------------------------------------------------------------------------------------------ status
        assertThat(
                instance.getList().stream()
                        .map(KftcFinancialInstitutionBranchInfo::getStatus)
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v));
        });
        // ------------------------------------------------------------------------------------------ managingBranchCode
        assertThat(
                instance.getList().stream()
                        .map(KftcFinancialInstitutionBranchInfo::getManagingBranchCode)
                        .filter(Objects::nonNull)
        ).allSatisfy(v -> {
            assertThat(v)
                    .isNotBlank()
                    .satisfies(x -> assertThat(x.strip()).isEqualTo(v));
        });
    }

    @Test
    void _infoSet_branchCode() {
        final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
        final var branchInfoSet = KftcFinancialInstitutionBranchInfoSet.newInstance();
        branchInfoSet.getList().forEach(b -> {
            final var branchCode = b.getBranchCode();
            final var code = branchCode.substring(0, 3);
            final var info = infoSet.get(code).orElse(null);
//            if (info == null && !code.startsWith("0")) {
            if (info == null) {
                // 099xxx: 금융결제원
                // 4920018/중소벤처기업진흥공단/성장융합금융처
                log.warn("no info for {}/{}/{}", b.getBranchCode(), b.getFinancialInstitutionName(), b.getBranchName());
                return;
            }
        });
    }
}

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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Readme1Test {

    @Test
    void __001() {
        final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
        final var info = infoSet.map().get("001");
        assertThat(info).satisfies(i -> {
            assertThat(i.getCategory()).isSameAs(KftcFinancialInstitutionCategory.BANK);
            assertThat(i.getCode()).isEqualTo("001");
            assertThat(i.getName()).isEqualTo("한국은행");
            assertThat(i.isRepresentative()).isTrue();
        });
    }

    @Test
    void __101() {
        final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
        final var info = infoSet.map().get("101");
        assertThat(info).isNotNull().satisfies(i -> {
            assertThat(i.getCategory()).isSameAs(KftcFinancialInstitutionCategory.OTHE);
            assertThat(i.getCode()).isEqualTo("101");
            assertThat(i.getName()).isEqualTo("한국신용정보원");
            assertThat(i.isRepresentative()).isTrue();
        });
    }

    @EnumSource(KftcFinancialInstitutionCategory.class)
    @ParameterizedTest
    void __BANK(KftcFinancialInstitutionCategory category) {
        final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
        infoSet.list().stream()
                .filter(i -> i.getCategory() == category)
                .filter(KftcFinancialInstitutionInfo::isRepresentative)
                .sorted(KftcFinancialInstitutionInfo.COMPARING_CODE)
                .forEach(i -> {
                    log.debug("{}: {} {}", category.name(), i.getCode(), i.getName());
                });
    }
}

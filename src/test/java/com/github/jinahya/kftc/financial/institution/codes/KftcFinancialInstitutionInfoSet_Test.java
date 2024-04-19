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
        assertThat(instance.get("001")).hasValueSatisfying(i -> {
            assertThat(i.getCode()).isEqualTo("001");
            assertThat(i.getName()).isEqualTo("한국은행");
        });
    }
}

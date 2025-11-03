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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitution_InfoSet_Test {

    // -----------------------------------------------------------------------------------------------------------------
    @Nested
    class NewInstance_Test {

        @Test
        void __() {
            final var result = KftcFinancialInstitutionInfoSet.newInstance();
            assertThat(result).isNotNull();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void getInstance1__() {
        final var result1 = KftcFinancialInstitutionInfoSet.getInstance1();
        log.debug("result1: {}", result1);
        assertThat(result1).isNotNull();
        if (ThreadLocalRandom.current().nextBoolean()) {
            System.gc();
        }
        final var result2 = KftcFinancialInstitutionInfoSet.getInstance1();
        log.debug("result2: {}", result2);
        assertThat(result2).isNotNull();
        // result2 may or may not same as result1
        assertThat(result2).satisfiesAnyOf(
                r -> {
                    assertThat(r).isSameAs(result1);
                },
                r -> {
                    assertThat(r).isNotSameAs(result1);
                }
        );
    }

    @Test
    void getInstance2__() {
        final var result1 = KftcFinancialInstitutionInfoSet.getInstance2();
        log.debug("result1: {}", result1);
        assertThat(result1).isNotNull();
        if (ThreadLocalRandom.current().nextBoolean()) {
            System.gc();
        }
        final var result2 = KftcFinancialInstitutionBranchInfoSet.getInstance2();
        log.debug("result2: {}", result2);
        assertThat(result2).isNotNull();
        // result2 may or may not same as result1
        assertThat(result2).satisfiesAnyOf(
                r -> {
                    assertThat(r).isSameAs(result1);
                },
                r -> {
                    assertThat(r).isNotSameAs(result1);
                }
        );
    }

    @Test
    void applyInstance__() {
        final var result1 = KftcFinancialInstitutionInfoSet.applyInstance(Function.identity());
        log.debug("result1: {}", result1);
        assertThat(result1).isNotNull();
        if (ThreadLocalRandom.current().nextBoolean()) {
            System.gc();
        }
        final var result2 = KftcFinancialInstitutionBranchInfoSet.applyInstance(Function.identity());
        log.debug("result2: {}", result2);
        assertThat(result2).isNotNull();
        // result2 may or may not same as result1
        assertThat(result2).satisfiesAnyOf(
                r -> {
                    assertThat(r).isSameAs(result1);
                },
                r -> {
                    assertThat(r).isNotSameAs(result1);
                }
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("list()")
    @Nested
    class List_Test {

        @Test
        void __() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = KftcFinancialInstitutionInfoSet.newInstance();
            // ---------------------------------------------------------------------------------------------------- when
            final var list = instance.list();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(list).isNotNull().isNotEmpty().doesNotContainNull();
        }
    }

    @DisplayName("map()")
    @Nested
    class Map_Test {

        @Test
        void __() {
            // --------------------------------------------------------------------------------------------------- given
            final var instance = KftcFinancialInstitutionInfoSet.newInstance();
            // ---------------------------------------------------------------------------------------------------- when
            final var map = instance.map();
            // ---------------------------------------------------------------------------------------------------- then
            assertThat(map).isNotNull().isNotEmpty().allSatisfy((k, v) -> {
                assertThat(k).isNotNull();
                assertThat(v).isNotNull();
            });
        }
    }
}

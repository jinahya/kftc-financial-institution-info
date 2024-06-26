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

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.function.Function;

@Slf4j
abstract class KftcFinancialInstitution_Persistence__Test {

    private static final String PERSISTENCE_UNIT_NAME = "kftcFinancialInstitutionInfoPU";

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeAll
    static void createEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @AfterAll
    static void closeEntityManagerFactory() {
        ENTITY_MANAGER_FACTORY.close();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void __() {
        log.debug("entityManagerFactory: {}", ENTITY_MANAGER_FACTORY);
    }

    // -------------------------------------------------------------------------------------------- entityManagerFactory

    <R> R applyEntityManager(final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        try (var entityManager = ENTITY_MANAGER_FACTORY.createEntityManager()) {
            return function.apply(entityManager);
        }
    }
}

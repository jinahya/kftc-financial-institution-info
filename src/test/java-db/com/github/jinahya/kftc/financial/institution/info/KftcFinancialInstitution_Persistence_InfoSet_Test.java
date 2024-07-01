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

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitution_Persistence_InfoSet_Test
        extends KftcFinancialInstitution_Persistence__Test {

    private static final String ENTITY_NAME = KftcFinancialInstitutionInfo.class.getSimpleName();

    @Test
    void __() {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        acceptEntityManager(em -> {
            final var transaction = em.getTransaction();
            try {
                transaction.begin();
//                final int removed = em
//                        .createQuery("DELETE FROM " + KftcFinancialInstitutionInfo.class.getSimpleName())
//                        .executeUpdate();
//                log.debug("removed: {}", removed);
                instance.getList().forEach(em::persist);
                transaction.commit();
            } catch (final Exception e) {
                log.error("failed to update", e);
                transaction.rollback();
            }
        });
        final var copy = new ArrayList<>(instance.getList());
        acceptEntityManager(em -> {
            em.createQuery("""
                                   SELECT e
                                   FROM %1$s AS e""".formatted(ENTITY_NAME))
                    .getResultList()
                    .forEach(e -> {
                        assertThat(copy.remove(e)).isTrue();
                    });
        });
        assertThat(copy).isEmpty();
    }
}

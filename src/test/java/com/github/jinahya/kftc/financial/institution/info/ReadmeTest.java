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

@Slf4j
class ReadmeTest {

    @Test
    void __1() {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final var info = instance.get("001").orElseThrow();
        assert info.getCode().equals("001");
        assert info.getName().equals("한국은행");
    }

    @Test
    void __2() {
        final var instance = KftcFinancialInstitutionBranchInfoSet.getInstance();
        final var info = instance.get("0010003").orElseThrow();
        assert info.getBranchCode().equals("0010003");
        assert info.getFinancialInstitutionName().equals("한국");
    }
}

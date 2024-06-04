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

@Slf4j
class ReadmeTest {

    @Test
    void __1() {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final var info = instance.get("001").orElseThrow();
        assert info.getCategory() == KftcFinancialInstitutionCategory.BANK;
        assert Objects.equals(info.getCode(), "001");
        assert Objects.equals(info.getName(), "한국은행");
        assert info.isRepresentative();
    }

    @Test
    void __2() {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final var info = instance.get("094").orElseThrow();
        assert info.getCategory() == KftcFinancialInstitutionCategory.MISC;
        assert info.getCode().equals("094");
        assert info.getName().equals("서울보증보험");
        assert info.isRepresentative();
    }

    @Test
    void __3() {
        final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var info = instance.get("0010003").orElseThrow();
        assert Objects.equals(info.getBranchCode(), "0010003");
        assert Objects.equals(info.getFinancialInstitutionName(), "한국");
        assert Objects.equals(info.getBranchName(), "본부총괄");
        assert Objects.equals(info.getPhoneNumber(), "02  759 4114"); // mind multiple spaces
        assert Objects.equals(info.getPhoneNumberNormalized(" "), "02 759 4114");
        assert Objects.equals(info.getPhoneNumberNormalized("-"), "02-759-4114");
        assert Objects.equals(info.getFaxNumber(), "02  759 4060");   // mind multiple spaces
        assert Objects.equals(info.getFaxNumberNormalized(" "), "02 759 4060");
        assert Objects.equals(info.getFaxNumberNormalized("-"), "02-759-4060");
        assert Objects.equals(info.getPostalCode(), "100794");
        assert Objects.equals(info.getAddress(), "서울특별시 중구 남대문로 39");
        assert Objects.equals(info.getStatus(), "정상");
        assert info.getManagingBranchCode() == null;
    }

    @Test
    void __4() {
        final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var info = instance.get("4920018").orElseThrow();
        assert info.getBranchCode().equals("4920018");
        assert info.getFinancialInstitutionName().equals("중소벤처기업진흥공단");
        assert info.getBranchName().equals("성장융합금융처");
        assert info.getPhoneNumber().equals("02  32115603"); // mind multiple spaces
        assert info.getPhoneNumberNormalized(" ").equals("02 32115603");
        assert info.getPhoneNumberNormalized("").equals("0232115603");
        assert info.getFaxNumber().equals("0505047 4412");
        assert info.getPostalCode().equals("52851");
        assert info.getAddress().equals("경상남도 진주시 동진로 430 (충무공동) 중소벤처기업진흥공단");
        assert info.getStatus().equals("정상");
        assert info.getManagingBranchCode() == null;
    }
}

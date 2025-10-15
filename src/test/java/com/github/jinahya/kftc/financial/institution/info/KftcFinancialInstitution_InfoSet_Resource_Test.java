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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitution_InfoSet_Resource_Test
        extends _KftcFinancialInstitution_ResourceTest {

    private static final KftcFinancialInstitutionInfoSet INSTANCE = KftcFinancialInstitutionInfoSet.newInstance();

    private static List<KftcFinancialInstitutionInfo> list;

    protected static List<KftcFinancialInstitutionInfo> list() {
        if (list == null) {
            list = INSTANCE.list().stream().sorted(Comparator.comparing(KftcFinancialInstitutionInfo::getCode))
                    .toList();
        }
        return list;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @CsvSource(value = {
            "001, 한국은행",
            "077, 기술보증기금",
            "076, 신용보증기금",
            "272, NH선물",
            "273, 코리아에셋투자증권",
            "373, 제주카드",
            "372, 전북카드",
            "494, 한국자산관리공사",
    })
    @ParameterizedTest
    void __대표코드(final String code, final String name) {
        assertThat(INSTANCE.map().get(code)).satisfies(v -> {
            assertThat(v.getCode()).isEqualTo(code);
            assertThat(v.getName()).isEqualTo(name);
        });
    }

    @CsvSource(value = {
            "001, 한국은행", "042,",
            "002, 산업은행", "043, 기업은행",
            "0404,", "084, 우리은행",
            "085, 새마을금고", "089, 케이뱅크",
            "088, 신한은행", "152, 인도네시아느가라은행"
    })
    @ParameterizedTest
    void __은행(final String code, final String name) {
        final var value = INSTANCE.map().get(code);
        if (name == null) {
            assertThat(value).isNull();
            return;
        }
        assertThat(value).satisfies(v -> {
            assertThat(v.getCode()).isEqualTo(code);
            assertThat(v.getCategory()).isEqualTo(KftcFinancialInstitutionCategory.BANK);
            assertThat(v.getName()).isEqualTo(name);
        });
    }

    @CsvSource(value = {
            "209, 유안타증권", "266, SK증권",
            "265, 엘에스증권"
    })
    @ParameterizedTest
    void __금융투자회사(final String code, final String name) {
        final var value = INSTANCE.map().get(code);
        if (name == null) {
            assertThat(value).isNull();
            return;
        }
        assertThat(value).satisfies(v -> {
            assertThat(v.getCode()).isEqualTo(code);
            assertThat(v.getCategory()).isEqualTo(KftcFinancialInstitutionCategory.FIIN);
            assertThat(v.getName()).isEqualTo(name);
        });
    }

    @CsvSource(value = {
            "299, 우리금융캐피탈", "307, 애큐온캐피탈",
            "306, NH농협캐피탈", "313, 한국투자캐피탈"
    })
    @ParameterizedTest
    void __캐피탈사(final String code, final String name) {
        final var value = INSTANCE.map().get(code);
        if (name == null) {
            assertThat(value).isNull();
            return;
        }
        assertThat(value).satisfies(v -> {
            assertThat(v.getCode()).isEqualTo(code);
            assertThat(v.getCategory()).isEqualTo(KftcFinancialInstitutionCategory.CAPI);
            assertThat(v.getName()).isEqualTo(name);
        });
    }

    @CsvSource(value = {
            "041, 우리카드", "369, 수협카드",
            "368, 롯데카드"
    })
    @ParameterizedTest
    void __카드사(final String code, final String name) {
        final var value = INSTANCE.map().get(code);
        if (name == null) {
            assertThat(value).isNull();
            return;
        }
        assertThat(value).satisfies(v -> {
            assertThat(v.getCode()).isEqualTo(code);
            assertThat(v.getCategory()).isEqualTo(KftcFinancialInstitutionCategory.CARD);
            assertThat(v.getName()).isEqualTo(name);
        });
    }

    @CsvSource(value = {
            "401, 하나생명보험", "442, 현대해상",
            "438, 신한라이프생명보험", "457, DB생명보험",
            "439, KB라이프", "458, KDB생명보험",
            "441, 삼성화재", "460, 처브라이프생명보험"
    })
    @ParameterizedTest
    void __보험사(final String code, final String name) {
        final var value = INSTANCE.map().get(code);
        if (name == null) {
            assertThat(value).isNull();
            return;
        }
        assertThat(value).satisfies(v -> {
            assertThat(v.getCode()).isEqualTo(code);
            assertThat(v.getCategory()).isEqualTo(KftcFinancialInstitutionCategory.INSU);
            assertThat(v.getName()).isEqualTo(name);
        });
    }

    @CsvSource(value = {
            "076, 신용보증기금", "101, 한국신용정보원",
            "077, 기술보증기금", "492, 중소벤처기업진흥공단",
            "094, 서울보증보험", "494, 한국자산관리공사",
    })
    @ParameterizedTest
    void __기타(final String code, final String name) {
        final var value = INSTANCE.map().get(code);
        if (name == null) {
            assertThat(value).isNull();
            return;
        }
        assertThat(value).satisfies(v -> {
            assertThat(v.getCode()).isEqualTo(code);
            assertThat(v.getCategory()).isEqualTo(KftcFinancialInstitutionCategory.OTHE);
            assertThat(v.getName()).isEqualTo(name);
        });
    }
}

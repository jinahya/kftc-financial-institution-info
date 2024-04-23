/**
 * Provides classes for accessing information of financial institutions provided by <a
 * href="https://www.kftc.or.kr">KFTC</a>
 * <p>
 * {@snippet lang = "java":
 * // 금융기관 정보
 * final var instance = KftcFinancialInstitutionInfoSet.newInstance();
 * final var info = instance.get("001").orElseThrow();
 * assert info.getCategory() == KftcFinancialInstitutionCategory.BANK;
 * assert info.getCode().equals("001");
 * assert info.getName().equals("한국은행");
 * assert info.isRepresentative();
 *}
 * {@snippet lang = "java":
 * // 금융기관 지점 정보 조회
 * final var instance = KftcFinancialInstitutionBranchInfoSet.newInstance();
 * final var info = instance.get("0010003").orElseThrow();
 * assert info.getBranchCode().equals("0010003");
 * assert info.getFinancialInstitutionName().equals("한국");
 * assert info.getBranchName().equals("본부총괄");
 * assert info.getPhoneNumber().equals("02  759 4114"); // mind multiple spaces
 * assert info.getFaxNumber().equals("02  759 4060");   // mind multiple spaces
 * assert info.getPostalCode().equals("100794");
 * assert info.getAddress().equals("서울특별시 중구 남대문로 39");
 * assert info.getStatus().equals("정상");
 * assert info.getManagingBranchCode() == null;
 *}
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
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

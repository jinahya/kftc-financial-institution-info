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

/**
 * Defines packages for accessing information of financial institutions provided by <a
 * href="https://www.kftc.or.kr">융결재원 (KFTC)</a>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://www.kftc.or.kr">금융결재원(KFTC)</a>
 * @see <a href="https://www.kftc.or.kr/kftc/data/EgovBankList.do">금융회사코드 조회</a>
 * @see <a href="https://github.com/jinahya/kftc-financial-institution-info">GitHub</a>
 */
module com.github.jinahya.kftc.financial.institution.info {
    requires transitive java.sql;
    requires static lombok;   // provided
    exports com.github.jinahya.kftc.financial.institution.info;
}

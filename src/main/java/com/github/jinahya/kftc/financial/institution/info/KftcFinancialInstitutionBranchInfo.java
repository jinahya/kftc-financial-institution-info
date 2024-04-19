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

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a financial institution branch managed by <a href="https://www.kftc.or.kr">KFTC</a>.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see KftcFinancialInstitutionBranchInfoSet
 */
public final class KftcFinancialInstitutionBranchInfo
        implements Serializable {

    private static final long serialVersionUID = -5372735657084376790L;

    // ---------------------------------------------------------------------------------------------------------- status

    public static final String STATUS_RAW_VALUE_ACTIVE = "정상";

    public static final String STATUS_RAW_VALUE_TEMPORARILY_CLOSED = "잠정폐쇄";

    /**
     * Predefined constants for mapping {@code status}.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public enum Status {

        /**
         * A value for {@value #STATUS_RAW_VALUE_ACTIVE}.
         */
        ACTIVE("정상"),

        /**
         * A value for {@value #STATUS_RAW_VALUE_TEMPORARILY_CLOSED}.
         */
        TEMPORARILY_CLOSED("잠정폐쇄");

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Returns the value whose {@link #getRawValue() rawValue} matches specified text value.
         *
         * @param rawValue the value for the {@link #rawValue} to match.
         * @return the value matched to {@code rawValue}.
         */
        public static Status valueOfRawValue(final String rawValue) {
            for (final Status value : Status.values()) {
                if (Objects.equals(value.rawValue, rawValue)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("no value for the rawValue: " + rawValue);
        }

        // ------------------------------------------------------------------------------------------------ CONSTRUCTORS
        Status(final String rawValue) {
            this.rawValue = rawValue;
        }

        // ---------------------------------------------------------------------------------------------------- rawValue

        /**
         * Returns the {@code rawValue} of this value.
         *
         * @return the {@code rawValue} of this value.
         */
        public String getRawValue() {
            return rawValue;
        }

        // -------------------------------------------------------------------------------------------------------------
        private final String rawValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static String string(String string) {
        if (string == null) {
            return null;
        }
        string = _StringUtils.half(string.strip());
        if (string.isEmpty() || string.isBlank()) {
            return null;
        }
        return string;
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{' +
                "name=" + financialInstitutionName +
                ",branchName=" + branchName +
                ",branchCode=" + branchCode +
                ",postalCode=" + postalCode +
                ",address=" + address +
                ",phoneNumber=" + phoneNumber +
                ",faxNumber=" + faxNumber +
                ",status=" + status +
                ",agency=" + managingBranchCode +
                '}';
    }

    // ------------------------------------------------------------------------------------------------------ branchCode

    /**
     * {@code 지점코드} 값을 반환한다.
     *
     * @return {@code 지점코드} 값을 반환한다.
     */
    public String getBranchCode() {
        return branchCode;
    }

    void setBranchCode(String branchCode) {
        this.branchCode = string(branchCode);
    }

    // ---------------------------------------------------------------------------------------- financialInstitutionName

    /**
     * {@code 금융회사명} 값을 반환한다.
     *
     * @return {@code 금융회사명} 값을 반환한다.
     */
    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    void setFinancialInstitutionName(final String financialInstitutionName) {
        this.financialInstitutionName = string(financialInstitutionName);
    }

    // ------------------------------------------------------------------------------------------------------ branchName

    /**
     * {@code 지점명} 값을 반환한다.
     *
     * @return {@code 지점명} 값을 반환한다.
     */
    public String getBranchName() {
        return branchName;
    }

    void setBranchName(final String branchName) {
        this.branchName = string(branchName);
    }

    // ----------------------------------------------------------------------------------------------------- phoneNumber

    /**
     * {@code 전화번호} 값을 반환한다.
     *
     * @return {@code 전화번호} 값을 반환한다.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = string(phoneNumber);
    }

    // ------------------------------------------------------------------------------------------------------- faxNumber

    /**
     * {@code 팩스번호} 값을 반환한다.
     *
     * @return {@code 팩스번호} 값을 반환한다.
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    void setFaxNumber(final String faxNumber) {
        this.faxNumber = string(faxNumber);
    }

    // ------------------------------------------------------------------------------------------------------ postalCode

    /**
     * {@code 우편번호} 값을 반환한다.
     *
     * @return {@code 우편번호} 값을 반환한다.
     */
    public String getPostalCode() {
        return postalCode;
    }

    void setPostalCode(final String postalCode) {
        this.postalCode = string(postalCode);
    }

    // --------------------------------------------------------------------------------------------------------- address

    /**
     * {@code 주소} 값을 반환한다.
     *
     * @return {@code 주소} 값을 반환한다.
     */
    public String getAddress() {
        return address;
    }

    void setAddress(final String address) {
        this.address = string(address);
    }

    // ---------------------------------------------------------------------------------------------------------- status

    /**
     * {@code 상태(구분)} 값을 반환한다.
     *
     * @return {@code 상태(구분)} 값을 반환한다.
     */
    public String getStatus() {
        return status;
    }

    void setStatus(final String status) {
        this.status = string(status);
    }

    // ---------------------------------------------------------------------------------------------- managingBranchCode

    /**
     * {@code 폐쇄관리지점} 값을 반환한다.
     *
     * @return {@code 폐쇄관리지점} 값을 반환한다.
     */
    public String getManagingBranchCode() {
        return managingBranchCode;
    }

    void setManagingBranchCode(final String managingBranchCode) {
        this.managingBranchCode = string(managingBranchCode);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 지점코드.
     */
    private String branchCode;

    /**
     * 금융회사명.
     */
    private String financialInstitutionName;

    /**
     * 지점멍.
     */
    private String branchName;

    /**
     * 전화번호.
     */
    private String phoneNumber;

    /**
     * 팩스번호.
     */
    private String faxNumber;

    /**
     * 우편번호.
     */
    private String postalCode;

    /**
     * 주소.
     */
    private String address;

    /**
     * 구분.
     */
    private String status;

    /**
     * 폐쇄관리지점.
     */
    private String managingBranchCode;
}
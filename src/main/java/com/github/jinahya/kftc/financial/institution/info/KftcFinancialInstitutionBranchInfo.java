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
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Represents a financial institution branch managed by <a href="https://www.kftc.or.kr">KFTC</a>.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @implSpec Instances of this class are unmodifiable and thread-safe.
 * @see KftcFinancialInstitutionBranchInfoSet
 */
public final class KftcFinancialInstitutionBranchInfo
        implements Serializable, _Info {

    private static final long serialVersionUID = -5372735657084376790L;

    // ---------------------------------------------------------------------------------------------------------- status

    private static final String STATUS_RAW_VALUE_ACTIVE = "정상";

    private static final String STATUS_RAW_VALUE_TEMPORARILY_CLOSED = "잠정폐쇄";

    /**
     * Predefined constants for mapping {@code status}.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public enum Status {

        /**
         * A value for {@code 정상}.
         */
        ACTIVE(STATUS_RAW_VALUE_ACTIVE),

        /**
         * A value for {@code 잠정폐쇄}.
         */
        TEMPORARILY_CLOSED(STATUS_RAW_VALUE_TEMPORARILY_CLOSED);

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

        // -------------------------------------------------------------------------------------- STATIC_FACTORY_METHODS

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

    static final Pattern PATTERN_MULTIPLE_WHITESPACES = Pattern.compile("\\s+");

    private static String replaceAllMultipleWhitespaces(final String text, final String replacement) {
        Objects.requireNonNull(text, "text is null");
        return PATTERN_MULTIPLE_WHITESPACES.matcher(text).replaceAll(replacement);
    }

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    KftcFinancialInstitutionBranchInfo() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + '{'
                + "branchCode=" + branchCode
                + ",financialInstitutionName=" + financialInstitutionName
                + ",branchName=" + branchName
                + ",phoneNumber=" + phoneNumber
                + ",faxNumber=" + faxNumber
                + ",postalCode=" + postalCode
                + ",address=" + address
                + ",status=" + status
                + ",managingBranchCode=" + managingBranchCode
                + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KftcFinancialInstitutionBranchInfo)) {
            return false;
        }
        final var that = (KftcFinancialInstitutionBranchInfo) obj;
        return Objects.equals(branchCode, that.branchCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(branchCode);
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

    public String getPhoneNumberNormalized(final String whitespacesReplacement) {
        Objects.requireNonNull(whitespacesReplacement, "whitespacesReplacement is null");
        return Optional.ofNullable(getPhoneNumber())
                .map(v -> replaceAllMultipleWhitespaces(v, whitespacesReplacement))
                .orElse(null);
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

    public String getFaxNumberNormalized(final String whitespacesReplacement) {
        Objects.requireNonNull(whitespacesReplacement, "whitespacesReplacement is null");
        return Optional.ofNullable(getFaxNumber())
                .map(v -> replaceAllMultipleWhitespaces(v, whitespacesReplacement))
                .orElse(null);
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

    /**
     * Returns the value of {@link #getAddress()} as its all consecutive white spaces are replaced with specified
     * value.
     *
     * @param whitespacesReplacement the value for each consecutive white spaces.
     * @return the value of {@link #getAddress()} as normalized.
     */
    public String getAddressNormalized(final String whitespacesReplacement) {
        Objects.requireNonNull(whitespacesReplacement, "whitespacesReplacement is null");
        return Optional.ofNullable(getAddress())
                .map(v -> replaceAllMultipleWhitespaces(v, whitespacesReplacement))
                .orElse(null);
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

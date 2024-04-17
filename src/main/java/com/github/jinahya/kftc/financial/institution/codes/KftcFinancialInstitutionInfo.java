package com.github.jinahya.kftc.financial.institution.codes;

import java.io.Serializable;

/**
 * Represents financial company info designated by <a href="https://www.kftc.or.kr">KFTC</a>.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class KftcFinancialInstitutionInfo
        implements Serializable {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 금융회사명.
     */
    private String name;

    /**
     * 지점멍.
     */
    private String branchName;

    /**
     * 지점코드.
     */
    private String branchCode;

    /**
     * 우편번호.
     */
    private String postalCode;

    /**
     * 주소.
     */
    private String address;

    /**
     * 전화번호.
     */
    private String phoneNumber;

    /**
     * 팩스번호.
     */
    private String faxNumber;

    /**
     * 구분.
     */
    private String status;

    /**
     * 폐쇄관리지점.
     */
    private String agency;
}

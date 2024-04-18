/**
 * Provides class for accessing information of financial institutions provided by <a
 * href="https://www.kftc.or.kr">KFTC</a>
 * <p>
 * {@snippet lang = java:
 * final var info = KftcFinancialInstitutionInfoSet.getInstance().get("001");
 * assert info != null;
 * assert info.getCode().equals("001");
 * assert info.getName().equals("한국은행");
 *}
 * {@snippet lang = java:
 * final var info = KftcFinancialInstitutionBranchInfoSet.getInstance().get("0010003");
 * assert info != null;
 * assert info.getBranchCode().equals("0010003");
 * assert info.getFinancialInstitutionName().equals("한국");
 *}
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
package com.github.jinahya.kftc.financial.institution.codes;

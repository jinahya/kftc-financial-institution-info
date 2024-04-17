/**
 * Bound codes and additional info assigned by <a href="https://www.kftc.or.kr">KFTC</a>
 * <p>
 * {@snippet lang = java:
 *  final var code = KftcFinancialInstitutionCodes.getCodeList()
 *          .stream()
 *          .filter(c -> c.getCode().equals("001"))
 *          .findFirst()
 *          .orElse(null);
 *  assert code != null;
 *  assert code.getCode().equals("001");
 *  assert code.getName().equals("한국은행");
 *}
 * {@snippet lang = java:
 *  final var code = KftcFinancialInstitutionCodes.getCodeMap().get("001")
 *  assert code != null;
 *  assert code.getCode().equals("001");
 *  assert code.getName().equals("한국은행");
 *}
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
package com.github.jinahya.kftc.financial.institution.codes;

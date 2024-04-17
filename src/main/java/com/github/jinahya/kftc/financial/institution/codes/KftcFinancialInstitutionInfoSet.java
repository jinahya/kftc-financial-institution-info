package com.github.jinahya.kftc.financial.institution.codes;

import java.io.Serializable;

/**
 * A class for accessing financial company info designated by <a href="https://www.kftc.or.kr">KFTC</a>.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class KftcFinancialInstitutionInfoSet
        implements Serializable {

    private KftcFinancialInstitutionInfoSet() {
        throw new AssertionError("instantiation is not allowed");
    }
}

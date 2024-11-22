package com.github.jinahya.kftc.financial.institution.info;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;

@Slf4j
class KftcFinancialInstitution_Ndjson_BranchInfoSet_Test
        extends KftcFinancialInstitution_Ndjson__Test {

    @Test
    void __() throws IOException {
        final var infoSet = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var list = infoSet.getList().stream()
                .sorted(Comparator.comparing(KftcFinancialInstitutionBranchInfo::getBranchCode))
                .toList();
        final var path = _IoTestUtils.buildOutputFile("codefilex.ndjson");
        writeValues(list, path.toFile());
    }
}

package com.github.jinahya.kftc.financial.institution.info;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

@Slf4j
class KftcFinancialInstitution_Json_BranchInfoSet_Test
        extends KftcFinancialInstitution_Json__Test {

    @Test
    void __() throws IOException {
        final var infoSet = KftcFinancialInstitutionBranchInfoSet.newInstance();
        final var list = infoSet.getList();
        {
            final var string = new ObjectMapper().writeValueAsString(list);
            final var path = _IoTestUtils.buildOutputFile("codefilex.json");
            Files.writeString(path, string);
        }
        {
            final var string = prettyPrinter(new ObjectMapper()).writeValueAsString(list);
            final var path = _IoTestUtils.buildOutputFile("codefilex_formatted.json");
            Files.writeString(path, string);
        }
    }
}

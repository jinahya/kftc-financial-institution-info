package com.github.jinahya.kftc.financial.institution.info;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;

@Slf4j
class KftcFinancialInstitution_Json_InfoSet_Test
        extends KftcFinancialInstitution_Json__Test {

    @Test
    void __() throws IOException {
        final var infoSet = KftcFinancialInstitutionInfoSet.newInstance();
        final var list = infoSet.getList().stream().sorted(Comparator.comparing(KftcFinancialInstitutionInfo::getCode)).toList();
        {
            final var string = new ObjectMapper().writeValueAsString(list);
            final var path = _IoTestUtils.buildOutputFile("bankinfo.json");
            Files.writeString(path, string);
        }
        {
            final var string = prettyPrinter(new ObjectMapper()).writeValueAsString(list);
            final var path = _IoTestUtils.buildOutputFile("bankinfo_formatted.json");
            Files.writeString(path, string);
        }
    }
}

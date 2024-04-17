package com.github.jinahya.kftc.financial.institution.codes;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionInfoSet_Resource_Test {

    @Test
    void __() throws IOException, URISyntaxException {
        final var resource = getClass().getResource("/codefilex.text");
        assertThat(resource).isNotNull();
        final var path = Paths.get(resource.toURI());
        final var charset = Charset.forName("EUC-KR");
        try (var lines = Files.lines(Paths.get(resource.toURI()), charset)) {
            log.debug("lines: {}", lines.count());
        }
        try (var lines = Files.lines(Paths.get(resource.toURI()), charset)) {
            lines.forEach(l -> {
//                log.debug("line: {}", l);
                final var tokens = l.split("\\|");
                assertThat(tokens).hasSize(9);
                for (final var token : tokens) {
//                    log.debug("\ttoken: {}", token);
                }
            });
        }
    }
}

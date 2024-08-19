package com.github.jinahya.kftc.financial.institution.info;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

abstract class KftcFinancialInstitution_Ndjson__Test {

    // https://cowtowncoder.medium.com/line-delimited-json-with-jackson-69c9e4cb6c00
    static void writeValues(final Iterable<?> values, final File file) throws IOException {
        try (var writer = new ObjectMapper().writer()
                .withRootValueSeparator("\n") // Important! Default value separator is single space
                .writeValues(file)) {
            values.forEach(v -> {
                try {
                    writer.write(v);
                } catch (final IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            });
        }
    }
}

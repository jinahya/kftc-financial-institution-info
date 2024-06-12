package com.github.jinahya.kftc.financial.institution.info;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

abstract class KftcFinancialInstitution_Json__Test {

    static ObjectWriter prettyPrinter(final ObjectMapper mapper) {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        final var printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        return mapper.writer(printer);
    }
}

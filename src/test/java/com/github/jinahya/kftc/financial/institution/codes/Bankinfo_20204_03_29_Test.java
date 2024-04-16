package com.github.jinahya.kftc.financial.institution.codes;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Bankinfo_20204_03_29_Test {

    private static final Pattern pattern = Pattern.compile("(\\d{3})\\s([\\p{L}\s]+)?\\s?(\\d{3})?\\s?([\\p{L}\s]+)?");

    static void parse(final boolean[] representatives, final Category[] categories, final String text,
                      Map<String, KftcFinancialInstitutionCode> map)
            throws IOException, URISyntaxException {
        text.lines().forEach(l -> {
            log.debug("line: '{}'", l);
//                    if (true) return;
            try {
                final var category = Category.valueOfDelimiter(l);
                log.debug("category <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,<<: {}", category);
                representatives[0] = false;
                categories[0] = category;
            } catch (final IllegalArgumentException iae) {
            }
            if (Objects.equals(Constants.DELIMITER_REPR, l)) {
                log.debug("................... 대표코드!!!");
                representatives[0] = true;
            }
            final var matcher = pattern.matcher(l);
            if (matcher.matches()) {
//                        log.debug("matches: {}", l);
//                        log.debug("matcher.groupCount: {}", matcher.groupCount());
                log.debug("{} / {} / {} / {}", matcher.group(1), matcher.group(2), matcher.group(3),
                          matcher.group(4));
                {
                    final var code = matcher.group(1);
                    final var name = matcher.group(2);
                    log.debug("\t\tcode: {}, name: {}", code, name);
                    final var value = KftcFinancialInstitutionCode.builder()
                            .category(categories[0])
                            .code(code)
                            .name(name)
                            .representative(representatives[0])
                            .build();
                    map.compute(code, (k, v) -> {
                        if (v != null) {
                            assertThat(v.isRepresentative())
                                    .as("representative of previous %1$s", v)
                                    .isTrue();
                            v.setCategory(value.getCategory());
                            return v;
                        } else {
                            return value;
                        }
                    });
                }
//                        assert prev1 == null : "prev1 for " + key1;
                if (matcher.group(3) != null && matcher.group(4) != null) {
                    final var code = matcher.group(3);
                    final var name = matcher.group(4);
                    log.debug("\t\tcode: {}, name: {}", code, name);
                    final var value = KftcFinancialInstitutionCode.builder()
                            .category(categories[0])
                            .code(code)
                            .name(name)
                            .representative(representatives[0])
                            .build();
                    map.compute(code, (k, v) -> {
                        if (v != null) {
                            assertThat(v.isRepresentative())
                                    .as("representative of previous %1$s", v)
                                    .isTrue();
                            v.setCategory(value.getCategory());
                            return v;
                        } else {
                            return value;
                        }
                    });
                }
//                        assert prev2 == null : "prev2 for " + key1;
            }
        });
    }

    @Test
    void __pdfbox() throws IOException, URISyntaxException {
        final var file = new File(getClass().getResource("/bankinfo_2024_03_29.hwp.pdf").toURI());
        assert file.isFile();
        log.debug("file.length: {}", file.length());
        final boolean[] representatives = new boolean[]{false};
        final Category[] categories = new Category[]{null};
        final Map<String, KftcFinancialInstitutionCode> map = new HashMap<>();
        try (var document = Loader.loadPDF(file)) {
            assert !document.isEncrypted();
            final var stripper = new PDFTextStripper();
            final var text = stripper.getText(document);
            parse(representatives, categories, text, map);
            map.forEach((k, v) -> {
                if (!v.getCode().equals("001")) {
                    assertThat(v.getCategory())
                            .as("category of %1$s", v)
                            .isNotNull();
                }
            });
            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.BANK).count()).isEqualTo(80);
            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.FIIN).count()).isEqualTo(47);
            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.CAPI).count()).isEqualTo(14);
            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.CARD).count()).isEqualTo(15);
            map.entrySet().stream().filter(e -> e.getValue().getCategory() == Category.INSU).forEach(e -> {
                log.debug("INSU / {}, {}", e.getKey(), e.getValue());
            });
            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.INSU).count()).isEqualTo(35);
            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.MISC).count()).isEqualTo(5);
        }
    }

    @Test
    void __itext() throws IOException, URISyntaxException {
        final var file = new File(getClass().getResource("/bankinfo_2024_03_29.hwp.pdf").toURI());
        assert file.isFile();
        log.debug("file.length: {}", file.length());
        final boolean[] representatives = new boolean[]{false};
        final Category[] categories = new Category[]{null};
        final Map<String, KftcFinancialInstitutionCode> map = new HashMap<>();
        final var reader = new PdfReader(file.toURI().toURL());
        try {
            int pages = reader.getNumberOfPages();
            for (int i = 1; i <= pages; i++) {
                String pageContent = PdfTextExtractor.getTextFromPage(reader, i);
                System.out.println("Content on Page " + i + ": \n" + pageContent);
                parse(representatives, categories, pageContent, map);
            }
        } finally {
            reader.close();
        }
        assertThat(map.values().stream().filter(v -> v.getCategory() == Category.BANK).count()).isEqualTo(80);
        assertThat(map.values().stream().filter(v -> v.getCategory() == Category.FIIN).count()).isEqualTo(47);
        assertThat(map.values().stream().filter(v -> v.getCategory() == Category.CAPI).count()).isEqualTo(14);
        assertThat(map.values().stream().filter(v -> v.getCategory() == Category.CARD).count())
                .as("count of %1$s", Category.CARD)
                .isEqualTo(15);

        map.entrySet().stream().filter(e -> e.getValue().getCategory() == Category.INSU).forEach(e -> {
            log.debug("INSU / {}, {}", e.getKey(), e.getValue());
        });
        assertThat(map.values().stream().filter(v -> v.getCategory() == Category.INSU).count()).isEqualTo(35);

        assertThat(map.values().stream().filter(v -> v.getCategory() == Category.MISC).count()).isEqualTo(5);
    }
}

package com.github.jinahya.kftc.financial.institution.codes;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class KftcFinancialInstitutionCodes_Resource_Test {

    private static final Pattern pattern = Pattern.compile("(\\d{3})\\s([\\p{L}\\(\\)\\s]+)?\\s?(\\d{3})?\\s?([\\p{L}\\(\\)\\s]+)?");

    static void parse(final boolean[] representatives, final KftcFinancialInstitutionCategory[] categories, final String text,
                      Map<String, KftcFinancialInstitutionCode> map) {
        text.lines().forEach(l -> {
            try {
                final var category = KftcFinancialInstitutionCategory.valueOfDelimiter(l);
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
                if (matcher.group(1) != null && matcher.group(2) != null) {
                    final var code = matcher.group(1);
                    assertThat(code).isNotBlank();
                    final var name = matcher.group(2);
                    assertThat(name).isNotBlank();
                    log.debug("\t\tcode: {}, name: {}", code, name);
                    final var value = new KftcFinancialInstitutionCode();
                    value.setCategory(categories[0]);
                    value.setCode(code.strip());
                    value.setName(name.strip());
                    value.setRepresentative(representatives[0]);
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
                    assertThat(code).isNotBlank();
                    final var name = matcher.group(4);
                    assertThat(name).isNotBlank();
                    log.debug("\t\tcode: {}, name: {}", code, name);
                    final var value = new KftcFinancialInstitutionCode();
                    value.setCategory(categories[0]);
                    value.setCode(code.strip());
                    value.setName(name.strip());
                    value.setRepresentative(representatives[0]);
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

//    @Disabled
//    @Test
//    void __pdfbox() throws IOException, URISyntaxException {
//        final var file = new File(getClass().getResource("/bankinfo_2024_03_29.hwp.pdf").toURI());
//        assert file.isFile();
//        log.debug("file.length: {}", file.length());
//        final boolean[] representatives = new boolean[]{false};
//        final Category[] categories = new Category[]{null};
////        final Map<String, KftcFinancialInstitutionCode> map = new TreeMap<>();
//        final Map<String, KftcFinancialInstitutionCode> map = new HashMap<>();
//        try (var document = Loader.loadPDF(file)) {
//            assert !document.isEncrypted();
//            final var stripper = new PDFTextStripper();
//            final var text = stripper.getText(document);
//            parse(representatives, categories, text, map);
//            map.forEach((k, v) -> {
//                if (!v.getCode().equals("001")) {
//                    assertThat(v.getCategory())
//                            .as("category of %1$s", v)
//                            .isNotNull();
//                }
//            });
//            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.BANK).count()).isEqualTo(80);
//            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.FIIN).count()).isEqualTo(47);
//            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.CAPI).count()).isEqualTo(14);
//            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.CARD).count()).isEqualTo(15);
//            map.entrySet().stream().filter(e -> e.getValue().getCategory() == Category.INSU).forEach(e -> {
//                log.debug("INSU / {}, {}", e.getKey(), e.getValue());
//            });
//            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.INSU).count()).isEqualTo(35);
//            assertThat(map.values().stream().filter(v -> v.getCategory() == Category.MISC).count()).isEqualTo(5);
//        }
//    }

    @Test
    void __itext() throws IOException, URISyntaxException {
        final var file = new File(getClass().getResource("/bankinfo_2024_03_29.hwp.pdf").toURI());
        assert file.isFile();
        final boolean[] representatives = new boolean[]{false};
        final KftcFinancialInstitutionCategory[] categories = new KftcFinancialInstitutionCategory[]{null};
        final Map<String, KftcFinancialInstitutionCode> map = new TreeMap<>();
        final var reader = new PdfReader(file.toURI().toURL());
        try {
            final var numberOfPages = reader.getNumberOfPages();
            for (var i = 1; i <= numberOfPages; i++) {
                final var textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
                parse(representatives, categories, textFromPage, map);
            }
        } finally {
            reader.close();
        }
        assertThat(map.values().stream().filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.BANK).count()).isEqualTo(80);
        assertThat(map.values().stream().filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.FIIN).count()).isEqualTo(47);
        assertThat(map.values().stream().filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.CAPI).count()).isEqualTo(14);
        assertThat(map.values().stream().filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.CARD).count())
                .as("count of %1$s", KftcFinancialInstitutionCategory.CARD)
                .isEqualTo(15);
        assertThat(map.values().stream().filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.INSU).count()).isEqualTo(35);
        assertThat(map.values().stream().filter(v -> v.getCategory() == KftcFinancialInstitutionCategory.MISC).count()).isEqualTo(5);
        assertThat(map.values().stream().filter(KftcFinancialInstitutionCode::isRepresentative).count()).isEqualTo(150);
        assertThat(map).hasSize(196);
        final var directory = Stream.concat(
                        Stream.of("src", "main", "resources"),
                        Arrays.stream(getClass().getPackage().getName().split("\\."))
                )
                .reduce(Path.of("."), Path::resolve, (p1, p2) -> p1)
                .toAbsolutePath()
                .normalize();
        Files.createDirectories(directory);
        final var path = directory.resolve(KftcFinancialInstitutionCodes.RESOURCE_NAME);
        try (var stream = new FileOutputStream(path.toFile())) {
            final var text = map
                    .values()
                    .stream()
                    .map(KftcFinancialInstitutionCode::toLine)
                    .collect(Collectors.joining("\r\n"));
            stream.write(text.getBytes(StandardCharsets.UTF_8));
            stream.flush();
        }
    }
}

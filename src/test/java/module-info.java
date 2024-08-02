// https://maven.apache.org/surefire/maven-surefire-plugin/examples/jpms.html
open module com.github.jinahya.kftc.financial.institution.info {
    exports com.github.jinahya.kftc.financial.institution.info;
    requires transitive java.sql;
    requires static lombok;   // provided
    // -----------------------------------------------------------------------------------------------------------------
    requires transitive jakarta.json.bind;
    requires transitive org.assertj.core;
    requires transitive org.slf4j;
    requires transitive org.junit.jupiter;
    requires transitive itextpdf;
    requires transitive org.apache.poi.ooxml;
    requires transitive org.apache.poi.poi;
    requires transitive org.apache.poi.ooxml.schemas;
}

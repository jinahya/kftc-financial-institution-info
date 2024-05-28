package com.github.jinahya.kftc.financial.institution.info.proto;

import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionInfoSet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.IntStream;

import static com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitution_Resource__Test.buildOutputFile;

@Slf4j
class KftcFinancialInstitutionInfoProtoOuterClassTest {

    @Test
    void __() throws IOException {
        final var instance = KftcFinancialInstitutionInfoSet.newInstance();
        final var mapped = instance.getList().stream()
                .map(i -> {
//                    log.debug("i: {}", i);
                    return KftcFinancialInstitutionInfoProtoOuterClass.KftcFinancialInstitutionInfoProto.newBuilder()
                            .setCategory(i.getCategory().name())
                            .setCode(i.getCode())
                            .setName(i.getName())
                            .setRepresentative(i.isRepresentative())
                            .build();
                })
                .toList();
        log.debug("size: {}", mapped.size());
        final var builder = KftcFinancialInstitutionInfoProtoOuterClass.KftcFinancialInstitutionInfoProtoRepeated.newBuilder();
        IntStream.range(0, mapped.size()).forEach(i -> {
            log.debug("i: {}", i);
        });
        IntStream.range(0, mapped.size()).forEach(i -> {
            final var value = mapped.get(i);
            builder.setObjects(i,value);
        });
        mapped.forEach(p -> {
            log.debug("p: {}", p);
            log.debug("p.name: {}", p.getName());
        });
        {
            final var path = buildOutputFile("bankinfo.binpb");
            try (var stream = new FileOutputStream(path.toFile())) {
            }
        }
    }
}

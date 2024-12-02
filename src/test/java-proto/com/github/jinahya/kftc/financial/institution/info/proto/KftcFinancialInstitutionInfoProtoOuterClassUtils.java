package com.github.jinahya.kftc.financial.institution.info.proto;

/*-
 * #%L
 * kftc-financial-institution-info
 * %%
 * Copyright (C) 2024 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionCategory;
import com.github.jinahya.kftc.financial.institution.info.KftcFinancialInstitutionInfo;
import com.github.jinahya.kftc.financial.institution.info.proto.KftcFinancialInstitutionInfoProtoOuterClass.KftcFinancialInstitutionInfoProto;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
final class KftcFinancialInstitutionInfoProtoOuterClassUtils {

    static KftcFinancialInstitutionInfoProto from(final KftcFinancialInstitutionInfo source) {
        Objects.requireNonNull(source, "source is null");
        return KftcFinancialInstitutionInfoProto.newBuilder()
                .setCategory(source.getCategory().name())
                .setCode(source.getCode())
                .setName(source.getName())
                .setRepresentative(source.isRepresentative())
                .build();
    }

    static KftcFinancialInstitutionInfo from(final KftcFinancialInstitutionInfoProto source)
            throws Exception {
        final var constructor = KftcFinancialInstitutionInfo.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        final var instance = constructor.newInstance();
        {
            final var method = KftcFinancialInstitutionInfo.class.
                    getDeclaredMethod("setCategory", KftcFinancialInstitutionCategory.class);
            method.setAccessible(true);
            method.invoke(instance, KftcFinancialInstitutionCategory.valueOf(source.getCategory()));
        }
        {
            final var method = KftcFinancialInstitutionInfo.class.
                    getDeclaredMethod("setCode", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getCode());
        }
        {
            final var method = KftcFinancialInstitutionInfo.class.
                    getDeclaredMethod("setName", String.class);
            method.setAccessible(true);
            method.invoke(instance, source.getName());
        }
        {
            final var method = KftcFinancialInstitutionInfo.class.
                    getDeclaredMethod("setRepresentative", boolean.class);
            method.setAccessible(true);
            method.invoke(instance, source.getRepresentative());
        }
        return instance;
    }

    private KftcFinancialInstitutionInfoProtoOuterClassUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

package com.github.jinahya.kftc.financial.institution.info;

import java.util.Objects;

abstract class _Info_Test<INFO extends _Info> {

    _Info_Test(final Class<INFO> infoClass) {
        super();
        this.infoClass = Objects.requireNonNull(infoClass, "infoClass is null");
    }

    final Class<INFO> infoClass;
}

package com.github.jinahya.kftc.financial.institution.info;

import java.util.Objects;

class _InfoSet_Test<INFOSET extends _InfoSet<INFO>, INFO extends _Info> {

    _InfoSet_Test(final Class<INFOSET> infoSetClass, final Class<INFO> infoClass) {
        super();
        this.infoSetClass = Objects.requireNonNull(infoSetClass, "infoSetClass is null");
        this.infoClass = Objects.requireNonNull(infoClass, "infoClass is null");
    }

    final Class<INFOSET> infoSetClass;

    final Class<INFO> infoClass;
}

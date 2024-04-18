package com.github.jinahya.kftc.financial.institution.codes;

import java.io.*;
import java.nio.file.Path;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@SuppressWarnings({"java:S101"})
final class _IoUtils {

    static void write(final File file, final Object obj) throws IOException {
        try (var fos = new FileOutputStream(file);
             var gzipos = new GZIPOutputStream(fos);
             var oos = new ObjectOutputStream(gzipos)) {
            oos.writeObject(obj);
            oos.flush();
        }
    }

    static void write(final Path path, final Object obj) throws IOException {
        write(path.toFile(), obj);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    static <T> T read(final InputStream stream) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(stream, "stream is null");
        try (var gzipos = new GZIPInputStream(stream);
             var oos = new ObjectInputStream(gzipos)) {
            return (T) oos.readObject();
        }
    }

    static <T> T read(final File file) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(file, "file is null");
        try (var fos = new FileInputStream(file)) {
            return read(fos);
        }
    }

    static <T> T read(final Path path) throws IOException, ClassNotFoundException {
        Objects.requireNonNull(path, "path is null");
        return read(path.toFile());
    }

    // -----------------------------------------------------------------------------------------------------------------
    private _IoUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}

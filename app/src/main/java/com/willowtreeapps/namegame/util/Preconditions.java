package com.willowtreeapps.namegame.util;

import java.io.File;
import java.util.Collection;

public final class Preconditions {

    public static <T> T checkNotNull(T reference, String message) {
        if (reference == null) {
            throw new IllegalArgumentException(message);
        }
        return reference;
    }

    public static <T extends Collection> T checkNotNullOrEmpty(T collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return collection;
    }

    public static <T> T[] checkNotNullOrEmpty(T[] collection, String message) {
        if (collection == null || collection.length == 0) {
            throw new IllegalArgumentException(message);
        }
        return collection;
    }

    public static <T extends CharSequence> T checkNotNullOrEmpty(T string, String message) {
        if (checkNotNull(string, message).length() == 0) {
            throw new IllegalArgumentException(message);
        }
        return string;
    }

    public static <T extends File> T checkExists(T file, String message) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException(message);
        }
        return file;
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }

}

package com.willowtreeapps.namegame.core;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListRandomizer {

    @NonNull
    private final Random random;

    public ListRandomizer(@NonNull Random random) {
        this.random = random;
    }

    @NonNull
    public <T> T pickOne(@NonNull List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    @NonNull
    public <T> List<T> pickN(@NonNull List<T> list, int n) {
        if (list.size() == n) return list;
        if (n == 0) return Collections.emptyList();
        List<T> pickFrom = new ArrayList<>(list);
        List<T> picks = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            picks.add(pickFrom.remove(random.nextInt(pickFrom.size())));
        }
        return picks;
    }
}

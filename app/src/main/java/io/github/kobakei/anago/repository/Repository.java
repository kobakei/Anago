package io.github.kobakei.anago.repository;

import java.util.HashMap;
import java.util.Map;

/**
 * リポジトリのベースクラス
 * Created by keisuke on 2016/09/25.
 */

public abstract class Repository<K, V> {

    private Map<K, V> cache;

    public Repository() {
        this.cache = new HashMap<>();
    }

    protected Map<K, V> getCache() {
        return cache;
    }

    public void clearCache() {
        cache.clear();
    }
}

package xknr.euler.util;

import java.util.List;
import java.util.Map;

public interface MultiMap<K, V> extends Map<K, List<V>> {
  void add(K key, V value);
}

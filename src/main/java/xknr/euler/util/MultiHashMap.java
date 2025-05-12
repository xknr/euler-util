package xknr.euler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MultiHashMap<K, V> extends HashMap<K, List<V>> implements MultiMap<K, V> {

  public void add(K key, V value) {
      this.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
  }
}

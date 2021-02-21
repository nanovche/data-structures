package com.egtinteractive.map;

public interface CustomMap<K, V> extends Iterable<CustomHashMap.Entry<K, V>> {

  V get(Object key);

  V put(K key, V value);

  V remove(K key);

  boolean containsKey(K key);

  boolean containsValue(V value);

  int size();

  void clear();

}

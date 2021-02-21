package com.egtinteractive.map;

import static java.util.Objects.hash;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomHashMap<K, V> implements CustomMap<K, V> {

  private static final int INITIAL_CAPACITY = 16;
  private static final float DEFAULT_LOAD_FACTOR = 0.75F;
  private Entry<K, V>[] buckets;
  private int size;

  public CustomHashMap(int initialCapacity) {
    this.buckets = new Entry[initialCapacity];
  }

  public CustomHashMap() {
    this(INITIAL_CAPACITY);
  }

  static final class Entry<K, V> {

    private K key;
    private V value;
    private Entry<K, V> next;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    Entry() {}

    K getKey() {
      return key;
    }

    V getValue() {
      return value;
    }

    @Override
    public boolean equals(Object obj) {

      if (this == obj) {
        return true;
      }

      if (!(obj instanceof Entry)) {
        return false;
      }

      Entry<?, ?> otherEntry = (Entry<?, ?>) obj;

      return Objects.equals(this.key, otherEntry.key)
          && Objects.equals(this.value, otherEntry.value);
    }

    @Override
    public int hashCode() {
      int result = this.key == null ? 0 : this.key.hashCode();
      return 31 * result + (this.value == null ? 0 : this.value.hashCode());
    }

    @Override
    public String toString() {
      return "key: " + getKey() + "\t" + "value: " + getValue();
    }
  }

  public V put(K key, V value) {

    ensureCapacity();
    Entry<K, V> entry = new Entry<>(key, value);

    final int bucket = calculatePosition(key);

    Entry<K, V> existing = buckets[bucket];
    Entry<K, V> previous = new Entry<>();

    if (existing == null) {
      buckets[bucket] = entry;
      size++;
      return null;
    } else {
      do {
        if ((Objects.equals(existing.key, key))) {
          V removedValue = existing.value;
          existing.value = value;
          return removedValue;
        }

        previous = existing;
        existing = existing.next;

      } while (existing != null);

      previous.next = entry;
      size++;
      return null;
    }
  }

  public V remove(Object key) {

    ensureCapacity();

    final int bucket = calculatePosition(key);
    Entry<K, V> existing = buckets[bucket];

    if (existing == null) {
      return null;
    } else {
      Entry<K, V> previous = new Entry<>();

      boolean existingMoved = false;

      do {
        if (Objects.equals(existing.key, key)) {

          if (!existingMoved) {
            V returnedValue = existing.value;
            size--;
            buckets[bucket] = existing.next;
            return returnedValue;
          }

          V returnedValue = existing.value;
          previous.next = existing.next;
          size--;
          return returnedValue;
        }
        previous = existing;
        existing = existing.next;
        existingMoved = true;
      } while (existing != null);
      return null;
    }
  }

  public V get(Object key) {

    ensureCapacity();

    Entry<K, V> existing = buckets[calculatePosition(key)];

    if (existing == null) {
      return null;
    } else {
      do {
        if (Objects.equals(existing.key, key)) {
          return existing.value;
        }
        existing = existing.next;
      } while (existing != null);

      return null;
    }

  }

  public boolean containsKey(Object key) {

    if (buckets.length == 0) {
      return false;
    }

    Entry<K, V> existing = buckets[calculatePosition(key)];

    if (existing == null) {
      return false;
    } else {
      do {
        if (Objects.equals(existing.key, key)) {
          return true;
        }
        existing = existing.next;
      } while (existing != null);

      return false;
    }

  }

  public boolean containsValue(Object value) {

    for (int i = 0; i < buckets.length; i++) {

      Entry<K, V> current = buckets[i];

      while (current != null) {
        if (Objects.equals(current.value, value)) {
          return true;
        }
        current = current.next;
      }
    }
    return false;
  }

  public int size() {
    return this.size;
  }

  public void clear() {

    Arrays.fill(buckets, null);
    size = 0;
  }

  private class CustomHashMapIterator implements Iterator<Entry<K, V>> {

    private Entry<K, V> cursor;
    private int index = -1;
    private int lastRetIndex = -1;
    private Entry<K, V> lastRet;
    private int numOfIteratedElements;

    @Override
    public boolean hasNext() {
      return numOfIteratedElements < size;
    }

    @Override
    public Entry<K, V> next() {

      boolean flag = false;

      if (!(hasNext())) {
        throw new NoSuchElementException();
      }

      Entry<K, V> returnedValue = cursor;

      if (cursor == null) {
        do {
          cursor = buckets[++index];
        } while (cursor == null);
        returnedValue = cursor;
        numOfIteratedElements++;
        flag = true;
      }
      lastRet = cursor;
      lastRetIndex = index;
      cursor = cursor.next;
      if (!flag) {
        numOfIteratedElements++;
      }
      return returnedValue;
    }

    @Override
    public void remove() {
      if (this.lastRetIndex < 0) {
        throw new IllegalStateException();
      }
      CustomHashMap.this.remove(lastRet.key);
      lastRetIndex = -1;
    }
  }

  @Override
  public Iterator<Entry<K, V>> iterator() {

    return new CustomHashMapIterator();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof CustomMap)) {
      return false;
    }

    @SuppressWarnings("unchecked")
    CustomMap<K, V> other = (CustomMap<K, V>) o;

    if (this.size != other.size()) {
      return false;
    }

    if (this.size == 0) {
      return true;
    }

    for (Entry<K, V> entry: this) {

        K key = entry.getKey();
        V value = entry.getValue();

        if(value == null){
          if(!(other.get(key) == null && other.containsKey(key))){
            return false;
          }
        } else {

          if(!value.equals(other.get(key))){
            return false;
          }

        }

    }

    return true;

  }

  @Override
  public int hashCode() {
    int result = Integer.hashCode(size);
    return result * 31 + Arrays.hashCode(buckets);
  }

  private int calculatePosition(Object key) {
    return key == null ? 0 : Math.abs(hash(key)) % buckets.length;
  }

  @SuppressWarnings("unchecked")
  private void ensureCapacity() {
    if (this.size < buckets.length * DEFAULT_LOAD_FACTOR) {
      return;
    }

    Entry<K, V>[] oldBuckets = buckets;
    buckets = new Entry[size == 0 ? INITIAL_CAPACITY : this.size * 2];

    this.size = 0;

    for (int i = 0; i < oldBuckets.length; i++) {

      while (oldBuckets[i] != null) {
        put(oldBuckets[i].key, oldBuckets[i].value);
        oldBuckets[i] = oldBuckets[i].next;
      }

    }

  }

}

package com.egtinteractive.lists;

public interface CustomList<T> extends Iterable<T> {

  T get(int index);

  void add(T element);

  void add(int index, T element);

  void set(int index, T element);

  boolean remove(T element);

  T remove(int index);

  boolean contains(T o);

  int indexOf(T element);

  int size();

  void clear();

}

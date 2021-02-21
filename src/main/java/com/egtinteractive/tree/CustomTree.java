package com.egtinteractive.tree;

public interface CustomTree<T> extends Iterable<T>{

  void add(T element);

  boolean remove(T element);

  T lower(T e);

  T higher(T e);

  T pollFirst();

  T pollLast();

  boolean contains(T o);

  int size();

  void clear();

  boolean equals(Object otherObject);

  int hashCode();

}

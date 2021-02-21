package com.egtinteractive.lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import static com.egtinteractive.lists.ListUtilities.*;

public class CustomArrayList<T> implements CustomList<T> {

  private static final int INITIAL_CAPACITY = 10;
  private T[] list;
  private int currentSize;

  public CustomArrayList() {
    this(INITIAL_CAPACITY);
  }

  @SuppressWarnings("unchecked")
  public CustomArrayList(int initialCapacity) {
    list = (T[]) new Object[initialCapacity];
  }

  public T get(int index) {

    validateIndex(index, currentSize);

    return list[index];
  }

  public void add(T element) {
    ensureCapacity();
    list[this.currentSize++] = element;
  }

  public void add(int index, T element) {

    validateIndexForAddMethod(index, currentSize);
    ensureCapacity();

    int lastElement = currentSize - 1;
    for (int i = lastElement; i >= index; i--) {
      list[i + 1] = list[i];
    }
    list[index] = element;
    currentSize++;
  }

  public void set(int index, T element) {

    validateIndex(index, currentSize);
    list[index] = element;

  }

  public boolean remove(T element) {

    for (int i = 0; i < currentSize; i++) {

      if (Objects.equals(element, list[i])) {
        remove(i);
        return true;
      }
    }
    return false;
  }

  public T remove(int index) {

    validateIndex(index, currentSize);

    T removedElement = (T) list[index];

    for (int i = index; i < this.currentSize - 1; i++) {
      list[i] = list[i + 1];
    }

    list[currentSize - 1] = null;

    this.currentSize--;

    return removedElement;
  }

  public boolean contains(T element) {

    return indexOf(element) != -1;

  }

  public int indexOf(T element) {

    for (int i = 0; i < currentSize; i++) {
      if (Objects.equals(list[i], element)) {
        return i;
      }
    }

    return -1;
  }

  public int size() {
    return this.currentSize;
  }

  public void clear() {

    for (int i = 0; i < currentSize; i++) {
      list[i] = null;
    }
    currentSize = 0;
  }

  private void ensureCapacity() {
    if (currentSize < list.length) {
      return;
    }
    list = Arrays.copyOf(list, currentSize == 0 ? INITIAL_CAPACITY : currentSize * 2);
  }

  @Override
  public boolean equals(Object o) {

    if (o == this) {
      return true;
    }

    if (!(o instanceof CustomList)) {
      return false;
    }

    CustomList<?> anotherList = (CustomList<?>) o;

    if(this.size() != anotherList.size()){
      return false;
    }

    Iterator<?> iteratorFirstList = this.iterator();
    Iterator<?> iteratorSecondList = anotherList.iterator();

    while(iteratorFirstList.hasNext() && iteratorSecondList.hasNext()){
      if(!(Objects.equals(iteratorFirstList.next(), iteratorSecondList.next()))){
        return false;
      }
    }

    return iteratorFirstList.hasNext() == iteratorSecondList.hasNext();

  }

  @Override
  public int hashCode() {
    int result = Integer.hashCode(currentSize);
    result = 31 * result + Arrays.hashCode(list);
    return result;
  }

  private class CustomListIterator implements Iterator<T> {

    private int cursor;
    private int lastRet = -1;

    @Override
    public boolean hasNext() {
      return cursor < currentSize;
    }

    @Override
    public T next() {
      if (!(hasNext())) {
        throw new NoSuchElementException();
      }
      this.lastRet = this.cursor++;
      return list[this.lastRet];
    }

    @Override
    public void remove() {
      if (this.lastRet < 0) {
        throw new IllegalStateException();
      }
      CustomArrayList.this.remove(lastRet);
      this.cursor = this.lastRet;
      this.lastRet = -1;
    }

  }

  @Override
  public Iterator<T> iterator() {
    return new CustomListIterator();
  }

}

package com.egtinteractive.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.egtinteractive.lists.ListUtilities.*;

public class CustomLinkedList<T> implements CustomList<T> {

  private Node<T> head;
  private int currentSize;

  private static final class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
    }

    public Node() {}

    @Override
    public boolean equals(Object obj) {

      if (this == obj) {
        return true;
      }

      if (!(obj instanceof Node)) {
        return false;
      }

      CustomLinkedList.Node otherNode = (CustomLinkedList.Node) obj;

      return this.data.equals(otherNode.data);
    }

    @Override
    public int hashCode() {
      return 31 * (data == null ? 0 : data.hashCode());
    }

  }

  public T get(int index) {

    validateIndex(index, currentSize);
    Node<T> cursor = getToNodeIndex(index, head);

    return cursor.data;
  }

  public void add(T element) {

    Node<T> newElement = new Node<>(element);
    Node<T> cursor = head;

    currentSize++;

    if (head == null) {
      head = newElement;
      return;
    }

    while (cursor.next != null) {
      cursor = cursor.next;
    }

    cursor.next = newElement;
  }

  public void add(int index, T element) {

    validateIndexForAddMethod(index, currentSize);

    currentSize++;

    Node<T> newElement = new Node<>(element);
    Node<T> cursor = getToNodeBeforeIndex(index, head);

    if (index == 0 || head == null) {
      head = newElement;
      return;
    }

    newElement.next = cursor.next;
    cursor.next = newElement;
  }

  public void set(int index, T element) {

    validateIndex(index, currentSize);
    Node<T> cursor = getToNodeIndex(index, head);
    cursor.data = element;
  }

  public boolean remove(T element) {

    Node<T> cursor = head;
    Node<T> previous = head;

    if (head == null) {
      return false;
    }

    int index = 0;

    do {
      if (Objects.equals(cursor.data, element)) {
        if (index == 0) {
          head = cursor.next;
          currentSize--;
          return true;
        }
        currentSize--;
        previous.next = cursor.next;
        return true;
      }
      previous = cursor;
      cursor = cursor.next;
      index++;

    } while (cursor != null);

    return false;
  }

  public T remove(int index) {

    validateIndex(index, currentSize);

    currentSize--;

    Node<T> cursor = getToNodeBeforeIndex(index, head);

    T removedElement;

    if (head == cursor) {
      removedElement = cursor.data;
      head = cursor.next;
      return removedElement;
    }

    removedElement = cursor.next.data;

    cursor.next = cursor.next.next;

    return removedElement;
  }

  public boolean contains(T element) {

    return indexOf(element) != -1;
  }

  public int indexOf(T element) {

    Node<T> cursor = head;

    if (cursor == null) {
      return -1;
    }

    int index = 0;

    do {
      if (Objects.equals(cursor.data, element)) {
        return index;
      }
      cursor = cursor.next;
      index++;
    } while (cursor != null);

    return -1;
  }

  public int size() {
    return this.currentSize;
  }

  public void clear() {
    this.currentSize = 0;
    this.head = null;
  }

  private class MyLinkedListIterator implements Iterator<T> {
    private Node<T> cursor = head;
    private int index = 0;
    private int lastRet = -1;

    @Override
    public boolean hasNext() {
      return cursor != null;
    }

    @Override
    public T next() {
      if (!(hasNext())) {
        throw new NoSuchElementException();
      }
      T currentElement = cursor.data;
      cursor = cursor.next;
      this.lastRet = this.index++;
      return currentElement;
    }

    @Override
    public void remove() {
      if (this.lastRet < 0) {
        throw new IllegalStateException();
      }
      CustomLinkedList.this.remove(lastRet);
      this.index = this.lastRet;
      this.lastRet = -1;
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new MyLinkedListIterator();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof CustomList)) {
      return false;
    }

    CustomList<?> anotherList = (CustomList<?>) o;

    if (this.size() != anotherList.size()) {
      return false;
    }

    Iterator<?> iteratorFirstList = this.iterator();
    Iterator<?> iteratorSecondList = anotherList.iterator();

    while (iteratorFirstList.hasNext() && iteratorSecondList.hasNext()) {

      if (!((iteratorFirstList.next().equals( iteratorSecondList.next())))) {
        return false;
      }
    }

    return iteratorFirstList.hasNext() == iteratorSecondList.hasNext();
  }

  @Override
  public int hashCode() {

    int result = Integer.hashCode(currentSize);

    Node<T> current = head;

    while(current != null){

      result = 31 * result + current.hashCode();

      current = current.next;

    }

    return result;
  }

  private Node<T> getToNodeIndex(int index, final Node<T> head) {

    Node<T> cursor = head;

    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }

    return cursor;
  }

  private Node<T> getToNodeBeforeIndex(int index, final Node<T> head) {

    Node<T> cursor = head;

    index--;

    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }

    return cursor;
  }
}

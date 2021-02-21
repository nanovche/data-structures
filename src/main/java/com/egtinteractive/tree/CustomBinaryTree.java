package com.egtinteractive.tree;

import java.util.*;

public class CustomBinaryTree<T extends Comparable<T>> implements CustomTree<T> {

  private Node<T> root;
  private int size;

  private static final class Node<T> {

    private T element;
    private Node<T> left;
    private Node<T> right;

    private Node(T element) {
      this.element = element;
    }

    private Node() {

    }

    @Override
    public boolean equals(Object o) {

      if (this == o) {
        return true;
      }

      if (!(o instanceof CustomBinaryTree.Node)) {
        return false;
      }

      CustomBinaryTree.Node otherNode = (CustomBinaryTree.Node) o;

      return this.element.equals(otherNode.element);

    }

    @Override
    public int hashCode() {
      return element == null ? 0 : this.element.hashCode();

    }

  }

  @Override
  public void add(T element) {

    if(element == null || contains(element)){
      throw new IllegalArgumentException("Cannot add duplicate or null values");
    }

    Node<T> cursor = root;

    Node<T> newElement = new Node<>(element);

    Node<T> parent = null;

    while (cursor != null) {
      parent = cursor;
      if (element.compareTo(cursor.element) < 0) {
        cursor = cursor.left;
      } else {
        cursor = cursor.right;
      }
    }

    if (parent == null) {
      root = newElement;
    } else if (element.compareTo(parent.element) < 0) {
      parent.left = newElement;
    } else if(element.compareTo(parent.element) > 0){
      parent.right = newElement;
    }

    size++;

  }

  @Override
  public boolean remove(T deletedElement) {

    if (root == null) {
      return false;
    }

    Node<T> deleted = root;
    Node<T> parent = root;

    List<Node<T>>  deletedAndParent = findElementAndParent(deleted, parent, deletedElement);

    if(deletedAndParent == null){
      return false;
    }else{
      deleted = deletedAndParent.get(0);
      parent = deletedAndParent.get(1);
    }

    Node<T> leftChildOfDeletedNode = deleted.left;
    Node<T> rightChildOfDeletedNode = deleted.right;

    if(deletedElement.compareTo(parent.element) < 0){

      if(leftChildOfDeletedNode == null && rightChildOfDeletedNode == null){
        parent.left = null;
        size--;
        return true;
      }
      else if(leftChildOfDeletedNode == null){
       parent.left = rightChildOfDeletedNode;
       size--;
       return true;
      }else if(rightChildOfDeletedNode == null){
        parent.left = leftChildOfDeletedNode;
        size--;
        return true;
      }

      parent.left = findLargestNodeInLeftSubTree(deleted, parent);
      if(leftChildOfDeletedNode.element.compareTo(parent.left.element) != 0){
        parent.left.left = leftChildOfDeletedNode;
      }

      parent.left.right = rightChildOfDeletedNode;

    }else  if(deletedElement.compareTo(parent.element) > 0){

      if(leftChildOfDeletedNode == null && rightChildOfDeletedNode == null){
        parent.right = null;
        size--;
        return true;
      }
      else if(leftChildOfDeletedNode == null){
        parent.right = rightChildOfDeletedNode;
        size--;
        return true;
      }else if(rightChildOfDeletedNode == null){
        parent.right = leftChildOfDeletedNode;
        size--;
        return true;
      }

      parent.right = findLargestNodeInLeftSubTree(deleted, parent);

      if(leftChildOfDeletedNode.element.compareTo(parent.right.element) != 0){
        parent.right.left = leftChildOfDeletedNode;
      }
      parent.right.right = rightChildOfDeletedNode;

      }
      else{

      if(size() == 1){
        size--;
        root = null;
        return true;
      }

      root = findLargestNodeInLeftSubTree(deleted, parent);

      if(root != null){

        if( leftChildOfDeletedNode != null && root.element.compareTo(leftChildOfDeletedNode.element) != 0){
          root.left = leftChildOfDeletedNode;
        }

        if(rightChildOfDeletedNode != null && root.element.compareTo(rightChildOfDeletedNode.element) != 0){
          root.right = rightChildOfDeletedNode;
        }

      }else{
        root = rightChildOfDeletedNode;
      }

    }

    size--;
    return  true;

  }

  private List<Node<T>> findElementAndParent(Node<T> deleted, Node<T> parent,T element){

    while (deleted != null) {

      if (element.compareTo(deleted.element) == 0) {

        return new ArrayList<>(Arrays.asList(deleted, parent));

      } else if (element.compareTo(deleted.element) < 0) {
        parent = deleted;
        deleted = deleted.left;
      } else if(element.compareTo(deleted.element) > 0){
        parent = deleted;
        deleted = deleted.right;
      }

    }

    return  null;
  }
  private Node<T> findLargestNodeInLeftSubTree(Node<T> cursor, Node<T> parentOfDeletedNode){

    Node<T> previous = cursor;
    Node<T> beforePrevious = cursor;

    cursor = cursor.left;

    if(cursor == null){
      return null;
    }

    while(cursor != null){
      beforePrevious = previous;
      previous = cursor;
      cursor = cursor.right;
    }

    if(beforePrevious.left != null && beforePrevious.right != null){

      if(!(previous.left == null && previous.right == null)){
        previous.right = beforePrevious.right;
      }

      return previous;
    }

    if(beforePrevious.left == null && beforePrevious.right != null){

      if(previous.left == null){
        beforePrevious.right = null;
      }else{
        beforePrevious.right = previous.left;
      }

      return previous;
    }

    root = null;
    return previous;

  }

  @Override
  public T lower(T element) {

    if(root == null) {
      return null;
    }

    Node<T> cursor = root;
    T lower = element;

    while(cursor != null){

      if(element.compareTo(cursor.element) <= 0){

        cursor = cursor.left;

      }else if(element.compareTo(cursor.element) > 0){

        lower = cursor.element;
        cursor = cursor.right;

      }
    }

    if(element.compareTo(lower) == 0){
      return null;
    }

    return lower;

  }

  @Override
  public T higher (T element){

    if(root == null) {
      return null;
    }

    Node<T> cursor = root;
    T higher = element;

    while(cursor != null){

      if(element.compareTo(cursor.element) < 0){

        higher = cursor.element;

        cursor = cursor.left;

      }
      else if(element.compareTo(cursor.element) >= 0){

        cursor = cursor.right;

      }

    }

    if(element.compareTo(higher) == 0){
      return  null;
    }
    return higher;
  }

  @Override
  public T pollFirst () {

    if (root == null) {
      return null;
    }

    Node<T> cursor = root;
    Node<T> previous = root;

    while (cursor != null) {
      previous = cursor;
      cursor = cursor.left;
    }

    T removedElement = previous.element;

    remove(previous.element);

    return removedElement;

  }

  @Override
  public T pollLast () {

    if(root == null){
      return  null;
    }

    Node<T> cursor = root;
    Node<T> previous = root;

    while (cursor != null) {
      previous = cursor;
      cursor = cursor.right;
    }

    T removedElement = previous.element;

    remove(previous.element);

    return removedElement;

  }

  @Override
  public boolean contains (T element){

    if(root == null){
      return false;
    }

    Node<T> cursor = root;

    while (cursor != null) {
      if (element.compareTo(cursor.element) == 0) {
        return true;
      } else if (element.compareTo(cursor.element) < 0) {
        cursor = cursor.left;
      } else {
        cursor = cursor.right;
      }

    }

    return false;

  }

  @Override
  public int size () {
    return this.size;
  }

  @Override
  public void clear () {
    root = null;
    size = 0;
  }

  @Override
  public boolean equals (Object obj){

    if (this == obj) {
      return true;
    }

    if (!(obj instanceof CustomTree)) {
      return false;
    }

    CustomTree<?> other = (CustomTree<?>) obj;

    if (this.size() != other.size()) {
      return false;
    }

    if (this.size == 0) {
      return true;
    }

    Iterator<T> thisIterator = this.iterator();
    Iterator<?> otherIterator = other.iterator();

    while(thisIterator.hasNext() && otherIterator.hasNext()){

      if(!(thisIterator.next().equals(otherIterator.next()))){
        return false;
      }

    }

    return thisIterator.hasNext() == otherIterator.hasNext();

  }

  @Override
  public int hashCode () {
    int result = Integer.hashCode(size);

    Iterator<T> itr = this.iterator();

    while (itr.hasNext()){
      result = 31 * result + itr.next().hashCode();
    }

    return result;

  }

  private final class CustomBinaryTreeIterator implements Iterator<T>{

    private final Stack<Node<T>> nodeStack = new Stack<>();
    private Node<T> current = root;
    private int numOfIteratedElements = 0;
    private int lastRetIndex = -1;
    private Node<T> lastRet = new Node<>();

    @Override
    public boolean hasNext() {

      return numOfIteratedElements < size();

    }

    @Override
    public T next() {

      if (!(hasNext())) {
        throw new NoSuchElementException();
      }

      do {

        if (current != null) {
          nodeStack.push(current.right);
          nodeStack.push(current);
          nodeStack.push(current.left);
        }

        current = nodeStack.pop();

        if (current == null) {
          if ((!nodeStack.empty()) && nodeStack.peek() != null) {

            T element = nodeStack.pop().element;
            lastRet.element = element;

            if (nodeStack.size() == 1 && nodeStack.peek() == null) {
              nodeStack.pop();
              numOfIteratedElements++;
            }

            lastRetIndex = numOfIteratedElements++;
            return element;
          }
        }

      } while ((!(nodeStack.empty())) || current != null);

      return null;

    }

    @Override
    public void remove() {

      if(lastRetIndex < 0){
        throw new IllegalStateException();
      }

      CustomBinaryTree.this.remove(lastRet.element);
      lastRetIndex = -1;

    }
  }

  @Override
  public Iterator<T> iterator () {

    return new CustomBinaryTreeIterator();

  }

}





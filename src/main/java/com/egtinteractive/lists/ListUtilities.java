package com.egtinteractive.lists;

class ListUtilities {

  private ListUtilities() {}

  private static String getExceptionMessage(int index, int listSize) {
    return "Index " + index + " out of bounds for length " + listSize;
  }

  static void validateIndexForAddMethod(int index, int listSize) {

    if (index < 0 || index > listSize) {
      throw new IndexOutOfBoundsException(getExceptionMessage(index, listSize)
      );
    }

  }

  static void validateIndex(int index, int listSize) {

    if (index < 0 || index >= listSize) {
      throw new IndexOutOfBoundsException(getExceptionMessage(index, listSize)
      );
    }

  }

}

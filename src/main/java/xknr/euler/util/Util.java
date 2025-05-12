package xknr.euler.util;

import java.util.List;

public class Util {
  
  public static <T> void push(List<T> li , T e) {
    li.add(e);
  }

  public static <T> T pop(List<T> li) {
    return li.remove(li.size() - 1);
  }

}

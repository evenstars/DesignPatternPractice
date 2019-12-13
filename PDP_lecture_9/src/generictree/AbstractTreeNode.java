package generictree;

import java.util.LinkedList;
import java.util.function.BiFunction;
import java.util.function.Predicate;

//import organization.Employee;

public abstract class AbstractTreeNode<T> implements TreeNode<T> {
  protected T data;

  public AbstractTreeNode(T data) {
    this.data = data;
  }

  @Override
  public int count(Predicate<T> condition) {
    if (condition.test(this.data)) {
      return 1;
    }
    return 0;
  }
}
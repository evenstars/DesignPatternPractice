package controller;

import java.util.List;

/**
 * Class for add command to a cache and execute the commands stored before at once.
 */
public interface BatchOperationController {

  /**
   * Add one operation.
   *
   * @param command string form of command
   */
  void addOperation(String command);

  /**
   * Add a list of commands.
   *
   * @param commamds list of commands
   */
  void addOperations(List<String> commamds);

  /**
   * Execute all the commands stored before. After executing, remove the command.
   */
  void executeBatch();
}

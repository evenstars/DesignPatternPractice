package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import view.CommandParser;

/**
 * Cache command executor based on queue. After executing, the cache is empty.
 */
public class BatchOperationControllerImpl implements BatchOperationController {

  /**
   * Executor for executing single operation.
   */
  private SingleOperationController executor;

  /**
   * Cache for commands.
   */
  private Queue<String> commandBatch;

  /**
   * Constructor fulfill the cache.
   *
   * @param commandParserInput             parser for getting commands
   * @param singleOperationController executor for single operation
   */
  public BatchOperationControllerImpl(CommandParser commandParserInput
          , SingleOperationController singleOperationController) {
    if (commandParserInput == null || singleOperationController == null) {
      throw new IllegalArgumentException("Null argument.");
    }
    CommandParser commandParser;
    commandParser = commandParserInput;
    this.executor = singleOperationController;
    commandBatch = new LinkedList<>();
    addOperations(commandParser.parseCommands());
  }

  @Override
  public void addOperation(String command) {
    if (command == null || command.isEmpty()) {
      throw new IllegalArgumentException("Command cannot be empty or null.");
    }
    commandBatch.offer(command);
  }

  @Override
  public void addOperations(List<String> commands) {
    if (commands == null || commands.isEmpty()) {
      throw new IllegalArgumentException("Commands cannot be empty or null.");
    }
    for (String command : commands) {
      addOperation(command);
    }
  }

  @Override
  public void executeBatch() {
    while (!commandBatch.isEmpty()) {
      executor.executeOperation(commandBatch.poll());
    }
  }
}

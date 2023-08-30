package taskmaster.parser;

import taskmaster.Taskmaster;
import taskmaster.tasks.*;
import taskmaster.storage.Storage;
import taskmaster.exceptions.DukeException;

public class Parser {

    public Parser () {}

    public void parse (String userInput, Storage storage, TaskList taskList) throws DukeException {
        if (userInput.equalsIgnoreCase("bye")) {
            Taskmaster.activated = false;
        } else if (userInput.equalsIgnoreCase("list")) {
            TaskList.printList();
        } else if (userInput.startsWith("todo")) {
            String description = userInput.substring(5).trim();
            taskList.addTask(TaskList.TaskType.TODO, description, "unmarked");
            storage.saveTasksToFile();
        } else if (userInput.startsWith("event")) {
            String description = userInput.substring(5);
            taskList.addTask(TaskList.TaskType.EVENT, description, "unmarked");
            storage.saveTasksToFile();
        } else if (userInput.startsWith("deadline")) {
            String description = userInput.substring(8);
            taskList.addTask(TaskList.TaskType.DEADLINE, description, "unmarked");
            storage.saveTasksToFile();
        } else if (userInput.startsWith("mark")) {
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                taskList.toggleMark(TaskList.MarkStatus.MARK, taskIndex);
                storage.saveTasksToFile();
            } else {
                throw new DukeException("Invalid command");
            }
        } else if (userInput.startsWith("unmark")) {
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                taskList.toggleMark(TaskList.MarkStatus.UNMARK, taskIndex);
                storage.saveTasksToFile();
            } else {
                throw new DukeException("Invalid command");
            }
        } else if (userInput.startsWith("delete")) {
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                taskList.deleteTask(taskIndex);
            } else {
                System.out.println("Please specify the task number to delete.");
            }
            storage.saveTasksToFile();
        } else if (userInput.startsWith("due")) {
            String date = userInput.substring(4).trim();
            taskList.printTasksByDate(date);
        } else {
            throw new DukeException("Please enter a valid command!");
        }
    }
}

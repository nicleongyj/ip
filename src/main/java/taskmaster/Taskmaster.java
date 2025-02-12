package taskmaster;

import taskmaster.exceptions.DukeException;
import taskmaster.parser.Parser;
import taskmaster.storage.Storage;
import taskmaster.tasks.TaskList;
import taskmaster.ui.Main;
import taskmaster.ui.Ui;

import javafx.application.Application;

import java.util.Scanner;
public class Taskmaster {
    private static final String FILE_PATH = "/Data.txt";
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    public Taskmaster() {
        String workingDirectory = System.getProperty("user.dir");
        this.storage = new Storage(workingDirectory + FILE_PATH);
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.storage.loadTasksFromFile();
        this.ui.printHello();
    }

    public static void main(String[] args) {
        Application.launch(Main.class);
    }

    public String getResponse(String input) {
        try {
            String response = parser.parse(input, this.storage, this.taskList);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}


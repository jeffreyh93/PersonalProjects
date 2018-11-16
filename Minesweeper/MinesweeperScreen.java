import java.awt.Component;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author jeffr
 * @version 1.0
 * Algorithm and Ideas:
 * Step 1 = pop-up screen, laying out selection of easy/medium/hard and a submit button
 * Step 2 = set up game based on selection
 * Step 3 = add settings button that brings up the window from step 1, if a new one is selected, restart the game
 * Step 4 = CONSTRUCT LOGIC OF THE GAME
 *
 */
public class MinesweeperScreen extends Application {
    /** difficulty of the game.*/
    private String diff = "easy";
    private int numRows = 8;
    private int numCols = 8;
    private int numMines = 10;
    /**main game window of the game.
     * @param args
     */
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        final double appSize = 1000;
        final double appHeight = 750;
        
        showSettings();
        startGame();
        
        Button settings = new Button("Settings");
        settings.setOnAction(this::processButtonPress);
        
        Group myGroup = new Group(settings);
        Scene scene = new Scene(myGroup, appSize, appHeight);
        
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**main method of the program.
     * @param args unused
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }
    /** Settings menu to configure the difficulty of the game.*/
    private void showSettings() {
        Object[] diffs = {"Easy", "Medium", "Hard"};
        Component frame = null;
        int n = JOptionPane.showOptionDialog(frame,
                "Select the difficulty",
                "Settings",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                diffs,
                diffs[2]);
        if (n == 0) {
            diff = "easy";
        } else if (n == 1) {
            diff = "medium";
        } else if (n == 2) {
            diff = "hard";
        }
    }
    private void startGame() {
        if (diff == "easy") {
            numRows = 8;
            numCols = 8;
            numMines = 10;
        } else if (diff == "medium") {
            numRows = 16;
            numCols = 16;
            numMines = 40;
        } else if (diff == "hard") {
            numRows = 24;
            numCols = 24;
            numMines = 99;
        }
        
    }
    public void processButtonPress(ActionEvent event) {
        showSettings();
    }
}

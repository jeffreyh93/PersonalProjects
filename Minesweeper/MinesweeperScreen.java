import java.awt.Component;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    private Scene scene;
    private int numRows = 8;
    private int numCols = 8;
    private int numMines = 10;
    private int numClicks = 0;
    StackPane rootPane;
    private boolean[][] gameArray;
    /**main game window of the game.
     * @param args
     */
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        final double appSize = 1000;
        final double appHeight = 950;
        
        showSettings(true);
        startGame();
        
        //settings = new Button("Settings");
        //settings.setOnAction(this::processButtonPress);
        Group myGroup = new Group();
        scene = new Scene(myGroup, appSize, appHeight);
        GridPane root = new GridPane();
        Scene scene = new Scene(root);
        //StackPane rootPane = new StackPane();
        
        for (int i = 0; i < numCols; i++) {
            //FlowPane flow = new FlowPane();
            for (int j = 0; j < numRows; j++) {
                Button temp = new Button();
                temp.setMinWidth(30);
                temp.setMinHeight(30);
                root.add(temp, i, j);
            }
        }
        contents();
        
        for (Node element : root.getChildren()) {
            element.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    // TODO Auto-generated method stub
                    int currRow = GridPane.getRowIndex(element);
                    int currCol = GridPane.getColumnIndex(element);
                    if(event.isPrimaryButtonDown()) {
                        // check mines here
                        if(numClicks == 0) {
                            
                        }
                        numClicks++;
                    } else if (event.isSecondaryButtonDown()) {
                        // add red flags here
                    }                    
                }
            });
        }
        
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /** Check if the array list contains a mine or not.
     * 
     */
    public boolean checkMine(int row, int col) {
        return gameArray[row][col];
    }
    /** Print out the contents of the game array (for debugging)
     * 
     */
    public void contents() {
        for (int i = 0; i < numRows; i++) {
            System.out.println("Row " + (i + 1) + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print(gameArray[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }
    /**main method of the program.
     * @param args unused
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }
    /** Settings menu to configure the difficulty of the game.*/
    private void showSettings(boolean first) {
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
        if(!first) {
            
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
        // true if the cell contains a mine, otherwise false
        gameArray = new boolean[numRows][numCols];
        
        // instantiate gameArray cells to false, then do algorithm to add mines to screen
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                gameArray[i][j] = false;
            }
        }
        // randomly add mines to the screen
        Random rngRow = new Random();
        Random rngCol = new Random();
        int randMineRow = 0;
        int randMineCol = 0;
        for (int k = 0; k < numMines; k++) {
            do {
                randMineRow = rngRow.nextInt(numRows);
                randMineCol = rngCol.nextInt(numCols);
            } while (gameArray[randMineRow][randMineCol] != false);
            gameArray[randMineRow][randMineCol] = true;
        }
    }
    public void processButtonPress(ActionEvent event, int row, int col) {
        System.out.println("Row = " + row);
        System.out.println("Col = " + col);
    }
}

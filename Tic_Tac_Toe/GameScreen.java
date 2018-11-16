import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * 
 */
import javafx.scene.control.ButtonType;

/**
 * @author jeffr
 *
 */
public class GameScreen extends Application {
    /**Array to hold which boxes have been already selected.*/
    String[] myBoxes = new String[9];
    /**Boolean to check whose turn it is.*/
    boolean firstPlayerTurn;
    /**Display text to the player.*/
    Text displayText;
    Group myGroup;
    String winPlayer;
    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        final double appSize = 800;
        final double appHeight = 900;
        final double divider = 3;
        
        Line vertLeft = new Line(0, appSize / divider, appSize, appSize / divider);
        Line vertRight = new Line(0, appSize * 2 / divider, appSize, appSize * 2 / divider);
        Line horzUp = new Line(appSize / divider, 0, appSize / divider, appSize);
        Line horzDown = new Line(appSize * 2 / divider, 0, appSize * 2 / divider, appSize);
        
        vertLeft.setStroke(Color.BLACK);
        vertRight.setStroke(Color.BLACK);
        horzUp.setStroke(Color.BLACK);
        horzDown.setStroke(Color.BLACK);        
        
        for (int i = 0; i < 9; i++) {
            myBoxes[i] = "empty";
        }
        firstPlayerTurn = true;
        Font myFont = new Font("Serif", 20);
        displayText = new Text(10, 850, "Player 1's Turn");
        displayText.setFont(myFont);
        Text descText = new Text(400, 850, "Player 1 = X, Player 2 = O");
        descText.setFont(myFont);
        myGroup = new Group(vertLeft, vertRight, horzUp, horzDown, displayText, descText);
        
        Scene scene = new Scene(myGroup, appSize, appHeight);

        scene.setOnMouseClicked(this::processMouseClick);
        
        primaryStage.setTitle("Tic-Tac-Toe!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }
    /** Method used to reset the variables
     */
    public void resetGame() {
        final double appSize = 800;
        final double divider = 3;
        Line vertLeft = new Line(0, appSize / divider, appSize, appSize / divider);
        Line vertRight = new Line(0, appSize * 2 / divider, appSize, appSize * 2 / divider);
        Line horzUp = new Line(appSize / divider, 0, appSize / divider, appSize);
        Line horzDown = new Line(appSize * 2 / divider, 0, appSize * 2 / divider, appSize);
        
        vertLeft.setStroke(Color.BLACK);
        vertRight.setStroke(Color.BLACK);
        horzUp.setStroke(Color.BLACK);
        horzDown.setStroke(Color.BLACK);        
        
        for (int i = 0; i < 9; i++) {
            myBoxes[i] = "empty";
        }
        firstPlayerTurn = true;
        Font myFont = new Font("Serif", 20);
        displayText = new Text(10, 850, "Player 1's Turn");
        displayText.setFont(myFont);
        Text descText = new Text(400, 850, "Player 1 = X, Player 2 = O");
        descText.setFont(myFont);
        myGroup.getChildren().add(vertLeft);
        myGroup.getChildren().add(vertRight);
        myGroup.getChildren().add(horzUp);
        myGroup.getChildren().add(horzDown);
        myGroup.getChildren().add(displayText);
        myGroup.getChildren().add(descText);
    }
    /** Method to add a red line to the winning line.
     * @param winLine is the line number that won
     */
    public void makeLine(int winLine) {
        int xLineStart;
        int yLineStart;
        int xLineEnd;
        int yLineEnd;
        switch (winLine) {
        case 1:
            xLineStart = 0;
            yLineStart = 130;
            xLineEnd = 800;
            yLineEnd = 130;
            break;
        case 2:
            xLineStart = 0;
            yLineStart = 390;
            xLineEnd = 800;
            yLineEnd = 390;
            break;
        case 3:
            xLineStart = 0;
            yLineStart = 650;
            xLineEnd = 800;
            yLineEnd = 650;
            break;
        case 4:
            xLineStart = 130;
            yLineStart = 0;
            xLineEnd = 130;
            yLineEnd = 800;
            break;
        case 5:
            xLineStart = 390;
            yLineStart = 0;
            xLineEnd = 390;
            yLineEnd = 800;
            break;
        case 6:
            xLineStart = 650;
            yLineStart = 0;
            xLineEnd = 650;
            yLineEnd = 800;
            break;
        case 7:
            xLineStart = 0;
            yLineStart = 0;
            xLineEnd = 800;
            yLineEnd = 800;
            break;
        case 8:
            xLineStart = 800;
            yLineStart = 0;
            xLineEnd = 0;
            yLineEnd = 800;
            break;
        default:
            xLineStart = 0;
            yLineStart = 0;
            xLineEnd = 0;
            yLineEnd = 0;
            break;
        }
        Line markLine = new Line(xLineStart, yLineStart, xLineEnd, yLineEnd);
        markLine.setStroke(Color.RED);
        markLine.setStrokeWidth(6);
        myGroup.getChildren().add(markLine);
        Alert gameReset = new Alert(AlertType.CONFIRMATION);
        
        if (firstPlayerTurn) {
            gameReset.setHeaderText("End of the Game, Player 2 Won");
        } else {
            gameReset.setHeaderText("End of the Game, Player 1 Won");
        }
        gameReset.setContentText("Would you like to play another?");
        Optional<ButtonType> another = gameReset.showAndWait();
        if (another.get() == ButtonType.OK) {
            myGroup.getChildren().clear();
            resetGame();
        } else {
            Platform.exit();
        }
        
    }
    /** Method to check line 1.
     * @return boolean if it's a win or not
     */
    public boolean check1() {
        if(myBoxes[0].equals(myBoxes[1]) && myBoxes[0].equals(myBoxes[2]) 
                && !myBoxes[0].equals("empty")) {
            makeLine(1);
            return true;
        }
        return false;
    }
    /** Method to check line 2.
     * @return boolean if it's a win or not
     */
    public boolean check2() {
        if(myBoxes[3].equals(myBoxes[4]) && myBoxes[3].equals(myBoxes[5]) 
                && !myBoxes[3].equals("empty")) {
            makeLine(2);
            return true;
        }
        return false;
    }
    /** Method to check line 3.
     * @return boolean if it's a win or not
     */
    public boolean check3() {
        if (myBoxes[6].equals(myBoxes[7]) && myBoxes[6].equals(myBoxes[8]) 
                && !myBoxes[6].equals("empty")) {
            makeLine(3);
            return true;
        }
        return false;
    }
    /** Method to check line 4.
     * @return boolean if it's a win or not
     */
    public boolean check4() {
        if (myBoxes[0].equals(myBoxes[3]) && myBoxes[0].equals(myBoxes[6]) 
                && !myBoxes[0].equals("empty")) {
            makeLine(4);
            return true;
        }
        return false;
    }
    /** Method to check line 5.
     * @return boolean if it's a win or not
     */
    public boolean check5() {
        if (myBoxes[1].equals(myBoxes[4]) && myBoxes[1].equals(myBoxes[7]) 
                && !myBoxes[1].equals("empty")) {
            makeLine(5);
            return true;
        }
        return false;
    }
    /** Method to check line 6.
     * @return boolean if it's a win or not
     */
    public boolean check6() {
        if (myBoxes[2].equals(myBoxes[5]) && myBoxes[2].equals(myBoxes[8]) 
                && !myBoxes[2].equals("empty")) {
            makeLine(6);
            return true;
        }
        return false;
    }
    /** Method to check line 7.
     * @return boolean if it's a win or not
     */
    public boolean check7() {
        if (myBoxes[0].equals(myBoxes[4]) && myBoxes[0].equals(myBoxes[8]) 
                && !myBoxes[0].equals("empty")) {
            makeLine(7);
            return true;
        }
        return false;
    }
    /** Method to check line 8.
     * @return boolean if it's a win or not
     */
    public boolean check8() {
        if (myBoxes[2].equals(myBoxes[4]) && myBoxes[2].equals(myBoxes[6]) 
                && !myBoxes[2].equals("empty")) {
            makeLine(8);
            return true;
        }
        return false;
    }
    
    /**Method to check which box was clicked
     * @param xLoc is the x coordinate clicked
     * @param yLoc is the y coordinate clicked
     * @param int corresponding to which box was clicked
     */
    public int checkBox(double xLoc, double yLoc) {
        final double appSize = 900;
        final double divider = 3;
        if (xLoc < (appSize / divider) && xLoc > 0) {// Check if we're in the first column
            if (yLoc < (appSize / divider) && yLoc > 0) {
                return 0;
            }
            else if (yLoc > (appSize / divider) && yLoc < (appSize * 2 / divider)) {
                return 3;
            }
            else if (yLoc > (appSize * 2 / divider) && yLoc < appSize) {
                return 6;
            }
        } else if (xLoc > (appSize / divider) && xLoc < (appSize * 2 / divider)) {// Check if we're in the second column
            if (yLoc < (appSize / divider) && yLoc > 0) {
                return 1;
            }
            else if (yLoc > (appSize / divider) && yLoc < (appSize * 2 / divider)) {
                return 4;
            }
            else if (yLoc > (appSize * 2 / divider) && yLoc < appSize) {
                return 7;
            }
        } else if (xLoc > (appSize * 2 / divider) && xLoc < appSize) {//Check if we're in the third column
            if (yLoc < (appSize / divider) && yLoc > 0) {
                return 2;
            }
            else if (yLoc > (appSize / divider) && yLoc < (appSize * 2 / divider)) {
                return 5;
            }
            else if (yLoc > (appSize * 2 / divider) && yLoc < appSize) {
                return 8;
            }
        }
        return -1;
    }
    /**Method to process a mouse click event.
     * @param event is the event on the javaFX window
     */
    public void processMouseClick(MouseEvent event) {
        double clickX = event.getX();
        double clickY = event.getY();
        int boxSelect = checkBox(clickX, clickY);
        //System.out.println("Box " + (boxSelect + 1) + " clicked!");
        if (myBoxes[boxSelect].equals("empty")) {
            //Add either X or O
            if (firstPlayerTurn) {//first player turn, add O
                addX(boxSelect);
                myBoxes[boxSelect] = "X";
                displayText.setText("Player 2's Turn");
            } else {//Second player turn, add X
                addO(boxSelect);
                myBoxes[boxSelect] = "O";
                displayText.setText("Player 1's Turn");
            }
            firstPlayerTurn = !firstPlayerTurn;   
        }
        if (check1() || check2() || check3() || check4() || check5() || check6() || check7() || check8()) {
            
        }
        boolean boxesFull = true;
        for (int i = 0; i < myBoxes.length; i++) {
            if (!myBoxes[i].equals("X") && !myBoxes[i].equals("O")) {
                boxesFull = false;
            }
        }
        if (boxesFull) {
            Alert gameReset = new Alert(AlertType.CONFIRMATION);
            
            gameReset.setHeaderText("End of the Game, it is a draw.");
            gameReset.setContentText("Would you like to play another?");
            Optional<ButtonType> another = gameReset.showAndWait();
            if (another.get() == ButtonType.OK) {
                myGroup.getChildren().clear();
                resetGame();
            } else {
                Platform.exit();
            }
        }
    }
    /**Method to add X to the specified box.
     * @param box is an integer corresponding to which box was selected
     */
    public void addX(int box) {
        int yLine;
        int xLine;
        int offset = 250;
        if (box == 0 || box == 1 || box == 2) {
            yLine = 5;
        } else if (box == 3 || box == 4 || box == 5) {
            yLine = 271;
        } else {
            yLine = 557;
        }
        if (box == 0 || box == 3 || box == 6) {
            xLine = 5;
        } else if (box == 1 || box == 4 || box == 7) {
            xLine = 271;
        } else {
            xLine = 557;
        }
        Line xLineLeft = new Line(xLine, yLine, xLine + offset, yLine + offset);
        Line xLineRight = new Line(xLine + offset, yLine, xLine, yLine + offset);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        myGroup.getChildren().add(xLineLeft);
        myGroup.getChildren().add(xLineRight);
    }
    /**Method to add O to the specified box.
     * @param box is an integer corresponding to which box was selected
     */
    public void addO(int box) {
        int centerPointX;
        int centerPointY;
        if (box == 0 || box == 1 || box == 2) {
            centerPointY = 130;
        } else if (box == 3 || box == 4 || box == 5) {
            centerPointY = 390;
        } else {
            centerPointY = 650;
        }
        if (box == 0 || box == 3 || box == 6) {
            centerPointX = 130;
        } else if (box == 1 || box == 4 || box == 7) {
            centerPointX = 390;
        } else {
            centerPointX = 650;
        }
        Circle myCircle = new Circle(centerPointX, centerPointY, 100);
        myCircle.setFill(Color.TRANSPARENT);
        myCircle.setStroke(Color.BLACK);
        myGroup.getChildren().add(myCircle);
    }
}

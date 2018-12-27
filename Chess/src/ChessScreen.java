import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**Displays the chess board.
 * @author jeffr
 * @version 1.0
 *
 */
public class ChessScreen extends Application {
    /** 3rd row of the chessboard. */
    private final int thirdR = 3;
    /** 4th row of the chessboard. */
    private final int fourthR = 4;
    /** 5th row of the chessboard. */
    private final int fifthR = 5;
    /** 6th row of the chessboard. */
    private final int sixthR = 6;
    /** 7th row of the chessboard. */
    private final int seventhR = 7;
    /** Sets the rows for the chessboard. */
    private final int numRows = 8;
    /** Sets the cols for the chessboard. */
    private final int numCols = 8;
    /** Sets the button size. */
    private final int buttonSize = 100;
    /** Sets the root of the scene. */
    private GridPane root;
    /**main game window of the game.
     * @param primaryStage of the board
     * @throws Exception 
     */
    public void start(Stage primaryStage) throws Exception {
        root = new GridPane();
        Scene scene = new Scene(root);
        setButtonColors();
        
        primaryStage.setTitle("Chess");
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
    /** Used to set the button colors and background images (pieces).*/
    private void setButtonColors() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Button temp = new Button();
                temp.setMinWidth(buttonSize);
                temp.setMinHeight(buttonSize);
                String bgColor = "";
                if (j == 1) {
                    if (i % 2 == 0) {
                        bgColor = "grey";
                    } else {
                        bgColor = "white";
                    }
                    temp.setStyle("-fx-background-color: " + bgColor + ";"
                            + "-fx-background-image: url('img/whitePawn.png');"
                            + "-fx-background-size: 100px 100px;");
                    root.add(temp, i, j);
                } else if (j == 0) {
                    setPieces(true);
                } else if (j == sixthR) {
                    if (i % 2 == 0) {
                        bgColor = "white";
                    } else {
                        bgColor = "grey";
                    }
                    temp.setStyle("-fx-background-color: " + bgColor + ";"
                            + "-fx-background-image: url('img/blackPawn.png');"
                            + "-fx-background-size: 100px 100px;");
                    root.add(temp, i, j);
                } else if (j == seventhR) {
                    setPieces(false);
                } else if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        temp.setStyle("-fx-background-color: white;");
                    } else {
                        temp.setStyle("-fx-background-color: grey");
                    }
                    root.add(temp, i, j);
                } else {
                    if (j % 2 == 0) {
                        temp.setStyle("-fx-background-color: grey");
                    } else {
                        temp.setStyle("-fx-background-color: white");
                    }
                    root.add(temp, i, j);
                }
            }
        }
    }
    /** Sets the background images to the corresponding pieces.
     *  @param firstRow checks whether it is the first or last row
     */
    private void setPieces(boolean firstRow) {
        String bgColor = "";
        String pColor = "";
        String pName = "";
        int currRow = 0;
        if (firstRow) {
            bgColor = "white";
            pColor = "white";
            currRow = 0;
        } else {
            bgColor = "grey";
            pColor = "black";
            currRow = seventhR;
        }
        for (int i = 0; i < numCols; i++) {
            Button temp = new Button();
            temp.setMinWidth(buttonSize);
            temp.setMinHeight(buttonSize);
            if (i == 0 || i == seventhR) {
                pName = "Rook";
            } else if (i == 1 || i == sixthR) {
                pName = "Knight";
            } else if (i == 2 || i == fifthR) {
                pName = "Bishop";
            } else if (i == thirdR) {
                pName = "Queen";
            } else if (i == fourthR) {
                pName = "King";
            }
            temp.setStyle("-fx-background-color: " + bgColor + ";"
                    + "-fx-background-image: url('img/" 
                    + pColor + pName + ".png');"
                    + "-fx-background-size: 100px 100px;");
            root.add(temp, i, currRow);
            if (bgColor.equals("white")) {
                bgColor = "grey";
            } else {
                bgColor = "white";
            }
            
        }
        
    }
}

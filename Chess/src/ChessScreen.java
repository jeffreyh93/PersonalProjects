import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
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
    /** Sets the font size. */
    private final int fontSize = 30;
    /** Sets the root of the scene. */
    private GridPane root;
    /** Player's turn label.*/
    private Label turnLabel = new Label();
    /** Player's turn string.*/
    private String playerTurn;
    /** Checking if it is the initial mouse click. */
    private boolean initial = true;
    /** 2D array to hold the location of the pieces. */
    private String[][] gameBoardP;
    /** 2D array to hold the location of the colors. */
    private String[][] gameBoardC;
    /**main game window of the game.
     * @param primaryStage of the board
     * @throws Exception 
     */
    public void start(Stage primaryStage) throws Exception {
        root = new GridPane();
        Scene scene = new Scene(root);
        playerTurn = "White";
        turnLabel.setText(playerTurn + " player's turn!");
        Font font = new Font(fontSize);
        turnLabel.setFont(font);
        root.add(turnLabel, 0, 0, numCols, 1);
        gameBoardP = new String[numRows][numCols];
        gameBoardC = new String[numRows][numCols];
        setButtonColors();
        
        for (Node element : root.getChildren()) {
            element.addEventFilter(MouseEvent.MOUSE_PRESSED, 
                    new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    // TODO Auto-generated method stub
                    int clickedR = GridPane.getRowIndex(element) - 1;
                    int clickedC = GridPane.getColumnIndex(element);
                    
                    if (event.isPrimaryButtonDown()) {
                        if (initial) {
                            if (gameBoardC[clickedR][clickedC]
                                    .equals(playerTurn)) {
                                processPiece(clickedR, clickedC);
                                initial = false;
                            }
                        } else {
                            initial = true;
                            if (playerTurn.equals("White")) {
                                playerTurn = "Black";
                            } else {
                                playerTurn = "White";
                            }
                            turnLabel.setText(playerTurn + " player's turn!");
                        }
                    }
                }
            });
        }
        printArr();
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
                    root.add(temp, i, j + 1);
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
                    root.add(temp, i, j + 1);
                } else if (j == seventhR) {
                    setPieces(false);
                } else if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        temp.setStyle("-fx-background-color: white;");
                    } else {
                        temp.setStyle("-fx-background-color: grey");
                    }
                    root.add(temp, i, j + 1);
                } else {
                    if (j % 2 == 0) {
                        temp.setStyle("-fx-background-color: grey");
                    } else {
                        temp.setStyle("-fx-background-color: white");
                    }
                    root.add(temp, i, j + 1);
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
            root.add(temp, i, currRow + 1);
            if (bgColor.equals("white")) {
                bgColor = "grey";
            } else {
                bgColor = "white";
            }
        }
        newBoard();
    }
    /** Creates the initial board in a 2D array.*/
    public void newBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == 0 || i == seventhR) {
                    if (j == 0 || j == seventhR) {
                        gameBoardP[i][j] = "Rook";
                    } else if (j == 1 || j == sixthR) {
                        gameBoardP[i][j] = "Knight";
                    } else if (j == 2 || j == fifthR) {
                        gameBoardP[i][j] = "Bishop";
                    } else if (j == thirdR) {
                        gameBoardP[i][j] = "Queen";
                    } else if (j == fourthR) {
                        gameBoardP[i][j] = "King";
                    }
                    
                } else if (i == 1 || i == sixthR) {
                    gameBoardP[i][j] = "Pawn";
                } else {
                    gameBoardP[i][j] = "0";
                }
                if (i == 0 || i == 1) {
                    gameBoardC[i][j] = "White";
                } else if (i == sixthR || i == seventhR) {
                    gameBoardC[i][j] = "Black";
                } else {
                    gameBoardC[i][j] = "0";
                }
            }
        }        
    }
    /** Prints out the contents of the array.*/
    public void printArr() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(gameBoardP[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(gameBoardC[i][j] + "\t");
            }
            System.out.println("");
        }
    }
    /** Processes the piece selected given that the correct color was chosen.
     * 
     * @param row selected row
     * @param col selected col
     */
    public void processPiece(int row, int col) {
        String myPiece = gameBoardP[row][col];
        if (myPiece.equals("Pawn")) {
            processPawn();
        }
        System.out.println(myPiece + ", " + gameBoardC[row][col]);
    }
    /** Process pawn click. */
    public void processPawn() {
        
    }
}

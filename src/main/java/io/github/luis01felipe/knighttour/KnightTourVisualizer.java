package io.github.luis01felipe.knighttour;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class KnightTourVisualizer extends Application {

    private static final int SIZE = 8;
    private static final int TILE_SIZE = 80;
    private static final int[] rowMoves = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] colMoves = {1, 2, 2, 1, -1, -2, -2, -1};

    private int[][] board = new int[SIZE][SIZE];
    private GridPane grid = new GridPane();
    private int step = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        solve(0, 0, step);
        drawBoard();

        Scene scene = new Scene(grid, SIZE * TILE_SIZE, SIZE * TILE_SIZE);
        primaryStage.setTitle("Knight's Tour Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();

        animateTour();
    }

    private boolean solve(int row, int col, int moveCount) {
        board[row][col] = moveCount;
        if (moveCount == SIZE * SIZE) return true;

        for (int i = 0; i < 8; i++) {
            int nextRow = row + rowMoves[i];
            int nextCol = col + colMoves[i];

            if (isValid(nextRow, nextCol)) {
                if (solve(nextRow, nextCol, moveCount + 1)) {
                    return true;
                }
            }
        }

        board[row][col] = 0;
        return false;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < SIZE && col < SIZE && board[row][col] == 0;
    }

    private void drawBoard() {
        grid.getChildren().clear();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                boolean isLight = (row + col) % 2 == 0;
                tile.setFill(isLight ? Color.BEIGE : Color.BROWN);

                Text number = new Text("");
                grid.add(tile, col, row);
                grid.add(number, col, row);
            }
        }
    }

    private void animateTour() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(SIZE * SIZE);

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), event -> {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    if (board[row][col] == step) {
                        Rectangle tile = new Rectangle(TILE_SIZE, TILE_SIZE);
                        tile.setFill(Color.LIGHTBLUE);
                        Text number = new Text(String.valueOf(step));
                        grid.add(tile, col, row);
                        grid.add(number, col, row);
                    }
                }
            }
            step++;
        }));

        timeline.play();
    }
}

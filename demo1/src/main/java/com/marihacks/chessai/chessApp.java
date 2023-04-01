package com.marihacks.chessai;

import com.marihacks.chessai.game.ui.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class chessApp extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("CHESS AI");
        Board board = new Board();
        primaryStage.setScene(new Scene(board, 500, 500));
        board.adjustImage();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


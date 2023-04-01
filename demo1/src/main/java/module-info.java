module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.marihacks.chessai to javafx.fxml;
    exports com.marihacks.chessai;
    exports com.marihacks.chessai.game.pieces;
    opens com.marihacks.chessai.game.pieces to javafx.fxml;
    exports com.marihacks.chessai.game.ui;
    opens com.marihacks.chessai.game.ui to javafx.fxml;
}

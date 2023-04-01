package com.marihacks.chessai.game.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Grid extends StackPane {

    private final int SIZE = 2;
    private Image image;

    Background background;
    Background possibilityBackground;

    public Grid(Background background) {
        this.background = background;
        this.possibilityBackground = background;
        this.setBackground(background);
        this.setPrefSize(SIZE * 50, SIZE * 50);
        setGrid();
    }

    private void setGrid() {
        this.setOnMouseEntered(e -> {
            this.setBackground(Background.fill(Color.LIGHTGREEN));
        });
        this.setOnMouseExited(e -> {

            this.setBackground(this.possibilityBackground);

        });
    }

    public void setImage(Image image) {
        this.image = image;
        this.getChildren().clear();
        this.getChildren().add(new javafx.scene.image.ImageView(image));
    }
}

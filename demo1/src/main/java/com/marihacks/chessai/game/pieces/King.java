package com.marihacks.chessai.game.pieces;

import com.marihacks.chessai.game.ui.Node;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public static final double WORTH = 100;

    @Override
    public String getImageURL(boolean white) {
        if (white) {
            return ("/com/marihacks/wikimedia/king_white.png");
        } else {
            return ("/com/marihacks/wikimedia/king_black.png");
        }
    }

    @Override
    public List<int[]> attackingSquares(Node node) {
        List<int[]> attackingSquares = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                int[] move = new int[]{x+this.getPoint()[0], y+this.getPoint()[1]};
                if (node.isInBounds(move)) {
                    //check if the square is occupied by a piece of the same color
                    if (node.board[move[0]][move[1]] == null
                            || node.board[move[0]][move[1]].isWhite() != isWhite()) {
                        attackingSquares.add(move);
                    }
                }
            }
        }
        return attackingSquares;
    }

    @Override
    public List<Piece[][]> calculatePosition(Node node) {
        List<Piece[][]> positions = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                int[] move = new int[]{x, y};
                if (node.isInBounds(move)) {
                    Piece[][] position = node.board.clone();
                    if (position[move[0]][move[1]] == null || position[move[0]][move[1]].isWhite() != isWhite()) {
                        position[move[0]][move[1]] = this;
                        position[getPoint()[0]][getPoint()[1]] = null;
                        positions.add(position);
                    }
                }
            }

        }
        return positions;
    }

    public King(int[] point, boolean white) {
        super(WORTH, white, point);
    }

}

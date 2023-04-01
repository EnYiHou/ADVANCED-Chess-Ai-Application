package com.marihacks.chessai.game.pieces;

import com.marihacks.chessai.game.ui.Node;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public static final double WORTH = 3;
    private static int[][] directions =
            {{1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};


    public Knight(int[] point, boolean white) {
        super(WORTH, white, point);
    }

    @Override
    public String getImageURL(boolean white) {
        if (white) {
            return ("/com/marihacks/wikimedia/knight_white.png");
        } else {
            return ("/com/marihacks/wikimedia/knight_black.png");
        }
    }

    @Override
    public List<int[]> attackingSquares(Node node) {
        // print current position of this piece
        System.out.println("Knight at " + getPoint()[0] + ", " + getPoint()[1]);


        List<int[]> attackingSquares = new ArrayList<>();
        for (int[] direction : directions) {
            int[] newPoint = {getPoint()[0] + direction[0], getPoint()[1] + direction[1]};
            if (node.isInBounds(newPoint))
                if (node.board[newPoint[0]][newPoint[1]] == null || node.board[newPoint[0]][newPoint[1]].isWhite() != this.isWhite())
                    attackingSquares.add(newPoint);

        }

        // print attacking squares
        for (int[] square : attackingSquares) {
            System.out.println("Attacking square at " + square[0] + ", " + square[1]);
        }
        return attackingSquares;
    }

    @Override
    public List<Piece[][]> calculatePosition(Node node) {
        List<Piece[][]> possiblePositions = new ArrayList<>();
        for (int[] direction : directions) {
            int[] newPoint = {getPoint()[0] + direction[0], getPoint()[1] + direction[1]};
            if (newPoint[0] >= 0 && newPoint[0] < 8 && newPoint[1] >= 0 && newPoint[1] < 8) {
                Piece[][] position = node.board;
                if (position[newPoint[0]][newPoint[1]] == null || position[newPoint[0]][newPoint[1]].isWhite() != this.isWhite()) {
                    Piece[][] newPosition = new Piece[8][8];
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (position[i][j] != null) {
                                newPosition[i][j] = position[i][j];
                            }
                        }
                    }
                    newPosition[newPoint[0]][newPoint[1]] = this;
                    newPosition[getPoint()[0]][getPoint()[1]] = null;
                    possiblePositions.add(newPosition);
                }
            }
        }
        return possiblePositions;
    }

}

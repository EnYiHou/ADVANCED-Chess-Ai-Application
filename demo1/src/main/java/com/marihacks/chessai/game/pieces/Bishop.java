package com.marihacks.chessai.game.pieces;

import com.marihacks.chessai.game.ui.Node;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public static final double WORTH = 3.5;
    public static ArrayList<ArrayList<int[]>> possibleMoves = new ArrayList<ArrayList<int[]>>();

    public static void setPossibleMoves() {
        for (int i = 0; i < 4; i++) {
            ArrayList<int[]> direction = new ArrayList<int[]>();
            for (int j = 1; j < 8; j++) {
                int[] move = new int[2];
                // 0 = up right, 1 = up left, 2 = down right, 3 = down left
                if (i == 0) {
                    move[0] = j;
                    move[1] = j;
                } else if (i == 1) {
                    move[0] = -j;
                    move[1] = j;
                } else if (i == 2) {
                    move[0] = j;
                    move[1] = -j;
                } else if (i == 3) {
                    move[0] = -j;
                    move[1] = -j;
                }
                direction.add(move);
            }
            possibleMoves.add(direction);
        }
    }

    public Bishop(int[] point, boolean isWhite) {
        super(WORTH, isWhite, point);
    }

    @Override
    public String getImageURL(boolean white) {
        if (white) {
            return ("/com/marihacks/wikimedia/bishop_white.png");
        } else {
            return ("/com/marihacks/wikimedia/bishop_black.png");
        }
    }

    @Override
    public List<int[]> attackingSquares(Node node) {
        List<int[]> attackingSquares = new ArrayList<>();
        for (ArrayList<int[]> direction : possibleMoves) {

            // Check if this direction is blocked
            boolean bloked = false;
            for (int[] move : direction) {
                if (!bloked) {
                    int newX = this.getPoint()[0] + move[0];
                    int newY = this.getPoint()[1] + move[1];
                    // check if move is in bounds
                    if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
                        bloked = true;
                    }

                    // add board to possibleBoards if move is valid
                    if (!bloked) {
                        if (node.board[newX][newY] == null || node.board[newX][newY].isWhite() != isWhite()) {
                            attackingSquares.add(new int[]{newX, newY});
                        }
                        // check if move is blocked by a piece

                        if (node.board[newX][newY] != null) {
                            bloked = true;
                        }
                    }
                    }


            }

        }
        return attackingSquares;
    }
}

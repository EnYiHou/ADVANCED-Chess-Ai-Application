package com.marihacks.chessai.game.pieces;

import com.marihacks.chessai.game.ui.Node;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public static final double WORTH = 9;

    public static ArrayList<ArrayList<int[]>> possibleMoves = new ArrayList<ArrayList<int[]>>();


    public static void setPossibleMoves() {
        // i : the direction
        for (int i = 0; i < 8; i++) {
            ArrayList<int[]> direction = new ArrayList<int[]>();

            // j : the number of spaces to move
            // For example, if i = 0, j = 2, then the move is 2 spaces to the right
            for (int j = 1; j < 8; j++) {
                int[] move = new int[2];
                // 0 = right, 1 = left, 2 = up, 3 = down, 4 = up right, 5 = up left, 6 = down right, 7 = down left
                if (i == 0) {
                    move[0] = j;
                    move[1] = 0;
                } else if (i == 1) {
                    move[0] = -j;
                    move[1] = 0;
                } else if (i == 2) {
                    move[0] = 0;
                    move[1] = j;
                } else if (i == 3) {
                    move[0] = 0;
                    move[1] = -j;
                } else if (i == 4) {
                    move[0] = j;
                    move[1] = j;
                } else if (i == 5) {
                    move[0] = -j;
                    move[1] = j;
                } else if (i == 6) {
                    move[0] = j;
                    move[1] = -j;
                } else if (i == 7) {
                    move[0] = -j;
                    move[1] = -j;
                }
                direction.add(move);
            }
            possibleMoves.add(direction);
        }
    }

    public Queen(int[] point, boolean white) {
        super(WORTH, white, point);
    }


    @Override
    public String getImageURL(boolean white) {
        if (white) {
            return ("/com/marihacks/wikimedia/queen_white.png");
        } else {
            return ("/com/marihacks/wikimedia/queen_black.png");
        }
    }

    @Override
    public List<int[]> attackingSquares(Node node) {
        ArrayList<int[]> attackingSquares = new ArrayList<int[]>();
        for (ArrayList<int[]> direction : possibleMoves) {

            // Check if this direction is blocked
            boolean bloked = false;
            for (int[] move : direction) {
                if (!bloked) {
                    int newX = getPoint()[0] + move[0];
                    int newY = getPoint()[1] + move[1];
                    // check if move is in bounds
                    if (newX < 0 || newX > 7 || newY < 0 || newY > 7) {
                        bloked = true;
                    }

                    // add board to possibleBoards if move is valid
                    if (!bloked) {
                        // check if move is blocked by a piece of the same color
                        if (node.board[newX][newY] == null || node.board[newX][newY].isWhite() != isWhite()) {
                            attackingSquares.add(new int[]{newX, newY});
                        }

                        // check if move is blocked by a piece of the same color
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

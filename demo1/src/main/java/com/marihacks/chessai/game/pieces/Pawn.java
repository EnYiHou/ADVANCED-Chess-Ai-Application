package com.marihacks.chessai.game.pieces;

import com.marihacks.chessai.game.ui.Node;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public static final double WORTH = 1;

    public boolean moved;


    public Pawn(int[] point, boolean white) {
        super(WORTH, white, point);
        this.moved = false;
    }


    @Override
    public String getImageURL(boolean white) {

        if (white) {
            return (("/com/marihacks/wikimedia/pawn_white.png").toString());
        }
        return (("/com/marihacks/wikimedia/pawn_black.png").toString());
    }

    @Override
    public List<int[]> attackingSquares(Node node) {
        ArrayList<int[]> possibleSquares = new ArrayList<>();
        int jumpPossible;
        if (this.moved == false) {
            jumpPossible = 2;
        } else {
            jumpPossible = 1;
        }
        for (int i = 1; i <= jumpPossible; i++) {


            if (this.getPoint()[1] - i >= 0 && this.isWhite()) {

                //if white
                if (node.board[this.getPoint()[0]][this.getPoint()[1] - i] == null) {
                    int[] square = new int[2];
                    square[0] = this.getPoint()[0];
                    square[1] = this.getPoint()[1] - i;
                    possibleSquares.add(square);
                }

                if (i == 1) {
                    //check capture left
                    if (this.getPoint()[0] - 1 >= 0 &&
                            node.board[this.getPoint()[0] - 1][this.getPoint()[1] - i] != null
                            && !node.board[this.getPoint()[0] - 1][this.getPoint()[1] - i].isWhite()) {
                        int[] square = new int[2];
                        square[0] = this.getPoint()[0] - 1;
                        square[1] = this.getPoint()[1] - i;
                        possibleSquares.add(square);
                    }
                    //check capture right
                    if (this.getPoint()[0] + 1 < 8 &&
                            node.board[this.getPoint()[0] + 1][this.getPoint()[1] - i] != null
                            && !node.board[this.getPoint()[0] + 1][this.getPoint()[1] - i].isWhite()) {
                        int[] square = new int[2];
                        square[0] = this.getPoint()[0] + 1;
                        square[1] = this.getPoint()[1] - i;
                        possibleSquares.add(square);
                    }
                }

            }
            // if black
            else if (this.getPoint()[1] + i < 8 && !this.isWhite()) {
                if (node.board[this.getPoint()[0]][this.getPoint()[1] + i] == null) {
                    int[] square = new int[2];
                    square[0] = this.getPoint()[0];
                    square[1] = this.getPoint()[1] + i;
                    possibleSquares.add(square);
                }

                if (i == 1) {
                    //check capture left
                    if (this.getPoint()[0] - 1 >= 0 &&
                            node.board[this.getPoint()[0] - 1][this.getPoint()[1] + i] != null
                            && node.board[this.getPoint()[0] - 1][this.getPoint()[1] + i].isWhite()) {
                        int[] square = new int[2];
                        square[0] = this.getPoint()[0] - 1;
                        square[1] = this.getPoint()[1] + i;
                        possibleSquares.add(square);
                    }
                    //check capture right
                    if (this.getPoint()[0] + 1 < 8 &&
                            node.board[this.getPoint()[0] + 1][this.getPoint()[1] + i] != null
                            && node.board[this.getPoint()[0] + 1][this.getPoint()[1] + i].isWhite()) {
                        int[] square = new int[2];
                        square[0] = this.getPoint()[0] + 1;
                        square[1] = this.getPoint()[1] + i;
                        possibleSquares.add(square);
                    }
                }

            }
        }


        return possibleSquares;

    }

}

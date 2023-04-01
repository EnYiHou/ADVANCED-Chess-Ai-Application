package com.marihacks.chessai.game.pieces;

import com.marihacks.chessai.game.ui.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {


    private double worth;
    private int[] point;


    private boolean white;

    public abstract String getImageURL(boolean white);


    public Piece(double worth, boolean white, int[] point) {
        this.white = white;
        this.point = point;
        this.worth = worth;
    }

    public abstract List<int[]> attackingSquares(Node node);

    /**
     * Calculates all possible positions for this piece
     * This function is only called for the normal pieces,
     * not the pawns, knights or kings
     *
     * @param node the node to calculate the position for
     * @return a list of all possible boards after this piece
     * has moved
     */
    public List<Piece[][]> calculatePosition(Node node) {

        List<int[]> movesPossible = attackingSquares(node);
        List<Piece[][]> possibleBoards = new ArrayList<>();
        for (int[] move : movesPossible) {
            Piece[][] newBoard = new Piece[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    newBoard[i][j] = node.board[i][j];
                }
            }
            newBoard[move[0]][move[1]] = newBoard[this.getPoint()[0]][this.getPoint()[1]];
            newBoard[this.getPoint()[0]][this.getPoint()[1]] = null;
            possibleBoards.add(newBoard);
        }
        return possibleBoards;
    }

    ;

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public int[] getPoint() {
        return point;
    }

    public void setPoint(int[] point) {
        this.point = point;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

}

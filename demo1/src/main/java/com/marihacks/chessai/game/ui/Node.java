package com.marihacks.chessai.game.ui;

import com.marihacks.chessai.game.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Node {


    public Node parent;
    public static final int DEPTH = 10;

    boolean whiteTurn;
    public Piece[][] board;

    private boolean[][] attackedByWhite;
    private boolean[][] attackedByBlack;
    ArrayList<Node> nodes;

    double worthBlackPlayer;
    double worthWhitePlayer;

    public Node(Piece[][] board, boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
        this.board = board;
        nodes = new ArrayList<Node>();
        attackedByBlack = new boolean[8][8];
        attackedByWhite = new boolean[8][8];
    }


    public List<Node> calculatePosition() {
        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null) {
                    if (piece.isWhite()) {
                        continue;
                    }
                    List<Piece[][]> boards = piece.calculatePosition(this);
                    for (Piece[][] board : boards) {
                        Node node = new Node(board, !whiteTurn);
                        node.parent = this;
                        nodes.add(node);
                    }
                }
            }
        }
        return nodes;
    }

    public double calculateWorth() {
        worthBlackPlayer = 0;
        worthWhitePlayer = 0;
        for (Piece[] row : board) {
            for (Piece piece : row) {
                if (piece != null) {
                    if (piece.isWhite()) {
                        worthWhitePlayer += piece.getWorth();
                    } else {
                        worthBlackPlayer += piece.getWorth();
                    }
                }
            }
        }

        return worthWhitePlayer - worthBlackPlayer;

    }

    public boolean isInBounds(int[] point) {
        return point[0] >= 0 && point[0] < 8 && point[1] >= 0 && point[1] < 8;
    }

    //clone
    public Object clone() {
        return new Node(board.clone(), whiteTurn);
    }


}

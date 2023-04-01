package com.marihacks.chessai.game.ui;

import com.marihacks.chessai.game.ai.Minimax;
import com.marihacks.chessai.game.pieces.*;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Board extends GridPane {

    boolean selected = false;

    private Node position;
    private ArrayList<ArrayList<Grid>> grids;

    public Board() {
        Bishop.setPossibleMoves();
        Queen.setPossibleMoves();
        Rook.setPossibleMoves();

        this.grids = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            grids.add(new ArrayList<>());
        }
        this.position = new Node(initialBord(), true);

        this.setAlignment(Pos.CENTER);
        this.setGridLinesVisible(true);

        int count = 0;
        for (int i = 0; i < 8; i++) {
            count++;

            for (int j = 0; j < 8; j++) {
                if ((count % 2) != 0) {
                    Grid grid = new Grid(Background.fill(Color.WHITE));
                    this.add(grid, i, j);
                    grids.get(i).add(grid);

                } else {
                    Grid grid = new Grid(Background.fill(Color.DARKCYAN));
                    this.add(grid, i, j);
                    grids.get(i).add(grid);

                }
                count++;
            }
        }
        setupGrids();

    }


    private List<int[]> allowedMoves;
    private Piece chosenPiece;

    public void setupGrids() {
        for (List<Grid> grid : grids) {
            for (Grid grid1 : grid) {

                grid1.setOnMousePressed(e -> {
                    Piece piece = this.position.board[GridPane.getColumnIndex(grid1)][GridPane.getRowIndex(grid1)];
                    if (piece != null && piece.isWhite()) {

                        selected = true;
                        chosenPiece = piece;
                        allowedMoves = piece.attackingSquares(this.position);

                        removeColor();

                        for (int[] allowedMove : allowedMoves) {
                            Grid target = grids.get(allowedMove[0]).get(allowedMove[1]);
                            target.setBackground(Background.fill(Color.ORANGERED));
                            target.possibilityBackground = Background.fill(Color.ORANGERED);
                        }
                    } else {
                        if (selected) {
                            selected = false;
                            removeColor();

                            // move this piece to this tile if it is allowed
                            // if not, then do nothing
                            for (int[] allowedMove : allowedMoves) {
                                if (allowedMove[0] == GridPane.getColumnIndex(grid1) && allowedMove[1] == GridPane.getRowIndex(grid1)) {
                                    this.position.board[GridPane.getColumnIndex(grid1)][GridPane.getRowIndex(grid1)] = chosenPiece;
                                    this.position.board[chosenPiece.getPoint()[0]][chosenPiece.getPoint()[1]] = null;
                                    System.out.println("changed");
                                    chosenPiece.setPoint(new int[]{GridPane.getColumnIndex(grid1), GridPane.getRowIndex(grid1)});
                                    if (chosenPiece instanceof Pawn) {
                                        ((Pawn) chosenPiece).moved = true;
                                    }
                                    chosenPiece = null;

                                    allowedMoves.clear();

                                    // AI move
                                    this.position.whiteTurn = false;
                                    Node node = Minimax.minimax(this.position, 4,false);
                                    this.position = node;

                                    adjustImage();

                                    break;
                                }
                            }

                        }
                    }
                });
            }
        }

    }

    private void removeColor() {

        for (List<Grid> grid2 : grids) {
            for (Grid grid3 : grid2) {
                if (grid3.getBackground().getFills().get(0).getFill().equals(Color.ORANGERED)) {
                    grid3.setBackground(grid3.background);
                    grid3.possibilityBackground = grid3.background;
                }
            }
        }
    }

    public void adjustImage() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = this.position.board[i][j];
                if (piece != null) {
                    grids.get(i).get(j).getChildren().clear();
                    grids.get(i).get(j).getChildren().add(new ImageView(getClass().getResource(piece.getImageURL(piece.isWhite())).toString()));
                } else {
                    grids.get(i).get(j).setImage(null);
                }
            }
        }
    }

    private Piece[][] initialBord() {
        Piece[][] board = new Piece[8][8];

        // black pieces
        board[0][0] = new Rook(new int[]{0, 0}, false);
        board[1][0] = new Knight(new int[]{1, 0}, false);
        board[2][0] = new Bishop(new int[]{2, 0}, false);
        board[3][0] = new Queen(new int[]{3, 0}, false);
        board[4][0] = new King(new int[]{4, 0}, false);
        board[5][0] = new Bishop(new int[]{5, 0}, false);
        board[6][0] = new Knight(new int[]{6, 0}, false);
        board[7][0] = new Rook(new int[]{7, 0}, false);

        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(new int[]{i, 1}, false);
        }

        // white pieces
        board[0][7] = new Rook(new int[]{0, 7}, true);
        board[1][7] = new Knight(new int[]{1, 7}, true);
        board[2][7] = new Bishop(new int[]{2, 7}, true);
        board[3][7] = new Queen(new int[]{3, 7}, true);
        board[4][7] = new King(new int[]{4, 7}, true);
        board[5][7] = new Bishop(new int[]{5, 7}, true);
        board[6][7] = new Knight(new int[]{6, 7}, true);
        board[7][7] = new Rook(new int[]{7, 7}, true);

        for (int i = 0; i < 8; i++) {
            board[i][6] = new Pawn(new int[]{i, 6}, true);
        }

        return board;
    }
}

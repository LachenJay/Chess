package chessmaster.environment;

import chessmaster.figures.*;

public class Board {
    private final Figure[][] board;
    private boolean isGameOn = false;

    public Board() {
        this.board = new Figure[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                this.board[col][row] = new Dummy(' ', col, row, 'N');
            }
        }
    }

    public void fillBoardWhites() {
        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn('♙', i, 1, 'W');
        }
        board[0][0] = new Rook('♖', 0, 0, 'W');
        board[7][0] = new Rook('♖', 7, 0, 'W');
        board[1][0] = new Knight('♘', 1, 0, 'W');
        board[6][0] = new Knight('♘', 6, 0, 'W');
        board[2][0] = new Bishop('♗', 2, 0, 'W');
        board[5][0] = new Bishop('♗', 5, 0, 'W');
        board[3][0] = new Queen('♕', 3, 0, 'W');
        board[4][0] = new King('♔', 4, 0, 'W');
    }

    public void fillBoardBlks() {
        for (int i = 0; i < 8; i++) {
            board[i][6] = new Pawn('♟', i, 6, 'B');
        }
        board[0][7] = new Rook('♜', 0, 7, 'B');
        board[7][7] = new Rook('♜', 7, 7, 'B');
        board[1][7] = new Knight('♞', 1, 7, 'B');
        board[6][7] = new Knight('♞', 6, 7, 'B');
        board[2][7] = new Bishop('♝', 2, 7, 'B');
        board[5][7] = new Bishop('♝', 5, 7, 'B');
        board[3][7] = new Queen('♛', 3, 7, 'B');
        board[4][7] = new King('♚', 4, 7, 'B');
    }

    public void showBoard() {
        System.out.println("\n    A    B    C    D    E    F    G    H");
        for (int row = 7; row >= 0; row--) {
            System.out.println();
            System.out.print((row + 1) + " |");
            for (int col = 0; col < 8; col++) {
                Figure figure = board[col][row];

                if ((row + col) % 2 == 0) {
                    if (figure.getName() == ' ') {
                        System.out.print("_\u2003_");
                    } else {
                        System.out.print("_" + figure.getName() + "_");
                    }
                } else {
                    if (figure.getName() == ' ') {
                        System.out.print("_\u2003_");
                    } else {
                        System.out.print("_" + figure.getName() + "_");
                    }
                }
                System.out.print("|");
            }
            System.out.print(" " + (8 - row));
        }
        System.out.println("\n \n   A    B    C    D    E    F    G    H");
    }

    public void newGame() {
        fillBoardBlks();
        fillBoardWhites();
        showBoard();
        System.out.println("\nNew game has started. You are playing as white!");
    }

    public void endGame() {
        isGameOn = false;

    }

    public boolean getIsGameOn() {
        return isGameOn;
    }

    public Figure getFigureAtPosition(int x, int y) {
        if (isPositionValid(x, y)) {
            return board[x][y];
        }
        return null;
    }

    public void setFigureAtPosition(int x, int y, Figure figure) {
        if (isPositionValid(x, y)) {
            board[x][y] = figure;
        }
    }

    private boolean isPositionValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}

package chessmaster.figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public class Rook extends Figure {
    private boolean pathIsClear = true;
    private Board board;

    public Rook(char name, int x, int y, char color) {
        super(name, x, y, color);
    }

    @Override
    public boolean move(Board board, Move move) {
        boolean success = false;
        int y = move.getY();
        int x = move.getX();
        int positionX = this.xPosition;
        int positionY = this.yPosition;
        this.board=board;
        if (x == 0 && y != 0) {
            if (y > 0) {
                for (int i = 0; i < y; i++) {
                    if (board.getFigureAtPosition(positionX, positionY + i).getName() == ' ') {
                        pathIsClear = true;
                    } else if (i > 0) {
                        pathIsClear = false;
                    }
                }
                if (board.getFigureAtPosition(positionX, positionY + y).getName() != this.color && pathIsClear && board.getFigureAtPosition(positionX, positionY + y).getColor() != 'N') {
                    take(board, move);
                    return true;
                } else if (board.getFigureAtPosition(positionX, positionY + y).getColor() == 'N') {
                    if (pathIsClear) {
                        return theMove(positionX, positionY, x, y);
                    } else {
                        System.out.println("That move is illegal!");
                        return false;
                    }
                } else if (board.getFigureAtPosition(positionX, positionY + y).getColor() != this.color && pathIsClear) {
                    take(board, move);
                    return true;
                } else {
                    System.out.println("That move is illegal!");
                    return false;
                }
            } else {
                int check = positionY + y;
                int j = 0;
                for (int i = positionY; i > check; i--) {
                    if (board.getFigureAtPosition(positionX, positionY - j).getName() == ' ') {
                        if (pathIsClear) {
                            pathIsClear = true;
                        }
                    } else if (i != positionY) {
                        pathIsClear = false;
                    }
                    j++;
                }
                if (board.getFigureAtPosition(positionX, positionY + y).getName() != this.color && pathIsClear && board.getFigureAtPosition(positionX, positionY + y).getColor() != 'N') {
                    take(board, move);
                    return true;
                } else if (board.getFigureAtPosition(positionX, positionY + y).getColor() == 'N') {
                    if (pathIsClear) {
                        return theMove(positionX, positionY, x, y);
                    } else {
                        System.out.println("That move is illegal!");
                        return false;
                    }
                } else if (board.getFigureAtPosition(positionX, positionY + y).getColor() != this.color && pathIsClear) {
                    take(board, move);
                    return true;
                } else {
                    System.out.println("That move is illegal!");
                    return false;
                }
            }
        } else if (x != 0 && y == 0) {
            if (x > 0) {
                for (int i = 0; i < x; i++) {
                    if (board.getFigureAtPosition(positionX + i, positionY).getName() == ' ') {
                        pathIsClear = true;
                    } else if (i > 0) {
                        pathIsClear = false;
                    }
                }
                if (board.getFigureAtPosition(positionX + x, positionY).getName() != this.color && pathIsClear && board.getFigureAtPosition(positionX + x, positionY).getColor() != 'N') {
                    take(board, move);
                    return true;
                } else if (board.getFigureAtPosition(positionX + x, positionY).getColor() == 'N') {
                    if (pathIsClear) {
                        return theMove(positionX, positionY, x, y);
                    } else {
                        System.out.println("That move is illegal!");
                        return false;
                    }
                } else if (board.getFigureAtPosition(positionX + x, positionY).getColor() != this.color && pathIsClear) {
                    take(board, move);
                    return true;
                } else {
                    System.out.println("That move is illegal!");
                    return false;
                }
            } else {
                int check = positionX + x;
                for (int i = positionX; i > check; i--) {
                    if (board.getFigureAtPosition(positionX - i, positionY).getName() == ' ') {
                        pathIsClear = true;
                    } else if (i != positionX) {
                        pathIsClear = false;
                    }
                }
                if (board.getFigureAtPosition(positionX + x, positionY).getName() != this.color && pathIsClear && board.getFigureAtPosition(positionX + x, positionY).getColor() != 'N') {
                    take(board, move);
                    return true;
                } else if (board.getFigureAtPosition(positionX + x, positionY).getColor() == 'N') {
                    if (pathIsClear) {
                        return theMove(positionX, positionY, x, y);
                    } else {
                        System.out.println("That move is illegal!");
                        return false;
                    }
                } else if (board.getFigureAtPosition(positionX + x, positionY).getColor() != this.color && pathIsClear) {
                    take(board, move);
                    return true;
                } else {
                    System.out.println("That move is illegal!");
                    return false;
                }
            }
        } else {
            System.out.println("That move is illegal!");
            return false;
        }
    }

    private boolean theMove(int positionX, int positionY, int x, int y) {
        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
        Rook f = this;
        board.setFigureAtPosition(positionX, positionY, dummy);
        f.xPosition = positionX + x;
        f.yPosition = positionY + y;
        board.setFigureAtPosition(positionX + x, positionY + y, f);
        return true;
    }


    public void take(Board board, Move move) {
        int positionX = this.xPosition;
        int positionY = this.yPosition;
        int x = move.getX();
        int y = move.getY();

        if (pathIsClear && !(board.getFigureAtPosition(positionX + x, positionY + y).getColor() == this.color)) {
            if (board.getFigureAtPosition(positionX + x, positionY + y).getName() == '♚' || board.getFigureAtPosition(positionX + x, positionY + y).getName() == '♔') {
                board.endGame();
            }
            theMove(xPosition, yPosition, x, y);
        }


    }
}

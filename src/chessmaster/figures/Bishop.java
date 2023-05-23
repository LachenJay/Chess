package chessmaster.figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public class Bishop extends Figure {
    public Bishop(char name, int x, int y, char color) {
        super(name, x, y, color);
    }

    @Override
    public boolean move(Board board, Move move) {
        boolean success = false;
        int positionX = this.xPosition;
        int positionY = this.yPosition;

        int x = move.getX();
        int y = move.getY();
        boolean pathIsClear = true;
        boolean isMine = board.getFigureAtPosition(positionX + x, positionY + y).getColor() == this.color && board.getFigureAtPosition(positionX + x, positionY + y).getName() != 'N';
        int check = 0;
        int yI = 0;

        if (Math.abs(x) - Math.abs(y) != 0) {
            System.out.println("This move is illegal");
            return false;
        }

        if (x < 0) {
            if (y < 0) {
                for (int i = 0; i >= x; i--) {
                    if (board.getFigureAtPosition(positionX + i, positionY + i).getColor() == 'N' && pathIsClear) {
                        pathIsClear = true;
                    } else if ((positionX != (positionX + i)) && (positionY != (positionY + i))) {
                        pathIsClear = false;
                        check = i;
                        break;
                    }
                }
            } else if (y > 0) {
                for (int j = 0; j >= x; j--) {
                    if (board.getFigureAtPosition(positionX + j, positionY + yI).getColor() == 'N' && pathIsClear) {
                        pathIsClear = true;
                    } else if (positionX != positionX + j && positionY != positionY + j) {
                        pathIsClear = false;
                        check = j;
                        break;
                    }
                    yI++;
                }
            }
        } else if (x > 0) {
            if (y < 0) {
                for (int i = 0; i <= x; i++) {
                    if (board.getFigureAtPosition(positionX + i, positionY - i).getColor() == 'N' && pathIsClear) {
                        pathIsClear = true;
                    } else if (positionX != positionX + i && positionY != positionY + i) {
                        pathIsClear = false;
                        check = i;
                        break;
                    }
                }
            } else if (y > 0) {
                for (int j = 0; j <= x; j++) {
                    if (board.getFigureAtPosition(positionX + j, positionY + j).getColor() == 'N' && pathIsClear) {
                        pathIsClear = true;
                    } else if (positionX != positionX + j && positionY != positionY + j) {
                        pathIsClear = false;
                        check = j;
                        break;
                    }
                }
            }
        }

        int absCheck = Math.abs(check);
        if (pathIsClear && board.getFigureAtPosition(positionX + x, positionY + y).getColor() == 'N') {
            return theMove(board, positionX, positionY, x, y);
        } else if (Math.abs(x) == absCheck && !isMine) {
            return take(board, move);
        }
        return false;
    }

    private boolean theMove(Board board, int positionX, int positionY, int x, int y) {
        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
        Bishop f = this;
        board.setFigureAtPosition(positionX, positionY, dummy);
        f.xPosition = positionX + x;
        f.yPosition = positionY + y;
        board.setFigureAtPosition(positionX + x, positionY + y, f);
        return true;
    }


    public boolean take(Board board, Move move) {
        int positionX = this.xPosition;
        int positionY = this.yPosition;

        int x = move.getX();
        int y = move.getY();
        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
        Bishop f = this;
        board.setFigureAtPosition(positionX, positionY, dummy);
        f.xPosition = positionX + x;
        f.yPosition = positionY + y;
        board.setFigureAtPosition(positionX + x, positionY + y, f);
        if (board.getFigureAtPosition(positionX + x, positionY + y).getName() == '♚' || board.getFigureAtPosition(positionX + x, positionY + y).getName() == '♔') {
            board.endGame();
        }
        return true;
    }
}

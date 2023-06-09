package chessmaster.figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public class Queen extends Figure {
    public Queen(char name, int x, int y, char color) {
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
        boolean isMine;
        int check = 0;

        int yI;
        isMine = board.getFigureAtPosition(positionX + x, positionY + y).getColor() == this.color && board.getFigureAtPosition(positionX + x, positionY + y).getName() != 'N';
        if (move.getX() == 0 || move.getY() == 0) {
            if (x == 0 && y != 0) {
                if (y > 0) {
                    for (int i = 0; i < y; i++) {
                        if (board.getFigureAtPosition(positionX, positionY + i).getName() == ' ') {

                                pathIsClear = true;

                        } else if (i > 0) {
                            pathIsClear = false;
                        }
                    }
                    if (!isMine && pathIsClear && board.getFigureAtPosition(positionX, positionY + y).getColor() != 'N') {
                        success =take(board, move);

                    } else {
                        success =rookMoveY(board, move, positionX, positionY, y, pathIsClear);
                    }
                } else {
                    int check2 = positionY + y;
                    int j = 0;
                    for (int i = positionY; i > check2; i--) {
                        if (board.getFigureAtPosition(positionX, positionY - j).getColor() == 'N') {
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
                        success = true;
                    } else {
                        success =rookMoveY(board, move, positionX, positionY, y, pathIsClear);
                    }
                }
            } else if (x != 0 && y == 0) {
                if (x > 0) {
                    for (int i = 0; i < x; i++) {
                        if (board.getFigureAtPosition(positionX + i, positionY).getName() == ' ') {
                            if (pathIsClear) {
                                pathIsClear = true;
                            }
                        } else if (i > 0) {
                            pathIsClear = false;
                        }
                    }
                    if (board.getFigureAtPosition(positionX + x, positionY).getColor() != this.color && pathIsClear && board.getFigureAtPosition(positionX + x, positionY).getColor() != 'N') {
                        success =take(board, move);

                    } else {
                        success = rookMoveX(board, move, positionX, positionY, x, pathIsClear);
                    }
                } else {
                    int check2 = positionX + x;
                    for (int i = positionX; i > check2; i--) {
                        if (board.getFigureAtPosition(positionX - i, positionY).getName() == ' ') {
                            if (pathIsClear) {
                                pathIsClear = true;
                            }
                        } else if (i != positionX) {
                            pathIsClear = false;
                        }
                    }
                    if (board.getFigureAtPosition(positionX + x, positionY).getName() != this.color && pathIsClear && board.getFigureAtPosition(positionX + x, positionY).getColor() != 'N') {
                        success =take(board, move);

                    } else {
                        success =rookMoveX(board, move, positionX, positionY, x, pathIsClear);
                    }
                }
            } else {
                System.out.println("That move is illegal!");
            }
        } else {
            if ((Math.abs(x) - Math.abs(y) != 0)) {
                System.out.println("This move is illegal");
                return false;
            }

            if (x < 0) {
                if (y < 0) {
                    for (int i = 0; i >= x; i--) {
                        if (board.getFigureAtPosition(positionX + i, positionY + i).getColor() == 'N' && pathIsClear) {
                            pathIsClear = true;
                        } else if ((positionY != (positionY + i))) {
                            pathIsClear = false;
                            check = i;
                            break;
                        }
                    }
                } else if (y > 0) {
                    yI = 0;
                    for (int j = 0; j >= x; j--) {
                        if (board.getFigureAtPosition(positionX + j, positionY + yI).getColor() == 'N' && pathIsClear) {
                            pathIsClear = true;
                        } else if (positionY != positionY + j) {
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
                        } else if (positionY != positionY + i) {
                            pathIsClear = false;
                            check = i;
                            break;
                        }
                    }
                } else if (y > 0) {
                    for (int j = 0; j <= x; j++) {
                        if (board.getFigureAtPosition(positionX + j, positionY + j).getColor() == 'N' && pathIsClear) {
                            pathIsClear = true;
                        } else if (positionY != positionY + j) {
                            pathIsClear = false;
                            check = j;
                            break;
                        }
                    }
                }
            }

            int absCheck = Math.abs(check);
            if (pathIsClear && board.getFigureAtPosition(positionX + x, positionY + y).getColor() == 'N') {
                success = theMove(board, positionX, positionY, x, y);
            } else if (Math.abs(x) == absCheck && !isMine) {

                success = take(board, move);
            }
        }
        return success;
    }

    private boolean theMove(Board board, int positionX, int positionY, int x, int y) {
        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
        Queen f = this;
        board.setFigureAtPosition(positionX, positionY, dummy);
        f.xPosition = positionX + x;
        f.yPosition = positionY + y;
        board.setFigureAtPosition(positionX + x, positionY + y, f);
        return true;
    }

    private boolean rookMoveX(Board board, Move move, int positionX, int positionY, int x, boolean pathIsClear) {
        if (board.getFigureAtPosition(positionX + x, positionY).getColor() == 'N') {
            if (pathIsClear) {
                return this.theMove(board, xPosition,yPosition, x, 0);
            } else {
                System.out.println("That move is illegal!");
            }
        } else if (board.getFigureAtPosition(positionX + x, positionY).getColor() != this.color && pathIsClear) {
            return take(board, move);

        } else {
            System.out.println("That move is illegal!");
            return false;
        }
        return false;
    }

    private boolean rookMoveY(Board board, Move move, int positionX, int positionY, int y, boolean pathIsClear) {
        if (board.getFigureAtPosition(positionX, positionY + y).getColor() == 'N') {
            if (pathIsClear) {
                return this.theMove(board, xPosition, yPosition, 0, y);
            } else {
                System.out.println("That move is illegal!");
            }
        } else if (board.getFigureAtPosition(positionX, positionY + y).getColor() != this.color && pathIsClear) {
            return take(board, move);

        } else {
            System.out.println("That move is illegal!");
            return false;
        }
        return false;
    }


    public boolean take(Board board, Move move) {
        int positionX = this.xPosition;
        int positionY = this.yPosition;
        int x = move.getX();
        int y = move.getY();
        boolean isMine = true;
        int check = 0;
        theMove(board, positionX, positionY, x, y);
        if (board.getFigureAtPosition(positionX + x, positionY + y).getName() == '♚' || board.getFigureAtPosition(positionX + x, positionY + y).getName() == '♔') {
            board.endGame();
        }
        return true;
    }
}

package chessmaster.figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public class Pawn extends Figure {
    private boolean didMove = false;

    private boolean success = false;
    public Pawn(char name, int x, int y, char color) {
        super(name, x, y, color);
    }

    @Override
    public boolean move(Board board, Move move) {
        this.success = false;
        int y = move.getY();

        if(((y < 0) && this.getColor()=='W') || ((y > 0) && this.getColor()=='B'))
        {
            return success;
        }
        if (Math.abs(y) > 2 || (didMove && Math.abs(y) == 2)) {
            System.out.println("This move is illegal");
            success= false;
        }

        int x = move.getX();

        if (x == 0) {
            boolean moveTwo = false;
            char cPlus;

            if (Math.abs(y) == 2 && !didMove) {
                moveTwo = true;
                cPlus = board.getFigureAtPosition(this.xPosition, this.yPosition + y).getName();
            } else {
                cPlus = board.getFigureAtPosition(this.xPosition, this.yPosition + (2 * y)).getName();
            }

            char c = board.getFigureAtPosition(this.xPosition, this.yPosition + y).getName();

            if (!didMove && moveTwo) {
                if (cPlus == ' ' && c == ' ')
                {
                    success =performMove(board, x, y);
                    didMove = true;
                } else {
                    System.out.println("That move is illegal!");
                }
            } else {
                if (c == ' ') {
                    success =performMove(board, x, y);
                    didMove = true;
                }
            }
        } else {
            success =take(board, move);
        }

        return success;
    }

    private boolean performMove(Board board, int x, int y) {
        Figure dummy = new Dummy(' ', xPosition, yPosition, 'N');
        Pawn pawn = this;

        board.setFigureAtPosition(xPosition, yPosition, dummy);
        pawn.xPosition += x;
        pawn.yPosition += y;

        board.setFigureAtPosition(xPosition, yPosition, pawn);

        if (pawn.getName() == '♚' || pawn.getName() == '♔') {
            board.endGame();
        }
        return true;
    }

    private boolean take(Board board, Move move) {
        int y = move.getY();
        int x = move.getX();
        int currX = this.xPosition;
        int currY = this.yPosition;
        boolean isMine = this.color == board.getFigureAtPosition(currX + x, currY + y).getColor();

        char c = board.getFigureAtPosition(currX + x, currY + y).getName();

        if ((x == -1 || x == 1) && (y == 1 || y == -1)) {
            if (c != ' ' && !isMine) {
                performMove(board, x, y);
            } else {
                if (isMine) {
                    System.out.println("You can't take your own figures!");
                } else {
                    System.out.println("That move is illegal!");
                }
            }
        } else {
            System.out.println("That move is illegal!");
        }
        return true;
    }
}

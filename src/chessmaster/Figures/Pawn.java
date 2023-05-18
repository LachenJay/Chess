package chessmaster.Figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public class Pawn extends Figure
{

    public boolean didMove = false;
    public Pawn(char name, int x, int y, char color)
    {

        super((name), x, y, color);
    }
    public boolean move(Board board, Move move)
    {


        int y = move.getY();
        if(Math.abs(y) > 2 || (didMove && Math.abs(y) == 2))
        {
            System.out.println("This move is illegal");
            return false;
        }
        int x = move.getX();
        boolean zeroTesterProtocol = x == 0;
        if(zeroTesterProtocol)
        {
            boolean moveTwo = false;

            char cPlus;
            if (Math.abs(y) == 2 && !didMove)
            {
                moveTwo = true;
                cPlus = board.board[this.xPosition][this.yPosition + y].getName();
            }
            else
            {
                cPlus = board.board[this.xPosition][this.yPosition + (2*y)].getName();
            }
            char c = board.board[this.xPosition][this.yPosition + y].getName();



            if (!didMove && moveTwo) {
                if (cPlus == ' ' && c == ' ') {

                    this.TheMove(board, xPosition, yPosition, x, y);
                    didMove=true;
                } else {
                    System.out.println("That move is illegal!");
                }
            }
            else {
                if (c == ' ') {
                    this.TheMove(board, xPosition, yPosition, x, y);
                    didMove=true;
                }
            }
        }
        else {this.take(board, move);}
        return true;
    }
    public boolean take(Board board, Move move)
    {
        int y = move.getY();
        int x = move.getX();
        int currX = this.xPosition;
        int currY = this.yPosition;
        boolean isMine = this.color == board.board[currX + x][currY + y].color;

        char c = board.board[currX+x][currY + y].getName();
        if((x == -1 || x == 1) && (y == 1 || y == -1))
        {

            if (c != ' ' && !isMine)
            {
                return this.TheMove(board, xPosition, yPosition, x, y);
            }
            else
            {
                if(isMine)
                {
                    System.out.println("You can't take your own figures!");
                    return false;
                }
                else {
                    System.out.println("That move is illegal!");
                    return false;
                }
            }
        }
        else
        {
            System.out.println("That move is illegal!");
            return false;
        }
    }

    private boolean TheMove(Board board, int positionX, int positionY, int x, int y) {
        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
        Pawn f = this;
        board.board[positionX][positionY] = dummy;
        f.xPosition = positionX + x;
        f.yPosition = positionY + y;

        board.board[positionX+x][positionY+y] = f;
        if(board.board[positionX+x][positionY+y].getName() == '♚' || board.board[positionX+x][positionY+y].getName() == '♔')
        {
            board.endGame();
        }
        return true;
    }
}

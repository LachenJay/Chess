package chessmaster.Figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public class Knight extends Figure{
    public Knight(char name, int x, int y, char color) {
        super((name), x, y, color);
    }

    @Override
    public boolean move(Board board, Move move)
    {
        boolean success = false;
        int positionX = this.xPosition;
        int positionY = this.yPosition;
        int x = move.getX();
        int y = move.getY();

        boolean isMine;
        isMine = board.board[positionX + x][positionY + y].color == this.color && board.board[positionX + x][positionY + y].name != 'N';
        try
        {
            if((x == 2 || x==-2) && (y == 1 || y == -1))
            {
                if(isMine)
                {
                    System.out.println("This move is illegal");
                }
                else if(!isMine && board.board[positionX+x][positionY+y].color == 'N')
                {
                    success = this.TheMove(board, xPosition, yPosition, x, y);
                }
                else
                {
                    success = take(board,move);
                }
            }
            else if((y==2 || y == -2) && (x==1 ||x == -1))
            {
                if(isMine)
                {
                    System.out.println("This move is illegal");
                }
                else if(!isMine && board.board[positionX+x][positionY+y].color == 'N')
                {
                    success = this.TheMove(board, xPosition, yPosition, x, y);
                }
                else
                {
                    success = take(board,move);
                }
            }
            else
            {
                System.out.println("That move is illegal");
            }
        }
        catch(Exception e)
        {
            System.out.println("That move is illegal");
        }
        return success;
    }

    @Override
    public boolean take(Board board, Move move)
    {

        int positionX = this.xPosition;
        int positionY = this.yPosition;
        int x = move.getX();
        int y = move.getY();


            Figure dummy = new Dummy(' ', positionX, positionY, 'N');
            Knight f = this;
            board.board[positionX][positionY] = dummy;
            f.xPosition = positionX + x;
            f.yPosition = positionY + y;
            board.board[positionX+x][positionY+y] = f;
            boolean success = true;
            return success;



    }

    private boolean TheMove(Board board, int positionX, int positionY, int x, int y) {
        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
        Knight f = this;
        board.board[positionX][positionY] = dummy;
        f.xPosition = positionX + x;
        f.yPosition = positionY + y;
        board.board[positionX+x][positionY+y] = f;
        return true;
    }
}

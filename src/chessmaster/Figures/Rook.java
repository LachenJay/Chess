package chessmaster.Figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public class Rook extends Figure
{
    private boolean pathIsClear = true;

    public Rook(char name, int x, int y, char color)
    {
        super((name), x, y, color);
    }



    @Override
    public boolean move(Board board, Move move)
    {
        boolean success =false;
        int y = move.getY();
        int x = move.getX();
        int positionX = this.xPosition;
        int positionY = this.yPosition;


        if(x == 0 && y != 0)
        {
            if(y > 0)
            {

                for (int i = 0; i < y; i++)
                {
                    if(board.board[positionX][positionY+i].getName() == ' ')
                    {

                            pathIsClear = true;

                    }
                    else if(i > 0)
                    {
                        pathIsClear=false;

                    }
                }
                if(board.board[positionX][positionY+y].getName() != this.color && pathIsClear && board.board[positionX][positionY+y].color != 'N')
                {
                    return take(board, move);

                }
                else if(board.board[positionX][positionY+y].color == 'N')
                {
                    if(pathIsClear)
                    {
                        boolean test= TheMove(board, xPosition, yPosition, x, y);
                        return test;
                    }
                    else
                    {
                        System.out.println("That move is illegal!");
                        return false;
                    }
                }
                else if (board.board[positionX][positionY+y].color != this.color && pathIsClear)
                {
                    take(board, move);
                }
                else
                {
                    System.out.println("That move is illegal!");
                    return false;
                }

            }
            else
            {
                int check = positionY+y;
                int j = 0;
                for (int i = positionY; i > check; i--)
                {
                    if(board.board[positionX][positionY-j].getName() == ' ')
                    {
                        if(pathIsClear)
                        {
                            pathIsClear = true;
                        }
                    }
                    else if(i != positionY)
                    {
                        pathIsClear=false;

                    }
                    j++;
                }
                if(board.board[positionX][positionY+y].getName() != this.color && pathIsClear && board.board[positionX][positionY+y].color != 'N')
                {
                    return take(board, move);

                }
                else if(board.board[positionX][positionY+y].color =='N')
                {
                    if (pathIsClear) {

                        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
                        Rook f = this;
                        board.board[positionX][positionY] = dummy;
                        f.yPosition = positionY + y;
                        board.board[positionX][positionY + y] = f;

                    }
                    else
                    {
                        System.out.println("That move is illegal!");
                        return false;
                    }
                }
                else if (board.board[positionX][positionY+y].color != this.color && pathIsClear)
                {
                    take(board, move);
                }
                else
                {
                    System.out.println("That move is illegal!");
                    return false;
                }
            }
        }
        else if(x != 0 && y == 0)
        {
            if(x > 0)
            {

                for (int i = 0; i < x; i++)
                {
                    if(board.board[positionX+i][positionY].getName() == ' ')
                    {

                            pathIsClear = true;

                    }
                    else if(i > 0)
                    {
                        pathIsClear=false;

                    }
                }
                if(board.board[positionX+x][positionY].getName() != this.color && pathIsClear && board.board[positionX+x][positionY].color != 'N')
                {
                    return take(board, move);

                }
                else if(board.board[positionX+x][positionY].color == 'N')
                {
                    if (pathIsClear)
                    {

                        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
                        Rook f = this;
                        board.board[positionX][positionY] = dummy;
                        f.xPosition = positionX + x;
                        board.board[positionX+x][positionY] = f;

                    }
                    else
                    {
                        System.out.println("That move is illegal!");
                        return false;
                    }
                }
                else if (board.board[positionX+x][positionY].color != this.color && pathIsClear)
                {
                    return take(board, move);
                }
                else
                {
                    System.out.println("That move is illegal!");
                    return false;
                }
            }
            else
            {
                int check = positionX+x;
                for (int i = positionX; i > check; i--)
                {
                    if(board.board[positionX-i][positionY].getName() == ' ')
                    {
                        if(pathIsClear)
                        {
                            pathIsClear = true;
                        }
                    }
                    else if(i != positionX)
                    {
                        pathIsClear=false;

                    }
                }
                if(board.board[positionX+x][positionY].getName() != this.color && pathIsClear && board.board[positionX+x][positionY].color != 'N')
                {
                    take(board, move);
                    return false;
                }
                else if(board.board[positionX+x][positionY].color == 'N')
                {
                    if (pathIsClear)
                    {

                        return this.TheMove(board, xPosition, yPosition, x, y );

                    }
                    else
                    {
                        System.out.println("That move is illegal!");
                        return false;
                    }
                }
                else if (board.board[positionX+x][positionY].color != this.color && pathIsClear)
                {
                    take(board, move);
                }
                else
                {
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


        return success;
    }

    private boolean TheMove(Board board, int positionX, int positionY, int x, int y) {
        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
        Rook f = this;
        board.board[positionX][positionY] = dummy;
        f.xPosition = positionX + x;
        f.yPosition = positionY + y;
        board.board[positionX+x][positionY+y] = f;
        return true;
    }
    @Override
    public boolean take(Board board, Move move)
    {
        int positionX = this.xPosition;
        int positionY = this.yPosition;
        int x = move.getX();
        int y = move.getY();
        if (pathIsClear && !(board.board[positionX + x][positionY + y].color == this.color))
        {
            if(board.board[positionX+x][positionY+y].getName() == '♚' || board.board[positionX+x][positionY+y].getName() == '♔')
            {
                board.endGame();
            }
            return TheMove(board, xPosition, yPosition, x, y);

        }

        return false;
    }
}

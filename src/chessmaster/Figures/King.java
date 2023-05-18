package chessmaster.Figures;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;

public class King extends Figure{
    public King(char name, int x, int y, char color) {
        super((name), x, y, color);
    }

    @Override
    public boolean move(Board board, Move move)
    {
        boolean success= false;
        int positionX = this.xPosition;
        int positionY = this.yPosition;

        int x = move.getX();
        int y = move.getY();
        boolean pathIsClear = true;
        boolean isMine;
        int check = 0;

        if(Math.abs(move.getX()) > 1 || Math.abs(move.getY()) >1) return false;
        int yI;
        isMine = board.board[positionX + x][positionY + y].color == this.color && board.board[positionX + x][positionY + y].name != 'N';
        if(move.getX() == 0 || move.getY()==0)
        {
            if(x == 0 && y != 0)
            {
                if(y > 0)
                {

                    for (int i = 0; i < y; i++)
                    {
                        if(board.board[positionX][positionY+i].getName() == ' ')
                        {
                            if(pathIsClear)
                            {
                                pathIsClear = true;
                            }
                        }
                        else if(i > 0)
                        {
                            pathIsClear=false;

                        }
                    }
                    if(!isMine && pathIsClear && board.board[positionX][positionY+y].color != 'N')
                    {
                        success = take(board, move);
                    }
                    else RookMoveY(board, move, positionX, positionY, y, pathIsClear);

                }
                else
                {
                    int check2 = positionY+y;
                    int j = 0;
                    for (int i = positionY; i > check2; i--)
                    {
                        if(board.board[positionX][positionY-j].color == 'N')
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
                        success= take(board, move);
                    }
                    else {
                        RookMoveY(board, move, positionX, positionY, y, pathIsClear);
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
                            if(pathIsClear)
                            {
                                pathIsClear = true;
                            }
                        }
                        else if(i > 0)
                        {
                            pathIsClear=false;

                        }
                    }
                    if(board.board[positionX+x][positionY].color != this.color && pathIsClear && board.board[positionX+x][positionY].color != 'N')
                    {
                        success =take(board, move);
                    }
                    else {
                        RookMoveX(board, move, positionX, positionY, x, pathIsClear);
                    }
                }
                else
                {
                    int check2 = positionX+x;
                    for (int i = positionX; i > check2; i--)
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
                        success =take(board, move);
                    }
                    else RookMoveX(board, move, positionX, positionY, x, pathIsClear);
                }
            }
            else
            {
                System.out.println("That move is illegal!");
            }
        }
        else
        {
            if((Math.abs(x) - Math.abs(y) != 0))
            {
                System.out.println("This move is illegal");
                return false;
            }

            if(x < 0)
            {
                if(y < 0)
                {

                    for(int i = 0; i >= x; i--)
                    {
                        if(board.board[positionX+i][positionY+i].color == 'N'&& pathIsClear)
                        {

                            pathIsClear = true;

                        }
                        else if((positionY != (positionY + i)))
                        {
                            pathIsClear=false;
                            check = i;
                            break;



                        }

                    }
                }
                else if(y > 0)
                {
                    yI = 0;
                    for(int j = 0; j >= x; j--)
                    {

                        if(board.board[positionX+j][positionY+yI].color == 'N'&& pathIsClear)
                        {

                            pathIsClear = true;

                        }
                        else if(positionY != positionY + j)
                        {
                            pathIsClear=false;
                            check = j;
                            break;


                        }
                        yI++;

                    }
                }
            }
            else if(x > 0)
            {
                if(y < 0)
                {

                    for(int i = 0; i <= x; i++)
                    {
                        if(board.board[positionX+i][positionY-i].color == 'N'&& pathIsClear)
                        {

                            pathIsClear = true;

                        }
                        else if(positionY != positionY + i)
                        {
                            pathIsClear=false;
                            check = i;
                            break;


                        }

                    }
                }
                else if(y > 0)
                {

                    for(int j = 0; j <= x; j++)
                    {

                        if(board.board[positionX+j][positionY+j].color == 'N'&& pathIsClear)
                        {

                            pathIsClear = true;

                        }
                        else if(positionY != positionY + j)
                        {
                            pathIsClear=false;
                            check = j;
                            break;


                        }


                    }
                }
            }

            int absCheck = Math.abs(check);
            if(pathIsClear && board.board[positionX+x][positionY+y].color == 'N')
            {
                success =TheMove(board, positionX, positionY, x, y);
            }
            else if(Math.abs(x) == absCheck && !isMine)
            {
                success= take(board, move);
            }
        }
        return success;
    }

    private boolean TheMove(Board board, int positionX, int positionY, int x, int y) {
        Figure dummy = new Dummy(' ', positionX, positionY, 'N');
        King f = this;
        board.board[positionX][positionY] = dummy;
        f.xPosition = positionX + x;
        f.yPosition = positionY + y;
        board.board[positionX+x][positionY+y] = f;
        return true;
    }

    private boolean RookMoveX(Board board, Move move, int positionX, int positionY, int x, boolean pathIsClear) {
        if(board.board[positionX+x][positionY].color == 'N')
        {
            if (pathIsClear)
            {

                Figure dummy = new Dummy(' ', positionX, positionY, 'N');
                King f = this;
                board.board[positionX][positionY] = dummy;
                f.xPosition = positionX + x;
                board.board[positionX+x][positionY] = f;
                return true;
            }
            else
            {
                System.out.println("That move is illegal!");
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
        return false;
    }

    private boolean RookMoveY(Board board, Move move, int positionX, int positionY, int y, boolean pathIsClear) {
        if(board.board[positionX][positionY+y].color =='N')
        {
            if (pathIsClear) {

                Figure dummy = new Dummy(' ', positionX, positionY, 'N');
                King f = this;
                board.board[positionX][positionY] = dummy;
                f.yPosition = positionY + y;
                board.board[positionX][positionY + y] = f;
                return true;
            }
            else
            {
                System.out.println("That move is illegal!");
            }
        }
        else if (board.board[positionX][positionY+y].color != this.color && pathIsClear)
        {
            return take(board, move);
        }
        else
        {
            System.out.println("That move is illegal!");
            return false;
        }
        return false;
    }

    @Override
    public boolean take(Board board, Move move)
    {
        int positionX = this.xPosition;
        int positionY = this.yPosition;

        int x = move.getX();
        int y = move.getY();
        boolean isMine=true;
        int check = 0;


        TheMove(board, positionX, positionY, x, y);
        if(board.board[positionX+x][positionY+y].getName() == '♚' || board.board[positionX+x][positionY+y].getName() == '♔')
        {
            board.endGame();
        }
        return true;
    }
}
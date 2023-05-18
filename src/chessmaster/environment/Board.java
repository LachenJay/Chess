package chessmaster.environment;

import chessmaster.Figures.*;

public class Board
{
    public Figure[][] board;
    private boolean isGameOn = false;

    public Board()
    {
        this.board = new Figure[8][8];
        for(int row = 0; row < 8; row++)
        {
            for(int col = 0; col <8; col++)
            {
                this.board[col][row] = new Dummy(' ', col,row, 'N');
            }
        }
    }


    public void fillBoardWhites()
    {

        for(int i = 0; i < 8; i++)
        {
            board[i][1] = new Pawn('♙',i, 1,'W');
        }
        board[0][0] = new Rook('♖', 0,0, 'W');
        board[7][0] = new Rook('♖', 7, 0, 'W');
        board[1][0] = new Knight('♘', 1, 0, 'W');
        board[6][0] = new Knight('♘', 6, 0, 'W');
        board[2][0] = new Bishop('♗',2, 0, 'W');
        board[5][0] = new Bishop('♗', 5,0, 'W');
        board[3][0] = new Queen('♕', 3,0, 'W');
        board[4][0] = new King('♔', 4, 0, 'W');
    }
    public void fillBoardBlks()
    {

        for(int i = 0; i < 8; i++)
        {
            board[i][6] = new Pawn('♟',i, 6, 'B');
        }
        board[0][7] = new Rook('♜',0,7, 'B');
        board[7][7] = new Rook('♜', 7, 7, 'B');
        board[1][7] = new Knight('♞', 1, 7, 'B');
        board[6][7] = new Knight('♞', 6, 7, 'B');
        board[2][7] = new Bishop('♝', 2, 7, 'B');
        board[5][7] = new Bishop('♝',5,7, 'B');
        board[3][7] = new Queen('♛', 3,7, 'B');
        board[4][7] = new King('♚', 4, 7, 'B');
    }

    public void showBoard()
    {
        System.out.println("\n    A    B    C   D    E    F   G   H");
        for(int row = 7; row >= 0; row--)
        {

            System.out.println();
            System.out.print((row+1) + " |");
            for (int col = 0; col < 8; col++)
            {


                if((row+col)%2 == 0)
                {
                    if(board[col][row].getName() == ' ')
                    {
                        System.out.print("_\u2003_");

                        System.out.print("|");
                    }
                    else
                    {
                        System.out.print("_" +this.board[col][row].getName() + "_");
                        System.out.print("|");
                    }
                }
                else
                {
                    if(board[col][row].getName() == ' ')
                    {
                        System.out.print("_\u2003_");

                        System.out.print("|");
                    }
                    else
                    {
                        System.out.print("_" +this.board[col][row].getName() + "_");
                        System.out.print("|");
                    }
                }


            }
            System.out.print(" " + (8-row));
        }
        System.out.println("\n \n   A     B    C   D    E    F   G    H");
    }
    public void newGame()
    {
        this.fillBoardBlks();
        this.fillBoardWhites();
        this.showBoard();
        System.out.println("\nNew game has been started. You are playing as white!");
    }

    public boolean endGame()
    {
        return true;
    }

    public boolean getIsGameOn()    {
        return isGameOn;
    }
}

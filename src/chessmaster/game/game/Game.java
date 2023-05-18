package chessmaster.game.game;


import chessmaster.environment.Board;
import chessmaster.game.moves.Move;
import chessmaster.game.textfile.ChessMoveRecorder;
import chessmaster.game.textfile.ChessPieceTranslator;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Game
{
    public Board board;
    private int counter = 1;
    private boolean succesfulTurn = false;

    private boolean gameIsOverNow = false;

    private ChessMoveRecorder chMoves;

    private ChessPieceTranslator chPiece = new ChessPieceTranslator();

    private String startingPosition;
    private String endingPosition;

    private char piece;

    public String nameOfGame;

    public Game()
    {
        this.board = new Board();
        board.newGame();
        Scanner sc = new Scanner(System.in);
        System.out.print("What is the name of this duel?");
        String gameName = sc.next();
        this.chMoves = new ChessMoveRecorder(gameName);
        this.nameOfGame = gameName;

        nextMove();

    }

    public void nextMove()
    {
        boolean checked = this.board.getIsGameOn();
        while(checked == false)
        {
            this.succesfulTurn = false;
            while (!succesfulTurn) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose your figure (A8)?");
                String nextMv = sc.next();
                startingPosition = nextMv;
                char[] array = new char[2];
                if(nextMv.equals("end") ||nextMv.equals("End"))
                {
                    this.succesfulTurn=true;
                   checked=true;

                }
                else {
                    array = nextMv.toCharArray();

                Position yourPosition = readPosition(array);
                int x = yourPosition.getX();
                int y = yourPosition.getY();
                    piece = this.board.board[x][y].getName();
                if (((counter % 2 == 0) && this.board.board[x][y].color == 'B') || ((counter % 2 != 0) && this.board.board[x][y].color == 'W'))
                {
                    if (x == 42 || y == 42)
                    {
                        System.out.println("Your input is invalid");
                    }
                    else
                    {
                        System.out.println("Choose your target (A8)?");
                        String nextMv2 = sc.next();
                        endingPosition = nextMv2;
                        char[] array2 = nextMv2.toCharArray();
                        Position targetPosition = readPosition(array2);
                        int xTarget = targetPosition.getX();
                        int yTarget = targetPosition.getY();
                        int hopX;
                        int hopY;
                        hopX = xTarget - x;
                        hopY = yTarget - y;
                        boolean test = this.board.board[x][y].move(this.board, new Move(hopX, hopY));
                        this.succesfulTurn = test;
                    }
                } else {
                    System.out.println("It isn't your turn!");
                    this.succesfulTurn = false;
                }
                if (succesfulTurn)
                {
                    if(this.board.board[x][y].color == 'W')
                    {
                        String pieceName = chPiece.translateChessPiece(piece);
                        chMoves.recordMove("White", pieceName, startingPosition, endingPosition);

                    }
                    else
                    {
                        String pieceName = chPiece.translateChessPiece(piece);
                        chMoves.recordMove("Black", pieceName, startingPosition, endingPosition);
                    }

                    board.showBoard();
                    counter++;
                }
                }
            }
        }
    }
    public Position readPosition(char[] array)
    {
        int x=42;
        int y=42;
        char zero=' ';
        char one=' ';
        try
        {
            zero = array[0];
            one = array[1];
        }
        catch(Exception e)
        {
            System.out.println("Your input is invalid");
        }
        switch (zero) {
            case 'A', 'a' -> x = 0;
            case 'B', 'b' -> x = 1;
            case 'C', 'c' -> x = 2;
            case 'D', 'd' -> x = 3;
            case 'E', 'e' -> x = 4;
            case 'F', 'f' -> x = 5;
            case 'G', 'g' -> x = 6;
            case 'H', 'h' -> x = 7;
            default -> System.out.println("Unsupported value");
        }

        switch (one) {
            case '1' -> y = 0;
            case '2' -> y = 1;
            case '3' -> y = 2;
            case '4' -> y = 3;
            case '5' -> y = 4;
            case '6' -> y = 5;
            case '7' -> y = 6;
            case '8' -> y = 7;
            default -> System.out.println("Unsupported value");
        }
        Position returner = new Position();
        returner.setX(x);
        returner.setY(y);
        return returner;
    }


    public void gameIsOver()
    {
        this.gameIsOverNow = true;
    }

}

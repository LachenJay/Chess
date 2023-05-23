package chessmaster.game.game;

import chessmaster.environment.Board;
import chessmaster.game.moves.Move;
import chessmaster.game.textfile.ChessMoveRecorder;
import chessmaster.game.textfile.ChessPieceTranslator;
import chessmaster.menu.MainMenu;

import java.util.Scanner;

public class Game {
    private Board board;
    private int counter = 1;
    private boolean successfulTurn = false;
    private boolean gameIsOverNow = false;
    private ChessMoveRecorder chMoves;
    private ChessPieceTranslator chPiece = new ChessPieceTranslator();
    private String startingPosition;
    private String endingPosition;
    private char piece;
    public String nameOfGame;

    public Game() {
        this.board = new Board();
        board.newGame();
        Scanner sc = new Scanner(System.in);
        System.out.print("What is the name of this duel? ");
        String gameName = sc.nextLine();
        this.chMoves = new ChessMoveRecorder(gameName);
        this.nameOfGame = gameName;
        nextMove();
    }

    public void nextMove() {
        boolean checked = this.board.getIsGameOn();
        while (!checked) {
            this.successfulTurn = false;
            while (!successfulTurn) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Choose your figure (A8): ");
                String nextMv = sc.next();
                startingPosition = nextMv;
                char[] array = nextMv.toCharArray();
                if (nextMv.equalsIgnoreCase("end")) {
                    this.successfulTurn = true;
                    checked = true;
                } else if (array.length > 2)
                {
                    this.successfulTurn=false;

                } else {
                    Position yourPosition = readPosition(array);
                    int x = yourPosition.getX();
                    int y = yourPosition.getY();
                    piece = this.board.getFigureAtPosition(x, y).getName();
                    if (((counter % 2 == 0) && this.board.getFigureAtPosition(x, y).getColor() == 'B')
                            || ((counter % 2 != 0) && this.board.getFigureAtPosition(x, y).getColor() == 'W')) {
                        if (x == 42 || y == 42) {
                            System.out.println("Your input is invalid");
                        } else {
                            System.out.print("Choose your target (A8): ");
                            String nextMv2 = sc.next();
                            endingPosition = nextMv2;
                            char[] array2 = nextMv2.toCharArray();
                            Position targetPosition = readPosition(array2);
                            int xTarget = targetPosition.getX();
                            int yTarget = targetPosition.getY();
                            int hopX = xTarget - x;
                            int hopY = yTarget - y;
                            boolean test = this.board.getFigureAtPosition(x, y).move(this.board, new Move(hopX, hopY));
                            this.successfulTurn = test;
                        }
                    } else {
                        System.out.println("It isn't your turn!");
                        this.successfulTurn = false;
                    }
                    if (successfulTurn) {
                        String pieceName = chPiece.translateChessPiece(piece);
                        chMoves.recordMove(pieceName, startingPosition, endingPosition);
                        board.showBoard();
                        counter++;
                    }
                }
            }
        }
        MainMenu mn = new MainMenu();
    }

    public Position readPosition(char[] array) {
        int x = 42;
        int y = 42;
        char zero = ' ';
        char one = ' ';
        try {
            zero = array[0];
            one = array[1];
        } catch (Exception e) {
            System.out.println("Your input is invalid");
        }
        switch (Character.toUpperCase(zero)) {
            case 'A' -> x = 0;
            case 'B' -> x = 1;
            case 'C' -> x = 2;
            case 'D' -> x = 3;
            case 'E' -> x = 4;
            case 'F' -> x = 5;
            case 'G' -> x = 6;
            case 'H' -> x = 7;
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

    public void gameIsOver() {
        this.gameIsOverNow = true;
    }
}

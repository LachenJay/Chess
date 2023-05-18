package chessmaster;

import chessmaster.environment.Board;
import chessmaster.game.game.Game;
import chessmaster.game.moves.Move;
import chessmaster.game.textfile.ChessMoveRecorder;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        ChessMoveRecorder chRecord = new ChessMoveRecorder();
        chRecord.setGameName(game.nameOfGame);
        System.out.println(chRecord.readMoves());




        //board.board[0][4].move(board,new Move(-1,2));

    }
}
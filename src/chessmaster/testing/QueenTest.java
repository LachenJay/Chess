package chessmaster.testing;

import chessmaster.environment.Board;
import chessmaster.figures.Pawn;
import chessmaster.figures.Queen;
import chessmaster.game.moves.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueenTest
{
    private Board board;
    private Queen queen;

    private Pawn pawn;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        queen = new Queen('♕', 0, 0, 'W');
        board.setFigureAtPosition(0, 0, queen);
        pawn = new Pawn('♟', 1, 1, 'B');
        board.setFigureAtPosition(1, 1, pawn);

    }
    @Test
    public void testValidMoveSide() {
        Move move = new Move(7, 0);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(7, 0) instanceof Queen);
    }

    @Test
    public void testValidMoveUp() {
        Move move = new Move(0, 7);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(0, 7) instanceof Queen);
    }

    @Test
    public void testInvalidMove() {
        Move move = new Move(2, 6);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertFalse(result);
    }

    @Test
    public void testValidMoveDiagonal()
    {
        Move move = new Move(3, 3);
        pawn = new Pawn(' ', 1, 1, 'N');
        board.setFigureAtPosition(1, 1, pawn);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
    }
    @Test
    public void testTake() {
        Move move = new Move(1, 1);

        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(1, 1) instanceof Queen);
        board.showBoard();
    }
}

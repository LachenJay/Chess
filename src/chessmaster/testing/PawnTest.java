package chessmaster.testing;

import chessmaster.environment.Board;
import chessmaster.figures.Pawn;
import chessmaster.game.moves.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PawnTest
{
    private Board board;
    private Pawn pawn2;

    private Pawn pawn;

    @BeforeEach
    public void setUp()
    {
        board = new Board();
        pawn2 = new Pawn('♟', 1, 1, 'B');
        board.setFigureAtPosition(1, 1, pawn2);
        pawn = new Pawn('♙', 0, 0, 'W');
        board.setFigureAtPosition(0, 0, pawn);

    }
    @Test
    public void testValidMove() {
        Move move = new Move(0, 1);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        pawn = (Pawn) board.getFigureAtPosition(0, 1);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(0, 1) instanceof Pawn);
    }

    @Test
    public void testValidMoveTwo() {
        Move move = new Move(0, 2);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        pawn = (Pawn) board.getFigureAtPosition(0, 2);
        Assertions.assertTrue(result);
        Assertions.assertTrue(board.getFigureAtPosition(0, 2) instanceof Pawn);
    }

    @Test
    public void testInvalidMove() {
        Move move = new Move(2, 0);
        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertFalse(result);
    }

    @Test
    public void testTake() {
        Move move = new Move(1, 1);

        boolean result = board.getFigureAtPosition(0, 0).move(board, move);
        Assertions.assertTrue(result);
        // Assert that the target position contains the knight
        Assertions.assertTrue(board.getFigureAtPosition(1, 1) instanceof Pawn);
        board.showBoard();
    }
}

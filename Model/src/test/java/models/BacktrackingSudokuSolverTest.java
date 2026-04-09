package models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;
import sudoku.model.models.SudokuBoard;
import sudoku.model.solver.BacktrackingSudokuSolver;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void testSolveProducesValidBoard() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        assertDoesNotThrow(() -> board.solveGame());
        assertTrue(board.isValidSudoku(), "Solved board should be valid");
    }

    @Test
    void testSolveProducesFullyFilledBoard() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        assertDoesNotThrow(() -> board.solveGame());

        for (int row = 0; row < SudokuBoard.BOARD_SIZE; row++) {
            for (int col = 0; col < SudokuBoard.BOARD_SIZE; col++) {
                int val = board.getField(col, row).getValue();
                assertTrue(val >= 1 && val <= 9,
                    "Field at (" + col + "," + row + ") has invalid value: " + val);
            }
        }
    }

    @RepeatedTest(5)
    void testSolveProducesRandomizedBoards() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());

        assertDoesNotThrow(() -> board1.solveGame());
        assertDoesNotThrow(() -> board2.solveGame());

        assertTrue(board1.isValidSudoku());
        assertTrue(board2.isValidSudoku());

        assertNotEquals(board1, board2,
            "Two independently solved boards should differ");
    }
}

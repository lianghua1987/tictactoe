import javafx.util.Pair;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


public class TicTacToeTest {

    @Test
    public void validate_legitInput_returnPosition() {
        Pair<Integer, Integer> pair = TicTacToe.validate("2,3");
        assertEquals("Input position should on row 1 since the array starts at index 0", Integer.valueOf(1), pair.getKey());
        assertEquals("Input position should one column 2 since the array starts at index 0", Integer.valueOf(2), pair.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_notANumber_throwsException() {
        TicTacToe.validate("a,3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_outOfRange_throwsException() {
        TicTacToe.validate("7,3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_invalidPosition_throwsException() {
        TicTacToe.validate("1,3,2");
    }

    @Test
    public void checkExists_exist_returnsTrue() {
        assertTrue("Position should already taken", TicTacToe.exists(dummyBoard(), new Pair<>(1, 2)));
    }

    @Test
    public void checkExists_notExist_returnsFalse() {
        assertFalse("Position should not taken", TicTacToe.exists(dummyBoard(), new Pair<>(2, 2)));
    }

    @Test
    public void checkWins_identicalFirstRow_returnTrue(){
        String board[][] = new String[3][3];
        TicTacToe.init(board);
        board[0][0] = "X";
        board[0][1] = "X";
        board[0][2] = "X";
        assertTrue("Should win", TicTacToe.win(board));
    }

    @Test
    public void checkWins_identicalLastRow_returnTrue(){
        String board[][] = new String[3][3];
        TicTacToe.init(board);
        board[2][0] = "O";
        board[2][1] = "O";
        board[2][2] = "O";
        assertTrue("Should win", TicTacToe.win(board));
    }

    @Test
    public void checkWins_identicalFirstColumn_returnTrue(){
        String board[][] = new String[3][3];
        TicTacToe.init(board);
        board[0][0] = "O";
        board[1][0] = "O";
        board[2][0] = "O";
        assertTrue("Should win", TicTacToe.win(board));
    }

    @Test
    public void checkWins_identicalLastColumn_returnTrue(){
        String board[][] = new String[3][3];
        TicTacToe.init(board);
        board[0][2] = "X";
        board[1][2] = "X";
        board[2][2] = "X";
        assertTrue("Should win", TicTacToe.win(board));
    }

    @Test
    public void checkWins_identicalDiagonal_returnTrue(){
        String board[][] = new String[3][3];
        TicTacToe.init(board);
        board[2][2] = "O";
        board[1][1] = "O";
        board[0][0] = "O";
        assertTrue("Should win", TicTacToe.win(board));
    }

    @Test
    public void checkWins_notStarted_returnFalse(){
        String board[][] = new String[3][3];
        TicTacToe.init(board);
        assertFalse("Game not started, nobody wins", TicTacToe.win(board));
    }

    @Test
    public void checkWins_1stStep_returnFalse(){
        String board[][] = new String[3][3];
        TicTacToe.init(board);
        board[2][2] = "O";
        assertFalse("Only one player plays the game, should return false", TicTacToe.win(board));
    }

    @Test
    public void checkWins_secondRow_returnFalse(){
        String board[][] = new String[3][3];
        TicTacToe.init(board);
        board[1][0] = "O";
        board[1][1] = "X";
        board[1][2] = "O";
        assertFalse("Not identical row, should return false", TicTacToe.win(board));
    }

    private String[][] dummyBoard() {
        String board[][] = new String[3][3];
        TicTacToe.init(board);
        board[1][2] = "X";
        return board;
    }
}

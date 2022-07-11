package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.

        // Do up first
        /*
            Idea : 由上往下看，有 merged 那就要將下面的往上多推
         */
        for (int c = 0 ; c < board.size() ; c++) {
            if(handleTileSingleColumn(c)) changed = true;
        }

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /*
    Tile helper function: c is column.
    Returns true if changed.
    */
    private boolean handleTileSingleColumn(int c) {
        boolean changed;
        changed = false;

        for (int r = 3; r >= 0; r--){
            if(board.tile(c, r) != null) {
                Tile t = board.tile(c, r);

                boolean isMerged = false;

                // 1. move up if the space above it is empty
                // 2. or it can move up one if the space above it has the same value as itself
                if((r < 3 && board.tile(c, 3) == null) || t.value() == board.tile(c, 3).value()) {
                    isMerged = board.move(c, 3, t);
                } else if ((r < 2 && board.tile(c, 2) == null) || t.value() == board.tile(c, 2).value()) {
                    isMerged = board.move(c, 2, t);
                } else if ((r < 1 && board.tile(c, 1) == null) || t.value() == board.tile(c, 1).value()) {
                    isMerged = board.move(c, 1, t);
                }

                // Handle score
                if (isMerged) {
                    // This move is merged, score increases.
                    score += board.tile(c, 3).value();

                } else {
                    // This move is not merged, score has no change.
                    score += 0;
                }

                changed = true;
            }
        }

        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        for(int r = 0 ; r < b.size() ; r++){
           for(int c = 0 ; c < b.size() ; c++) {
                if(b.tile(c, r) == null) return true;
           }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        int MAX_PIECE = 2048;

        for(int r = 0 ; r < b.size() ; r++){
            for(int c = 0 ; c < b.size() ; c++) {
                if(b.tile(c, r) != null && b.tile(c, r).value() == MAX_PIECE) return true;
            }
        }

        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.

        int MAX_PIECE = 2048;

        // condition 1. There is at least one empty space on the board. or exists MAX_PIECE
        for(int r = 0 ; r < b.size() ; r++){
            for(int c = 0 ; c < b.size() ; c++) {
                if(b.tile(c, r) == null || b.tile(c, r).value() == MAX_PIECE) return true;
            }
        }

        // condition 2. There are two adjacent tiles with the same value.
        // idea: 先將外層唯一圈，就不用分類。上下左右檢查
        int [][] checkBoard = new int[6][6];
        for(int i = 0 ; i < 5 ; i ++){
            checkBoard[0][i] = 1;
            checkBoard[i][0] = 1;
            checkBoard[5][i] = 1;
            checkBoard[i][5] = 1;
        }

        // put board's value to checkBoard
        for(int r = 0 ; r < b.size() ; r++){
            for(int c = 0 ; c < b.size() ; c++) {
                if(b.tile(c, r) != null) {
                    checkBoard[c + 1][r + 1] = b.tile(c, r).value();
                }
            }
        }

        for(int r = 1 ; r < b.size() + 1; r++){
            for(int c = 1 ; c < b.size() + 1; c++) {
                int compareTileValue = checkBoard[r][c];

                if(compareTileValue == checkBoard[r + 1][c] ||
                    compareTileValue == checkBoard[r - 1][c] ||
                    compareTileValue == checkBoard[r][c + 1] ||
                    compareTileValue == checkBoard[r][c - 1]
                ) return true;
            }
        }


        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}

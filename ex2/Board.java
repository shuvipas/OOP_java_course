
//enum Winner {
//    DRAW, X_WIN, Y_WIN
//};

enum GameStatus {
    DRAW, X_WIN, O_WIN, IN_PROGRESS
};

public class Board {

    public static final int SIZE = 5;
    public static final int WIN_STREAK = 3;
    private int spacesLeft = SIZE * SIZE;
    private Mark[][] board = new Mark[SIZE][SIZE];
    private Mark winner;// = Mark.BLANK;

    public Board() {
        winner = Mark.BLANK;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = Mark.BLANK;
            }
        }

    }

    private boolean onTheBoard(int row, int col) {
        if (row >= SIZE || row < 0 || col >= SIZE || col < 0) {
            return false;
        }
        return true;

    }

    public Mark getMark(int row, int col) {
        if (!onTheBoard(row, col)) {
            return Mark.BLANK;
        }

        return board[row][col];
    }

    public boolean putMark(Mark mark, int row, int col) {
        if (!onTheBoard(row, col) || board[row][col] != Mark.BLANK) {
            return false;
        }
        board[row][col] = mark;
        spacesLeft--;
        this.winner = getWinnerMark();
        return true;
    }

    private boolean rowStreakCheck(int row, int col, Mark mark) {
        // int rowRight =row, rowLeft =row;
        for (int i = 1; i < WIN_STREAK; i++) {
            if (!onTheBoard(row, col + i) || getMark(row, col + i) != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean colStreakCheck(int row, int col, Mark mark) {
        // int rowRight =row, rowLeft =row;
        for (int i = 1; i < WIN_STREAK; i++) {
            if (!onTheBoard(row + i, col) || getMark(row + i, col) != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean rightDigStreakCheck(int row, int col, Mark mark) {
        // int rowRight =row, rowLeft =row;
        for (int i = 1; i < WIN_STREAK; i++) {
            if (!onTheBoard(row + i, col + i) || getMark(row + i, col + i) != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean leftDigStreakCheck(int row, int col, Mark mark) {
        // int rowRight =row, rowLeft =row;
        for (int i = 1; i < WIN_STREAK; i++) {
            if (!onTheBoard(row - i, col + i) || getMark(row - i, col + i) != mark) {
                return false;
            }
        }
        return true;
    }

    private Mark getWinnerMark() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Mark mark = getMark(row, col);
                if (mark == Mark.BLANK) {
                    continue;
                }
                if (rowStreakCheck(row, col, mark) || colStreakCheck(row, col, mark) ||
                        leftDigStreakCheck(row, col, mark) || rightDigStreakCheck(row, col, mark)) {
                    return mark;
                }
            }
        }
        return Mark.BLANK;
    }
    /*
     * public boolean isGameEnded(){
     * if(spacesLeft>0){
     * return false;
     * }
     * return true;
     * }
     */

    public GameStatus getGameStatus(){// if i want this way of impolmanting
       // Mark winner = getWinnerMark();
        if(this.winner==Mark.BLANK){
            return spacesLeft<=0? GameStatus.DRAW: GameStatus.IN_PROGRESS;
        }
        return winner == Mark.X? GameStatus.X_WIN: GameStatus.O_WIN;
    }
        /*    public Winner getWinner(){
        Mark winner = getWinnerMark();
        if(winner==Mark.BLANK&&spacesLeft<=0){
            return Winner.DRAW;
        }

    }
     */

}
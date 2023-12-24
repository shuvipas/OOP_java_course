
enum Winner {DRAW, X_WIN, Y_WIN};
//enum GameStatus {DRAW, X_WIN, O_WIN, IN_PROGRESS};
public class Board {

    
    public static final int SIZE =5;
    public static final int WIN_STREAK = 3;
    private int spacesLeft = SIZE*SIZE; 
    private Mark[][] board = new Mark[SIZE][SIZE]; 
    public Board(){
        
        for(int r =0; r<SIZE; r++){
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = Mark.BLANK;
            }
        }
    }
    
    public boolean isGameEnded(){
        if(spacesLeft>0){
            return false;
        }
        return true;
    }
    //public GameStatus isGameEnded() if i want this way of impolmanting
    private boolean onTheBoard(int row, int col){
        if(row>=SIZE||row<0||col>=SIZE||col<0){
            return false;
        }
        return true;
        
    }
    public Mark getMark(int row, int col){
        if(!onTheBoard(row, col)){
            return Mark.BLANK;
        }

        return board[row][col];
    } 
    public  boolean putMark(Mark mark, int row, int col){
        if(!onTheBoard(row, col)||board[row][col] != Mark.BLANK){
            return false;
        }
        board[row][col] = mark;
        spacesLeft--;
        return true;
    }    
    
}
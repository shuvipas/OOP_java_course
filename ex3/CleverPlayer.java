public class CleverPlayer implements Player {
    private int row;
    private int col;

    CleverPlayer() {
        this.col = 0;
        this.row = 0;
    }

    @Override
    public void playTurn(Board board, Mark mark) {
        if(board.getMark(0, 0)==Mark.BLANK||board.getMark(0, 1)==Mark.BLANK){
                row =0;
                col=0;
            }
        boolean emptyPlace = false;
        while (!emptyPlace) {
            
            emptyPlace = board.putMark(mark, row, col);
            col++;
            if (col == Board.SIZE) {
              //  System.out.printf("row = %d  col= %d\n", row,col);
                this.col = 0;
                this.row = (row + 1 == Board.SIZE) ? 0 : row+1;
               // System.out.printf("row = %d  col= %d\n", row,col);
                // row++;
                // if (row >= Board.SIZE) {
                // row = 0;
                // col =0;
            }
        }

    }
}

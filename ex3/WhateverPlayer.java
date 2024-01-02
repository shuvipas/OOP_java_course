//import java.util.InputMismatchException;
import java.util.Random;

public class WhateverPlayer implements Player{
    private final Random rand = new Random();
    WhateverPlayer(){

    }
    @Override
    public void playTurn(Board board, Mark mark){
        boolean emptyPlace = false;
        while (!emptyPlace) {
            
            int row = rand.nextInt(Board.SIZE);
            int col = rand.nextInt(Board.SIZE);
            emptyPlace = board.putMark(mark, row, col);
                        
        }
    }
}

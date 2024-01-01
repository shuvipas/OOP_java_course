public class Game {

    private final Player playerX, playerO;
    private final Renderer Renderer;
    private final Board board;
    private Player currPlayer;

    

    public Game(Player playerX, Player playerO, Renderer Renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.board = new Board();
        this.Renderer = Renderer;
        // x starts
        currPlayer = playerX;

    }

    public Mark run() {
        // create board
        // Board theBoard = new Board();
        // Mark winner = Mark.BLANK;
        // loop intal the game stops
        this.Renderer.renderBoard(this.board);
        while (board.getGameStatus() == GameStatus.IN_PROGRESS) {
            //this.Renderer.renderBoard(this.board);
            Mark currMark = currPlayer == playerX ? Mark.X : Mark.O;
            System.out.println(currMark+ " turn:");
            currPlayer.playTurn(this.board, currMark);

            currPlayer = (currPlayer == playerX ? playerO : playerX);

            this.Renderer.renderBoard(this.board); // i rendererd the boerd like that i will see it in the start and end of the
        }
        
        GameStatus winner = board.getGameStatus();
        if (winner == GameStatus.DRAW) {
            return Mark.BLANK;
        }
        return winner == GameStatus.X_WIN ? Mark.X : Mark.O;

    }
}

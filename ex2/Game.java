public class Game {

    private final Player playerX, playerO;
    private final Renderer renderer;
    private final Board board;
    private Player currPlayer;

    public static void main(String[] args) {

        Renderer renderer = new Renderer();
        Player px = new Player(), po = new Player();

        Game newGame = new Game(px, po, renderer);
        System.out.println("start game:");
        Mark won = newGame.run();
        System.out.println(won + " has won!!!");
        
        // Mark mark = Mark.X;

        // boardRenderer.renderBoard(this.board);
        // p.playTurn(board,mark);
        // boardRenderer.renderBoard(board);
    }

    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.board = new Board();
        this.renderer = renderer;
        // x starts
        currPlayer = playerX;

    }

    public Mark run() {
        // create board
        // Board theBoard = new Board();
        // Mark winner = Mark.BLANK;
        // loop intal the game stops
        while (board.getGameStatus() == GameStatus.IN_PROGRESS) {
            Mark currMark = currPlayer == playerX ? Mark.X : Mark.O;
            System.out.println(currMark+ " turn:");
            currPlayer.playTurn(this.board, currMark);

            currPlayer = (currPlayer == playerX ? playerO : playerX);

            this.renderer.renderBoard(this.board);
        }
        GameStatus winner = board.getGameStatus();
        if (winner == GameStatus.DRAW) {
            return Mark.BLANK;
        }
        return winner == GameStatus.X_WIN ? Mark.X : Mark.O;

    }
}

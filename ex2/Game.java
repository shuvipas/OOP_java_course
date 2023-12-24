public class Game {
    public static void main(String[] args){
        Board nb = new Board();
        Renderer boardRenderer = new Renderer();
        boardRenderer.renderBoard(nb);
    }
}

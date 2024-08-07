//import java.util.*;

// commend to run the program:
//java Tournament 2 none clever whatever

public class Tournament {
    private final int rounds;
    private final Renderer renderer;
    private final Player player1;
    private final Player player2;
    private static final String RESULTS_MESSAGE = "---Game stats---\n%s (player 1)  won %d rounds.\n" +
            "%s (player 2)  won %d rounds.\nDraws (both lost) %d\n";

    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Error: not enough  argumants");
            System.err.println(
                    "You need 4 args: [round count] [render target: console/none] [player: human/clever/whatever/...] X 2");
            return;
        }
        String strRoundCount = args[0]; // num of rounds to play
        String strRenderTarget = args[1];
        String strPlayer1 = args[2];
        String strPlayer2 = args[3];
        int roundCount = 0;
        try {
            roundCount = Integer.parseInt(strRoundCount);
            if (roundCount < 1) {
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: The strRoundCount is not a valid integer.");
        }

        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderer = rendererFactory.buildRenderer(strRenderTarget);

        PlayerFactory playerFactory = new PlayerFactory();
        Player[] players = { playerFactory.buildPlayer(strPlayer1), playerFactory.buildPlayer(strPlayer2) };
        if(renderer==null||players[0]==null||players[1]==null){
            return;
        }

        Tournament tournament = new Tournament(roundCount, renderer, players[0], players[1]);
        System.out.println("start game:");

        int[] tournamentStats = tournament.playTournament();
        System.out.printf(RESULTS_MESSAGE,
                strPlayer1, tournamentStats[0],
                strPlayer2, tournamentStats[1],
                tournamentStats[2]);

        double percantegeWinning = ((double) tournamentStats[0])/  roundCount;
        System.out.printf("win rate p1 (1/rounds): %.2f%%\n",percantegeWinning);

    }

    Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;

    }

    public int[] playTournament() {
        // Game newGame = new Game(player1, player2, renderer);
        int[] gameStats = { 0, 0, 0 };// [1 wins, 2 wins]
        Player[] players = { player1, player2 };
        int isEven = 1; // 1 when i is even
        for (int i = 0; i < rounds; i++) {
            if (renderer instanceof ConsoleRenderer) {
                System.out.println("new round");
            }

            Game newGame = new Game(players[i % 2], players[isEven], renderer);
            Mark winner = newGame.run();
            switch (winner) {
                case X:
                    gameStats[i % 2]++;
                    break;
                case O:
                    gameStats[isEven]++;
                    break;
                case BLANK:
                    gameStats[2]++;
                    break;

                default:
                    continue;
            }
            isEven ^= 1;
        }
        return gameStats;
    }

}
public class PlayerFactory {
    private final String HUMAN_PLAYER = "human";
    Player buildPlayer(String playerType){
        switch (playerType) {
            case HUMAN_PLAYER:
                return new HumanPlayer();
                
        
            default:
                // bad args err
                return null;
          
        }
        //return new HumanPlayer();
    }
}

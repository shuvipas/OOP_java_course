public class PlayerFactory {
    private final String HUMAN_PLAYER = "human";
    private final String WHATEVER_PLAYER = "whatever"; 
    private final String CLEVER_PLAYER = "clever";
    
    Player buildPlayer(String playerType) {
        switch (playerType) {
            case HUMAN_PLAYER:
                return new HumanPlayer();

            case WHATEVER_PLAYER:
                return new WhateverPlayer();
            
            case CLEVER_PLAYER:
                return new CleverPlayer();

            default:
                // bad args err
                System.out.printf("ERROR: bad player argumant there is no '%s' \n", playerType);
                return null;

        }
        // return new HumanPlayer();
    }
}

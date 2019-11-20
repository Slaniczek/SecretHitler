package cz.mendelu.pef.xslanin1.SecretHitler;

import java.util.HashMap;
import java.util.Map;

class Game {
    private Map<String, String> dictionary = new HashMap<String, String>();
    private State state = new State();

    void start(){
        state.generatePlayers(Constants.PLAYERS_COUNT);

        setupDicrionary();
        System.out.println(dictionary.get("newGame"));

        round();
    };

    private void setupDicrionary(){
        dictionary.put("newGame", "Nová hra");
        dictionary.put("voteYes", "Ja!");
        dictionary.put("voteNo", "Nein!");
        dictionary.put("playOnline", "Hrát online");
    }

    private void round(){
        state.round.inc();

        election();

        state.printState();

        if(state.checkWinner() == null){
            round();
        } else {
            end();
        }
    }

    private void election(){
        state.setPresident(state.nextPresident());
        state.setChancellor(
                state.players.getItem(
                        state.getPresident()
                ).voteChancellor(
                        state.getPresident(),
                        (byte) state.players.getCount()
                )
        );
        if(!state.voteForGov()){
            election();
        }
    };


    public void end(){};
}

package cz.mendelu.pef.xslanin1.SecretHitler;

import java.util.Random;

public class State {
    private byte president = 0;
    private byte hitler;
    private byte chancellor;

    ShortCounter round = new ShortCounter();

    ByteArrayList<Player> players = new ByteArrayList<Player>();


    boolean voteForGov(){
        final byte[] yes = {0};
        players.getItems().forEach((player) -> {
            if(player.vote()){
                yes[0]++;
            }
        });
        return yes[0] > (players.getCount() % 2);
    }

    public void setHitler(byte hitler) {
        this.players.getItem(hitler).setHitler(true);
        this.hitler = hitler;
    }

    /**
     * Dle zadaných pravidel kontroluje zda někdo vyhrál.
     * @return Vítěz nebo null.
     */

    public Policy checkWinner() {
        if(hitler == chancellor) return Policy.FASCIST;
        return round.getValue() >= 5 ? Policy.FASCIST : null;
    }

    public void setPresident(byte president) {
        this.president = president;
    }

    public byte getPresident() {
        return president;
    }

    public void setChancellor(byte chancellor) {
        this.chancellor = chancellor;
    }

    public byte getChancellor() {
        return chancellor;
    }

    /**
     * Vrací id hráče, který bude příštím prezidentem. Id hráče nesmí být vyšší než počet hráčů.
     * @return id hráče, který bude příští prezident.
     */
    byte nextPresident(){
        return president + 1 == players.getCount() ? 0 : (byte)(president + 1);
    };

    /**
     * Generuje id pro hitlera. Hráč, který bude zvolen Hitlerem musí mít typ Policy.FASCIST. Tento hráč je zvolen náhodně.
     * @return Id hráče, který bude Hitler
     */

    byte hitlerIdGenerator(){
        Random rand = new Random();
        byte hitler = (byte) (rand.nextInt(2) + 1);
        byte fasCounter = 0;
        for (byte i = 0; i < Constants.PLAYERS_COUNT; i++) {
            if (players.getItem(i).getType() == Policy.FASCIST) {
                fasCounter++;
                if (fasCounter == hitler) {
                    return i;
                }
            }
        }
        return -1;
    }

    void generatePlayers(byte count){
        for (int i = 0; i < count; i++) {
            players.addItem(
                    new Player(
                            i >= 3 ? Policy.LIBERAL : Policy.FASCIST
                    )
            );
        }
        players.shuffle();

        final byte[] id = {0};

        players.getItems().forEach(player -> {
            player.setId(id[0]);
            id[0]++;
        });

        setHitler(hitlerIdGenerator());

        players.getItems().forEach(player -> {
            System.out.println(player.toString());
        });
    }

    void printState(){
        System.out.println(
                "Round: " + round.getValue()
        );
        System.out.println(
                "Players: " + players.getCount()
        );
        System.out.println(
                "President: " + president
        );
        System.out.println(
                "Chancellor: " + chancellor
        );
        System.out.println(
                "Hitler: " + hitler
        );
    }

    @Override
    public String toString() {
        return "Round: " + round.getValue() + " Players: " + players.getCount() + " President: " + president + " Chancellor: " + chancellor + " Hitler: " + hitler;
    }

    @Override
    public int hashCode() {
        return (int) round.getValue() + players.getCount() + president + chancellor + hitler;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof State){
            State x = (State) obj;

            boolean samePlayers = this.players.getItems().equals(x.players.getItems());

            return this.round.getValue() == x.round.getValue() && samePlayers && this.president == x.president && this.chancellor == x.chancellor && this.hitler == x.hitler;
        }
        return false;
    }
}

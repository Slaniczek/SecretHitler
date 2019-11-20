package cz.mendelu.pef.xslanin1.SecretHitler;

import java.util.Random;

public class Player {
    private byte id;
    private Policy type;
    private boolean isPresident;
    private boolean isHitler;

    Player(Policy type){
        this.type = type;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    public boolean vote(){
        return true;
    }

    public Policy getType() {
        return type;
    }

    /**
     * Náhodně zvolí id hráče, který bude kancléř. Zvolený hráč nesmí být president a musí se vejít do počtu hráčů.
     * @param presidentId       id aktuálního presidenta
     * @param playersCount      počet hráčů ve hře
     * @return                  id kancléře
     */

    byte voteChancellor(byte presidentId, byte playersCount){
        Random random = new Random();
        byte result = (byte)random.nextInt(playersCount - 1);
        return result != presidentId ? result : (presidentId >= playersCount - 1 ? 0 : (byte) (presidentId + 1));
    }

    public void removeCards(){}

    public void setHitler(boolean hitler) {
        isHitler = hitler;
    }

    @Override
    public String toString() {
        return "Hráč s id " + id + ", který " + (isPresident ? "je prezident" : "není prezident") + " a " + (isHitler ? "je Hitler" : "není Hitler") + " a " + (type == Policy.FASCIST ? "je fašista" : "je liberál");
    }

    @Override
    public int hashCode() {
        return 42 + id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Player){
            Player x = (Player) obj;
            return this.type == x.type && this.isHitler == x.isHitler && this.isPresident == x.isPresident;
        }
        return false;
    }
}

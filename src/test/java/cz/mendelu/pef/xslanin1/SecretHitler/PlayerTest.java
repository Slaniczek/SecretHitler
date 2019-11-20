package cz.mendelu.pef.xslanin1.SecretHitler;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void voteChancellorNotPresident() {
        Player pl = new Player(Policy.LIBERAL);
        byte president = 3;
        byte playersCount = 5;
        assertTrue(pl.voteChancellor(president, playersCount) != president);
    }

    @Test
    public void voteChancellor() {
        Player pl = new Player(Policy.LIBERAL);
        byte president = 3;
        byte playersCount = 5;
        assertTrue(pl.voteChancellor(president, playersCount) < playersCount);
    }
}
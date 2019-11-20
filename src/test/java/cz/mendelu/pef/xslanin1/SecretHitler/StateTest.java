package cz.mendelu.pef.xslanin1.SecretHitler;

import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

    State state = new State();

    @Test
    public void fascistWinner() {
        state.setHitler((byte) 1);
        state.setChancellor((byte) 1);
        assertEquals(state.checkWinner(), Policy.FASCIST);
    }

    @Test
    public void stillNotWinner() {
        state.setHitler((byte) 0);
        state.setChancellor((byte) 1);
        assertNull(state.checkWinner());
    }

    @Test
    public void successfulHitlerIdGenerated() {
        state.generatePlayers(Constants.PLAYERS_COUNT);
        byte id = state.hitlerIdGenerator();
        assertTrue( id >= 0 && id <= Constants.PLAYERS_COUNT);
    }

    @Test
    public void successfulHitlerIdGenerated2() {
        state.generatePlayers(Constants.PLAYERS_COUNT);
        byte id = state.hitlerIdGenerator();
        assertNotEquals(id, -1);
    }

    @Test
    public void hitlerIsFascist() {
        state.generatePlayers(Constants.PLAYERS_COUNT);
        byte id = state.hitlerIdGenerator();
        assertTrue( state.players.getItem(id).getType() == Policy.FASCIST);
    }

    @Test
    public void nextPresidentInRange() {
        state.generatePlayers(Constants.PLAYERS_COUNT);
        state.setPresident((byte) (Constants.PLAYERS_COUNT - 1));
        assertEquals(state.nextPresident(), 0);
    }

    @Test
    public void nextPresidentSuccessful() {
        state.generatePlayers(Constants.PLAYERS_COUNT);
        state.setPresident((byte) 1);
        assertEquals(state.nextPresident(), 2);
    }
}
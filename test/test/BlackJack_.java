package test;

import Model.Card;
import static Model.Card.*;
import static Model.HandJava.createHand;
import static Model.GameSimulator.getCards;
import static Model.GameSimulator.getWinners;
import Model.Hand;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Assert;
import static org.junit.Assert.*;

import org.junit.Test;

public class BlackJack_ {
    
    @Test
    public void given_one_card_should_calculate_value() {
        
       assertEquals(5, createHand(_5).value());
       assertEquals(6, createHand(_6).value());
       assertEquals(10, createHand(Jack).value());
       assertEquals(10, createHand(Queen).value());
       assertEquals(10, createHand(King).value());
       assertEquals(11, createHand(Ace).value());
    }
    
    @Test
    public void given_two_cards_should_calculate_value() {
       assertEquals(11, createHand(_5, _6).value());        
       assertEquals(12, createHand(Ace, Ace).value());        
    }
    
    @Test 
    public void given_two_cards_should_determine_if_is_black_jack() {
       assertEquals(false, createHand(_5, _6).isBlackJack());        
       assertEquals(true, createHand(Ace, Queen).isBlackJack());               
       assertEquals(true, createHand(Ace, _10).isBlackJack());               
    }

    @Test 
    public void given_three_cards_should_determine_that_is_not_black_jack() {
       assertEquals(false, createHand(_5, _6, Queen).isBlackJack());               
       assertEquals(false, createHand(King, King, Ace).isBlackJack());               
       assertEquals(false, createHand(_10, Queen, Ace).isBlackJack());               
    }
    
    @Test 
    public void given_two_cards_should_determine_that_is_not_bust() {
       assertEquals(false, createHand(_4,_3).isBust());               
       assertEquals(false, createHand(King,Queen).isBust());               
       assertEquals(false, createHand(Ace,Ace).isBust());               
    }
    
    @Test 
    public void given_three_cards_should_determine_that_is_bust_or_not() {
       assertEquals(true, createHand(_4, Jack, King).isBust());               
       assertEquals(false, createHand(_4, _2, _3).isBust());               
       assertEquals(true, createHand(_4, _10, _8).isBust());               
       assertEquals(false, createHand(_4, Ace, King).isBust());               
    }
    
    @Test 
    public void given_three_cards_should_calculate_value() {
        assertEquals(18, createHand(_5, _6, _7).value());  
        assertEquals(18, createHand(Ace, King, _7).value());  
        assertEquals(9, createHand(_2, _4, _3).value());  
    }
    
    @Test
    public void test_hit() {
        assertEquals(9, createHand(_3, _4).hit(_2).value());
        assertEquals(12, createHand(_3, _4).hit(_5).value());
        assertEquals(12, createHand(Ace, Ace).hit(King).value());
    }
    
    @Test
    public void given_croupier_with_blackjack_no_winners() {
        String[] res = null;
                
        Hand[] hands = new Hand[4];
        hands[0] = createHand(Ace, King);
        hands[1] = createHand(King, King, Ace);
        hands[2] = createHand(_2, King, King);
        hands[3] = createHand(_10, Ace);
        Card[] cards = getCards(Ace, _4, _3, King, Queen);
        
        Assert.assertArrayEquals(res, getWinners(hands, cards));
    }
    
    @Test
    public void given_croupier_Busted_everyone_wins() {
        String[] res1 = new String[3];
        res1[0] = "Player 1";
        res1[1] = "Player 2";
        res1[2] = "Player 3";
        Hand[] hands = new Hand[4];
        hands[0] = createHand(Queen, King);
        hands[1] = createHand(King, King, Ace);
        hands[2] = createHand(_2, _3, King);
        hands[3] = createHand(_10, _5);
        Card[] cards = getCards(King, _4, _3, King, Queen);
        
        Assert.assertArrayEquals(res1, getWinners(hands, cards));
    }
    
    @Test
    public void given_croupier_Busted_not_busted_players_win() {
        String[] res1 = new String[2];
        res1[0] = "Player 2";
        res1[1] = "Player 3";
        Hand[] hands = new Hand[4];
        hands[0] = createHand(Queen, King,_4);
        hands[1] = createHand(King, King, Ace);
        hands[2] = createHand(_2, _3, King);
        hands[3] = createHand(_10, _5);
        Card[] cards = getCards(King, _4, _3, King, Queen);
        
        Assert.assertArrayEquals(res1, getWinners(hands, cards));
    }
    
    @Test
    public void given_player_with_blackJack_and_croupier_with_21_should_win() {
        String[] res1 = new String[2];
        res1[0] = "Player 2";
        res1[1] = "Player 3";
        Hand[] hands = new Hand[4];
        hands[0] = createHand(Queen, King, Ace);
        hands[1] = createHand(Queen, Ace);
        hands[2] = createHand(Ace, King);
        hands[3] = createHand(_3, _7);
        Card[] cards = getCards(_6, _5, _3, King, Queen);
        
        Assert.assertArrayEquals(res1, getWinners(hands, cards));
    }

    

}

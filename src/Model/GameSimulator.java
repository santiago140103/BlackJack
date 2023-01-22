package Model;

import java.util.ArrayList;
import java.util.List;

public class GameSimulator {
    
    public GameSimulator() {
        
    }
    
    public static String[] getWinners(Hand[] hands, Card[] cards) {
        
        if (hands[3].isBlackJack()) return null;
        List<String> winners = new ArrayList<>();
        Hand croupier = getCroupierHand(hands[3], cards);
        
        for (int i=0;i<3;i++) {
            
            if (hands[i].isBlackJack()) {
                winners.add("Player " + (i+1));
                continue;
            }
            
            if (hands[i].value() > croupier.value() && !hands[i].isBust() || (croupier.isBust() && !hands[i].isBust())) 
                winners.add("Player " + (i+1));
            
        }
        
        String[] res = new String[winners.size()];
        for (int i=0;i<winners.size();i++)
            res[i] = winners.get(i);
        
        for (String win : res)
            System.out.println(win);
                
        return res;
    }
    
    private static Hand getCroupierHand(Hand hand, Card[] cards) {
        if (hand.value()>=17)
            return hand;
        
        hand = hand.hit(cards[0]);
        int i = 1;
        while (hand.value()<17) {
            hand = hand.hit(cards[i]);
            i+=1;
        }
        
        return hand;
    }
    
    public static Card[] getCards(Card... cards) {
        return cards;
    }
    
}

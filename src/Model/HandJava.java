package Model;

import static Model.Card.*;
import java.util.stream.Stream;

public class HandJava implements Hand{
    
    private Card[] cards;

    private HandJava(Card... cards) {
        this.cards = cards;
    } 
    
    @Override
    public int value() {
        return canUseAceExtendedValue() ? sum() + 10 : sum();
    }

    private int sum() {
        return Stream.of(cards).mapToInt(c->c.value()).sum();
    }

    private boolean canUseAceExtendedValue() {
        return sum() <= 11 && containsAce();
    }

    @Override
    public Hand hit(Card card) {
        Card[] newCards = new Card[cards.length+1];
        for (int i=0;i<cards.length;i++)
            newCards[i] = cards[i];
        newCards[newCards.length-1] = card;
        return createHand(newCards);
    }

    private boolean containsAce() {
        return Stream.of(cards).anyMatch(c->c==Ace);
    }

    @Override
    public boolean isBlackJack() {
        return value() == 21 && cards.length == 2;
    }

    @Override
    public boolean isBust() {                
        return value() > 21;
    }
    
    
    public static Hand createHand(Card... cards) {
        return new HandJava(cards);
    }
    
}



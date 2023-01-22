package Model;

public interface Hand {
    int value();
        boolean isBlackJack();
        boolean isBust();
        public Hand hit(Card card);
}

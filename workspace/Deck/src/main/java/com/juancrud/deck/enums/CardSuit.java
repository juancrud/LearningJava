package com.juancrud.deck.enums;

import java.util.HashMap;
import java.util.Map;

public enum CardSuit {
	Spades(0), 
	Hearts(1), 
	Diamonds(2), 
	Clubs(3);
	
	//Reverse-lookup map for getting a CardSuit from a value
    private static final Map<Integer, CardSuit> lookup = new HashMap<Integer, CardSuit>();
    static {
        for (CardSuit suit : CardSuit.values()) {
            lookup.put(suit.getValue(), suit);
        }
    }

	private final int value;

	CardSuit(int value) {
		this.value = value;
	}
	
	public static CardSuit fromValue(int value) {
        return lookup.get(value);
    }

	public int getValue() {
		return value;
	}

	public CardColor getCardColor() {
		switch (this) {
		case Diamonds:
		case Hearts:
			return CardColor.Red;
		default:
			return CardColor.Black;
		}
	}
}

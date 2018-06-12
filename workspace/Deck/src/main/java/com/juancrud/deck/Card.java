package com.juancrud.deck;

import com.juancrud.deck.enums.CardRank;
import com.juancrud.deck.enums.CardSuit;

public class Card implements ICard {
	private final int value;

	public Card(CardSuit suit, CardRank rank) {
		this.value = suit.getValue() * 13 + rank.getValue();
	}

	public Card(int value) {
		this.value = value;
	}

	public CardSuit getSuit() {
		return CardSuit.fromValue((value - 1) / 13);
	}

	public CardRank getRank() {
		return CardRank.fromValue((value % 13) + 1);
	}

	@Override
	public String toString() {
		return String.format("%s = %s of %s", value, getRank(), getSuit());
	}
	
	public String toShortString() {
		return String.format("%s%s", getRank().getAbreviation(), getSuit());
	}

}

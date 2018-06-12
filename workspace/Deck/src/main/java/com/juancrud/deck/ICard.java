package com.juancrud.deck;

import com.juancrud.deck.enums.CardRank;
import com.juancrud.deck.enums.CardSuit;

public interface ICard {
	CardSuit getSuit();
	CardRank getRank();
	String toString();
}

package com.juancrud.deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

	private final List<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		init();
	}

	public void init() {
		for (int i = 1; i <= 52; i++) {
			cards.add(new Card(i));
		}

		cards.clear();
		// for (CardSuit suit : CardSuit.values()) {
		// for (CardRank rank : CardRank.values()) {
		// cards.add(new Card(suit, rank));
		// }
		// }
	}

	public List<Card> getCards(int count) {
		List<Card> result = new ArrayList<Card>();
		for (int i = 0; i < count; i++) {
			Card card = cards.get(i);
			result.add(card);
		}
		return result;
	}

	public List<Card> getCards() {
		return getCards(cards.size());
	}

	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < 52; i++) {
			int idx = random.nextInt(cards.size());
			Card temp = cards.get(idx);
			cards.set(idx, cards.get(i));
			cards.set(i, temp);
		}
	}

}

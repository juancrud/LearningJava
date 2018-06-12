package com.juancrud.deck.enums;

import java.util.HashMap;
import java.util.Map;

public enum CardRank {
	Ace("A", 1),
	Two("2", 2),
	Three("3", 3),
	Four("4", 4),
	Five("5", 5),
	Six("6", 6),
	Seven("7", 7),
	Eight("8", 8),
	Nine("9", 9),
	Ten("10", 10),
	Jack("J", 11),
	Queen("Q", 12),
	King("K", 13);
	
	//Reverse-lookup map for getting a CardRank from a value
    private static final Map<Integer, CardRank> lookup = new HashMap<Integer, CardRank>();
    static {
        for (CardRank rank : CardRank.values()) {
            lookup.put(rank.getValue(), rank);
        }
    }
	
	private final String abreviation;
	private final int value;
	
	CardRank(String abreviation, int value){
		this.abreviation = abreviation;
		this.value = value;
	}
	
	public static CardRank fromValue(int value) {
        return lookup.get(value);
    }
	
	public String getAbreviation() {
		return abreviation;
	}
	
	public int getValue() {
		return value;
	}
}

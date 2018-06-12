package com.juancrud.linq.tests.helpers;

import java.util.Collection;

import com.juancrud.linq.delegates.Action;
import com.juancrud.linq.delegates.Function;
import com.juancrud.linq.delegates.Function2;
import com.juancrud.linq.delegates.Predicate;
import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.classes.PrimitiveCollectionPair;

public class DelegatesHelper {
	
	// Function 
	
	public static Function<Integer, Integer> getSumFunction(final int number) {
		return new Function<Integer, Integer>() {
			public Integer execute(Integer value) {
				return value + number;
			}
		};
	}
	
	public static Function<IntStringPair, Double> getDoubleValueFunction() {
		return new Function<IntStringPair, Double>() {
			public Double execute(IntStringPair value) {
				return (double)value.getIntValue();
			}
		};
	}
	
	public static Function<IntStringPair, String> getStringValueFunction() {
		return new Function<IntStringPair, String>() {
			public String execute(IntStringPair value) {
				return value.getStringValue();
			}
		};
	}
	
	public static <T> Function<PrimitiveCollectionPair<T>, Collection<T>> getCollectionFunction() {
		return new Function<PrimitiveCollectionPair<T>, Collection<T>>() {
			public Collection<T> execute(PrimitiveCollectionPair<T> value) {
				return value.getCollection();
			}
		};
	}
	
	public static Function<Integer, Collection<String>> getStringCollectionFunction() {
		return new Function<Integer, Collection<String>>() {
			public Collection<String> execute(Integer value) {
				return CollectionsHelper.getStringCollection();
			}
		};
	}
	
	
	// Function2
	
	public static Function2<Integer, String, IntStringPair> getIntStringPairFunction() {
		return new Function2<Integer, String, IntStringPair>() {
			public IntStringPair execute(Integer value1, String value2) {
				return new IntStringPair(value1, value2);
			}
		};
	}
	
	public static Function2<Integer, Integer, Integer> getSumFunction() {
		return new Function2<Integer, Integer, Integer>() {
			public Integer execute(Integer value1, Integer value2) {
				return value1 + value2;
			};
		};
	}
	
	public static Function2<StringBuilder, String, StringBuilder> getConcatFunction() {
		return new Function2<StringBuilder, String, StringBuilder>() {
			public StringBuilder execute(StringBuilder value1, String value2) {
				value1.append(value2);
				return value1;
			};
		};
	}
	
	public static Function2<Collection<String>, String, Collection<String>> getAddToCollectionFunction() {
		return new Function2<Collection<String>, String, Collection<String>>() {
			public Collection<String> execute(Collection<String> value1, String value2) {
				value1.add(value2);
				return value1;
			}
		};
	}
	
	
	// Predicate
	
	public static Predicate<Integer> getIsEqualPredicate(final int intValue) {
		return new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value == intValue;
			}
		};
	}
	
	public static Predicate<Integer> getIsGreaterThanPredicate(final int intValue) {
		return new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value > intValue;
			}
		};
	}
	
	public static Predicate<Integer> getIsLessThanPredicate(final int intValue) {
		return new Predicate<Integer>() {
			public boolean execute(Integer value) {
				return value < intValue;
			}
		};
	}
	
	public static Predicate<String> getIsNotEqualPredicate(final String stringValue) {
		return new Predicate<String>() {
			public boolean execute(String value) {
				return !value.equals(stringValue);
			}
		};
	}
	
	public static Predicate<IntStringPair> getIntStringPairIsEqualPredicate(final int intValue) {
		return new Predicate<IntStringPair>() {
			public boolean execute(IntStringPair value) {
				return value.getIntValue() == intValue;
			}
		};
	}
	
	public static Predicate<PrimitiveCollectionPair<Integer>> getCollectionNotEmptyPredicate() {
		return new Predicate<PrimitiveCollectionPair<Integer>>() {
			public boolean execute(PrimitiveCollectionPair<Integer> value) {
				return !value.getCollection().isEmpty();
			}
		};
	}
	
	
	// Action
	
	public static Action<String> getConcatStringAction(final StringBuilder stringBuilder) {
		return new Action<String>() {
			public void execute(String value) {
				stringBuilder.append(value);
			}
		};
	}
	
	public static Action<Integer> getSumAction(final IntStringPair intStringPair) {
		return new Action<Integer>() {
			public void execute(Integer value) {
				int originalValue = intStringPair.getIntValue();
				intStringPair.setIntValue(originalValue + value);
			}
		};
	}
	
}

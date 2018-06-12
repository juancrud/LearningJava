package com.juancrud.linq.tests.helpers;

import java.util.ArrayList;
import java.util.Collection;

import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.classes.Numbers;
import com.juancrud.linq.tests.classes.PrimitiveCollectionPair;

public class CollectionsHelper {
	
	public static Collection<Numbers> getNumbersCollection(int start, int end) {
		Collection<Numbers> collection = new ArrayList<Numbers>();
		for(int i = start; i <= end; i++) {
			Integer integer = Integer.valueOf(i);
			
			Numbers numbers = new Numbers();
			numbers.setDoubleValue(integer.doubleValue());
			numbers.setFloatValue(integer.floatValue());
			numbers.setLongValue(integer.longValue());
			numbers.setIntegerValue(integer.intValue());
			
			collection.add(numbers);
		}
		return collection;
	}
	
	public static Collection<Numbers> getNumbersCollection() {
		return getNumbersCollection(1, 10);
	}
	
	public static Collection<Integer> getIntCollection(int start, int end) {
		Collection<Integer> collection = new ArrayList<Integer>();
		for(int i = start; i <= end; i++) {
			collection.add(i);
		}
		return collection;
	}
	
	public static Collection<Integer> getIntCollection() {
		return getIntCollection(1, 10);
	}
	
	public static Collection<String> getStringCollection() {
		Collection<String> collection = new ArrayList<String>();
		collection.add("One");
		collection.add("Two");
		collection.add("Three");
		collection.add("Four");
		collection.add("Five");
		collection.add("Six");
		collection.add("Seven");
		collection.add("Eight");
		collection.add("Nine");
		collection.add("Ten");
		return collection;
	}
	
	public static Collection<IntStringPair> getIntStringPairCollection() {
		Collection<IntStringPair> collection = new ArrayList<IntStringPair>();
		collection.add(new IntStringPair(1, "One"));
		collection.add(new IntStringPair(2, "Two"));
		collection.add(new IntStringPair(3, "Three"));
		collection.add(new IntStringPair(4, "Four"));
		collection.add(new IntStringPair(5, "Five"));
		collection.add(new IntStringPair(6, "Six"));
		collection.add(new IntStringPair(7, "Seven"));
		collection.add(new IntStringPair(8, "Eight"));
		collection.add(new IntStringPair(9, "Nine"));
		collection.add(new IntStringPair(10, "Ten"));
		return collection;
	}
	
	public static Collection<PrimitiveCollectionPair<Integer>> getIntPrimitiveCollectionPairCollection() {
		Collection<PrimitiveCollectionPair<Integer>> collection = new ArrayList<PrimitiveCollectionPair<Integer>>();
		collection.add(new PrimitiveCollectionPair<Integer>(1, getIntCollection()));
		collection.add(new PrimitiveCollectionPair<Integer>(2, getIntCollection()));
		collection.add(new PrimitiveCollectionPair<Integer>(3, getIntCollection()));
		collection.add(new PrimitiveCollectionPair<Integer>(4, getIntCollection()));
		collection.add(new PrimitiveCollectionPair<Integer>(5, getIntCollection()));
		collection.add(new PrimitiveCollectionPair<Integer>(6, getIntCollection()));
		collection.add(new PrimitiveCollectionPair<Integer>(7, getIntCollection()));
		collection.add(new PrimitiveCollectionPair<Integer>(8, getIntCollection()));
		collection.add(new PrimitiveCollectionPair<Integer>(9, getIntCollection()));
		collection.add(new PrimitiveCollectionPair<Integer>(10, getIntCollection()));
		return collection;
	}
	
	public static Collection<PrimitiveCollectionPair<String>> getStringPrimitiveCollectionPairCollection() {
		Collection<PrimitiveCollectionPair<String>> collection = new ArrayList<PrimitiveCollectionPair<String>>();
		collection.add(new PrimitiveCollectionPair<String>("One", getStringCollection()));
		collection.add(new PrimitiveCollectionPair<String>("Two", getStringCollection()));
		collection.add(new PrimitiveCollectionPair<String>("Three", getStringCollection()));
		collection.add(new PrimitiveCollectionPair<String>("Four", getStringCollection()));
		collection.add(new PrimitiveCollectionPair<String>("Five", getStringCollection()));
		collection.add(new PrimitiveCollectionPair<String>("Six", getStringCollection()));
		collection.add(new PrimitiveCollectionPair<String>("Seven", getStringCollection()));
		collection.add(new PrimitiveCollectionPair<String>("Eight", getStringCollection()));
		collection.add(new PrimitiveCollectionPair<String>("Nine", getStringCollection()));
		collection.add(new PrimitiveCollectionPair<String>("Ten", getStringCollection()));
		return collection;
	}
	
}

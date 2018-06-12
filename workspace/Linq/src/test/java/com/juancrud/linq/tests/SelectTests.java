package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.Function;
import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;
import com.juancrud.linq.tests.helpers.DelegatesHelper;

public class SelectTests {

	@Test
	public void testSelect_intCollection() {
		Object[] expected = CollectionsHelper.getIntCollection(2, 11).toArray();
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Function<Integer, Integer> f = DelegatesHelper.getSumFunction(1);
		
		// Act
		LinqCollection<Integer> result = linqCollection.select(f);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSelect_intStringPairCollection() {
		Object[] expected = CollectionsHelper.getStringCollection().toArray();
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		Function<IntStringPair, String> f = DelegatesHelper.getStringValueFunction();
		
		// Act
		LinqCollection<String> result = linqCollection.select(f);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}

}

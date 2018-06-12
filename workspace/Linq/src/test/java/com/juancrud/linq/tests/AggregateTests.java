package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.Function2;
import com.juancrud.linq.exceptions.LinqException;
import com.juancrud.linq.tests.helpers.CollectionsHelper;
import com.juancrud.linq.tests.helpers.DelegatesHelper;

public class AggregateTests {

	@Test
	@Ignore
	public void testAggregate_intCollection() throws LinqException {
		int expected = 0;
		for(int i = 1; i <= 10; i++) {
			expected += i;
		}
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Function2<Integer, Integer, Integer> f = DelegatesHelper.getSumFunction();
		
		// Act
		int result = linqCollection.aggregate(f, Integer.class);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testAggregate_intCollection_WithSeed() throws LinqException {
		int expected = 5;
		for(int i = 1; i <= 10; i++) {
			expected += i;
		}
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Function2<Integer, Integer, Integer> f = DelegatesHelper.getSumFunction();
		
		// Act
		int result = linqCollection.aggregate(5, f);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testAggregate_stringCollection_WithStringBuilderSeed() throws LinqException {
		String expected = "";
		for(String s : CollectionsHelper.getStringCollection()) {
			expected += s;
		}
		
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		Function2<StringBuilder, String, StringBuilder> f = DelegatesHelper.getConcatFunction();
		
		// Act
		StringBuilder seed = new StringBuilder();
		StringBuilder result = linqCollection.aggregate(seed, f);
		
		// Assert
		assertEquals(expected, result.toString());
	}
	
	@Test
	public void testAggregate_stringCollection_WithSeed() throws LinqException {
		Object[] expected = CollectionsHelper.getStringCollection().toArray();
		
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		Function2<Collection<String>, String, Collection<String>> f = DelegatesHelper.getAddToCollectionFunction();
		
		// Act
		Collection<String> seed = new ArrayList<String>();
		Collection<String> result = linqCollection.aggregate(seed, f);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}

}

package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.tests.helpers.CollectionsHelper;

public class UnionTests {

	@Test
	public void testUnion_stringCollection_sameSets() {
		Collection<String> expected = CollectionsHelper.getStringCollection();
		
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		Collection<String> collection2 = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		LinqCollection<String> result = linqCollection.union(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testUnion_intCollection_differentSets() {
		Collection<Integer> expected = new ArrayList<Integer>();
		expected.addAll(CollectionsHelper.getIntCollection(5, 10));
		expected.addAll(CollectionsHelper.getIntCollection(1, 4));
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection(5, 10);
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Collection<Integer> collection2 = CollectionsHelper.getIntCollection(1, 5);
		LinqCollection<Integer> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		LinqCollection<Integer> result = linqCollection.union(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testUnion_intCollection_emptySet() {
		Collection<Integer> expected = CollectionsHelper.getIntCollection();
		
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		Collection<Integer> collection2 = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		LinqCollection<Integer> result = linqCollection.union(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=NullPointerException.class)
	public void testUnion_intCollection_nullSet() {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		linqCollection.union(null);
	}
	
}

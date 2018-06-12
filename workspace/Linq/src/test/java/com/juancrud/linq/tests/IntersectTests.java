package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.tests.helpers.CollectionsHelper;

public class IntersectTests {

	@Test
	public void testIntersect_stringCollection_sameSets() {
		Collection<String> expected = CollectionsHelper.getStringCollection();
		
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		Collection<String> collection2 = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		LinqCollection<String> result = linqCollection.intersect(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testIntersect_intCollection_differentSets() {
		Collection<Integer> expected = CollectionsHelper.getIntCollection(4, 6);
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection(1, 6);
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Collection<Integer> collection2 = CollectionsHelper.getIntCollection(4, 10);
		LinqCollection<Integer> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		LinqCollection<Integer> result = linqCollection.intersect(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testIntersect_stringCollection_emptySet() {
		Collection<String> expected = new ArrayList<String>();
		
		// Prepare
		LinqCollection<String> linqCollection = new LinqCollection<String>();
		
		Collection<String> collection2 = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		LinqCollection<String> result = linqCollection.intersect(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=NullPointerException.class)
	public void testIntersect_intCollection_nullSet() {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		// Act
		linqCollection.intersect(null);
	}
	
}

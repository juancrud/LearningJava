package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.tests.helpers.CollectionsHelper;

public class ExceptTests {

	@Test
	public void testExcept_intCollection_differentSets() {
		Collection<Integer> expected = CollectionsHelper.getIntCollection(4, 6);
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Collection<Integer> collection2 = CollectionsHelper.getIntCollection(1, 3);
		collection2.addAll(CollectionsHelper.getIntCollection(7, 15));
		LinqCollection<Integer> linqCollection2 = new LinqCollection<Integer>(collection2);
		
		// Act
		LinqCollection<Integer> result = linqCollection.except(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testExcept_stringCollection_sameSets() {
		Collection<String> expected = new ArrayList<String>();
		
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		Collection<String> collection2 = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		LinqCollection<String> result = linqCollection.except(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testExcept_stringCollection_emptySet1() {
		Collection<String> expected = new ArrayList<String>();
		
		// Prepare
		LinqCollection<String> linqCollection = new LinqCollection<String>();
		
		Collection<String> collection2 = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		LinqCollection<String> result = linqCollection.except(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testExcept_stringCollection_emptySet2() {
		Collection<String> expected = CollectionsHelper.getStringCollection();
		
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		LinqCollection<String> linqCollection2 = new LinqCollection<String>();
		
		// Act
		LinqCollection<String> result = linqCollection.except(linqCollection2);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=NullPointerException.class)
	public void testExcept_intCollection_nullSet() {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		linqCollection.add(1);
		
		// Act
		linqCollection.except(null);
	}
	
}

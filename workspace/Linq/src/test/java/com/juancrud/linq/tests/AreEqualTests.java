package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.exceptions.LinqException;
import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;

public class AreEqualTests {

	@Test
	public void testAreEqual_intCollection() throws LinqException {
		// Prepare
		Collection<Integer> collection1 = CollectionsHelper.getIntCollection();
		Collection<Integer> collection2 = CollectionsHelper.getIntCollection();
		collection2.remove(4);
		LinqCollection<Integer> linqCollection1 = LinqCollection.Get(collection1);
		LinqCollection<Integer> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		boolean result = linqCollection1.areEqual(linqCollection2);
		
		// Assert
		assertFalse(result);
	}
	
	@Test
	public void testAreEqual_stringCollection() throws LinqException {
		// Prepare
		Collection<String> collection1 = CollectionsHelper.getStringCollection();
		Collection<String> collection2 = CollectionsHelper.getStringCollection();
		collection2.clear();
		LinqCollection<String> linqCollection1 = LinqCollection.Get(collection1);
		LinqCollection<String> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		boolean result = linqCollection1.areEqual(linqCollection2);
		
		// Assert
		assertFalse(result);
	}
	
	@Test
	public void testAreEqual_intStringPairCollection() throws LinqException {
		// Prepare
		Collection<IntStringPair> collection1 = CollectionsHelper.getIntStringPairCollection();
		Collection<IntStringPair> collection2 = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection1 = LinqCollection.Get(collection1);
		LinqCollection<IntStringPair> linqCollection2 = LinqCollection.Get(collection2);
		
		// Act
		boolean result = linqCollection1.areEqual(linqCollection2);
		
		// Assert
		assertTrue(result);
	}

}

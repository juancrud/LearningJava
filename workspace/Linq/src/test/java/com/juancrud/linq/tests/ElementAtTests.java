package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.exceptions.LinqException;
import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;

public class ElementAtTests {
	
	@Test
	public void testElementAt_intStringPairCollection() throws LinqException {
		IntStringPair expected = new IntStringPair(4, "Four");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		// Act
		IntStringPair result = linqCollection.elementAt(3);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void testElementAt_intCollection_largerIndex() throws LinqException {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		linqCollection.elementAt(15);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void testElementAt_intCollection_negativeIndex() throws LinqException {
		// Prepare
		LinqCollection<Integer> linqCollection = new LinqCollection<Integer>();
		
		// Act
		linqCollection.elementAt(-1);
	}
	
	@Test
	public void testElementAtOrDefaut_intStringPairCollection() throws LinqException {
		IntStringPair expected = new IntStringPair(4, "Four");
		
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		// Act
		IntStringPair result = linqCollection.elementAtOrDefault(3);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testElementAtOrDefaut_intStringPairCollection_largerIndex() throws LinqException {
		// Prepare
		Collection<IntStringPair> collection = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection);
		
		// Act
		IntStringPair result = linqCollection.elementAtOrDefault(11);
		
		// Assert
		assertNull(result);
	}
	
	@Test
	public void testElementAtOrDefaut_intCollection_negativeIndex() throws LinqException {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		Integer result = linqCollection.elementAtOrDefault(-1);
		
		// Assert
		assertNull(result);
	}
	
}

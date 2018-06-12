package com.juancrud.linq.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.Predicate;
import com.juancrud.linq.tests.classes.PrimitiveCollectionPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;
import com.juancrud.linq.tests.helpers.DelegatesHelper;

public class AnyTests {
	
	@Test
	public void testAny_intCollection() {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = DelegatesHelper.getIsGreaterThanPredicate(10);
		
		// Act
		boolean result = linqCollection.any(p);
		
		// Assert
		assertFalse(result);
	}
	
	@Test
	public void testAny_primitiveCollectionPairCollection() {
		// Prepare
		Collection<PrimitiveCollectionPair<Integer>> collection = CollectionsHelper.getIntPrimitiveCollectionPairCollection();
		LinqCollection<PrimitiveCollectionPair<Integer>> linqCollection = LinqCollection.Get(collection);
		
		Predicate<PrimitiveCollectionPair<Integer>> p = DelegatesHelper.getCollectionNotEmptyPredicate();
		
		// Act
		boolean result = linqCollection.any(p);
		
		// Assert
		assertTrue(result);
	}
	
	@Test
	public void testAny_stringCollection() {
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		// Act
		boolean result = linqCollection.any();
		
		// Assert
		assertTrue(result);
	}
	
}

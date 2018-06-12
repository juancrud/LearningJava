package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.Predicate;
import com.juancrud.linq.tests.classes.PrimitiveCollectionPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;
import com.juancrud.linq.tests.helpers.DelegatesHelper;

public class AllTests {
	
	@Test
	public void testAll_intCollection() {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> p = DelegatesHelper.getIsGreaterThanPredicate(5);
		
		// Act
		boolean result = linqCollection.all(p);
		
		// Assert
		assertFalse(result);
	}
	
	@Test
	public void testAll_primitiveCollectionPairCollection() {
		// Prepare
		Collection<PrimitiveCollectionPair<Integer>> collection = CollectionsHelper.getIntPrimitiveCollectionPairCollection();
		LinqCollection<PrimitiveCollectionPair<Integer>> linqCollection = LinqCollection.Get(collection);
		
		Predicate<PrimitiveCollectionPair<Integer>> p = DelegatesHelper.getCollectionNotEmptyPredicate();
		
		// Act
		boolean result = linqCollection.all(p);
		
		// Assert
		assertTrue(result);
	}
	
}

package com.juancrud.linq.tests;

import static org.junit.Assert.assertArrayEquals;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.tests.classes.IntStringPair;
import com.juancrud.linq.tests.helpers.CollectionsHelper;

public class DistinctTests {
	
	@Test
	public void testDistinct_stringCollection() {
		Object[] expected = CollectionsHelper.getStringCollection().toArray();
		
		// Prepare
		Collection<String> collection1 = CollectionsHelper.getStringCollection();
		Collection<String> collection2 = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection1);
		linqCollection.addAll(LinqCollection.Get(collection2));
		
		// Act
		LinqCollection<String> result = linqCollection.distinct();
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testDistinct_intStringPairCollection() {
		Object[] expected = CollectionsHelper.getIntStringPairCollection().toArray();
		
		// Prepare
		Collection<IntStringPair> collection1 = CollectionsHelper.getIntStringPairCollection();
		Collection<IntStringPair> collection2 = CollectionsHelper.getIntStringPairCollection();
		LinqCollection<IntStringPair> linqCollection = LinqCollection.Get(collection1);
		linqCollection.addAll(LinqCollection.Get(collection2));
		
		// Act
		LinqCollection<IntStringPair> result = linqCollection.distinct();
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
}

package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.tests.helpers.CollectionsHelper;

public class CastTests {

	@Test
	public void testCast_stringCollection() {
		Object[] expected = CollectionsHelper.getStringCollection().toArray();
		
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		// Act
		LinqCollection<Object> result = linqCollection.cast(Object.class);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test (expected=ClassCastException.class)
	public void testCast_intCollection() {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		linqCollection.cast(String.class);
	}

}

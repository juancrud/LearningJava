package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.exceptions.LinqException;
import com.juancrud.linq.tests.helpers.CollectionsHelper;

public class SkipTakeTests {

	@Test
	public void testSkipTake_intCollection() throws LinqException {
		Object[] expected = CollectionsHelper.getIntCollection(2, 6).toArray();
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		LinqCollection<Integer> result = linqCollection.skip(1).take(5);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSkipTake_intCollection_emptySkip() throws LinqException {
		Object[] expected = CollectionsHelper.getIntCollection(1, 5).toArray();
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		LinqCollection<Integer> result = linqCollection.skip(0).take(5);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSkipTake_intCollection_emptyTake() throws LinqException {
		Object[] expected = new Object[0];
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		LinqCollection<Integer> result = linqCollection.skip(5).take(0);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSkipTake_intCollection_completeSkip() throws LinqException {
		Object[] expected = new Object[0];
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		LinqCollection<Integer> result = linqCollection.skip(10);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSkipTake_intCollection_completeTake() throws LinqException {
		Object[] expected = CollectionsHelper.getIntCollection().toArray();
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		LinqCollection<Integer> result = linqCollection.take(10);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test (expected=LinqException.class)
	public void testSkipTake_intCollection_exception() throws LinqException {
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		// Act
		linqCollection.skip(10).take(5);
	}
	
	@Test (expected=LinqException.class)
	public void testSkipTake_stringCollection_exception() throws LinqException {
		// Prepare
		Collection<String> collection = CollectionsHelper.getStringCollection();
		LinqCollection<String> linqCollection = LinqCollection.Get(collection);
		
		// Act
		linqCollection.skip(1).take(10);
	}

}

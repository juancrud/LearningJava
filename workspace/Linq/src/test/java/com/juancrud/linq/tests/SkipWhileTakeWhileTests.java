package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.Predicate;
import com.juancrud.linq.exceptions.LinqException;
import com.juancrud.linq.tests.helpers.CollectionsHelper;
import com.juancrud.linq.tests.helpers.DelegatesHelper;

public class SkipWhileTakeWhileTests {

	@Test
	public void testSkipWhileTakeWhile_intCollection() throws LinqException {
		Object[] expected = CollectionsHelper.getIntCollection(3, 7).toArray();
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> skipF = DelegatesHelper.getIsLessThanPredicate(3);
		Predicate<Integer> takeF = DelegatesHelper.getIsLessThanPredicate(8);
		
		// Act
		LinqCollection<Integer> result = linqCollection.skipWhile(skipF).takeWhile(takeF);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSkipWhileTakeWhile_intCollection_emptySkip() throws LinqException {
		Object[] expected = CollectionsHelper.getIntCollection(1, 5).toArray();
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> skipF = DelegatesHelper.getIsLessThanPredicate(-1);
		Predicate<Integer> takeF = DelegatesHelper.getIsLessThanPredicate(6);
		
		// Act
		LinqCollection<Integer> result = linqCollection.skipWhile(skipF).takeWhile(takeF);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSkipWhileTakeWhile_intCollection_emptyTake() throws LinqException {
		Object[] expected = new Object[0];
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> skipF = DelegatesHelper.getIsLessThanPredicate(5);
		Predicate<Integer> takeF = DelegatesHelper.getIsLessThanPredicate(5);
		
		// Act
		LinqCollection<Integer> result = linqCollection.skipWhile(skipF).takeWhile(takeF);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSkipWhileTakeWhile_intCollection_completeSkip() throws LinqException {
		Object[] expected = new Object[0];
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> skipF = DelegatesHelper.getIsLessThanPredicate(100);
		
		// Act
		LinqCollection<Integer> result = linqCollection.skipWhile(skipF);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}
	
	@Test
	public void testSkipWhileTakeWhile_intCollection_completeTake() throws LinqException {
		Object[] expected = CollectionsHelper.getIntCollection().toArray();
		
		// Prepare
		Collection<Integer> collection = CollectionsHelper.getIntCollection();
		LinqCollection<Integer> linqCollection = LinqCollection.Get(collection);
		
		Predicate<Integer> takeF = DelegatesHelper.getIsLessThanPredicate(100);
		
		// Act
		LinqCollection<Integer> result = linqCollection.takeWhile(takeF);
		
		// Assert
		assertArrayEquals(expected, result.toArray());
	}

}

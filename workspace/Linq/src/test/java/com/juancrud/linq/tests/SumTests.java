package com.juancrud.linq.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import com.juancrud.linq.LinqCollection;
import com.juancrud.linq.delegates.DoubleFunction;
import com.juancrud.linq.delegates.FloatFunction;
import com.juancrud.linq.delegates.IntegerFunction;
import com.juancrud.linq.delegates.LongFunction;
import com.juancrud.linq.tests.classes.Numbers;
import com.juancrud.linq.tests.helpers.CollectionsHelper;

public class SumTests {

	@Test
	public void testSum_doubleCollection() {
		double expected = 10d * 11d / 2d;
		
		// Arrange
		Collection<Numbers> collection = CollectionsHelper.getNumbersCollection();
		LinqCollection<Numbers> linqCollection = LinqCollection.Get(collection);
		
		DoubleFunction<Numbers> f = new DoubleFunction<Numbers>() {
			public double execute(Numbers value) {
				return value.getDoubleValue();
			}
		};
		
		// Act
		double result = linqCollection.sum(f);
		
		// Assert
		assertEquals(expected, result, 0d);
	}
	
	@Test
	public void testSum_floatCollection() {
		float expected = 10f * 11f / 2f;
		
		// Arrange
		Collection<Numbers> collection = CollectionsHelper.getNumbersCollection();
		LinqCollection<Numbers> linqCollection = LinqCollection.Get(collection);
		
		FloatFunction<Numbers> f = new FloatFunction<Numbers>() {
			public float execute(Numbers value) {
				return value.getFloatValue();
			}
		};
		
		// Act
		float result = linqCollection.sum(f);
		
		// Assert
		assertEquals(expected, result, 0f);
	}
	
	@Test
	public void testSum_longCollection() {
		long expected = 10l * 11l / 2l;
		
		// Arrange
		Collection<Numbers> collection = CollectionsHelper.getNumbersCollection();
		LinqCollection<Numbers> linqCollection = LinqCollection.Get(collection);
		
		LongFunction<Numbers> f = new LongFunction<Numbers>() {
			public long execute(Numbers value) {
				return value.getLongValue();
			}
		};
		
		// Act
		long result = linqCollection.sum(f);
		
		// Assert
		assertEquals(expected, result);
	}
	
	@Test
	public void testSum_intCollection() {
		long expected = 10l * 11l / 2l;
		
		// Arrange
		Collection<Numbers> collection = CollectionsHelper.getNumbersCollection();
		LinqCollection<Numbers> linqCollection = LinqCollection.Get(collection);
		
		IntegerFunction<Numbers> f = new IntegerFunction<Numbers>() {
			public int execute(Numbers value) {
				return value.getIntegerValue();
			}
		};
		
		// Act
		int result = linqCollection.sum(f);
		
		// Assert
		assertEquals(expected, result);
	}
	
}

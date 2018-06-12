package com.juancrud.linq;

import java.util.Collection;
import java.util.Comparator;

import com.juancrud.linq.delegates.Action;
import com.juancrud.linq.delegates.DoubleFunction;
import com.juancrud.linq.delegates.FloatFunction;
import com.juancrud.linq.delegates.Function;
import com.juancrud.linq.delegates.Function2;
import com.juancrud.linq.delegates.IntegerFunction;
import com.juancrud.linq.delegates.LongFunction;
import com.juancrud.linq.delegates.Predicate;
import com.juancrud.linq.exceptions.LinqException;

public interface ILinqCollection<T> extends Collection<T> {
	
	// SQL methods
	<SelectT> LinqCollection<SelectT> select(Function<T, SelectT> function);
	<SelectT> LinqCollection<SelectT> selectMany(Function<T, Collection<SelectT>> function);
	<CollectionT, ResultT> LinqCollection<ResultT> selectMany(Function<T, Collection<CollectionT>> collectionFunction, Function2<T, CollectionT, ResultT> resultFunction);
	LinqCollection<T> distinct();
	LinqCollection<T> where(Predicate<T> predicate);
	
	// Boolean methods
	boolean all(Predicate<T> predicate);
	boolean any();
	boolean any(Predicate<T> predicate);
	boolean areEqual(LinqCollection<T> collection) throws LinqException;
	
	// Skip - Take methods
	LinqCollection<T> skip(int number) throws LinqException;
	LinqCollection<T> skipWhile(Predicate<T> predicate);
	LinqCollection<T> take(int number) throws LinqException;
	LinqCollection<T> takeWhile(Predicate<T> predicate);
	
	// Other methods
	<CastT> LinqCollection<CastT> cast(Class<CastT> clazz);
	void forEach(Action<T> action);
	T aggregate(Function2<T, T, T> function, Class<T> clazz) throws LinqException;
	<AccumulateT> AccumulateT aggregate(AccumulateT seed, Function2<AccumulateT, T, AccumulateT> function) throws LinqException;
	
	// Math methods
	double sum(final DoubleFunction<T> function);
	float sum(final FloatFunction<T> function);
	long sum(final LongFunction<T> function);
	int sum(final IntegerFunction<T> function);
	double average(DoubleFunction<T> function);
	float average(FloatFunction<T> function);
	long average(LongFunction<T> function);
	int average(IntegerFunction<T> function);
	T max(Comparator<T> comparator);
	T min(Comparator<T> comparator);
	
	// Get element methods
	T elementAt(int position);
	T elementAtOrDefault(int position);
	T first() throws LinqException;
	T first(Predicate<T> predicate) throws LinqException;
	T firstOrDefault();
	T firstOrDefault(Predicate<T> predicate);
	T last() throws LinqException;
	T last(Predicate<T> predicate) throws LinqException;
	T lastOrDefault();
	T lastOrDefault(Predicate<T> predicate);
	T single() throws LinqException;
	T single(Predicate<T> predicate) throws LinqException;
	T singleOrDefault();
	T singleOrDefault(Predicate<T> predicate) throws LinqException;
	
	// Set methods
	LinqCollection<T> union(LinqCollection<T> collection);
	LinqCollection<T> intersect(LinqCollection<T> collection);
	LinqCollection<T> except(LinqCollection<T> collection);
	
}

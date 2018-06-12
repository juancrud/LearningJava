package com.juancrud.utility;

import java.util.Collection;
import java.util.Comparator;

import com.juancrud.utility.linq.Action;

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
	<CastT> LinqCollection<CastT> cast(CastT type);
	void forEach(Action<T> action);
	T aggregate(Function2<T, T, T> function) throws LinqException;
	<AccumulateT> AccumulateT aggregate(AccumulateT seed, Function2<AccumulateT, T, AccumulateT> function) throws LinqException;
	
	// Math methods
	double average(Function<T, Double> function);
	double sum(Function<T, Double> function);
	T max(Comparator<T> comparator);
	T min(Comparator<T> comparator);
	
	// Get element methods
	T elementAt(int position);
	T elementAtOrDefault(int position);
	T first();
	T first(Predicate<T> predicate) throws LinqException;
	T firstOrDefault();
	T firstOrDefault(Predicate<T> predicate);
	T single() throws LinqException;
	T single(Predicate<T> predicate) throws LinqException;
	T singleOrDefault();
	T singleOrDefault(Predicate<T> predicate) throws LinqException;
	T last();
	T last(Predicate<T> predicate) throws LinqException;
	T lastOrDefault();
	T lastOrDefault(Predicate<T> predicate);
	
	// Set methods
	LinqCollection<T> union(LinqCollection<T> collection);
	LinqCollection<T> intersect(LinqCollection<T> collection);
	LinqCollection<T> except(LinqCollection<T> collection);
	
}

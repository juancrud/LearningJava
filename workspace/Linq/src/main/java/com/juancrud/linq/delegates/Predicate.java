package com.juancrud.linq.delegates;

public interface Predicate<X> {
	public boolean execute(X value);
}

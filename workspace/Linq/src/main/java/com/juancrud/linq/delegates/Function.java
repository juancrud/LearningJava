package com.juancrud.linq.delegates;

public interface Function<X, Y> {
	public Y execute(X value);
}

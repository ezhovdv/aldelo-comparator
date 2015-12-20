package me.edv.samples.di;

public class ScopeThrowableCache<T, E extends Throwable> {
	private T cache = null;

	public synchronized T get(final ThrowingProvider<T, E> freshProvider) throws E {
		if (cache == null) {
			cache = freshProvider.get();
		}
		return cache;
	}
}

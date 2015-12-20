package me.edv.samples.di;

public interface ThrowingProvider<T, E extends Throwable> {
	T get() throws E;
}

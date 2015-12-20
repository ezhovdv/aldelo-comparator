package me.edv.samples;

import java.io.IOException;

import me.edv.samples.scope.ApplicationScope;

public class ConsoleApp {
	public static void main(final String[] args) throws IOException {
		final ApplicationScope scope = new ApplicationScope(args);
		final MainHelper helper = MainInjector.injectMainHelper(scope);
		helper.run();
	}
}

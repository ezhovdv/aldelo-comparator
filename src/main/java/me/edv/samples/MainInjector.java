package me.edv.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.common.collect.ImmutableList;

import me.edv.samples.command.Command;
import me.edv.samples.command.CommandInjector;
import me.edv.samples.command.CommandManager;
import me.edv.samples.database.DatabaseManager;
import me.edv.samples.scope.ApplicationScope;

public class MainInjector {
	public static ArgumentParser injectArgumentParser(final ApplicationScope scope) {
		return new ArgumentParser(scope.getArgs());
	}

	public static CommandManager injectCommandManager(final ApplicationScope scope) throws IOException {
		return new CommandManager(injectCommands(scope));
	}

	public static List<Command> injectCommands(final ApplicationScope scope) throws IOException {
		return ImmutableList.<Command> of(
				CommandInjector.injectExportCommand(injectDatabaseManager(scope), injectPrintWriter(scope)),
				CommandInjector.injectTableCommand(injectDatabaseManager(scope), injectPrintWriter(scope)));
	}

	public static DatabaseManager injectDatabaseManager(final ApplicationScope scope) throws IOException {
		return new DatabaseManager(injectArgumentParser(scope).getDatabaseFilename());
	}

	public static MainHelper injectMainHelper(final ApplicationScope scope) throws IOException {
		return new MainHelper(injectDatabaseManager(scope));
	}

	private static PrintWriter injectPrintWriter(final ApplicationScope scope) {
		// TODO Auto-generated method stub
		return null;
	}
}

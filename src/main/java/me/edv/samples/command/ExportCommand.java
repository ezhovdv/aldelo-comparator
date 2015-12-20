package me.edv.samples.command;

import java.io.File;
import java.io.PrintWriter;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;
import lombok.RequiredArgsConstructor;
import me.edv.samples.database.DatabaseManager;

@RequiredArgsConstructor
public class ExportCommand implements Command {
	private static final String COMMAND = "export";
	private final DatabaseManager dbManager;
	private final File folder;

	@Override
	public String command() {
		return COMMAND;
	}

	@Override
	public Completer completer() {
		return new ArgumentCompleter(new StringsCompleter(COMMAND), NULL_COMPLETER);
	}

	@Override
	public boolean process(final String command, final PrintWriter out) {
		out.println("Executing command: " + COMMAND);
		final boolean res = dbManager.export(folder);
		if (res) {
			out.println("DB export OK");
		} else {
			out.println("DB export failure");
		}
		return res;
	}

}

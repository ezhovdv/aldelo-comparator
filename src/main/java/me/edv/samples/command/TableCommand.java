package me.edv.samples.command;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.edv.samples.database.DatabaseManager;

@RequiredArgsConstructor
@Slf4j
public class TableCommand implements Command {
	private static final String COMMAND = "table";
	private static final String ROWS = "rows";
	private static final String COLUMNS = "columns";
	private static final char[] SEPARATOR = null;
	private final DatabaseManager dbManager;

	@Override
	public String command() {
		return COMMAND;
	}

	@Override
	public Completer completer() {
		return new ArgumentCompleter(new StringsCompleter(COMMAND), new StringsCompleter(COLUMNS, ROWS),
				NULL_COMPLETER);
	}

	@Override
	public boolean process(final String command, final PrintWriter out) {
		final Iterator<String> iterator = Splitter.on(CharMatcher.BREAKING_WHITESPACE).split(command).iterator();
		if (iterator.hasNext()) {
			final String verb = iterator.next();
			if (COMMAND.equalsIgnoreCase(verb)) {
				if (iterator.hasNext()) {
					final String action = iterator.next();
					switch (action.toLowerCase()) {
					case ROWS: {
						while (iterator.hasNext()) {
							final String tableName = iterator.next();
							final List<String> rows = dbManager.dumpTableRows(tableName);
							printHeader(out, tableName);
							printCollection(out, rows);
							printFooter(out);
						}
						break;
					}
					case COLUMNS: {
						while (iterator.hasNext()) {
							final String tableName = iterator.next();
							final List<String> columns = dbManager.dumpTableColumns(tableName);
							printHeader(out, tableName);
							printCollection(out, columns);
							printFooter(out);
						}
						break;
					}
					default: {
						log.error("Unsupported action: [{}]", action);
					}
					}
				}
			}
		}
		return false;
	}

	private void printCollection(final PrintWriter out, final List<String> rows) {
		for (final String row : rows) {
			out.println(row);
		}
	}

	private void printFooter(final PrintWriter out) {
		out.println(SEPARATOR);
	}

	private void printHeader(final PrintWriter out, final String table) {
		out.println(SEPARATOR);
		out.println(table);
		out.println(SEPARATOR);
	}

}

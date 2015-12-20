package me.edv.samples.command;

import java.io.PrintWriter;

import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;

public interface Command {
	NullCompleter NULL_COMPLETER = new NullCompleter();

	String command();

	Completer completer();

	boolean process(String command, PrintWriter out);
}

package me.edv.samples;

public class ArgumentParser {
	private final String[] args;

	public ArgumentParser(final String[] args) {
		this.args = args;
	}

	public String getDatabaseFilename() {
		return args[0];
	}

	public String getExportFolder() {
		return args[1];
	}
}

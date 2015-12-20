package me.edv.samples;

import java.io.IOException;
import java.util.Set;

import lombok.Value;
import me.edv.samples.database.DatabaseManager;

@Value
public class MainHelper {
	private final DatabaseManager dbMgr;

	public void run() throws IOException {
		final Set<String> tableNames = dbMgr.getTableNames();
		for (final String tableName : tableNames) {
			System.out.println(tableName);
		}
	}
}

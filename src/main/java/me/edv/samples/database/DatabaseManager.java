package me.edv.samples.database;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.util.ExportUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseManager {
	private final Database db;

	public DatabaseManager(final String databaseFilename) throws IOException {
		db = DatabaseBuilder.open(new File(databaseFilename));
	}

	public List<String> dumpTableColumns(final String tableName) {
		try {
			final Table table = db.getTable(tableName);
			if (null != table) {
				return ImmutableList.copyOf(Iterables.transform(table.getColumns(), Functions.toStringFunction()));
			} else {
				log.error("Can't find table: [{}]", tableName);
			}
		} catch (final IOException e) {
			log.error("Can't get columns info on table [{}]", tableName);
		}
		return ImmutableList.<String> of();
	}

	public List<String> dumpTableRows(final String tableName) {
		try {
			final Table table = db.getTable(tableName);
			if (null != table) {
				return ImmutableList.copyOf(Iterables.transform(table, Functions.toStringFunction()));
			} else {
				log.error("Can't find table: [{}]", tableName);
			}
		} catch (final IOException e) {
			log.error("Can't get rows info on table [{}]", tableName);
		}
		return ImmutableList.<String> of();
	}

	public boolean export(final File folder) {
		try {
			ExportUtil.exportAll(db, folder);
			log.debug("DB export to [{}] succeeded", folder.getAbsolutePath());
			return true;
		} catch (final IOException e) {
			log.error("DB export to [{}] failed");
			return false;
		}
	}

	public Set<String> getTableNames() throws IOException {
		return ImmutableSet.<String> copyOf(db.getTableNames());
	}
}

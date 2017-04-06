package org.btkj.master.persistence;

import org.btkj.master.persistence.domain.Administrator;
import org.rapid.data.storage.db.Table;

public interface Tables {

	final Table<Integer, Administrator> ADMINISTRATOR			= new Table<Integer, Administrator>("administrator", Administrator.class);
}


CREATE TABLE place(
	uuid VARCHAR(255) PRIMARY KEY,
	name VARCHAR NOT NULL
);


CREATE TABLE date_record(
	id INTEGER PRIMARY KEY,
	date DATE NOT NULL
);

CREATE TABLE date_place_record(
	date_record_id INTEGER REFERENCES date_record (id) ON DELETE CASCADE,
	place_uuid VARCHAR(255) REFERENCES place(uuid) ON DELETE CASCADE,
	avgTemperature NUMERIC(4,2) NOT NULL,
	PRIMARY KEY (date_record_id, place_uuid)
);

CREATE TABLE sensor(
	uuid VARCHAR(255) PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	place_uuid VARCHAR(255) REFERENCES place(uuid) ON DELETE CASCADE
);

CREATE TABLE record(
	time_registered TIMESTAMP PRIMARY KEY,
	temperature NUMERIC(4,2) NOT NULL
);



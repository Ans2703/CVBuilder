CREATE TABLE "users" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"email"	TEXT NOT NULL UNIQUE,
	"phone"	TEXT,
	"address"	TEXT,
	"resource_type"	TEXT,
	"resource_id"	INTEGER NOT NULL
);

CREATE TABLE "candidates" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"first_name"	TEXT,
	"last_name"	TEXT,
	"user_id"	INTEGER NOT NULL
);
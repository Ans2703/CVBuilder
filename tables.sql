CREATE TABLE "users" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"email"	TEXT NOT NULL UNIQUE,
	"password" TEXT NOT NULL,
	"phone"	TEXT,
	"address"	TEXT,
	"resource_type"	TEXT,
	"resource_id"	INTEGER NOT NULL
);

CREATE TABLE "candidates" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"first_name"	TEXT,
	"last_name"	TEXT,
	"user_id"	INTEGER
);

CREATE TABLE "companies" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT NOT NULL,
	"description"	TEXT,
	"user_id"	INTEGER
);

CREATE TABLE "cvs" (
	"id"	INTEGER NOT NULL,
	"user_id"	INTEGER NOT NULL,
	"title"	TEXT NOT NULL,
	"skills" TEXT,
	"experience"	TEXT,
	"education"	TEXT,
	"about"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "jobs" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"user_id"	INTEGER NOT NULL,
	"title" TEXT NOT NULL,
	"description" TEXT,
	"salary" NUMERIC,
	"salary_currency" TEXT,
	"country" TEXT,
	"location" TEXT
);

CREATE TABLE "jobs_cvs" (
	"job_id" INTEGER NOT NULL,
	"cv_id" INTEGER NOT NULL,
	"applied_at" TEXT NOT NULL
);
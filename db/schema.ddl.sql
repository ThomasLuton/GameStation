DROP TABLE IF EXISTS to_play;
DROP TABLE IF EXISTS results;
DROP TABLE IF EXISTS instances;
DROP TABLE IF EXISTS instance_steps;
DROP TABLE IF EXISTS to_earn;
DROP TABLE IF EXISTS successes;
DROP TABLE IF EXISTS to_prefer;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS to_inform;
DROP TABLE IF EXISTS notifications;
DROP TABLE IF EXISTS notification_templates;
DROP TABLE IF EXISTS to_be_friend;
DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS roles;

-- players related

CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE players (
	id SERIAL PRIMARY KEY,
	email VARCHAR(255) NOT NULL UNIQUE,
	suffix INTEGER NOT NULL UNIQUE,
	player_name VARCHAR(20) NOT NULL,
	password CHAR(60) NOT NULL,
	role_id INTEGER REFERENCES roles(id) NOT NULL,
    avatar VARCHAR(1000),
	email_notification_enable BOOLEAN NOT NULL,
	day_before_notification INTEGER,
	is_connected BOOLEAN NOT NULL
);

CREATE TABLE to_be_friend (
	id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES players(id) NOT NULL,
	friend_id INTEGER REFERENCES players(id) NOT NULL,
	UNIQUE(player_id, friend_id)
);

-- notifications related


CREATE TABLE notification_templates (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE notifications(
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	content VARCHAR(1000) NOT NULL,
	send_at TIMESTAMP NOT NULL,
	is_read BOOLEAN NOT NULL,
	redirect_link VARCHAR(255),
	template_id INTEGER REFERENCES notification_templates(id)
);

CREATE TABLE to_inform(
	id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES players(id) NOT NULL,
	notification_id INTEGER REFERENCES notifications(id) NOT NULL
);

-- game related


CREATE TABLE games (
	id SERIAL PRIMARY KEY,
	game_name VARCHAR(255) NOT NULL UNIQUE,
	game_detail VARCHAR(1000) NOT NULL UNIQUE,
	min_player INTEGER NOT NULL,
	max_player INTEGER NOT NULL,
	thumbnail VARCHAR(1000) NOT NULL UNIQUE,
	ai_available BOOLEAN NOT NULL
);

CREATE TABLE to_prefer (
	id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES players(id) NOT NULL,
	game_id INTEGER REFERENCES games(id) NOT NULL,
	UNIQUE (player_id, game_id)
);

CREATE TABLE successes (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE,
	point INTEGER NOT NULL,
	description VARCHAR(1000) NOT NULL,
	game_id INTEGER REFERENCES games(id) NOT NULL
);

CREATE TABLE to_earn (
	id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES players(id) NOT NULL,
	success_id INTEGER REFERENCES successes(id) NOT NULL
);

-- instances related


CREATE TABLE instance_steps (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE results (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE,
	ranking INTEGER
);

CREATE TABLE instances (
	id SERIAL PRIMARY KEY, 
	play_at TIMESTAMP,
	only_friend BOOLEAN NOT NULL,
	step_id INTEGER REFERENCES instance_steps(id) NOT NULL,
	game_id INTEGER REFERENCES games(id) NOT NULL
);

CREATE TABLE to_play (
	id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES players(id) NOT NULL,
	instance_id INTEGER REFERENCES instances(id) NOT NULL,
	result_id INTEGER REFERENCES results(id)
);
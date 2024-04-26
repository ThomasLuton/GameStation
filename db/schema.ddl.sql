DROP TABLE IF EXISTS to_play;
DROP TABLE IF EXISTS results;
DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS to_prefer;
DROP TABLE IF EXISTS games;
DROP TABLE IF EXISTS notifications;
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
	day_before_notification INTEGER DEFAULT 7,
	is_connected BOOLEAN NOT NULL
);

CREATE TABLE to_be_friend (
	id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES players(id) NOT NULL,
	friend_id INTEGER REFERENCES players(id) NOT NULL,
	pending BOOLEAN NOT NULL DEFAULT false,
	UNIQUE(player_id, friend_id)
);

-- notifications related

CREATE TABLE notifications(
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	content VARCHAR(1000) NOT NULL,
	send_at TIMESTAMP NOT NULL,
	is_read BOOLEAN NOT NULL,
	redirect_link VARCHAR(255),
	player_id INTEGER REFERENCES players(id) NOT NULL
);

-- game related

CREATE TABLE games (
	id SERIAL PRIMARY KEY,
	game_name VARCHAR(255) NOT NULL UNIQUE,
	game_detail VARCHAR(1000) NOT NULL UNIQUE,
	min_player INTEGER NOT NULL,
	max_player INTEGER NOT NULL,
	thumbnail VARCHAR(1000) NOT NULL,
	ai_available BOOLEAN NOT NULL
);

CREATE TABLE to_prefer (
	id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES players(id) NOT NULL,
	game_id INTEGER REFERENCES games(id) NOT NULL,
	UNIQUE (player_id, game_id)
);

-- instances related

CREATE TABLE results (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL UNIQUE,
	ranking INTEGER
);

CREATE TABLE sessions (
	id SERIAL PRIMARY KEY, 
	session_code CHAR(7) NOT NULL UNIQUE,
	play_at TIMESTAMP,
	only_friend BOOLEAN NOT NULL,
	step INTEGER NOT NULL,
	game_id INTEGER REFERENCES games(id) NOT NULL,
	player_id INTEGER REFERENCES players(id) NOT NULL
);

CREATE TABLE to_play (
	id SERIAL PRIMARY KEY,
	player_id INTEGER REFERENCES players(id) NOT NULL,
	session_id INTEGER REFERENCES sessions(id) NOT NULL,
	result_id INTEGER REFERENCES results(id)
);
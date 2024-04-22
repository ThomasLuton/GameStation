DELETE FROM games;
DELETE FROM to_be_friend;
DELETE FROM players;
DELETE FROM roles;


INSERT INTO roles (name) values ('player'), ('admin'), ('moderator');
-- hash = 123
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('player1', 9999, 'Player Alice', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'player'), NULL, false);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('player2', 9998, 'Player Bob', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'player'), NULL, false);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('email1', 0001, 'BOT Maryline', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'player'), NULL, true);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('email2', 0002, 'BOT Guy', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'player'), NULL, true);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('email3', 0003, 'BOT Fergusson', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'player'), NULL, true);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('email4', 0004, 'BOT Yseult', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'player'), NULL, true);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('email5', 0005, 'BOT Jacynthe', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'player'), NULL, true);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('email6', 0006, 'BOT Michel', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'player'), NULL, true);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('emailModo', 1235, 'BOT Moderator', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'moderator'), NULL, true);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('emailAdmin', 1236, 'BOT Admin', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'admin'), NULL, true);

INSERT INTO to_be_friend (player_id, friend_id, pending) values ((SELECT id FROM players WHERE suffix = 9999), (SELECT id FROM players WHERE suffix = 0001), false);
INSERT INTO to_be_friend (player_id, friend_id, pending) values ((SELECT id FROM players WHERE suffix = 9999), (SELECT id FROM players WHERE suffix = 0002), false);
INSERT INTO to_be_friend (player_id, friend_id, pending) values ((SELECT id FROM players WHERE suffix = 9999), (SELECT id FROM players WHERE suffix = 0003), false);
INSERT INTO to_be_friend (player_id, friend_id, pending) values ((SELECT id FROM players WHERE suffix = 9999), (SELECT id FROM players WHERE suffix = 0004), false);

INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('TicTacToe', 'Align 3 cross or 3 circle to win in a 9*9 square', '2', '2', '/images/morpion_total.png', 'false');
INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Fake game 1', 'Fake game 1 to be implement, victoire random, nombre de joueurs variables selon mes tests, jeu non valable', '1', '3', '/images/dame_total.png', 'false');
INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Fake game 2', 'Fake game 2 to be implement, victoire random, nombre de joueurs variables selon mes tests, jeu non valable', '1', '3', '/images/dame_total.png', 'false');
INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Fake game 3', 'Fake game 3 to be implement, victoire random, nombre de joueurs variables selon mes tests, jeu non valable', '1', '3', '/images/dame_total.png', 'false');
INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Fake game 4', 'Fake game 4 to be implement, victoire random, nombre de joueurs variables selon mes tests, jeu non valable', '1', '3', '/images/dame_total.png', 'false');
INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Fake game 5', 'Fake game 5 to be implement, victoire random, nombre de joueurs variables selon mes tests, jeu non valable', '1', '3', '/images/dame_total.png', 'false');
INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Fake game 6', 'Fake game 6 to be implement, victoire random, nombre de joueurs variables selon mes tests, jeu non valable', '1', '3', '/images/dame_total.png', 'false');

-- INSERT INTO favorites (player_id, game_id) values ('1', '2');
-- INSERT INTO favorites (player_id, game_id) values ('1', '1');
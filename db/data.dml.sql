DELETE FROM games;
DELETE FROM players;
DELETE FROM roles;


INSERT INTO roles (name) values ('player'), ('admin'), ('moderator');
-- hash = 123
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('bot', 1234, 'Bot', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'player'), 'testAvatar', true);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('modo', 1235, 'Modo', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'moderator'), 'testAvatar', true);
INSERT INTO players (email, suffix, player_name, password, role_id, avatar, is_connected) values ('admin', 1236, 'Admin', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', (SELECT id FROM roles WHERE name = 'admin'), 'testAvatar', true);

INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('TicTacToe', 'Jeu classique du morpion', '2', '2', '/images/morpion_total.png', 'false');

INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Jeu test', 'Jeu sans contenu, victoire random, pour n joueurs', '2', '5', '/images/pow.png', 'false');

INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Jeu de remplissage', 'Jeu sans contenu, victoire random, nombre de joueurs variables selon mes tests, jeu non valable', '1', '3', '/images/dame_total.png', 'false');

-- INSERT INTO favorites (player_id, game_id) values ('1', '2');
-- INSERT INTO favorites (player_id, game_id) values ('1', '1');
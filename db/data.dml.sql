DELETE FROM games;
DELETE FROM users;
DELETE FROM roles;


INSERT INTO roles (name) values ('player'), ('admin');
-- hash = 123
INSERT INTO users (email, nickname, password, role_id) values ('123', '123', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', '1');
INSERT INTO users (email, nickname, password, role_id) values ('456', '456', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', '1');
INSERT INTO users (email, nickname, password, role_id) values ('admin', 'admin', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', '2');

INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, available, victory_point, lose_point, draw_point)
values ('Jeu test', 'Jeu sans contenu, victoire random, nombre de joueurs variables selon mes tests', '1', '3', '/images/zap.png', 'true', '3', '0', '1');

INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, available, victory_point, lose_point, draw_point)
values ('Jeu non dispo', 'Jeu sans contenu, victoire random, nombre de joueurs variables selon mes tests, jeu non valable', '1', '3', '/images/pow.png', 'false', '3', '0', '1');
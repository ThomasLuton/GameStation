DELETE FROM games;
DELETE FROM players;
DELETE FROM roles;


INSERT INTO roles (name) values ('player'), ('admin'), ('moderator');
-- hash = 123
-- INSERT INTO players (email, nickname, password, role_id, coins, connection) values ('123', '123', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', '1', 0, true);
-- INSERT INTO players (email, nickname, password, role_id, coins, connection) values ('456', '456', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', '1', 0, true);
-- INSERT INTO players (email, nickname, password, role_id, coins, connection) values ('admin', 'admin', '$2a$11$h6b5Wg9TCb.05/VZTs3BieP1Ya7VFmSqh.SIvg1zurUS0yQFaJr/W', '2', 0, false);

-- INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, available, victory_point, lose_point, draw_point)
-- values ('Jeu solo', 'Jeu sans contenu, victoire random, pour un joueur', '1', '1', '/images/morpion_total.png', 'true', '3', '0', '1');

-- INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, available, victory_point, lose_point, draw_point)
-- values ('Jeu multi', 'Jeu sans contenu, victoire random, pour n joueurs', '2', '5', '/images/pow.png', 'true', '3', '0', '1');

-- INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, available, victory_point, lose_point, draw_point)
-- values ('Jeu non dispo', 'Jeu sans contenu, victoire random, nombre de joueurs variables selon mes tests, jeu non valable', '1', '3', '/images/dame_total.png', 'false', '3', '0', '1');

-- INSERT INTO favorites (player_id, game_id) values ('1', '2');
-- INSERT INTO favorites (player_id, game_id) values ('1', '1');
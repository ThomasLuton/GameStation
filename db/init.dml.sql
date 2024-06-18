INSERT INTO roles (name) values ('player'), ('admin'), ('moderator');

INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('TicTacToe', 'The famous game : Align 3 cross or 3 circle to win in a 9*9 square', '2', '2', '/images/morpion_total.png', false);
INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Draughts', 'Be smarter than your opponent and use your pieces to capture his pieces and try to make a queen !', '2', '2', '/images/dame_total.png', false );
INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Horses race', 'Take your horses from the stable to the sky !', '2', '4', '/images/chevaux_total.png', false );
INSERT INTO games (game_name, game_detail, min_player, max_player, thumbnail, ai_available)
values ('Chess', 'Use your pieces to capture the enemy king !', '2', '2', '/images/ECHEC_TOTAL.png', false );
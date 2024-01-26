insert into game(id, name) 
  values (1, 'fortnite'),
        (2, 'call of duty'),
        (3, 'dota'),
        (4, 'valhalla'),
        (5, 'amongus');        
        
insert into gamer (id, name, nickname, gender, geography, version)
  values (1, 'shimul kumar saha', 'shimul', 'MALE', 'EUROPE', 0),
      (2, 'palash kumar saha', 'palash', 'MALE', 'ASIA', 0),
      (3, 'sohel rana', 'sohel', 'MALE', 'ASIA', 0),
      (4, 'titli roy', 'titli', 'FEMALE', 'EUROPE', 0);   
      
insert into gamer_interests (gamer_id, game_id, interest_level)
  values (1, 1, 'NOOB'), (1, 2, 'PRO'), (2, 2, 'NOOB'), (2, 3, 'PRO'),
      (2, 4, 'INVINCIBLE'), (3, 1, 'NOOB'), (3, 4, 'PRO'),
      (4, 4, 'PRO'), (4, 3, 'INVINCIBLE');
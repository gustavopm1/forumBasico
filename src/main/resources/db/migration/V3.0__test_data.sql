INSERT INTO user(birth_date,email,name,user,password) VALUES ('1996-05-22','user@teste.com','Teste','teste123','password123');
INSERT INTO user(birth_date,email,name,user,password) VALUES ('1990-10-02','user2@teste.com','Teste2','teste223','password223');

INSERT INTO post(date,description,user_id) VALUES('2017-09-27 09:23:21','first post', 1);
INSERT INTO post(date,description,user_id) VALUES('2017-09-29 10:32:10','Testing second post', 2);
INSERT INTO post(date,description,user_id) VALUES('2017-09-30 10:55:05','Testing third post', 1);

INSERT INTO message(date,text,user_id,post_id) VALUES ('2017-09-30 10:15:11','Hello Test',1,1);
INSERT INTO message(date,text,user_id,post_id, parent_id) VALUES ('2017-09-30 10:32:45','How are you?',1,1,1);
INSERT INTO message(date,text,user_id,post_id) VALUES ('2017-10-01 9:34:56','Hello',2,2);


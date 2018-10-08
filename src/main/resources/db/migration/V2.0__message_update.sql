alter table message add column message_rating INT;

update message set message_rating = 0;

alter table message modify column message_rating INT NOT NULL DEFAULT 0;
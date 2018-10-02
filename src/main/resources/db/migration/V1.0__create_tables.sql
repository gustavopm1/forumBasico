create table user (
       id integer not null auto_increment,
        birth_date date,
        email varchar(255),
        name varchar(255),
        password varchar(255),
        user varchar(255),
        primary key (id)
    );

create table post (
       post_id integer not null auto_increment,
        date datetime,
        description varchar(255),
        user_id integer,
        primary key (post_id),
        foreign key (user_id) references user(id)
    );

create table message (
       id integer not null auto_increment,
        date datetime,
        text varchar(255),
        parent_id integer,
        post_id integer,
        user_id integer,
        primary key (id),
        foreign key(post_id) references post (post_id),
        foreign key(user_id) references user (id),
        foreign key(parent_id) references message (id)
    );
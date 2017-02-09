# Users schema
 
# --- !Ups
 
CREATE TABLE User (
    id bigint(20) NOT NULL AUTO_INCREMENT,
	userName varchar(20) NOT NULL,
    email varchar(255) NOT NULL,
    passwordHash binary(60) NOT NULL,
    PRIMARY KEY (id)
);
 
# --- !Downs
 
DROP TABLE User;
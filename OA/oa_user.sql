drop table if exists oa_user;

CREATE TABLE oa_user(
    id int primary key auto_increment,
    username VARCHAR(255),
    password VARCHAR(255)
);

INSERT INTO oa_user(username,password) VALUES('Admin','123456');
INSERT INTO oa_user(username,password) VALUES('Jack','123456');
commit;
select * from oa_user;

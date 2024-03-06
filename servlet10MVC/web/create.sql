CREATE TABLE bank(
    id INT AUTO_INCREMENT PRIMARY KEY,
    actno VARCHAR(255),
    balance DECIMAL(10,2)
);

INSERT INTO bank (actno,balance) values ('act1',50000);
INSERT INTO bank (actno,balance) values ('act2',0);

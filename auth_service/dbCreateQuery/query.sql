drop table if exists token;
drop table if exists token_seq;
drop table if exists user;


CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(64) NOT NULL,
    pass VARCHAR(255) NOT NULL,
    role VARCHAR(32) NOT NULL  
);



INSERT INTO user (name, pass, role)
VALUES
    ('Admin', '$2a$10$tszsi8tCOnIgUhtA17ZEMO6xk1YoQ/zYXeRgit26TazZ9z30pz2Zi', 'Admin');






CREATE TABLE app_user (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT IGNORE INTO app_user (id, password, role, username) VALUES (4, '$2y$10$URsJ9zb8tyVbzNbuEAX6A.BUfK5j/TGFeTzOAGoHC21uBeX201QJu', 'USER', 'Kayttaja');

CREATE TABLE category (
    categoryid BIGINT(20) NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (categoryid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT IGNORE INTO category (categoryid, name) VALUES (5, 'Sci-Fi');

CREATE TABLE movie (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    director VARCHAR(255),
    movie_name VARCHAR(255),
    rating DOUBLE NOT NULL,
    release_year INT(11) NOT NULL,
    categoryid BIGINT(20),
    PRIMARY KEY (id),
    FOREIGN KEY (categoryid) REFERENCES category(categoryid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT IGNORE INTO movie (id, director, movie_name, rating, release_year, categoryid) VALUES (9, 'Ohjaaja 1', 'Elokuva 1', 4.5, 2022, 1);
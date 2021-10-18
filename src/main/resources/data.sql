DROP TABLE IF EXISTS weather;

CREATE MEMORY TABLE weather
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    city_name    varchar(100) not null,
    country_code varchar(16)  not null,
    temperature  DOUBLE       not null,
    clouds       LONG         not null,
    timestamp    TIMESTAMP    not null,
    added        TIMESTAMP    not null
);

CREATE MEMORY TABLE itinerary
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) not null,
    city VARCHAR(100) not null
)
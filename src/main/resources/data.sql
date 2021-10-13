DROP TABLE IF EXISTS weather;

CREATE TABLE weather
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    city_name    varchar(100) not null,
    country_code varchar(16)  not null,
    temperature  DOUBLE       not null,
    clouds       LONG         not null
);
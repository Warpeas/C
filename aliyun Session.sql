CREATE TABLE station01(
    station_id  int primary key,
    name VARCHAR not null,
    city_code VARCHAR
);
CREATE TABLE city(
    city_code VARCHAR primary key,
    city_name VARCHAR not null,
    province_id int not NULL,
    province_name varchar
);
DROP TABLE station01;

BEGIN TRANSACTION;
-----------------------------------
DROP TABLE IF EXISTS `STOPS`;
CREATE TABLE IF NOT EXISTS `STOPS`
(
    `id_line`    INTEGER NOT NULL,
    `id_station` INTEGER NOT NULL,
    `id_order`   INTEGER NOT NULL,
    FOREIGN KEY (`id_line`) REFERENCES `LINES` (`id`),
    FOREIGN KEY (`id_station`) REFERENCES `STATIONS` (`id`),
    PRIMARY KEY (`id_line`, `id_station`)
);
-----------------------------------
DROP TABLE IF EXISTS `STATIONS`;
CREATE TABLE IF NOT EXISTS `STATIONS`
(
    `id`   INTEGER,
    `name` TEXT NOT NULL,
    PRIMARY KEY (`id`)
);
-----------------------------------
DROP TABLE IF EXISTS `LINES`;
CREATE TABLE IF NOT EXISTS `LINES`
(
    `id` INTEGER,
    PRIMARY KEY (`id`)
);
-----------------------------------
DROP TABLE IF EXISTS FAVORITES;
create table FAVORITES
(
    start_station int  not null
        references STATIONS,
    end_station   int  not null
        references STATIONS,
    name          text not null,
    constraint FAVORITES_pk
        primary key (start_station, end_station),
    constraint UC_PK
        unique (start_station, end_station)
);
-----------------------------------
COMMIT;
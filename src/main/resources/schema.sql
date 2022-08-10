DROP TABLE batch_process IF EXISTS;

CREATE TABLE batch_process  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    batch_item_num VARCHAR(10),
    name VARCHAR(20),
    prgss_status VARCHAR(30)
);

DROP TABLE item_process IF EXISTS;

CREATE TABLE item_process  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    batch_num VARCHAR(10),
    name VARCHAR(20),
    desc VARCHAR(50)
);
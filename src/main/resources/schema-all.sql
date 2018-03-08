CREATE DATABASE IF NOT EXISTS log_analyser;

DROP TABLE IF EXISTS tb_log_analyser;
DROP TABLE IF EXISTS tb_ips_bloqued;


CREATE TABLE tb_log_analyser (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data_access DATETIME,
    ip VARCHAR(15),
    request VARCHAR(50),
    status INTEGER(3),
    user_agent TEXT
);

CREATE TABLE tb_ips_bloqued (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ip VARCHAR(15),
    description TEXT
);
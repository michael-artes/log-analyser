DROP TABLE IF EXISTS log_analyser;

CREATE TABLE log_analyser  (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data_access DATETIME,
    ip VARCHAR(15),
    request VARCHAR(50),
    status INTEGER(3),
    user_agent TEXT
);

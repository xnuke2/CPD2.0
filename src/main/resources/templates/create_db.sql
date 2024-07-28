CREATE TABLE IF NOT EXISTS files
(
    key    VARCHAR(200) PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    size BIGINT NOT NULL ,
    content_type VARCHAR(200) NOT NULL ,
    date_of_upload VARCHAR(200)  NOT NULL
);
CREATE TABLE IF NOT EXISTS document_summary (
    id varchar(255) NOT NULL,
    number integer,
    author varchar(255),
    title varchar(255),
    create_date timestamp without time zone,
    update_date timestamp without time zone,
    status varchar(255),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS document_summary (
    id varchar(255) NOT NULL,
    number BIGINT,
    author varchar(255),
    title varchar(255),
    create_date timestamp without time zone,
    update_date timestamp without time zone,
    status varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS history_summary (
    id varchar(255) NOT NULL,
    id_document varchar(255) NOT NULL,
    author varchar(255),
    action varchar(255),
    comment varchar(255),
    date timestamp without time zone,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS statement_register_summary (
    id varchar(255) NOT NULL,
    id_document varchar(255) NOT NULL,
    author varchar(255),
    date timestamp without time zone,
    PRIMARY KEY (id)
);

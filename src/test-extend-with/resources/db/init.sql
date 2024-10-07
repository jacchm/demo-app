CREATE SEQUENCE article_id_seq START WITH 100 INCREMENT BY 50;

CREATE TABLE article_entity
(
    id            BIGINT                   NOT NULL DEFAULT nextval('article_id_seq') PRIMARY KEY,
    title         VARCHAR(255)             NOT NULL,
    author        VARCHAR(255)             NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    abstract_text TEXT,
    filename      VARCHAR(255)
);

-- finds_article_by_id
INSERT INTO article_entity (id, title, author, creation_date, abstract_text, filename)
VALUES (1, 'Test title', 'Test author', '2024-10-03T10:00:00+00:00', 'Test abstract text', NULL);

-- finds_all_articles
INSERT INTO article_entity (id, title, author, creation_date, abstract_text, filename)
VALUES (2, 'Test title 1', 'Test author 1', '2024-10-03T11:00:00+00:00', 'Test abstract text 1', NULL),
       (3, 'Test title 2', 'Test author 2', '2024-10-03T12:00:00+00:00', 'Test abstract text 2', NULL);

-- deletes_article_by_id
INSERT INTO article_entity (id, title, author, creation_date, abstract_text, filename)
VALUES (4, 'Test title to delete', 'Test author to delete', '2024-10-03T13:00:00+00:00', 'Test abstract text to delete',
        NULL),
       (5, 'Test title to keep', 'Test author to keep', '2024-10-03T14:00:00+00:00', 'Test abstract text to keep',
        NULL);
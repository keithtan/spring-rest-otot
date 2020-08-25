
drop table if exists quote;

CREATE TABLE quote (
    id serial,
    content text NOT NULL,
    author varchar(50) not null,
    PRIMARY KEY (id)
);


INSERT INTO quote (content, author)
VALUES
('The way I see it, if you want the rainbow, you gotta put up with the rain', 'Dolly Parton'),
('If you''re trying to be normal, you will never know how amazing you can be.', 'Maya Angelou');


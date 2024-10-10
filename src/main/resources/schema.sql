DROP TABLE IF EXISTS books CASCADE;

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    update_date TIMESTAMP NOT NULL
);

CREATE OR REPLACE FUNCTION set_update_date_on_insert()
RETURNS TRIGGER AS $$
BEGIN
    NEW.update_date := NOW();
    RETURN NEW;
END;;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_set_update_date
BEFORE INSERT ON books
FOR EACH ROW
EXECUTE FUNCTION set_update_date_on_insert();

INSERT INTO books (title) VALUES ('t1');
INSERT INTO books (title) VALUES ('t2');

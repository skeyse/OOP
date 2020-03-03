CREATE TABLE address (
    id TEXT NOT NULL PRIMARY KEY,
    user_id TEXT NOT NULL,
    street TEXT NOT NULL,
    zip_code TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES person(id)
);

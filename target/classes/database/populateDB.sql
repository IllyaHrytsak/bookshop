INSERT INTO role (ROLE_NAME) VALUES ('ADMIN'), ('USER'), ('BLOCKED');
INSERT INTO account (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ROLE_ID)
VALUES ('123',
        '$2a$04$8X/kPX4FCQ.LGG4.6O2IfuJ6FHIbh7Sad1JxOuckT5GkmLP8hK9a2',
        'Illya',
        'Hrytsak',
        '+380935215824',
        1);
INSERT INTO book (BOOK_TITLE, BOOK_AUTHOR, BOOK_PRICE) VALUES
        ('Java', 'Illya Hrytsak', 100),
        ('C++', 'Dima Andreev', 200);
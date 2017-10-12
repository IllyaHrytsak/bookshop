INSERT INTO role (ROLE_NAME) VALUES ('ADMIN'), ('USER'), ('BLOCKED');
INSERT INTO account (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ROLE_ID)
VALUES ('admin',
        '$2a$04$VFj2kmDuiH3vA8wEmwImS.elOaZDEZQ0Xjx./HTR0YN9Wgf27iFjm',
        'Illya',
        'Hrytsak',
        '+380935215824',
        1),
        ('user',
        '$2a$04$vZoZ/unmpGFoJnVEtfLX1efU16IeanmJ9vzf0YJtXvZAhhzrmDnue',
        'Igor',
        'Petrov',
        '+380673890102',
        2);
INSERT INTO book (BOOK_TITLE, BOOK_AUTHOR, BOOK_PRICE) VALUES
        ('Java Core', 'Vlad Borisov', 100),
        ('C++ Core', 'Dima Andreev', 200),
        ('MySql Core', 'Vadim Baranin', 265),
        ('Spring Core', 'Ivan Pupkin', 399);
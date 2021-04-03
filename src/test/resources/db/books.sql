insert into book (id, title, year)
values (1, 'Analiza 1', 2010),
       (2, 'Analiza 2', 2010),
       (3, 'Analiza 3'),
       (4, 'Wstęp do matematyki współczesnej'),
       (5, 'Wstęp do krainy nierówności'),
       (6, 'Powrót do krainy nierówności'),
       (7, 'Słynne nierówności'),
       (8, 'Designing data intensive applications'),
       (9, 'Programming in Scala'),
       (10, 'Scala in depth');
insert into author (id, first_name, last_name)
values (1, 'Krzysztof', 'Maurin'),
       (2, 'Helena', 'Rasiowa'),
       (3, 'Lev', 'Kourliandtchik'),
       (4, 'Martin', 'Kleppman'),
       (5, 'Martin', 'Odersky'),
       (6, 'Lex', 'Spoon'),
       (7, 'Bill', 'Venners'),
       (8, 'Joshua', 'Suereth');
insert into author_book (book_id, author_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 3),
       (6, 3),
       (7, 3),
       (8, 4),
       (9, 5),
       (9, 6),
       (9, 7),
       (10, 8);

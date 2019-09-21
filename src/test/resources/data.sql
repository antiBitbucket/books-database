delete from USER_TBL where name <> 'admin';

delete from BOOK_TBL;

insert into USER_TBL values
    (2, 'user1', '7b1a5193fc707a04eb3f889a58e082976bd1d63b5722388dba0f32f001d79565df1e4dd06e8f8589', 'USER'),
	(3, 'user2', '77a51e6406e0250026c7e78f31b2860a7e8f41aeadd42633113e6b74c0f8982c1e9d9ef8ad5291fb', 'USER'),
	(4, 'user3', '7b4e21ec63ccf7ecdbcf06cb221ea61fd04537ce4acd2ab8d1c64b442292469d0705938fc2cd0387', 'USER'),
	(5, 'user4', '33d2cbe9235d4a82d0c994c7c1439587002e8d782c31d59929e5172b523a33c529df52a0b1ddddff', 'USER');

insert into BOOK_TBL values
    (1, 'A', 1),
    (2, 'B', 1),
    (3, 'C', 1),
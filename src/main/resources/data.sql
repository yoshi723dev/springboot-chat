INSERT INTO M_USER (USER_ID, USER_NM, PASSWORD) VALUES(1, 'テスト太郎1', 'test');
INSERT INTO M_USER (USER_ID, USER_NM, PASSWORD) VALUES(2, 'テスト太郎2', 'test');
INSERT INTO M_USER (USER_ID, USER_NM, PASSWORD) VALUES(3, 'テスト太郎3', 'test');
INSERT INTO M_USER (USER_ID, USER_NM, PASSWORD) VALUES(4, 'テスト太郎4', 'test');
INSERT INTO M_USER (USER_ID, USER_NM, PASSWORD) VALUES(5, 'テスト太郎5', 'test');

INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES('1_2', '2024-01-01 00:00:00', 1, '明日カラオケ行かない？');
INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES('1_2', '2024-01-01 00:01:00', 2, '明日予定あるから明後日なら大丈夫');
INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES('1_2', '2024-01-01 00:02:00', 1, 'じゃあそれで、14:00に新宿でいい？');
INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES('1_2', '2024-01-01 00:05:00', 2, 'OK');
INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES('1_3', '2024-01-02 00:05:00', 3, '元気？');

INSERT INTO T_FREND (USER_ID, FREND_USER_ID) VALUES(1, 2);
INSERT INTO T_FREND (USER_ID, FREND_USER_ID) VALUES(1, 3);
INSERT INTO T_FREND (USER_ID, FREND_USER_ID) VALUES(1, 4);
INSERT INTO T_FREND (USER_ID, FREND_USER_ID) VALUES(2, 1);
INSERT INTO T_FREND (USER_ID, FREND_USER_ID) VALUES(2, 3);
INSERT INTO T_FREND (USER_ID, FREND_USER_ID) VALUES(3, 1);
INSERT INTO T_FREND (USER_ID, FREND_USER_ID) VALUES(4, 1);

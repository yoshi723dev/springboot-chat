INSERT INTO M_USER (USER_ID, USER_NM, EMAIL, PASSWORD) VALUES(1, 'テスト太郎1', 'test1@test.com', 'test');
INSERT INTO M_USER (USER_ID, USER_NM, EMAIL, PASSWORD) VALUES(2, 'テスト太郎2', 'test2@test.com', 'test');
INSERT INTO M_USER (USER_ID, USER_NM, EMAIL, PASSWORD) VALUES(3, 'テスト太郎3', 'test3@test.com', 'test');
INSERT INTO M_USER (USER_ID, USER_NM, EMAIL, PASSWORD) VALUES(4, 'テスト太郎4', 'test4@test.com', 'test');
INSERT INTO M_USER (USER_ID, USER_NM, EMAIL, PASSWORD) VALUES(5, 'テスト太郎5', 'test5@test.com', 'test');

INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES(1, '2024-01-01 00:00:00', 1, '明日カラオケ行かない？');
INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES(1, '2024-01-01 00:01:00', 2, '明日予定あるから明後日なら大丈夫');
INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES(1, '2024-01-01 00:02:00', 1, 'じゃあそれで、14:00に新宿でいい？');
INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES(1, '2024-01-01 00:05:00', 2, 'OK');
INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES(1, '2024-01-01 00:15:00', 3, '自分も行きます！');
INSERT INTO T_CHAT_LOG (CHAT_GROUP_ID, CHAT_DATE, USER_ID, COMMENT) VALUES(2, '2024-01-02 00:05:00', 3, '元気？');

INSERT INTO T_FRIEND (USER_ID, FRIEND_USER_ID) VALUES(1, 2);
INSERT INTO T_FRIEND (USER_ID, FRIEND_USER_ID) VALUES(1, 3);
INSERT INTO T_FRIEND (USER_ID, FRIEND_USER_ID) VALUES(1, 4);
INSERT INTO T_FRIEND (USER_ID, FRIEND_USER_ID) VALUES(2, 1);
INSERT INTO T_FRIEND (USER_ID, FRIEND_USER_ID) VALUES(2, 3);
INSERT INTO T_FRIEND (USER_ID, FRIEND_USER_ID) VALUES(3, 1);
INSERT INTO T_FRIEND (USER_ID, FRIEND_USER_ID) VALUES(4, 1);

INSERT INTO T_CHAT_GROUP (CHAT_GROUP_ID, USER_ID) VALUES(1, 1);
INSERT INTO T_CHAT_GROUP (CHAT_GROUP_ID, USER_ID) VALUES(1, 2);
INSERT INTO T_CHAT_GROUP (CHAT_GROUP_ID, USER_ID) VALUES(1, 3);
INSERT INTO T_CHAT_GROUP (CHAT_GROUP_ID, USER_ID) VALUES(2, 1);
INSERT INTO T_CHAT_GROUP (CHAT_GROUP_ID, USER_ID) VALUES(2, 3);

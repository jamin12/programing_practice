insert into Article(id,title,content) values (1,'qwer','1111');
insert into Article(id,title,content) values (2,'asdf','2222');
insert into Article(id,title,content) values (3,'zxcv','3333');

-- article더미 데이터
insert into Article(id,title,content) values (4,'당신의 인생 영화는?','댓글 ㄱ');
insert into Article(id,title,content) values (5,'당신의 인생 음식은?','댓글 ㄱ');
insert into Article(id,title,content) values (6,'당신의 인생 취미는?','댓글 ㄱ');

-- comment더미 데이터
----4번 게시글의 댓글들
insert into Comment(id,article_id,nickname,body) values (1,4,'Park','굳 윌 헌팅');
insert into Comment(id,article_id,nickname,body) values (2,4,'Kim','아이 엠 샘');
insert into Comment(id,article_id,nickname,body) values (3,4,'Choi','쇼생크의 탈출');

----5번 게시글의 댓글들
insert into Comment(id,article_id,nickname,body) values (4,5,'Park','치킨');
insert into Comment(id,article_id,nickname,body) values (5,5,'Kim','샤브샤브');
insert into Comment(id,article_id,nickname,body) values (6,5,'Choi','초밥');

----5번 게시글의 댓글들
insert into Comment(id,article_id,nickname,body) values (7,6,'Park','조깅');
insert into Comment(id,article_id,nickname,body) values (8,6,'Kim','유트브');
insert into Comment(id,article_id,nickname,body) values (9,6,'Choi','독서');
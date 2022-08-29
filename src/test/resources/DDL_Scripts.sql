drop table if exists blog_posts;

create table blog_posts (
	id serial NOT NULL,
	title varchar(100) NOT NULL,
	content text,
	date_created date NOT NULL
);

insert into blog_posts(title, content, date_created) values('title1', 'content1', current_date);
insert into blog_posts(title, content, date_created) values('title2', 'content2', current_date);
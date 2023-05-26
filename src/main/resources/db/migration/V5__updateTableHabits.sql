alter table habits rename column done to finish;
alter table habits add column done boolean;
update habits set done = true where habits.finish = 1::bit(1);
update habits set done = false where habits.finish = 0::bit(1);
alter table habits drop column if exists finish;
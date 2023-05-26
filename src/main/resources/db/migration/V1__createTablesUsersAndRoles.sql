create table if not exists users (  id bigserial not null,
                                    username varchar(30) not null,
                                    password varchar(80) not null,
                                    email varchar(50) UNIQUE,
                                    primary key (id));

create table if not exists roles (  id serial not null,
                                    name varchar(50) not null,
                                    primary key (id));

create table if not exists users_roles (user_id bigint not null,
                                        role_id int not null,
                                        primary key (user_id, role_id),
                                        foreign key (user_id) references users (id),
                                        foreign key (role_id) references  roles (id));

insert into roles (name) values ('ROLE_USER'), ('ROLE_ADMIN'), ('READ_PROFILE');
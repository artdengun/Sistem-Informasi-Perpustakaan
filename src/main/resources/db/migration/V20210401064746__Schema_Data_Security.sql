create table t_role(
    id varchar(36),
    role varchar(100) not null,
    primary key(id),
    unique(id)
);

create table t_user(
  id    varchar(36),
  firstname varchar(50) not null,
  lastname varchar(50) not null,
  username varchar(50)not null,
  email     varchar(150)not null,
  password  varchar(150)not null,
  active Integer,
  roleid varchar not null,
  primary key(id),
  unique(username),
  foreign key (roleid) references t_role(id)
);

create table s_user_permission(
    user_id varchar(36),
    role_id varchar(36),
    primary key(user_id, role_id),
    foreign key(role_id) references t_role(id),
    foreign key (user_id) references t_user(id)
);
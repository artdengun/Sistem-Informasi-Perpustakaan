create table roles (
  id  varchar(36),
  nama varchar(100),
  primary key (id),
  unique (nama)
);

create table users(
  id varchar (36),
  firstname varchar(20) not null,
  lastname varchar(20) not null,
  username varchar(50) not null,
  email varchar(64) not null,
  password varchar(64) not null,
  verificationcode varchar(64),
  active boolean not null,
  id_role varchar (36),
  primary key (id),
  unique(email, username),
  foreign key (id_role) references roles(id)
);

create table users_roles(
  id_user    varchar(36),
  id_role    varchar(100) not null,
  primary key (id_user, id_role),
  foreign key (id_role) references roles(id),
  foreign key (id_user) references users(id)
);

create table s_permissions (
  id    varchar(36),
  label varchar(100) not null,
  value varchar(100) not null,
  primary key (id),
  unique (value)
);

create table s_roles_permissions (
  id_role varchar (36),
  id_permission varchar (36),
  primary key (id_role, id_permission),
  foreign key (id_role) references roles(id),
  foreign key (id_permission) references s_permissions(id)
);

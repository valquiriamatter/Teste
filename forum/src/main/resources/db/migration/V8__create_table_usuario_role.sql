create table usuario_role(
    id BIGINT not null AUTO_INCREMENT,
    usuario_id BIGINT not null,
    role_id BIGINT not null,
    primary key(id),
    foreign key(usuario_id) references usuario(id),
    foreign key(role_id) references role(id)
);

insert into usuario_role (id, usuario_id, role_id) values (1, 1, 1);
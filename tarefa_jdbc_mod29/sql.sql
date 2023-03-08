create sequence sq_cliente
start 1 
increment 1
owned by tb_cliente.id; 

create sequence sq_produto
start 1 
increment 1 
owned by tb_produto.id;

create table tb_produto(
	id bigint, 
	nome varchar(50) not null, 
	codigo varchar(50) not null, 
	fabricante varchar(50) not null,
	constraint pk_id_produto primary key(id)
);

create table tb_cliente(
	id bigint,
	nome varchar(50) not null, 
	codigo varchar(50) not null, 
	constraint pk_id_cliente primary key(id)
);

select * from tb_cliente 
truncate table tb_cliente
truncate table tb_produto;
select * from tb_produto;
SELECT * FROM tb_produto WHERE codigo = '10'
UPDATE tb_produto 
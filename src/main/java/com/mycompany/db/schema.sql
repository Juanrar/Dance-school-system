create table estudiantes (
  	id bigint primary key generated always as identity,
  	nombre text not null,
  	email text unique not null,
	dni text unique not null,
  	telefono text
);

create table pack (
  id bigint primary key generated always as identity,
  nombre text not null,
  cantidad_clases int not null,
  precio numeric not null
);


create table profesores (
	id bigint primary key generated always as identity,
  	nombre text not null,
	email text unique not null,
	dni text unique not null,
	telefono text
);

create table pagos (
	id bigint primary key generated always as identity,
	estudiante_id bigint references estudiante(id),
	pack_id bigint references pack(id),
	cantidad_restante int not null,
	fecha_adquisicion timestamp with time zone default now()
);

create table inscripciones (
  	id bigint primary key generated always as identity,
  	clase_id bigint references clase (id),
	pago_id bigint references pagos (id),
  	fecha_inscripcion timestamp with time zone default now()
);

create table clase (
  id bigint primary key generated always as identity,
  estilo text not null,
  dia_semana text not null,
  horario_inicio time not null,
  horario_fin time not null,
  profesor_id bigint references profesores (id)
);

insert into
	pack (nombre,cantidad_clases, precio)
values
	(
	'Clase individual',1,1500
	),
	(
	'Pack x4',4,4000
	),
	(
	'Pack x8',8,8000
	),
	(
	'Pack x16',16,16000
	);

insert into
  estudiantes (nombre, email,dni, telefono)
values
  (
    'Ana García',
    'ana.garcia@example.com',
	'1',
    '555-1234'
  ),
  (
    'Luis Pérez',
    'luis.perez@example.com',
	'11',
    '555-5678'
  ),
  (
    'María López',
    'maria.lopez@example.com',
	'12',
    '555-8765'
  ),
  (
    'Carlos Sánchez',
    'carlos.sanchez@example.com',
	'13',
    '555-4321'
  ),
  (
    'Laura Fernández',
    'laura.fernandez@example.com',
	'14',
    '555-6789'
);

insert into
  profesores (nombre, email, dni, telefono)
values
  (
    'sere barci',
    'wachumin@example.com',
	'11',
    '555-1234'
  ),
  (
    'erik zapata',
    'erikson@example.com',
	'12',
    '555-5678'
  ),
  (
    'iaru speroni',
    'speroni@example.com',
	'13',
    '555-8765'
  ),
  (
    'malena rosas',
    'rosas@example.com',
	'14',
    '555-4321'
  ),
  (
    'daiana aguirre',
    'africa@example.com',
	'15',
    '555-6789'
);



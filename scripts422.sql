create table car (id serial primary key,
                  brand varchar(100) not null,
                  model varchar(100) not null,
                  price numeric
                 );

create table person (id serial primary key,
                     name varchar(100) not null,
                     age integer check (age > 0),
                     driving_license boolean,
                     car_id integer references car (id)
                    );
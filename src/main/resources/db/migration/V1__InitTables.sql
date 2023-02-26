Create table movie (
    id Bigserial primary key,
    name text not null ,
    release_date date not null ,
    unique (name)
);
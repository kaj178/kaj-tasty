CREATE DATABASE "kaj-tasty"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
SET search_path = "kaj-tasty";

CREATE TABLE public."user" (
	"fullname" TEXT NOT NULL,
	"email" TEXT NOT NULL,
	"password" TEXT NOT NULL,
	PRIMARY KEY (email)
);

CREATE TABLE public."product" (
	"id" SERIAL,
	"name" TEXT NOT NULL,
	"price" INT NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO public."user" (fullname, email, password) VALUES
('admin1', 'ad1@gmail.com', '123'),
('admin2', 'ad2@gmail.com', '456');

INSERT INTO public."product" (name, price) VALUES
('Macbook Air M1', 1100),
('Macbook Pro 2020', 2400);

SELECT * FROM public."user";
SELECT * FROM public."product";
drop table if exists tool_city;
drop table if exists city;
drop table if exists tool;
drop table if exists brand;


CREATE TABLE brand (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL
);


CREATE TABLE tool (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(3000),
    image_url VARCHAR(500),
    marca_id INT,
    precio DOUBLE(10,2),
    FOREIGN KEY (marca_id) REFERENCES brand(id)
);



CREATE TABLE city (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL
);



CREATE TABLE tool_city (
    tool_id INT,
    city_id INT,
    cantidad INT,
    PRIMARY KEY (tool_id, city_id),
    FOREIGN KEY (tool_id) REFERENCES tool(id),
    FOREIGN KEY (city_id) REFERENCES city(id)
);



INSERT INTO brand (name) 
VALUES
  ('DeWalt'),
  ('Caterpillar'),
  ('Makita');

INSERT INTO tool (id, name, description, image_url, marca_id, precio) 
VALUES
    (1, 'Llave Inglesa', 'Llave universal ajustable', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRVSJWENHQdzyaa6e-zK-ncOjcl4_arIkDHwXq83bkFM9kffR6b2uIsX3z6xc9CW42rYAw&usqp=CAU ', 1, 9.67),
    (2, 'Martillo L', 'Martillo tamaño industrial', 'https://homecenterco.scene7.com/is/image/SodimacCO/97957', 2, 19.89),
    (3, 'Destornillador', 'Destornillador Estrella Universal', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQQU6Uj3SBGuJEcaoUpvLTEiHo-I13mr_vcQ&usqp=CAU', 3, 29.91);

INSERT INTO city (name) 
VALUES
  ('Cali'),
  ('Medellin'),
  ('Bogota');

INSERT INTO tool_city (tool_id, city_id, cantidad) 
VALUES
  (1, 1, 10),
  (1, 2, 5),
  (2, 1, 3),
  (2, 3, 8),
  (3, 2, 12),
  (3, 3, 6);




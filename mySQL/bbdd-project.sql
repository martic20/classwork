/*
Tablas:
Coche ( concesionario, marca/, modelo );
CLIENTE (identificador/, tipo, nombre, apellido, teléfono, cuenta );
COCHE (marca, modelo, numero/, color, puertas, cilindrada, matricula, concesionario );
CONCESIONARIO (codigo/, nombre, fecha_inauguracion, direccion, teléfono, marca );
MARCA (nombre/, concesionario_principal );
MODELO (marca, modelo/, pvr);
VENDEDOR (codigo/, nombre, teléfono, jefe, concesionario );
VENTA (marca, modelo, coche/, cliente, vendedor, fecha, precio );
*/

DROP SCHEMA IF EXISTS project;
CREATE SCHEMA project;
USE project;
SET AUTOCOMMIT=0; -- Per no guardar dades fins que no es faci commit.
--Política de claus extranjeres: en cas de que el seu valor canvii es canvia a totes les altres taules, però en cas de es vulgui borrar no es pot i s'ha de fer taula per taula

DROP TABLE IF EXISTS `Marca`;
CREATE TABLE `Marca` (
  `nombre` VARCHAR(32) NOT NULL ,
  `concesionario_principal` SMALLINT(4) ZEROFILL UNSIGNED , 
  PRIMARY KEY (`nombre`)  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--seguint el esquema el camp concesionario_principal hauria d'estar relacionat amb la taula Concesionario però per evitar els problemes que comporta tenir dos relacions inverses entre dos taules he decidit evitar-ho
INSERT INTO `Marca` VALUES ('Nissan','0001');
INSERT INTO `Marca` VALUES ('Toyota','0009');
INSERT INTO `Marca` VALUES ('Seat','0010');
INSERT INTO `Marca` VALUES ('Ford','0006');
INSERT INTO `Marca` VALUES ('BMW','0008');
INSERT INTO `Marca` VALUES ('Audi','0004');

DROP TABLE IF EXISTS `Concesionario`;
CREATE TABLE `Concesionario` (
  `codigo` SMALLINT(4) ZEROFILL UNSIGNED AUTO_INCREMENT,
  `nombre` VARCHAR(32) NOT NULL ,
  `fecha_inauguracion` DATE  NOT NULL ,
  `direccion` VARCHAR(64) NOT NULL ,
  `telefono` VARCHAR(9) NOT NULL ,
  `marca` VARCHAR(32)  NOT NULL ,
  PRIMARY KEY (`codigo`),  
  FOREIGN KEY (`marca`) REFERENCES `Marca`(`nombre`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

INSERT INTO `Concesionario` VALUES (default,'Barcelona Nissanot cotxes','2002-03-20','Aragó 34, Barcelona','935354532','Nissan');
INSERT INTO `Concesionario` VALUES (default,'Coches Pepe','1990-01-25','Catalunya 1, Tremp ','973654634','Ford');
INSERT INTO `Concesionario` VALUES (default,'MyCar.com','2009-03-02','Gran Via 203, Barcelona','935354346','Seat');
INSERT INTO `Concesionario` VALUES (default,'BarnaCar','2002-03-20','Numància 54, Barcelona','935765331','Audi');
INSERT INTO `Concesionario` VALUES (default,'Tot cotxes','2004-03-10','Bac de Roda 200, Barcelona','935764538','Nissan');
INSERT INTO `Concesionario` VALUES (default,'Ford official','2007-03-24','Allivela 34,El Prat de Llobregat','935345776','Ford');
INSERT INTO `Concesionario` VALUES (default,'TarracoCAR','2002-05-12','Rambla 34, Tarragona','957554552','Ford');
INSERT INTO `Concesionario` VALUES (default,'BMW concesionarios','2001-03-07','Diagonal 455','935359743','BMW');
INSERT INTO `Concesionario` VALUES (default,'Gavà cotxes','2000-03-03','Jaume Primer 45, Gavà','935354987','Toyota');
INSERT INTO `Concesionario` VALUES (default,'Seat Girona','2006-03-16','Riera seca 45, Girona','943654322','Seat');


DROP TABLE IF EXISTS `Modelo`;
CREATE TABLE `Modelo` (
  `marca` VARCHAR(32) NOT NULL ,
  `modelo` VARCHAR(32) NOT NULL ,
  `pvr` VARCHAR(9) NOT NULL , 
  PRIMARY KEY (`marca`,`modelo`), 
  FOREIGN KEY (`marca`) REFERENCES `Marca` (`nombre`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Modelo` VALUES('Ford','fiesta','8000');
INSERT INTO `Modelo` VALUES('Ford','octus','14000');
INSERT INTO `Modelo` VALUES('Ford','grown','10000');
INSERT INTO `Modelo` VALUES('Toyota','grande','23000');
INSERT INTO `Modelo` VALUES('Toyota','bett','7609');
INSERT INTO `Modelo` VALUES('Toyota','camper','26065');
INSERT INTO `Modelo` VALUES('Seat','express','13000');
INSERT INTO `Modelo` VALUES('Seat','stuff','15001');
INSERT INTO `Modelo` VALUES('Seat','prof','18000');
INSERT INTO `Modelo` VALUES('Seat','blanco','7000');
INSERT INTO `Modelo` VALUES('Nissan','patrol','12000');
INSERT INTO `Modelo` VALUES('Nissan','master','34000');
INSERT INTO `Modelo` VALUES('BMW','minimal','22000');
INSERT INTO `Modelo` VALUES('Audi','senior','40555');
INSERT INTO `Modelo` VALUES('Audi','origins','23454');



DROP TABLE IF EXISTS `Catalogo`;
CREATE TABLE `Catalogo` (
  `concesionario` SMALLINT(4) ZEROFILL UNSIGNED,
  `marca` VARCHAR(32) NOT NULL,
  `modelo` VARCHAR(32) NOT NULL ,
	PRIMARY KEY (`concesionario`,`marca`,`modelo`),
  FOREIGN KEY (`concesionario`) REFERENCES `Concesionario` (`codigo`) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (`marca`,`modelo`) REFERENCES `Modelo` (`marca`,`modelo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Catalogo` VALUES('0002','Ford','fiesta');
INSERT INTO `Catalogo` VALUES('0002','Ford','octus');
INSERT INTO `Catalogo` VALUES('0002','Ford','grown');
INSERT INTO `Catalogo` VALUES('0002','Seat','stuff');
INSERT INTO `Catalogo` VALUES('0001','Nissan','patrol');
INSERT INTO `Catalogo` VALUES('0001','Nissan','master');
INSERT INTO `Catalogo` VALUES('0001','Ford','fiesta');
INSERT INTO `Catalogo` VALUES('0003','Seat','prof');
INSERT INTO `Catalogo` VALUES('0003','Seat','blanco');
INSERT INTO `Catalogo` VALUES('0004','Audi','senior');
INSERT INTO `Catalogo` VALUES('0004','Audi','origins');
INSERT INTO `Catalogo` VALUES('0005','Nissan','patrol');
INSERT INTO `Catalogo` VALUES('0006','Seat','express');
INSERT INTO `Catalogo` VALUES('0006','Ford','fiesta');
INSERT INTO `Catalogo` VALUES('0006','Ford','octus');
INSERT INTO `Catalogo` VALUES('0006','Ford','grown');
INSERT INTO `Catalogo` VALUES('0007','Audi','senior');
INSERT INTO `Catalogo` VALUES('0008','BMW','minimal');
INSERT INTO `Catalogo` VALUES('0009','Toyota','bett');
INSERT INTO `Catalogo` VALUES('0010','Seat','express');
INSERT INTO `Catalogo` VALUES('0010','Seat','stuff');
INSERT INTO `Catalogo` VALUES('0010','Seat','blanco');
INSERT INTO `Catalogo` VALUES('0010','Seat','prof');

DROP TABLE IF EXISTS `Cliente`;
CREATE TABLE `Cliente` (
  `identificador` CHAR(8) NOT NULL,
  `tipo` ENUM('particular','empresa') NOT NULL ,
  `nombre` VARCHAR(32) NOT NULL ,
  `apellido` VARCHAR(32) NOT NULL ,
  `telefono` CHAR(9) NOT NULL ,
  `cuenta` CHAR(20) NOT NULL ,
  PRIMARY KEY (`identificador`)  
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Cliente` VALUES('45654576','particular','José','Benitez','676656654','76578765746585768574');
INSERT INTO `Cliente` VALUES('87675654','particular','Anna','Benitez','676656654','09578765746585768598');
INSERT INTO `Cliente` VALUES('65434565','empresa','Desguaceros inoxidables S.L.','','676656654','76578765746585768987');
INSERT INTO `Cliente` VALUES('45654554','particular','Judith','Benitez','676656654','24578765746585768538');
INSERT INTO `Cliente` VALUES('56765456','particular','Rosario','Benitez','678856654','76578765746585768574');
INSERT INTO `Cliente` VALUES('23454786','empresa','UNITED STATES FBI','','676656654','45578765746585768574');
INSERT INTO `Cliente` VALUES('56787642','particular','Ramon','Garcia','676634554','98578765746585768456');
INSERT INTO `Cliente` VALUES('34674932','particular','Juan','Carlos','987678764','98578765746585768456');

DROP TABLE IF EXISTS `Coche`;
CREATE TABLE `Coche` (
  `marca` VARCHAR(32) NOT NULL,
  `modelo` VARCHAR(32) NOT NULL ,
  `numero` SMALLINT(3) ZEROFILL UNSIGNED ,
  `color` VARCHAR(32) NOT NULL ,
  `puertas` TINYINT(1) UNSIGNED NOT NULL ,
  `cilindrada` VARCHAR(8) NOT NULL ,
  `matricula` CHAR(7), 
  `concesionario` SMALLINT(4) ZEROFILL UNSIGNED,
  PRIMARY KEY (`marca`,`modelo`,`numero`),
  FOREIGN KEY (`marca`,`modelo`) REFERENCES `Modelo` (`marca`,`modelo`) ON DELETE RESTRICT ON UPDATE CASCADE ,
  FOREIGN KEY (`concesionario`) REFERENCES `Concesionario` (`codigo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Coche` VALUES	(	'Ford'	,	'fiesta'	,	'100'	,	'amarillo'	,	'5'	,	'1300'	,	'3456BGH'	,	'0002'		);
INSERT INTO `Coche` VALUES	(	'Ford'	,	'octus'	,	'100'	,	'rojo'	,	'5'	,	'1601'	,	'3457BGH'	,	'0002'		);
INSERT INTO `Coche` VALUES	(	'Ford'	,	'grown'	,	'100'	,	'plata'	,	'5'	,	'1902'	,	'3458BGH'	,	'0002'		);
INSERT INTO `Coche` VALUES	(	'Seat'	,	'stuff'	,	'100'	,	'oro'	,	'5'	,	'2203'	,	'3459BGH'	,	'0002'		);
INSERT INTO `Coche` VALUES	(	'Nissan'	,	'patrol'	,	'100'	,	'negro'	,	'5'	,	'2504'	,	'3460BGH'	,	'0001'		);
INSERT INTO `Coche` VALUES	(	'Nissan'	,	'master'	,	'100'	,	'granate'	,	'5'	,	'1605'	,	'3461BGH'	,	'0001'		);
INSERT INTO `Coche` VALUES	(	'Ford'	,	'fiesta'	,	'101'	,	'carbon'	,	'5'	,	'1606'	,	'3462BGH'	,	'0001'		);
INSERT INTO `Coche` VALUES	(	'Seat'	,	'prof'	,	'100'	,	'rojo'	,	'5'	,	'1877'	,	'3463BGH'	,	'0003'		);
INSERT INTO `Coche` VALUES	(	'Seat'	,	'blanco'	,	'100'	,	'amarillo'	,	'4'	,	'2148'	,	'3464BGH'	,	'0003'		);
INSERT INTO `Coche` VALUES	(	'Audi'	,	'senior'	,	'100'	,	'plata'	,	'3'	,	'2419'	,	'3465BGH'	,	'0004'		);
INSERT INTO `Coche` VALUES	(	'Audi'	,	'origins'	,	'100'	,	'oro'	,	'2'	,	'2690'	,	'3466BGH'	,	'0004'		);
INSERT INTO `Coche` VALUES	(	'Nissan'	,	'patrol'	,	'101'	,	'plata'	,	'5'	,	'2961'	,	'3467BGH'	,	'0005'		);
INSERT INTO `Coche` VALUES	(	'Nissan'	,	'patrol'	,	'102'	,	'oro'	,	'5'	,	'1882'	,	'3468BGH'	,	'0005'		);
INSERT INTO `Coche` VALUES	(	'Seat'	,	'express'	,	'100'	,	'negro'	,	'3'	,	'1613'	,	'3469BGH'	,	'0006'		);
INSERT INTO `Coche` VALUES	(	'Ford'	,	'fiesta'	,	'102'	,	'granate'	,	'2'	,	'1000'	,	'3470BGH'	,	'0006'		);
INSERT INTO `Coche` VALUES	(	'Ford'	,	'octus'	,	'101'	,	'carbon'	,	'2'	,	'1107'	,	'3471BGH'	,	'0006'		);
INSERT INTO `Coche` VALUES	(	'Ford'	,	'grown'	,	'101'	,	'rojo'	,	'2'	,	'1214'	,	'3472BGH'	,	'0006'		);
INSERT INTO `Coche` VALUES	(	'Audi'	,	'senior'	,	'101'	,	'amarillo'	,	'5'	,	'1214'	,	'3473BGH'	,	'0007'		);
INSERT INTO `Coche` VALUES	(	'BMW'	,	'minimal'	,	'100',	'amarillo'	,	'5'	,	'1321'	,	'3474BGH'	,	'0008'		);
INSERT INTO `Coche` VALUES	(	'Toyota'	,	'bett'	,	'100'	,	'rojo'	,	'5'	,	'1428'	,	'3475BGH'	,	'0009'		);
INSERT INTO `Coche` VALUES	(	'Seat'	,	'express'	,	'101'	,	'plata'	,	'5'	,	'1535'	,	'3476BGH'	,	'0010'		);
INSERT INTO `Coche` VALUES	(	'Seat'	,	'stuff'	,	'101'	,	'oro'	,	'5'	,	'1642'	,	'3477BGH'	,	'0010'		);
INSERT INTO `Coche` VALUES	(	'Seat'	,	'blanco'	,	'101'	,	'negro'	,	'5'	,	'1749'	,	'3478BGH'	,	'0010'		);
INSERT INTO `Coche` VALUES	(	'Seat'	,	'prof'	,	'101'	,	'negro'	,	'5'	,	'1623'	,	'3479BGH'	,	'0010'		);


DROP TABLE IF EXISTS `Vendedor`;
CREATE TABLE `Vendedor` (
  `codigo` SMALLINT(4) ZEROFILL UNSIGNED AUTO_INCREMENT,
  `nombre` VARCHAR(32) NOT NULL ,
  `telefono` CHAR(9) NOT NULL ,
  `jefe` SMALLINT(4) ZEROFILL UNSIGNED ,
  `concesionario` SMALLINT(4) ZEROFILL UNSIGNED,
  PRIMARY KEY (`codigo`),
  FOREIGN KEY (`concesionario`) REFERENCES `Concesionario` (`codigo`)ON DELETE RESTRICT ON UPDATE CASCADE,
 FOREIGN KEY (`jefe`) REFERENCES `Vendedor` (`codigo`)ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Vendedor` VALUES(default	,	'Jose'	,	'976857467'	,	null	,	'1'			);
INSERT INTO `Vendedor` VALUES(	default	,	'Rosario'	,	'975434343'	,null	,	'1'			);
INSERT INTO `Vendedor` VALUES(	default	,	'Juan'	,	'974011219'	,	'1'	,	'2'				);
INSERT INTO `Vendedor` VALUES(	default	,	'Sandra'	,	'972588095'	,	'1'	,	'3'			);
INSERT INTO `Vendedor` VALUES(	default	,	'Cristina'	,	'971164971'	,	'1'	,	'4'			);
INSERT INTO `Vendedor` VALUES(	default	,	'José'	,	'969741847'	,	'1'	,	'5'				);
INSERT INTO `Vendedor` VALUES(	default	,	'Marti'	,	'968318723'	,	'1'	,	'6'				);
INSERT INTO `Vendedor` VALUES(	default	,	'Joan'	,	'966895599'	,	'1'	,	'7'				);
INSERT INTO `Vendedor` VALUES(	default	,	'Javir'	,	'965472475'	,	'1'	,	'8'				);
INSERT INTO `Vendedor` VALUES(	default	,	'Roman'	,	'964049351'	,	'2'	,	'9'				);
INSERT INTO `Vendedor` VALUES(	default	,	'Pau'	,	'962626227'	,	'2'	,	'10'		);

DROP TABLE IF EXISTS `Venta`;
CREATE TABLE `Venta` (
  `marca` VARCHAR(32) NOT NULL,
  `modelo` VARCHAR(32) NOT NULL ,
  `coche` SMALLINT(3) ZEROFILL UNSIGNED ,
  `cliente` CHAR(8) NOT NULL,
  `vendedor` SMALLINT(4) ZEROFILL UNSIGNED,
  `fecha` DATE NOT NULL ,
  `precio` VARCHAR(32) NOT NULL ,
  FOREIGN KEY (`marca`,`modelo`,`coche`) REFERENCES `Coche` (`marca`,`modelo`,`numero`) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (`cliente`) REFERENCES `Cliente` (`identificador`)ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (`vendedor`) REFERENCES `Vendedor` (`codigo`)ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `Venta` VALUES	(	'Ford'	,	'fiesta'	,	'100'	,	'45654576'	,	'5'	,	'2011-12-04'	,	'10000'	);
INSERT INTO `Venta` VALUES	(	'Ford'	,	'octus'	,	'100'	,	'45654576'	,	'4'	,	'2011-12-04'	,	'10987'	);
INSERT INTO `Venta` VALUES	(	'Ford'	,	'grown'	,	'100'	,	'87675654'	,	'9'	,	'2012-12-05'	,	'12356'	);
INSERT INTO `Venta` VALUES	(	'Seat'	,	'stuff'	,	'100'	,	'87675654'	,	'7'	,	'2013-12-06'	,	'22333'	);
INSERT INTO `Venta` VALUES	(	'Nissan'	,	'patrol'	,	'100'	,	'65434565'	,	'5'	,	'2013-12-06'	,'12111'		);
INSERT INTO `Venta` VALUES	(	'Nissan'	,	'master'	,	'100'	,	'45654554'	,	'8'	,	'2013-12-07'	,'11222'		);
INSERT INTO `Venta` VALUES	(	'Ford'	,	'fiesta'	,	'102'	,	'56765456'	,	'5'	,	'2013-12-08'	,	'23444');
INSERT INTO `Venta` VALUES	(	'Seat'	,	'prof'	,	'101'	,	'23454786'	,	'2'	,	'2013-12-08'	,	'13223'		);
INSERT INTO `Venta` VALUES	(	'Seat'	,	'blanco'	,	'101'	,	'56787642'	,	'4'	,	'2013-12-08'	,	'15676'	);
INSERT INTO `Venta` VALUES	(	'Audi'	,	'senior'	,	'100'	,	'56787642'	,	'3'	,	'2014-12-08'	,	'17654'	);
INSERT INTO `Venta` VALUES	(	'Audi'	,	'origins'	,	'100'	,	'56787642'	,	'2'	,	'2014-12-09'	,	'19765'	);
INSERT INTO `Venta` VALUES	(	'Nissan'	,	'patrol'	,	'101'	,	'56787642'	,	'5'	,	'2015-12-09'	,	'19876');
INSERT INTO `Venta` VALUES	(	'Nissan'	,	'patrol'	,	'102'	,	'34674932'	,	'5'	,	'2015-12-09'	,	'14678');
INSERT INTO `Venta` VALUES	(	'Seat'	,	'express'	,	'101'	,	'34674932'	,	'3'	,	'2015-12-09'	,	'13567'	);
INSERT INTO `Venta` VALUES	(	'Ford'	,	'fiesta'	,	'101'	,	'34674932'	,	'2'	,	'2015-12-09'	,	'16787'	);
INSERT INTO `Venta` VALUES	(	'Ford'	,	'grown'	,	'101'	,	'65434565'	,	'1'	,	'2015-12-10'	,	'23443'		);
INSERT INTO `Venta` VALUES	(	'Audi'	,	'senior'	,	'101'	,	'65434565'	,	'2'	,	'2016-12-11'	,	'12365'	);
INSERT INTO `Venta` VALUES	(	'BMW'	,	'minimal'	,	'100'	,	'65434565'	,	'5'	,	'2016-12-12'	,	'34555'	);
INSERT INTO `Venta` VALUES	(	'Toyota'	,	'bett'	,	'100'	,	'65434565'	,	'3'	,	'2016-12-13'	,	'12222'	);
INSERT INTO `Venta` VALUES	(	'Seat'	,	'express'	,	'100'	,	'65434565'	,	'4'	,	'2016-12-14'	,	'13444'	);

INSERT INTO `Venta` VALUES	(	'Seat'	,	'blanco'	,	'100'	,	'65434565'	,	'5'	,	'2016-12-15'	,	'9000');
INSERT INTO `Venta` VALUES	(	'Seat'	,	'prof'	,	'100'	,	'65434565'	,	'6'	,	'2016-12-16'	,	'6878'	);

commit;


/* some example queries

--- Get total of sells for every model:---

select m.modelo,count(v.cliente) as 'Total ventas' from Modelo m join Venta v
using(modelo) group by v.modelo order by count(v.coche) desc;

--- Get total of sells of every dealer:---

select Concesionario.nombre,count(Venta.cliente) from Coche join
Concesionario on (Coche.concesionario=Concesionario.codigo) join Venta on
Coche.numero=Venta.coche and Coche.marca=Venta.marca and
Coche.modelo=Venta.modelo group by concesionario order by 2 desc;

--- Get stock ---

select concat(marca,' ', modelo) as Modelos,count(concesionario) as
'Catalogos' from Catalogo group by modelo,marca order by
count(concesionario) desc;

--- Get principal clients ---

select nombre,apellido,telefono,tipo, sum(precio) as Facturacion from Venta
join Cliente on(identificador=cliente) group by cliente order by Facturacion
desc;

--- Get the best emplor seller of every year ---

create view ventaAño as (select year(fecha) as fecha,vendedor,sum(precio) as
precio from Venta group by vendedor,year(fecha));
select a.fecha,a.precio as'facturacion',v.nombre,v.telefono,jefe.nombre as
Jefe,c.nombre as Concesionario from ventaAño a join (select max(precio) as
precio from ventaAño group by fecha) as b on a.precio=b.precio join
Vendedor v on a.vendedor=v.codigo join Vendedor jefe on v.jefe=jefe.codigo
join Concesionario c on v.concesionario=c.codigo order by 1 desc;

--- Get difference beetwen the recommended price and the price is sold ---

select c.nombre,avg(a.med-m.pvr) as diferencia from Vendedor v join (select
marca,modelo,vendedor,avg(precio) as med from Venta group by
marca,modelo,vendedor) as a on a.vendedor=v.codigo join Concesionario c
on v.concesionario=c.codigo join Modelo m on m.marca=a.marca and
m.modelo=a.modelo group by c.codigo order by 2;

*/

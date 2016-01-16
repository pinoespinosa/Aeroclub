
create view vencimientosproximos as

(

select 	pi.`fechaVencimientoLicencia` AS `fecha`,
		concat('Vencimiento de licencia: ',pe.`apellido`,' , ',pe.`nombre`) AS `detalle` 

from 	`aviones`.`piloto` as pi inner join `aviones`.`persona` as pe on (pe.id = pi.id)  

where 	(pi.`fechaVencimientoLicencia` is not null)

) 

union 

(

select 	`aviones`.`vencimientos`.`fecha` AS `fecha`,
		`aviones`.`vencimientos`.`detalle` AS `detalle` 

from 	`aviones`.`vencimientos` 

where 	(`aviones`.`vencimientos`.`fecha` is not null)

) 

order by `fecha`;
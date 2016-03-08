
DROP VIEW `vencimientosproximos`;

CREATE VIEW `vencimientosproximos` AS 
(
	select 	'1' AS `fecha`,
			concat('Alerta de stock. ',
			sum(`gastos_normalizados`.`unidades`),
			' litros de combustible') AS `detalle` 
	from `gastos_normalizados` 
	where (`gastos_normalizados`.`tipo` like 'COMBUSTIBLE%') 
	having (sum(`gastos_normalizados`.`unidades`) < 80000)
) 
union 
(
	select 	'1' AS `fecha`,
			concat('Alerta de stock. ',
			sum(`gastos_normalizados`.`unidades`),
			' litros de aceite') AS `detalle` 
	from `gastos_normalizados` 
	where (`gastos_normalizados`.`tipo` like 'ACEITE%') 
	having (sum(`gastos_normalizados`.`unidades`) < 50)
) 
union 
(
	select 	`vencimientos`.`fecha` AS `fecha`,
			`vencimientos`.`detalle` AS `detalle` 
	from `vencimientos` 
	where (`vencimientos`.`fecha` is not null)
) 
	order by `fecha`;

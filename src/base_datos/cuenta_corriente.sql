
DROP VIEW `cuenta_corriente`;

CREATE VIEW `cuenta_corriente` AS 
(
	select 	`gasto`.`idPersona` AS `idPersona`,
			`gasto`.`fecha` AS `fecha`,
			(`gasto`.`total` * -(1)) AS `total`,
			concat(`gasto`.`claseDeGasto`,' - ',`gasto`.`tipo`) AS `detalle` 
	from `gasto` 
	where (`gasto`.`claseDeGasto` like 'VENTA%' and `gasto`.`formaPago` like '1')
) 
union 
(
	select 	`gasto`.`idPersona` AS `idPersona`,
			`gasto`.`fecha` AS `fecha`,
			`gasto`.`total` AS `total`,
			concat(`gasto`.`claseDeGasto`,' - ',`gasto`.`tipo`) AS `detalle` 
	from `gasto` 
	where (`gasto`.`claseDeGasto` like 'DEPOSITO' and `gasto`.`formaPago` like '1')
) 
union 
(
	select `vuelo`.`idPiloto` AS `idPiloto`,
	`vuelo`.`horaInicio` AS `fecha`,
	(`vuelo`.`precio` * -(1)) AS `total`,
	'VUELO' AS `detalle` 
	from `vuelo`
	where (`vuelo`.`formaDePago` like '1')
	
)
union 
(
	select `horas_vendida_adelantado`.`pilotoId` AS `idPiloto`,
	`horas_vendida_adelantado`.`fecha` AS `fecha`,
	(`horas_vendida_adelantado`.`saldoOriginal` * -(1)) AS `total`,
	'VUELO_ADELANTADO' AS `detalle` 
	from `horas_vendida_adelantado`
	where (`horas_vendida_adelantado`.`formaDePago` like '1')
);

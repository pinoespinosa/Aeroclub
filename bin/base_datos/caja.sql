
DROP VIEW `caja`;

CREATE VIEW `caja` AS 
(
	select 	`gasto`.`idPersona` AS `idPersona`,
			`gasto`.`fecha` AS `fecha`,
			'0' AS `Egreso_de_caja`,
			`gasto`.`total` AS `Ingreso_en_caja`,
			concat(`gasto`.`claseDeGasto`,' - ',`gasto`.`tipo`) AS `detalle` 
	from `gasto` 
	where (`gasto`.`claseDeGasto` like 'VENTA%' and (`gasto`.`formaPago` like '0' or `gasto`.`formaPago` like '2'))
) 
union 
(
	select 	`gasto`.`idPersona` AS `idPersona`,
			`gasto`.`fecha` AS `fecha`,
            `gasto`.`total` AS `Egreso_de_caja`,
			'0' AS `Ingreso_en_caja`,            
			concat(`gasto`.`claseDeGasto`,' - ',`gasto`.`tipo`) AS `detalle` 
	from `gasto` 
	where (`gasto`.`claseDeGasto` like 'DEPOSITO' and (`gasto`.`formaPago` like '0' or `gasto`.`formaPago` like '2'))
)
union 
(
	select 	`gasto`.`idPersona` AS `idPersona`,
			`gasto`.`fecha` AS `fecha`,
            `gasto`.`total` AS `Egreso_de_caja`,
			'0' AS `Ingreso_en_caja`,            
			concat(`gasto`.`claseDeGasto`,' - ',`gasto`.`tipo`) AS `detalle` 
	from `gasto` 
	where (`gasto`.`claseDeGasto` like 'COMPRA%' and (`gasto`.`formaPago` like '0' or `gasto`.`formaPago` like '2'))
)  
union 
(
	select `vuelo`.`idPiloto` AS `idPiloto`,
	`vuelo`.`horaInicio` AS `fecha`,
	'0' AS `Egreso_de_caja`,
	`vuelo`.`precio` AS `Ingreso_en_caja`,
	'VUELO' AS `detalle` 
	from `vuelo`
	where ((`vuelo`.`formaDePago` like '0' or `vuelo`.`formaDePago` like '2'))
	
)
union 
(
	select `horas_vendida_adelantado`.`pilotoId` AS `idPiloto`,
	`horas_vendida_adelantado`.`fecha` AS `fecha`,
	'0' AS `Egreso_de_caja`,
	`horas_vendida_adelantado`.`saldoOriginal` AS `Ingreso_en_caja`,
    'VUELO_ADELANTADO' AS `detalle` 
	from `horas_vendida_adelantado`
	where ((`horas_vendida_adelantado`.`formaDePago` like '0' or `horas_vendida_adelantado`.`formaDePago` like '2'))
);
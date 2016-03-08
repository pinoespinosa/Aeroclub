
DROP VIEW `gastos_normalizados`;

CREATE VIEW `gastos_normalizados` AS 
(
	#Esto es para control de stock, pongo las cantidades en negativo y positivo
	
	
	
	
	select 	`gasto`.`id` AS `id`,
			`gasto`.`claseDeGasto` AS `claseDeGasto`,
			`gasto`.`tipo` AS `tipo`,
			`gasto`.`unidades` AS `unidades`,
			`gasto`.`detalle` AS `detalle`,
			`gasto`.`total` AS `total`,
			`gasto`.`formaPago` AS `enEfectivo`,
			`gasto`.`fecha` AS `fecha`,
			`gasto`.`idPersona` AS `idPersona` 
			
			from `gasto` 
			where ((`gasto`.`claseDeGasto` like 'COMPRA%') and ((`gasto`.`tipo` like 'ACEITE%') OR (`gasto`.`tipo` like 'COMBUSTIBLE%')))
) 
union 
(
	select 	`gasto`.`id` AS `id`,
			`gasto`.`claseDeGasto` AS `claseDeGasto`,
			`gasto`.`tipo` AS `tipo`,
			(`gasto`.`unidades` * -(1)) AS `unidades`,
			`gasto`.`detalle` AS `detalle`,
			`gasto`.`total` AS `total`,
			`gasto`.`formaPago` AS `enEfectivo`,
			`gasto`.`fecha` AS `fecha`,
			`gasto`.`idPersona` AS `idPersona` 
			
			from `gasto` 
			where ((`gasto`.`claseDeGasto` like 'VENTA%') and ((`gasto`.`tipo` like 'COMBUSTIBLE%')OR(`gasto`.`tipo` like 'ACEITE%')))
) 
union 
(
		select 	`vuelo`.`id` AS `id`,
				'VENTA' AS `VENTA`,
				'ACEITE_VUELO' AS `tipo`,
				(`vuelo`.`cantAceite` * -(1)) AS `unidades`,
				'VUELO' AS `detalle`,
				'' AS `total`,		
				`vuelo`.`formaDePago` AS `enEfectivo`,
				`vuelo`.`horaInicio` as fecha, 
				`vuelo`.`idPiloto` AS `idPersona`
		from `vuelo`
) 
union 
(
		select 	`vuelo`.`id` AS `id`,
				'VENTA' AS `VENTA`,
				'COMBUSTIBLE_VUELO' AS `tipo`,
				(`vuelo`.`cantCombustible` * -(1)) AS `unidades`,
				'VUELO' AS `detalle`,
				'' AS `total`,
				`vuelo`.`formaDePago` AS `enEfectivo`,
				`vuelo`.`horaInicio` as fecha, 
				`vuelo`.`idPiloto` AS `idPersona` 
		from `vuelo`
);

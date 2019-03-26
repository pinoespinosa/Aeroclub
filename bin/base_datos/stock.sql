(
SELECT 'COMBUSTIBLE' as Recurso, SUM(unidades) as Stock_disponible 
FROM gastos_normalizados 
WHERE tipo like 'COMBUSTIBLE%'
)
UNION
(
SELECT tipo as Recurso, SUM(unidades) as Stock_disponible 
FROM gastos_normalizados 
WHERE tipo like 'ACEITE%'
)
UNION
(
SELECT tipo as Recurso, SUM(unidades) as Stock_disponible 
FROM gastos_normalizados 
WHERE not (tipo like 'ACEITE%' or tipo like 'COMBUSTIBLE%')
)
SELECT sql
FROM main.sqlite_master
WHERE type = 'table'
  and name = 'financial_institution'
;

SELECT *
FROM financial_institution
ORDER BY code ASC
;

SELECT *
FROM financial_institution
WHERE category = 'BANK'
  AND name = '하나은행'
  AND representative IS TRUE
;

-- ------------------------------------------------------------------------------------------------------ representative
SELECT *
FROM financial_institution
WHERE representative IS TRUE
;

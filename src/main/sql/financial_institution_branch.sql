SELECT sql
FROM main.sqlite_master
WHERE type = 'table'
  and name = 'financial_institution_branch'
;

-- ------------------------------------------------------------------------------------------------ managing_branch_code
SELECT fib1.branch_code,
       fib1.financial_institution_name,
       fib1.branch_name,
       fib1.status,
       fib1.managing_branch_code,
       fib2.branch_code,
       fib2.financial_institution_name,
       fib2.branch_name
FROM (SELECT * FROM financial_institution_branch WHERE managing_branch_code IS NOT NULL) AS fib1
         LEFT JOIN financial_institution_branch AS fib2 ON fib1.managing_branch_code = fib2.branch_code
    ANd fib2.branch_code IS NULL
;

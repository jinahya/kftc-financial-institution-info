---
-- #%L
-- kftc-financial-institution-info
-- %%
-- Copyright (C) 2024 Jinahya, Inc.
-- %%
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- 
--      http://www.apache.org/licenses/LICENSE-2.0
-- 
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- #L%
---
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

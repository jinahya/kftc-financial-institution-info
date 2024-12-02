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

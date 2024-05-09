# kftc-financial-institution-info.sqlite3

## .schema

```sqlite
CREATE TABLE financial_institution
(
    code           TEXT    not null primary key,
    name           TEXT    not null,
    representative INTEGER not null,
    category       TEXT    not null
);
CREATE TABLE financial_institution_branch
(
    branch_code                TEXT not null primary key,
    financial_institution_name TEXT not null,
    branch_name                TEXT not null,
    phone_number               TEXT,
    fax_number                 TEXT,
    postal_code                TEXT,
    address                    TEXT,
    status                     TEXT,
    managing_branch_code       TEXT
);
CREATE INDEX idx_category_name
    on financial_institution (category, name);
CREATE INDEX idx_name
    on financial_institution (name);
CREATE INDEX idx_financial_institution_name_branch_name
    on financial_institution_branch (financial_institution_name, branch_name);
```

## `financial_institution`

금융 기관 정보.

| name             | description | notes       |
|------------------|-------------|-------------|
| `code`           | 금융기관 코드     |             |
| `name`           | 금융기관 이름     |             |
| `representative` | 대표 여부       |             |
| `category`       | 카테고리        | e.g. `BANK` |

## `financial_institution_branch`

금융 기관 지점 정보

| name                         | description | notes     |
|------------------------------|-------------|-----------|
| `branch_code`                | 지점 코드       |           |
| `financial_institution_name` | 금융기관 이름     |           |
| `branch_name`                | 지점 이름       |           |
| `phone_number`               | 전화번호        |           |
| `fax_number`                 | 팩스번호        |           |
| `postal_code`                | 우편번호        |           |
| `address`                    | 주소          |           |
| `status`                     | 상태          | e.g. `정상` |
| `managing_branch_code`       | 관리지점 코드     |           |

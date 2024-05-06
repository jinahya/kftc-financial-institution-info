# kftc-financial-institution-info.sqlite3

You can generate an SQLite database file, with two tables, while builds,
into `db/kftc-financial-institution-info.sqlite3`.

See `db/kftc-financial-institution-info.sqlite3.md` for more information.

## `financial_institution`

금융 기관 정보.

| name             | type      | description | notes       |
|------------------|-----------|-------------|-------------|
| `code`           | `TEXT`    | 금융기관 코드     |             |
| `name`           | `TEXT`    | 금융기관 이름     |             |
| `representative` | `INTEGER` | 대표 여부       |             |
| `category`       | `TEXT`    | 카테고리        | e.g. `BANK` |

## `financial_institution_branch`

금융 기관 지점 정보

| name                         | type   | description | notes     |
|------------------------------|--------|-------------|-----------|
| `branch_code`                | `TEXT` | 지점 코드       |           |
| `financial_institution_name` | `TEXT` | 금융기관 이름     |           |
| `branch_name`                | `TEXT` | 지점 이름       |           |
| `phone_number`               | `TEXT` | 전화번호        |           |
| `fax_number`                 | `TEXT` | 팩스번호        |           |
| `postal_code`                | `TEXT` | 우편번호        |           |
| `address`                    | `TEXT` | 주소          |           |
| `status`                     | `TEXT` | 상태          | e.g. `정상` |
| `managing_branch_code`       | `TEXT` | 관리지점 코드     |           |

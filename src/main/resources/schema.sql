DROP TABLE IF EXISTS USR_MASTER_MST;
CREATE TABLE USR_MASTER_MST
(
    user_id         varchar(20) not null,
    user_name       varchar(30),
    user_email      varchar(100),
    created_at      date,
    created_user_id varchar(10),
    updated_at      date,
    updated_user_id varchar(10)
);

ALTER TABLE USR_MASTER_MST ADD CONSTRAINT PK_USR_MASTER_MST PRIMARY KEY (user_id);
COMMENT ON TABLE USR_MASTER_MST IS '사용자 마스터';
COMMENT ON COLUMN USR_MASTER_MST.user_id IS '사용자 ID';
COMMENT ON COLUMN USR_MASTER_MST.user_name IS '사용자 이름';
COMMENT ON COLUMN USR_MASTER_MST.user_email IS '사용자 이메일';
COMMENT ON COLUMN USR_MASTER_MST.created_at IS '최초등록일시';
COMMENT ON COLUMN USR_MASTER_MST.created_user_id IS '최초등록사용자 ID';
COMMENT ON COLUMN USR_MASTER_MST.updated_at IS '최종변경일시';
COMMENT ON COLUMN USR_MASTER_MST.updated_user_id IS '최종변경사용자 ID';


DROP TABLE IF EXISTS USR_ACNBLN_MST;
CREATE TABLE USR_ACNBLN_MST
(
    account_id      varchar(8)  not null,
    user_id         varchar(20) not null,
    balance_amount  decimal(20, 0),
    inactive_yn     varchar(1) default 'N',
    created_at      date,
    created_user_id varchar(10),
    updated_at      date,
    updated_user_id varchar(10)

);

ALTER TABLE USR_ACNBLN_MST ADD CONSTRAINT PK_USR_ACNBLN_MST PRIMARY KEY (account_id);

COMMENT ON TABLE USR_ACNBLN_MST IS '회원 계좌 및 잔액 마스터';
COMMENT ON COLUMN USR_ACNBLN_MST.account_id IS '계좌 ID';
COMMENT ON COLUMN USR_ACNBLN_MST.user_id IS '사용자 ID';
COMMENT ON COLUMN USR_ACNBLN_MST.balance_amount IS '잔액';
COMMENT ON COLUMN USR_ACNBLN_MST.inactive_yn IS '휴면여부';
COMMENT ON COLUMN USR_ACNBLN_MST.created_at IS '최초등록일시';
COMMENT ON COLUMN USR_ACNBLN_MST.created_user_id IS '최초등록사용자 ID';
COMMENT ON COLUMN USR_ACNBLN_MST.updated_at IS '최종변경일시';
COMMENT ON COLUMN USR_ACNBLN_MST.updated_user_id IS '최종변경사용자 ID';


DROP TABLE IF EXISTS INV_PRDUCT_MST;
CREATE TABLE INV_PRDUCT_MST
(
    product_id          varchar(8)   not null,
    product_category    varchar(10)  not null,
    product_name        varchar(200) not null,
    started_at          date,
    finished_at         date,
    total_invest_amount decimal(20, 0),
    max_invest_amount   decimal(20, 0),
    created_at          date         not null,
    created_user_id     varchar(10)  not null,
    updated_at          date         not null,
    updated_user_id     varchar(10)  not null
);

ALTER TABLE INV_PRDUCT_MST ADD CONSTRAINT PK_INV_PRDUCT_MST PRIMARY KEY (product_id);

COMMENT ON TABLE INV_PRDUCT_MST IS '투자상품 마스터';
COMMENT ON COLUMN INV_PRDUCT_MST.product_id IS '투자상품 ID';
COMMENT ON COLUMN INV_PRDUCT_MST.product_category IS '투자상품 분류';
COMMENT ON COLUMN INV_PRDUCT_MST.product_name IS '투자상품 이름';
COMMENT ON COLUMN INV_PRDUCT_MST.started_at IS '투자 시작 일시';
COMMENT ON COLUMN INV_PRDUCT_MST.finished_at IS '투자 종료 일시';
COMMENT ON COLUMN INV_PRDUCT_MST.total_invest_amount IS '총투자모집금액';
COMMENT ON COLUMN INV_PRDUCT_MST.created_at IS '최초등록일시';
COMMENT ON COLUMN INV_PRDUCT_MST.created_user_id IS '최초등록사용자 ID';
COMMENT ON COLUMN INV_PRDUCT_MST.updated_at IS '최종변경일시';
COMMENT ON COLUMN INV_PRDUCT_MST.updated_user_id IS '최종변경사용자 ID';


DROP TABLE IF EXISTS INV_PRDUCT_HST;
CREATE TABLE INV_PRDUCT_HST
(
    invest_id       bigint      not null,
    product_id      varchar(8)  not null,
    user_id         varchar(20) not null,
    invested_at     date,
    invest_amount   decimal(20, 0),
    cancel_yn       varchar(1),
    created_at      date        not null,
    created_user_id varchar(10) not null,
    updated_at      date        not null,
    updated_user_id varchar(10) not null

);

ALTER TABLE INV_PRDUCT_HST ADD CONSTRAINT PK_INV_PRDUCT_HST PRIMARY KEY (invest_id);

COMMENT ON TABLE INV_PRDUCT_HST IS '투자상품별 투자 내역';
COMMENT ON COLUMN INV_PRDUCT_HST.product_id IS '투자상품 ID';
COMMENT ON COLUMN INV_PRDUCT_HST.user_id IS '사용자 ID';
COMMENT ON COLUMN INV_PRDUCT_HST.invested_at IS '투자일시';
COMMENT ON COLUMN INV_PRDUCT_HST.invest_amount IS '투자 금액';
COMMENT ON COLUMN INV_PRDUCT_HST.cancel_yn IS '취소여부';
COMMENT ON COLUMN INV_PRDUCT_HST.created_at IS '최초등록일시';
COMMENT ON COLUMN INV_PRDUCT_HST.created_user_id IS '최초등록사용자 ID';
COMMENT ON COLUMN INV_PRDUCT_HST.updated_at IS '최종변경일시';
COMMENT ON COLUMN INV_PRDUCT_HST.updated_user_id IS '최종변경사용자 ID';


CREATE SEQUENCE IF NOT EXISTS SEQ_INV_PRDUCT_HST
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;
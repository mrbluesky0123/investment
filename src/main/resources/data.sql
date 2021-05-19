-- User data
INSERT INTO USR_MASTER_MST(user_id, user_name, user_email, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( '2021050001', 'KOGILDONG', 'kogildong@dooly.com', CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );
INSERT INTO USR_MASTER_MST(user_id, user_name, user_email, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( '2021050002', 'MICHOL', 'michol@dooly.com', CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );
INSERT INTO USR_MASTER_MST(user_id, user_name, user_email, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( '2021050003', 'DOOLY', 'dooly@dooly.com', CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );
INSERT INTO USR_MASTER_MST(user_id, user_name, user_email, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( '2021050004', 'DDOCHI', 'ddochi@dooly.com', CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );
INSERT INTO USR_MASTER_MST(user_id, user_name, user_email, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( '2021050005', 'KOHEEDONG', 'koheedong@dooly.com', CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );
INSERT INTO USR_MASTER_MST(user_id, user_name, user_email, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( '2021050006', 'DOWNER', 'downer@dooly.com', CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );


-- ACCOUNT DATA
INSERT INTO USR_ACNBLN_MST(account_id, user_id, balance_amount, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( 'A1241555', '2021050001', 24000000, CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );
INSERT INTO USR_ACNBLN_MST(account_id, user_id, balance_amount, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( 'A1241533', '2021050002', 120000, CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );
INSERT INTO USR_ACNBLN_MST(account_id, user_id, balance_amount, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( 'A1241524', '2021050003', 3560000, CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );
INSERT INTO USR_ACNBLN_MST(account_id, user_id, balance_amount, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( 'A1241566', '2021050004', 100000000, CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );
INSERT INTO USR_ACNBLN_MST(account_id, user_id, balance_amount, inactive_yn, created_at, created_user_id, updated_at, updated_user_id)
VALUES ( 'A1241512', '2021050005', 24000000, 'Y',  CURRENT_DATE, 'SYSTEM', CURRENT_DATE, 'SYSTEM' );

-- PRODUCT DATA
INSERT INTO INV_PRDUCT_MST(product_id,
                           product_category,
                           product_name,
                           started_at,
                           finished_at,
                           total_invest_amount,
                           max_invest_amount,
                           created_at,
                           created_user_id,
                           updated_at,
                           updated_user_id)
VALUES ('CRD00001',
        'CREDIT',
        'CreditFunding1',
        PARSEDATETIME ('2021-05-01 00:00:00','yyyy-MM-dd hh:mm:ss'),
        PARSEDATETIME ('2021-05-31 23:59:59','yyyy-MM-dd hh:mm:ss'),
        3000000,
        50000,
        CURRENT_DATE,
        'SYSTEM',
        CURRENT_DATE,
        'SYSTEM' );
INSERT INTO INV_PRDUCT_MST(product_id,
                           product_category,
                           product_name,
                           started_at,
                           finished_at,
                           total_invest_amount,
                           max_invest_amount,
                           created_at,
                           created_user_id,
                           updated_at,
                       updated_user_id)
VALUES ('CRD00002',
        'CREDIT',
        'CreditFunding2',
        PARSEDATETIME ('2021-04-01 00:00:00','yyyy-MM-dd hh:mm:ss'),
        PARSEDATETIME ('2021-04-30 23:59:59','yyyy-MM-dd hh:mm:ss'),
        6000000,
        1000000,
        CURRENT_DATE,
        'SYSTEM',
        CURRENT_DATE,
        'SYSTEM' );

INSERT INTO INV_PRDUCT_MST(product_id,
                           product_category,
                           product_name,
                           started_at,
                           finished_at,
                           total_invest_amount,
                           max_invest_amount,
                           created_at,
                           created_user_id,
                           updated_at,
                           updated_user_id)
VALUES ('RES00001',
        'REALEST',
        'RealEstateFunding1',
        PARSEDATETIME ('2021-04-01 00:00:00','yyyy-MM-dd hh:mm:ss'),
        PARSEDATETIME ('2021-05-30 23:59:59','yyyy-MM-dd hh:mm:ss'),
        30000000,
        700000,
        CURRENT_DATE,
        'SYSTEM',
        CURRENT_DATE,
        'SYSTEM' );

INSERT INTO INV_PRDUCT_MST(product_id,
                           product_category,
                           product_name,
                           started_at,
                           finished_at,
                           total_invest_amount,
                           max_invest_amount,
                           created_at,
                           created_user_id,
                           updated_at,
                           updated_user_id)
VALUES ('RES00002',
        'REALEST',
        'RealEstateFunding2',
        PARSEDATETIME ('2021-04-01 00:00:00','yyyy-MM-dd hh:mm:ss'),
        PARSEDATETIME ('2021-05-30 23:59:59','yyyy-MM-dd hh:mm:ss'),
        100000000,
        400000,
        CURRENT_DATE,
        'SYSTEM',
        CURRENT_DATE,
        'SYSTEM' );

INSERT INTO INV_PRDUCT_MST(product_id,
                           product_category,
                           product_name,
                           started_at,
                           finished_at,
                           total_invest_amount,
                           max_invest_amount,
                           created_at,
                           created_user_id,
                           updated_at,
                           updated_user_id)
VALUES ('RES00003',
        'REALEST',
        'RealEstateFunding3',
        PARSEDATETIME ('2021-04-01 00:00:00','yyyy-MM-dd hh:mm:ss'),
        PARSEDATETIME ('2021-05-30 23:59:59','yyyy-MM-dd hh:mm:ss'),
        1,
        10,
        CURRENT_DATE,
        'SYSTEM',
        CURRENT_DATE,
        'SYSTEM' );

-- INVESTED DATA
INSERT INTO INV_PRDUCT_HST(
    invest_id,
    product_id,
    user_id,
    invested_at,
    invest_amount,
    cancel_yn,
    created_at,
    created_user_id,
    updated_at,
    updated_user_id
) VALUES (
    SEQ_INV_PRDUCT_HST.NEXTVAL,
    'CRD00001',
    '2021050002',
    PARSEDATETIME ('2021-05-11 10:23:40','yyyy-MM-dd hh:mm:ss'),
    40000,
    'N',
    CURRENT_DATE,
    'SYSTEM',
    CURRENT_DATE,
    'SYSTEM'
);


INSERT INTO INV_PRDUCT_HST(
    invest_id,
    product_id,
    user_id,
    invested_at,
    invest_amount,
    cancel_yn,
    created_at,
    created_user_id,
    updated_at,
    updated_user_id
) VALUES (
    SEQ_INV_PRDUCT_HST.NEXTVAL,
    'RES00001',
    '2021050001',
    PARSEDATETIME ('2021-04-23 22:14:15','yyyy-MM-dd hh:mm:ss'),
    25000,
    'N',
    CURRENT_DATE,
    'SYSTEM',
    CURRENT_DATE,
    'SYSTEM'
);


INSERT INTO INV_PRDUCT_HST(
    invest_id,
    product_id,
    user_id,
    invested_at,
    invest_amount,
    cancel_yn,
    created_at,
    created_user_id,
    updated_at,
    updated_user_id
) VALUES (
             SEQ_INV_PRDUCT_HST.NEXTVAL,
             'CRD00001',
             '2021050004',
             PARSEDATETIME ('2021-05-08 02:48:57','yyyy-MM-dd hh:mm:ss'),
             10000,
             'Y',
             CURRENT_DATE,
             'SYSTEM',
             CURRENT_DATE,
             'SYSTEM'
         );

INSERT INTO INV_PRDUCT_HST(
    invest_id,
    product_id,
    user_id,
    invested_at,
    invest_amount,
    cancel_yn,
    created_at,
    created_user_id,
    updated_at,
    updated_user_id
) VALUES (
             SEQ_INV_PRDUCT_HST.NEXTVAL,
             'CRD00001',
             '2021050004',
             PARSEDATETIME ('2021-05-08 02:49:34','yyyy-MM-dd hh:mm:ss'),
             30000,
             'N',
             CURRENT_DATE,
             'SYSTEM',
             CURRENT_DATE,
             'SYSTEM'
         );




INSERT INTO INV_PRDUCT_HST(
    invest_id,
    product_id,
    user_id,
    invested_at,
    invest_amount,
    cancel_yn,
    created_at,
    created_user_id,
    updated_at,
    updated_user_id
) VALUES (
             SEQ_INV_PRDUCT_HST.NEXTVAL,
             'CRD00001',
             '2021050003',
             PARSEDATETIME ('2021-05-01 02:34:25','yyyy-MM-dd hh:mm:ss'),
             40000,
             'N',
             CURRENT_DATE,
             'SYSTEM',
             CURRENT_DATE,
             'SYSTEM'
         );




INSERT INTO INV_PRDUCT_HST(
    invest_id,
    product_id,
    user_id,
    invested_at,
    invest_amount,
    cancel_yn,
    created_at,
    created_user_id,
    updated_at,
    updated_user_id
) VALUES (
             SEQ_INV_PRDUCT_HST.NEXTVAL,
             'RES00002',
             '2021050004',
             PARSEDATETIME ('2021-04-04 20:12:47','yyyy-MM-dd hh:mm:ss'),
             100000000,
             'N',
             CURRENT_DATE,
             'SYSTEM',
             CURRENT_DATE,
             'SYSTEM'
         );



INSERT INTO INV_PRDUCT_HST(
    invest_id,
    product_id,
    user_id,
    invested_at,
    invest_amount,
    cancel_yn,
    created_at,
    created_user_id,
    updated_at,
    updated_user_id
) VALUES (
             SEQ_INV_PRDUCT_HST.NEXTVAL,
             'CRD00001',
             '2021050002',
             PARSEDATETIME ('2021-05-11 10:23:40','yyyy-MM-dd hh:mm:ss'),
             40000,
             'N',
             CURRENT_DATE,
             'SYSTEM',
             CURRENT_DATE,
             'SYSTEM'
         );


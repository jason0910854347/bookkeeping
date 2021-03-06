CREATE TABLE IF NOT EXISTS SYS_ACCOUNT (
	ACCOUNT_ID			BIGINT NOT NULL,		-- ID(流水號)
	USER_NAME			VARCHAR(100) ,			-- 使用者名稱
	ACCOUNT				VARCHAR(50)  NOT NULL,	-- 帳號
	PASSWORD 			VARCHAR(255) NOT NULL,	-- 密碼
	ROLE_ID				BIGINT NOT NULL,		-- 角色權限ID(流水號)
	CREATE_TIME			DATETIME NOT NULL,		-- 建立時間 
	PRIMARY KEY (ACCOUNT_ID), UNIQUE (ACCOUNT)
);

CREATE TABLE IF NOT EXISTS SYS_ROLE (
	ROLE_ID			BIGINT NOT NULL,			-- ID(流水號)
	ROLE_NAME		VARCHAR(50) NOT NULL,		-- role名稱
	REMARK			VARCHAR(100) NOT NULL,		-- 備註說明
	LEVEL	 		INT NOT NULL,				-- 權限等級
	PRIMARY KEY (ROLE_ID)
);

CREATE TABLE IF NOT EXISTS ACCOUNT_SUBJECT (
	SUBJECT_ID			INT NOT NULL,			-- 科目ID(流水號)
	SUBJECT_NAME		VARCHAR(100) NOT NULL,	-- 科目名稱
	ACCOUNT_ID			BIGINT NOT NULL,		-- 建立者ID(流水號)
	CREATE_TIME			DATETIME NOT NULL,		-- 建立時間
	PRIMARY KEY (SUBJECT_ID)
);

CREATE TABLE IF NOT EXISTS ACCOUNT_DATA (
	DATA_ID				BIGINT NOT NULL,		-- ID(流水號)
	SUBJECT_ID			BIGINT NOT NULL ,		-- 科目ID(流水號)
	AMOUNT				BIGINT NOT NULL ,		-- 金額
	ACCOUNT_ID			BIGINT NOT NULL,		-- 建立者ID(流水號)
	CREATE_TIME			DATETIME NOT NULL,		-- 建立時間
	PRIMARY KEY (DATA_ID)
);

CREATE TABLE IF NOT EXISTS SEQ_STORE
(
	SEQUENCE_NAME VARCHAR(255) NOT NULL PRIMARY KEY,
	NEXT_VAL BIGINT
);


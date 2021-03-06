DROP TABLE IF EXISTS T_ROLE;
CREATE TABLE T_ROLE
(
    ID          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    NAME        varchar(50) NOT NULL COMMENT '角色名称',
    CODE        varchar(50) NOT NULL COMMENT '角色编码',
    STATUS      int(1) NOT NULL DEFAULT 1 COMMENT '1:启用，0：禁用',
    REMARK        varchar(255)                  COMMENT '备注',
    CREATE_TIME datetime  NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    CREATE_BY   varchar(50) NOT NULL,
    UPDATE_TIME datetime ON UPDATE CURRENT_TIMESTAMP,
    UPDATE_BY   varchar(50) ,
    PRIMARY KEY (ID)
);


DROP TABLE IF EXISTS T_USER;
CREATE TABLE T_USER
(
    ID          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    USERNAME        varchar(50) NOT NULL COMMENT '用户名',
    PASSWORD        varchar(200) NOT NULL COMMENT '密码',
    STATUS      int(1) NOT NULL DEFAULT 1 COMMENT '1:启用，0：禁用',
    SEX           INT(1) NOT NULL DEFAULT 1 COMMENT '1:男性，0：女性',
    CREATE_TIME datetime  NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    CREATE_BY   varchar(50) NOT NULL,
    UPDATE_TIME datetime ON UPDATE CURRENT_TIMESTAMP,
    UPDATE_BY   varchar(50) ,
    PRIMARY KEY (ID)
);


DROP TABLE IF EXISTS T_PERMISSION;
CREATE TABLE T_PERMISSION
(
    ID          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    NAME        varchar(50) NOT NULL            COMMENT '权限名称',
    TYPE        VARCHAR(20) NOT NULL            COMMENT 'URL:接口地址，MENU：菜单，BUTTON：按钮，FIELD：字段',
    URL           varchar(255) NOT NULL         COMMENT '资源地址',
    STATUS      int(1) NOT NULL DEFAULT 1       COMMENT '1:启用，0：禁用',
    PARENT_ID          int(11)                  COMMENT '父ID，为空表示最顶层节点',
    REMARK        varchar(255)                  COMMENT '备注',
    CREATE_TIME datetime  NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    CREATE_BY   varchar(50) NOT NULL,
    UPDATE_TIME datetime ON UPDATE CURRENT_TIMESTAMP,
    UPDATE_BY   varchar(50) ,
    PRIMARY KEY (ID)
);


DROP TABLE IF EXISTS T_ROLE_PERMISSION_REL;
CREATE TABLE T_ROLE_PERMISSION_REL
(
    ID             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    ROLE_ID          int(11) NOT NULL,
    PERMISSION_ID   INT(11)  NOT NULL,
    CREATE_TIME datetime  NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    CREATE_BY   varchar(50) NOT NULL,
    PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS T_USER_ROLE_REL;
CREATE TABLE T_USER_ROLE_REL
(
    ID             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    USER_ID         int(11) NOT NULL,
    ROLE_ID         INT(11)  NOT NULL,
    CREATE_TIME datetime  NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    CREATE_BY   varchar(50) NOT NULL,
    PRIMARY KEY (ID)
);



DROP TABLE IF EXISTS T_OPERATION_LOG;
CREATE TABLE T_OPERATION_LOG
(
    ID             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    NAME         VARCHAR(50) NOT NULL,
    TYPE         VARCHAR(10)  NOT NULL,
    IS_SUCCESS   INT(1)       NOT NULL COMMENT '1:成功，0：失败',
    REQUEST_IP   VARCHAR(32)  NOT NULL,
    REQUEST_JSON TEXT,
    RESPONSE_JSON TEXT,
    TIME_COUNT  INT(6) NOT NULL ,
    ERROR_MSG TEXT,
    CREATE_TIME datetime  NOT NULL,
    CREATE_BY   varchar(50) NOT NULL,
    PRIMARY KEY (ID)
);
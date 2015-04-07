--附件表
CREATE SEQUENCE t_files_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE t_files(
  id bigint DEFAULT nextval('t_files_id_seq'),
  form_id bigint,
  file_name varchar(30),
  content bytea,
  owner varchar(30),
  ts timestamp with time zone,
  dob date,
  PRIMARY KEY (id)
);

--审批日志表
CREATE SEQUENCE form_approve_log_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE form_approve_log(
  id bigint DEFAULT nextval('form_approve_log_id_seq'),
  form_id bigint,
  approve_user varchar(30),
  approve_cname varchar(30),
  task_id varchar(100),
  task_name varchar(255),
  manager_type varchar(10),
  next_task_id varchar(100),
  next_task_name varchar(255),
  next_candidate text,
  memo text,
  owner varchar(100),
  owner_cname varchar(100),
  ts timestamp with time zone,
     dob date,
    PRIMARY KEY (id)
);

--表单修改历史日志表
CREATE SEQUENCE form_update_log_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE form_update_log(
  id bigint DEFAULT nextval('form_update_log_id_seq'),
  form_id bigint,
  form_item text,
  new_value text,
  old_value text,
  create_user varchar(30),
  ts timestamp with time zone,
     dob date,
  PRIMARY KEY (id)
);

--表单主表，表单名称对应表名
CREATE SEQUENCE form_appmain_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE form_appmain (
  id bigint DEFAULT nextval('form_appmain_id_seq'),
  appname varchar(500) NOT NULL,
  table_name varchar(200),
  state smallint NOT NULL,
  PRIMARY KEY (id)
);

--日常费用报销主表：
CREATE SEQUENCE formmain_0114_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formmain_0114 (
  id bigint DEFAULT nextval('formmain_0114_id_seq'),
  oid varchar(255),
  proc_inst_id varchar(64),
  proc_title varchar(255),
  state int DEFAULT NULL,
  start_member_id varchar(255) DEFAULT NULL,
  start_date timestamp with time zone DEFAULT NULL,
  approve_member_id varchar(255) DEFAULT NULL,
  approve_date timestamp with time zone DEFAULT NULL,
  finishedflag int DEFAULT NULL,
  ratifyflag int DEFAULT NULL,
  ratify_member_id varchar(255) DEFAULT NULL,
  ratify_date timestamp with time zone DEFAULT NULL,
  field0001 varchar(255) DEFAULT NULL,
  field0002 varchar(255) DEFAULT NULL,
  field0003 varchar(255) DEFAULT NULL,
  field0004 varchar(255) DEFAULT NULL,
  field0005 date DEFAULT NULL,
  field0006 text,
  field0007 varchar(255) DEFAULT NULL,
  field0008 varchar(255) DEFAULT NULL,
  field0009 varchar(255) DEFAULT NULL,
  field0010 bigint DEFAULT NULL,
  field0011 bigint DEFAULT NULL,
  field0012 text,
  field0013 varchar(255) DEFAULT NULL,
  field0014 date DEFAULT NULL,
  field0015 text,
  field0016 varchar(255) DEFAULT NULL,
  field0017 date DEFAULT NULL,
  field0018 text,
  field0019 varchar(255) DEFAULT NULL,
  field0020 date DEFAULT NULL,
  field0021 text,
  field0022 varchar(255) DEFAULT NULL,
  field0023 date DEFAULT NULL,
  field0024 text,
  field0025 varchar(255) DEFAULT NULL,
  field0026 date DEFAULT NULL,
  field0027 text,
  field0028 varchar(255) DEFAULT NULL,
  field0029 date DEFAULT NULL,
  field0030 bigint DEFAULT NULL,
  field0031 bigint DEFAULT NULL,
  field0032 bigint DEFAULT NULL,
  field0033 bigint DEFAULT NULL,
  field0063 varchar(255) DEFAULT NULL,
  field0064 varchar(255) DEFAULT NULL,
  field0065 bigint DEFAULT NULL,
  field0066 bigint DEFAULT NULL,
  field0067 bigint DEFAULT NULL,
  field0068 bigint DEFAULT NULL,
  field0069 bigint DEFAULT NULL,
  field0070 bigint DEFAULT NULL,
  field0071 text,
  field0072 varchar(255) DEFAULT NULL,
  field0073 date DEFAULT NULL,
  field0074 varchar(255) DEFAULT NULL,
  field0075 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  field0076 varchar(255) DEFAULT NULL,
  field0078 varchar(255) DEFAULT NULL,
  field0079 varchar(255) DEFAULT NULL,
  field0080 varchar(255) DEFAULT NULL,
  field0081 date DEFAULT NULL,
  field0082 varchar(255) DEFAULT NULL,
  field0083 varchar(255) DEFAULT NULL,
  field0084 date DEFAULT NULL,
  field0085 bigint DEFAULT NULL,
  field0086 bigint DEFAULT NULL,
  field0087 text,
  field0090 varchar(255) DEFAULT NULL,
  field0091 varchar(255) DEFAULT NULL,
  field0092 date DEFAULT NULL,
  field0093 varchar(255) DEFAULT NULL,
  field0096 varchar(255) DEFAULT NULL,
  field0097 varchar(255) DEFAULT NULL,
  field0099 bigint DEFAULT NULL,
  field0100 varchar(255) DEFAULT NULL,
  field0101 bigint DEFAULT NULL,
  field0102 varchar(255) DEFAULT NULL,
  field0103 varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

--日常费用报销主表_历史：
CREATE SEQUENCE formmain_0114_history_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formmain_0114_history(
  id bigint DEFAULT nextval('formmain_0114_history_id_seq'),
  oid varchar(255),
  proc_inst_id varchar(64),
  proc_title varchar(255),
  state int DEFAULT NULL,
  start_member_id varchar(255) DEFAULT NULL,
  start_date timestamp with time zone DEFAULT NULL,
  approve_member_id varchar(255) DEFAULT NULL,
  approve_date timestamp with time zone DEFAULT NULL,
  finishedflag int DEFAULT NULL,
  ratifyflag int DEFAULT NULL,
  ratify_member_id varchar(255) DEFAULT NULL,
  ratify_date timestamp with time zone DEFAULT NULL,
  field0001 varchar(255) DEFAULT NULL,
  field0002 varchar(255) DEFAULT NULL,
  field0003 varchar(255) DEFAULT NULL,
  field0004 varchar(255) DEFAULT NULL,
  field0005 date DEFAULT NULL,
  field0006 text,
  field0007 varchar(255) DEFAULT NULL,
  field0008 varchar(255) DEFAULT NULL,
  field0009 varchar(255) DEFAULT NULL,
  field0010 bigint DEFAULT NULL,
  field0011 bigint DEFAULT NULL,
  field0012 text,
  field0013 varchar(255) DEFAULT NULL,
  field0014 date DEFAULT NULL,
  field0015 text,
  field0016 varchar(255) DEFAULT NULL,
  field0017 date DEFAULT NULL,
  field0018 text,
  field0019 varchar(255) DEFAULT NULL,
  field0020 date DEFAULT NULL,
  field0021 text,
  field0022 varchar(255) DEFAULT NULL,
  field0023 date DEFAULT NULL,
  field0024 text,
  field0025 varchar(255) DEFAULT NULL,
  field0026 date DEFAULT NULL,
  field0027 text,
  field0028 varchar(255) DEFAULT NULL,
  field0029 date DEFAULT NULL,
  field0030 bigint DEFAULT NULL,
  field0031 bigint DEFAULT NULL,
  field0032 bigint DEFAULT NULL,
  field0033 bigint DEFAULT NULL,
  field0063 varchar(255) DEFAULT NULL,
  field0064 varchar(255) DEFAULT NULL,
  field0065 bigint DEFAULT NULL,
  field0066 bigint DEFAULT NULL,
  field0067 bigint DEFAULT NULL,
  field0068 bigint DEFAULT NULL,
  field0069 bigint DEFAULT NULL,
  field0070 bigint DEFAULT NULL,
  field0071 text,
  field0072 varchar(255) DEFAULT NULL,
  field0073 date DEFAULT NULL,
  field0074 varchar(255) DEFAULT NULL,
  field0075 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  field0076 varchar(255) DEFAULT NULL,
  field0078 varchar(255) DEFAULT NULL,
  field0079 varchar(255) DEFAULT NULL,
  field0080 varchar(255) DEFAULT NULL,
  field0081 date DEFAULT NULL,
  field0082 varchar(255) DEFAULT NULL,
  field0083 varchar(255) DEFAULT NULL,
  field0084 date DEFAULT NULL,
  field0085 bigint DEFAULT NULL,
  field0086 bigint DEFAULT NULL,
  field0087 text,
  field0090 varchar(255) DEFAULT NULL,
  field0091 varchar(255) DEFAULT NULL,
  field0092 date DEFAULT NULL,
  field0093 varchar(255) DEFAULT NULL,
  field0096 varchar(255) DEFAULT NULL,
  field0097 varchar(255) DEFAULT NULL,
  field0099 bigint DEFAULT NULL,
  field0100 varchar(255) DEFAULT NULL,
  field0101 bigint DEFAULT NULL,
  field0102 varchar(255) DEFAULT NULL,
  field0103 varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

--日常费用报销子表：
--加班餐费：
CREATE SEQUENCE formson_0115_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0115 (
  id bigint DEFAULT nextval('formson_0115_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0034 date DEFAULT NULL,
  field0035 text,
  field0036 bigint DEFAULT NULL,
  field0037 bigint DEFAULT NULL,
  field0038 bigint DEFAULT NULL,
  field0039 varchar(255) DEFAULT NULL,
  field0040 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  field0077 varchar(255) DEFAULT NULL,
  field0094 decimal(20,2) DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

--招待费：
CREATE SEQUENCE formson_0116_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0116 (
  id bigint DEFAULT nextval('formson_0116_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0041 date DEFAULT NULL,
  field0042 varchar(255) DEFAULT NULL,
  field0043 bigint DEFAULT NULL,
  field0044 varchar(255) DEFAULT NULL,
  field0045 varchar(255) DEFAULT NULL,
  field0046 varchar(255) DEFAULT NULL,
  field0047 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  memo text,
  PRIMARY KEY (id)
);

--其他费用：
CREATE SEQUENCE formson_0117_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0117 (
  id bigint DEFAULT nextval('formson_0117_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0048 varchar(255) DEFAULT NULL,
  field0049 bigint DEFAULT NULL,
  field0050 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

--员工关系费：
CREATE SEQUENCE formson_0118_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0118 (
  id bigint DEFAULT nextval('formson_0118_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0051 bigint DEFAULT NULL,
  field0052 varchar(255) DEFAULT NULL,
  field0053 date DEFAULT NULL,
  field0054 varchar(255) DEFAULT NULL,
  field0055 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  field0089 text,
  ratify bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

--出租车费：
CREATE SEQUENCE formson_0119_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0119 (
  id bigint DEFAULT nextval('formson_0119_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0056 varchar(255) DEFAULT NULL,
  field0057 bigint DEFAULT NULL,
  field0058 varchar(255) DEFAULT NULL,
  field0059 varchar(255) DEFAULT NULL,
  field0060 varchar(255) DEFAULT NULL,
  field0061 varchar(255) DEFAULT NULL,
  field0062 date DEFAULT NULL,
  sort int DEFAULT NULL,
  field0088 varchar(255) DEFAULT NULL,
  field0095 decimal(20,2) DEFAULT NULL,
  field0098 varchar(255) DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

--日常费用报销子表_历史数据：
--加班餐费：
CREATE SEQUENCE formson_0115_history_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0115_history(
 id bigint DEFAULT nextval('formson_0115_history_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0034 date DEFAULT NULL,
  field0035 text,
  field0036 bigint DEFAULT NULL,
  field0037 bigint DEFAULT NULL,
  field0038 bigint DEFAULT NULL,
  field0039 varchar(255) DEFAULT NULL,
  field0040 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  field0077 varchar(255) DEFAULT NULL,
  field0094 decimal(20,2) DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

--招待费：
CREATE SEQUENCE formson_0116_history_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0116_history (
  id bigint DEFAULT nextval('formson_0116_history_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0041 date DEFAULT NULL,
  field0042 varchar(255) DEFAULT NULL,
  field0043 bigint DEFAULT NULL,
  field0044 varchar(255) DEFAULT NULL,
  field0045 varchar(255) DEFAULT NULL,
  field0046 varchar(255) DEFAULT NULL,
  field0047 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  ratify bigint DEFAULT NULL,
   memo text,
  PRIMARY KEY (id)
);

--其他费用：
CREATE SEQUENCE formson_0117_history_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0117_history (
  id bigint DEFAULT nextval('formson_0117_history_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0048 varchar(255) DEFAULT NULL,
  field0049 bigint DEFAULT NULL,
  field0050 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

--员工关系费：
CREATE SEQUENCE formson_0118_history_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0118_history (
  id bigint DEFAULT nextval('formson_0118_history_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0051 bigint DEFAULT NULL,
  field0052 varchar(255) DEFAULT NULL,
  field0053 date DEFAULT NULL,
  field0054 varchar(255) DEFAULT NULL,
  field0055 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  field0089 text,
  ratify bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

--出租车费：
CREATE SEQUENCE formson_0119_history_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0119_history (
   id bigint DEFAULT nextval('formson_0119_history_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0056 varchar(255) DEFAULT NULL,
  field0057 bigint DEFAULT NULL,
  field0058 varchar(255) DEFAULT NULL,
  field0059 varchar(255) DEFAULT NULL,
  field0060 varchar(255) DEFAULT NULL,
  field0061 varchar(255) DEFAULT NULL,
  field0062 date DEFAULT NULL,
  sort int DEFAULT NULL,
  field0088 varchar(255) DEFAULT NULL,
  field0095 decimal(20,2) DEFAULT NULL,
  field0098 varchar(255) DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  PRIMARY KEY (id)
);

--日常费用报销子表_日志数据：
--加班餐费：
CREATE SEQUENCE formson_0115_log_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0115_log(
  id bigint DEFAULT nextval('formson_0115_log_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0034 date DEFAULT NULL,
  field0035 text,
  field0036 bigint DEFAULT NULL,
  field0037 bigint DEFAULT NULL,
  field0038 bigint DEFAULT NULL,
  field0039 varchar(255) DEFAULT NULL,
  field0040 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  field0077 varchar(255) DEFAULT NULL,
  field0094 decimal(20,2) DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  ts timestamp with time zone DEFAULT NULL,
  dob date,
  PRIMARY KEY (id)
);

--招待费：
CREATE SEQUENCE formson_0116_log_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0116_log(
  id bigint DEFAULT nextval('formson_0116_log_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0041 date DEFAULT NULL,
  field0042 varchar(255) DEFAULT NULL,
  field0043 bigint DEFAULT NULL,
  field0044 varchar(255) DEFAULT NULL,
  field0045 varchar(255) DEFAULT NULL,
  field0046 varchar(255) DEFAULT NULL,
  field0047 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  ts timestamp with time zone DEFAULT NULL,
  dob date,
  PRIMARY KEY (id)
);

--其他费用：
CREATE SEQUENCE formson_0117_log_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0117_log(
  id bigint DEFAULT nextval('formson_0117_log_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0048 varchar(255) DEFAULT NULL,
  field0049 bigint DEFAULT NULL,
  field0050 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  ts timestamp with time zone DEFAULT NULL,
  dob date,
  PRIMARY KEY (id)
);

--员工关系费：
CREATE SEQUENCE formson_0118_log_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0118_log(
  id bigint DEFAULT nextval('formson_0118_log_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0051 bigint DEFAULT NULL,
  field0052 varchar(255) DEFAULT NULL,
  field0053 date DEFAULT NULL,
  field0054 varchar(255) DEFAULT NULL,
  field0055 varchar(255) DEFAULT NULL,
  sort int DEFAULT NULL,
  field0089 text,
  ratify bigint DEFAULT NULL,
  ts timestamp with time zone DEFAULT NULL,
  dob date,
  PRIMARY KEY (id)
);

--出租车费：
CREATE SEQUENCE formson_0119_log_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE formson_0119_log(
  id bigint DEFAULT nextval('formson_0119_log_id_seq'),
  formmain_0114Id bigint DEFAULT NULL,
  field0056 varchar(255) DEFAULT NULL,
  field0057 bigint DEFAULT NULL,
  field0058 varchar(255) DEFAULT NULL,
  field0059 varchar(255) DEFAULT NULL,
  field0060 varchar(255) DEFAULT NULL,
  field0061 varchar(255) DEFAULT NULL,
  field0062 date DEFAULT NULL,
  sort int DEFAULT NULL,
  field0088 varchar(255) DEFAULT NULL,
  field0095 decimal(20,2) DEFAULT NULL,
  field0098 varchar(255) DEFAULT NULL,
  ratify bigint DEFAULT NULL,
  ts timestamp with time zone DEFAULT NULL,
  dob date,
  PRIMARY KEY (id)
);

--代理表
CREATE SEQUENCE t_delegation_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE t_delegation
(
  id bigint DEFAULT nextval('t_delegation_id_seq'),
  master_user_id character varying(50),
  agent_user_id character varying(50),
  ts time with time zone,
  is_delete boolean
);

  
--组表
CREATE SEQUENCE t_group_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE t_group
(
  id bigint DEFAULT nextval('t_group_id_seq'),
  group_key character varying(50),
  group_name character varying(50),
  ts time with time zone
);

--组员表
CREATE SEQUENCE t_group_member_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
CREATE TABLE t_group_member
(
  id bigint DEFAULT nextval('t_group_member_id_seq'),
  group_key character varying(50),
  member_user_id character varying(50),
  ts time with time zone
);



--索引
create index idx_form_id on form_approve_log (form_id);
create index idx_form_approve_owner on form_approve_log (form_id, approve_user, owner);
create index idx_14proc on formmain_0114 (proc_inst_id);
create index idx_14hproc on formmain_0114_history (oid);
create index idx_15form_id on formson_0115 (formmain_0114id);
create index idx_15hform_id on formson_0115_history (formmain_0114id);
create index idx_16form_id on formson_0116 (formmain_0114id);
create index idx_16hform_id on formson_0116_history (formmain_0114id);
create index idx_17form_id on formson_0117 (formmain_0114id);
create index idx_17hform_id on formson_0117_history (formmain_0114id);
create index idx_18form_id on formson_0118 (formmain_0114id);
create index idx_18hform_id on formson_0118_history (formmain_0114id);
create index idx_19form_id on formson_0119 (formmain_0114id);
create index idx_19hform_id on formson_0119_history (formmain_0114id);
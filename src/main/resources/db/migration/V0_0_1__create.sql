-- 员工
CREATE TABLE IF NOT EXISTS t_emp_info (
    emp_id  TEXT NOT NULL PRIMARY KEY ASC,                      -- 员工ID（主键，不能重复）
    group_id TEXT NOT NULL,                                     -- 抽奖组ID
    emp_name TEXT NOT NULL,                                     -- 员工姓名
    emp_sex TEXT NOT NULL,                                      -- 员工性别
    emp_date TEXT NOT NULL,                                     -- 入职日期
    dept_id TEXT NOT NULL,                                      -- 员工所在部门ID
    emp_rate INTEGER,                                           -- 中奖权值（越大概率越高）
    prize_flag TEXT NOT NULL                                    -- 中奖标识（0为未中奖，1为已中奖，9为已放弃）
);

-- 奖项表
CREATE TABLE IF NOT EXISTS t_prize_info (
    prize_id TEXT NOT NULL PRIMARY KEY ASC,                     -- 奖项ID
    prize_type TEXT NOT NULL,                                   -- 奖项类型（0为现金奖，1为实物奖）
    prize_name TEXT NOT NULL,                                   -- 奖项名称
    prize_desc TEXT NOT NULL,                                   -- 奖项描述
    prize_order INTEGER NOT NULL,                               -- 抽选顺序
    prize_multi TEXT NOT NULL,                                  -- 是否允许重复中奖（0为不允许，1为允许）
    prize_status TEXT NOT NULL                                  -- 抽选状态
);

-- 奖项表
CREATE TABLE IF NOT EXISTS t_prize_group_info (
    prize_id TEXT NOT NULL,                                     -- 奖项ID
    group_id TEXT NOT NULL,                                     -- 抽奖组ID（000000为不限定抽奖组）
    prize_number INTEGER NOT NULL,                              -- 奖项数量
    prize_winner INTEGER NOT NULL,                              -- 中奖人数
    CONSTRAINT pk_prize_group_info UNIQUE (prize_id, group_id)  -- 唯一约束（同一奖项一个抽奖组只能出现一次）
);

-- 中奖表
CREATE TABLE IF NOT EXISTS t_win_info (
    emp_id TEXT NOT NULL,                                       -- 员工ID
    prize_id TEXT NOT NULL,                                     -- 奖项ID
    win_time TEXT,                                              -- 中奖时间
    CONSTRAINT pk_win_info UNIQUE (emp_id, prize_id)            -- 唯一约束（同一奖项只能中一次）
);

-- 部门表
CREATE TABLE IF NOT EXISTS t_dept_info (
    dept_id TEXT NOT NULL PRIMARY KEY ASC,                      -- 部门ID（主键，不能重复）
    dept_name TEXT NOT NULL,                                    -- 部门名称
    branch_id TEXT NOT NULL                                     -- 分处ID
);

-- 系统表
CREATE TABLE IF NOT EXISTS t_sys_info (
    sys_key TEXT NOT NULL PRIMARY KEY ASC,                      -- 系统设定键值（主键，不能重复）
    sys_value TEXT NOT NULL                                     -- 系统设定内容
);

------------------------------------------
-- 初始化部门数据
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1000', '第一事业单位', 'IC0000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1001', '商务中心', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1010', '产品全球化交付中心', 'IC1200');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1020', '高科技业务部', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1021', '高科技业务一课', 'IC1020');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1022', '高科技业务二课', 'IC1020');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1023', '高科技业务三课', 'IC1020');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1030', '高科技业务三部', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1031', '高科技业务一课', 'IC1030');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1032', '高科技业务二课', 'IC1030');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1040', '新业务开发部', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1041', '高科技业务一课', 'IC1040');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1042', '高科技业务二课', 'IC1040');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1043', '互联网业务课', 'IC1040');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1050', '大连交付中心', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC10C0', '共享服务中心', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC10C1', '人资服务课', 'IC10C0');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC10C2', '行政服务课', 'IC10C0');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC10C3', '信息服务课', 'IC10C0');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1100', '大连开发中心', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1110', 'ITO业务部', 'IC1100');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1111', 'ITO开发课', 'IC1110');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1112', '技术课', 'IC1110');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1113', '技术支持课', 'IC1110');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1120', '高科技业务一部', 'IC1100');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1130', 'BPO业务部', 'IC1100');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1131', 'BPO业务一课', 'IC1130');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1132', 'BPO业务二课', 'IC1130');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1140', '产品全球化交付中心', 'IC1100');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1200', '综合业务处', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1210', '大连BPO中心', 'IC1200');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1211', 'BPO交付一课', 'IC1210');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1212', 'BPO交付二课', 'IC1210');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1220', '业务一部', 'IC1200');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1230', '综合业务三部', 'IC1200');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1231', '高科技業務一課', 'IC1230');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1232', '高科技业务二课', 'IC1230');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1240', '综合业务一部', 'IC1200');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1241', '电信业务部', 'IC1240');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1242', '高科技业务课', 'IC1240');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1250', '综合业务二部', 'IC1200');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1251', '金融业务课', 'IC1250');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1252', '互联网业务课', 'IC1250');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1253', 'BPO业务课', 'IC1250');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1254', '综合业务课', 'IC1250');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1300', '国际业务处', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1310', '業務部', 'IC1300');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('IC1999', 'WIST BU1', 'IC1000');
INSERT INTO t_dept_info (dept_id, dept_name, branch_id) VALUES ('TF0160', '經營分析二部', 'TF0100');

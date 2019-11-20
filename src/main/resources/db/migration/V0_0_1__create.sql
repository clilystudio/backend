-- 员工
CREATE TABLE IF NOT EXISTS t_emp_info (
    emp_id  TEXT NOT NULL PRIMARY KEY ASC,                      -- 员工ID（主键，不能重复）
    emp_name TEXT NOT NULL,                                     -- 员工姓名
    dept_id TEXT NOT NULL,                                      -- 员工所在部门ID
    emp_rate INTEGER,                                           -- 中奖权值（越大概率越高）
    prize_flag TEXT NOT NULL                                    -- 中奖标识（0为未中奖，1为已中奖，9为已放弃）
);

-- 奖项表
CREATE TABLE IF NOT EXISTS t_prize_info (
    prize_id TEXT NOT NULL PRIMARY KEY ASC,                     -- 奖项ID（主键，不能重复）
    prize_name TEXT NOT NULL,                                   -- 奖项名称
    prize_desc TEXT NOT NULL,                                   -- 奖项描述
    prize_order INTEGER NOT NULL,                               -- 抽选顺序
    prize_number INTEGER NOT NULL,                              -- 奖品数量
    prize_winner INTEGER NOT NULL,                              -- 中奖人数
    dept_id TEXT,                                               -- 限定部门ID（为空时，不限定）
    prize_multi TEXT NOT NULL,                                  -- 是否允许重复中奖（0为不允许，1为允许）
    prize_status TEXT NOT NULL                                  -- 奖项状态
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
    dept_name TEXT NOT NULL                                     -- 部门名称
);

-- 系统表
CREATE TABLE IF NOT EXISTS t_sys_info (
    sys_key TEXT NOT NULL PRIMARY KEY ASC,                      -- 系统设定键值（主键，不能重复）
    sys_value TEXT NOT NULL                                     -- 系统设定内容
);

------------------------------------------
-- 初始化部门数据
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('DC1032', '高科技业务二课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('DC111', '开发一课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('FC10C0', '共享服务中心');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('FC10C1', '人资服务课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('FC10C2', '行政服务课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('FC10C3', '资讯服务课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1000', '第一事业单位');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1001', '商务中心');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1010', '产品全球化交付中心');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1020', '高科技业务二部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1021', '高科技业务一课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1022', '高科技业务二课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1030', '高科技业务三部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1031', '高科技业务一课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1032', '高科技业务二课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1040', '新业务开发部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1041', '高科技业务一课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1042', '高科技业务二课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1043', '互联网业务课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1050', '大连交付中心');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1100', '业务一处');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1110', '大连开发中心');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1111', '开发一课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1112', '开发二课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1113', '技术支持课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1120', '高科技业务一部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1200', '业务二处');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1210', '大连BPO中心');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1211', 'BPO交付一课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1212', 'BPO交付二课');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1220', '业务一部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC1230', '业务二部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('IC2001', '驻场');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('WF008', '大连出纳');

-- 员工
CREATE TABLE IF NOT EXISTS t_emp_info (
    emp_id  TEXT NOT NULL PRIMARY KEY ASC,                      -- 员工ID（主键，不能重复）
    emp_name TEXT NOT NULL,                                     -- 员工姓名
    dept_id TEXT NOT NULL,                                      -- 员工所在部门ID
    emp_rate INTEGER,                                           -- 中奖权值（越大概率越高）
    prize_flag INTEGER                                          -- 中奖标识（0为没中奖，1为已中奖，-1为已放弃）
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
    prize_multi INTEGER NOT NULL,                               -- 是否允许重复中奖（0为不允许，1为允许）
    prize_status INTEGER NOT NULL,                              -- 奖项状态（0为待抽选，1为抽选中，2为抽选结束）
    prize_person INTEGER NOT NULL                               -- 抽选人数
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
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('ID010', '事业一部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('ID020', '事业二部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('ID030', '事业三部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('ID050', '业务发展支持部');
INSERT INTO t_dept_info (dept_id, dept_name) VALUES ('WD010', '行政管理部');

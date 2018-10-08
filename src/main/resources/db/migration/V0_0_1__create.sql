-- 员工
CREATE TABLE IF NOT EXISTS t_emp_info (
    emp_id  TEXT NOT NULL PRIMARY KEY ASC,          -- 员工ID（主键，不能重复）
    emp_cname TEXT NOT NULL,                        -- 员工姓名
    emp_fname TEXT NOT NULL,                        -- 员工名拼写
    emp_lname TEXT NOT NULL,                        -- 员工姓拼写
    emp_deptno TEXT NOT NULL,                       -- 员工所在部门编号
    emp_deptname TEXT NOT NULL,                     -- 员工所在部门名称
    emp_rate INTEGER,                               -- 中奖权值（越大概率越高）
    prize_flag INTEGER                              -- 中奖标识（0为没中奖，1为已中奖，-1为已放弃）
);

-- 奖项表
CREATE TABLE IF NOT EXISTS t_prize_info (
    prize_id TEXT NOT NULL PRIMARY KEY ASC,         -- 奖项ID（主键，不能重复）
    prize_name TEXT NOT NULL,                       -- 奖项名称
    prize_desc TEXT NOT NULL,                       -- 奖项描述
    prize_number INTEGER NOT NULL,                  -- 奖品数量
    prize_winner INTEGER NOT NULL,                  -- 中奖人数
    emp_deptno TEXT,                                -- 限定部门编号（为空时，不限定）
    prize_multi INTEGER NOT NULL                    -- 是否允许重复中奖（0为不允许，1为允许）
);

-- 中奖表
CREATE TABLE IF NOT EXISTS t_win_info (
    emp_id TEXT NOT NULL,                           -- 员工ID
    prize_id TEXT NOT NULL,                         -- 奖项ID
    update_time TEXT                                -- 更新时间
);

-- 系统表
CREATE TABLE IF NOT EXISTS t_sys_info (
    sys_key TEXT NOT NULL PRIMARY KEY ASC,          -- 系统设定键值（主键，不能重复）
    sys_value TEXT NOT NULL                         -- 系统设定内容
);

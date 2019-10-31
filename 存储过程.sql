CREATE PROCEDURE Alter_ADetail(IN A_id char(20),IN S_id char(20),IN Situation_ varchar(10))
begin
		UPDATE ADetail set Situation = Situation_
		where Sid = S_id and Aid = A_id;
end;
--修改出勤情况的存储过程
call ALTER_ADetail('1','2','迟到')

CREATE PROCEDURE Alter_StudentName(IN S_id char(20),IN S_name varchar(15))
begin
		UPDATE Student set Sname = S_name
		where Sid = S_id
end;
--修改学生名字的存储过程

CREATE PROCEDURE Alter_StudentPasswd(IN S_id char(20),IN S_passwd char(20))
begin
		UPDATE Student set Spassword = S_passwd
		where Sid = S_id
end;
--修改学生密码的存储过程

CREATE PROCEDURE Alter_TeacherName(IN T_id char(20),IN T_name varchar(30))
begin
		UPDATE Teacher set Tname = T_name
		where Tid = T_id
end;
--修改老师名字的存储过程


CREATE PROCEDURE Alter_TeacherPasswd(IN T_id char(20),IN T_passwd char(20))
begin
		UPDATE Student set Tpassword = T_passwd
		where Tid = T_id
end;
--修改老师密码的存储过程

CREATE PROCEDURE Alter_ClassName(IN C_id char(20),IN C_name varchar(30))
begin
		UPDATE Class set Cname = C_name
		where Cid = C_id
end;
--修改班级名字的存储过程

CREATE PROCEDURE Insert_Student(IN S_id char(20),IN S_pwd char(20),IN S_name varchar(15))
begin
		INSERT INTO Student(Sid,Spassword,Sname)
		VALUES(S_id,S_pwd,S_name)
end;
--插入学生信息的存储过程

CREATE PROCEDURE Insert_Teacher(IN T_id char(20),IN T_pwd char(20),IN T_name varchar(30))
begin
		INSERT INTO Teacher(Tid,Tpassword,Tname)
		VALUES(T_id,T_pwd,T_name)
end;
--插入老师信息的存储过程

CREATE PROCEDURE Insert_Class(IN C_id char(20),IN C_name varchar(30),IN T_id char(20))
begin
		INSERT INTO Class(Cid,Cname,Tid)
		VALUES(C_id,C_name,T_id)
end;
--插入老师信息的存储过程

CREATE PROCEDURE Insert_Class(IN C_id char(20),IN C_name varchar(30),IN T_id char(20))
begin
		INSERT INTO Class(Cid,Cname,Tid)
		VALUES(C_id,C_name,T_id)
end;
--插入班级的存储过程

CREATE PROCEDURE Insert_CDetail(IN C_id char(10),IN S_id varchar(20))
begin
		INSERT INTO CDetail(Cid,Sid)
		VALUES(C_id,S_id)
end;
--插入班级详情的存储过程


CREATE PROCEDURE Insert_Attendance(IN A_id char(10),IN A_type varchar(20),A_time date,C_id char(20))
begin
		INSERT INTO Attendance(Aid,Atype,Atime,Cid)
		VALUES(A_id,A_type,A_time,C_id)
end;
--插入考勤的存储过程

CREATE PROCEDURE Insert_ADetail(IN A_id char(10),IN S_id
char(20),Situation_ varchar(10))
begin
		INSERT INTO ADetail(Aid,Sid,Situation)
		VALUES(A_id,S_id,Situation_)
end;
--插入考勤详情的存储过程

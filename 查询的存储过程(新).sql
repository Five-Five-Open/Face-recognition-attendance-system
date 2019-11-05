CREATE PROCEDURE Select_Student(IN S_id char(20))
begin
		SELECT * from Student
		where Sid = S_id;
end;
call Select_Student('1')

--查询学生个人信息的存储过程

CREATE PROCEDURE Select_StuClass(IN S_id char(20))
begin
		SELECT Cname ,(SELECT count(Cid) from CDetail where CDetail.Cid = Class.Cid) 'people_number'
		from Class,CDetail 
		where S_id = CDetail.Sid and CDetail.Cid = Class.Cid;
end;
drop PROCEDURE Select_StuClass
call Select_StuClass('1')
--查询学生拥有的班级

CREATE PROCEDURE Select_Teacher(IN T_id char(20))
begin
		SELECT * from Teacher
		where Tid = T_id;
end;
call Select_Teacher('110')
--查询老师个人信息的存储过程

CREATE PROCEDURE Select_ThClass(IN T_id char(20))
begin
		select Cname ,(select count(Cid) from CDetail where CDetail.Cid = Class.Cid and Class.Tid = T_id) 'people_number'
		from Class
		where Tid = T_id;
end;
drop PROCEDURE Select_ThClass
call Select_ThClass('110')
--查询老师拥有的班级

CREATE PROCEDURE Select_CSAttend(IN S_id char(20),IN C_id char(10))
begin
		select Atype,Atime,
		(select Situation from ADetail where Sid = S_id and ADetail.Aid = Attendance.Aid and Cid = C_id) 'Situation' 
		from Attendance where Cid = C_id;
end;
call Select_CSAttend('2','AAA111')
--查询x班x学生的所有考勤情况

CREATE PROCEDURE Select_chuqin(IN S_id char(20),IN C_id char(10))
begin
		select count(Situation) 'chuqin'
		from ADetail,Attendance 
		where Situation = '出勤' and Sid = S_id and Cid = C_id and ADetail.Aid = Attendance.Aid;
end;
drop PROCEDURE Select_chuqin
call Select_chuqin('2','AAA111')
--查询x班x学生的出勤次数


CREATE PROCEDURE Select_chidao(IN S_id char(20),IN C_id char(10))
begin
		select count(Situation) 'chidao'
		from ADetail,Attendance 
		where Situation = '迟到' and Sid = S_id and Cid = C_id and ADetail.Aid = Attendance.Aid;
end;
drop PROCEDURE Select_chuqin
call Select_chidao('2','AAA111')
--查询x班x学生的迟到次数


CREATE PROCEDURE Select_zaotui(IN S_id char(20),IN C_id char(10))
begin
		select count(Situation) 'zaotui'
		from ADetail,Attendance 
		where Situation = '早退' and Sid = S_id and Cid = C_id and ADetail.Aid = Attendance.Aid;
end;
drop PROCEDURE Select_zaotui
call Select_zaotui('2','AAA111')
--查询x班x学生的早退次数


CREATE PROCEDURE Select_kuangke(IN S_id char(20),IN C_id char(10))
begin
		select count(Situation) 'kuangke'
		from ADetail,Attendance 
		where Situation = '旷课' and Sid = S_id and Cid = C_id and ADetail.Aid = Attendance.Aid;
end;
drop PROCEDURE Select_kuangke
call Select_kuangke('2','AAA111')
--查询x班x学生的旷课次数



CREATE PROCEDURE Select_qingjia(IN S_id char(20),IN C_id char(10))
begin
		select count(Situation) 'qingjia'
		from ADetail,Attendance 
		where Situation = '请假' and Sid = S_id and Cid = C_id and ADetail.Aid = Attendance.Aid;
end;
drop PROCEDURE Select_qingjia
call Select_qingjia('2','AAA111')
--查询x班x学生的请假次数


--知道Aid
CREATE PROCEDURE Select_ADetail(IN A_id int)
begin
		select Sid,
		(select Sname from Student where Student.Sid = ADetail.Sid) 'Sname',Situation
		from ADetail where Aid = A_id;
end
call Select_ADetail(5)
--查询x次考勤的情况
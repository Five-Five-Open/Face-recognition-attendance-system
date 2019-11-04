CREATE PROCEDURE Select_Student(IN S_id char(20))
begin
		SELECT * from Student
		where Sid = S_id;
end;
call Select_Student('1')

--查询学生个人信息的存储过程

CREATE PROCEDURE Select_StuClass(IN S_id char(20))
begin
		SELECT Cname ,(SELECT sum(Cid) from CDetail where S_id = CDetail.Sid and CDetail.Cid = Class.Cid) '人数'
		from Class,CDetail 
		where S_id = CDetail.Sid and CDetail.Cid = Class.Cid;
end;
drop PROCEDURE Select_StuClass
call Select_StuClass('1')
--查询学生拥有的班级



SELECT * from Attendance where Cid = 'AAA111' ORDER BY Atime DESC 
SELECT Situation from ADetail,Attendance where Cid='AAA111' and Sid = '1'

select Aid from Attendance where Cid = 'AAA111'

SELECT Situation,(select Aid from Attendance where Cid = 'AAA111') x from ADetail where Aid = x.Aid and Sid = '1'


CREATE PROCEDURE Select_Teacher(IN T_id char(20))
begin
		SELECT * from Teacher
		where Tid = T_id;
end;
call Select_Teacher('1')
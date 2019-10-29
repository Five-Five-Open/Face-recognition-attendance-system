CREATE TRIGGER Attendance_Delete
BEFORE DELETE
on Attendance
for each row
BEGIN
		DELETE FROM ADetail where Aid = old.Aid
end;
--删除考勤表的同时删除考勤详情表触发器

CREATE TRIGGER Class_Delete
before DELETE
on Class
for each row
begin
		DELETE FROM CDetail where Cid = old.Cid
		DELETE FROM Attendance where Cid = old.Cid
end;
--删除班级同时删除班级详情表触发器


CREATE PROCEDURE Alter_ADetail(IN A_id char(20),IN S_id char(20),IN Situation_ varchar(10))
begin
		UPDATE ADetail set Situation = Situation_
		where Sid = S_id and Aid = A_id;
end;
--修改出勤情况的存储过程
call ALTER_ADetail('1','2','迟到')


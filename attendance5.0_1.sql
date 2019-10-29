CREATE TABLE `Student` (
`Sid` char(20) NOT NULL,
`Spassword` char(20) NOT NULL,
`Sname` varchar(15) NOT NULL,
PRIMARY KEY (`Sid`) 
);
CREATE TABLE `Teacher` (
`Tid` char(20) NOT NULL,
`Tpassword` char(20) NOT NULL,
`Tname` varchar(30) NOT NULL,
PRIMARY KEY (`Tid`) 
);
CREATE TABLE `Class` (
`Cid` char(10) NOT NULL,
`Cname` varchar(30) NOT NULL,
`Tid` char(20) NOT NULL,
PRIMARY KEY (`Cid`) 
);
CREATE TABLE `Attendance` (
`Aid` char(20) NOT NULL,
`Atype` char(20) NOT NULL,
`Atime` date NOT NULL,
`Cid` char(20) NOT NULL,
PRIMARY KEY (`Aid`) 
);
CREATE TABLE `ADetail` (
`ADid` int NOT NULL AUTO_INCREMENT,
`Aid` char(20) NOT NULL,
`Sid` char(20) NOT NULL,
`Situation` varchar(10) NOT NULL DEFAULT '旷课',
PRIMARY KEY (`ADid`) 
);
CREATE TABLE `CDetail` (
`CDid` int NOT NULL AUTO_INCREMENT,
`Cid` char(10) NOT NULL,
`Sid` char(20) NOT NULL,
PRIMARY KEY (`CDid`) 
);

ALTER TABLE `Class` ADD CONSTRAINT `Tid_C` FOREIGN KEY (`Tid`) REFERENCES `Teacher` (`Tid`);
ALTER TABLE `Attendance` ADD CONSTRAINT `Cid_A` FOREIGN KEY (`Cid`) REFERENCES `Class` (`Cid`);
ALTER TABLE `ADetail` ADD CONSTRAINT `Aid_AD` FOREIGN KEY (`Aid`) REFERENCES `Attendance` (`Aid`);
ALTER TABLE `ADetail` ADD CONSTRAINT `Sid_AD` FOREIGN KEY (`Sid`) REFERENCES `Student` (`Sid`);
ALTER TABLE `CDetail` ADD CONSTRAINT `Sid_CD` FOREIGN KEY (`Sid`) REFERENCES `Student` (`Sid`);
ALTER TABLE `CDetail` ADD CONSTRAINT `Cid_CD` FOREIGN KEY (`Cid`) REFERENCES `Class` (`Cid`);


CREATE TRIGGER 
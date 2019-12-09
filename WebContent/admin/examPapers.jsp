<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>试卷信息管理</title>
	<%
    	String path = request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    %>
	<c:set var="path" value="<%=basePath %>"></c:set>
 	<link href="${path }/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
 	<link rel="stylesheet" type="text/css" href="${path }/js/zeroModal/zeroModal.css" />
</head>
<body>

	<div>
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>考勤编号</th>
					<th>考勤名称</th>
					<th>考勤限时</th>
					<th>分科情况</th>
					<th>考勤类型</th>
					<th>所属年级</th>
					<th>操作
						<button type="button" class="btn btn-xs btn-info" onclick="_iframe(0, 'preAddExamPaper', 'examPapers')">添加</button>
					</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty examPapers }">
						<c:forEach items="${examPapers }" var="ep">
							<tr class="subject_info">
								<td>${ep.examPaperId }</td>
								<td>${ep.examPaperName }</td>
								<td>${ep.examPaperTime }</td>
								<td>
									<c:if test="${ep.division == 0 }">
										未分科
									</c:if>
									<c:if test="${ep.division == 1 }">
										文科
									</c:if>
									<c:if test="${ep.division == 2 }">
										理科
									</c:if>
								</td>
								<td>
									<c:if test="${ep.examPaperEasy == 0 }">
										二维码考勤
									</c:if>
									<c:if test="${ep.examPaperEasy == 1 }">
										GPS考勤
									</c:if>
									<c:if test="${ep.examPaperEasy == 2 }">
										人脸识别考勤
									</c:if>
								</td>
								<td>${ep.grade.gradeName }</td>
								<td>
									<div class="btn-group">
										<button type="button" class="btn btn-info btn-sm" onclick="_iframe(1, 'examPaper/${ep.examPaperId }', 'examPapers')">修改</button>
										<button type="button" class="btn btn-danger btn-sm" onclick="del('examPaper/${ep.examPaperId }')">删除</button>
									</div>
								</td>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
		<form action="student" method="post">
			<input type="hidden" value="DELETE" name="_method" />
		</form>
		<c:if test="${pageTotal > 1 }">
			<div>
				<ul class="pagination"> 
					<c:if test="${pageNow-1 > 0 }">
						<li><a href="teachers?startPage=${pageNow-1 }">&laquo;</a></li>
					</c:if>
					<c:forEach begin="1" end="${pageTotal }" step="1" var="pageNo">
						<c:if test="${pageNow == pageNo }">
							<li class="active"><a href="examPapers?startPage=${pageNo }">${pageNo }</a></li>
						</c:if>
						<c:if test="${pageNow != pageNo }">
							<li><a href="teachers?startPage=${pageNo }" class="pageLink">${pageNo }</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${pageNow+1 <= pageTotal }">
						<li><a href="teachers?startPage=${pageNow+1 }">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>


	<!-- js引入 -->
    <script src="${path }/js/jquery.js"></script>
    <script src="${path }/js/bootstrap/bootstrap.min.js"></script>
    <script src="${path }/js/handle.js"></script>
    <script src="${path }/js/zeroModal/zeroModal.min.js"></script>
    <script src="${path }/js/add-update.js"></script>
   	<script src="${path }/js/view-link.js"></script>
</body>
</html>
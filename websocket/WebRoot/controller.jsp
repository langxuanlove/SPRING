<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Map<String, Object>> list = (ArrayList)request.getAttribute("list");
//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
%>

<!DOCTYPE html>
<html lang="zh-CN">
<!-- <html lang="zh-CN" manifest="/Big_Screen/main.manifest"> -->
<head>
 <base href="<%=basePath%>">
 <meta charset="UTF-8">
 <title>控制模拟器</title>
</head>

<body>
	<table>
		<thead>
			<tr>
				<th>pageNum</th>
				<th>actionType</th>
				<th>subPageFlag</th>
				<th>subPageNum</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input id="pageNum" /></td>
				<td><input id="actionType" /></td>
				<td><input id="subPageFlag" /></td>
				<td><input id="subPageNum" /></td>
				<td><input id="submit" type="button" value="提交"/></td>
			</tr>
		</tbody>
	</table>
<script src="plugins/js/jquery-1.8.3.min.js"></script>
<script>
	$(function(){
		$("#submit").bind("click", function(){
			$.ajax({
				url : "page/displayController/updatePageInfo",
				type : "post",
				data : {
					pageNum : $("#pageNum").val(),
					actionType : $("#actionType").val(),
					subPageFlag : $("#subPageFlag").val(),
					subPageNum : $("#subPageNum").val()
				},
				success : function(data){
					console.log(data);
				}
			})
		});
	})
</script>
</body>
</html>
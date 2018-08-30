<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<body>
<table border="1" class="table table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>账号</th>
        <th>密码</th>
        <th>姓名</th>
        <th>性别</th>
        <th>日期</th>
        <th>电话</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <#if lists??>
        <#list lists as User>
				<tr>
                    <td>${User.id}</td>
                    <td>${User.number}</td>
                    <td>${User.password}</td>
                    <td>${User.name}</td>
                    <td>${User.sex}</td>
                    <td>${User.date}</td>
                    <td>${User.tel}</td>
                    <td><a href="/touser2modify?number=${User.number}">修改</a>
                        <a href="/user2delete?number=${User.number}">删除</a>
                    </td>
				<tr>
        </#list>
    <#else>
				<p>	抱歉!您所查询的账号不存在 </p>
    </#if>
    </tbody>
</table>
<input type="button" name="返回" value="返回" onclick ="location.href='user2query'"/>
</body>
</html>
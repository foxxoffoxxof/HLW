<html>
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
    </tr>
    </thead>
    <tbody>
    <#list lists as User>
    <tr>
        <td>${User.id}</td>
        <td>${User.number}</td>
        <td>${User.password}</td>
        <td>${User.name}</td>
        <td>${User.sex}</td>
        <td>${User.date}</td>
        <td>${User.tel}</td>
    </tr>
    </#list>
    </tbody>
</table>
<input type="button" name="返回" value="返回" onclick ="location.href='user1'"/>

</body>
</html>
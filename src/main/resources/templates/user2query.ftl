<#setting classic_compatible=true>
<#assign base=request.contextPath />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>User List</title>
    <link href=" ${base}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <style>
        li{float: left; list-style: none;margin: 10px;}
        .active{background-color: red}
    </style>
</head>

<body>
<form action="user2query" name="user2query" accept-charset="utf-8" method="post">
    <div>
        <td>请输入您需要搜索的账号：</td>
        <td><label>
            <input type="text" name="number"/>
        </label></td>
        <input type="submit" name="搜索" value="搜索" />
        <input type="button" name="返回" value="返回" onclick ="location.href='touser2'"/>
    </div>
<table  border="1" class="table table-striped">
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
			<#if page.list??>
                <#list page.list as User>
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
				<p>	抱歉!暂时无数据 </p>
            </#if>
    </tbody>

</table>
<div class="message">
    共<span class="blue" style="font-style: italic;">${page.totalDataCount}</span>条记录，当前显示第 <span
        class="blue" style="font-style: italic;">${page.pageCount}/${page.totalPageCount}</span> 页
</div>
<div style="text-align:center;">
    <ul class="pagination">

                <#if page.pageCount==1 >
					<li>首页</li>
					<li>上一页</li>
                </#if>
				<#if (page.pageCount>1) >
					<li><a href="javascript:queryAll(${2});">首页</a></li>
					<li><a href="javascript:queryAll(${page.pageCount});">上一页</a></li>
                </#if>

                <#if (page.getTotalPageCount() == 1)>
                <li><a href="http://localhost:8080/user2query?pageCount=1" >1</a></li>
                </#if>

                <#--如果不只有一页-->
                <#if (page.getTotalPageCount() > 1)>
                <li <#if page.pageCount == 1>class="disabled"</#if>><a href="http://localhost:8080/user2query?pageCount=1" >1</a></li>
                <#--如果当前页往前查2页不是第1页-->
                <#if ((page.pageCount - 2) >= 1)>
                    <li><span class="text">…</span></li>
                </#if>
                <#--当前页的前3页和后3页-->
                <#list (page.pageCount - 1)..(page.pageCount + 1) as index>
                <#--如果位于第一页和最后一页之间-->
                    <#if (index > 1) && (index < page.getTotalPageCount())>
                        <li <#if page.pageCount == index>class="disabled"</#if>><a href="http://localhost:8080/user2query?pageCount=${index}" >${index}</a></li>
                    </#if>
                </#list>

                <#--如果当前页往后查3页不是倒数第1页-->
                    <#if (page.pageCount + 2) <= (page.getTotalPageCount())>
                        <li><span class="text">…</span></li>
                    </#if>

                <#--最后页-->
                    <li <#if page.pageCount == page.getTotalPageCount()>class="disabled"</#if>><a href="http://localhost:8080/user2query?pageCount=${page.getTotalPageCount()}" >${page.getTotalPageCount()}</a></li>
                </#if>


                <#if (page.pageCount>=page.totalPageCount)>
					<li>下一页</li>
					<li>尾页</li>
                </#if>
				<#if (page.pageCount<page.totalPageCount)>
					<li><a href="javascript:queryAll2(${page.pageCount});">下一页</a></li>
					<li><a href="javascript:queryAll2(${(page.totalPageCount)-1});">尾页</a></li>
                </#if>

    </ul>
</div>

<script type="text/javascript">
    function queryAll(pageNum){
        document.location.href="http://localhost:8080/user2query?pageCount="+(pageNum-1);
    }
    function queryAll2(pageNum){
        document.location.href="http://localhost:8080/user2query?pageCount="+(pageNum+1);
    }
</script>
</form>
</body>
</html>

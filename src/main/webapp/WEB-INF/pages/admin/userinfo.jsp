<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- 导入jquery核心类库 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 导入easyui类库 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/css/default.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
    <script
            src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $("body").css({visibility: "visible"});
            $('#save').click(function () {
                $('#form').submit();
            });
            $("#loginName").change(function (e, name) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/admin/user/checkLoginName",
                    type: "post",
                    data: {
                        loginName: $(this).val()
                    },
                    success: function (data) {
                        if (data.status == 1) {

                        } else {
                            $("#loginName").val('');
                            alert(data.message);
                        }
                    }
                })
            })
        });
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
    <div class="datagrid-toolbar">
        <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
    </div>
</div>
<div region="center" style="overflow:auto;padding:5px;" border="false">
    <form id="form" method="post" action="${pageContext.request.contextPath}/admin/user/save">
        <table class="table-edit" width="95%" align="center">
            <tr class="title">
                <td colspan="4">基本信息</td>
            </tr>
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="loginName" id="loginName" class="easyui-validatebox" required="true"/></td>
                <td>口令:</td>
                <td><input type="password" name="password" id="password" class="easyui-validatebox" required="true"
                           validType="minLength[5]"/></td>
            </tr>
            <tr class="title">
                <td colspan="4">其他信息</td>
            </tr>
            <tr>
                <td>工资:</td>
                <td><input type="text" name="salary" id="salary" class="easyui-numberbox"/></td>
                <td>生日:</td>
                <td><input type="text" name="birthday" id="birthday" class="easyui-datebox"/></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td>
                    <select name="sex" id="sex" class="easyui-combobox" style="width: 150px;">
                        <option value="">请选择</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </td>
                <td>单位:</td>
                <td>
                    <select name="unitId" id="unit.id" class="easyui-combobox" style="width: 150px;">
                        <option value="">请选择</option>
                        <c:forEach items="${units}" var="unit">
                            <option value="${unit.id}">${unit.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td colspan="3">
                    <input type="text" name="phone" id="telephone" class="easyui-validatebox" required="true"/>
                </td>
                <td>角色:</td>
                <td><select name="sysRoleId" id="sysRole.id" class="easyui-combobox" style="width: 150px;">
                    <option value="">请选择</option>
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.id}">${role.name}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td>备注:</td>
                <td colspan="3"><textarea name="remark" style="width:80%"></textarea></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
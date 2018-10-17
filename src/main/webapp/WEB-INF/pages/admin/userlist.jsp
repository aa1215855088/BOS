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
        // 工具栏
        var toolbar = [{
            id: 'button-view',
            text: '查看',
            iconCls: 'icon-search',
            handler: doView
        }, {
            id: 'button-add',
            text: '新增',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-delete',
            text: '删除',
            iconCls: 'icon-cancel',
            handler: doDelete
        }];
        //定义冻结列
        var frozenColumns = [[{
            field: 'id',
            checkbox: true,
            rowspan: 2
        }, {
            field: 'loginName',
            title: '名称',
            width: 80,
            rowspan: 2
        }]];


        // 定义标题栏
        var columns = [[{
            field: 'sex',
            title: '性别',
            width: 60,
            rowspan: 2,
            align: 'center'
        }, {
            field: 'birthday',
            title: '生日',
            width: 120,
            rowspan: 2,
            align: 'center',
            formatter: function (data, row, index) {
                return getTime(data);
            }
        }, {
            title: '其他信息',
            colspan: 2
        }, {
            field: 'phone',
            title: '电话',
            width: 800,
            rowspan: 2
        }], [{
            field: 'station',
            title: '单位',
            width: 80,
            align: 'center',
            formatter: function (data, row, index) {
                return row.unit.name;
            }
        }, {
            field: 'salary',
            title: '工资',
            width: 80,
            align: 'right'
        }]];
        $(function () {
            // 初始化 datagrid
            // 创建grid
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,
                striped: true,
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/admin/user/findAll",
                idField: 'id',
                frozenColumns: frozenColumns,
                columns: columns,
                onClickRow: onClickRow,
                onDblClickRow: doDblClickRow
            });

            $("body").css({visibility: "visible"});

        });

        function getTime(dateTime) {
            // // 简单的一句代码
            // var date = new Date(dateTime); //获取一个时间对象
            //
            // /**
            //  1. 下面是获取时间日期的方法，需要什么样的格式自己拼接起来就好了
            //  2. 更多好用的方法可以在这查到 -> http://www.w3school.com.cn/jsref/jsref_obj_date.asp
            //  */
            // var y = date.getFullYear();  // 获取完整的年份(4位,1970)
            // var m = date.getMonth();  // 获取月份(0-11,0代表1月,用的时候记得加上1)
            // var d = date.getDate();  // 获取日(1-31)
            // var t = date.getTime();  // 获取时间(从1970.1.1开始的毫秒数)
            // var h = date.getHours();  // 获取小时数(0-23)
            // var mm = date.getMinutes();  // 获取分钟数(0-59)
            // var s = date.getSeconds();  // 获取秒数(0-59)
            // return y + "年" + parseInt(m+1)+ "月" + d;
            var date = new Date(dateTime);
            Y = date.getFullYear() + '年';
            M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '月';
            D = date.getDate() + '日';
            h = date.getHours() + ':';
            m = date.getMinutes() + ':';
            s = date.getSeconds();
            return Y + M + D;
        }

        // 双击
        function doDblClickRow(rowIndex, rowData) {
            var items = $('#grid').datagrid('selectRow', rowIndex);
            doView();
        }

        // 单击
        function onClickRow(rowIndex) {

        }

        function doAdd() {
            location.href = "${pageContext.request.contextPath}/admin/toUserInfo";
        }

        function doView() {
            var rows = $('#grid').datagrid('getSelections');
            if (rows == null || rows == "") {
                alert("请选择一行");
                return;
            }
            if (rows.length > 1) {
                alert("请选择-行")
                return;
            }
            window.location.href = "${pageContext.request.contextPath}/admin/user/findUserById/" + rows[0].id;
            //window.location.href = "edit.html";
        }

        function doDelete() {
            var rows = $('#grid').datagrid('getSelections');
            if (rows == null || rows == "" || rows == 'un') {
                alert("请选择一行");
                return;
            }
            var ids = "";

            for (var i = 0; i < rows.length; i++) {
                var id = rows[i].uid
                ids += id + ",";
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/user/del",
                type: "post",
                data: {
                    ids: ids
                },
                success: function (data) {
                    alert(data.message);
                    $('#grid').datagrid("reload");
                    $('#grid').datagrid('clearSelections');
                },
                error: function () {
                    alert("作废失败");
                }
            })
        }

    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
</body>
</html>
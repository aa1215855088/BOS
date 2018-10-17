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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/css/bootstrap.css"></script>
    <script type="text/javascript">

        var title = "添加取派员";
        $(function () {
            $("#searchWindow").window("close");
        });

        function doAdd() {
            //alert("增加...");
            $("input").val('');
            $("input:checkbox").attr("checked", false);
            $(".panel-title").text("添加取派员");
            $('#addStaffWindow').window("open");
        }

        function doView() {
            $("#searchWindow").window("open");
        }

        function doDelete() {
            var rows = $('#grid').datagrid('getSelections');
            if (rows == null || rows == "" || rows == 'un') {
                alert("请选择一行");
                return;
            }
            var ids = "";

            for (var i = 0; i < rows.length; i++) {
                var id = rows[i].id
                ids += id + ",";
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/base/staff/delStaff",
                type: "post",
                data: {
                    ids: ids
                },
                success: function (data) {
                    $('#addStaffWindow').window("close");
                    alert(data.message);
                    $('#grid').datagrid("reload");
                    $('#grid').datagrid('clearSelections');
                },
                error: function () {
                    alert("作废失败");
                }
            })
        }

        function doRestore() {
            var rows = $('#grid').datagrid('getSelections');
            if (rows == null || rows == "" || rows == 'un') {
                alert("请选择一行");
                return;
            }
            var ids = "";

            for (var i = 0; i < rows.length; i++) {
                var id = rows[i].id
                ids += id + ",";
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/base/staff/restoreStaff",
                type: "post",
                data: {
                    ids: ids
                },
                success: function (data) {
                    $('#addStaffWindow').window("close");
                    alert(data.message);
                    $('#grid').datagrid("reload");
                    $('#grid').datagrid('clearSelections');
                },
                error: function () {
                    alert("还原失败");
                }
            })
        }

        //工具栏
        var toolbar = [{
            id: 'button-view',
            text: '查询',
            iconCls: 'icon-search',
            handler: doView
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-delete',
            text: '作废',
            iconCls: 'icon-cancel',
            handler: doDelete
        }, {
            id: 'button-save',
            text: '还原',
            iconCls: 'icon-save',
            handler: doRestore
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'name',
            title: '姓名',
            width: 120,
            align: 'center'
        }, {
            field: 'telephone',
            title: '手机号',
            width: 120,
            align: 'center'
        }, {
            field: 'pda',
            title: '是否有PDA',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "1") {
                    return "有";
                } else {
                    return "无";
                }
            }
        }, {
            field: 'deleteTag',
            title: '是否作废',
            width: 120,
            align: 'center',
            formatter: function (data, row, index) {
                if (data == "0") {
                    return "正常使用"
                } else {
                    return "已作废";
                }
            }
        }, {
            field: 'standard',
            title: '取派标准',
            width: 120,
            align: 'center'
        }, {
            field: 'station',
            title: '所谓单位',
            width: 200,
            align: 'center'
        }]];

        $(function () {
            // 先将body隐藏，再显示，不会出现页面刷新效果
            $("body").css({visibility: "visible"});

            // 取派员信息表格
            $('#grid').datagrid({
                iconCls: 'icon-forward',
                fit: true,
                border: false,
                rownumbers: true,
                striped: true,
                pageList: [3, 5, 10],
                pageSize: 3,
                pagination: true,
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/base/staff/findAll",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow
            });

            // 添加取派员窗口
            $('#addStaffWindow').window({
                title: title,
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });

        });


        function doDblClickRow(rowIndex, rowData) {
            $("input:checkbox").attr("checked", false);
            $('#addStaffWindow').window("open");
            $(".panel-title").text("修改取派员");
            $("input").val('');
            $.ajax({
                url: "${pageContext.request.contextPath}/base/staff/findStaffById/" + rowData.id,
                type: "post",
                success: function (data) {
                    if (data.status == 1) {
                        $("#staffId").val(data.data.id);
                        $("input[name=name]").val(data.data.name);
                        $("input[name=telephone]").val(data.data.telephone);
                        $("input[name=station]").val(data.data.station);
                        if (data.data.pda == '1') {
                            $("input[name=pda]").attr("checked", true);
                        }
                        $("input[name=standard]").val(data.data.standard)
                    }
                },
                error: function () {
                    alert("系统繁忙!请重试.")
                }
            })
        }
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>

            <script type="text/javascript">
                $(function () {
                    $("#save").unbind("click");
                    $("#save").bind("click", function () {
                        // 真正地添加操作
                        var isValid = $("#form1").form('validate');
                        if (!isValid) {
                            // false则意味着不能提交表单
                            alert("输入有误");
                            return;
                        }
                        ;
                        var pda = "";
                        if ($("input[name=pda]").attr('checked')) {
                            pda = 1;
                        }
                        if ($("#staffId").val() != "") {
                            $.ajax({
                                url: "${pageContext.request.contextPath}/base/staff/updateStaff?id=" + $("#staffId").val(),
                                type: "post",
                                data: {
                                    name: $("input[name=name]").val(),
                                    telephone: $("input[name=telephone]").val(),
                                    station: $("input[name=station]").val(),
                                    pda: pda,
                                    standard: $("input[name=standard]").val()
                                },
                                success: function (data) {
                                    $('#addStaffWindow').window("close");
                                    alert(data.message);
                                    $("input").val('');
                                    $('#grid').datagrid("reload");
                                    $('#grid').datagrid('clearSelections');
                                }
                                ,
                                error: function () {
                                    alert("修改失败")
                                }
                            })
                        } else {
                            $.ajax({
                                url: "${pageContext.request.contextPath}/base/staff/addStaff",
                                type: "post",
                                data: {
                                    name: $("input[name=name]").val(),
                                    telephone: $("input[name=telephone]").val(),
                                    station: $("input[name=station]").val(),
                                    pda: pda,
                                    standard: $("input[name=standard]").val()
                                },
                                success: function (data) {
                                    $('#addStaffWindow').window("close");
                                    alert(data.message);
                                    $("input").val('');
                                    $('#grid').datagrid("reload");
                                },
                                error: function () {
                                    alert("添加失败")
                                }
                            })
                        }

                    });
                })
            </script>
        </div>
    </div>

    <input id="staffId" type="hidden">
    <div region="center" style="overflow:auto;padding:5px;" border="false">
        <form id="form1">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">收派员信息</td>
                </tr>
                <!-- TODO 这里完善收派员添加 table -->
                <tr>
                    <td>姓名</td>
                    <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>手机</td>
                    <td><input type="text" name="telephone" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>单位</td>
                    <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" id="pda" name="pda" value="1"/>
                        是否有PDA
                    </td>
                </tr>
                <tr>
                    <td>取派标准</td>
                    <td>
                        <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="easyui-window" title="查询取派员窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false"
     style="top:20px;left:200px">
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="search">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询条件</td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input type="text" id="name"/></td>
                </tr>
                <tr>
                    <td>手机号</td>
                    <td><input type="text" id="phone"/></td>
                </tr>
                <tr>
                    <td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
            </div>
            <div class="modal-body">按下 ESC 按钮退出。</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<script>
    $("#btn").click(function () {
        $('#grid').datagrid("load", {
            "phone": $("#phone").val(),
            "name": $("#name").val()
        });
        $("#searchWindow").window("close");
    })
</script>
<!-- /.modal -->
</body>
</html>	
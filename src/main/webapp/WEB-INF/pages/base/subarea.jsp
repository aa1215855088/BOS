<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理分区</title>
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
</head>
<body class="easyui-layout" style="visibility:hidden;">
<script type="text/javascript">
    function doAdd() {
        $("input").val('');
        $("#bbq").val("1");
        $("input[name=id]").attr("readOnly", false);
        $('#addSubareaWindow').window("open");
    }

    function doEdit() {
        $("input").val('');
        $("#bbq").val("2");
        var rows = $('#grid').datagrid('getSelections');
        if (rows == null || rows == "" || rows == 'un') {
            alert("请选择一行");
            return;
        }
        if (rows.length > 1) {
            alert("请选择一行");
            return;
        }
        $('#addSubareaWindow').window("open");
        $("input[name=id]").val(rows[0].id);
        $("input[name=id]").attr("readOnly", true);
        $("#area").combobox('select', rows[0].area.id);
        $("input[name=addressKey]").val(rows[0].addressKey);
        $("input[name=startNum]").val(rows[0].startNum);
        $("input[name=endNum]").val(rows[0].endNum);
        $("#single").combobox('select', rows[0].single);
        $("input[name=position]").val(rows[0].position);
    }

    function closeWindos() {
        $('#addSubareaWindow').window("close");
        $("input").val('');
    }

    function doDelete() {
        var rows = $('#grid').datagrid('getSelections');
        if (rows == null || rows == "" || rows == 'un') {
            alert("请选择一行");
            return;
        }
        var ids = "";
        for (var i = 0; i < rows.length; i++) {
            var id = rows[i].id;
            ids += id + ",";
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/base/subArea/delSubArea",
            type: "post",
            data: {
                ids: ids.substring(0, ids.length - 1)
            },
            success: function (data) {
                alert(data.message);
                $('#grid').datagrid("reload");
            }
            ,
            error: function () {
                alert("删除失败");
            }
        })
    }

    function doSearch() {
        $('#searchWindow').window("open");
    }

    function doExport() {
        $("#exportExcelWindos").window("open");

    }

    function doImport() {
        $("#import-excel-template").window("open");
    }

    //工具栏
    var toolbar = [{
        id: 'button-search',
        text: '查询',
        iconCls: 'icon-search',
        handler: doSearch
    }, {
        id: 'button-add',
        text: '增加',
        iconCls: 'icon-add',
        handler: doAdd
    }, {
        id: 'button-edit',
        text: '修改',
        iconCls: 'icon-edit',
        handler: doEdit
    }, {
        id: 'button-delete',
        text: '删除',
        iconCls: 'icon-cancel',
        handler: doDelete
    }, {
        id: 'button-import',
        text: '导入',
        iconCls: 'icon-redo',
        handler: doImport
    }, {
        id: 'button-export',
        text: '导出',
        iconCls: 'icon-undo',
        handler: doExport
    }];
    // 定义列
    var columns = [[{
        field: 'id',
        checkbox: true,
    }, {
        field: 'showid',
        title: '分拣编号',
        width: 120,
        align: 'center',
        formatter: function (data, row, index) {
            return row.id;
        }
    }, {
        field: 'province',
        title: '省',
        width: 120,
        align: 'center',
        formatter: function (data, row, index) {
            return row.area.province;
        }
    }, {
        field: 'city',
        title: '市',
        width: 120,
        align: 'center',
        formatter: function (data, row, index) {
            return row.area.city;
        }
    }, {
        field: 'district',
        title: '区',
        width: 120,
        align: 'center',
        formatter: function (data, row, index) {
            return row.area.district;
        }
    }, {
        field: 'addressKey',
        title: '关键字',
        width: 120,
        align: 'center'
    }, {
        field: 'startNum',
        title: '起始号',
        width: 100,
        align: 'center'
    }, {
        field: 'endNum',
        title: '终止号',
        width: 100,
        align: 'center'
    }, {
        field: 'single',
        title: '单双号',
        width: 100,
        align: 'center',
        formatter: function (data, row, index) {
            if (data == "0") {
                return "单双号";
            } else if (data == "1") {
                return "单号";
            } else if (data == "2") {
                return "双号";
            }
        }
    }, {
        field: 'position',
        title: '位置',
        width: 200,
        align: 'center'
    }]];

    $(function () {
        // 先将body隐藏，再显示，不会出现页面刷新效果
        $("body").css({visibility: "visible"});

        // 收派标准数据表格
        $('#grid').datagrid({
            iconCls: 'icon-forward',
            fit: true,
            border: true,
            rownumbers: true,
            striped: true,
            pageList: [30, 50, 100],
            pagination: true,
            toolbar: toolbar,
            url: "${pageContext.request.contextPath}/base/subArea/findAll",
            idField: 'id',
            columns: columns,
            onDblClickRow: doDblClickRow
        });

        // 添加、修改分区
        $('#addSubareaWindow').window({
            title: '添加修改分区',
            width: 600,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable: false
        });

        // 查询分区
        $('#searchWindow').window({
            title: '查询分区',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable: false
        });

        $("#save").click(function () {

            var isValid = $("#form3").form('validate');
            if (!isValid) {
                // false则意味着不能提交表单
                alert("输入有误");
                return;
            }
            if ($("#bbq").val() == "1") {
                $.ajax({
                    url: "${pageContext.request.contextPath}/base/subArea/addSubArea",
                    type: "post",
                    data: $("#form3").serialize(),
                    success: function (data) {
                        alert(data.message);
                        closeWindos();
                        $('#grid').datagrid("reload");
                    },
                    error: function () {
                        alert("添加失败");
                    }
                })
            } else {
                $.ajax({
                    url: "${pageContext.request.contextPath}/base/subArea/updateSubArea",
                    type: "post",
                    data: $("#form3").serialize(),
                    success: function (data) {
                        alert(data.message);
                        closeWindos();
                        $('#grid').datagrid("reload");
                    },
                    error: function () {
                        alert("修改失败");
                    }
                })
            }

        })
    });

    function doDblClickRow() {
        doEdit()
    }


</script>
<div region="center" border="false">
    <table id="grid"></table>
</div>
<input id="bbq">
<!-- 添加 修改分区 -->
<div class="easyui-window" title="分区添加修改" id="addSubareaWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>

    <div style="overflow:auto;padding:5px;" border="false">
        <form id="form3">
            <input id="id" type="hidden">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">分区信息</td>
                </tr>
                <tr>
                    <td>分拣编码</td>
                    <td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>选择区域</td>
                    <td>
                        <input name="area.id" id="area" class="easyui-combobox"
                               data-options="valueField:'id',textField:'name',url:'/base/area/findAllArea'"/>
                    </td>
                </tr>
                <tr>
                    <td>关键字</td>
                    <td><input type="text" name="addressKey" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>起始号</td>
                    <td><input type="text" name="startNum" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>终止号</td>
                    <td><input type="text" name="endNum" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>单双号</td>
                    <td>
                        <select id="single" class="easyui-combobox" name="single" style="width:150px;">
                            <option value="0">单双号</option>
                            <option value="1">单号</option>
                            <option value="2">双号</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>位置信息</td>
                    <td><input type="text" name="position" class="easyui-validatebox" required="true"
                               style="width:250px;"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!-- 查询分区 -->
<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false"
     style="top:20px;left:200px">
    <div style="overflow:auto;padding:5px;" border="false">
        <form id="search">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">查询条件</td>
                </tr>
                <tr>
                    <td>省</td>
                    <td><input type="text" name="area.province"/></td>
                </tr>
                <tr>
                    <td>市</td>
                    <td><input type="text" name="area.city"/></td>
                </tr>
                <tr>
                    <td>区（县）</td>
                    <td><input type="text" name="area.district"/></td>
                </tr>
                <tr>
                    <td>关键字</td>
                    <td><input type="text" id="addressKey"/></td>
                </tr>
                <tr>
                    <td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="easyui-window" id="exportExcelWindos" title="导出" style="width:400px;height:160px;padding:2px;"
     closed="true">
    <div style="text-align:center;clear:both;margin:5px;">
        <table style="margin:5px;height:70px;">
            <tr>
                <td>请选择导出路径</td>
                <td width="5px;"></td>
                <td><input id="path" type="text" name="path" size="30">
                </td>
                <td><input type=button value="选择" onclick="browseFolder('path')"></td>
            </tr>
        </table>


        <a id="export" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
           data-bind="click:importFileClick" href="javascript:void(0)">导出</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-bind="click:closeImportClick"
           href="javascript:void(0)" onclick="close()">关闭</a>
    </div>
</div>
<div class="easyui-window" id="import-excel-template" title="导入" style="width:400px;height:160px;padding:2px;"
     closed="true">
    <form id="importFileForm" method="post" enctype="multipart/form-data" style="display:block"
          action="${pageContext.request.contextPath}/base/area/importData">
        <table style="margin:5px;height:70px;">
            <tr>
                <td>请选择文件</td>
                <td width="5px;"></td>
                <td><input type="file" class="easyui-filebox easyui-validatebox" id="fileImport" name="file"
                           style="width:260px;">
                </td>
                <td></td>
            </tr>
            <tr>
                <td colspan="4"><label id="fileName"/></td>
            </tr>
            <tr>
                <td colspan="4">
                    <label id="uploadInfo"/>
                </td>
            </tr>
        </table>
        <div style="text-align:center;clear:both;margin:5px;">
            <a id="uploadFile" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
               data-bind="click:importFileClick" href="javascript:void(0)">导入</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-bind="click:closeImportClick"
               href="javascript:void(0)" onclick="closeDoFile()">关闭</a>
        </div>
    </form>
</div>
</body>
</html>
<script>
    function close() {
        $("#exportExcelWindos").window("close");
    }

    $("#export").click(function () {
        var titles = "";
        $(".datagrid-header-row>td").each(function () {
            titles += $(this).text() + ",";
        });
        // window.location.href = "/base/subArea/export?titles=" + titles;
        $.ajax({
            url: "/base/subArea/export",
            type: "post",
            data: {
                titles: titles.substring(2, titles.length - 1)
            },
            success: function (data) {
                alert(data.message);
                close();
            }
            ,
            error: function () {
                alert("导出失败");
            }
        })
    });

    function closeDoFile() {
        $('#import-excel-template').window("close");
    };
    $("#uploadFile").click(function () {
        var fileName = $("#fileImport").val();
        if (fileName == "" || fileName == null) {
            // false则意味着不能提交表单
            alert("请选择文件");
            return;
        }
        var fileType = fileName.substr(fileName.length - 4, fileName.length);
        if (fileType != ".xls") {
            alert("请选择后缀为.xls的文件");
            return;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/base/subArea/importData",　　　　　　　　　　//上传地址
            type: 'POST',
            cache: false,
            data: new FormData($('#importFileForm')[0]),　　　　　　　　　　　　　//表单数据
            processData: false,
            contentType: false,
            success: function (data) {
                alert(data.message);
                $('#import-excel-template').window("close");
                $("input").val('');
                $('#grid').datagrid("reload");
            }
        });
    });
    $("#btn").click(function () {
        //解决乱码问题
        // var result = decodeURIComponent($("#search").serialize());
        $('#grid').datagrid("load", {
            "province": $("input[name='area.province']").val(),
            "city": $("input[name='area.city']").val(),
            "district": $("input[name='area.district']").val(),
            "addressKey": $("#addressKey").val()
        });
        $("#searchWindow").window("close");

    });
</script>
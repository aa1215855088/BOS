<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>区域设置</title>
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
        function doAdd() {
            $("#ppap").val("0");
            $("input").val('');
            $(".panel-title").text("添加区域");
            $('#addRegionWindow').window("open");
        }

        function doView() {
            $("#ppap").val("1");
            $(".panel-title").text("修改区域");
            var rows = $('#grid').datagrid('getSelections');
            if (rows == null || rows == "") {
                alert("请选择一行");
                return;
            }


            $.ajax({
                url: "${pageContext.request.contextPath}/base/area/findAreaById",
                type: "post",
                data: {
                    id: rows[0].id
                },
                success: function (data) {
                    $('#addRegionWindow').window("open");
                    $("input[name=cityCode]").val(data.data.cityCode);
                    $("input[name=district]").val(data.data.district);
                    $("input[name=city]").val(data.data.city);
                    $("input[name=postCode]").val(data.data.postCode);
                    $("input[name=province]").val(data.data.province);
                    $("input[name=shortCode]").val(data.data.shortCode);
                    $("#id").val(data.data.id);
                },
                error: function () {
                    alert("生成失败");
                }
            })
        }

        function doDelete() {
            var rows = $('#grid').datagrid('getSelections');
            if (rows == null || rows == "") {
                alert("请选择一行");
                return;
            }
            var ids = "";

            for (var i = 0; i < rows.length; i++) {
                var id = rows[i].id
                ids += id + ",";
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/base/area/delArea",
                type: "post",
                data: {
                    ids: ids
                },
                success: function (data) {
                    $('#addRegionWindow').window("close");
                    alert(data.message);
                    $('#grid').datagrid("reload");
                    $('#grid').datagrid('clearSelections');
                },
                error: function () {
                    alert("删除失败");
                }
            })
        }

        //工具栏
        var toolbar = [{
            id: 'button-edit',
            text: '修改',
            iconCls: 'icon-edit',
            handler: doView
        }, {
            id: 'button-add',
            text: '增加',
            iconCls: 'icon-add',
            handler: doAdd
        }, {
            id: 'button-delete',
            text: '删除',
            iconCls: 'icon-cancel',
            handler: doDelete
        }, {
            id: 'button-import',
            text: '导入',
            iconCls: 'icon-redo',
            handler: doFile
        }];
        // 定义列
        var columns = [[{
            field: 'id',
            checkbox: true,
        }, {
            field: 'province',
            title: '省',
            width: 120,
            align: 'center'
        }, {
            field: 'city',
            title: '市',
            width: 120,
            align: 'center'
        }, {
            field: 'district',
            title: '区',
            width: 120,
            align: 'center'
        }, {
            field: 'postCode',
            title: '邮编',
            width: 120,
            align: 'center'
        }, {
            field: 'shortCode',
            title: '简码',
            width: 120,
            align: 'center'
        }, {
            field: 'cityCode',
            title: '城市编码',
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
                border: false,
                rownumbers: true,
                striped: true,
                pageList: [30, 50, 100],
                pagination: true,
                toolbar: toolbar,
                url: "${pageContext.request.contextPath}/base/area/findAll",
                idField: 'id',
                columns: columns,
                onDblClickRow: doDblClickRow
            });

            // 添加、修改区域窗口
            $('#addRegionWindow').window({
                title: '添加区域',
                width: 400,
                modal: true,
                shadow: true,
                closed: true,
                height: 400,
                resizable: false
            });
        });

        function doDblClickRow() {
            doView();
        }

        function closeDoFile() {
            $('#import-excel-template').window("close");
        }

        function doFile() {
            $('#import-excel-template').window("open");
        }

        $(function () {
            $("#save").click(function () {
                var isValid = $("#form2").form('validate');
                if (!isValid) {
                    // false则意味着不能提交表单
                    alert("输入有误");
                    return;
                }
                if ($("#ppap").val() == "1") {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/base/area/updateArea",
                        type: "post",
                        data: {
                            id: $("#id").val(),
                            cityCode: $("input[name=cityCode]").val(),
                            district: $("input[name=district]").val(),
                            city: $("input[name=city]").val(),
                            postCode: $("input[name=postCode]").val(),
                            province: $("input[name=province]").val(),
                            shortCode: $("input[name=shortCode]").val()
                        },
                        success: function () {
                            $('#addRegionWindow').window("close");
                            $("input").val('');
                            $('#grid').datagrid("reload");
                            $('#grid').datagrid('clearSelections');
                        }
                        ,
                        error: function () {
                            alert("添加失败");
                        }
                    })
                } else {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/base/area/addArea",
                        type: "post",
                        data: $("#form2").serialize(),
                        success: function () {
                            $('#addRegionWindow').window("close");
                            $("input").val('');
                            $('#grid').datagrid("reload");
                        },
                        error: function () {
                            alert("添加失败");
                        }
                    })
                }
            });
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
                    url: "${pageContext.request.contextPath}/base/area/importData",　　　　　　　　　　//上传地址
                    type: 'POST',
                    cache: false,
                    data: new FormData($('#importFileForm')[0]),　　　　　　　　　　　　　//表单数据
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        $('#import-excel-template').window("close");
                        $("input").val('');
                        $('#grid').datagrid("reload");
                    }
                });
            })
        })
    </script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
<input id="ppap" type="hidden">
<div region="center" border="false">
    <table id="grid"></table>
</div>
<div class="easyui-window" title="区域添加修改" id="addRegionWindow" collapsible="false" minimizable="false"
     maximizable="false" style="top:20px;left:200px">
    <div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
        <div class="datagrid-toolbar">
            <a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
        </div>
    </div>
    <input id="id" type="hidden">
    <div region="center" style="overflow:auto   ;padding:5px;" border="false">
        <form id="form2">
            <table class="table-edit" width="80%" align="center">
                <tr class="title">
                    <td colspan="2">区域信息</td>
                </tr>
                <tr>
                    <td>省</td>
                    <td><input type="text" name="province" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>市</td>
                    <td><input type="text" name="city" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>区</td>
                    <td><input type="text" name="district" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>邮编</td>
                    <td><input type="text" name="postCode" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>简码</td>
                    <td><input type="text" name="shortCode" class="easyui-validatebox" required="true"/></td>
                </tr>
                <tr>
                    <td>城市编码</td>
                    <td><input type="text" name="cityCode" class="easyui-validatebox" required="true"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div class="easyui-window" id="import-excel-template" title="区域导入" style="width:400px;height:160px;padding:2px;"
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
               data-bind="click:importFileClick" href="javascript:void(0)">上传</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-bind="click:closeImportClick"
               href="javascript:void(0)" onclick="closeDoFile()">关闭</a>
        </div>
    </form>
</div>
</body>
</html>
$(function ($) {
    var AddPositionURl = "/position/insertPosition";
    //查看部门信息url
    var queryPositionUrl = '/position/queryPositionById';
    //编辑部门url
    var editDepartmentUrl = '/position/editPosition';
    //从url中获取depId
    var pos_id = getURLString('positionId');
    //从url中获取edit,判断是否是编辑操作
    var is_edit = getURLString('edit');
    //查看对应id的部门信息
    if(pos_id){
        $.post(queryPositionUrl,{positionId:pos_id},function (data) {
                $("#pos-name").val(data.data.positionName);
                $("#create-time").val(data.data.createTime);
            if (data.data.status === 1) {
                $("#status-on").attr("checked", true);
                $("#status-off").removeAttr("checked");
            } else {
                $("#status-off").attr("checked", true)
                $("#status-on").removeAttr("checked")
            }
        },"json");
    }

    $("#status-off").click(function () {
        $("#status-off").attr("clicked", true);
        $("#status-on").removeAttr("clicked");
    });
    $('#status-on').click(function () {
        $("#status-on").attr("checked", true)
        $("#status-off").removeAttr("checked")
    });
    // 预检测输入的职位名称
    $("#pos-name").blur(checkPosName);

    $("#submit").click(function () {
        if (checkPosName()) {
            var position = {}
            if(is_edit){
                position.positionId = pos_id;
            }
            position.positionName = $("#pos-name").val();
            position.createTime = $("#create-time").val();
            position.status = $("input[name='status'][checked]").val();
            $.post(is_edit?editDepartmentUrl:AddPositionURl, position, function (data) {
                if (data.flag) {
                    lightyear.notify('操作成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                    // window.location.href = '/department/toList'
                } else {
                    lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                }
            },"json")
        }
    })

});

function showWarn(pos_name, placeholder) {
    pos_name.css("border", "1px solid red");
    pos_name.next().show().html(placeholder);
}

function checkPosName() {
    var posName = $("#pos-name").val();
    var placeholder1 = '职位名称不能为空';
    var placehodeer2 = '输入格式错误';
    var regPosName = /^[\u4E00-\u9FA5A-Za-z0-9]{3,10}$/;
    var flag = regPosName.test(posName);
    if (flag) {
        $("#pos-name").css("border", "1px solid green");
        $("#pos-name").next().hide();
    } else {
        if (posName.length <= 0 || posName == '') {
            showWarn($("#pos-name"), placeholder1);
        } else {
            showWarn($("#pos-name"), placehodeer2);
        }
    }
    return flag;
}

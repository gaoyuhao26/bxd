$(function () {
    //新增部门url
    var addDepartmentUrl = '/department/insertDepartment';
    //查看部门信息url
    var queryDepartmentUrl = '/department/queryDepartmentById';
    //编辑部门url
    var editDepartmentUrl = '/department/editDepartment';
    //从url中获取depId
    var dep_id = getURLString('depId');
    //从url中获取edit,判断是否是编辑操作
    var is_edit = getURLString('edit');
    //查看对应id的部门信息
    if (dep_id) {
        $.post(queryDepartmentUrl, {departmentId: dep_id}, function (data) {
            $('#dep-name').val(data.data.name);
            $('#dep-address').val(data.data.address);
            if (data.data.status === 1) {
                $("#status-on").attr("checked", true);
                $("#status-off").removeAttr("checked");
            } else {
                $("#status-off").attr("checked", true)
                $("#status-on").removeAttr("checked")
            }
        },"json")
    }

    $('#status-off').click(function () {
        $("#status-off").attr("checked", true)
        $("#status-on").removeAttr("checked")
    });

    $('#status-on').click(function () {
        $("#status-on").attr("checked", true)
        $("#status-off").removeAttr("checked")
    })

    //输入校验
    //提示信息并改变边框颜色
    function f(item, holderText) {
        item.css("border", "1px solid red");
        // item.next().show().html(holderText).css("color", "red");
        //  data-toggle="tooltip" data-placement="right" title="Tooltip on right"
        item.next().show().html(holderText)
    }

    function testItem(item, placeholder1, placeholder2, reItem) {
       item.blur(function () {
            var name = item.val();
            if (name.length <= 0 || name == '') {
                //提示信息并改变边框颜色
                f(item, placeholder1);
                return true;
            } else {
                if (reItem.test(name)) {
                    item.css("border", "1px solid green");
                    item.next().hide();
                } else {
                    f(item, placeholder2);
                }

            }

        });

    }


    //3-10位中文
    var Reg_name = /^[\u4E00-\u9FA5A-Za-z0-9]{3,10}$/;
    var placehodeer2 = '输入格式错误';
    var dep_name = $('#dep-name');
    var placeholder1 = '部门名称不能为空';
    testItem(dep_name, placeholder1, placehodeer2, Reg_name);
    var dep_address = $('#dep-address');
    var placeholder1a = '部门地址不能为空';
    testItem(dep_address, placeholder1a, placehodeer2, Reg_name);


    // var flag = Reg_name.test(departmentRegular);
    // //4.对校验的结果给出提示
    // if (flag) {
    //     //校验通过
    //     $("#dep-name").css("border", "1px solid green");
    // } else {
    //     //校验失败
    //     $("#dep-name").css("border", "1px solid red");
    // }
    //return flag;

    //新增部门或者编辑
    $('#submit').click(function () {
            var department = {}
            //如果是编辑就要传dep_id
            if (is_edit) {
                department.depId = dep_id
            }
            department.name = $('#dep-name').val();
            department.address = $('#dep-address').val();
            department.status = $("input[name='status'][checked]").val();
            var formData = new FormData();
            formData.append('departmentStr', JSON.stringify(department));
            $.ajax({
                url: is_edit ? editDepartmentUrl : addDepartmentUrl,
                type: "post",
                dataType: 'json',
                data: formData,
                contentType: false,
                processData: false,
                cache: false,
                async: false,
                success: function (data) {
                    console.log(data)
                    if (data.success) {
                        lightyear.notify('操作成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                        // window.location.href = '/department/toList'
                    } else {
                        lightyear.notify(data.errMsg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                    }
                }

            });

    })

})
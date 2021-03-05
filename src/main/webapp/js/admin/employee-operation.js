$(function () {
    //新增员工url
    var addEmployeeUrl = '/employee/insertEmployee';
    //查看员工信息url
    var queryEmployeeUrl = '/employee/queryEmployeeById';
    //编辑员工url
    var editEmployeeUrl = '/employee/editEmployee';
    //获取有效部门列表url
    var queryDepartmentListUrl = '/department/getDepartmentInfo';
    //获取有效职位列表url
    var queryPositionListUrl = '/position/getPositionInfo';
    //根据部门名称锁定相应的职位
    var positionByDepartmentName = '/position/positionByDepartmentName';

    //从url中获取员工Id
    var emId = getURLString('emId');
    //从url中获取edit,判断是否是编辑操作
    var is_edit = getURLString('edit');

    //未修改之前的loginName
    var loginName = null;

    function selectSearch(selectData) {
        selectData = selectData === undefined ? {} : selectData;
        //获取有效的部门列表和有效的职位列表
        $.post(queryDepartmentListUrl, function (data) {
            if (data.flag) {
                var departmentList = data.data;
                var optionHtml = '';
                departmentList.map(function (item, index) {
                    function showSelected(selectData) {
                        if(selectData.depId == item.depId){
                           return  '<option data-value="' + item.depId + '" selected="selected">' + item.name + '</option>';
                        }else {
                            return  '<option data-value="' + item.depId + '">' + item.name + '</option>';
                        }

                    }

                    //<option value="0">请选择</option>
                    optionHtml += showSelected(selectData)

                })

                $('#department-select').html(optionHtml)

            } else {
                //TODO:错误提示
            }
        }, "json")
        $.post(queryPositionListUrl, function (data) {

            if (data.flag) {
                var positionList = data.data;
                var optionHtml = '';
                positionList.map(function (item, index) {
                    //<option value="0">请选择</option>
                    function showSelected(selectData) {
                        if(selectData.positionId == item.positionId){
                            return '<option data-value="' + item.positionId + '" selected="selected">' + item.positionName + '</option>';
                        }else {
                            return '<option data-value="' + item.positionId + '" >' + item.positionName + '</option>';
                        }
                    }
                    optionHtml += showSelected(selectData)
                })
                $('#position-select').html(optionHtml)
            }
        }, "json")

    }
    //查看对应id的职位信息
    if (emId) {
        $.post(queryEmployeeUrl, {emId: emId}, function (data) {
            if (data.flag) {
                $('#employee-name').val(data.data.name);
                $('#employee-loginName').val(data.data.loginName);
                loginName = data.data.loginName;
                selectSearch(data.data);
                if (data.data.status === 1) {
                    $("#status-on").attr("checked", true);
                    $("#status-off").removeAttr("checked");
                } else {
                    $("#status-off").attr("checked", true)
                    $("#status-on").removeAttr("checked")
                }
            }
        });

    } else {
        selectSearch();
    }


    $('#status-off').click(function () {
        $("#status-off").attr("checked", true)
        $("#status-on").removeAttr("checked")
    });

    $('#status-on').click(function () {
        $("#status-on").attr("checked", true)
        $("#status-off").removeAttr("checked")
    })

    //新增职位或者编辑
    $('#submit').click(function () {
        var employee = {}
        //如果是编辑就要传position_id
        if (is_edit) {
            employee.emId = emId;
        }
        employee.name = $('#employee-name').val();
        employee.loginName = $('#employee-loginName').val();
        employee.status = $("input[name='status'][checked]").val()
        employee.depId = $('#department-select').find('option').not(
            //option里面选中了的元素(不是没有选中元素)
            function () {
                return !this.selected;
            }
        ).data('value')
        employee.positionId = $('#position-select').find('option').not(
            //option里面选中了的元素(不是没有选中元素)
            function () {
                return !this.selected;
            }
        ).data('value')
        var formData = new FormData();
        formData.append('employeeStr', JSON.stringify(employee));
        formData.append('oldLoginName',JSON.stringify(loginName))
        $.ajax({
            url: is_edit ? editEmployeeUrl : addEmployeeUrl,
            type: "post",
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            async: false,
            success: function (data) {
                if (data.flag) {
                    lightyear.notify('操作成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                   window.location.href = '/employee/toEmployee';
                } else {
                    lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                }
            }
        });
    })

    //_____________________________________________________________________________
    //实时监控select



    $('#department-select').change(function () {
           var positionName = $(this).val();
           $.post(positionByDepartmentName,{positionName:positionName},function (data) {
               if(data.flag){
                   var positionList = data.data;
                   var optionHtml = '';
                       positionList.map(function (item, index) {
                           optionHtml += '<option data-value="' + item.positionId + '" >' + item.positionName + '</option>';
                       })
                       $('#position-select').html(optionHtml)
               }
           },"json")

    })


})
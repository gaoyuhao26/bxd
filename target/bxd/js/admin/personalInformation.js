$(function($) {
//查看员工信息url
    var queryEmployeeUrl = '/employee/queryEmployeeById';
//获取有效部门列表url
    var queryDepartmentListUrl = '/department/getDepartmentInfo';
//获取有效职位列表url
    var queryPositionListUrl = '/position/getPositionInfo';
    //修改个人信息
    var editEmployeeUrl = '/employee/editEmployee';
    //修改头像
    var upload  = '/personal/upload';
    //修改密码
    var updatePassword = '/personal/updatePassword';

//未修改之前的loginName
    var loginName = null;

    var fileUpdate_input = $('#fileUpdate_input');
    var emId = $('#employee-emId').val();


    $('#fileUpdate_button').click(
        function () {
            fileUpdate_input.trigger("click");
        }
    )

    /*显示职位和部门的选择列表*/
    function selectSearch(selectData) {
        selectData = selectData === undefined ? {} : selectData;
        //获取有效的部门列表和有效的职位列表
        $.post(queryDepartmentListUrl, function (data) {
            if (data.flag) {
                var departmentList = data.data;
                var optionHtml = '';
                departmentList.map(function (item, index) {
                    function showSelected(selectData) {
                        if (selectData.depId == item.depId) {
                            return '<option data-value="' + item.depId + '" selected="selected">' + item.name + '</option>';
                        } else {
                            return '<option data-value="' + item.depId + '">' + item.name + '</option>';
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
                        if (selectData.positionId == item.positionId) {
                            return '<option data-value="' + item.positionId + '" selected="selected">' + item.positionName + '</option>';
                        } else {
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
            }
        });

    } else {
        selectSearch();
    }

    //修改个人信息
    $('#submit').click(function () {
        var employee = {}
        employee.emId = emId;
        employee.name = $('#employee-name').val();
        employee.loginName = $('#employee-loginName').val();
        employee.depId = $('#department-select').find('option').not(
            //option里面选中了的元素(不是没有选中元素)
            function () {
                return !this.selected;
            }
        ).data('value');
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
            url:  editEmployeeUrl,
            type: "post",
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            async: false,
            success: function (data) {
                console.log(data)
                if (data.flag) {
                    lightyear.notify('操作成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                    // window.location.href = '/employee/toEmployee';
                } else {
                    lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                }
            }
        });
    })






    /*修改头像*/
    fileUpdate_input.change(function () {
        var file = fileUpdate_input[0].files[0];
        console.log(file);
        var formData = new FormData();
        formData.append("file", file);
        $.ajax({
            url: upload,//这里写你的url
            type: "post",
            dataType: 'json',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            async: false,
            success: function (data) {
                console.log(data)
                if (data.flag) {
                    lightyear.notify('操作成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                    window.location.href = '/personal/gopersonalInformation';
                } else {
                    lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                }
            }
        });
    });




    /*修改密码*/
   /* employee.password = $('#employee-password').val();*/

  $('#keep').click(function () {
      var password = $('#employee-password').val();
      var isPassword = $('#is-employee-password').val();
      if(password === isPassword){
          $.post(updatePassword,{emId:emId,password:password},function (data) {
              if(data.flag){
                  if (data.flag) {
                      lightyear.notify('操作成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                      window.location.href = '/main';
                  } else {
                      lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                  }
              }
          },"json");
      }else {
          lightyear.notify('两次输入的密码不一致', 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
      }

  })



})

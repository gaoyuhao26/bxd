$(function () {
    //初始化分页插件标志
    var flag = true;
    //请求封装的数据
    var reqeust_data = {};
    //获取有效部门列表url
    var queryDepartmentListUrl = '/department/getDepartmentInfo';
    //获取有效职位列表url
    var queryPositionListUrl = '/position/getPositionInfo';

    $('.search-bar .dropdown-menu a').click(function () {
        var field = $(this).data('field') || '';
        $('#search-field').val(field);
        $('#search-btn').html($(this).text() + ' <span class="caret"></span>');
    });

    $('#status-condition').change(function () {
        //重新加载分页插件
        flag = true;
        reqeust_data.pageNum = 1;

        //判断筛选状态
        if ($('#status-condition').is(':checked')) {
            reqeust_data.status = 1;
            getList(reqeust_data);
        } else {
            reqeust_data.status = null;
            getList(reqeust_data)
        }
    })

    $('#search-input').keydown(function (e) {
        if (e.keyCode == 13) {
            var keyword = $('#search-input').val();
            flag = true;
            reqeust_data.name = keyword;
            getList(reqeust_data)
        }
    })


    //动态渲染
    function handleList(data) {
        var i = 1;
        var html = '';
        data.list.map(function (item, index) {
            html += '<tr>'
                + '     <td>' + ((data.pageNum - 1) * data.pageSize + i++) + '</td>'
                + '     <td>' + (item.name) + '</td>'
                + '     <td>' + (item.loginName) + '</td>'
                + '     <td>' + (item.department.name) + '</td>'
                + '     <td>' + (item.position.positionName) + '</td>'
                + employeeStatus(item.status)
                + '     <td>'
                + '         <div class="btn-group">'
                + '         <a class="btn btn-xs btn-default" href="/employee/goEmployeeEdit?edit=true&emId=' + (item.emId) + '" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>'
                + '         <a class="btn btn-xs btn-default" href="/employee/goEmployee?emId=' + (item.emId) + '" title="查看" data-toggle="tooltip"><i class="mdi mdi-eye"></i></a>'
                + showEmployeeStatus(item.status, item.emId)
                + '         </div>'
                + '     </td>'
                + ' </tr>'
        })
        //覆盖
        $('.employee-wrap').html(html);
    }

    //分页模糊查询
    function getList(data) {
        data = data === undefined ? {} : data;
        $.ajax({
            url: "/employee/getList",
            type: "post",
            cache: false,
            async: true,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (data) {
                /*console.log(data);*/
                if (data.flag) {
                    handleList(data.data);
                }
                if (flag) {
                    getPageInfo(data.data);
                    flag = false;
                }
            }
        })
    }

    getList();
//获取有效的部门列表和有效的职位列表
    $.post(queryDepartmentListUrl, function (data) {
       console.log(data)
        if (data.flag) {
            var departmentList = data.data;
            var optionHtml = '<option data-value="">全部</option>';
            departmentList.map(function (item, index) {
                //<option value="0">请选择</option>
                optionHtml += '<option data-value="'
                    + item.depId + '">'
                    + item.name + '</option>'
            })
            $('#department-select').html(optionHtml)
        } else {
            //TODO:错误提示
        }
    })
    $('#department-select').change(function (e) {
        var opt = $('#department-select').find('option').not(function () {
            return !this.selected
        }).data('value')
        reqeust_data.depId = opt;
        flag = true;
        reqeust_data.pageNum = 1;
        getList(reqeust_data);
    })
    $.post(queryPositionListUrl, function (data) {
         console.log(data)
        if (data.flag) {
            var positionList = data.data;
            var optionHtml = '<option data-value="">全部</option>';
            positionList.map(function (item, index) {
                //<option value="0">请选择</option>
                optionHtml += '<option data-value="'
                    + item.positionId + '">'
                    + item.positionName + '</option>'
            })
            $('#position-select').html(optionHtml)
        }
    })
    $('#position-select').change(function (e) {
        var opt = $('#position-select').find('option').not(function () {
            return !this.selected
        }).data('value')
        reqeust_data.positionId = opt;
        flag = true;
        reqeust_data.pageNum = 1;
        getList(reqeust_data);
    })

    //状态文字化处理
    function employeeStatus(status) {
        if (status == 1) {
            return '<td><font class="text-success">在职</font></td>';
        }
        return '<td><font class="text-danger">离职</font></td>';
    }

    //修改状态
    function showEmployeeStatus(status, emId) {
        if (status == 1) {
            return '<a data-id=' + emId + ' data-status=' + status + ' class="btn btn-xs btn-default position-status-btn" href="#!" title="修改状态" data-toggle="tooltip" ><i class="mdi mdi-toggle-switch"></i></a>'
        }
        return '<a data-id=' + emId + ' data-status=' + status + ' class="btn btn-xs btn-default position-status-btn" href="#!" title="修改状态" data-toggle="tooltip" ><i class="mdi mdi-toggle-switch-off"></i></a>'
    }

    //点击切换状态
    $(".employee-wrap").on('click', 'a', function (e) {
        var target = $(e.currentTarget);
        if (target.hasClass('position-status-btn')) {
            var emId = e.currentTarget.dataset.id;
            var status = e.currentTarget.dataset.status;
            //如果选择只显示有效状态的列表,那么某个条目的状态修改为失效,可能会造成总页数减少,所以需要重新加载分页插件,而且要跳转到第一页,避免造成当前页不是合理的值
            if (reqeust_data.status === 1) {
                reqeust_data.pageNum = 1
                flag = true
            }
            $.ajax({
                url: "/employee/toggleEmployeeStatus",
                type: "post",
                cache: false,
                async: true,
                dataType: 'json',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify({emId: emId, status: status}),
                success: function (data) {
                    getList(reqeust_data);
                    if (data.flag) {
                        lightyear.notify('修改成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                    } else {
                        lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                    }
                }
            });
        }
    })

    //分页
    function getPageInfo(data) {
        $('.pagination').pagination({
            pageCount: data.pages,
            coping: true,
            callback: function (e) {
                reqeust_data.pageNum = e.getCurrent();
                getList(reqeust_data);
            }
        })
    }

})
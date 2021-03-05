$(function () {
    //初始化分页插件标志
    var flag = true;
    //请求封装的数据
    var reqeust_data = {}

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
            var type = $('#search-field').val();
            var keyword = $('#search-input').val();
            flag = true;
            if (type == "name") {
                reqeust_data.name = keyword
                reqeust_data.address = null
            } else {
                reqeust_data.address = keyword
                reqeust_data.name = null
            }
            getList(reqeust_data)
        }
    })


    //初始化列表
    getList();


    function getList(data) {
        data = data === undefined ? {} : data;
        $.ajax({
            url: "/department/getList",
            type: "post",
            cache: false,
            async: false,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (data) {
                if (data.flag) {
                    //将获取到的数据动态渲染
                    handleList(data.data);
                    console.log(data)
                    if (flag) {
                        getPageInfo(data.data);
                        flag = false;
                    }

                } else {
                    lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                }
            }
        })
    }

    //动态渲染
    function handleList(data) {
        var i = 1;
        var html = '';
        data.list.map(function (item, index) {
            html += '<tr>'
                + '     <td>' + ((data.pageNum - 1) * data.pageSize + i++)   + '</td>'
                + '     <td>' + (item.name) + '</td>'
                + '     <td title="'+(item.address)+'" data-toggle="tooltip">' + (item.address) + '</td>'
                + departmentStatus(item.status)
                + '     <td>'
                + '         <div class="btn-group">'
                + '         <a class="btn btn-xs btn-default" href="/department/goDepartmentEdit?edit=true&depId=' + (item.depId) + '" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>'
                + '         <a class="btn btn-xs btn-default" href="/department/goDepartment?depId=' + (item.depId) + '" title="查看" data-toggle="tooltip"><i class="mdi mdi-eye"></i></a>'
                + showDepartmentStatus(item.status, item.depId)
                + '         </div>'
                + '     </td>'
                + ' </tr>'
        })
        //覆盖
        $('.department-wrap').html(html);
    }

    //状态文字化处理
    function departmentStatus(status) {
        if (1 == status) {
            return '<td><font class="text-success">有效</font></td>';
        }
        return '<td><font class="text-danger">失效</font></td>';
    }

    //修改状态
    function showDepartmentStatus(status, depId) {
        if (status == 1) {
            return '<a data-id=' + depId + ' data-status=' + status + ' class="btn btn-xs btn-default department-status-btn" href="#!" title="修改状态" data-toggle="tooltip" ><i class="mdi mdi-toggle-switch"></i></a>'
        }
        return '<a data-id=' + depId + ' data-status=' + status + ' class="btn btn-xs btn-default department-status-btn" href="#!" title="修改状态" data-toggle="tooltip" ><i class="mdi mdi-toggle-switch-off"></i></a>'
    }

    $(".department-wrap").on('click', 'a', function (e) {
        var target = $(e.currentTarget);
        if (target.hasClass('department-status-btn')) {
            var depId = e.currentTarget.dataset.id;
            var status = e.currentTarget.dataset.status;
            //如果选择只显示有效状态的列表,那么某个条目的状态修改为失效,可能会造成总页数减少,所以需要重新加载分页插件,而且要跳转到第一页,避免造成当前页不是合理的值
            if (reqeust_data.status === 1) {
                reqeust_data.pageNum = 1
                flag = true
            }
            $.ajax({
                url: "/department/toggleDepartmentStatus",
                type: "post",
                cache: false,
                async: true,
                dataType: 'json',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify({depId: depId, status: status}),
                success: function (data) {
                    // //从cookie中取得当前页
                    // console.log(document.cookie);
                    // var cookies = document.cookie.split(";")
                    // $.each(cookies,function (index,item) {
                    //     var names = item.split("=")
                    //     if ("currentPage"===names[0]) {
                    //         getList({pageNum:names[1]});
                    //     }
                    // })
                    getList(reqeust_data);
                    if (data.flag) {
                        lightyear.notify('修改成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                        // getList();
                    } else {
                        lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                        // getList();
                    }
                }
            })
        }
    })

    //分页数据
    function getPageInfo(data) {
        $('.pagination').pagination({
            pageCount: data.pages,
            coping: true,
            callback: function (e) {
                reqeust_data.pageNum = e.getCurrent();
                //cookie : 名称=内容 ; 名称=key:value .. ;
                // document.cookie = "currentPage="+ e.getCurrent();
                getList(reqeust_data);
            }
        })
    }
})
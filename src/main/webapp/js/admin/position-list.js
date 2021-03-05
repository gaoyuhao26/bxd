$(function () {
    //初始化分页插件标志
    var flag = true;
    //请求封装的数据
    var reqeust_data = {};
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
      /*      var type = $('#search-field').val();*/
            var keyword = $('#search-input').val();
            flag = true;
           /* if (type == "name") {
                reqeust_data.name = keyword
                reqeust_data.address = null
            } else {
                reqeust_data.address = keyword
                reqeust_data.name = null
            }*/
            reqeust_data.positionName = keyword;
            getList(reqeust_data)
        }
    })

    getList();
    function getList(data) {
        data = data === undefined ? {} : data;
        $.ajax({
            url:"/position/getList",
            type:"post",
            cache: false,
            async: false,
            dataType:"json",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function (data) {
                console.log(data);
                if (data.flag) {
                    //将获取到的数据动态渲染
                    handleList(data.data);
                    if (flag) {
                        getPageInfo(data.data);
                        flag = false;
                    }

                } else {
                    lightyear.notify("获取失败", 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
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
                + '     <td>' + ((data.pageNum - 1) * data.pageSize + i++) + '</td>'
                + '     <td>' + (item.positionName) + '</td>'
                + '     <td>' + (item.createTime) + '</td>'
                + departmentStatus(item.status)
                + '     <td>'
                + '         <div class="btn-group">'
                + '         <a class="btn btn-xs btn-default" href="/position/goPositionEdit?edit=true&positionId=' + (item.positionId) + '" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>'
                + '         <a class="btn btn-xs btn-default" href="/position/goPosition?positionId=' + (item.positionId) + '" title="查看" data-toggle="tooltip"><i class="mdi mdi-eye"></i></a>'
                + showDepartmentStatus(item.status, item.positionId)
                + '         </div>'
                + '     </td>'
                + ' </tr>'
        })
        //覆盖
        $('.position-wrap').html(html);
    }
    //状态文字化处理
    function departmentStatus(status) {
        if (1 == status) {
            return '<td><font class="text-success">有效</font></td>';
        }
        return '<td><font class="text-danger">失效</font></td>';
    }
    //修改状态
    function showDepartmentStatus(status, positionId) {
        if (status == 1) {
            return '<a data-id=' + positionId + ' data-status=' + status + ' class="btn btn-xs btn-default position-status-btn" href="#!" title="修改状态" data-toggle="tooltip" ><i class="mdi mdi-toggle-switch"></i></a>'
        }
        return '<a data-id=' + positionId + ' data-status=' + status + ' class="btn btn-xs btn-default position-status-btn" href="#!" title="修改状态" data-toggle="tooltip" ><i class="mdi mdi-toggle-switch-off"></i></a>'
    }
    $('.position-wrap').on('click','a',function (t) {
        var target = $(t.currentTarget);
        if(target.hasClass('position-status-btn')){
            var positionId = t.currentTarget.dataset.id;
            var status = t.currentTarget.dataset.status;
            if(reqeust_data.status === 1){
                reqeust_data.pageNum = 1;
                flag = true;
            }
            $.ajax({
                url:'/position/togglePositionStatus',
                type:'post',
                cache: false,
                async: true,
                dataType: 'json',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify({positionId:positionId,status:status}),
                success: function (data) {
                    console.log(data.data);
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
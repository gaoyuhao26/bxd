$(function () {
    //查看报销单列表
    var getExpenseReportList = '/expenseReport/getExpenseReportList';
    //初始化分页插件标志
    var flag = true;
    //请求封装的数据
    var reqeust_data = {}


    $('.search-bar .dropdown-menu a').click(function () {
        var field = $(this).data('field') || '';
        $('#search-field').val(field);
        $('#search-btn').html($(this).text() + ' <span class="caret"></span>');
        $('#search-input').attr('placeholder', '请输入' + $(this).text());
    });
    //切换输入框模糊查询
    $('#search-input').keydown(function (e) {
        if (e.keyCode == 13) {
            var type = $('#search-field').val();
            var keyword = $('#search-input').val();
            flag = true;
            if (type == "cause") {
                reqeust_data.cause = keyword
                reqeust_data.emName = null
            } else {
                reqeust_data.emName = keyword
                reqeust_data.cause = null
            }
            getList(reqeust_data)
        }
    })
    //获取指定时间内的待处理报销单


    $('#example-daterange1').change(function () {
        reqeust_data.createTimeDown = $('#example-daterange1').val();
        reqeust_data.createTimeUp = $('#example-daterange2').val();
        reqeust_data.pageNum = 1;
        flag = true;
        getList(reqeust_data);
    })
    $('#example-daterange2').change(function () {
        reqeust_data.createTimeDown = $('#example-daterange1').val();
        reqeust_data.createTimeUp = $('#example-daterange2').val();
        reqeust_data.pageNum = 1;
        flag = true;
        getList(reqeust_data);
    })

    //获取用户名
    var nextDealEmName = $('#next_deal-emId').val();
    reqeust_data.emName = nextDealEmName
    function getList(data) {
        data = data === undefined ? reqeust_data : data;
        $.ajax({
            url: getExpenseReportList,
            type: "post",
            cache: false,
            async: true,
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            success: function (data) {
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

    getList();

    //动态渲染
    function showExpenseReportDetailList(data) {
        var html = '';
        data.expenseReportDetailList.map(function (item, index) {
            //  console.log(item)
            html += '<span>'
                + '报销项目:' + (item.item) + ','
                + '费用明细:' + (item.amount) + ','
                + '费用备注:' + (item.comment) + ';</span>'
        })
        return html;
    }
    function handleList(data) {
        var i = 1;
        var html = '';
        data.list.map(function (item, index) {
            html += '<tr>'
                + '     <td>' + ((data.pageNum - 1) * data.pageSize + i++) + '</td>'
                + '     <td>' + (item.cause) + '</td>'
                + '     <td>' + (item.nextDealEmName) + '</td>'
                + '     <td>' + (item.createTime) + '</td>'
                + '     <td>￥' + (parseFloat(item.totalAmount).toFixed(2)) + '</td>'
                + '     <td>' + showExpenseReportDetailList(item) + '</td>'
                + '     <td style="color: ' + ((item.status == '通过') ? '#0FB25F;' : 'red;') + '">' + (item.status) + '</td>'
                + '     <td>'
                + '         <div class="btn-group">'
                + '           <a class="btn btn-xs btn-default" href="/expenseReport/goPersonalExpenseReportInfo?expenseId=' + (item.expenseId) + '" title="查看" data-toggle="tooltip"><i class="mdi mdi-eye"></i></a>'
                + '         </div>'
                + '     </td>'
                + ' </tr>'
        })
        //覆盖
        $('.expense-report-wrap').html(html);
    }

    /* //获取用户名
     var nextDealEmName = $('#next_deal-name').val();
     $.post('/expenseReport/getExpenseReportList',{nextDealEmName:nextDealEmName},function (data) {
         if(data.flag){

            $('#expense-report-wrap').html();
         }
     },"json")*/

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
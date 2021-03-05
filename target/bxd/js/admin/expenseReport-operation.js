$(function () {
    //获取创建人下拉列表
    var getFounder = '/employee/getEmployeeInfo';
    //获取待处理人下拉列表
    var getnextDealEm = '/employee/getnextDealEm';
    //存储报销单细节的List
    var expenseReportDetailList = [];
    //总金额
    var totalAmount = 0;
    //Amount的正则规则
    var regAmount = /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/;
    //从url中获取员工Id
    var expenseId = getURLString('expenseId');
 /*   //从url中获取edit,判断是否是编辑操作
    var is_edit = getURLString('edit');*/
    $('#status-off').click(function () {
        $("#status-off").attr("checked", true)
        $("#status-on").removeAttr("checked")
    });

    $('#status-on').click(function () {
        $("#status-on").attr("checked", true)
        $("#status-off").removeAttr("checked")
    })


    var append = function (data) {
        //根据当前数组的长度插入新的数据
        expenseReportDetailList[expenseReportDetailList.length] = data;
    }

    //渲染添加的报销单细节列表
    function showexpenseReportDetailList() {
        var html = '';
        var i = 1;
        expenseReportDetailList.map(function (item, index) {
            html += '<tr>'
                + '<td>' + (i++) + '</td>'
                + '<td>' + item.item + '</td>'
                + '<td>' + item.amount + '</td>'
                + '<td>' + item.comment + '</td>'
                + '     <td>'
                + '         <div class="btn-group">'
                /*                           + '         <a class="btn btn-xs btn-default" href="/employee/goEmployeeEdit?edit=true&emId=' + (item.emId) + '" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>'*/
                + '         <a class="btn btn-xs btn-default" data-id=' + i + ' title="删除" data-toggle="tooltip" '+ (expenseId?"disabled":"") +'><i class="mdi mdi-close"></i></a>'
                + '         </div>'
                + '     </td>'
                + '</tr>';
        })
        $('#show-expenseReportDetail').html(html);
        $('.expense-report-table').show();
        $('#expenseReport-totalAmount').val(totalAmount)
    }

    //删除标签触发事件
    $('#show-expenseReportDetail').on('click', 'a', function (e) {
        expenseReportDetailList.splice(e.currentTarget.dataset.id - 2, 1);
        showexpenseReportDetailList();
    })

    //正则判断输入费用明细是否位number
    function showWarn(amount, placeholder) {
        amount.css("border", "1px solid red");
        amount.next().show().html(placeholder);
    }

    function checkPosName() {
        var amount = $(".amount").val();
        var placeholder1 = '费用明细不能为空';
        var placehodeer2 = '输入格式错误,请输入输入数字';
        var flag = regAmount.test(amount);
        if (flag) {
            $(".amount").css("border", "1px solid green");
            $(".amount").next().hide();
        } else {
            if (amount.length <= 0 || amount == '') {
                showWarn($(".amount"), placeholder1);
            } else {
                showWarn($(".amount"), placehodeer2);
            }
        }
        return flag;
    }

    function selectSearch(selectData) {
        selectData = selectData === undefined ? {} : selectData;
        //获取创建人下拉列表
        $.post(getFounder, function (data) {
            if (data.flag) {
                var employeeList = data.data;
                var html = '';
                employeeList.map(function (item, index) {
                    function showSelected(selectData) {
                        if (selectData.emId == item.emId) {
                            return '<option data-value="' + item.emId + '" selected="selected">' + item.name + '</option>';
                        } else {
                            return '<option data-value="' + item.emId + '" >' + item.name + '</option>';
                        }
                    }

                    html += showSelected(selectData)
                })
                $('#employee-name-select').html(html);
            }
        }, "json")

        //获取待处理人下拉列表
        $.post(getnextDealEm, function (data) {
            if (data.flag) {
                var employeeList = data.data;
                var html = '';
                employeeList.map(function (item, index) {
                    function showSelected(selectData) {
                        if (selectData.nextDealEm == item.emId) {
                            return '<option data-value="' + item.emId + '"  selected="selected">' + item.name + '</option>';
                        } else {
                            return '<option data-value="' + item.emId + '" >' + item.name + '</option>';
                        }
                    }
                    html += showSelected(selectData)
                })
                $('#nextDealEm-select').html(html);
            }
        }, "json")
    }


    //弹出添加报销单细节对话框
    function dialogBox() {
        $('.example-p-3').on('click', function () {
            $.confirm({
                title: '报销单细节',
                content: '' +
                    '<form action="" class="formName">' +
                    '<div class="form-group">' +
                    '<label>报销项目</label>' +
                    '<input type="text" placeholder="请输入报销项目" class="item form-control" required />' +
                    '</div>' +
                    '<div class="form-group">' +
                    '<label>费用明细</label>' +
                    '<input type="text" placeholder="请输入费用明细"  class="amount form-control" required />' +
                    '<label style="font-size: 10px; color: #ff0000; "></label>' +
                    '</div>' +
                    '<div class="form-group">' +
                    '<label>费用备注</label>' +
                    '<input type="text" placeholder="请输入费用备注" class="comment form-control" required />' +
                    '</div>' +
                    '</form>',
                buttons: {
                    formSubmit: {
                        text: '提交',
                        btnClass: 'btn-blue',
                        action: function () {
                            var item = this.$content.find('.item').val();
                            var amount = this.$content.find('.amount').val();
                            var comment = this.$content.find('.comment').val();
                            var expenseReportDetail = {item: item, amount: amount, comment: comment};
                            var flag = regAmount.test(amount);
                            if (flag) {
                                totalAmount += parseFloat(amount);
                                append(expenseReportDetail);
                                showexpenseReportDetailList();
                            } else {
                                lightyear.notify('添加报销单细节失败', 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                            }
                        }
                    },
                    cancel: {
                        text: '取消'
                    }
                },

                onContentReady: function () {
                    $('.amount').blur(checkPosName)
                    this.$content.find('form').on('submit', function (e) {
                        e.preventDefault();
                        jc.$$formSubmit.trigger('click');

                    });
                },

            });
        });
    }

    dialogBox();
    //通过expenseId查询报销单细节
    function queryPersonalExpenseReportDetial() {
        $.post('/expenseReportDetial/queryPersonalExpenseReportDetial',{expenseId: expenseId}, function (data) {
            if (data.flag) {
                expenseReportDetailList = data.data;
                showexpenseReportDetailList()
            }
        },"json")
    };
    if (expenseId) {
        $.post('/expenseReport/queryPersonalExpenseReport', {expenseId: expenseId}, function (data) {
            if (data.flag) {
                $('#expenseReport-cause').val(data.data.cause);
                totalAmount = data.data.totalAmount
                selectSearch(data.data);
                queryPersonalExpenseReportDetial();
            }
        });
    } else {
        selectSearch();
    }

    //弹出对话框,提示是否改变待处理人
    function dialogtoChangenextDealEm() {
        $.confirm({
            title: '待处理人权限不足',
            content: '确定更改待处理人吗？',
            type: 'orange',
            buttons: {
                confirm: {
                    text: '确认',
                    action: function(){
                       $.post('/expenseReport',{expenseId:expenseId},function (data) {
                            if(data.flag){
                                lightyear.notify('更改成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                                window.location.href = '/expenseReport/toExpenseReportListPending';
                            }else {
                                lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                            }
                       },"json")
                    }
                },
                cancel: {
                    text: '关闭',
                }
            }
        });
    }

    $('#submit').click(function () {
        if(expenseId){
            var status = $("input[name='status'][checked]").val();

            if((totalAmount >= 3000) && ($('#userId').html() != 1)){
                //弹出对话框,提示是否改变待处理人
                dialogtoChangenextDealEm();
            }else {
                $.post('/expenseReport/toggleExpenseReportStatus',{expenseId:expenseId,status:status},function (data) {
                    if(data.flag){
                        lightyear.notify('操作成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                        window.location.href = '/expenseReport/toExpenseReportListPending';
                    }else {
                        lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                    }
                },"json")
            }

        }else {
            var expenseReport = {};
            expenseReport.cause = $('#expenseReport-cause').val();
            expenseReport.emId = $('#employee-name-select').find('option').not(
                //option里面选中了的元素(不是没有选中元素)
                function () {
                    return !this.selected;
                }
            ).data('value')
            expenseReport.nextDealEm = $('#nextDealEm-select').find('option').not(
                //option里面选中了的元素(不是没有选中元素)
                function () {
                    return !this.selected;
                }
            ).data('value')
            expenseReport.totalAmount = totalAmount;
            expenseReport.expenseReportDetailList = expenseReportDetailList;
            var formData = new FormData();
            formData.append('expenseReportStr', JSON.stringify(expenseReport));
            console.log(!expenseReportDetailList)
            if (expenseReportDetailList) {
                $.ajax({
                    url: '/expenseReport/expenseReportAdd',
                    type: 'post',
                    dataType: 'json',
                    data: formData,
                    contentType: false,
                    processData: false,
                    cache: false,
                    async: false,
                    success: function (data) {
                        if (data.flag) {
                            lightyear.notify('操作成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center');
                            window.location.href = '/expenseReport/toexpenseReportAdd';
                        } else {
                            lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
                        }
                    }
                })
            } else {
                lightyear.notify('报销单细节不能为空', 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
            }
            }
    })
})
$(function () {
    //获取创建人下拉列表
    var getFounder = '/employee/getEmployeeInfo';
    //获取待处理人下拉列表
    var getnextDealEm = '/employee/getnextDealEm';
    //存储报销单细节的List
    var expenseReportDetailList = [];
    //从url中获取员工Id
    var expenseId = getURLString('expenseId');

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
                + '</tr>';
        })
        $('#show-expenseReportDetail').html(html);
    }



    //创建人和待处理人下拉框
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
                $('#expenseReport-totalAmount').val(data.data.totalAmount);
                selectSearch(data.data);
                $('#status').val(data.data.status);
                queryPersonalExpenseReportDetial();
            }
        });

    } else {
        lightyear.notify("获取失败", 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center');
    }

})
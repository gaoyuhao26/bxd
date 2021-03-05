<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>填写报销单</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/css/style.min.css" rel="stylesheet">
    <!--对话框-->
    <link rel="stylesheet" href="/js/jconfirm/jquery-confirm.min.css">
    <link href="/css/style.min.css" rel="stylesheet">
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <#--   头部信息  -->
        <#include "topbar.ftl" />
        <#--   侧边栏  -->
        <#include "slider.ftl"/>

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">

                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <label for="expenseReport-cause">报销类型</label>
                                        <input type="text" class="form-control" id="expenseReport-cause"
                                               name="expenseReport-cause"
                                               value="" placeholder="请输入报销类型"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="employee-name-select">创建人</label>
                                        <select class="form-control" id="employee-name-select"
                                                name="employee-name-select"
                                                size="1">
                                            <option value="0">请选择</option>
                                            <option value="1">选项 #1</option>
                                            <option value="2">选项 #2</option>
                                            <option value="3">选项 #3</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="nextDealEm-select">待处理人</label>
                                        <select class="form-control" id="nextDealEm-select" name="nextDealEm-select"
                                                size="1">
                                            <option value="0">请选择</option>
                                            <option value="1">选项 #1</option>
                                            <option value="2">选项 #2</option>
                                            <option value="3">选项 #3</option>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-12">
                                        <label for="nextDealEm-select">报销单细节</label>
                                            <table class="table table-bordered expense-report-table" hidden>
                                                <thead>
                                                <tr>
                                                    <th>序号</th>
                                                    <th>报销项目</th>
                                                    <th>费用明细</th>
                                                    <th>费用备注</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <#--     列表数据     -->
                                                <tbody id="show-expenseReportDetail">
                                                </tbody>
                                            </table>
                                    </div>
                                    <div class="col-md-2">
                                        <button class="btn btn-primary btn-block example-p-3">点击填写报销单细节</button> <br>
                                    </div>

                                    <div class="form-group col-md-12">
                                        <label for="expenseReport-totalAmount">报销总金额</label>
                                        <input type="text" class="form-control" id="expenseReport-totalAmount"
                                               name="expenseReport-totalAmount"
                                               value="" disabled/>
                                    </div>
                                    <#--<div class="form-group col-md-12">
                                        <label for="status">状态</label>
                                        <input type="text" class="form-control" id="status" name="status"
                                               value="无效" disabled/>
                                    </div>-->
                                    <div class="form-group col-md-12">
                                        <a id="submit" type="submit" class="btn btn-primary">确 定</a>
                                        <a type="button" class="btn btn-default"
                                           onclick="javascript:history.back(-1);return false;">返 回</a>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>

<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/js/main.min.js"></script>
<!--消息提示-->
<script src="/js/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="/js/lightyear.js"></script>
<script type="text/javascript" src="/js/admin/common.js"></script>
<!--对话框-->
<script src="/js/jconfirm/jquery-confirm.min.js"></script>
<script type="text/javascript" src="/js/main.min.js"></script>
<script type="text/javascript" src="/js/admin/expenseReport-operation.js"></script>
</body>
</html>
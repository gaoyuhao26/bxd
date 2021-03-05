<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>待处理报销单列表</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/css/style.min.css" rel="stylesheet">
    <link href="/css/pagination.css" rel="stylesheet">
    <!--日期选择插件-->
    <link rel="stylesheet" href="/js/bootstrap-datepicker/bootstrap-datepicker3.min.css">
    <link href="/css/style.min.css" rel="stylesheet">
    <style>
        table{
            table-layout: fixed !important;
        }
        td{
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>
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
                            <div class="card-toolbar clearfix">
                                <div class="pull-right search-bar">
                                    <div class="input-group m-l-10">
                                        <div class="input-group-btn">
                                            <input type="hidden" name="search_field" id="search-field" value="cause">
                                            <button class="btn btn-default dropdown-toggle" id="search-btn"
                                                    data-toggle="dropdown" type="button" aria-haspopup="true"
                                                    aria-expanded="false">
                                                报销类型 <span class="caret"></span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a tabindex="-1" href="javascript:void(0)" data-field="cause">报销类型</a>
                                                </li>
                                                <li><a tabindex="-1" href="javascript:void(0)"
                                                       data-field="emName">创建人</a>
                                                </li>
                                            </ul>
                                        </div>
                                        <input id='search-input' type="text" class="form-control" value="" name="keyword"
                                               placeholder="请输入报销类型">
                                    </div>
                                </div>
                                <div class="pull-right m-r-15">
                                    <div class="input-daterange input-group js-datepicker" data-auto-close="false" data-date-format="yyyy-mm-dd">
                                        <input class="form-control" type="text" id="example-daterange1" name="example-daterange1" placeholder="从">
                                        <span class="input-group-addon"><i class="mdi mdi-chevron-right"></i></span>
                                        <input class="form-control" type="text" id="example-daterange2" name="example-daterange2" placeholder="至">
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <input id="next_deal-emId" value="${Session["user"].name}"  hidden/>
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th width="50px">编号</th>
                                            <th>报销类型</th>
                                            <th>创建人</th>
                                            <th>创建时间</th>
                                            <th>报销总金额</th>
                                            <th>内容</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <#--     列表数据     -->
                                        <tbody class="expense-report-wrap">
                                        </tbody>
                                    </table>
                                </div>
                                <#--     分页     -->
                                <ul class="pagination m-style">
                                </ul>

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
<script type="text/javascript" src="/js/jquery.pagination.js"></script>
<!--日期选择插件-->
<script src="/js/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
<script src="/js/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="/js/main.min.js"></script>
<script type="text/javascript" src="/js/admin/expenseReport-list.js"></script>
</body>
</html>
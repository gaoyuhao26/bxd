<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>用户列表</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="/css/style.min.css" rel="stylesheet">
    <link href="/css/pagination.css" rel="stylesheet">
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
                                    <div class="input-group">
                                        <div class="input-group-btn">
<#--                                            <input type="hidden" name="search_field" id="search-field" value="name">-->
<#--                                            <button class="btn btn-default dropdown-toggle" id="search-btn"-->
<#--                                                    data-toggle="dropdown" type="button" aria-haspopup="true"-->
<#--                                                    aria-expanded="false">-->
<#--                                                名称 <span class="caret"></span>-->
<#--                                            </button>-->
<#--                                            <ul class="dropdown-menu">-->
<#--                                                <li><a tabindex="-1" href="javascript:void(0)" data-field="name">名称</a>-->
<#--                                                </li>-->
<#--                                                <li><a tabindex="-1" href="javascript:void(0)"-->
<#--                                                       data-field="address">地址</a></li>-->
<#--                                            </ul>-->
                                        </div>
                                        <input id='search-input' type="text" class="form-control" value="" name="keyword"
                                               placeholder="请输入名称">
                                    </div>
                                </div>
                                <div class="pull-right m-10">
                                    <label class="lyear-switch switch-solid switch-primary">
                                        <input id="status-condition" type="checkbox" >筛选状态
                                        <span></span>
                                    </label>
                                </div>
                                <div class="pull-right m-10">
                                    <label>
                                        筛选部门
                                        <select id="department-select" name="department-select" size="1">
                                            <option value="0">请选择</option>
                                            <option value="1">选项 #1</option>
                                            <option value="2">选项 #2</option>
                                            <option value="3">选项 #3</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="pull-right m-10">
                                    <label>
                                        筛选职位
                                        <select id="position-select" name="position-select" size="1">
                                            <option value="0">请选择</option>
                                            <option value="1">选项 #1</option>
                                            <option value="2">选项 #2</option>
                                            <option value="3">选项 #3</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="toolbar-btn-action">
                                    <a class="btn btn-primary m-r-5" href="/employee/toAddEmployee"><i class="mdi mdi-plus"></i> 新增</a>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>编号</th>
                                            <th>姓名</th>
                                            <th>登录账号(手机号)</th>
                                            <th>所属部门</th>
                                            <th>职务</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <#--     列表数据     -->
                                        <tbody class="employee-wrap">
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
<script type="text/javascript" src="/js/jquery.pagination.js"></script>
<script type="text/javascript" src="/js/admin/employee-list.js"></script>
</body>
</html>
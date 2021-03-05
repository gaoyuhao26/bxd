<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>新增职位</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/materialdesignicons.min.css" rel="stylesheet">
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

                                <div  class="row">
                                    <div class="form-group col-md-12">
                                        <label for="pos-name">职位名称</label>
                                        <input type="text" class="form-control" id="pos-name" name="pos-name" value="" placeholder="请输入职位名称" />
                                        <span  class="label label-danger"></span>
                                    </div>

                                    <div class="form-group col-md-12">
                                        <label for="status">状态</label>
                                        <div id="radioes" class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input id="status-off" type="radio" name="status" value="0"><span>禁用</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input id="status-on" type="radio" name="status" value="1" checked><span>启用</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <a id="submit" type="submit" class="btn btn-primary">确 定</a>
                                        <a type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">返 回</a>
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
<script type="text/javascript" src="/js/admin/position-operation.js"></script>
<script type="text/javascript" src="/js/admin/common.js"></script>
</body>
</html>
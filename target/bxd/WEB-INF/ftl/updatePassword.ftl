<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <title>修改密码</title>
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

                                <input id="employee-emId" value="${Session["user"].emId}"  hidden/>
                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <label for="employee-name">密码</label>
                                        <input type="text" class="form-control" id="employee-password"
                                               name="employee-password" value="" placeholder="请输入密码"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="employee-name">确认密码</label>
                                        <input type="text" class="form-control" id="is-employee-password"
                                               name="is-employee-password" value="" placeholder="请输入确认密码"/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <a id="keep" type="submit" class="btn btn-primary">保存</a>
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
<script type="text/javascript" src="/js/admin/personalInformation.js"></script>

</body>
</html>
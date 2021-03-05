<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
  <title>个人信息</title>
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
                <div class="edit-avatar">
                  <img src=" <#if Session["user"]?exists>
                                        ${"/images/uploads/"+Session["user"].headPortrait}
                                   <#else>
                                       ${"/images/users/avatar.jpg"}
                                   </#if>" alt="..." class="img-avatar">
                  <div class="avatar-divider"></div>
                  <div class="edit-avatar-content">
                    <button class="btn btn-default" id="fileUpdate_button">修改头像</button>
                      <input id="fileUpdate_input" type="file" name="file"  style="display: none;" ref="files" />
                    <p class="m-0">选择一张你喜欢的图片，裁剪后会自动生成264x264大小，上传图片大小不能超过2M。</p>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <input id="employee-emId" value="${Session["user"].emId}"  hidden/>
                  <div class="form-group col-md-12">
                    <label for="employee-name">员工姓名</label>
                    <input type="text" class="form-control" id="employee-name" name="employee-name"
                           value="" placeholder="请输入员工姓名"  />
                  </div>
                  <div class="form-group col-md-12">
                    <label for="employee-loginName">登录账号(手机号)</label>
                    <input type="text" class="form-control" id="employee-loginName"
                           name="employee-loginName" value="" placeholder="请输入手机号" />
                  </div>
                  <div class="form-group col-md-12">
                    <label for="department-select">所属部门</label>
                    <select class="form-control" id="department-select" name="department-select"
                            size="1"  >
                      <option value="0">请选择</option>
                      <option value="1">选项 #1</option>
                      <option value="2">选项 #2</option>
                      <option value="3">选项 #3</option>
                    </select>
                  </div>
                  <div class="form-group  col-md-12">
                    <label for="position-select">担任职位</label>
                    <select class="form-control" id="position-select" name="position-select"
                            size="1" >
                      <option value="0">请选择</option>
                      <option value="1">选项 #1</option>
                      <option value="2">选项 #2</option>
                      <option value="3">选项 #3</option>
                    </select>
                  </div>
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
<script type="text/javascript" src="/js/admin/personalInformation.js"></script>

</body>
</html>
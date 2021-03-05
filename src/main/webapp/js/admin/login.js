$(function () {
    //登录验证的controller的url
    var loginUrl = 'checkLogin';
    //登录次数,累计超过3次登陆失败后,显示验证码
    var loginCount = 0;
    function login(){
        //获取输入的账号
        var userName = $('#username').val();
        //获取输入的密码
        var password = $('#password').val();
        //获取输入的验证码
        var verifyCodeActual = $('#j_captcha').val();
        //是否需要验证码校验
        var needVerify = false;
        //如果失败登录次数达到3次
        if(loginCount >= 3){
            needVerify = true;
            if(!verifyCodeActual){
                lightyear.notify('请输入验证码~', 'warning', 100, 'mdi mdi-emoticon-sad', 'top', 'center' );
                return;
            }
        }
        $.ajax({
            url:loginUrl,
            type:"post",
            cache: false,
            async: false,
            dataType: 'json',
            contentType :"application/json",
            data: JSON.stringify({
                userName: userName,
                password: password,
                verifyCodeActual: verifyCodeActual,
                needVerify: needVerify
            }),
            success: function (data) {
                if (data.flag) {
                    console.log(data);
                    lightyear.notify('登陆成功~', 'success', 100, 'mdi mdi-emoticon-happy', 'top', 'center' );
                    window.location.href = '/main';
                } else {
                    lightyear.notify(data.msg, 'danger', 100, 'mdi mdi-emoticon-sad', 'top', 'center' );
                    loginCount++;
                    if (loginCount >= 3) {
                        $('#verifyPart').show();
                        $('#captcha').click();
                    }
                }
            }
        })
    }
    $('#submit').click(function () {
       login();
    })
    $('#password').keydown(function (e) {
        if(e.keyCode == 13){
            login();
        }
    })
});



//更换验证码图片
function chageVerificationCode(img) {
    img.src = "/Kaptcha?"+Math.floor(Math.random()*100);
}
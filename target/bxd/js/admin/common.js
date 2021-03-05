//获取url中对应name的值
function getURLString(name) {
    var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)")
    var result =  window.location.search.substr(1).match(reg);
    if (result != null) {
        return decodeURIComponent(result[2])
    }
    return '';
}
layer.errorAlert = function(message){
    layer.alert(message, {
        icon: 0
        ,skin: 'demo-class'
        ,closeBtn: 0
        ,anim: 4 //动画类型
        ,offset: '100px'
    });
};

layer.okAlert = function(message){
    layer.alert(message, {
        skin: 'layui-layer-lan'
        ,closeBtn: 0
        ,anim: 4 //动画类型
        ,offset: '100px'
    });
};

layer.infoConfirm = function(message,ok,cancel){
    let haveOk = typeof(ok) == "undefined";
    let haveCancel = typeof(cancel) == "undefined";
    if(haveOk && haveCancel){
        layer.confirm(message, {
            btn: ['确定','取消'] //按钮
            ,offset: '100px'
        },ok,cancel);
    }else if(haveOk){
        layer.confirm(message, {
            btn: ['确定','取消'] //按钮
            ,offset: '100px'
        },ok);
    }else{
        layer.confirm(message, {
            btn: ['确定','取消'] //按钮
            ,offset: '100px'
        });
    }

};

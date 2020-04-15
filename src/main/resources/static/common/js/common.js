
function postJson(url,data,success,error) {
    $.ajax({
        url: url,
        method: "post",
        dataType: 'json',
        data: JSON.stringify(data),
        success: function (result) {
            success(result)
        },
        error: function () {
            error()
        },
        contentType: "application/json"

})
}

function putJson(url,success,error) {
    $.ajax({
        url: url,
        method: "put",
        success: function () {
            success()
        },
        error: function () {
            error()
        },
        contentType: "application/json"
    })
}

function deleteJson(url,success,error) {
    $.ajax({
        url: url,
        method: "delete",
        success: function () {
            success()
        },
        error: function () {
            error()
        },
        contentType: "application/json"

    })
}

$.postJSON = postJson;
$.putJson = putJson;
$.deleteJson = deleteJson;

dialogOpen = function(opt){
    var defaults = {
        id : 'layerForm',
        title : '',
        width: '',
        height: '',
        url : null,
        scroll : false,
        data : {},
        btn: ['确定', '取消'],
        success: function(){},
        yes: function(){}
    };
    var option = $.extend({}, defaults, opt), content = null;
    if(option.scroll){
        content = [option.url]
    }else{
        content = [option.url, 'no']
    }
    top.layer.open({
        type : 2,
        id : option.id,
        title : option.title,
        closeBtn : 1,
        anim: -1,
        isOutAnim: false,
        shadeClose : false,
        shade : 0.1,
        area : [option.width, option.height],
        content : content,
        btn: option.btn,
        success : function(){
            option.success(option.id);
        },
        yes: function (index,dom) {
            option.yes(option.id,index,dom);
        }
    });
};


const cnv = document.getElementById('mineCanvas');
let ctx = cnv.getContext('2d');

// 基础配置
let count = 20;
let innerRadius = 30;
let outterRadius = 150;
let moreOutterRadius = 200;
let easing = 0.05;
let mcolor = [
    '#f4433699',
    '#ffeb3b99',
    '#03a9f499'
]

// 窗口变化重置 canvas 大小
window.onresize = resize();

function resize() {
    cnv.width = window.innerWidth;
    cnv.height = window.innerHeight;
}

window.onload = function () {
    // 初始化宽高
    resize();

    // 鼠标逻辑
    let mouse = new Mouse();
    mouse.getMousePosition(cnv);

    cnv.addEventListener('mousedown', () => {
        let balls = getEnoughBall(count, mouse.x, mouse.y);

        let circle = new Ball(mouse.x, mouse.y, innerRadius, '#f4433699');

        var opacticy = 0.6;

        (function animation() {
            requestAnimationFrame(animation);

            ctx.clearRect(0, 0, cnv.width, cnv.height);

            balls.forEach(item => {
                item.draw('fill');

                // 缓动动画
                item.vx = (item.dx - item.x) * easing;
                item.vy = (item.dy - item.y) * easing;
                item.x += item.vx;
                item.y += item.vy;
                item.sx += -item.sx * easing;
                item.sy += -item.sy * easing;
            })

            circle.draw('stroke');
            circle.radius += (outterRadius - circle.radius) * easing;
            opacticy = opacticy - 0.6 * easing;
            circle.color = `rgba(244, 67, 54, ${opacticy})`
        })()
    }, false)
}

function getEnoughBall(num, mouseX, mouseY) {
    var balls = [];

    for (let i = 0; i < num; i++) {
        var ball = new Ball(0, 0, Math.random() * (40 - 5 + 1) + 5, mcolor[parseInt(Math.random() * 3)]);
        ball.x = mouseX + Math.random() * innerRadius - Math.random() * innerRadius;
        ball.y = mouseY + Math.random() * innerRadius - Math.random() * innerRadius;

        // 计算最终位置
        var x = mouseX - ball.x;
        var y = mouseY - ball.y;
        var scale = Math.abs(x / y);
        ball.dx = (x < 0 ? 1 : -1) * moreOutterRadius / Math.sqrt(scale * scale + 1) * Math.random() * scale + mouseX;
        ball.dy = (y < 0 ? 1 : -1) * moreOutterRadius / Math.sqrt(scale * scale + 1) * Math.random() + mouseY;

        balls.push(ball);
    }

    return balls;
}

// ball class
class Ball {
    constructor(x, y, radius, color) {
        this.x = x || 0;
        this.y = y || 0;
        this.vx = 0;
        this.vy = 0;
        this.sx = 1;
        this.sy = 1;
        this.radius = radius || 10;
        this.color = color || 'black';
    }

    draw(type) {
        if (['fill', 'stroke'].indexOf(type) == -1) {
            throw ('ball.draw need a right type');
        }
        ctx.save();
        ctx.translate(this.x, this.y);
        ctx.scale(this.sx, this.sy);
        ctx.fillStyle = this.color;
        ctx.strokeStyle = this.color;
        ctx.beginPath();
        ctx.arc(0, 0, this.radius, 0, 360 * Math.PI / 180);
        ctx.closePath();
        type === 'fill' ? ctx.fill() : ctx.stroke();
        ctx.restore();
    }
}

// mouse class
class Mouse {
    constructor() {
        this.x = 0;
        this.y = 0;
    }

    getMousePosition(cnv) {
        cnv.addEventListener('mousedown', (e) => {
            this.x = e.clientX - cnv.offsetLeft;
            this.y = e.clientY - cnv.offsetTop;
        }, false)
    }
}

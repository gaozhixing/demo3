// pages/deepKeen/deepKeen.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    time:"60",
    timer:"",
    degree:0,
    show:"",
    btnshow:"",
    btn:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/开始.png",
    src1:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/3to1.mp3",
    src2:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/一分钟.mp3",
    dialog: "嘿，快点过来开始做运动了！这次，我们的动作可能需要很强的持久力，你需要着重训练腿部!"
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.audioCtx = wx.createAudioContext('myAudio'); 
    this.audioCtx.play();

  },
  funended: function () {
    this.countDown();
    this.audioCtx2 = wx.createAudioContext('myAudio2');
    this.audioCtx2.play();
    this.setData({
      btnshow:"show"
    });
  },
  funtimeupdate:function(u){
    let that=this;
    if (u.detail.currentTime>10&&u.detail.currentTime<23){
      that.setData({
        dialog:"要小心膝盖不要比脚尖凸出。一开始不习惯的时候可以先把背靠在墙壁上，这样膝盖的负荷就不会太大了。"
      });
    }else if(u.detail.currentTime>23&&u.detail.currentTime<29){
      that.setData({
        dialog: "今天的运动时间有1分钟，坚持到底哦！"
      });
    } else if (u.detail.currentTime>29){
      that.setData({
        dialog: "3 2 1 开始!"
      });
    }
  },
  funtimeupdate2: function (u) {
    let that = this;
    let arr = ["第一个，蹲~  缓慢起身","第一个，蹲~  缓慢起身", "第二个", "不错嘛，第三个", "四", "已经是第五个了，来！", "六，蹲，起", "挺棒嘛，继续加油！第七个", "注意保持呼吸匀称八", "九", "十，坚持住！", "十一，你有点超乎我的想象了", "还有最后一个!"]
    for(let i=0;i<arr.length;i++){
      if(u.detail.currentTime>(5*i)&&u.detail.currentTime<5*(i+1)){
        that.setData({
          dialog:arr[i]
        });
      }
    }
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  countDown: function () {
    let that = this;
    let countDownNum = parseInt(that.data.time);//获取倒计时初始值
    let degree=that.data.degree;
    //如果将定时器设置在外面，那么用户就看不到countDownNum的数值动态变化，所以要把定时器存进data里面
    that.setData({
      timer: setInterval(function () {//这里把setInterval赋值给变量名为timer的变量
        //每隔一秒countDownNum就减一，实现同步
        countDownNum--;
        if(countDownNum>=0){
          if (countDownNum < 10 && countDownNum > -1) {
            that.setData({
              time: "0" + countDownNum.toString()
            });
          } else if (countDownNum >= 10) {
            that.setData({
              time: countDownNum
              //然后把countDownNum存进data，好让用户知道时间在倒计着
            })
          }
          if (countDownNum % 5 == 0) {
            degree++;
            that.setData({
              degree: degree
            })
          }
        }else if(countDownNum<0){
          //这里特别要注意，计时器是始终一直在走的，如果你的时间为0，那么就要关掉定时器！不然相当耗性能
          //因为timer是存在data里面的，所以在关掉时，也要在data里取出后再关闭
          clearInterval(that.data.timer);
          that.setData({
            show: "show"
          });
          //关闭定时器之后，可作其他处理codes go here
        }
        
      }, 1000)
    })
  },
  startOrPause:function(){
    let that = this;
    if (that.data.btn =="https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/开始.png"){
      that.setData({
        btn:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/暂停.png",
      });
      this.audioCtx2.pause();
      clearInterval(that.data.timer);
    }else{
      that.setData({
        btn: "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/开始.png"
      });
      this.countDown();
      this.audioCtx2.play()

    }
  },
  again:function(){
    let that=this;
    that.setData({
      show:"",
      time:"60",
      degree:0,
      dialog: "嘿，快点过来开始做运动了！这次，我们的动作可能需要很强的持久力，你需要着重训练腿部!"
    });
    that.countDown;
    this.audioCtx.play();
    
  },
  finish:function(){
    wx.redirectTo({
      url: '../achieve/achieve',
    })
  }

})

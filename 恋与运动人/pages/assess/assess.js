// pages/assess/assess.js
const app=getApp();
var user;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "mySrc":"",
    "firstImg":"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/肩宽.png",
    "secoundImg":"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/xiongwei.png",
    "thridImg": "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/腰围.png",
    "fourthImg": "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/臀围.png",
    height: "",
    weight: "",   
    jieshao:"1" ,
    bodyType:"1",
    advise:"1"
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(app.globalData.height);
    console.log(app.globalData.user);
    console.log(this.data.fourthImg);
    user = app.globalData.user;
    console.log(user);
    console.log(user.data);
    console.log(user.data.bodytype)
    let arr=["苹果型","香蕉型","沙漏型","梨型"];
    this.setData({
      height: app.globalData.height,
      weight: app.globalData.weight,
      bodyType : app.globalData.user.data.bodytype,
      jieshao : app.globalData.user.data.bodydesc,
      advise: app.globalData.user.data.advise

    });
    if (app.globalData.user.data.bodytype=="苹果型"){
      this.setData({
        "mySrc" :"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/apple.png"
      });
    } else if (app.globalData.user.data.bodytype == "香蕉型") {
      this.setData({
        "mySrc": "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/banana.png"
      });
    } else if (app.globalData.user.data.bodytype == "沙漏型") {
      this.setData({
        "mySrc": "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/shalou.png"
      });
    } else if (app.globalData.user.data.bodytype == "梨型") {
      this.setData({
        "mySrc": "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/pear.png"
      });
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
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
  gotoNext:function(){
    wx.redirectTo({
      url: '../motion/motion',
    })
  }
})
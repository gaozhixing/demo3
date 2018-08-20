const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    nextStep:"下一步~",
    man:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/man_hui.png",
    woman:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/woman_hui.png",
    sex:""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo
      })
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
  catchTouchMove: function (res) {
    return false;
  },
  gotoNext:function(){
    if(this.data.sex==""){
      wx.showModal({
        title: '提示',
        content: '性别不能为空请从新选择',
        showCancel:false
      })
    }else{
      wx.redirectTo({
        url: '../Info/Info',
      });
      app.globalData.sex = this.data.sex;
    }
  
  },
  chooseSex:function(e){
  
    if(e.currentTarget.id==="sexForWoman"){
      this.setData({
        woman: "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/woman.png",
        man: "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/man_hui.png",
        sex:"man"
      });
    }else if(e.currentTarget.id === "sexForMan") {
      this.setData({
        man: "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/man.png",
        woman: "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/woman_hui.png",
        sex:"woman"
      });
    }
  },
 

  
})
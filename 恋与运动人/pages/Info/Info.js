var app=getApp();
// pages/Info/Info.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    "userHeight":"身高",
    "shDanWei":"CM",
    "nextStep":"GO~",
    "height":"",
    "weight":""
    
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
  getHeight:function(e){
    this.data.height=e.detail.value;
  },
  getWeight:function(e){
    this.data.weight=e.detail.value;
  },
 
 
  gotoNext:function(){
    console.log(this.data.height,this.data.weight);
    var height=this.data.height;
    var weight=this.data.weight;
    
    if(this.data.height==""||this.data.weight==""){
      wx.showModal({
        title: '提示',
        content: '有信息为空，测量数据可能不准确请您注意！',
        success:function(res){
          if(res.confirm){
            wx.redirectTo({
              url: '../scan/scan',
            });
            app.globalData.height = height;
            app.globalData.weight = weight;
            console.log(app.globalData.height);
            console.log(app.globalData.weight);
            wx.request({
              url: 'http://192.168.43.229:8080/user/create',
              data: {
                id: "008",
                sex:app.globalData.sex,
                height: height,
                weight: weight
              },
              header: {
                'content-typy': 'application/json'
              },
              success() {
              },
            })
          }
        }
      })
    }else{
      wx.redirectTo({
        url: '../scan/scan',
      });
      app.globalData.height = height;
      app.globalData.weight = weight;
      console.log(app.globalData.height);
      console.log(app.globalData.weight);
      wx.request({
        url: 'http://192.168.43.229:8080/user/create',
        data: {
          id: "008",
          sex:app.globalData.sex,
          height: height,
          weight: weight
        },
        header: {
          'content-typy': 'application/json'
        },
        success() {
        },
      })
    }
  }
   
})
//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    "userInfo.nickName":"神秘人",
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    user:{}
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  gotoNext:function(){
    wx.request({
      url: 'http://192.168.43.229:8080/user/query',
      data: {
        // id: app.globalData.userInfo.city+app.globalData.userInfo.nickName,
        id: "008"
      },
      header: {
        'content-typy': 'application/json'
      },
      success: (res) => {
        app.globalData.user = res.data;
        console.log(app.globalData.user.data);
        console.log(app.globalData.user.data != null);
        if (app.globalData.user.data != null) {
          wx.redirectTo({
            url: '../motion/motion',
          });
          app.globalData.sex = app.globalData.user.data.sex;
          app.globalData.weight = app.globalData.user.data.weight;
          app.globalData.height = app.globalData.user.data.height;
        } else if (app.globalData.user.data == null) {
          wx.showModal({
            title: '提示',
            content: '您是首次登陆请填写一些个人信息',
            showCancel:false,
            success:function(res){
              if(res.confirm){
                wx.redirectTo({
                  url: '../seting/seting',
                });
              }
            }
          })
         
        }
      }
    })
  },
  //   var that = this;
  //   wx.showModal({
  //     title: '提示',
  //     content: '为了测量的准确性，请填写一些个人信息',
  //     success:function(res){
  //         if(res.confirm){
  //           // wx.request({
  //           //   url: 'http://192.168.137.48:8080/user/query',
  //           //   data: {
  //           //     // id: app.globalData.userInfo.city+app.globalData.userInfo.nickName,
  //           //     id:"008"
  //           //   },
  //           //   header: {
  //           //     'content-typy': 'application/json'
  //           //   },
  //           //   success: (res) => {
               
  //           //     app.globalData.user = res.data;
  //           //     console.log(app.globalData.user.data);
  //           //     console.log(app.globalData.user.data != null);
  //           //     if (app.globalData.user.data != null){
  //           //       wx.redirectTo({
  //           //         url: '../motion/motion',
  //           //       });
  //           //       app.globalData.sex = app.globalData.user.data.sex;
  //           //       app.globalData.weight = app.globalData.user.data.weight;
  //           //       app.globalData.height = app.globalData.user.data.height;
  //           //     }else if(app.globalData.user.data==null){
  //           //       wx.redirectTo({
  //           //         url: '../seting/seting',
  //           //       });
  //           //     }
  //             }
  //           })  
  //         }
  //     }
  //   })
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    app.globalData.userInfo = e.detail.userInfo;
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    });
 
  },
})

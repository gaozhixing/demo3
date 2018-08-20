/* pages/scan/scan.js */
const app = getApp()
Page({
  data: {
    userInfo: {},
    counting: false,
    camera: false,//倒计时
    title:"身体正面",
    Refilming:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/组 1.png",
  },
  onLoad: function () {
   
  },
  //倒计时
  daojishi: function (that) {
    if (!that.data.counting) {
      //开始倒计时5秒
      countDown(that, 2);
    }
  },
  Refilming:function(){
    var that = this;
    this.daojishi(that);
    this.ctx = wx.createCameraContext()//创建摄像头对象
  },
 
})
//倒计时函数 在page外
function countDown(that, count) {
  if (count == 0) {
    //等于0时拍照 
    that.ctx.takePhoto({
      quality: 'high',
      success: (res) => {
        if (that.data.title == "身体正面"){
          wx.uploadFile({
            url: 'http://192.168.43.229:8080/upload/picture',
            filePath: res.tempImagePath,//图片路径，如tempFilePaths[0]
            name: 'image',
            header: {
              "Content-Type": "multipart/form-data"
            },
            formData:{
                userId: '008'
            },
            success: function (res) {
              console.log(res.data);
              app.globalData.user = JSON.parse(res.data);
              console.log(typeof (app.globalData.user));
              if(typeof(res.data) != "string"){
                console.log(1);
              }
              console.log(app.globalData.user.data.bodytype);
              console.log(app.globalData.user);
              that.setData({
                title: "身体侧面"
              });
              wx.showModal({
                title: '',
                content: "正面照扫描完成，请侧身",
                showCancel: false
              })
            }
          })
         }else if(that.data.title=="身体侧面"){
            wx.showModal({
            title: '',
            content: '测面照扫描完成',
            success:function(){
              wx.redirectTo({
                url: '../assess/assess',
              })
            },
            showCancel:false
          })
        }
      }
    })
    that.setData({
      counting: false
    })
    return;
  }
  wx.showLoading({//加载时显示倒计时
    title: "正在扫描",
  })
  setTimeout(function () {
    wx.hideLoading()
  }, 1000)
  that.setData({
    counting: true,
  })
  setTimeout(function () {
    count--;
    countDown(that, count);
  }, 1000);
}
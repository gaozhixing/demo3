// pages/motion/motion.js
const app=getApp();
var sideBarstart;
import wxCharts from '../../utils/wxcharts.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sportTime:"1小时30分",
    motion:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/运动 (1).png",
    record:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/数据(1).png",
    img1:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/运动1.png",
    img2:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/运动2.png",
    img3:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/运动3.png",
    img4:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/运动4.png",
    img5:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/运动5.png",
    scan:"https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/组 1.png",
    display:"",
    currentTab:"",
    tabviewOne:"",
    tabviewTwo:"noshow",
    maxheight:""
  },
  /*** 点击tab切换***/
  swichNav: function (e) {
    var that = this;
    if (e.currentTarget.dataset.current==0){
      e.detail.scrollTop=0;
      that.setData({
        tabviewTwo:"noshow",
        tabviewOne:"",
        maxheight:"",
        currentTab: e.currentTarget.dataset.current,
        motion: "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/运动 (1).png",
        record: "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/数据(1).png"
      });
    }else{
      if (app.globalData.user.data.bodytype == "苹果型") {
        this.setData({
          "display": "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/apple.png"
        });
      } else if (app.globalData.user.data.bodytype == "香蕉型") {
        console.log(1);
        this.setData({
          "display": "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/banana.png"
        });
      } else if (app.globalData.user.data.bodytype == "沙漏型") {
        this.setData({
          "display": "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/shalou.png"
        });
      } else if (app.globalData.user.data.bodytype == "梨型") {
        this.setData({
          "display": "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/pear.png"
        });
      }
      that.setData({
        tabviewOne: "noshow",
        tabviewTwo:"",
        maxheight:"maxheight",
        currentTab: e.currentTarget.dataset.current,
        motion: "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/运动 (2).png",
        record: "https://lg-lus2o5k0-1257321410.cos.ap-shanghai.myqcloud.com/数据.png"
      });
      new wxCharts({
        canvasId: 'canvasOne',
        type: 'line',
        categories: ['08.11', '08.12', '08.13', '08.14', '08.15', '08.16'],
        series: [{
          name: '分钟数',
          data: [15, 2, 45, 37, 4, 8],
          format: function (val) {
            return val.toFixed(2) + '分钟';
          }
        }],
        yAxis: {
          title: '锻炼时长',
          format: function (val) {
            return val.toFixed(2);
          },
          min: 0
        },
        width: 336,
        height: 160,
      });
      new wxCharts({
        canvasId: 'canvasTwo',
        type: 'line',
        categories: ['08.11', '08.12', '08.13', '08.14', '08.15', '08.16'],
        series: [{
          name: '胸围',
          data: [78, 78, 78, 78, 78, 78],
        }, {
          name: '腰围',
          data: [70, 70, 70, 70, 70, 70],
        }, {
          name: '肩围',
          data: [40, 40, 40, 40, 40, 40],
        }],
        yAxis: {
          title: '三围值',
          format: function (val) {
            return val.toFixed(2);
          },
          min: 0
        },
        width: 336,
        height: 160,
      });
      new wxCharts({
        canvasId: 'canvasThree',
        type: 'line',
        categories: ['08.11', '08.12', '08.13', '08.14', '08.15', '08.16'],
        series: [{
          name: 'BMI',
          data: [20.2, 20.1, 20.3, 20.4, 20.4, 20.8],
        }],
        xAxis: {
          disableGrid: true
        },
        yAxis: {
          title: 'BMI值',
          format: function (val) {
            return val.toFixed(2);
          },
          min: 0
        },
        width: 336,
        height: 160,
      });
      new wxCharts({
        canvasId: 'canvasFour',
        type: 'line',
        categories: ['08.11', '08.12', '08.13', '08.14', '08.15', '08.16'],
        series: [{
          name: '体重值',
          data: [62.6, 59.0, 59.0, 57.0, 56.5, 55.0],
        }],
        yAxis: {
          title: '体重',
          format: function (val) {
            return val.toFixed(2);
          },
          min: 0
        },
        width: 336,
        height: 160,
      });
    }
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
  scan:function(){
    wx.navigateTo({
      url: '../scan/scan',
    })
  },
  deepKeen:function(){
    wx.navigateTo({
      url: '../deepKeen/deepKeen',
    })
  }
})
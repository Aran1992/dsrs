import { request } from "./utils/util";

enum RoleType {
  Student,
  SchoolOwner,
  PlatformManager,
  SchoolOwnerNotCertified,
  PlatformManagerNotCertified,
}

App<IAppOption>({
  globalData: {
  },
  onLaunch() {
    request('user/test', {}, () => {
      wx.login({
        success: res => {
          request<{ response: User }>('anonymous/login', { code: res.code, }, (res) => {
            // todo 错误检查和处理
            const user = this.globalData.user = res.data.response;
            switch (user.roleType) {
              case RoleType.SchoolOwnerNotCertified: {
                wx.navigateTo({ url: '/pages/school/wait-certified/wait-certified' });
                // wx.navigateTo({ url: '/pages/platform/school-wait-certified-list/school-wait-certified-list' });
                break;
              }
              case RoleType.PlatformManagerNotCertified: {
                wx.navigateTo({ url: '/pages/paltform/wait-certified/wait-certified' });
                break;
              }
              default: {
                request('user/test', {}, () => {
                  request('platform/certify', {}, () => {

                  })
                })
              }
            }
          })
        },
      })
    })
  },
})
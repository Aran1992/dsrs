/// <reference path="./types/index.d.ts" />

interface User {
  id: number,
  username: string,
  avatarUrl: string,
  roleType: number,
}

interface IAppOption {
  globalData: {
    userInfo?: WechatMiniprogram.UserInfo,
    user?: User
  }
  userInfoReadyCallback?: WechatMiniprogram.GetUserInfoSuccessCallback,
}
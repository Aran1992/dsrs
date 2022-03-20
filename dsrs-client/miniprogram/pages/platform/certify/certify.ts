import { request } from "../../../utils/util";

Page({
  onTapCertify(e: { detail: { value: { name: string, address: string, phone: string } } }) {
    request('platform/certify', e.detail.value, () => {
      wx.navigateTo({ url: '../wait-certified/wait-certified' })
    })
  },
})
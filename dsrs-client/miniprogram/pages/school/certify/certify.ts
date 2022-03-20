import { request } from "../../../utils/util";

Page({
    onTapCertify(e: { detail: { value: { name: string, address: string, phone: string } } }) {
        if (Object.values(e.detail.value).some(str => str.length === 0)) {
            wx.showToast({
                title: '请填写所有信息',
                icon: 'error',
            })
            return;
        }
        request('school/certify', e.detail.value, () => {
            wx.navigateTo({ url: '../wait-certified/wait-certified' })
        })
    },
})
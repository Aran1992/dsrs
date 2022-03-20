export const formatTime = (date: Date) => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return (
    [year, month, day].map(formatNumber).join('/') +
    ' ' +
    [hour, minute, second].map(formatNumber).join(':')
  )
}

const formatNumber = (n: number) => {
  const s = n.toString()
  return s[1] ? s : '0' + s
}

let cookie: string;

export function request<T>(
  url: string,
  data: { [key: string]: any },
  success: (res: WechatMiniprogram.RequestSuccessCallbackResult<T>) => void
) {
  console.log('request start url:', url, 'data:', data)
  wx.request<T>({
    url: 'http://localhost:8080/' + url,
    method: 'POST',
    header: {
      'content-type': 'application/x-www-form-urlencoded',
      'cookie': cookie,
      'cache': 'no-cache'
    },
    data: data,
    success(res) {
      console.log('request success url:', url, ' res:', res)
      cookie = res.header['Set-Cookie'] || cookie
      success(res)
    }
  })
}

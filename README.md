# CUBTest
這是一個查看台北旅遊網的APP

Demo影片：
https://drive.google.com/file/d/13Ase4Wr3caZALVOGET6lcvZ2KYmIOd5F/view

開發說明：
本APP使用Kotlin開發，架構為MVVM，切換畫面使用Fragment，可以切換語系、展示景點圖片、使用WebView顯示景點官方網站
使用到的Lib有lifecycle的viewmodel、livedata綁定畫面與資料完成MVVM架構、retrofit發動API、gson解析JSON資料、glide顯示連結圖片、recyclerview顯示景點清單、navigation的fragment切換畫面...等

功能說明：
打開APP後看到的第一個畫面是景點清單，可以看到景點縮圖、景點名稱、景點簡易說明
右上角可以選擇切換語系

當在景點清單點擊景點後會切換畫面進入到第二個畫面為景點詳情
景點詳情會顯示景點的圖片、景點名稱、景點完整說明、景點位置地址、景點在旅遊網最後更新時間、景點官方網站連結

當在景點詳情點擊景點官方網站連結後會切換畫面進入到第三個畫面為景點官方網站
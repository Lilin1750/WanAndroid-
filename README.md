# WanAndroid 項目

基於 MVVM 架構開發的 Android 學習客戶端。

## 核心功能
* **首页列表**：展示最新首页文章，支持下拉刷新。
* **自动轮播banner**：首页顶部展示API返回的banner，支持自动轮播。
* **WebView 跳转**：点击文章和banner可以用Webview打开对应网页

## 使用的技术
* **架构**：MVVM (ViewModel + LiveData)。
* **网络**：Retrofit + OkHttp。
* **图片加载**：Glide。
* **布局**：RecyclerView, ViewPager2, SwipeRefreshLayout。

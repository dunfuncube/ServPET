# 寵寵唯跡友善平台 ServPET

## 🐾 專案簡介
本專案為一個寵物友善服務平台，提供「寵物商品商城」、「寵物美容師預約」、「寵物保母預約」等服務。
使用者可透過會員登入進行下單、預約、收藏等操作，平台同時提供後台管理功能（商品 / 訂單 / 美容師）供系統管理員維護資料。
此專案為八人小組開發成果，採用 Spring Boot 為後端框架，搭配 Thymeleaf 建立前端畫面，並整合 MySQL 資料庫進行資料管理。
專案介紹影片：https://youtu.be/u9TOILy-A-8

---

## 🛠 使用技術（Tech Stack）

- 後端框架：Java, Spring Boot, Spring MVC, Spring Data JPA
- 前端模板：Thymeleaf, HTML5, CSS3, JavaScript, jQuery, AJAX
- 資料庫：MySQL
- 開發工具：Git, GitHub, Eclipse, VS Code
- 協作工具：Google Drive, Asana

---

## 📁 功能模組與負責人

| 模組名稱         | 功能簡述                             | 負責人         |
|-----------------|-------------------------------------|----------------|
| 使用者首頁       | 服務分類導覽、首頁輪播圖、導覽功能     | 王立文          |
| 會員訂單管理     | 訂單查詢、地址修改、狀態變更           | 王立文         |
| 商品後台管理     | 商品新增、刪除、查詢、異動（CRUD）     | 林宛萱         |
| 美容師管理       | 美容師列表、預約狀態管理              | 徐立穎          |
| 會員登入系統     | 會員註冊、登入、Session 管理          | 何紹甫          |
| 保母服務預約系統  | 保母查詢、預約建立與狀態回報          | 邱承穎          |
| 收藏列表         | 收藏商品、美容師、保母                | 王立文、陳郁昕  |
| 後台管理員系統    | 管理員權限、後台整合建置              | 袁學庸          |

---

## 🔍 王立文負責模組詳解（By Duncan）

### 🔹 系統首頁（首頁導覽模組）
- 使用 Thymeleaf + HTML5 建構首頁畫面
- 呈現平台三大服務模組入口（商城、保母、美容師）
- 輪播區使用 Slick.js 實作，具備自動播放與響應式設計
- 導入前端視覺引導邏輯，提升初次使用者操作體驗

### 🔹 會員訂單管理（會員端）
- 使用 DataTables 呈現訂單列表，支援分頁、篩選與即時互動
- Spring MVC + JPA 處理後端 CRUD
- AJAX + SweetAlert2 實作「修改地址」與「取貨確認」功能
- 導入 @Transactional 確保交易資料一致性
- 設計訂單狀態切換與視覺提示（例如已取貨變色）

### 🔹 商品收藏列表（會員端）
- 會員可將商品加入／移除收藏
- 使用 AJAX + jQuery 實作收藏按鈕交互，無須刷新頁面
- Thymeleaf 渲染收藏頁面，動態呈現會員收藏清單
- 收藏紀錄以會員編號 + 商品編號進行資料庫對應

### 🔹 美容師收藏列表（會員端）
- 會員可將美容師加入／移除收藏，並查看收藏名單
- 使用 AJAX 非同步傳送操作，前端即時更新
- 後端使用 Spring MVC + JPA 管理會員與美容師間關聯表
- Thymeleaf 顯示收藏狀態與操作按鈕，並依收藏情況切換提示

---

## 🚀 如何執行（Setup Instructions）

### 📦 安裝需求
- Java 8+
- Maven 3.6+
- MySQL 5.7+
- IDE（建議使用 Eclipse 或 IntelliJ）

### 🧰 啟動步驟

1. 下載本專案：

git clone https://github.com/dunfuncube/ServPET.git

2. 匯入至 IDE，並確認 Maven 自動建置

3. 修改 `src/main/resources/application.properties` 檔案中的資料庫連線資訊：

spring.datasource.url=jdbc:mysql://localhost:3306/cia103_g3?serverTimezone=Asia/Taipei
spring.datasource.username=【請填入你的 MySQL 帳號】
spring.datasource.password=【請填入你的 MySQL 密碼】

📌 請先於本機建立名為 `cia103_g3` 的資料庫，並手動建立資料表（依專案程式需求自行設計）。

4. 執行 Application.java，伺服器將啟動於 http://localhost:8080

---

## 👤 作者資訊（Author）

王立文 Duncan Wang  
擁有多年品牌與營運管理經驗，2024 年轉型學習 Java 後端開發，期望成為懂使用者與流程的技術整合人才。  
📧 duncan.wang73@gmail.com

---

## 📝 備註

- 本專案為學習性質，未整合完整權限驗證與正式部署機制。
- 所有模組皆可於本地端啟動後進行測試。
- 若您對本專案或我個人開發能力有進一步興趣，歡迎與我聯繫。

# CaloriesTracker 
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-blue) 
![Jetpack%20Compose](https://img.shields.io/badge/Jetpack_Compose-1.5.4-green)
![Firebase](https://img.shields.io/badge/Firebase-32.6.0-orange)

> 基于Android的饮食营养分析应用 - 课程设计项目

## 🍎 核心功能
### 设想功能
- 📸 拍照上传食物图片，通过AI分析营养成分
- 📊 主页面实时显示当日营养摄入数据
- 👤 用户基础信息录入（身高/体重）

### 未来计划功能
- 🎯 个性化营养摄入计划制定
- 🔄 用户历史数据存储与对比分析

## 🛠️ 技术栈
- ​**开发语言**: Kotlin (2.0.21)
- ​**UI框架**: Jetpack Compose + Navigation组件
- ​**架构模式**: MVVM
- ​**云服务**: 
  - Firebase Storage (图片存储)
  - Firebase Firestore (用户数据存储)
  - Vertex AI (多模态图片分析)
- ​**最低支持**: Android 9.0 (API 28)

## 🚀 快速开始
### 环境要求
- Android Studio Meerkat 2024.3.1+
- Android SDK 35

### 项目结构
src/

├── androidTest/       # Android组件测试

└── main/

    ├── java/cn/itcast/caloriestracker02/
    
    │   ├── data/                # 数据层
    
    │   │   ├── mapper/         # 数据模型转换器
    
    │   │   ├── remote/         # 网络请求与Firebase交互
    
    │   │   └── repositoryImpl/ # 数据仓库实现 
    
    │   │
    
    │   ├── domain/             # 业务逻辑层
    
    │   │   ├── model/         # 领域模型
    
    │   │   ├── repository/    # 数据仓库接口
    
    │   │   └── utils/         # 领域工具类
    
    │   │
    
    │   └── presentation/      # UI展示层
    
    │       ├── components/    # 可复用Compose组件
    
    │       ├── navigation/    # 页面导航配置

    
    │       ├── Screens/       # 各功能页面
    
    │       ├── theme/         # 应用主题配置
    
    │       ├── viewModels/    # ViewModel逻辑
    
    │       └── MainActivity.kt # 应用入口
    
    │
    
    └── res/                   # 资源文件
   

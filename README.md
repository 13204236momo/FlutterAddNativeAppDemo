# FlutterAddNativeAppDemo
## 简介

这个项目是展示在原有Android项目中引入Flutter module的搭建过程的Demo。



还在开发中。。。



## 搭建步骤

1.进入到已有android工程的同一级目录
2.创建flutter module

```
//终端执行已下命令，flutter_module：flutter模块名，可以自己命名
flutter create -t module flutter_module
```

3.在已有android工程的根目录中的Settings.gradle中添加如下代码

```kotlin
val filePath =
    settingsDir.parentFile.toString() + "/flutter_module/.android/include_flutter.groovy"
apply(from = File(filePath))
```

4.在已有android工程的app模块依赖新建的flutter模块，固定写法

```
//注：不要写成flutter模块名，这里是固定写法
implementation(project(":flutter"))
```

5.在android/app/src/main/AndroidManifest.xml中添加如下代码

```xml
<activity
    android:name="io.flutter.embedding.android.FlutterActivity" android:configChanges="orientation|keyboardHidden|keyboard|screenSize|smallestScreenSize|locale|layoutDirection|fontScale|screenLayout|density"
    android:hardwareAccelerated="true"
    android:windowSoftInputMode="adjustResize" />
```

6.从原生页面打开Flutter页面

```kotlin
vb.btnFlutter.setOnClickListener {
    startActivity(FlutterActivity.createDefaultIntent(this))
}
```

7.处理构建时错误

错误1：

![错误1](.\images\错误1.png)

修改已有android工程根目录中settings.gradle.kts中的代码

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    //改为
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    ...
}
```

错误2：

![错误2](.\images\错误2.png)

找不到一些代码包，在已有android工程根目录中settings.gradle.kts中的添加：

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        //添加这个仓库
        maven { url = uri("https://storage.googleapis.com/download.flutter.io") }
    }
}
```






package com.test.androidapp

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

object FlutterNativeProtocolManager {
    //可随意定义，与Flutter端保持一致，一般使用 包名/channel_name 格式，以免冲突
    private const val CHANNEL_NAME = "com.test.androidapp/communication"
    fun bindMethodChannel(flutterEngine: FlutterEngine) {
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger, CHANNEL_NAME
        ).setMethodCallHandler { call, result ->
            when (call.method) {
                "getDeviceInfo" -> {
                    val info = "Android Device: ${android.os.Build.MODEL}"
                    result.success(info)
                }
            }
        }
    }

    fun callFlutterMethod(methodName: String, arguments: Any?, result: MethodChannel.Result?) {
        val flutterEngine = FlutterEngineCache.getInstance()[MyApp.FLUTTER_ENGINE_ID]
        flutterEngine?.let {
            MethodChannel(
                flutterEngine.dartExecutor.binaryMessenger, CHANNEL_NAME
            ).invokeMethod(methodName, arguments,result)
        }
    }
}
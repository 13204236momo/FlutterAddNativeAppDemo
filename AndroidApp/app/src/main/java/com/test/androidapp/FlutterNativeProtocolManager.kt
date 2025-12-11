package com.test.androidapp

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

object FlutterNativeProtocolManager {
    private const val CHANNEL_NAME = "666_native"
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
}
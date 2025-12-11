import 'package:flutter/services.dart';

class NativeBridge {
  static const MethodChannel _channel = MethodChannel('666_native');

  static Future<String> getDeviceInfo() async {
    final result = await _channel.invokeMethod('getDeviceInfo')??"";
    return result;
  }

  static Future<int?> plus(int a, int b) async {
    final result = await _channel.invokeMethod('plus', {
      "a": a,
      "b": b,
    });
    return result;
  }
}

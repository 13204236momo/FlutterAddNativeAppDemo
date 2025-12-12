import 'package:flutter/services.dart';

class NativeBridge {
  static const MethodChannel channel = MethodChannel('com.test.androidapp/communication');

  static Future<String> getDeviceInfo() async {
    final result = await channel.invokeMethod('getDeviceInfo')??"";
    return result;
  }


}

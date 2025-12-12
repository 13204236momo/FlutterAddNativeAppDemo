import 'package:flutter/material.dart';
import 'package:flutter_module1/protocol/native_bridge.dart';
import 'package:fluttertoast/fluttertoast.dart';
void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String deviceInfo = '';

  @override
  void initState() {
    super.initState();
    getDeviceInfo();
    onNativeCall();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Center(
        child: Text(
          deviceInfo,
          style: Theme.of(context).textTheme.headlineMedium,
        ),
      ),
    );
  }

  void getDeviceInfo() async {
    String info = await NativeBridge.getDeviceInfo();
    setState(() {
      deviceInfo = info;
    });
  }

  void onNativeCall() async {
    NativeBridge.channel.setMethodCallHandler((call) async {
      Fluttertoast.showToast(
        msg: "method name: ${call.method}",
        toastLength: Toast.LENGTH_SHORT,
        gravity:
        ToastGravity.CENTER, // 位置：TOP、CENTER、BOTTOM
      );
      switch (call.method) {
        case 'getPageName':
          // 获取 Android 传递的参数（可选）
          String? param = call.arguments["platform"];
          // 执行业务逻辑
          String result = "$param:MyHomePage";
          // 返回结果给 Android（可选）
          return result;
        default:
          print('unknown method');
      }
    });
  }
}

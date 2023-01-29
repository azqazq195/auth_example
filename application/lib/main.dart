import 'package:auth_example/components/constants.dart';
import 'package:auth_example/screens/home/home_page.dart';
import 'package:flutter/material.dart';
import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';
import 'package:url_strategy/url_strategy.dart';

void main() {
  KakaoSdk.init(
    nativeAppKey: "a4069a30314e75ecf5e18b8e9f6bf4b5",
    javaScriptAppKey: "c2b49b2e39ff0f5930865fb53e309b6a",
  );
  setPathUrlStrategy();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: HomePage.routeName,
      routes: routes,
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        pageTransitionsTheme: pageTransitionsTheme,
      ),
    );
  }
}

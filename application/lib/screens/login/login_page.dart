import 'package:auth_example/screens/login/components/kakao_login.dart';
import 'package:flutter/material.dart';

class LoginPage extends StatelessWidget {
  const LoginPage({super.key});
  static const routeName = "/login";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Center(
          child: Column(
            children: [
              const Padding(
                padding: EdgeInsets.only(top: 10),
                child: Text("Login Page"),
              ),
              Expanded(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: const [
                    KakaoLogin(),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

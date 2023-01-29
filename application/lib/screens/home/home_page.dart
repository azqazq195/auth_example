import 'package:auth_example/screens/callback/callback_page.dart';
import 'package:auth_example/screens/login/login_page.dart';
import 'package:flutter/material.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);
  static const routeName = "/";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Center(
          child: Column(
            children: [
              const Padding(
                padding: EdgeInsets.only(top: 10),
                child: Text("Home Page"),
              ),
              Expanded(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    TextButton(
                      onPressed: () =>
                          Navigator.pushNamed(context, LoginPage.routeName),
                      child: const Text("Login"),
                    ),
                    TextButton(
                      onPressed: () =>
                          Navigator.pushNamed(context, CallbackPage.routeName),
                      child: const Text("Callback"),
                    ),
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

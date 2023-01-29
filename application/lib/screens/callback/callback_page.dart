import 'package:flutter/material.dart';

class CallbackPage extends StatelessWidget {
  const CallbackPage({super.key});
  static const routeName = "/callback";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Center(
          child: Column(
            children: [
              const Padding(
                padding: EdgeInsets.only(top: 10),
                child: Text("Callback Page"),
              ),
              Expanded(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: const [],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

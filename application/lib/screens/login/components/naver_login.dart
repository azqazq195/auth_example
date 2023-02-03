import 'package:flutter/material.dart';

class NaverLogin extends StatelessWidget {
  const NaverLogin({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      child: InkWell(
        onTap: () async {
          // await _kakaoLogin();
        },
        child: Image.asset("assets/oauth/naver.png"),
      ),
    );
  }
}

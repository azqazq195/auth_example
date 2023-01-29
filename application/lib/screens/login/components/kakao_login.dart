import 'package:dio/dio.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';

class KakaoLogin extends StatelessWidget {
  const KakaoLogin({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      child: InkWell(
        onTap: () async {
          await _kakaoLogin();
        },
        child: Image.asset("assets/oauth/kakao.png"),
      ),
    );
  }

  Future<void> _kakaoLogin() async {
    (kIsWeb) ? _kakaoLoginWeb() : _kakaoLoginApp();
  }

  // Redirect
  Future<void> _kakaoLoginWeb() async {
    print("** web 로그인 **");
    if (await isKakaoTalkInstalled()) {
      try {
        String response = await AuthCodeClient.instance.authorizeWithTalk(
          redirectUri: "http://localhost:8080/v1/member/oauth/kakao/callback",
        );
        print("카카오톡으로 로그인 성공");
        print("response: $response");
      } catch (error) {
        print("카카오톡으로 로그인 실패 $error");
      }
    } else {
      try {
        print("request login");
        var response = await Dio()
            .get("http://localhost:8080/v1/member/oauth/kakao/authorize");
        print(response);
        // await AuthCodeClient.instance.authorize(
        //   redirectUri: "http://localhost:8080/v1/member/oauth/kakao/callback",
        // );
        print("카카오계정으로 로그인 성공");
      } catch (error) {
        print('카카오계정으로 로그인 실패 $error');
      }
    }
  }

  // TODO Token과 User정보로 Backend에 로그인 요청
  Future<void> _kakaoLoginApp() async {
    print("** app 로그인 **");
    if (await isKakaoTalkInstalled()) {
      print("카카오톡 설치됨");
      try {
        OAuthToken token = await UserApi.instance.loginWithKakaoTalk();
        print("카카오톡으로 로그인 성공 ${token.accessToken}");
        _getUserInfo();
      } catch (error) {
        print("카카오톡으로 로그인 실패 $error");
        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
        if (error is PlatformException && error.code == 'CANCELED') {
          return;
        }
        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인
        try {
          OAuthToken token = await UserApi.instance.loginWithKakaoAccount();
          print('카카오계정으로 로그인 성공');
        } catch (error) {
          print('카카오계정으로 로그인 실패 $error');
        }
      }
    } else {
      print("카카오톡 미설치");
      try {
        OAuthToken token = await UserApi.instance.loginWithKakaoAccount();
        print("카카오계정으로 로그인 성공 ${token.accessToken}");
        _getUserInfo();
      } catch (error) {
        print('카카오계정으로 로그인 실패 $error');
      }
    }
  }

  void _getUserInfo() async {
    try {
      User user = await UserApi.instance.me();
      print('사용자 정보 요청 성공 ${user.toJson()}');
    } catch (error) {
      print('사용자 정보 요청 실패 $error');
    }
  }
}

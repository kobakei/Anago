# Anago

MVVMとClean architectureで書いたAndroidのサンプルアプリです。

## 説明

- 以下の機能に限定したGitHubクライアントです
  - GitHubログイン
  - 自分のリポジトリを表示
  - スターしたリポジトリを表示
  - スター登録・解除
  - スターした人一覧を表示
  - ユーザーのプロフィール表示

## アーキテクチャ

![Diagram](https://github.com/kobakei/Anago/blob/master/art/diagram.png?raw=true)

上からユーザーに近い順に並んでいます。UseCase以下の各層は、呼び出し元にRxJavaのObservableを返します。

## ライブラリ

- [Android data binding](https://developer.android.com/topic/libraries/data-binding/index.html)
- [google/dagger](https://github.com/google/dagger)
- [evant/gradle-retrolambda](https://github.com/evant/gradle-retrolambda)
- [ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)
- [ReactiveX/RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [trello/RxLifecycle](https://github.com/trello/RxLifecycle)
- [greenrobot/EventBus](https://github.com/greenrobot/EventBus)
- [google/gson](https://github.com/google/gson)
- [square/okhttp](https://github.com/square/okhttp)
- [square/retrofit](https://github.com/square/retrofit)
- [bumptech/glide](https://github.com/bumptech/glide)
- [gfx/Android-Orma](https://github.com/gfx/Android-Orma)
- [JakeWharton/timber](https://github.com/JakeWharton/timber)
- [facebook/stetho](https://github.com/facebook/stetho)
- [square/leakcanary](https://github.com/square/leakcanary)
- [robolectric/robolectric](https://github.com/robolectric/robolectric)

## License

```
Copyright 2016 Keisuke Kobayashi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

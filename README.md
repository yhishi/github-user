### 作ったアプリ



https://user-images.githubusercontent.com/26667944/161571499-5dbc6a07-70b4-4f48-9581-d096ea9dad40.mp4





#### アプリの対応OSバージョン
- minSdkVersion 7.0 (24)
- compileSdkVersion 12 (32)
- targetSdkVersion 12 (32)

#### ライブラリ
- AndroidX Activity
- AndroidX Fragment
- AndroidX AppCompat
- AndroidX ConstraintLayout
- AndroidX Lifecycle
- AndroidX RecyclerView
- AndroidX Browser
- OKHttp3
- Retrofit2
- Moshi
- Dagger2
- Rxjava3
- Picasso

#### ブランチ
`develop`で開発を行いました。

#### アーキテクチャ
MVVM + Repository

- `GithubRepository`から永続化情報にアクセスし、`GithubService`というinterfaceの関数を呼び出すようになっています。
- `RetrofitService`に実際のRetrofitを使った実装
- `ServiceModule`で`RetrofitService`の注入

#### できていない箇所
- アイコン設定
- 遷移アニメーション
- API通信時のローディング表示
- API失敗時のエラーハンドリング
- オフラインでの表示

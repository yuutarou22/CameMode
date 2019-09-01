# CameMode

## どういうアプリ？

カメラマンとモデル、両者の「やりたい」を叶えるためのアプリ。

## アプリ名の由来

アプリ名：CameMode
1. 「モデルさん募集してます」と言う前、というか「モデ」の時点で見つかるほど早く目的を叶えられる<br>
という願いを込めて（その逆であるカメラマンも同様）。
2. 「Came（来た）」の意味を含む。

## 制作の経緯

趣味で写真撮影を行なっている。<br>
普段は風景や植物などを撮影しているが、ポートレート撮影をする機会があり人物撮影の楽しみに触れた。<br>
それがきっかけでポートレート撮影にも力を入れて行こうとしたが、モデルが見つからない。<br>
声をかけてもほとんど無視、予定を決めるところまで行けてもドタキャンされたりすることが多々あった。畜生！<br>
モデル探し、意外と大変。畜生！<br>
ということで同じ意思を持った（撮りたい、撮られたい）人を集めたいという思いから制作に着手した。<br>

## アプリの工夫点

学習が目的での制作だが、「使ってもらえる」アプリを制作するため以下の差別化を図った。

1. Twitterでの募集、声かけだと非効率
  - Twitter上にはハッシュタグが存在するが、数が多すぎる。<br>
  飽和状態のため、目につく機会が少ない。すぐに流れてしまう。<br>
  複数のハッシュタグをつけるという対策もあるが、煩わしい。<br>
  →このアプリで絞り込み化を実現。カメラマン、モデルは集まれ！

2. Twitterだとフォロワー数、拡散数などに左右されやすいため、人目につかない<br>
  - 上記同様、絞り込み化を実現。<br>
  カメラマン、モデル募集目的に特化したアプリにすることで対策。
  
3. アカウント登録が不要
  - アプリ内でのコミュニケーション機能は無い。<br>
  そのため、ユーザそれぞれが利用するSNSでの「繋がるきっかけ作り」を目的としている。<br>
  したがって当アプリ専用アカウントの登録は不要。<br>
  ※SNS認証を実装するか検討中
  
4. UI改善を施した<br>
ユーザの入力労力を減らすことは、コンバージョン率増加が見込まれる。
  - 入力に合わせてキーボードを変える
    - 投稿表示→かな文字
    - 編集・削除パスワード→英数字
    - SNSアカウント名→英数字
    - 撮りたいイメージ→かな文字
  - フローティングラベルにする
    - 表示するラベル領域を省略できる上、ユーザは入力したものが求められていたものか確認できる
  - 入力フィールドは最大でも、５〜７つにする
    - ユーザの入力労力を減らす
  - Submitボタン、Cansellボタンを設け、二次的なボタンは視覚的に存在感を下げる。<br>
  位置も、押しにくいところに設置する。
  - 選択項目が２〜７つなら、ドロップダウンよりラジオボタンを使う。
    - オプションが常に表示されるため、認知が楽で、操作なしでオプション内容の比較も行える
  - チェックボックスの選択の判断は、ユーザに任せるため、デフォルトをOFFにする。
    - しかし、もし入力漏れがあるとNGの場合は、バリデーションを設ける

## アプリのテーマカラーについて
テーマカラーは「オレンジ」「黄色」。<br>
活発的で、温和な印象を与える。<br>
モデル、カメラマンがお互いに「撮影したい人がいないかな、、」「撮らせてくれる人いないかな、、」と探り探りになりがち。<br>
不安も多少抱えるもの。。。うん。不安。
だけど、探すだけならいくらでもやれるし、もっとワクワクしてこのアプリを使って欲しいため、この色にした。
赤も活発的な色として挙げられるが、警告色でもあり、色味が強いため却下。

## 今後していきたいこと（直近での残作業）

- [x] onActivityResultでリストの更新を行う

- アプリ全体の設計の見直し
  - [ ] 文言を strings に定義
  - [ ] 数値を dimens に定義
  - [ ] 色を colors に定義
  - [x] Activity と Layout の切り分け
  - [ ] ディレクトリを分けて切り分ける
  - [x] 不要なファイルの削除
  - [ ] NCMBのカラム名、変数名同士に統一性がない。<br>
  カラム名、変数名共に見直す 
  - [ ] 現在NCMBに格納されているStringカラムをIntカラムとして対応する（データ容量削減のため）
  
- Main画面
  - [x] 追加画面で登録が完了した時、アプリ起動時のみリストの更新を行う
  - [x] カメラマンとモデルで判定し、一目で判断できるようViewを表示させる
    - [ ] 上記に伴い、アイコンも変更する。
  - [x] ユーザ情報をデータストアから取得し、リストに表示
  - [ ] スクロールして自動更新（10件ずつくらい）
  - [ ] 毎回データ取得してリスト表示しているので、キャッシュで再表示する
  - [x] リストの表示名の語尾を三点リーダにする
  
    
- Search画面
  - [ ] 検索項目の精査
    - [ ] 希望種別の判定
    - [ ] 空き日の判定
      - 選択している曜日が一つでも当てはまればヒット
      - 一つも選択していない場合、全ての曜日として検索
    - [ ] 有償・無償
    - [ ] 地域
      - 「未選択」の場合、地域問わず検索
    - [ ] 性別
      - 「未選択」の場合、男女問わず検索
    - [ ] 年代
      - 「未選択」の場合、年代問わず検索
  - [x] 検索処理を実装する
  - [x] 検索結果画面を実装する（デフォルトの画面に戻る実装も必要）

- Add画面
  - [x] ユーザ情報をデータストアへ登録
  - [ ] 一回登録したら、もう登録処理は出来ないように対応する（AddボタンをEditボタンに変更するとか？アカウント作成するようにするとか方法は複数ありそう）
  - [ ] 撮影イメージを入力する際、Twitterライクな入力文字数を表示する

- 共通処理
  - [ ] リストビューのアイテムを選択した場合、詳細情報を表示させる処理
  - [ ] レイアウトの見直し（Main,Search画面それぞれ揃える。またリストの１セル分のカスタマイズ）
  
- その他
  - [ ] アプリ名、ロゴ、デザイン見直し
  - [ ] Twitter連携
  - [ ] パスワードを使い、自分の投稿を編集・削除する

- バリデーションメモ
  - 希望種別（ラジオボタンなのでバリデ必要なし）
    - 投稿表示名（未入力NG）
    - 編集、削除パスワード（未入力OKだが、未入力の場合注意喚起する）
  - SNSの種類（ラジオボタンなのでバリデ必要なし）
    - 空き日（全てFalseはNG、デフォは土日）
  - 有償無償（ラジオボタンなのでバリデ必要なし）
  - 地域（デフォルトが北海道なので必要なし）
  - 性別（デフォルトが未選択なので必要なし）
  - 年代（デフォルトが10代なので必要なし）
  - イメージ（未入力可能、バリデ特になし）

## 今後していきたいこと(長期)
- Twitter連携機能を使って、ユーザの情報入力を省く。

- Twitterアンケート結果[https://twitter.com/YutaroM0224/status/1131857157907861504]
  - 日常生活の中でもiPhoneユーザが多いように感じる。<br>
    少しでも多くのユーザに触れてもらうためにも、iOS開発に着手したい。

- Webサービス化してもっと利用しやすくする。
  - アプリをインストールせずともアクセスするだけで利用できるようにするという意味
  
- ProGuardでソースを難読化する（完全に逆解析を防ぐことは出来ないけど）

## 2019_0817 実際に撮影させてもらったモデルさんにアプリを操作してもらった際に気づいた点
- SNSの種類→Twitter,Instagram両方選べない
- 投稿表示名と、SNSアカウント名の区別がわからない。説明不足。<br>
口頭で説明が必要になるということは、ユーザは想定している操作とは全く別の操作をしてしまう。
- 検索する際、「地域」「性別」「年代」が、デフォルト値だとその値しかヒットしなくなる。<br>
未選択の場合は、全検索にするようデフォルト値を設定する。
- 何度も操作していると、MainActivityのリスト項目の下がだんだん広がる。

## References
- ボタンを等間隔で配置する https://qiita.com/k_keisuke/items/fa56434dc11ec6608f67
- RecyclerViewの基本的な実装 https://qiita.com/HideMatsu/items/a9ab48608e4f681d31fe
- RecyclerViewのタッチイベント実装（どこに実装するのが正解かわからん！） https://qiita.com/konifar/items/5ad771b3e3a78e1eda4f
- NCMBReferences https://mbaas.nifcloud.com/assets/sdk_doc/ios/doc/html/index.html
- 独自リスナーを作成 https://techbooster.org/android/9054/
- Activityのライフサイクル https://www.javadrive.jp/android/activity/index2.html
- Twitterライクな入力文字数を表示する https://unsolublesugar.com/20140102/170914/
- 長押しで説明が出るツールチップの実装 https://android.roof-balcony.com/view/toast/
- 動的にFragmentをレイアウトに追加する方法 https://rakuishi.com/archives/6637/
- 重ねたViewの親Viewへタッチイベントを無効にする方法 http://java-lang-programming.com/ja/articles/83
- Fragment内のテキストを変更する https://codeday.me/jp/qa/20190211/258925.html
- EditTextの入力タイプ指定 https://qiita.com/joji/items/41cc6cbedb7b84b632df

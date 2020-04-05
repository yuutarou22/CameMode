# CameMode

## どういうアプリ？

カメラマンとモデル、両者の「やりたい」を叶えるためのアプリ。

## アプリ名の由来

アプリ名：CameMode
1. **「モデルさん募集してます」と言う前、というか「モデ」の時点で見つかるほど早く目的を叶えられる** という願いを込めて（その逆であるカメラマンも同様）。
2. 「Came（来た）」の意味を含む。

## 制作の経緯

趣味で写真撮影を行なっている。<br>
普段は風景や植物などを撮影しているが、ポートレート撮影をする機会があり人物撮影の楽しみに触れた。

それがきっかけで、ポートレート撮影にも力を入れて行こうとした。しかし、モデルが見つからない。

依頼してもほとんど無視、予定を決めるところまで行けてもドタキャンされたりすることが多々あった。<br>
モデル探し、意外と大変！

……ということで同じ意思を持った（撮りたい、撮られたい）人を集めたいという思いから制作に着手した。

## アプリの工夫点

学習が目的での制作だが、「使ってもらえる」アプリを制作するため以下の差別化を図った。

1. Twitterでの募集、声かけだと非効率→このアプリで絞り込み化
  - Twitter上にはハッシュタグが存在するが、数が多すぎる。<br>
  飽和状態のため、目につく機会が少ない。すぐに流れてしまう。<br>
  複数のハッシュタグをつけるという対策もあるが、煩わしい。<br>
  →このアプリで絞り込み化を実現。カメラマン、モデルは集まれ！

2. Twitterだとフォロワー数、拡散数などに左右されやすいため、人目につかない→このアプリで絞り込み化<br>
  - 上記同様、絞り込み化を実現。<br>
  カメラマン、モデル募集目的に特化したアプリにすることで、同じ目的の人を集める。
  
3. アカウント登録が不要
  - アプリ内でのコミュニケーション機能は**無い**。<br>
  そのため、ユーザそれぞれが利用するSNSでの **「繋がるきっかけ作り」** を目的としている。<br>
  したがって当アプリ専用アカウントの登録は不要。<br>
  ※SNS認証を実装するか検討中
  
4. UI改善を施した<br>
ユーザの入力労力を減らすことで、コンバージョン率増加を図る。
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
  - Ripple Effectを実装
    - マテリアルデザインのタッチフィードバック。<br>
      タッチした項目を波紋効果で視覚的に表現する。

## 今後していきたいこと(長期)
- Twitter連携機能を使って、ユーザの情報入力を省く。

- Twitterアンケート結果[https://twitter.com/YutaroM0224/status/1131857157907861504]
  - 日常生活の中でもiPhoneユーザが多いように感じる。<br>
    少しでも多くのユーザに触れてもらうためにも、iOS開発に着手したい。

- Webサービス化してもっと利用しやすくする。
  - アプリをインストールせずともアクセスするだけで利用できるようにするという意味
  
- ProGuardでソースを難読化する（完全に逆解析を防ぐことは出来ないけど）

- 毎回mBaaSから値を取得して表示している。キャッシュに保持して置く方法を検討する。

## 2019/08/17 実際に撮影させてもらったモデルさんにアプリを操作してもらった際に気づいた点
- SNSの種類→Twitter,Instagram両方選べない
- 投稿表示名と、SNSアカウント名の区別がわからない。説明不足。<br>
口頭で説明が必要になるということは、ユーザは想定している操作とは全く別の操作をしてしまう。
- 検索する際、「地域」「性別」「年代」が、デフォルト値だとその値しかヒットしなくなる。<br>
未選択の場合は、全検索にするようデフォルト値を設定する。
- 何度も操作していると、MainActivityのリスト項目の下がだんだん広がる。

## 2019/10/22 友人に操作してもらい意見をもらって気づいた点
- 撮影イメージのテキスト入力時、自己紹介なのか、撮影のイメージなのかユーザは分かりにくい。
  ヒントを予め表示されていると分かりやすいかも。
- パスワード入力時などのアラートダイアログは、⚠️マークなどの画像をつけてパッと見で判断できるようにしたほうがいい。
  赤色、黄色、黒などの警告色を使う。
  パスワードが未入力の場合→⚠️
  パスワードが入力されている場合→？マークでこれでいいですか？みたいな感じを伝える
- SNSはTwitterとInstagramしか選べないのか。。→検討中
- ユーザ名の下に、撮影イメージ文章を持ってきたほうがいい。ユーザはどういう人なのかをまず見たいと思うから。
- パスワード入力は必要なのかどうか問題。管理するパスワードが一つ増えるのは、若干手間。
- パスワード入力時は、確認用として２回入力してもらうべきでは。
- DBに削除フラグを立てて、日次バッチなどで完全削除するほうが良いのでは。

## 2019/12/12 実際に上司に操作してもらってFB
- 登録（Add）した後でも、マイページを表示して編集したり出来るようにするために、端末に永続化させる。
  - SharedPreferencesとかで？
- 下のボタン2つが見辛い
  - 
- 最初のページャは端まで行ったら矢印マークを非活性か、消す。
  - 間違えてずっと押し続けてしまう。
- Main画面でのタイトルが大きすぎる。半分くらいでOK
  - 右側にメニューボタンを設置して、好きな時に説明書やチュートリアル、利用規約などを見れるようにした方がいい。
- 外部サービス（Instagram、Twitter）に委任してしまうと再訪問する理由がなくなってしまうので、リピーターが減る恐れがある。
  - 本当だったらこのサービスだけで完結するような仕組みにできたら良いんだけど。

## RecyclerViewの上スクロールで更新する処理
Twitterライクなくるくるを実装する。意外と簡単だった。
- https://qiita.com/konifar/items/b68f224f4dc5b6ce5165
- 本当はMainLayoutファイルにリスナーを代入して実装したかったができなかった。
  - MainLayoutから検索クエリ作成処理を呼び出す場合、MainActivityにある処理を呼び出さなければならないため（クエリーとかデータ処理を担っているので）
  - 上記の場合、メソッドをStaticにしなければならないが、影響範囲が広すぎた
  - その結果、諦めてMainActivityに実装した。

## RecyclerViewの下スクロールで読み込む処理
一番下スクロール検知→差分データ取得→表示位置を維持し更新
- RecyclerViewのスクロール検知
  - https://qiita.com/u_nation/items/282e3220ae863e6d21e5
- NCMBのupdateDataは、ISO 8601の拡張形式だった。それと比較するため、デバイス側にあるユーザ情報リストの一番古い日時を取得し、NCMBで検索するクエリを発行する。
  - https://qiita.com/azumagoro/items/11cadb4de3015e732d3d
  - https://drambuie.hatenadiary.org/entry/20110219/p1
  - http://enumorish.hatenablog.com/entry/20101114/1289733321
  - https://www.sejuku.net/blog/20994
- 取得した差分データを表示する
  - https://qiita.com/fu_neko/items/77683a800a9da9ca29d1
  - https://qiita.com/sussan0416/items/e7d68a208f71b3b95f8f
  - http://ja.voidcc.com/question/p-knbqemsq-ge.html

## スプラッシュ画面の実装
- 横長のロゴとアイコンの画像を表示させようとしたが、うまくいかなかった。
  - ブランチ：0032_スプラッシュの実装
- 以下のサイトを参考に実装している
  - https://minpro.net/splash-screen
  - http://sakebook.hatenablog.com/entry/2016/06/01/031950 (参考)
  - http://chiiia12.hatenablog.jp/entry/2018/10/30/184328 (参考)
  - https://developer.android.com/guide/topics/resources/drawable-resource?hl=ja (参考)
  - https://dev.classmethod.jp/smartphone/android/android-ui-design-layer-list/ (参考)

## ImageButtonの画像がはみ出す時の対応
scaleTypeを「CENTER_CROP」に指定する
http://adash-android.jp.net/imagebutton%E3%81%A7%E3%83%9C%E3%82%BF%E3%83%B3%E5%86%85%E3%81%AB%E7%94%BB%E5%83%8F%E3%82%92%E8%A1%A8%E7%A4%BA%E3%81%99%E3%82%8B/

## ImageButtonの背景の灰色枠を消す対応
backgroundに「@null」か「#00000000」を指定する
https://love-fukushima.hatenadiary.org/entry/20120111/1326291791

## 参考文献
- 初回起動時のみ実行する　https://qiita.com/inuko/items/399a4431941dd6b3160b
http://programming-cafe.com/android/sharedpreferences/first-launch-the-application
（実際には使ってない。SharedPreferencesのデフォルト値を利用）
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
- Styleを適用して画像を丸角にする https://github.com/araemon/AndroidExercise/tree/master/TryRadius/app/src/main/java/com/apppppp/tryradius
- ライブラリを使ってチュートリアルの実装 https://phicdy.hatenablog.com/entry/2017/06/25/%E3%80%90Android%E3%80%91MaterialShowcaseView%E3%83%A9%E3%82%A4%E3%83%96%E3%83%A9%E3%83%AA%E3%81%A7%E3%83%81%E3%83%A5%E3%83%BC%E3%83%88%E3%83%AA%E3%82%A2%E3%83%AB%E3%82%92%E5%AE%9F%E8%A3%85%E3%81%99
- CardView全体の背景色を設定する（ちょっと特殊） https://codeday.me/jp/qa/20181212/48426.html
- Viewを両端に表示する http://blog.tappli.com/article/40838047.html
- AlertDialogに入力可能なEditTextを乗っける https://programming-jissen.com/how-to-create-dialog-that-can-be-input/
- 親Viewと子Viewの関連があると、ちゃんとremoveViewしないといけない。特にDialog http://sumahodays.sblo.jp/article/105586834.html
- ダイアログが閉じるListenerを検知する https://programming-jissen.com/how-to-get-event-to-close-dialog/
  - ちなみに、PositiveButton,NegativeButtonどちらともDismissListenerを通っちゃう。
- ラジオボタンのチェックをあらかじめ設定する https://seesaawiki.jp/w/moonlight_aska/d/%A5%E9%A5%B8%A5%AA%A5%DC%A5%BF%A5%F3%A4%CE%A5%C1%A5%A7%A5%C3%A5%AF%BE%F5%C2%D6%A4%F2%C0%DF%C4%EA%2C%20%BC%E8%C6%C0%A4%B9%A4%EB
- スピナー（プルダウン）の選択を設定する https://onestepbeyond7830.wordpress.com/2016/10/16/androidspinner選択値を強制的に変更する方法/

## 補足事項
- 使用しているリソースは「ICOOON MONO」さんからお借りしています。商用利用も可能です。
  - https://icooon-mono.com/
- 当アプリのロゴフォントは「Phenomena Bold」です。
  - https://www.fontfabric.com/fonts/phenomena/
- 当プロジェクトは、公開リポジトリにしておりますが、APIキーを記載したConfigファイルのみ非公開のためビルドできません🙇‍♂️
- 20200312
  - Toshikiくんの助言の通り、issueで管理するよう移行（したい）
- ラジオボタンのチェックをあらかじめ設定する https://seesaawiki.jp/w/moonlight_aska/d/%A5%E9%A5%B8%A5%AA%A5%DC%A5%BF%A5%F3%A4%CE%A5%C1%A5%A7%A5%C3%A5%AF%BE%F5%C2%D6%A4%F2%C0%DF%C4%EA%2C%20%BC%E8%C6%C0%A4%B9%A4%EBき

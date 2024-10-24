# shohinKanri

## 概要
CRUDに基づいた商品管理Webアプリケーション。

## 機能
- 商品の検索
- 商品の追加・更新・削除
- 登録されている全件をCSV形式で出力

## データベース作成に使用したSQL文
 詳細なSQL文は、以下のファイルをご覧ください :
 - [shohinKanri.sql](shohinKanri.sql)

## スクリーンショット

|   機能名      | スクリーンショット                             | 説明                   |
|-------------|----------------------------------------------|------------------------|
|  初期(検索)画面  |![初期](https://github.com/user-attachments/assets/12106f58-6205-4b32-bb07-d6e459871cdf) | 商品の検索・全件CSV出力・商品追加画面へ遷移が可能です <br> [index.jsp](shohinKanri_28/src/main/webapp/index.jsp) / |
|  検索結果画面  | ![検索結果](https://github.com/user-attachments/assets/b58b7ec4-7c2f-489e-b1cf-dc3cb8b421f5)| 商品名または商品分類で検索が可能です |
|  CSV出力  |![CSV出力](https://github.com/user-attachments/assets/245ee178-fdad-4c80-bd29-33f9ca0ca0e8) | CSV出力ボタンを押すとローカルのダウンロード直下へ出力されます |
| 　追加画面  | ![追加](https://github.com/user-attachments/assets/e23b39c6-c5e2-4f25-b506-9409a99d3f17)　| 商品の追加が可能です <br> [inputShohin.jsp](shohinKanri_28/src/main/webapp/WEB-INF/jsp/inputShohin.jsp)|
|  更新画面  |![更新](https://github.com/user-attachments/assets/b299a7be-a6ee-4501-b4d8-308facbbed18) | ラジオボタンで選択された商品の変更ができます <br> [updateShohin.jsp](shohinKanri_28/src/main/webapp/WEB-INF/jsp/updateShohin.jsp)|
|  削除画面  |![削除](https://github.com/user-attachments/assets/972cb610-5028-47b9-8206-ad4be386bc12) | ラジオボタンで選択された商品の削除ができます |

## コード遷移
1. [savlet](shohinKanri_28/src/main/java/servlet) <br>
  
2. [model](shohinKanri_28/src/main/java/model) <br>
・[ShohinLogic.java](shohinKanri_28/src/main/java/model/ShohinLogic.java)
・


## 開発の動機
 授業の一環としてこのアプリケーションを開発しました。CRUDに基づいた機能の作成を経験できました。

## 学びと反省
CSV出力の際ブラウザになげるコードがわからず、苦戦しました。
どういった語句で調べたらいいのか分からず時間はかかりましたが、できるだけ自分で調べて解決することを目標に一人で開発できたことはよかったです。

## 今後の改善点
 デザイン面に全く触れてないので作りこめる余地があります。商品分類が現在ラジオボタンのみで限られているので汎用性を高く改良したいです。


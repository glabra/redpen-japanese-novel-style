redpen-japanese-novel-style
===========================

日本の小説における一般的な作法について検査する RedPen Plugin です。

jarファイルを `$REDPEN_HOME/lib` に置いて使ってください。

この Plugin は、 [textlint-rule-general-novel-style-ja](https://github.com/io-monad/textlint-rule-general-novel-style-ja) に触発されています。


## RedundantFullStop
閉じ括弧の手前に句点が存在するか検査します。
句点が存在するとき、 `RedundantFullStop` はエラーを出力します。

句点は `FULL_STOP` に、閉じ括弧は `RIGHT_SQUARE_BRACKET` に対応します。

## RedundantComma
閉じ括弧の手前に読点が存在するか検査します。
読点が存在するとき、 `RedundantComma` はエラーを出力します。

読点は `COMMA` に、閉じ括弧は `RIGHT_SQUARE_BRACKET` に対応します。

## EvenSymbols
三点リーダーとダッシュが偶数個連続しているか検査します。
三点リーダーなどが奇数個連続しているとき、 `EvenSymbols` はエラーを出力します。

## OrphanedMinusSign
マイナス記号 (`U+2212`) が数字と独立して使われていないか検査します。
マイナス記号が数字と独立して使用されているとき、 `OrphanedMinusSign` はエラーを出力します。


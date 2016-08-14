# Full scratch RadioGroup and RadioButton

Android 6系のコードをBaseにフルスクラッチでRadioGroupとRadioButton的なViewを写経して実装してみた

「動機、実装前に気になったこと」の部分をカバーできる範囲の最低限の実装になってる


## 動機、実装前に気になったこと


### RadioGroupとRadioButtonみたいにRadioButtonにClickListenerみたいなの設定しなくても、親のListenerに通知が来るって実装はどうやってるのか気になった

* ViewGroup.OnHierarchyChangeListenerを使ってる
 * Viewの階層に変更があると呼び出されることをうまく使ってる
* ViewGroup.OnHierarchyChangeListenerのonChildViewAdded method内で追加されるRadioButtonに対して内部で作ったCompoundButton.OnCheckedChangeListenerを設定してる
 * この内部で作ったOnCheckedChangeListenerのonCheckedChanged method内でRadioButtonのチェック状態やチェックしてるViewのidなどを変更している
* 実際にはCompoundButton.setOnCheckedChangeWidgetListenerに設定してる
 * hideのAPIなので普通には使えない
 * http://tools.oesf.biz/android-6.0.0_r1.0/xref/frameworks/base/core/java/android/widget/CompoundButton.java#176
 * なんで普通のsetOnCheckedChangeListener使わないのかは、内部で設定してるListenerが上書きされると正常に動作しなくなるからだと思われる
 * javadocにも「This callback is used for internal purpose only.」って書いてあるからやっぱり内部実装用のListenerってことになる
 * hideだったので、FullScratchRadioButtonに同じように内部で使う用のListenerの口を用意してあげた


### RadioButtonにidを振ってなくても各Buttonがそれぞれ別々のButtonとして認識されているのはどうやってるのか気になった

* viewにidが振られていなかったら[View.generateViewId](https://developer.android.com/reference/android/view/View.html#generateViewId%28%29) methodでViewのidを作成して、setIdしてる
* View.generateViewId methodはAPI Level 17から追加されたもので、それ以下のバージョンでどう生成してたのか
* → hashCode methodの戻り値をViewのidにしてた


## 主な実装

* [FullScratchRadioGroup](https://github.com/operando/FullScratchRadioGroup/blob/master/app/src/main/java/com/os/operando/fullscratchradiogroup/widget/FullScratchRadioGroup.java)
* [FullScratchRadioButton](https://github.com/operando/FullScratchRadioGroup/blob/master/app/src/main/java/com/os/operando/fullscratchradiogroup/widget/FullScratchRadioButton.java)


## Link

* http://tools.oesf.biz/android-6.0.0_r1.0/xref/frameworks/base/core/java/android/widget/RadioGroup.java
* http://tools.oesf.biz/android-6.0.0_r1.0/xref/frameworks/base/core/java/android/widget/RadioButton.java
* http://tools.oesf.biz/android-4.0.3_r1.0/xref/frameworks/base/core/java/android/widget/RadioGroup.java
 * 後方互換のために読んだ
* https://developer.android.com/reference/android/widget/RadioGroup.html
* https://developer.android.com/reference/android/widget/RadioButton.html
* [カスタムビューでタッチイベントを受け取れない問題](http://android-note.open-memo.net/sub/trouble__cannot_catch_touch_event.html)
 * これが正しいかわからないが、FullScratchRadioButtonをタップしても全くタッチイベントが飛ばなくて仕方なくこれと同じような感じで実装した
 * ちゃんと理由を知る必要がありそう
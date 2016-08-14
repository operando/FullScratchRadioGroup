# Full scratch RadioGroup and RadioButton

Android 6系のコードをBaseにフルスクラッチでRadioGroupとRadioButton的なViewを写経して実装してみた

「動機、実装前に気になったこと」の部分をカバーできる範囲の最低限の実装になってる


## 動機、実装前に気になったこと

* RadioGroupとRadioButtonみたいにRadioButtonにClickListenerみたいなの設定しなくても、親のListenerに通知が来るって実装はどうやってるのか気になった
* RadioButtonにidを振ってなくても各Buttonがそれぞれ別々のButtonとして認識されているのはどうやってるのか気になった


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

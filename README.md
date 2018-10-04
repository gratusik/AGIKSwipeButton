# AGIKButtonSwipe
Swipe button with both direction function for android

[![](https://jitpack.io/v/gratusik/AGIKButtonSwipe.svg)](https://jitpack.io/#gratusik/AGIKButtonSwipe)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/gratusik/AGIKButtonSwipe/blob/master/LICENSE)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-15-e91e63.svg)](https://developer.android.com/about/versions/android-4.0.3)

![](https://raw.githubusercontent.com/gratusik/AGIKButtonSwipe/master/demo.gif)

# Gradle
The library is hosted on jitpack, add this to your build.gradle:
```
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```
```
dependencies {
	        implementation 'com.github.gratusik:AGIKButtonSwipe:1.0.1'
	}
```

# Usage
 Add `Swipe_Button_View` to your layout file, for example:
 Basic implementation:
```
  <com.agik.swipe_button.View.Swipe_Button_View
        android:id="@+id/start"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/stop"
        android:layout_marginTop="15dp"
        app:sb_swipe_text="Start"
        app:sb_swipe_animate_text="true"
        android:layout_marginRight="16dp"
        android:layout_marginLeft="16dp"
        />
```

# Listeners for swipe actions on the Swipe_Button_View

```
     start.setOnSwipeCompleteListener_forward_reverse(new OnSwipeCompleteListener() {
            @Override
            public void onSwipe_Forward(Swipe_Button_View swipeView) {

            }

            @Override
            public void onSwipe_Reverse(Swipe_Button_View swipeView) {

            }
        });
```

# Attribute information

|Attribute name|Default value|
|:-:|:-:|:-:|
|sb_swipe_bg_color| Green |
|sb_thumb_bg_color| white |
|sb_stroke_bg_color| white |
|sb_thumb_image| forward arrow |	
|sb_swipe_text| text |
|sb_swipe_text_color| white |
|sb_swipe_animate_text| True |
|sb_swipe_text_size| 16dp |
|sb_swipe_reverse| False |
|sb_swipe_both_direction| False |


# Reconfiguration method programmatically

```
// set the label
 setText(CharSequence text); 
 
 // set the text size
 setTextSize(int size);
 
 // set the drawable for the button
 setThumbImage(Drawable image);
 
 // set the label color
 setTextColor(@ColorInt int color) ;
 
 // set the thumb background color
 public void setThumbBackgroundColor(int color)
 
 // set the swipe background color
  public void setSwipeBackgroundColor(int color)
```

## Contributing

Contributions are welcome, feel free to submit a pull request.

# License

```
MIT License

Copyright (c) 2018 gratusik

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

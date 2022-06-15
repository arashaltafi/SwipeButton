# SwipeButton
[![](https://jitpack.io/v/arashaltafi/SwipeButton.svg)](https://jitpack.io/#arashaltafi/SwipeButton)

[SwipeButton](https://github.com/arashaltafi/SwipeButton) is a Custom Swipe For Left To Right And Right To Left

## Screenshots

![App Screenshot](https://cdn.dribbble.com/users/4393223/screenshots/18481406/media/15633bb1ef9d31c2c77175c92e3aee51.png?compress=1&resize=1200x900)

## Set-up

Dependency:
```groovy
dependencies {
	implementation 'com.github.arashaltafi:SwipeButton:1.3'
}
  ```
  
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

## Initialize

Kotlin:
```groovy
swipeBtnLogout.setOnActiveListener(object : OnActiveListener {
	override fun onActive() {
		Toast.makeText(this@MainActivity, "onActive", Toast.LENGTH_SHORT).show()
	}
})

swipeBtnLogin.setOnStateChangeListener(object : OnStateChangeListener {
	override fun onStateChange(active: Boolean) {
		Toast.makeText(this@MainActivity, "onStateChange $active", Toast.LENGTH_SHORT).show()
	}
})
```

Xml:

```groovy

//Logout
<com.arash.altafi.swipe.SwipeButton
	android:id="@+id/swipe_btn_logout"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	app:background_swipe_color="@drawable/back_slide"
	app:button_bottom_padding="20dp"
	app:button_image_height="90dp"
	app:button_image_width="140dp"
	app:button_left_padding="20dp"
	app:button_right_padding="20dp"
	app:button_top_padding="20dp"
	app:has_activate_state="true"
	app:initial_state="disabled"
	app:inner_date="2022/06/14"
	app:inner_day="Tuesday"
	app:inner_text_bottom_padding="18dp"
	app:inner_text_color="#000"
	app:inner_text_size="16sp"
	app:inner_text_top_padding="18dp"
	app:inner_time="01:53"
	app:is_rtl="false"
	app:text_sliding="Logout"
	app:text_sliding_swipe="Swipe To Right" />

//Login
<com.arash.altafi.swipe.SwipeButton
	android:id="@+id/swipe_btn_login"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	app:background_swipe_color="@drawable/back_slide_login"
	app:button_bottom_padding="20dp"
	app:button_image_height="90dp"
	app:button_image_width="140dp"
	app:button_left_padding="20dp"
	app:button_right_padding="20dp"
	app:button_top_padding="20dp"
	app:has_activate_state="true"
	app:initial_state="disabled"
	app:inner_date="2022/06/14"
	app:inner_day="Tuesday"
	app:inner_text_bottom_padding="18dp"
	app:inner_text_color="#000"
	app:inner_text_size="16sp"
	app:inner_text_top_padding="18dp"
	app:inner_time="01:53"
	app:is_rtl="true"
	app:text_sliding="Login"
	app:text_sliding_swipe="Swipe To Left" />
```

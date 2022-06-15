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

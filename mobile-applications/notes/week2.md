## Week 2

### Application Components
* **Activity** (enables user to give and receive information to and from an
application - has user interface)
* **Service** (support long-running or in the background operations)
* **BroadcastReceiver** (listen for and respond to events that happen on a device)
* **ContentProvider** (allow multiple applications to store and share data)

#### Activity
* Primary class for user interface
* Usually implements a single focused task that the user can do (e.g., dialing a phone number)

#### Service
* Run in the background (e.g., music player's MediaPlaybackService)
* To perform long-running operations
* To support interration with remote processes

#### BroadcastReceiver
* Component that listens for and respond to events (e.g., sms receiver)
* The subscriber in publish / subscribe pattern
* Events represented by the Intent class and the Broadcast
* BroadcastReceiver receives and respond to broadcast events

#### ContentProviders
* Store and share data across applications (e.g., BrowserProvider)
 * Uses database-style interface (encapsulate datasets)
 * Handles interprocess communication
* Android supports several system-wide ContentProviders

### Implement MapLocation Activity
* User enters an address
* App displaces a map of area around the address

### Build an Application
Android Project => Compilation and Packaging => Android Package (```.apk```) [```.dex```
files, resources ```.arsc```, uncompiled resources, ```AndroidManifest.xml```] => Signing
=ADB=> Device for Emulator

### Creating an Android App
1. Define resources
2. Implement application classes
3. Package application
4. Install & run application

#### 1. Defining resources
* Resources are non-source code entities
* Many different resource types, such as Layout, Strings, Images, Menus, & Animations
* Allow apps to be customized for different devices and users

    **Strings**
* Types: String, String Array, Plurals (string arrays that can be used
to choose specific strings that are associated with certain quantities, such as
one book, two books)
* Typically stored in ```res/values/*.xml```
 * ```<string name="hello">Hello World!</string>```
 * Can include formatting and styling
* Accessed by other resources as: ```@string/tring_name```
* Accessed in Java as: ```R.string.string_name```

    **User Interface Layout**
* UI layout specified in XML files
 * some tools allow visual layout
* XML files typically stored in ```res/layout/*.xml```
* Accessed in Java as: ```R.layout.layout_name```
* Accessed by other resources as: ```@layout/layout_name```
* Using multiple layout files - can specify different layout files based on your
device's orientation, screen size, etc.

    **R.java**
* At compilation time, resources are used to generate the R.java class
* Java code uses the R class to access resources

#### 2. Implement Classes
* Usually involves at least one activity
* Activity initialization code usually in ```onCreate()```

    **Typical onCreate() workflow**
* Restore saved state
* Set content view
* Initialize UI elements
* Link UI elements to code actions

#### 3. Package Application
* System packages application components & resources into a ```.apk``` file
* Developers specify required application information in a file called ```AndroidManifest.xml```

    **AndroidManifest.xml** Information includes:
* Application name
* Components
* Other
 * Required permissions
 * Application hardware features
 * Minimum API level

#### 4. Install & Run
* From Eclipse run in the emulator or device
* From command line
 * Enable USB Debugging on the device: ```Settings > Applications > Development > USB Debugging```
 * ```% adb install <path_to_apk>```
 * A few extra steps. In particular, in Eclipse, click on ```File > Export > Export > Export Android Application```.
 From there you will need to follow a dialog that involves signing the ```.apk``` file.
 Eclipse normally puts the ```debug.keystore``` in the ```~/.android directory```.
 See [Signing in Debug Mode](http://developer.android.com/tools/publishing/app-signing.html)
 for further explanation and the various passwords you'll need. Once the dialog completes,
 you can issue the adb install command to install the ```.apk``` file on your device.

### The Activity Class
> **Activity**
* Provides a visual interface for user interaction
* Each activity typically supports one focused thing a user can to, such as viewing an email message, showing a login screen
* Applications often comprise several activities

> **Navigation through Activities**
* Android supports navigation in several ways:
 * Tasks
 * The task backstack
 * Suspending and resuming activities

> **Tasks**
* A task is a set of related activities
* These related activities don't have to be part of the same application
* Most tasks start at the home screen

> **Task Backstack**
* When an activity is launched, it goes on top of the backstack
* When the activity is destroyed, it is popped off the backstack

> **The Activity Lifecycle**
* Activities are created, suspended, resumed & destroyed as necessary when an application executes
* Some of these actions depend on user behavior
* Some depend on Android, e.g., Android can kill activities when it need their resources
* Activity lifecycle States
 * Resumed/Running - visible, user interacting
 * Paused - visible, user not interating, can be terminated*
 * Stopped - not visible, can be terminated
* The Activity Lifecycle methods
 * Android announces activity lifecycle state changes to activity by calling special activity methods
 * Some Activity Callback Methods:
  - ```protected void onCreate(Bundle savedInstanceState)```
  - ```protected void onStart()```
  - ```protected void onResume()```
  - ```protected void onPause()```
  - ```protected void onRestart()```
  - ```protected void onStop()```
  - ```protected void onDestroy()```

> onCreate()
* Called when activity is created
* Sets up initial state
 * Call ```super.onCreate()```
 * Set the activity's content view
 * Retain references to UI views as necessary
 * Configure views as necessary

> onRestart()
* Called if the activity has been stopped and is about to be started again
* Typical actions
 * Special processing needed only after having be stopped

> onStart()
* Activity is about to become visible
* Typical actions
 * Start when visible-only behaviors
 * Loading persistent application state

> onResume()
* Activity is visible and about to start interacting with user
* Typical actions
 * Start foreground-only behaviors

> onPause()
* Focus about to switch to another activity
* Typical actions
 * Shutdown foreground-only behaviors
 * Save persistent state

> onStop()
* Activity is no longer visible to user
 * May be restarted later
* Typical actions
 * Cache state
* May not be called if android kills your application (due to low memory on the device)

> onDestroy()
* Activity is about to be destroyed
* Typical actions
 * Release activity resources
* May not be called if android kills your application (due to low memory on the device)

> **Starting Activities**
* Create an Intent object specifying the activity to start
* Pass newly created Intent to methods, such as
 * ```startActivity()```
 * ```startActivityForResult()``` Involves a callback method when the called activity finishes to return a result

> Activity.setResult()
* Started activity can set its result by calling `Activity.setResult()`
 * `public final void setResult(int resultCode)`
 * `public final void setResult(int resultCode, Intent data)`
* resultCode (an int)
 * `RESULT_CANCELED`
 * `RESULT_OK`
 * `RESULT_FIRST_USER` custom resultCodes can be added

> **Configuration Changes**
* Keyboard, orientation, locale, etc.
* Device configuration can change at runtime
* On configuration changes, Android usually kills the current activity & then restarts it
* Activity restarting should be fast
* If necessary you can:
 * Retain an Object containing important state information during a configuration change
 * Manually handle the configuration change

> Retaining an Object
* Hard to recompute data can be cached to speed up handling of configuration changes
* Override `onRetainNonConfigurationInstance()` to build and return configuration Object
 * Will called between `onStop()` and `onDestroy()`
* Call `getLastNonConfigurationInstance()` during `onCreate()` to recover retained Object
* **Note:** These methods have been deprecated in favor of methods in the Fragment class

> Manual Reconfiguration
* Can prevent system from restarting Activity
* Declare the configuration changes your activity handles in `AndroidManifest.xml` file, e.g.,
```java
<activity android:name=".MyActivity"
    android:configChanges=
        "orientation|screensize|keyboardHidden"...>
```
* When configuration changes, Activity's `onConfigurationChanged()` method is called
* Passed a Configuration object specifying the new device configuration

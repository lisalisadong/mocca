## Week 3

### The Intent Class
A data structure that represents:
* An operation to be performed, or
* An event that has occurred

Intent as Desired Operations:
* Intents provide a flexible language for specifying operations to be performed
 * e.g., pick a contact, take a photo, dial a phone number
* Intent is constructed by one component that wants some work done
* Received by one activity can perform that work

Intent Fields:
* Action
* Data
* Category
* Type
* Component
* Extras
* Flags

#### Action
* String representing desired operation
* Example:
 * `ACTION_DIAL` - dial a number
 * `ACTION_EDIT` - display data to edit
 * `ACTION_SYNC` - synchronize device data with server
 * `ACTION_MAIN` - start as initial activity of app
* Setting the Intent action:
 * `Intent newInt = new Intent(Intent.ACTION_DIAL);` - pass an action string as
 a parameter to the Intent constructor
 ```java
 Intent newInt = new Intent();
 newInt.setAction(Intent.ACTION_DIAL);
 ```
 Alternatively, create an empty Intent and call the `setAction` method

#### Data
* Data associated with the Intent
 * Formatted as a Uniform Resource Identifier (URI)
* Example:
 * Data to view on map:
 `Uri.parse("geo:0,0?q=1600+Pennsylvania+Ave+Washington+DC")`
 * Number to dial in the phone dialer:
 `Uri.parse("tel:+15555555555")`
* Setting Intent data:
 * `Intent newInt = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+15555555555"));` or
 ```java
 Intent newInt = new Intent(Intent.ACTION_DIAL);
 newInt.setData(Uri.parse("tel:+15555555555"));
 ```

#### Category
* Additional information about the components that can handle the intent
* Example:
 * `Category_browsable` - can be invoked by a browser to display data ref's by a URI
 * `Category_launcher` - can be the initial activity of a task & is listed in top-level
 app launcher

#### Type
* Specifies the MIME type of the Intent data
* Examples:
 * `image/*`, `image/png`, `image/jpeg`
 * `text/html`, `text/plain`
* If unspecified, Android will infer the type
* Setting the type:
 * `Intent.setType(String type)` or
 * `Intent.setDataAndType(Uri data, String type)`

#### Component
* The component that should receive this intent
* Use this when there's exactly one component that should receive the intent
* Setting the component:
 * `Intent newInt = Intent(Context packageContext, Class<?> cls)` or
 * `Intent newInt = new Intent();` and one of: `setComponent()`, `setClass()`, or
 `setClassName()`

#### Extras
* Additional information associated with the intent
* Treated as a map (key-value pairs)
* Example:
 * `Intent.EXTRA_EMAIL`: email recipient
 ```java
 Intent newInt = new Intent(Intent.ACTION_SEND);
 newInt.putExtra(android.content.Intent.EXTRA_EMAIL,
        new String[]{
                "aporter@cs.umd.edu", "ceo@microsoft.com",
                "potus@whitehouse.gov", "mozart@musician.org"
                }
 );
 ```
* Setting the Extra attributes
 * Several forms depending on data type
 * `putExtra(String name, String value)`
 * `putExtra(String name, float[] value)`

#### Flags
* Specify how Intent should be handle
* Example:
 * `FLAG_ACTIVITY_NO_HISTORY` don't put this Activity in the History stack
 * `FLAG_DEBUG_LOG_RESOLUTION` print extra logging information when this Intent
 is processed

### Starting Activities with Intents
* `startActivity(Intent intent, ...)`
* `startActivityForResult(Intent intent, ...)`

  **The target Activity**
 * can be named explicitly by setting the intent's component
 * can be determined implicitly, when the activity to be activated is not explicitly
 named, Android tries to find Activities that match the Intent (this process is
 called intent resolution)

  **Intent resolution process:**
 * An Intent describing a desired operation
 * IntentFilters which describe which operations an Activity can handle

  **Intent resolution data:**
 * Action
 * Data (both URI and TYPE)
 * Category

  **Specifying IntentFilters:**
    ```xml
    <activity ...>
        <intent-filter ...>
            ...
            <action android:name="actionName"/>
            ...
        </intent-filter>
    </acivity>
    ```
  Example: Handling Intent.ACTION_DIAL
  ```xml
  <activity ...>
      <intent-filter ...>
          ...
          <action android:name="android.intent.action.DIAL"/>
          ...
      </intent-filter>
  ...
  </acivity>
  ```
  **Adding Data to IntentFilter:**
  ```xml
  <intent-filter ...>
      ...
      <data
       android:mimeType="string"
       android:scheme="string"
       android:host="string"
       android:port="string"
       android:path="string"
       android:pathPattern="string"
       android:pathPrefix="string"
      />
      ...
  </intent-filter>
  ```
  Example: Handling geo: scheme Intents:
  ```xml
  <intent-filter ...>
      ...
      <data android:scheme="geo"/>
      ...
  </intent-filter>
  ```
  **Adding a Category to IntentFilter:**
  ```xml
  <intent-filter ...>
      ...
      <category android:name="string"/>
      ...
  </intent-filter>
  ```
  Example: Maps Application
  ```xml
  <intent-filter ...>
      <action android:name="android.intent.action.VIEW" />
      <category android:name="android.intent.category.DEFAULT" />
      <category android:name="android.intent.category.BROWSEABLE" />
      <data android:scheme="geo" />
  </intent-filter>
  ```
  **Receiving Implicit Intents**
  * Note: to receive implicit intents an application should specify an IntentFilter
  with the category `andorid.intent.category.DEFAULT`

  **Priority**
  * `android:priority` - priority given to parent component when handling matching
  Intents
  * Causes Android to prefer one Activity over another
  * Value should be greater than -1000 and less than 1000
  * Higher values represent higher priorities

  **Investigate Intent Filters**
  * `% adb shell dumpsys package`

### Permissions
* Android protects resources & data with permissions
* Used to limit access to:
 * User information - e.g., contacts
 * Cost-sensitive API's - e.g., SMS/MMS
 * System resources - e.g., Camera
* Permisions are represented as strings
* In `AndroidManifest.xml`, apps declare the permissions
* They use themselves
* They require of other components

  #### Using Permisions
  * Application specify permissions they use through a <uses-permission> tag
  * Users must accept these permissions before an application can be installed
    ```xml
    <manifest ...>
        <uses-permission android:name="android.permission.CAMERA"/>
        <uses-permission android:name="android.permission.INTERNET"/>
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
        ...
    </manifest>
    ```

  #### Defining Permissions
  * Apps can also define and enforce their own permissions using `<permission>` tag
  * Suppose your application performs a privileged/dangerous operation, you
  might not want to allow just any application to invoke yours, so you can
  define & enforce your own permission

  #### Uses-Permission
  * An application can declare/accept the permissions of the applications it uses

  #### Component Permissions
  * Individual components can set their own permissions, restricting wich other components can access them
  * Component permissions take precedence over application-level permissions

    **Activity Permissions**
    * Restricts which components can start the associated activity
    * Checked within execution of
     * `startActivity()`
     * `startActivityResult()`
    * Throws SecurityException on permissions failure

    **Service Permissions**
    * Restricts which components can start or bind to the associated service
    * Checked within execution of
     * `Context.startService()`
     * `Context.stopService()`
     * `Context.bindService()`
    * Throws SecurityException on permissions failure

    **BroadcastReceiver Permissions**
    * Restricts which components can send & receive broadcasts
    * Permissions checked in multiple places

    **ContentProvider Permissions**
    * Restrict which components can read & write the data in a ContentProvider

### The Fragment Class
For better supporting user interfaces for devices with large screen such as tablets

  **Tablet UIs**
  * Tablets have larger displays than phones do
  * They can support multiple UI panes / user behaviors at the same time
   * The 1 activity - 1 thing the user can do heuristic may not work for larger devices

    #### Fragment
    * Represents a behavior / portion of UI within an Activity
    * Multiple Fragments can be embedded in an Activity to create a multi-pane UI
    * A single Fragment can be reused across multiple Activities

    #### Fragment Lifecycle
    * Fragment lifecycle is coordinated with the lifecycle of its containing Activity
    * Fragments have their own lifecycles and receive their own callbacks

    #### Fragment Lifecycle States
    * Resumed
     * Fragment is visible in the running activity
    * Paused
     * Another activity is in the foreground and has focus, containing activity is visible
    * Stopped
     * The fragment is not visible

    #### Lifecycle Callback Methods
    * Activity Created:
     * `onAttach()` - fragment is first attached to its activity
     * `onCreate()` - initialize the fragment (unlike the activity.onCreate, user
         interface is not set up in fragment.onCreate)
     * `onCreateView()` - Fragment sets up & returns its user interface
     * `onActivityCreated()` - containing activity has completed onCreate() and
     the fragment has been installed
    * Activity Started:
     * `onStart()` - hosting activity about to become visible
    * Activity Resumed:
     * `onResume()` - hosting activity is about to become visible and ready for
     user interaction
    * Activity Paused:
     * `onPause()` - hosting activity is visible, but does not have focus
    * Activity Stopped:
     * `onStop()` - hosting activity is no longer visible
    * Activity Destroyed:
     * `onDestroyView()` - view previously created by `onCreateView()` has been
     detached from the activity; **typical actions**: clean up view resources
     * `onDestroy()` - fragment is no longer in use; **typical actions**: clean up
     fragment resources
     * `onDetach()` - fragment no longer attached to its activity; **typical actions**:
     null out references to hosting activity

    #### Adding Fragments to Activities
    * Two general ways to add Fragment to an Activity's layout
     * Declare it statically in the activity's layout file, or
     * Add it programmatically using the fragmentManager

    #### Fragment Layout
    * Layout can be inflated / implemented in `onCreateView()`
    * `onCreateView()` must return the View at the root of the Fragment's layout
    * This View is added to the containing activity

    #### Adding Fragments Dynamically
    * While an activity's running you can add a Fragment to its layout
     * Get reference to the fragmentManager
     * Begin a FragmentTransaction
     * Add the Fragment
     * Commit the FragmentTransaction

    #### Dynamic Layout
    * Fragment transactions allows you to dynamically change your app's user interface
    * Can make the interface more fluid & take better advantage of available screen space

    #### Configuration Changes
    * If you call `setRetainInstance(true)`, Android won't destroy the Fragment
    on configuration changes
    * Results in some changes to lifecycle callback sequence
     * `onDestroy()` will not be called
     * `onCreate()` will not be called

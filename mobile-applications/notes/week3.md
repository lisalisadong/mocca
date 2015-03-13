## Week 2

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
    * Restrict which components can read & write the data in a CntentProvider

## Week 4

### Android User Interfaces
* Activities usually display a user interface
* Android provides many classes for constructing user interfaces

### View
* Key building block for UI components
* Occupy a rectangular space on screen
* Responsible for drawing themselves and for handling events

  **Some Predefined Views**
  * Button
  * ToggleButton
  * Checkbox
  * RatingBar
  * AutoCompleteTextView

    **Button**
    * View that can be clicked on to perform an action

    **ToggleButton**
    * A 2-state button: checked/not checked state
    * Light indicator showing state

    **Checkbox**
    * Another 2-state button
    * Checked/not checked

    **RatingBar**
    * A view comprising a row of stars
    * The user can click or drag the stars to highlight some number of them

    **AutoCompleteTextView**
    * An editable text field that provides completion suggestions as the user types
    in next

### Common View operations
* Set visibility: show or hide view
* Set checked state
* Set listeners: Code that should be executed when specific events occur
* Set properties: opacity, background, rotation
* Manage input focus: allow view to take focus, request focus

### View Event sources
* User interaction
 * Touch
 * Keyboard/trackball/D-pad
* System control
 * Lifecycle changes

### Handling View Events
* Often handle events with listeners
* Numerous Listener interfaces defined by the View class
* Custom View subclasses can override various View methods, e.g.,
 * `onMeasure()` determine the size of this view and its children
 * `onLayout()` View must assign a size and position to all its children
 * `onFocusChanged()` view's focus status has changed
 * `onKeyUp()`, `onKeyDown()` a hardware key event has occurred
 * `onWindowVisibilityChanged` window containing view has changed its visibility status

### View Listener Interfaces
* `onClickListener.onClick()` called when view has been clicked
* `onLongClickListener.onLongClick()` called when View has been pressed and held
* `onFocusChangeListener.onFocusChange()` called when View has received or lost focus
* `onKeyListener.onKey()` called when View about to receive a hardware key press

### Displaying Views
* Views are organized in a Tree
* Displaying has multiple steps
* Measure - get dimensions of each view
* Layout - position each View
* Draw - draw each view

### ViewGroup
* An invisible view that contains other views
* Used for grouping & organizing a set of views
* Base class for view containers & layouts

  **Some predefined ViewGroups**
  * RadioGroup
  * TimePicker
  * DatePicker
  * WebView
  * MapView
  * Gallery
  * Spinner

    **RadioGroup**
    * A ViewGroup containing a set of Radio Buttons (CheckBoxes)
    * Only one button can be selected at any one instant

    **TimePicker**
    * A ViewGroup that allows the user to select a time

    **DatePicker**
    * A ViewGroup that allows the user to select a date

    **WebView**
    * A ViewGroup that displays a web page

    **MapView**
    * A ViewGroup that displays a Map

### Adapters & AdapterViews
* AdapterViews are views whose children and data are managed by an Adapter
* Adapter manages the data and provides data views to AdapterView

  **ListView**
  * AdapterView displaying a scrollable list of selectable items
  * Items managed by a ListAdapter
  * ListView can filter the list of items based on text input

  **Spinner**
  * An AdapterView that provides a scrollable list of items
  * User can select one item from the list
  * Item managed by a SpinnerAdapter

  **Gallery**
  * A ViewGroup showing a horizontally scrolling list
  * Item managed by a SpinnerAdapter

### Layouts
* A generic ViewGroup that defines a structure for the Views it contains

  #### LinearLayout
  * Child views arranged in a single horizontal or vertical row

  #### RelativeLayout
  * Child views are positioned relative to each other and to parent view

  #### TableLayout
  * Child views arranged into rows and columns

  #### GridView
  * Child views arranged in a two-dimensional, scrollable grid

### Menus and ActionBar
* Activities support menus
* Activities can
 * Add items to a menu
 * Handle clicks on the menu items

  #### Menu Types
  * Options
   * Menu shown when user presses the menu button
  * Context
   * View-specifiv menu shown when user presses and holds the view
  * Submenu
   * A menu activated when user touches a visible menu item

  #### Creating Menus
  * Define menu resource in XML file
   * Store in `res/menu/filename.xml`
  * Inflate menu resource using Menu Inflator in `onCreate...Menu()` methods
  * Handling item selection in appropriate `on...ItemsSelected()` methods

  #### Menu - Many other features supported
  * Grouping menu items
  * Binding shortcut keys to menu items
  * Binding intents to menu items

  #### ActionBar
  * Similar to application bar in many desktop applications
  * Enables quick Access to common operations

  #### FragmentDynamicLayoutWithActionBar
  * Shows play titles and one quote from the selected play
  * Provides actions for the ActionBar
  * Three main objects
   * QuoteViewerActivity
   * TitleFragment
   * QuoteFragment

  #### ActionBar.Tab
  * Each tab is associated with one Fragment
  * Exactly one tab is selected at any given time
  * Fragment corresponding to the selected tab is visible in the content area

### Dialogs
* Independent subwindows used by Activities to communicate with user
  #### Dialog subclasses
  * AlertDialog
  * ProgressDialog
  * DatePickerDialog
  * TimePickerDialog

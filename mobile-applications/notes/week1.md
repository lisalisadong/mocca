## Week 1

### The Android Platform
* A multi-layered software stack for mobile devices:
  * OS kernel, system libraries, application frameworks & key apps
* Android SDK for creating apps:
  * libraries & development tools
  * lots of documentation.

See: http://developer.android.com/training

### The Android Architecture
#### Linux kernel
Standard services:
* Security
* Memory & Process Management
* File & Process I/O
* Device Drivers

Android specific:
* Power Management
* Android shared Memory
* Low Memory Killer
* Interprocess Communication
* Binder (IPC) Driver
And much more.

#### Libraries
- System C library
 - Bionic libc
- Surface Mgr.
 - Display
 - Management
- Media
- Framework
 - Audio/Video
- Webkit
 - Browser engine
- OpenGL
 - Graphics engines
- SQLite
 - Relational
 - Database engine

#### Android Runtime
Two main components:
- Core Java libraries
 - Basic Java classes -- ```Java.*```, ```JavaX.*```
 - App lifecycle -- ```Android.*```
 - Internet/Web services -- ```org.*```
 - Unit testing -- ```Junit.*```
- Dalvik Virtual Machine
 - Apps are executed by the Dalvik virtual machine
 - Typical workflow: App written in Java -> Compiled to Java bytecode files -> DX converts Java bytecode files to a single dex bytecode file (classes.dex) -> Dalvik executes dex bytecode file
 - Designed for resource-constrained environments (slower CPU, less RAM, limited battery life)

#### Application Framework
- Package manager
 - Keeps track of app packages on device
- Window manager
 - Manages the windows comprising an app
- View system
 - Provides common user interface elements (e.g., icons, text entry boxes, buttons and more)
- Resource manager
 - Manages non-compiled resources (e.g., strings, graphics, and layout files)
- Activity manager
 - Manages app lifecycle and navigation stack
- Content provider
 - Inter-application data sharing
- Location manager
 - Provides location and movement information
- Notification manager
 - Place notification icons in the status bar when important events occur

#### Applications
Standard apps include:
- Home - main screen
- Contacts - contacts database
- Phone - dial phone numbers
- Browser - view web pages
- Email reader - compose & read email messages

None of these apps is hard-coded into the system. You can substitute your own or 3rd party app for any of them.

### The Android Development Environment
The workbench for writing android application.
- Android Developer Tools (ADT) Bundle
 - Latest android platform
 - Eclipse + ADT plugin
 - Latest system image for emulator
 - Additional development tools

### The Android Emulator
Runs virtual devices.

Pros:
- Doesn't require an actual phone.
- Hardware is reconfigurable
- Changes are non-destructive

Cons:
- Can be very slow
- Some features unavailable (e.g., no support for bluetooth or USB connections)
- Performance / User experience can be misleading

Advanced features:
- Can emulate many different device/ User characteristics, such as network speed/latencies, battery power, location coordinates.
    ```
# connect to emulator
telnet localhost 5554
# change network speed
network speed edge
network speed full
# change power capacity
power capacity 5
# change charging status
power status not-charging
# change GPS location
geo fix 0.00 40.00
    ```
- Emulate incoming phone calls & SMS messages
    ```
telnet localhost 5554
# send SMS to emulator
sms send 3055555555 "This is a text message"
    ```
- Can interconnect multiple emulators

### Debugger
Tool for examining the internal state of a running application.
- Dalvik Debug Monitor Service (DDMS)
 - General tool for monitoring application behaviors
 - Include: file explorer, logcat, traceview, hierachyview

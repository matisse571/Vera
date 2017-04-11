# Vera

---Use at your own risk---

Vera scene controller for Wink Relay.  Requires root and changing of permissions for key files.  The address of the controller is hard coded into the program and needs to be personalized.  

This program is a bit of a Frankenstein and was created from bits and pieces of information and code off the web.  It is being published not as an example of a good program, but to help document aome of the Wink Relay features and how they can be used for other uses.  The following pages helped me understand some of the concepts and much of the code was cut and pasted from their work.  Important to add that since I really don't know what I am doing, I am sure this program could be re-written much more elegantly and nothing I have done reflects on their skills or work as it has been appropriated for this project.

http://android.serverbox.ch/?p=972

This page provided the insight to the gpio interrupts needed to activate the two buttons on the Relay.  Important to note that the edge of the gpios is set to none, so a script is needed to set them to rising, falling or both depending on requirements.
gpio7 - bottom button
gpio8 - top button
gpio30 - turns the screen on and off - not used in this program
gpio203 and 204 - switch relays - not used in this program

http://www.roman10.net/2011/08/06/android-fileobserverthe-underlying-inotify-mechanism-and-an-example/

At one point this program also controlled truning the screen on and off, but the function was removed and moved to a stand alone program.  For reference, the way it was achieved was listening to the screen input file.

/dev/input/event0 - screen input file
/sys/bus/i2c/devices/2-005a/input/input3/ps_input_data - proximity sensor

https://code.tutsplus.com/tutorials/create-a-weather-app-on-android--cms-21587

This was the reference for adding the weather portion to the program.  To use this feature you will need an Open Weather key.  The key and city are stored in the strings file.  Instead of using the weather font, started to impliment changing the screen background image as an indicator, but this is incomplete.

http://stacktips.com/tutorials/android/repeat-alarm-example-in-android

Used to understand alarms for refreshing the weather.

http://readmenow.in/enable-soft-keys-on-android/

Not part of the program, but useful information on adding soft keys to the Relay.

https://forum.xda-developers.com/showthread.php?t=2565758

Again not part of the program, but what was used to root the Relay.

---Use at your own risk---




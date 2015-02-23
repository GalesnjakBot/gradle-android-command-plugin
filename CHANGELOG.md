# 1.4.0

- add Files task to support adb push / pull functionality (see sample project for an example)
- derive adb location from android plugin (contribution by Emanuele Zattin)
- better exception on missing android plugin (contribution by Emanuele Zattin)
- allow to specify seed in monkey task (contribution by Emanuele Zattin)
- fix broken script task on GenyMotion (contribution by Eugen Martynov) 
- update to Gradle 2.2 (via wrapper)

# 1.3.0

- add an Input task to support basic adb scripting
- add subgrouping of plugin tasks either by variant name or sub task based on a new setting "sortBySubtasks" (defaults to false)
- updated Gradle 2.1 (via wrapper)
- sample app: android-gradle 0.14, making it compatible with Android Studio 0.9+

# 1.2.1

- added fallback if there is no SDK version available from the device properties
- add descriptions to tasks and group them together (instead of "Other Tasks")

# 1.2.0

Complete rewrite of the plugin.

### New commands

Replaced generic ADB command with useful predefined tasks:
- `installDevice<Variant>`
- `uninstallDevice<Variant>`
- `run<Variant>`
- `monkey<Variant>` - the default number of monkeyrunner events is 10000, change via setting _android.events_
- `clearPreferences<Variant>`

See [README](https://github.com/novoda/gradle-android-command-plugin/blob/master/README.md) for examples.

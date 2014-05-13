gradle-android-command-plugin
=============================

Use gradle tasks to run specific command, such as:

- find all devices attached and get basic info about them
- select the first one that complies with a custom rule
- install a specific APK from the available build types + flavours
- clear preferences or do something related to the APK to prepare for tests
- run monkeyrunner for that specific APK on that specific device
- uninstall the APK


This is particularly useful for CI servers but could be used to speed up IDE development as well.

Usage
=============================

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'com.novoda:gradle-android-command-plugin:1.0'
    }
}
apply plugin: 'android-command'
```

To get the current snapshot version:

```groovy
buildscript {
    repositories {
        mavenCentral()
        maven {
            url "https://oss.sonatype.org/content/repositories/snapshots/"
        }
    }
    dependencies {
        classpath 'com.novoda:gradle-android-command-plugin:1.2.0'
    }
}
apply plugin: 'android-command'
```



Example
=============================

The plugin makes available new tasks `installDevice<Variant>`, `uninstallDevice<Variant>`, `run<Variant>`, `monkey<Variant>`, `clearPreferences<Variant>`.
Just apply the plugin via

```groovy
apply plugin: 'android-command'
```

If you have a special case for your tasks you can define your own tasks or override
default values as shown below.

```groovy
android {
        events 1000

        // run on device with highest SDK version
        task('runNewest', com.novoda.gradle.command.Run, ['installDevice']) {
            deviceId {
                def device = devices().max({ it.sdkVersion() })
                if (!device) {
                    throw new GroovyRuntimeException('No device found!')
                }
                device.id
            }
        }
}
```

License
=======

    (c) Copyright 2014 Novoda

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


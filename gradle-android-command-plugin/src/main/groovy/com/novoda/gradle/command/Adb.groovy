package com.novoda.gradle.command


public class Adb extends org.gradle.api.DefaultTask {

    protected pluginEx = project.android.extensions.findByType(AndroidCommandPluginExtension)

    // set automatically by VariantConfigurator
    def apkPath

    // set automatically by VariantConfigurator
    def variationName

    def deviceId

    def getDeviceId() {
        deviceId ?: pluginEx.deviceId
    }

    def packageName = "${-> packageName()}"
    def launchableActivity = "${-> launchableActivity()}"

    protected handleCommandOutput(def text)  {
        logger.info text
    }

    protected runCommand(def parameters) {
        def deviceId = getDeviceId()
        def adbCommand = ["$pluginEx.adb", '-s', deviceId] + parameters
        logger.info "running command: " + adbCommand
        assertDeviceConnected(deviceId)
        handleCommandOutput(adbCommand.execute().text)
    }

    void assertDeviceConnected(def deviceId) {
        if (!pluginEx.attachedDevices().contains(deviceId))
            throw new IllegalStateException("Device $deviceId is not found!")
    }

    protected packageName() {
        def matcher = readApkProperty('package').readLines()[0] =~ /name='(.*?)'/
        if (matcher) {
            matcher[0][1]
        }
    }

    protected launchableActivity() {
        def matcher = readApkProperty('launchable-activity').readLines()[0] =~ /name='(.*?)'/
        if (matcher) {
            matcher[0][1]
        }
    }

    private String readApkProperty(String propertyKey) {
        if (apkPath == null) {
            throw new IllegalStateException("No apk found for the task $name")
        }
        String output = ["$pluginEx.aapt", "dump", "badging", "$apkPath"].execute().text.readLines().find {
            it.startsWith("$propertyKey:")
        }
        if (output == null) {
            throw new IllegalStateException("Could not read property '$propertyKey' for apk $apkPath")
        }
        output
    }
}

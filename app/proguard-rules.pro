# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/sultanm/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# ez-vcard
-dontwarn ezvcard.io.json.**            # JSON serializer (for jCards) not used
-dontwarn freemarker.**                 # freemarker templating library (for creating hCards) not used
-dontwarn org.jsoup.**                  # jsoup library (for hCard parsing) not used
-dontwarn sun.misc.Perf
-keep,includedescriptorclasses class ezvcard.property.** { *; } # keep all VCard properties (created at runtime)
-keep class !ezvcard.Ezvcard, ezvcard.* { *; }

# for sugardb to work
# Ensures entities remain un-obfuscated so table and columns are named correctly
-keep class opencontacts.open.com.opencontacts.orm.** { *; }

#lodash
-dontwarn com.github.underscore.lodash.*

##okhttp starts
# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
##okhttp ends

#Keep all the app's source code intact atleast upto names. Optimization might still happen
-keepclasseswithmembers class opencontacts.open.com.opencontacts.** {*;}
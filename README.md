# Assigment #

	Implement an Android native app .Contain Two Functionalists :
	1)User input screen(Project Type ,Project Language)
	2)Show data from api 


### Build Configuration ###
     This project was build on JDK 1.8.0
    compileSdkVersion 28
    minSdkVersion 15
    targetSdkVersion 28
	

### AndroidMainfest Permission 
	
#### Internet ####
     
	 <uses-permission android:name="android.permission.INTERNET" />
	




### Dependencies Used###

#### Butterknife ####

    implementation 'com.jakewharton:butterknife:8.8.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
	Butterknife is used to Bind the view with activity/fragments.
	

#### Recyclerview ####

    implementation 'com.android.support:recyclerview-v7:28.0.0'
	


#### Retrofit ####
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.4.1'
    implementation 'com.squareup.retrofit2:converter-gson:3.4.1'
    implementation 'com.squareup.retrofit2:adapter-rxjava:2.4.0'
    implementation 'io.reactivex:rxandroid:1.1.0'



#### Dagger ####
    implementation 'com.google.dagger:dagger:2.14.1'
    annotationProcessor 'com.google.dagger:dagger-compiler:2.14.1'
#include "Hardware_windmill.h"

JNIEXPORT jfloat JNICALL Java_Hardware_windmill_energyProduction(JNIEnv * var, jclass var2){
	jfloat test = 3.0;
	return test;
}

/*
* Class:     Hardware_windmill
* Method:    turnOn
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_Hardware_windmill_turnOn
(JNIEnv *var, jclass var2, jint var3){
	return 1;
}

/*
* Class:     Hardware_windmill
* Method:    isOn
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_Hardware_windmill_isOn
(JNIEnv *var, jclass var2){
	return 2;
}

/*
* Class:     Hardware_windmill
* Method:    error
* Signature: ()Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_Hardware_windmill_error
(JNIEnv *var, jclass var2){

}
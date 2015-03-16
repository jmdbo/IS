#define _CRT_SECURE_NO_WARNINGS
#include "Hardware_windmill.h"
#include "hardware.h"
#include <stdlib.h>
#include <string.h>

JNIEXPORT jfloat JNICALL Java_Hardware_windmill_energyProduction(JNIEnv * env, jclass javaClass){
	jfloat energy = energyProduction();
	return energy;
}

/*
* Class:     Hardware_windmill
* Method:    turnOn
* Signature: (I)I
*/
JNIEXPORT jint JNICALL Java_Hardware_windmill_turnOn
(JNIEnv *env, jclass javaClass, jint state){
	return turnOn(state);
}

/*
* Class:     Hardware_windmill
* Method:    isOn
* Signature: ()I
*/
JNIEXPORT jint JNICALL Java_Hardware_windmill_isOn
(JNIEnv *var, jclass javaClass){
	return isOn();
}

/*
* Class:     Hardware_windmill
* Method:    error
* Signature: ()Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_Hardware_windmill_error
(JNIEnv *env, jclass javaClass){
	char* errorMsg = error();
	jstring errorJava = (*env)->NewStringUTF(env, errorMsg);
	return errorJava;
}
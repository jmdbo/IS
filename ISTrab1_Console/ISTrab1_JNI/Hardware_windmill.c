#define _CRT_SECURE_NO_WARNINGS
#include "Hardware_windmill.h"
#include "hardware.h"
#include <stdlib.h>
#include <string.h>

JNIEXPORT jfloat JNICALL Java_Hardware_windmill_energyProduction(JNIEnv * var, jclass var2){
	jfloat energy = energyProduction();
	return energy;
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
	jstring teste3 = (*var)->NewStringUTF(var, "teste");
	return teste3;
}
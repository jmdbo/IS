#define _CRT_SECURE_NO_WARNINGS
#include "Hardware_windmill.h"
#include "hardware.h"
#include <stdlib.h>
#include <string.h>


	JNIEXPORT jfloat JNICALL Java_Hardware_windmill_energyProduction(JNIEnv * env, jclass javaClass){
		float energy = energyProduction();
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
		(JNIEnv *env, jclass javaClass){
		return isOn();
	}

	/*
	* Class:     Hardware_windmill
	* Method:    error
	* Signature: ()Ljava/lang/String;
	*/
	JNIEXPORT jstring JNICALL Java_Hardware_windmill_error
		(JNIEnv *var, jclass var2){
		char* errorVal = error();
		jstring errorJava = var->NewStringUTF(errorVal);
		return errorJava;
	}

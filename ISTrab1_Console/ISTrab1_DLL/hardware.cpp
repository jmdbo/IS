#include "hardware.h"
#include "SerialClass.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define COM "COM6"

int state=0;
Serial* SP=NULL;


_declspec(dllexport)float energyProduction(){
	float r = -1;

	//if has no connection
	if (SP == NULL){
		SP = new Serial(COM);
	}
	
	char incomingData[10] = "";
	int readResult = 0;

	if (SP->IsConnected()){
		SP->WriteData("enerProd", 10);
		Sleep(500);
		readResult = SP->ReadData(incomingData,10);
	}
	//Cast para evitar warning do compilador
	float res = (float)atof(incomingData);
	return res;
}

_declspec(dllexport)int turnOn(int Fstate){
	int readResult;
	char incomingData[10] = "";
	//if has no connection
	if (SP == NULL){
		SP = new Serial(COM);
	}
	if (state == Fstate){
		return 0;
	}
	else if (Fstate == 1){
		if (SP->IsConnected()){
			SP->WriteData("turnOn", 10);
			Sleep(500);
			readResult = SP->ReadData(incomingData, 10);
		}
		int res = atoi(incomingData);
		if (res == 1){
			state = res;
			return 1;
		}
		return 0;
	}
	else if (Fstate == 0 ){
		if (SP->IsConnected()){
			SP->WriteData("turnOff", 10);
			Sleep(500);
			readResult = SP->ReadData(incomingData, 10);
		}
		int res = atoi(incomingData);
		if (res == 0){
			state = res;
			return 1;
		}
		return 0;
	}
	else return 0;
}

_declspec(dllexport)int isOn(){
	int readResult;
	char incomingData[10] = "";
	//if has no connection
	if (SP == NULL){
		SP = new Serial(COM);
	}
	if (SP->IsConnected()){
		SP->WriteData("isOn", 10);
		Sleep(500);
		readResult = SP->ReadData(incomingData, 10);
	}
	int res = atoi(incomingData);
	if (res == 0 || res == 1){
		state = res;
		return state;
	}
	return -1;
}

_declspec(dllexport)char* error(){
	int readResult;
	char incomingData[10] = "";
	//if has no connection
	if (SP == NULL){
		SP = new Serial(COM);
	}
	if (SP->IsConnected()){
		SP->WriteData("error", 10);
		Sleep(500);
		readResult = SP->ReadData(incomingData, 10);
	}
	char* teste = (char*)malloc(20);
	strcpy(teste, incomingData);
	return teste;
}
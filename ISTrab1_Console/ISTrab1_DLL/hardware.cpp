#include "hardware.h"
#include "SerialClass.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int state=0;
Serial* SP=NULL;

_declspec(dllexport)float energyProduction(){
	float r = -1;

	//if has no connection
	if (SP == NULL){
		//SP = new Serial("\\\\.\\COM6");
		SP = new Serial("COM6");
	}
	
	char incomingData[10] = "";
	int readResult = 0;

	if (SP->IsConnected()){
		SP->WriteData("enerProd", 10);
		Sleep(100);
		readResult = SP->ReadData(incomingData,10);
		
	}
	float res=atof(incomingData);
	//float r = (float)(rand()) / (float)(RAND_MAX);
	return res;
}

_declspec(dllexport)int turnOn(int Fstate){
	//if has no connection
	if (SP == NULL){
		SP = new Serial("\\\\.\\COM3");
	}
	if (state == Fstate){
		return 0;
	}
	else if (((Fstate == 0) || (Fstate == 1))){
		state = Fstate;
		return 1;
	}
	else return 0;
}

_declspec(dllexport)int isOn(){
	//if has no connection
	if (SP == NULL){
		SP = new Serial("\\\\.\\COM3");
	}
	return state;
}

_declspec(dllexport)char* error(){
	//if has no connection
	if (SP == NULL){
		SP = new Serial("\\\\.\\COM3");
	}
	char* teste = (char*)malloc(20);
	strcpy(teste, "Isto eh um teste!\n");
	return teste;
}
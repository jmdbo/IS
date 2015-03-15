#include "hardware.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int state=0;

_declspec(dllexport)float energyProduction(){
	float r = (float)(rand()) / (float)(RAND_MAX);
	return r;
}

_declspec(dllexport)int turnOn(int Fstate){
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
	return state;
}

_declspec(dllexport)char* error(){
	char* teste = malloc(20);
	strcpy(teste, "Isto eh um teste!\n");
	return teste;
}
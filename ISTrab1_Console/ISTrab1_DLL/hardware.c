#include"hardware.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

_declspec(dllexport)float energyProduction(){
	float r = (float)(rand()) / (float)(RAND_MAX);
	return r;
}

_declspec(dllexport)int turnOn(int state){
	return state;
}

_declspec(dllexport)int isOn(){
	return rand();
}

_declspec(dllexport)char* error(){
	char* teste = malloc(20);
	strcpy(teste, "Isto eh um teste!\n");
	return teste;
}
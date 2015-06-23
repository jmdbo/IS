#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

float energyProduction(){
	float r =  (float)(rand()) / (float)(RAND_MAX);
	return r;
}

int turnOn(int state){
	return state;
}

int isOn(){
	return rand();
}

char* error(){
	char* teste = malloc(20);
	strcpy(teste, "Isto eh um teste!\n");
	return teste;
}

int main(){
	char string[20];
	float f = energyProduction();
	printf("Energy Production: %f \n", f);
	
	int i = turnOn(1);
	printf("Turn On State : %d\n", i);

	i = isOn();
	printf("Is On State : %d\n", i);

	char* s = error(&string);
	printf("%s", s);

	getchar();
}

#include "hardware.h"

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
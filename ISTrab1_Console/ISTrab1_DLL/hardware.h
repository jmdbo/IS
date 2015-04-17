#ifndef _HARDWARE
#define _HARDWARE

#define _CRT_SECURE_NO_WARNINGS

_declspec(dllexport)float energyProduction();
_declspec(dllexport)int turnOn(int state);
_declspec(dllexport)int isOn();
_declspec(dllexport)char* error();
_declspec(dllexport)int serialNumber();
#endif
/***********************Windmill_Similation************************/
/*****************System Integrated course form ******************/
/*Faculdade Ciências e Tecnologias from New University of Lisbon*/
/*********Authors: João Miguel Duarte Barata Oliveira **********/
/***************************    &   ***************************/
/************* Pedro Miguel dos Santos Martins ***************/
/************************************************************/

#include<string.h>
#include <avr/wdt.h>

//PINS
const int buttonPin=2;     //Digital Pin 2. 
const int buttonPin1=3;    //Digital Pin 3. 
const int ledPin=4;        //Digital Pin 4.
const int ledPin1=13;      //Digital Pin 13.
const int potPin=A0;       //Analog Pin 0.

//Variables
int buttonState=0;       //state of buttonPin
int buttonState1=0;      //state od buttonPin1
int sensorValue=0;       //Value of potPin
int isOn=0;              //Statio State
char buffer[10];         //rx % tx buffer 
char errorbuffer[10];    //buffer for error.

//clear tx&rxbuffer
void clean_buffer(){
  memset(buffer,0,sizeof(buffer)); 
}

//clear error buiffer
void clean_errorbuffer(){
  memset(errorbuffer,0,sizeof(errorbuffer));
}

//initialization of PinMode
int init_pinMode(){
  pinMode(buttonPin,INPUT);
  pinMode(buttonPin1,INPUT);
  pinMode(ledPin,OUTPUT);
  pinMode(ledPin,OUTPUT);

  return 0;  
}

//initialization of UART/USB COM
int init_UART(){
  Serial.begin(9600);
  while(!Serial){
    ;
  }  

  return 0;
}

//Software reboot
void software_reboot(){
  wdt_enable(WDTO_15MS);
  while(1){
  }
}

void setup(){
  int handlerror=0;

  handlerror=init_pinMode();
  if(handlerror==0)
    handlerror=init_UART();

  if(handlerror==1)
    software_reboot();
}

void loop(){
  //reading input values
  buttonState=digitalRead(buttonPin);
  buttonState1=digitalRead(buttonPin1);
  sensorValue=analogRead(potPin);
  
  //read UART COM
  if(Serial.available()>0){
    Serial.readBytesUntil('/0',buffer,10);  
  }

  //Answer to isOn comand
  if(!strcmp(buffer,"isOn")){
    Serial.print("isOn: ");
    Serial.println(isOn);
    clean_buffer(); 
  }

  //Answer to enerProd command
  if(!strcmp(buffer,"enerProd")){
    Serial.print("enerProd: ");
    Serial.println(sensorValue); 
    clean_buffer();
  }
  //Answer to turnOn command
  if(!strcmp(buffer,"turnOn")){
    if(isOn==0){
      isOn=1;
      digitalWrite(ledPin,HIGH); 
    }
    else{
      isOn=0;
      digitalWrite(ledPin,LOW);  
    }
    Serial.print("isOn: ");
    Serial.println(isOn); 
    clean_buffer();
  }

  //Answer to error comand
  if(!strcmp(buffer,"error")){
    Serial.print("error: ");
    Serial.println(errorbuffer);
    clean_buffer(); 
    digitalWrite(ledPin1,LOW);
  }

  if(buttonState==HIGH){
    strcpy(errorbuffer,"error-1");
    digitalWrite(ledPin1,HIGH);
  }

  if(buttonState1==HIGH){
    clean_errorbuffer();
    digitalWrite(ledPin1,LOW);
  }
}


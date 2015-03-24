/***********************Windmill_Similation************************/
/*****************System Integrated course form ******************/
/*Faculdade Ciências e Tecnologias from New University of Lisbon*/
/*********Authors: João Miguel Duarte Barata Oliveira **********/
/***************************    &   ***************************/
/************* Pedro Miguel dos Santos Martins ***************/
/************************************************************/

#include<string.h>
#include <avr/wdt.h>
#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

//PINS
const int buttonPin=6;     //Digital Pin 2. 
const int buttonPin1=7;    //Digital Pin 3. 
const int ledPin=10;        //Digital Pin 4.
const int ledPin1=13;      //Digital Pin 13.
const int potPin=A0;       //Analog Pin 0.

//Variables
int buttonState=0;       //state of buttonPin
int buttonState1=0;      //state od buttonPin1
int sensorValue=0;       //Value of potPin
int isOn=0;              //Statio State
char buffer[10];         //rx % tx buffer 
char errorbuffer[10];    //buffer for error.
float enerProd=0;

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
  pinMode(ledPin1,OUTPUT);

  return 0;  
}

void updateLCD(){
  lcd.home();
  if(isOn){
    lcd.write("Sys ON  ");
  }else lcd.write("Sys OFF ");
  lcd.write("enerProd: ");
  lcd.write(enerProd);
  lcd.setCursor(0,1);
  if(errorbuffer[0]==0){
    lcd.write("No error detected.");
  }else{
    lcd.write("Error code: ");
    lcd.write(errorbuffer);
  }
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
  lcd.begin(16, 2);
}

void loop(){
  //reading input values
  buttonState=digitalRead(buttonPin);
  buttonState1=digitalRead(buttonPin1);
  sensorValue=analogRead(potPin);
  
  //Reading Analog Value
  if(isOn!=0){
    enerProd = (float)sensorValue/10.0;
  }else
    enerProd=0;
  updateLCD();
  
  //read UART COM
  if(Serial.available()>0){
    Serial.readBytesUntil('/0',buffer,10);  
  }

  //Answer to isOn comand
  if(!strcmp(buffer,"isOn")){
    Serial.println(isOn);
    clean_buffer(); 
  }

  //Answer to enerProd command
  if(!strcmp(buffer,"enerProd")){
    Serial.println(enerProd); 
    clean_buffer();
  }
  //Answer to turnOn command
  if(!strcmp(buffer,"turnOn")){
    isOn=1;
    digitalWrite(ledPin,HIGH); 
    Serial.println(isOn); 
    clean_buffer();
  }
  
  //Answer to turnOff command
    if(!strcmp(buffer,"turnOff")){
    isOn=0;
    digitalWrite(ledPin,LOW); 
    Serial.println(isOn); 
    clean_buffer();
  }

  //Answer to error comand
  if(!strcmp(buffer,"error")){
    Serial.println(errorbuffer);
    clean_buffer();
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


/**************************Windmill_Test***************************/
/*****************System Integrated course form ******************/
/*Faculdade Ciências e Tecnologias from New University of Lisbon*/
/*********Authors: João Miguel Duarte Barata Oliveira **********/
/***************************    &   ***************************/
/************* Pedro Miguel dos Santos Martins ***************/
/************************************************************/

//PINS
const int buttonPin=2;
const int buttonPin1=3;
const int ledPin=4;
const int potPin=A0;

//Variables
int buttonState=0;
int buttonState1=0;
int sensorValue;

void init_pinMode(){
  pinMode(buttonPin,INPUT);
  pinMode(buttonPin1,INPUT);
  pinMode(ledPin,OUTPUT);
}

void setup(){
  init_pinMode(); 
}

void loop(){
  buttonState=digitalRead(buttonPin);
  buttonState1=digitalRead(buttonPin1);
  sensorValue=analogRead(potPin);

  if(buttonState == HIGH){
    digitalWrite(ledPin,HIGH);
    delay(100);
    digitalWrite(ledPin,LOW); 
  }

  if(buttonState1 == HIGH){
    for(int i=0;i<=10;i++){
      digitalWrite(ledPin,HIGH);
      delay(sensorValue);
      digitalWrite(ledPin,LOW); 
    }
  }
}



#include <SoftwareSerial.h>

SoftwareSerial mySerial(2, 3); // RX, TX  

int RELAY = 10;
char data = 0;                //Variable for storing received data

void setup() {
  mySerial.begin(9600);         //Sets the data rate in bits per second (baud) for serial data transmission
  pinMode(RELAY, OUTPUT);       
}

void loop(){
  // Send data only when you receive data:
  if(mySerial.available() > 0){
    data = mySerial.read();      //Read the incoming data and store it into variable data
    if(data == '1')            //Checks whether value of data is equal to 1 
      digitalWrite(RELAY, HIGH);  //If value is 1 then RELAY turns ON
    else if(data == '0')       //Checks whether value of data is equal to 0
      digitalWrite(RELAY, LOW);   //If value is 0 then RELAY turns OFF
  }                            
}

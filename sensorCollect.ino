int pinOne = 1;    // analog sensor in pin 1
int pinTwo = 2;    // analog sensor in pin 2
int pinThree = 3;  // analog sensor in pin 3
int pinFour = 4;   // analog sensor in pin 4

int val = 0;       // variable to store the value coming from the sensor
int val2 = 0;      // variable to store the value coming from the sensor
int val3 = 0;
int val4 = 0;
int count = 0;     // count to keep track of values.  We'll only print every ITH value
int ITH = 1000;       // constant that dictates how often we print a value.


// setup
void setup() {
  Serial.begin(9600);      // open the serial port at 9600 bps:    
}

// loop to continually read sensor
void loop() {
  
  // read value from analog pins
  val = analogRead(pinOne);
  val2 = analogRead(pinTwo);
  val3 = analogRead(pinThree);
  val4 = analogRead(pinFour);
  count++;

  // print values to serial.  We will separate values by the '.'
  //    We will mark the end of a cycle with the '#' symbol
  //    No newlin character as our python code will read the stream one
  //    character at a time
  if(count == ITH){
    Serial.print(val);
    Serial.print(".");
    Serial.print(val2);
    Serial.print(".");
    Serial.print(val3);
    Serial.print(".");
    Serial.print(val4);
    Serial.print("#");
  }
  
  
  if(count >= ITH)
  {
    count = 0; 
  }
  
}

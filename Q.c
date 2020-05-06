void forward();
void back();
void LeftForward();
void RightForward();
void LeftBack();
void RightBack();
void Stop();

void setup() {
  pinMode(9, OUTPUT); //选取9号引脚接电调信号线
  pinMode(9, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(9, OUTPUT);
  /*油门行程校准（i<=1000时，整个循环用时3-4秒）*/

  //该循环运行时会伴有“哔-哔-”油门最高点确认音
  for (int i = 0; i <= 1000; i++) {
    digitalWrite(9, HIGH);
    delayMicroseconds(2000); //高电平持续2000微秒（油门最高点）
    digitalWrite(9, LOW);
    delayMicroseconds(18000);
  }

  //该循环运行时会伴有N声短鸣声（表示锂电池节数）和“哔-”油门最低点确认音
  for (int i = 0; i <= 1000; i++) {
    digitalWrite(9, HIGH);
    delayMicroseconds(1000); //高电平持续1000微秒（油门最低点）
    digitalWrite(9, LOW);
    delayMicroseconds(19000);
  }

  /*现在可以加大高电平持续时间，即加大油门，实现电机的启动  */
  //该循环运行的时间即电机低速转动的时间，进一步证明了i<=1000时，整个循环用时3-4秒
  for (int i = 0; i <= 1000; i++) {
    digitalWrite(9, HIGH);
    delayMicroseconds(1100); //油门1100
    digitalWrite(9, LOW);
    delayMicroseconds(18900);
  }
}

// loop()函数使油门保持在1200
void loop() {
  for (int i = 0; i <= 1000; i++) {
    digitalWrite(9, HIGH);
    delayMicroseconds(1200);
    digitalWrite(9, LOW);
    delayMicroseconds(18800);
  }
}

void forward() { //小车前进
  Serial.println("forward");
  digitalWrite(DIRA, HIGH);
  digitalWrite(DIRB, HIGH);

  analogWrite(PWMA, 500);
  analogWrite(PWMB, 500);
}

void back() { //小车后退
  Serial.println("back");
  digitalWrite(DIRA, LOW);
  digitalWrite(DIRB, LOW);

  analogWrite(PWMA, 500);
  analogWrite(PWMB, 500);
}

void LeftForward() {
  Serial.println("back");
  digitalWrite(DIRA, LOW);
  digitalWrite(DIRB, LOW);

  analogWrite(PWMA, 500);
  analogWrite(PWMB, 500);
}
void RightForward() {
  Serial.println("back");
  digitalWrite(DIRA, LOW);
  digitalWrite(DIRB, LOW);

  analogWrite(PWMA, 500);
  analogWrite(PWMB, 500);
}
void LeftBack() {
  Serial.println("back");
  digitalWrite(DIRA, LOW);
  digitalWrite(DIRB, LOW);

  analogWrite(PWMA, 500);
  analogWrite(PWMB, 500);
}
void RightBack() {
  Serial.println("back");
  digitalWrite(DIRA, LOW);
  digitalWrite(DIRB, LOW);

  analogWrite(PWMA, 500);
  analogWrite(PWMB, 500);
}
void Stop() {
  Serial.println("back");
  digitalWrite(DIRA, LOW);
  digitalWrite(DIRB, LOW);

  analogWrite(PWMA, 500);
  analogWrite(PWMB, 500);
}
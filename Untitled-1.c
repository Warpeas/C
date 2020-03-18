
int main(){
    int i = 10;
    int temp;
    int B = 0;
LOOP: if(i > 0) temp = 1;
	  else temp = 0;
	  if(temp == 0) goto DONE;
	  i--;
	  B += 2;
	  goto LOOP;
DONE:
    return 0;
}
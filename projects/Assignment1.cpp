#include "iostream"
#include "math.h"
using namespace std;

struct city{
    char name[30];
    float latitude;
    float longitude;
};

int main(){
    int radius = 6371;
    int i, j;
    float phi1, theta1, phi2, theta2;
    char name1[31],name2[31], test[2];

    //initialize
    for(int a = 0;a < 30;a++){
    name1[a]='\0';
    name2[a] = '\0';
    }

    city c1;
    city c2;
    cout << "The first city: ";
    cin.get(name1,31);
    cin.getline(test,2);

    //Data test
    if(test[0]!=0){
        cout << "The input is invalid, system exit.";
        return 0;
    }
    if(name1[30] != 0){
        cout << "The input is invalid, system exit.";
        return 0;
    }

    //Construct city name
    for(i = 0;name1[i]==' ';i++){}
    for(j = 30;name1[j]==' '||name1[j]=='\0';j--){}
    for(int p = 0;i <= j;i++){
        if((name1[i]==' '&&name1[i-1]==' ')||(name1[i]==' '&&name1[i+1]==',')){
            continue;
        }
        c1.name[p] = name1[i];
        c1.name[p + 1] = '\0';
        p++;
    }

    cout << "The latitude and longitude of first city: ";
    cin >> c1.latitude;
    cin >> c1.longitude;
    if(c1.latitude==0||c1.longitude==0||!(-90 <= c1.latitude && c1.latitude <=90&&-180 <= c1.longitude&&c1.longitude <= 180)){
        cout << "The input is invalid, system exit.";
        return 0;
    }
    phi1 = 90 - c1.latitude;
    theta1 = c1.longitude;

    if(cin.get()!='\n'){
        cout << "The input is invalid, system exit.";
        return 0;
    }//clear the stream and test the data

    //City 2
    cout << "The second city: ";
    cin.get(name2,30);
    cin.getline(test,2);

    //Data test
    if(test[0]!=0){
        cout << "The input is invalid, system exit.";
        return 0;
    }
    if(name2[30] != 0){
        cout << "The input is invalid, system exit.";
        return 0;
    }

    //Construct city name
    for(i = 0;name2[i]==' ';i++){}
    for(j = 30;name2[j]==' '||name2[j]=='\0';j--){}
    for(int p = 0;i <= j;i++){
        if((name2[i]==' '&&name2[i-1]==' ')||(name2[i]==' '&&name2[i+1]==',')){
            continue;
        }
        c2.name[p] = name2[i];
        c2.name[p + 1] = '\0';
        p++;
    }

    cout << "The latitude and longitude of second city: ";
    cin >> c2.latitude;
    cin >> c2.longitude;
    if(c2.latitude==0||c2.longitude==0||!(-90 <= c2.latitude && c2.latitude <=90&&-180 <= c2.longitude&&c2.longitude <= 180)){
        cout << "The input is invalid, system exit.";
        return 0;
    }
    if(cin.get()!='\n'){
        cout << "The input is invalid, system exit.";
        return 0;
    }//clear the stream and test the data
    phi2 = 90 - c2.latitude;
    theta2 = c2.longitude;

    //Calculate and output
    cout << "The distance between " << c1.name << " and " << c2.name << " is " << radius * acos(sin(phi1*atan(1)*4/180) * sin(phi2*atan(1)*4/180) * cos((theta1 - theta2)*atan(1)*4/180) + cos(phi1*atan(1)*4/180) * cos(phi2*atan(1)*4/180)) << " km" << endl;
}
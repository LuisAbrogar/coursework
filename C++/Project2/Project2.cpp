// Project2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>

using namespace std;

bool checker(char word[500]) {
	if (word[0] == 'q' && word[1] == 'u' && word[2] == 'i' && word[3] == 't') {
		return true;
	}
	else {
		return false;
	}
}


int main() {
	char firstname[50], lastname[50], ssn[50], userID[50], pass[50];
	int x, y, ssncounter, passcounter;
	bool check;

	


	while (1) {
		cout << "Enter items separated by a space, in the following order:" << endl;
		cout << "<first name> <last name> <SSN> <student ID> <password>" << endl;
		cout << "Only one line at a time please, this program takes only one line per time to analyze" << endl;
		cin >> firstname;
	check = checker(firstname);

	if (check == 0) {
		cin >> lastname >> ssn >> userID >> pass;
		cout << firstname << " " << lastname << " ";

		ssncounter = string(ssn).length();
		passcounter = string(pass).length();

		for (x = 0; x < ssncounter; x++) {
			cout << "x";
		}

		cout << " " << userID << " ";

		for (y = 0; y < passcounter; y++) {
			cout << "x";
		}

	}

	else if (check == 1) {
		return 0;
	}
}
	return 0;

	
}
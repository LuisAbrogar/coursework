// Project1.cpp : Defines the entry point for the console application.
//






#include <stdafx.h>
#include <iostream>
#include <iomanip>
#include <cmath>

using namespace std;

int main()
{
	double Resistor, Capacitor, LInductor, Diode;									//Case types
	double R, r, Vin;															//Input for Resistor Case
	double C, time;																//Input for Capacitor Case
	double Vd;																	//Input for Diode Case
	double Vbe, Vcc, alpha;														//Input for Transistor Case
	double t0, t1, t2, t3, x1, x2, x3;										//Input for Neuron Model Case
	int I, Vr, Pr, Ve, Pe;													//Output Data for Resistor Case
	int Ib, Ie, Ic, Vout, PR;												//Output Data for Transistor Case
	int g, z;																//Output Data for Neuron Model
	char E;

	while (1) {
		cout << "What type of circuit element is it?" << endl;
		cout << "R = Resistor, C = Capacitor, T = Transistor, D = Diode, N = Neuron Model" << endl;

		cin >> E;
		if (E == 82 || E == 114 ) {													//Resistor case
			cin >> R >> r >> Vin;
			I = r + R;
			I = Vin / I;

			Pr = (I ^ 2) * r;
			Vr = Pr / I;

			Pe = (I ^ 2) * R;
			Ve = Pe / I;

			cout << "I = " << I << "A, Vr = " << Vr << "V, Pr = " << Pr << "W, Ve = " << Ve << "V, Pe =" << Pe << "W" << endl;
			break;
		}
		else if (E == 67 || E == 99) {												//Capacitor Case
			cin >> C >> r >> Vin >> time;
			
			Ve = -time / (r*C);
			Ve = 1 - exp(Ve);
			Ve *= Vin;

			Pe = Ve * I;

			Pr = (I ^ 2) * r;
			Vr = Pr / I;

			I = (Vin - Ve) / r;

			cout << "I = " << I << "A, Vr = " << Vr << "V, Pr = " << Pr << "W, Ve = " << Ve << "V, Pe =" << Pe << "W" << endl;
			break;
		}
		else if (E == 84 || E == 116) {												//Transistor Case
			
			cin >> Vbe >> Vcc >> r >> R >> alpha;
			Vin = Vcc;
			
			if (Vin < Vbe) {
				Ib = 0;
				Ic = 0;
				Vout = Vcc;
			}
			else if (Vin > 10) {
				cout << "Out of operating limit" << endl;
				return 0;
			}
			else {
				Ib = (Vin - 0.7) / r;
				Ie = Ib / (1 - alpha);
				Ic = alpha * Ie;

				Vout = Vcc - Ic * R;
				PR = (Ic ^ 2) * R;
				Pr = (Ib ^ 2) * r;
			}
		
		}
		else if (E == 68 || E == 100) {												//Diode Case
			cin >> Vd >> r >> Vin;
			
			if (Vin < 0.7) {
				I = 0;
			}
			else if (Vin > 10.0) {
				cout << "Vin is out of operating range" << endl;
				return 0;
			}
			else {
				I = (Vin - 0.7) / r;
			}
			
			cout << "I = " << I << "A" << endl;
			break;
		}
		else if (E == 78 || E == 110) {
			cin >> t0 >> t1 >> t2 >> t3 >> x1 >> x2 >> x3;
			
			z = t0 + (t1 * x1) + (t2 * x2) + (t3 * x3);
			
			g = 1 + exp(-z);
			g = 1 / g;

			cout << "g = " << g << endl;
			break;
		}
		else {
			cout << "Invalid Input" << endl;
		}
	}
	cout << "It Works" << endl;
    return 0;
}


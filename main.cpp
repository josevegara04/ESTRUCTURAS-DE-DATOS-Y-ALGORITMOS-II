#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

class Agencia
{
    private:
        string nombre;
        int costo_minimo;
    
    public:
        Agencia(string nombre, int costo_minimo)
        {
            this->nombre = nombre;
            this->costo_minimo = costo_minimo;
        }

        string getNombre()
        {
            return this->nombre;
        }
        
        int getCostoMinimo()
        {
            return this->costo_minimo;
        }

        void setNombre(string nombre)
        {
            this->nombre = nombre;
        }

        void setCostoMinimo(int costo_minimo)
        {
            this->costo_minimo = costo_minimo;
        }

        string toString()
        {
            return this->nombre  + " " + to_string(this->costo_minimo);
        }
};

int main()
{
   
    int T;
    int N;
    int M; 
    int L; 
    string nombre_agencia; 
    int costo_A; 
    int costo_B; 
    int costo_minimo = 0;
    int precio = 0;

    //Se lee el número de casos a realizar
    cin >> T;

    for(int i = 1; i <= T; i++)
    {
        vector<Agencia> agencias;
        bool cen;

        cin >> N;
        int N_copia;
        cin >> M;
        cin >> L;
        string blanco;
        getline(cin, blanco);

        for(int j = 0; j < L; j++)
        {
            N_copia = N;

            string linea;
            getline(cin, linea);
            vector<string> partes;
            istringstream ss(linea);
            string subcadena;

            while(getline(ss, subcadena, ':'))
            {
                partes.push_back(subcadena);
            }

            if(partes.size() == 2)
            {
                nombre_agencia = partes[0];
                vector<string> costos;
                istringstream ss(partes[1]);
                while(getline(ss, subcadena, ','))
                {
                    costos.push_back(subcadena);
                }
                if(costos.size() == 2)
                {
                    costo_A = stoi(costos[0]);
                    costo_B = stoi(costos[1]);

                    while(N_copia > M)
                    {
                        int half = N_copia/2;

                        if(N_copia % 2 == 0)
                        {
                            precio = costo_A * (N_copia/2);
                        }
                        else
                        {
                            precio = costo_A * (N_copia/2 + 1);
                        }

                        if(N_copia/2 >= M)
                        {
                            if(costo_A > costo_B || precio > costo_B || costo_A == costo_B)
                            {
                                N_copia /= 2;
                                costo_minimo += costo_B;
                            }
                            else                     
                            {
                                while(N_copia > half)
                                {
                                    costo_minimo += costo_A;
                                    N_copia -= 1;
                                }
                            }
                        }
                        else
                        {
                            while(N_copia != M)
                            {
                                N_copia -= 1;
                                costo_minimo += costo_A;
                            }
                        }
                    }

                    Agencia agencia(nombre_agencia, costo_minimo);
                    agencias.push_back(agencia);
                }
            }

            costo_minimo = 0;
        }

        for(int j = 0; j < agencias.size(); j++)
        {
            cen = false;
            for(int k = 0; k < agencias.size() - j - 1; k++)
            {
                if(agencias[k].getCostoMinimo() > agencias[k + 1].getCostoMinimo())
                {
                    Agencia temp = agencias[k];
                    agencias[k] = agencias[k + 1];
                    agencias[k + 1] = temp;
                    cen = true;
                }
                else if(agencias[k].getCostoMinimo() == agencias[k + 1].getCostoMinimo()
                        &&  agencias[k].getNombre()[0]> agencias[k + 1].getNombre()[0])
                {
                    Agencia temp = agencias[k];
                    agencias[k] = agencias[k + 1];
                    agencias[k + 1] = temp;
                    cen = true;
                }
            }
            if(!cen)
            {
                break;
            }
        }

        cout << "Case " << i << endl;
        for(int j = 0; j < agencias.size(); j++)
        {
            cout << agencias[j].toString() << endl;
        }
    }
    return 0;
}
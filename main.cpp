#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

//Se crea la clase Agencia con sus respectivos atributos y métodos
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

//Función main
int main()
{
   
   //Se declaran las variables a utilizar
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

    //Se crea un ciclo para realizar los casos
    for(int i = 1; i <= T; i++)
    {

        //Se crea la lista que almacenará las agencias
        vector<Agencia> agencias;
        bool cen;

        //Se leen los datos de cada caso
        cin >> N;
        int N_copia;
        cin >> M;
        cin >> L;
        string blanco;
        getline(cin, blanco);


        //Se crea un ciclo administrar los datos de cada agencia
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

                    //Se calcula el costo mínimo de cada agencia
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

                    //Se crea un objeto de la clase Agencia y se agrega a la lista
                    Agencia agencia(nombre_agencia, costo_minimo);
                    agencias.push_back(agencia);
                }
            }
            costo_minimo = 0;
        }

        //Se ordena la lista de agencias con el método de ordenamiento Bubble Sort
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

        //Se imprime el resultado de cada caso
        cout << "Case " << i << endl;
        for(int j = 0; j < agencias.size(); j++)
        {
            cout << agencias[j].toString() << endl;
        }
    }
    return 0;
}
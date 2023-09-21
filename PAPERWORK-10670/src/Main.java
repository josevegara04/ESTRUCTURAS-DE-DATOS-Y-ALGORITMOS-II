import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        int T; 
        int N; 
        int M; 
        int L; 
        String nombre_agencia; 
        int costo_A; 
        int costo_B; 
        int costo_minimo = 0;
        int precio = 0;
    
        
        Scanner scan = new Scanner(System.in);
        
        //Se lee el número de casos a realizar.
        T = scan.nextInt();
        
        //Empieza el loop que recorre cada caso
        for(int i = 1; i <= T; i++)
        {
            List<Agencia> agencias = new ArrayList<>(); //Se crea la lista de tipo Agencia
            boolean cen; //variable utilizada para el método de ordenamiento de la lista

            //Se escanea la primera línea del input 
            N = scan.nextInt();
            int N_copia;
            M = scan.nextInt();
            L = scan.nextInt();
            scan.nextLine();
            
            //Empieza el loop que recorre cada agencia del caso
            for(int j = 0; j < L; j++)
            {  
                N_copia = N;
                
                //A continuacion, se guardan en las variables locales, la información obtenida por consola
                String linea = scan.nextLine();
                String[] partes = linea.split(":");
                if(partes.length == 2)
                {
                    nombre_agencia = partes[0].trim();
                    String[] costos = partes[1].split(",");
                    if(costos.length == 2)
                    {
                        costo_A = Integer.parseInt(costos[0].trim());
                        costo_B = Integer.parseInt(costos[1].trim());
                        
                        //Se hace el loop mientras que N_copia sea mayor a M
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
                                //Aqui se toma una decision local: sale mas optimo por el costo_A o por el costo_B
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
                        //Se agrega la información a un objeto tipo Agencia y se guarda en la lista agencias
                        Agencia agencia = new Agencia(nombre_agencia, costo_minimo);
                        agencias.add(agencia);
                    }
                }
                costo_minimo = 0;
            }
            
            //Luego de tener la información de todas las agencias del caso, se procede a organizarlas de menera ascendente por medio del bubble sort
            for(int j = 0; j < agencias.size(); j++)
            {
                cen = false;
                for(int k = 0; k < agencias.size() - j - 1; k++)
                {
                    if(agencias.get(k).getCostoMinimo() > agencias.get(k + 1).getCostoMinimo())
                    {
                        Agencia temp = agencias.get(k);
                        agencias.set(k, agencias.get(k + 1));
                        agencias.set(k + 1, temp);
                        cen = true;
                    }
                    else if(agencias.get(k).getCostoMinimo() == agencias.get(k + 1).getCostoMinimo()
                            &&  agencias.get(k).getNombre().charAt(0) > agencias.get(k + 1).getNombre().charAt(0))
                    {
                        Agencia temp = agencias.get(k);
                        agencias.set(k, agencias.get(k + 1));
                        agencias.set(k + 1, temp);
                        cen = true;
                    }
                }
                if(!cen)
                {
                    break;
                }
            }
            
            //Una vez terminado el caso, se imprime las agencias con sus respectivos costos mínimos
            System.out.println("Case " + i);
            for(int j = 0; j < agencias.size(); j++)
            {
                System.out.println(agencias.get(j).toString());
                System.out.flush();
            }
        }
        scan.close();
    }
}

//Se crea la clase Agencia con sus respectivos getters and setters
class Agencia 
{
    private String nombre;
    private int costo_minimo;
    
    public Agencia(String nombre, int costo_minimo)
    {
        this.nombre = nombre;
        this.costo_minimo = costo_minimo;
    }
     
    //getters and setters
    public String getNombre()
    {
        return this.nombre;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public void setCostoMinimo(int costoMinimo)
    {
        this.costo_minimo = costoMinimo;
    }
    public int getCostoMinimo()
    {
        return this.costo_minimo;
    }
    
    public String toString()
    {
        return this.nombre + " " + this.costo_minimo;
    }
}
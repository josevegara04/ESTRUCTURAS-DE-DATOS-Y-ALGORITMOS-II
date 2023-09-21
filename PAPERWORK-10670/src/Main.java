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
        int precio_1; 
        int precio_2; 
    
        
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
                        
                        //Se hace el loop mientras que N sea mayor a M
                        while(N_copia > M)
                        {
                            precio_1 = 0;
                            precio_2 = 0;
                            int N1 = N_copia;
                            int N2 = N_copia;
                            
                            /*Se hace la reducción por mitad si el resultado es mayor a M 
                            para probar con con el costoB*/
                            if(N_copia/2 >= M)
                            {
                                N1 = N1/2; 
                                precio_1 += costo_B; 
                                
                                /*También se prueba con el costo A para comparar precios*/
                                while(N2 > N1)
                                {
                                    N2 -= 1;
                                    precio_2 += costo_A; 
                                } 
                            }
                            else
                            {
                                while(N2 > M)
                                {
                                    N2 -= 1;
                                    precio_2 += costo_A;
                                    precio_1 += precio_2;
                                }
                            }
                            
                            //Se compara con cual costo fue más barato y se actualiza el precio
                            if(precio_1 > precio_2)
                            {
                                costo_minimo += precio_2;
                            }
                            else
                            {
                                costo_minimo += precio_1; 
                            }
                            N_copia = N2; 
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
                //Cambio
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
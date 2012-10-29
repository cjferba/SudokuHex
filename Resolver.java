/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
/**
 *
 * @author Carlos Basso
* */
import java.util.*;/*
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;*/
public class Resolver {
    
	int tablero[][]=new int[16][16];
	boolean bMarcaH[][]=new boolean[16][16];
	boolean bMarcaM[][]=new boolean[16][16];
	boolean bMarcaC[][]=new boolean[16][16];
        
	public void SudokuHex (String template){
            char[] a= template.toCharArray();
            int ii=0;
            int s=0;
            for(int i=0;i<a.length;i=i+1){
                tablero[s][ii]=Pos(a[i]);
                ii=ii+1;
                if(ii==16){
                    s=s+1;
                    ii=0;
                }
            }	       
	}
	public Resolver(String cadena){
                cadena=cadena.toLowerCase();
                System.out.print(cadena);
                SudokuHex(cadena);
		marcar();
	}	
	public void marcar(){
		for (int n=0;n<tablero.length;n++){
			for (int m=0;m<tablero[n].length;m++){
				if (tablero[n][m]!=-1){
					int num=tablero[n][m];
					bMarcaH[n][num]=true;
					bMarcaM[m][num]=true;
					bMarcaC[n/4+(m/4)*4][num]=true;
				}
			}
		}
	}
	public void visualizarTablero(){
		for (int n=0;n<tablero.length;n++){
			if (n % 4 == 0){
				for (int m=0;m<tablero[n].length;m++){
					System.out.print("-");
				}
				System.out.println();
			}
			
			for (int m=0;m<tablero[n].length;m++){
				if (tablero[n][m]!=-1)
					if (tablero[n][m]<10)
						System.out.print(tablero[n][m]);
					else
						System.out.print((char)(tablero[n][m]-10+((int)'A')));
				else
					System.out.print(' ');
				if (m % 4 == 3){
					System.out.print('|');
				}
			}
                        System.out.print("\n");
			//System.out.println("  Lon:" + tablero[n].length);
		}
	}
        private int Pos(char x){
             switch(x) {
                case '0': 
                    return(0);
                case '1': 
                    return(1);
                case '2': 
                    return(2);
                case '3': 
                    return(3);
                case '4': 
                    return(4);
                case '5': 
                    return(5);
                case '6': 
                    return(6);
                case '7': 
                    return(7);
                case '8': 
                    return(8);
                case '9': 
                    return(9);
                case 'a': 
                    return(10);
                case 'b': 
                    return(11);
                case 'c': 
                    return(12);
                case 'd': 
                    return(13);
                case 'e': 
                    return(14);
                case 'f': 
                    return(15);
                default: 
                    return (-1);
             }
        }
        /*
         * intentar buscar solucion sin sitio fijo
         * public void buscarSolucion(int x,int y)
         */
	public void buscarSolucion(int cas){
                //busqueda
		int n=cas/16;
		int m=cas%16;
		if (tablero[n][m]==-1){
                        //for sobre los posibles
			for (int z=0;z<16;z++){
				if (bMarcaH[n][z]==false &&bMarcaM[m][z]==false &&bMarcaC[n/4+(m/4)*4][z]==false){
					bMarcaH[n][z]=true;
					bMarcaM[m][z]=true;
					bMarcaC[n/4+(m/4)*4][z]=true;
					tablero[n][m]=z;
                                        //si solucion completa acabo
					if (cas==16*16-1){
						//System.out.println("\nSolución encontrada!!!!");
						visualizarTablero();
                                                //System.exit(0);
					}else{
						buscarSolucion(cas+1);
					}

					tablero[n][m]=-1;
					bMarcaH[n][z]=false;
					bMarcaM[m][z]=false;
					bMarcaC[n/4+(m/4)*4][z]=false;
				}
			}
		}else{
                        //if(==-1) entonces no hay mas casillas
			if (cas==16*16-1){
				//System.out.println("Solución encontradapor 2!!!!");
				visualizarTablero();
                                //System.exit(0);
			}else{
				buscarSolucion(cas+1);
			}			
		}
	}
        public class Posicion {
            public int x;
            public int y;
            public void Posicion(int x1,int x2){
        this.x=x1;
        this.y=x2;
        }
            public void Posicion(){
        this.x=0;
        this.y=0;
        }
        }
        //para ordenar pos(falta hacer la funcion//

	

	public static void main(String a[]){
            
                String cadena="..0..A7..4.2.F9.62.91.4.D.F3........D....8.C..4.84.....9..0.1DB2"+
                              ".C.6.F5.02..78D.DF27318.C.54.......8.7ADF..B.2....5...CE37.8F16."+
                              "4D317...8.2AC..E...2....7EC...F....08D.F56...32BF.8..C..B3...7.."+
                              "27.39...A..5..CF..9...D..0...A..0.....B.9..FD48..B.FA8074.E6.935";
               String cadena2="a0....3.fd...c.....d459.b.c..2f.be.cd.280...1.7.5..2.e0..834..b."+
                              "....2.83...f.061......1.e3..4f..78..6df4.....e..4.d..c..1.26.b87"+
                              "ca7.f3.d..0..5.4..f.....649a..0b..04..b7.5......dbe.a...27.3...."+
                              ".c..e4d..f6.b..a.d.b...27e.10.cf.7a..1.b.0dc8.....5...7f.2....ed";
               String cadena3="1A...4C.E....8..2F.9...568.B...D38....E7.4F..2C...C6...8..7.AE.3"+
                              "5...C84BF2.3..766B3....2.9.C41E...4..10.....F5D.8...7.9.1...B.3C"+
                              "D.1.4..C.FE78........7.0...2D.14F......E..A..C.00C2.9.DF4136E.A."+
                              "C....B7A.......E9.820C61.E.A7..5AD0.3E592.8.C4..BE5.2D...7.936..";
                //String 
                long time_start, time_end;
                time_start = System.currentTimeMillis();
                Resolver r=new Resolver(cadena);
                r.visualizarTablero();
		r.buscarSolucion(0);
		//System.out.println("FIN");
                time_end = System.currentTimeMillis();
                System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
                
	}
	
}

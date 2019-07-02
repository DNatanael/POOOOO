package calculaaaa;


import java.util.Scanner;

public class Calculaaaa {
static double resultado; 
    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    
    System.out.println ("Digite o primeiro número: ");
    double num1 = scan.nextDouble();
    
    System.out.println("Escolha uma das opções: \n 01: Somar \n 02: Subtrair \n 03: Multiplicar  \n 04: Dividir: \n Opção escolhida: ");
    int num2= scan.nextInt();
    
    System.out.println ("Digite o segundo número: ");
    double num3=scan.nextDouble();    
  
    if(num2<5 && num2>0)
    {
    switch (num2)
    {
        case 1:
            resultado = num1+num3;
            break;
        case 2:
            resultado = num1-num3;
            break;
        case 3:
            resultado = num1*num3;
            break;
        case 4:
            resultado = num1/num3;
            break;
            
    
    }
    System.out.println("Resultado igual a: "+ resultado);
}
    else
    {
        System.out.println("Fail: operação invalida!!! tenta ai de novo man vá lá");
    }
    }
}
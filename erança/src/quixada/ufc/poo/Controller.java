package quixada.ufc.poo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Funcionario {
    private String nome;
    protected double salario;
    protected char titulo;

    public Funcionario(String nome, double salario) { //cria a classe funcionaria para receber nome e salario
        this.nome = nome;
        this.salario = salario;
        this.titulo = 'F';
    }

    public String getNome(){ //Nome esta privado o get ira chamalo 
        return nome;
    }

    public double getBonificacao() { //chamar a bonificação
        return salario * 0.10;
    }

    double getSalario(){ //chamar salario
        return salario + getBonificacao();
    }

    @Override
    public String toString() {
        return titulo + ":" + nome + ":" + salario;
    }
}

class Gerente extends Funcionario { // cria a classe gerente recebendo parametros de funcionario(egrente e funionario)
    String pass;
    public Gerente(String nome, double salario, String pass) {
        super(nome, salario);
        this.pass = pass;
        this.titulo = 'G';
    }

    String contratar(){
        return "contratei um funcionario";
    }

    public double getBonificacao() {
        return this.salario * 0.15;
    }

    @Override
    public String toString() {
        return super.toString() + ":" + this.pass;
    }

}


class Diretor extends Gerente{
    double Gratificacao;
    public Diretor(String nome, double salario, double Gratificacacao) {
        super(nome, salario, nome);
        this.Gratificacao = Gratificacacao;
        this.titulo = 'D';
    }

    public double getBonificacao() {
        return super.getBonificacao() * 2;
    }

    @Override
    double getSalario() {
        return super.getSalario() + Gratificacao;
    }

    @Override
    public String toString() {
        return super.toString() + ":" + Gratificacao;
    }
}

class Empresa{ //cria um array com funcionarios
    ArrayList<Funcionario> funcionarios; 
    public Empresa(){
        funcionarios = new ArrayList<>();
    }
    public void addFuncionario(Funcionario func){
        funcionarios.add(func);
    }

    public double calcularFolha(){
        double folha = 0.0; //fola inicia em zero
        folha = funcionarios.stream().map((func) -> func.getSalario()).reduce(folha, (accumulator, _item) -> accumulator + _item);
        return folha;
    }

    @Override
    public String toString() {
        String saida = "";
        saida = funcionarios.stream().map((func) -> func + "\n").reduce(saida, String::concat);
        return saida;
    }
}


public class Controller {
    public static void main(String[] args) {
        Empresa emp = new Empresa();
        Scanner scanner = new Scanner(System.in);
        System.out.println("addfunc(nome+salario), adderente(nome+salario+sena), adddiretor(nome+salario+gratificação), vai(roda prorama), fp(fola paamento), fim(encerra cod)") ;

        
        while (true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            switch (ui[0]) {
                case "fim":
                    break;
                case "addfunc":
                    //addF _nome + salario
                    emp.addFuncionario(new Funcionario(ui[1], Double.parseDouble(ui[2])));
                    break;
                case "adderente":
                    //addG _nome +salario + senha
                    emp.addFuncionario(new Gerente(ui[1], Double.parseDouble(ui[2]), ui[3]));
                    break;
                case "adddiretor":
                    //addG _nome +salario+ Gratificacao
                    emp.addFuncionario(new Diretor(ui[1], Double.parseDouble(ui[2]), Double.parseDouble(ui[3])));
                    break;
                case "vai":
                    System.out.println(emp);
                    break;
                case "folapag":
                    System.out.println(emp.calcularFolha());
                    break;
            }
        }
    }
}
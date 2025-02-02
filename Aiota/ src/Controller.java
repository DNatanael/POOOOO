import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Transacao{
    int id;
    float valor;
    String clientId;

    public Transacao(int id, float valor, String clientId) {
        this.id = id;
        this.valor = valor;
        this.clientId = clientId;
       
    }

    public String toString(){
        return "" + id + ":" + clientId + ":" + valor;
    }
}

class Cliente{
    String id;
    String fullname;
    float  saldo;
    public Cliente(String id, String fullname){
        this.id = id;
        this.fullname = fullname;
        this.saldo = 0;
    }

    @Override
    public String toString() {
        return this.id + ":" + this.fullname + ":" + this.saldo;
    }
}

class Sistema {
    float saldo;
    ArrayList<Cliente> clientes;
    ArrayList<Transacao> transacoes;
    int nextTrId;

    public Sistema(float saldo) {
        this.saldo = saldo;
        this.clientes = new ArrayList<Cliente>();
        this.transacoes = new ArrayList<Transacao>();
        this.nextTrId = 0;
    }

    void cadastrar(Cliente cliente) {
        try {
            this.procurarcli(cliente.id);
            throw new RuntimeException("Erro:Cliente ja exite");
        }catch(RuntimeException re){
            clientes.add(cliente);
        }
    }

    Cliente procurarcli(String id) {
        for (Cliente cli : clientes) {
            if (cli.id.equals(id))
                return cli;
        }
        throw new RuntimeException("fail: cliente nao existe");
    }

    void addTransacao(float value, String clienteId) {
        this.transacoes.add(new Transacao(nextTrId, value, clienteId));
        nextTrId += 1;
    }

    void emprestar(String id, float saldo) {
        Cliente cli = procurarcli(id);
        addTransacao(-saldo, id);
        this.saldo -= saldo;
        cli.saldo += saldo;
        
    }

    void receber(String id, float saldo) {
        Cliente cli = procurarcli(id);
        if(cli.saldo < saldo){
            System.out.println("fail: pagamento maior que divida");
            return;
        }
        addTransacao(saldo, id);
        cli.saldo -= saldo;
        this.saldo += saldo;
    }

    ArrayList<Transacao> getHistorico() {
        return transacoes;
    }


    
    public String toString() {
        String saida = "";
        for(Cliente cliente : clientes)
            saida += cliente + "\n";
        saida += "saldo:" + this.saldo;
        return saida;
    }
}

public class Controller {
    public static void main(String[] args) {
        Sistema sistema = new Sistema(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Diite: init+(valor..que..o..ajiota..tem)..cadastrar+nome+Id(cliente)..emprestar+nome+valor(emprestar)..vai(mostrar)..istorico(istorico)..fim(finalizar).");
        
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            try {
                if (ui[0].equals("fim")) {
                    break;
                } else if (ui[0].equals("init")) {
                    sistema = new Sistema(Float.parseFloat(ui[1]));
                } else if (ui[0].equals("vai")) {
                    System.out.println(sistema);
                }else if (ui[0].equals("receber")) {
                    sistema.receber(ui[1], Float.parseFloat(ui[2]));
                } else if (ui[0].equals("emprestar")) {
                    sistema.emprestar(ui[1], Float.parseFloat(ui[2]));
                } else if (ui[0].equals("istorico")) {
                    for (Transacao tr : sistema.getHistorico())
                        System.out.println(tr);
                } else if (ui[0].equals("cadastrar")) {
                    String id = ui[1];
                  
                } else {
                    System.out.println("fail: comando invalido");
                }
            }catch(RuntimeException re){
                System.out.println(re.getMessage());
            }
        }
    }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaAgendamento {

    private final String nomeArquivo = "agendamentos.txt";
    private final ArrayList<Agendamento> lista = new ArrayList<>();

    public SistemaAgendamento() {
        carregarArquivo();
    }

    public boolean horarioValido(String horario) {
        if (horario.length() != 5) {
            return false;
        }

        if (horario.charAt(2) != ':') {
            return false;
        }

        try {
            int hora = Integer.parseInt(horario.substring(0, 2));
            int minuto = Integer.parseInt(horario.substring(3, 5));

            if (hora < 0 || hora > 23) {
                return false;
            }

            if (minuto != 0 && minuto != 30) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public void salvarArquivo() {
        try {
            FileWriter fw = new FileWriter(nomeArquivo);
            PrintWriter pw = new PrintWriter(fw);

            for (Agendamento a : lista) {
                pw.println(
                        a.getCliente().getNome() + ";" +
                                a.getCliente().getTelefone() + ";" +
                                a.getHorario()
                );
            }

            pw.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo.");
        }
    }

    public void carregarArquivo() {
        try {
            lista.clear();

            File arquivo = new File(nomeArquivo);
            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] partes = linha.split(";");

                if (partes.length != 3) {
                    continue;
                }

                String nome = partes[0];
                String telefone = partes[1];
                String horario = partes[2];

                Cliente cliente = new Cliente(nome, telefone);
                Agendamento agendamento = new Agendamento(cliente, horario);

                lista.add(agendamento);
            }

            leitor.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de agendamentos ainda nao existe.");
        }
    }

    public void agendar(Scanner sc) {
        System.out.println("Nome do cliente:");
        String nome = sc.nextLine().trim();

        System.out.println("Telefone do cliente:");
        String telefone = sc.nextLine().trim();

        System.out.println("Horario do cliente (HH:MM):");
        String horario = sc.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("Nome invalido.");
            return;
        }

        if (telefone.isEmpty()) {
            System.out.println("Telefone invalido.");
            return;
        }

        if (!horarioValido(horario)) {
            System.out.println("Horario invalido. Use HH:MM e apenas minutos 00 ou 30.");
            return;
        }

        boolean horarioOcupado = false;

        for (Agendamento a : lista) {
            if (a.getHorario().equals(horario)) {
                horarioOcupado = true;
                break;
            }
        }

        if (horarioOcupado) {
            System.out.println("Esse horario ja esta ocupado.");
            return;
        }

        Cliente cliente = new Cliente(nome, telefone);
        Agendamento agendamento = new Agendamento(cliente, horario);
        lista.add(agendamento);
        salvarArquivo();

        System.out.println("Agendamento realizado com sucesso.");
    }

    public void listar() {
        if (lista.isEmpty()) {
            System.out.println("Nenhum agendamento cadastrado.");
            return;
        }

        System.out.println("\n===== AGENDAMENTOS =====");
        for (Agendamento a : lista) {
            System.out.println("Cliente : " + a.getCliente().getNome());
            System.out.println("Telefone: " + a.getCliente().getTelefone());
            System.out.println("Horario : " + a.getHorario());
            System.out.println("------------------------");
        }
    }

    public void cancelar(Scanner sc) {
        System.out.println("Digite o horario que deseja cancelar:");
        String horarioCancel = sc.nextLine().trim();

        boolean encontrado = false;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getHorario().equals(horarioCancel)) {
                lista.remove(i);
                salvarArquivo();
                encontrado = true;
                System.out.println("Agendamento cancelado com sucesso.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Horario nao encontrado.");
        }
    }

    public void buscarPorNome(Scanner sc) {
        System.out.println("Digite o nome do cliente:");
        String nomeBusca = sc.nextLine().trim();

        boolean encontrado = false;

        for (Agendamento a : lista) {
            if (a.getCliente().getNome().equalsIgnoreCase(nomeBusca)) {
                System.out.println("\nCliente : " + a.getCliente().getNome());
                System.out.println("Telefone: " + a.getCliente().getTelefone());
                System.out.println("Horario : " + a.getHorario());
                System.out.println("------------------------");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Cliente nao encontrado.");
        }
    }

    public void editarAgendamento(Scanner sc) {
        System.out.println("Digite o horario atual do agendamento:");
        String horarioAtual = sc.nextLine().trim();

        int indiceEncontrado = -1;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getHorario().equals(horarioAtual)) {
                indiceEncontrado = i;
                break;
            }
        }

        if (indiceEncontrado == -1) {
            System.out.println("Agendamento nao encontrado.");
            return;
        }

        System.out.println("Digite o novo horario (HH:MM):");
        String novoHorario = sc.nextLine().trim();

        if (!horarioValido(novoHorario)) {
            System.out.println("Horario invalido. Use HH:MM e apenas minutos 00 ou 30.");
            return;
        }

        for (Agendamento a : lista) {
            if (a.getHorario().equals(novoHorario)) {
                System.out.println("Esse novo horario ja esta ocupado.");
                return;
            }
        }

        lista.get(indiceEncontrado).setHorario(novoHorario);
        salvarArquivo();
        System.out.println("Agendamento editado com sucesso.");
    }
}
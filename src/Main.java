import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaAgendamento sistema = new SistemaAgendamento();

        int opcao = 0;

        while (opcao != 6) {
            System.out.println("\n========= MENU =========");
            System.out.println("1 - Agendar horario");
            System.out.println("2 - Listar agendamentos");
            System.out.println("3 - Cancelar agendamento");
            System.out.println("4 - Buscar cliente por nome");
            System.out.println("5 - Editar agendamento");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opcao: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Digite apenas numeros.");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    sistema.agendar(sc);
                    break;

                case 2:
                    sistema.listar();
                    break;

                case 3:
                    sistema.cancelar(sc);
                    break;

                case 4:
                    sistema.buscarPorNome(sc);
                    break;

                case 5:
                    sistema.editarAgendamento(sc);
                    break;

                case 6:
                    System.out.println("Encerrando programa...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }

        sc.close();
    }
}
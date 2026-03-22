import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main{
    static ObjectMapper objectMapper = new ObjectMapper();
    static final Scanner sc = new Scanner(System.in);
    static List<Task> tasks = new ArrayList<>();
    static File arquivo = new File("C://CLI_TASK_APP/task.json");
    public enum statusFilter{
        Não_Terminada,Em_Andamento,Terminada,Todas
    }

    public static void addTask() throws IOException {
        String descricao = "";
        System.out.print("Insira uma nova task: ");
        try {
            descricao = sc.nextLine();
        }catch(NullPointerException e){
            System.out.println("Código do erro: " + e.getMessage());
        }

        Task task = new Task();
        task.setDescricao(descricao);
        tasks.add(task);

        try (FileOutputStream fos = new FileOutputStream(arquivo)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fos, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Tarefa Criada com sucesso!");

    }
    public static void removeTask() throws IOException {
        int rmId = 0;

        for (Task task : tasks) {
           task.getInfo();
        }
        System.out.println("Digite o ID da tarefa que deseja remover: ");

        try {
            rmId = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        tasks.remove(rmId);
        for (Task task : tasks) {
            if (task.getId() > rmId) {
                task.setId(task.getId() - 1);
            }
        }
        try (FileOutputStream fos = new FileOutputStream(arquivo)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fos, tasks);
        }
        System.out.println("Tarefa removida com sucesso!");
    }
    public static void updateTask() throws IOException {
        int updId = 0;
        int res = 0;
        int auxResEstado = 0;
        for (Task task : tasks) {
            task.getInfo();
        }
        System.out.println("Digite o ID da tarefa que deseja atualizar: ");

        try {
            updId = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        Task updTask = tasks.get(updId);

        while(res != 3){
        System.out.println("[1] - Atualizar descrição");
        System.out.println("[2] - Atualizar estado");
        System.out.println("[3] - Sair");
        res = Integer.parseInt(sc.nextLine());
        switch (res){
            case 1:
                System.out.print("Nova tarefa: ");
                try{
                    updTask.setDescricao(sc.nextLine());
                }catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
                System.out.println("Descricao atualizada!");
                break;
            case 2:
                System.out.println("[1] - Não terminada ");
                System.out.println("[2] - Em andamento");
                System.out.println("[3] - Terminada");
                try{
                    auxResEstado = Integer.parseInt(sc.nextLine());
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }

                if(auxResEstado == 1){
                    updTask.setEstado("Não terminada");
                }else if(auxResEstado == 2){
                    updTask.setEstado("Em andamento");
                }else if(auxResEstado == 3){
                    updTask.setEstado("Terminada");
                }
                System.out.println("Estado atualizado");
                break;
            case 3:
                break;
            default:
                System.out.println("opção inválida");
        }
        }
        tasks.set(updId, updTask);
        try (FileOutputStream fos = new FileOutputStream("C://CLI_TASK_APP/task.json")) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fos, tasks);
        }
        System.out.println("Tarefa atualizada com sucesso!");


    }
    public static void listTasks(statusFilter filtro) throws IOException {
        System.out.println("Listagem de tasks:");
        for (Task task : tasks) {
            if (filtro == statusFilter.Todas || task.getEstado().equalsIgnoreCase(filtro.toString().replace("_", " "))) {
                task.getInfo();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
                objectMapper.writeValue(arquivo, new ArrayList<Task>());
                System.out.println("Arquivo task.json criado com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao criar o banco de dados: " + e.getMessage());
            }
        }
        int res = 1;
        while(res != 0){
            if (arquivo.exists() && arquivo.length() > 0) {
                tasks = objectMapper.readValue(arquivo, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Task.class));
            }

            System.out.println("O que deseja fazer ?");
            System.out.println("[1] - Criar uma nova task");
            System.out.println("[2] - Remover uma task");
            System.out.println("[3] - Atualizar uma task");
            System.out.println("[4] - Listar todas as tasks");
            System.out.println("[5] - Listar todas as tasks Não terminadas");
            System.out.println("[6] - Listar todas as tasks Em andamento");
            System.out.println("[7] - Listar todas as tasks Terminadas");

            System.out.print("Sua opção: ");
            res = Integer.parseInt(sc.nextLine());
            switch (res) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        removeTask();
                        break;
                    case 3:
                        updateTask();
                        break;
                    case 4:
                        listTasks(statusFilter.Todas);
                        break;
                    case 5:
                        listTasks(statusFilter.Não_Terminada);
                        break;
                    case 6:
                        listTasks(statusFilter.Em_Andamento);
                        break;
                    case 7:
                        listTasks(statusFilter.Terminada);
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
        }
    }
}

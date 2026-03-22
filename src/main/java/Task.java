import java.util.Date;

public class Task {
    private String descricao = null;
    private int id = Main.tasks.size();
    private String estado = "Nao terminada";
    private final Date data = new Date();

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEstado(){
        return this.estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public Date getData(){
        return this.data;
    }

    public void getInfo(){
        System.out.println("Descricao: " + this.descricao);
        System.out.println("Estado: " + this.estado);
        System.out.println("Id: " + this.id);
        System.out.println("Data: " + this.data);
    }

}
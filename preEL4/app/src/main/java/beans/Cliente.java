package beans;

public class Cliente {

    private String IdCliente;
    private String Apellidos;
    private String Nombres;
    private int Edad;
    private String Sexo;

    public Cliente(String IdCliente, String Apellidos, String Nombres, int Edad, String Sexo){
        this.IdCliente = IdCliente;
        this.Apellidos = Apellidos;
        this.Nombres = Nombres;
        this.Edad = Edad;
        this.Sexo = Sexo;
    }


    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String idCliente) {
        IdCliente = idCliente;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }
}

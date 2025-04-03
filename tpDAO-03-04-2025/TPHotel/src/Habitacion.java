public class Habitacion {
    private int id, numero;
    private TipoHabitacion tipoHabitacion;
    private double precio;

    public Habitacion(int id, int numero, TipoHabitacion tipoHabitacion, double precio) {
        this.id = id;
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
    }

    public Habitacion(int numero, TipoHabitacion tipoHabitacion, double precio) {
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
    }

    public Habitacion() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", numero=" + numero +
                ", tipoHabitacion=" + tipoHabitacion +
                ", precio=" + precio +
                '}';
    }
}

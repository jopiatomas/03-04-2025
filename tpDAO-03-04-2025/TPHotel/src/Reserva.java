import java.sql.Date;

public class Reserva {

    private int id, cliente_id, habitacion_id;
    private Date fecha_entrada, fecha_salida;


    public Reserva(int id, int cliente_id, int habitacion_id, Date fecha_entrada, Date fecha_salida) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.habitacion_id = habitacion_id;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
    }
    public Reserva(int cliente_id, int habitacion_id, Date fecha_entrada, Date fecha_salida) {
        this.cliente_id = cliente_id;
        this.habitacion_id = habitacion_id;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
    }

    public Reserva() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getHabitacion_id() {
        return habitacion_id;
    }

    public void setHabitacion_id(int habitacion_id) {
        this.habitacion_id = habitacion_id;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cliente_id=" + cliente_id +
                ", habitacion_id=" + habitacion_id +
                ", fecha_entrada=" + fecha_entrada +
                ", fecha_salida=" + fecha_salida +
                '}';
    }
}

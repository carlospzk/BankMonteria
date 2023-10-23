
package bankmonteria;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;



/**
 *
 * @author sergio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
    //ArrayList Empleado
    //ArrayList Cliente
    //ArrayList Tramite
    ArrayList<Empleado> empleados = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Tramite> tramites = new ArrayList<>();
    ArrayList<Cargo> cargos = new ArrayList<>();
    ArrayList<tipoCuenta> tipoCuentas = new ArrayList<>();

    //Crear cargos
    Cargo[] cargosArray = generateCargos();

    //Agregar cargos al arraylist
    for (int i = 0; i < cargosArray.length; i++) {
        cargos.add(cargosArray[i]);
    }

    
    



        int opcion;
        

        do {
            opcion = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Menú\n"
                    + "1. Crear empleado\n"
                    + "2. Crear cliente\n"
                    + "3. Crear trámite\n"
                    + "4. Consulta de saldo disponible\n" 
                    + "5. Realizar consignaciones\n"
                    + "6. Realizar retiros\n"
                    + "7. Actualizar datos de los clientes\n"
                    + "8. Auditoría\n"
                    + "9. Salir\n"
                    + "Ingrese una opción: "));


            switch (opcion) {
                case 1:
                Empleado empleado = new Empleado();

                empleado = createEmpleado(cargos);

                JOptionPane.showMessageDialog(null, "Empleado creado: \n" + empleado.toString());

                empleados.add(empleado);

                
                    //Crear Empleados
                    JOptionPane.showMessageDialog(null, "Crear Empleados");
                    

                    break;
                case 2:
                    //Crear Clientes
                    JOptionPane.showMessageDialog(null, "Crear Clientes");
                    if(empleados.size() > 0){
                        JOptionPane.showMessageDialog(null, "Empleados: \n" + empleados.toString());
                    }else{
                        JOptionPane.showMessageDialog(null, "No hay empleados creados, por favor cree un empleado primero.");
                    }

                    Cliente cliente = new Cliente();

                    //cliente = createCliente(empleados);

                    JOptionPane.showMessageDialog(null, "Cliente creado: \n" + cliente.toString());

                    clientes.add(cliente);




                    break;
                case 3:
                    //Crear trámites
                    JOptionPane.showMessageDialog(null, "Crear trámites");



                    break;
                case 4:
                    //Consulta de saldo disponible
                    JOptionPane.showMessageDialog(null, "Consulta de saldo disponible");



                    break;

                case 5:
                    //Realizar consignaciones
                    JOptionPane.showMessageDialog(null, "Realizar consignaciones");




                    break;

                case 6:
                    //Realizar retiros
                    JOptionPane.showMessageDialog(null, "Realizar retiros");



                    break;

                case 7:
                    //Actualizar datos de los clientes
                    JOptionPane.showMessageDialog(null, "Actualizar datos de los clientes");




                    break;

                case 8:
                    //Auditoría

                    JOptionPane.showMessageDialog(null, "Auditoría");


                    break;

                case 9:
                    //Salir
                    JOptionPane.showMessageDialog(null, "Salir");

                    break;

                default:
                    //Opción inválida 
                    javax.swing.JOptionPane.showMessageDialog(null, "Opción inválida");
                    
                    
                    break;
            }

        } while (opcion != 9);
    }


    static Empleado createEmpleado(ArrayList<Cargo> cargos) {
        String identificacionEmpleado = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");
                    String nombreEmpleado = JOptionPane.showInputDialog("Ingrese su nombre: ");
                    String apellidoEmpleado = JOptionPane.showInputDialog("Ingrese su apellido: ");
                    String input = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del usuario " + (i + 1) + " en formato dd/MM/yyyy: ");

            String[] parts = input.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

                    

                    String direccionEmpleado = JOptionPane.showInputDialog("Ingrese su direccion: ");

                    
                    
                    

                    //Desplegar select con los cargos
                    String cargoEmpleado = JOptionPane.showInputDialog("Ingrese el codigo de su cargo: " + 
                    "\n1. Asesor de ventas" +
                    "\n2. Cajero" , generateCargos());
                    

                    //validar que el cargo exista
                    boolean isCargoValido = validateCargoConCode(cargoEmpleado, cargos);

                    // si el cargo no es válido, mostrar un mensaje de error y volver a pedir el código del cargo
                    while (!isCargoValido) {
                        JOptionPane.showMessageDialog(null, "El código del cargo no es válido. Por favor, ingresa un código válido.");
                        cargoEmpleado = JOptionPane.showInputDialog("Ingrese el codigo de su cargo: " + 
                        "\n1. Asesor de ventas" +
                        "\n2. Cajero", generateCargos());
                        isCargoValido = validateCargoConCode(cargoEmpleado, cargos);
                    }

                    String cargoEmpleadoDescription = "";

                    //Recorrer los cargos
                    for (int i = 0; i < cargos.size(); i++) {
                        //Obtener el cargo actual
                        Cargo cargo = cargos.get(i);

                        //Obtener el código del cargo actual
                        String codeCargo = cargo.getCode();

                        //Comparar el código del cargo actual con el código que se está buscando
                        if (codeCargo.equals(cargoEmpleado)) {
                            //Obtener la descripción del cargo actual
                            cargoEmpleadoDescription = cargo.getDescription();
                        }
                    }

        
        Empleado empleado = new Empleado(Integer.parseInt(identificacionEmpleado), nombreEmpleado, apellidoEmpleado, fechaNacimientoFormatead, direccionEmpleado, cargoEmpleado, cargoEmpleadoDescription);

        return empleado;
        
                    


    }

    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

    public static boolean isAdult(Date fechaNacimiento, Date now) {
        Calendar a = getCalendar(fechaNacimiento);
        Calendar b = getCalendar(now);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);

        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }

        return diff >= 18;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            return false;
        }

        if (month < 1 || month > 12) {
            return false;
        }

        if (day < 1 || day > 31) {
            return false;
        }

        if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
            return false;
        }

        if (month == 2) {
            if (day > 29) {
                return false;
            }
            if (day == 29 && !isLeapYear(year)) {
                return false;
            }
        }

        return true;
    }

    

    
    
    

    

    private static Cargo[] generateCargos() {
        Cargo[] cargos = {
            new Cargo("1", "Asesor de ventas"),
            new Cargo("2", "Cajero"),
        };

        return cargos;
    }

    public static boolean validateCargoConCode(String code, ArrayList<Cargo> cargos) {
        // Obtener todos los cargos de la clase Cargo
        // recorrer los cargos
        for (int i = 0; i < cargos.size(); i++) {
            // obtener el cargo actual
            Cargo cargo = cargos.get(i);

            // obtener el código del cargo actual
            String codeCargo = cargo.getCode();

            // comparar el código del cargo actual con el código que se está buscando
            if (codeCargo.equals(code)) {
                // retornar verdadero si el código del cargo es igual al código que se está buscando
                return true;
            }
        }

        // retornar falso si el código del cargo no es igual al código que se está buscando
        return false;
    }
    

    
















}


package bankmonteria;

import java.text.ParseException;
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
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        
        
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
                    //Crear Empleados
                Empleado empleado = new Empleado();

                empleado = createEmpleado(cargos);

                JOptionPane.showMessageDialog(null, "Empleado creado: \n" + empleado.toString());

                empleados.add(empleado);

                
                    //Crear Empleados
                    JOptionPane.showMessageDialog(null, "Crear Empleados");
                    //Mostrar todos los Empleados creados
                    if(empleados.size() > 0){
                        JOptionPane.showMessageDialog(null, "Empleados: \n" + empleados.toString());
                    }else{
                        JOptionPane.showMessageDialog(null, "No hay empleados creados, por favor cree un empleado primero.");
                    }
                    

                    break;
                case 2:
                    //Crear Clientes

                Cliente cliente = new Cliente();

                    //cliente = createCliente(empleados);

                JOptionPane.showMessageDialog(null, "Cliente creado: \n" + cliente.toString());

                JOptionPane.showMessageDialog(null, "Crear Clientes");

                clientes.add(cliente);

                //Mostrar todos los Clientes creados
                if(clientes.size() > 0){
                    JOptionPane.showMessageDialog(null, "Clientes: \n" + clientes.toString());
                }else{
                    JOptionPane.showMessageDialog(null, "No hay clientes creados, por favor cree un cliente primero.");
                }




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

    //Crear Empleado
    static Empleado createEmpleado(ArrayList<Cargo> cargos) {
        String identificacionEmpleado = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");
                    String nombreEmpleado = JOptionPane.showInputDialog("Ingrese su nombre: ");
                    String apellidoEmpleado = JOptionPane.showInputDialog("Ingrese su apellido: ");

                    // Pedir la fecha de nacimiento como string
                    String fechaNacimientoEmpleadoStr = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");

                    // Crear un objeto SimpleDateFormat para parsear la fecha
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    // Parsear la fecha de nacimiento
                    Date fechaNacimientoEmpleado = null;
                    try {
                        fechaNacimientoEmpleado = sdf.parse(fechaNacimientoEmpleadoStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //Mostrar fecha en calendario
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(fechaNacimientoEmpleado);
                    //Mostrar edad en años
                    int edad = calendar.get(Calendar.YEAR);
                    //Mostrar edad en meses
                    int edadMeses = calendar.get(Calendar.MONTH);
                    //Mostrar edad en dias
                    int edadDias = calendar.get(Calendar.DAY_OF_MONTH);
                    //Mostrar edad en horas
                    int edadHoras = calendar.get(Calendar.HOUR_OF_DAY);
                    //Mostrar edad en minutos
                    int edadMinutos = calendar.get(Calendar.MINUTE);
                    //Mostrar edad en segundos
                    int edadSegundos = calendar.get(Calendar.SECOND);

                    //Obtener la fecha actual
                    Date now = new Date();

                    //Continuar con el programa si es mayor de edad, sino, pedir fecha de nacimiento
                    if (isAdult(fechaNacimientoEmpleado, now)) {
                        JOptionPane.showMessageDialog(null, "Fecha de nacimiento: " + fechaNacimientoEmpleado + "\nEdad: " + edad + " años, " + edadMeses + " meses, " + edadDias + " dias, " + edadHoras + " horas, " + edadMinutos + " minutos, " + edadSegundos + " segundos");
                    } else {
                        JOptionPane.showMessageDialog(null, "No eres mayor de edad, por favor ingresa una fecha de nacimiento válida.");
                        fechaNacimientoEmpleadoStr = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
                        try {
                            fechaNacimientoEmpleado = sdf.parse(fechaNacimientoEmpleadoStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

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

        //Crear el objeto empleado mostrando la fecha de nacimiento y edad

        Empleado empleado = new Empleado(Integer.parseInt(identificacionEmpleado), nombreEmpleado, apellidoEmpleado, fechaNacimientoEmpleado, direccionEmpleado, cargoEmpleado, cargoEmpleadoDescription);
        

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

    //Generar cargos
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




    //Crear cliente
    static Cliente createCliente(ArrayList<tipoCuenta> tipoCuentas) {
        



                String identificacionCliente = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");
                    String nombreCliente = JOptionPane.showInputDialog("Ingrese su nombre: ");
                    String apellidoCliente = JOptionPane.showInputDialog("Ingrese su apellido: ");

                    // Pedir la fecha de nacimiento como string
                    String fechaNacimientoClienteStr = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");

                    // Crear un objeto SimpleDateFormat para parsear la fecha
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    // Parsear la fecha de nacimiento
                    Date fechaNacimientoCliente = null;
                    try {
                        fechaNacimientoCliente = sdf.parse(fechaNacimientoClienteStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //Mostrar fecha en calendario
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(fechaNacimientoCliente);
                    //Mostrar edad en años
                    int edad = calendar.get(Calendar.YEAR);
                    //Mostrar edad en meses
                    int edadMeses = calendar.get(Calendar.MONTH);
                    //Mostrar edad en dias
                    int edadDias = calendar.get(Calendar.DAY_OF_MONTH);
                    //Mostrar edad en horas
                    int edadHoras = calendar.get(Calendar.HOUR_OF_DAY);
                    //Mostrar edad en minutos
                    int edadMinutos = calendar.get(Calendar.MINUTE);
                    //Mostrar edad en segundos
                    int edadSegundos = calendar.get(Calendar.SECOND);

                    //Obtener la fecha actual
                    Date now = new Date();

                    //Continuar con el programa si es mayor de edad, sino, pedir fecha de nacimiento
                    if (isAdult(fechaNacimientoCliente, now)) {
                        JOptionPane.showMessageDialog(null, "Fecha de nacimiento: " + fechaNacimientoCliente + "\nEdad: " + edad + " años, " + edadMeses + " meses, " + edadDias + " dias, " + edadHoras + " horas, " + edadMinutos + " minutos, " + edadSegundos + " segundos");
                    } else {
                        JOptionPane.showMessageDialog(null, "No eres mayor de edad, por favor ingresa una fecha de nacimiento válida.");
                        fechaNacimientoClienteStr = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
                        try {
                            fechaNacimientoCliente = sdf.parse(fechaNacimientoClienteStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    String direccionCliente = JOptionPane.showInputDialog("Ingrese su direccion: ");

                    //Desplegar select con los tipos de cuenta
                    String tipoCuentaCliente = JOptionPane.showInputDialog("Ingrese el codigo de su tipo de cuenta: " +
                    "\n1. Ahorros" +
                    "\n2. Corriente", generateTipoCuentas());

                    //validar que el tipo de cuenta exista
                    boolean isTipoCuentaValido = validateTipoCuentaConCode(tipoCuentaCliente, tipoCuentas);

                    // si el tipo de cuenta no es válido, mostrar un mensaje de error y volver a pedir el código del tipo de cuenta
                    while (!isTipoCuentaValido) {
                        JOptionPane.showMessageDialog(null, "El código del tipo de cuenta no es válido. Por favor, ingresa un código válido.");
                        tipoCuentaCliente = JOptionPane.showInputDialog("Ingrese el codigo de su tipo de cuenta: " +
                        "\n1. Ahorros" +
                        "\n2. Corriente", generateTipoCuentas());
                        isTipoCuentaValido = validateTipoCuentaConCode(tipoCuentaCliente, tipoCuentas);
                    }

                    String tipoCuentaClienteDescription = "";

                    //Recorrer los tipos de cuenta
                    for (int i = 0; i < tipoCuentas.size(); i++) {
                        //Obtener el tipo de cuenta actual
                        tipoCuenta tipoCuenta = tipoCuentas.get(i);

                        //Obtener el código del tipo de cuenta actual
                        String codeTipoCuenta = tipoCuenta.getCodigo();

                        //Comparar el código del tipo de cuenta actual con el código que se está buscando
                        if (codeTipoCuenta.equals(tipoCuentaCliente)) {
                            //Obtener la descripción del tipo de cuenta actual
                            tipoCuentaClienteDescription = tipoCuenta.getDescripcion();
                        }
                    }

        //Crear el objeto cliente mostrando la fecha de nacimiento y edad

        Cliente cliente = new Cliente(Integer.parseInt(identificacionCliente), nombreCliente, apellidoCliente, fechaNacimientoCliente, direccionCliente, tipoCuentaCliente, tipoCuentaClienteDescription);

        return cliente;
    }

    //Generar tipos de cuenta
    private static tipoCuenta[] generateTipoCuentas() {
        tipoCuenta[] tipoCuentas = {
            new tipoCuenta("1", "Ahorros"),
            new tipoCuenta("2", "Corriente"),
        };

        return tipoCuentas;
    }

    public static boolean validateTipoCuentaConCode(String code, ArrayList<tipoCuenta> tipoCuentas) {
        // Obtener todos los tipos de cuenta de la clase tipoCuenta
        // recorrer los tipos de cuenta
        for (int i = 0; i < tipoCuentas.size(); i++) {
            // obtener el tipo de cuenta actual
            tipoCuenta tipoCuenta = tipoCuentas.get(i);

            // obtener el código del tipo de cuenta actual
            String codeTipoCuenta = tipoCuenta.getCodigo();

            // comparar el código del tipo de cuenta actual con el código que se está buscando
            if (codeTipoCuenta.equals(code)) {
                // retornar verdadero si el código del tipo de cuenta es igual al código que se está buscando
                return true;
            }
        }

        // retornar falso si el código del tipo de cuenta no es igual al código que se está buscando
        return false;
    }












}

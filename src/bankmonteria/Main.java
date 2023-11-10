
package bankmonteria;

// @charset "UTF-8";

import java.lang.reflect.Array;
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
    private static ArrayList<Empleado> empleados = new ArrayList<>();
    private static ArrayList<TipoCuenta> tipoCuentas = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Tramite> tramites = new ArrayList<>();
    private static ArrayList<Cargo> cargos = new ArrayList<>();

    public static void main(String[] args) throws ParseException {

        System.out.println("Probando tildes: áéíóúñ");
        
        seed();

        int opcion;

        do {
            opcion = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Menú\n"
                    + "1. Crear empleado\n"
                    + "2. Crear cliente\n"
                    + "3. Turno Empleado\n"
                    + "4. Consulta de saldo Disponible\n"
                    + "5. Realizar consignaciones\n"
                    + "6. Realizar retiros\n"
                    + "7. Actualizar datos de los clientes\n"
                    + "8. Auditoria\n"
                    + "9. Salir\n"
                    + "Ingrese una opción: "));

            switch (opcion) {
                case 1:
                    // Crear Empleados
                    Empleado empleado = new Empleado();

                    empleado = createEmpleado(cargos);

                    JOptionPane.showMessageDialog(null, "Empleado creado: \n" + empleado.toString());

                    empleados.add(empleado);

                    // Crear Empleados
                    JOptionPane.showMessageDialog(null, "Crear Empleados");
                    // Mostrar todos los Empleados creados
                    if (empleados.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Empleados: \n" + empleados.toString());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No hay empleados creados, por favor cree un empleado primero.");
                    }

                    break;

                case 2:
                    // Crear Clientes
                    JOptionPane.showMessageDialog(null, "Crear Clientes");

                    // Crear clientes
                    Cliente cliente = new Cliente();

                    cliente = createCliente(tipoCuentas);

                    JOptionPane.showMessageDialog(null, "Cliente creado: \n" + cliente.toString());

                    clientes.add(cliente);

                    // Mostrar todos los Clientes creados
                    if (clientes.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Clientes: \n" + clientes.toString());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No hay clientes creados, por favor cree un cliente primero.");
                    }

                    break;

                case 3:
                    // Cambiar Turno Empleado
                    JOptionPane.showMessageDialog(null, "Turno Empleado");

                    // Pedir identificación del empleado para asignarle el turno

                    String identificacionEmpleado = JOptionPane
                            .showInputDialog("Ingrese su numero de identificacion: ");

                    // Buscar el empleado con la identificación ingresada

                    boolean isIdentificacionValida = validateIdentification(identificacionEmpleado);

                    while (!isIdentificacionValida) {
                        JOptionPane.showMessageDialog(null,
                                "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
                        identificacionEmpleado = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");
                        isIdentificacionValida = validateIdentification(identificacionEmpleado);
                    }

                    Empleado empleadoConsultar = Empleado
                            .obtenerConIdentificacionEmpleado(Integer.parseInt(identificacionEmpleado), empleados);

                    if (empleadoConsultar == null) {
                        JOptionPane.showMessageDialog(null,
                                "No hay un empleado con la identificacion ingresada. Por favor, cree un empleado.");
                        return;
                    }

                    // Validar que el empleado exista

                    boolean isEmpleadoValido = empleadoConsultar != null;

                    // si el empleado no es válido, mostrar un mensaje de error y volver a pedir la
                    // identificacion del empleado

                    while (!isEmpleadoValido) {
                        JOptionPane.showMessageDialog(null,
                                "El empleado no existe. Por favor, ingresa una identificacion válida.");
                        identificacionEmpleado = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");
                        isEmpleadoValido = empleadoConsultar != null;
                    }

                    // Mostrar empleado con la identificacion ingresada
                    JOptionPane.showMessageDialog(null, "Empleado: " + empleadoConsultar.toString());

                    // Desplegar select con los turnos, si se elige el mismo turno, mostrar un
                    // mensaje de error y volver a pedir el turno

                    String turnoEmpleado = ChooseTurn();

                    System.out.println(turnoEmpleado);

                    boolean isTurnoEmpleado = turnoEmpleado.equals("Activo");

                    // Imprimir descripción del turno

                    JOptionPane.showMessageDialog(null, "Turno: " + turnoEmpleado);

                    // Mostrar mensaje de error si el empleado ya tiene el turno asignado

                    if (empleadoConsultar.isTurno() == isTurnoEmpleado) {
                        JOptionPane.showMessageDialog(null,
                                "El empleado ya tiene el turno asignado. Por favor, ingresa un turno diferente.");
                        turnoEmpleado = ChooseTurn();
                        isTurnoEmpleado = turnoEmpleado.equals("Activo");
                    }

                    // Asignar turno al empleado

                    empleadoConsultar.setTurno(isTurnoEmpleado);

                    // Mostrar empleado con el turno asignado

                    JOptionPane.showMessageDialog(null, "Empleado: " + empleadoConsultar.toString());

                    break;

                case 4:
                    // Consulta de saldo disponible lo realiza el cajero y el cliente
                    JOptionPane.showMessageDialog(null, "Consulta de saldo disponible");

                    // Consultar saldo con la identificacion del cliente y el numero de cuenta
                    int identificacionCliente = Integer
                            .parseInt(JOptionPane.showInputDialog("Ingrese su numero de identificacion: "));
                    int numeroCuentaCliente = Integer
                            .parseInt(JOptionPane.showInputDialog("Ingrese su numero de cuenta: "));

                    // Buscar el cliente con la identificacion ingresada
                    Cliente clienteConsultarSaldo = null;
                    for (Cliente clienteActual : clientes) {
                        if (clienteActual.getIdentificacion() == identificacionCliente) {
                            clienteConsultarSaldo = clienteActual;
                        }
                    }

                    // Buscar el cliente con el numero de cuenta ingresado
                    Cliente clienteConsultarSaldoNumeroCuenta = null;
                    for (Cliente clienteActualNumeroCuenta : clientes) {
                        if (clienteActualNumeroCuenta.getNumeroCuenta() == numeroCuentaCliente) {
                            clienteConsultarSaldoNumeroCuenta = clienteActualNumeroCuenta;
                        }
                    }

                    // Validar que el cliente exista
                    boolean isClienteValido = clienteConsultarSaldo != null
                            && clienteConsultarSaldoNumeroCuenta != null;

                    // si el cliente no es válido, mostrar un mensaje de error y volver a pedir la
                    // identificacion del cliente
                    while (!isClienteValido) {
                        JOptionPane.showMessageDialog(null,
                                "El cliente no existe. Por favor, ingresa una identificacion válida.");
                        identificacionCliente = Integer
                                .parseInt(JOptionPane.showInputDialog("Ingrese su numero de identificacion: "));
                        numeroCuentaCliente = Integer
                                .parseInt(JOptionPane.showInputDialog("Ingrese su numero de cuenta: "));
                        isClienteValido = clienteConsultarSaldo != null && clienteConsultarSaldoNumeroCuenta != null;
                    }

                    // Mostrar saldo disponible del cliente
                    JOptionPane.showMessageDialog(null, "Saldo disponible: " + clienteConsultarSaldo.getSaldo());

                    break;
                case 5:
                    // Realizar consignaciones
                    JOptionPane.showMessageDialog(null, "Realizar consignaciones");

                    // Crear tramite
                    Tramite tramiteConsignacion = new Tramite();

                    tramiteConsignacion = createTramiteConsignacion(clientes, empleados);

                    JOptionPane.showMessageDialog(null, "Tramite creado: \n" + tramiteConsignacion.toString());

                    tramites.add(tramiteConsignacion);

                    // Mostrar todos los Tramites creados
                    if (tramites.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Tramites: \n" + tramites.toString());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No hay tramites creados, por favor cree un tramite primero.");
                    }

                    break;

                case 6:
                    // Realizar retiros (realizado por el empleado cajero)
                    JOptionPane.showMessageDialog(null, "Realizar retiros");

                    Tramite tramiteRetiro = new Tramite();

                    tramiteRetiro = createTramiteRetiro(clientes, empleados);

                    JOptionPane.showMessageDialog(null, "Tramite creado: \n" + tramiteRetiro.toString());

                    tramites.add(tramiteRetiro);

                    // Mostrar todos los Tramites creados
                    if (tramites.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Tramites: \n" + tramites.toString());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No hay tramites creados, por favor cree un tramite primero.");
                    }



                    break;

                case 7:
                    // Actualizar datos de los clientes
                    JOptionPane.showMessageDialog(null, "Actualizar datos de los clientes");

                    Cliente clienteActualizarDatosCliente = new Cliente();

                    cliente = createActualizarDatos(clientes);

                    clientes.add(clienteActualizarDatosCliente);

                    // Mostrar cliente con los datos actualizados

                    JOptionPane.showMessageDialog(null, "Cliente: " + clienteActualizarDatosCliente.toString());

                    break;

                case 8:
                    // Auditoría

                    JOptionPane.showMessageDialog(null, "Auditoría");

                    auditoriaTramite(tramites);

                    break;

                case 9:
                    // Cerrar menu

                    JOptionPane.showMessageDialog(null, "Saliendo del programa");

                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema");

                    System.exit(0);

                    break;

                default:
                    // Opción inválida
                    javax.swing.JOptionPane.showMessageDialog(null, "Opción inválida");

                    break;
            }

        } while (opcion != 9);
    }

    // Crear Empleado
    static Empleado createEmpleado(ArrayList<Cargo> cargos) {

        String identificacionEmpleado;

        Empleado empleadoConsultar;

        boolean isIdentificacionEmpleadoValida = false; 

        do{
            identificacionEmpleado = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");

            // Validar identificacion del empleado
            isIdentificacionEmpleadoValida = validateIdentification(identificacionEmpleado);

            // Validar que la identificacion del empleado no se repita

            empleadoConsultar = Empleado.obtenerConIdentificacionEmpleado(Integer.parseInt(identificacionEmpleado),
                    empleados);

            if (empleadoConsultar != null) {
                JOptionPane.showMessageDialog(null,
                        "Ya hay un empleado con la identificacion ingresada. Por favor, ingresa una identificacion válida.");
                        isIdentificacionEmpleadoValida = false;
                
            } else if (!isIdentificacionEmpleadoValida) {
                JOptionPane.showMessageDialog(null,
                        "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
                return null;
            }
            
        } while (empleadoConsultar != null || !isIdentificacionEmpleadoValida);

        // String identificacionEmpleado = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");

        // boolean isIdentificacionValida = validateIdentification(identificacionEmpleado);

        // while (!isIdentificacionValida) {
        //     JOptionPane.showMessageDialog(null,
        //             "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
        //     identificacionEmpleado = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");
        //     isIdentificacionValida = validateIdentification(identificacionEmpleado);
        // }

        // // Buscar el empleado con la identificacion ingresada
        // Empleado empleadoConsultar = Empleado.obtenerConIdentificacionEmpleado(Integer.parseInt(identificacionEmpleado),
        //         empleados);

        // if (empleadoConsultar != null) {
        //     JOptionPane.showMessageDialog(null,
        //             "Ya hay un empleado con la identificacion ingresada. Por favor, ingresa una identificacion válida.");
        //     return null;
        // }

        String nombreEmpleado = JOptionPane.showInputDialog("Ingrese su nombre: ");
        String apellidoEmpleado = JOptionPane.showInputDialog("Ingrese su apellido: ");

        // Pedir la fecha de nacimiento como string
        String fechaNacimientoEmpleadoStr = JOptionPane
                .showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");

        // Crear un objeto SimpleDateFormat para parsear la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Parsear la fecha de nacimiento
        Date fechaNacimientoEmpleado = null;
        try {
            fechaNacimientoEmpleado = sdf.parse(fechaNacimientoEmpleadoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Obtener la fecha actual
        Date now = new Date();

        // Continuar con el programa si es mayor de edad, sino, pedir fecha de
        // nacimiento
        if (isAdult(fechaNacimientoEmpleado, now)) {
            JOptionPane.showMessageDialog(null,
                    "Fecha de nacimiento: " + fechaNacimientoEmpleado);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No eres mayor de edad, por favor ingresa una fecha de nacimiento válida.");
            fechaNacimientoEmpleadoStr = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
            try {
                fechaNacimientoEmpleado = sdf.parse(fechaNacimientoEmpleadoStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Fecha de nacimiento inválida");
                e.printStackTrace();
            }
        }

        String direccionEmpleado = JOptionPane.showInputDialog("Ingrese su dirección: ");

        // Desplegar select con los cargos
        String cargoEmpleado = JOptionPane.showInputDialog("Ingrese el código de su cargo: " +
                "\n1. Asesor de ventas" +
                "\n2. Cajero");


        // cargoEmpleado = '1'

                System.out.println(cargoEmpleado);

        // validar que el cargo exista
        boolean isCargoValido = validateCargoConCode(cargoEmpleado, cargos);

        

        // si el cargo no es válido, mostrar un mensaje de error y volver a pedir el
        // código del cargo
        while (!isCargoValido) {
            JOptionPane.showMessageDialog(null,
                    "El código del cargo no es válido. Por favor, ingresa un código válido.");
            cargoEmpleado = JOptionPane.showInputDialog("Ingrese el código de su cargo: " +
                    "\n1. Asesor de ventas" +
                    "\n2. Cajero");
            isCargoValido = validateCargoConCode(cargoEmpleado, cargos);
        }

        Cargo cargoConsultado = null;

        // Recorrer los cargos
        for (int i = 0; i < cargos.size(); i++) {
            // Obtener el cargo actual
            Cargo cargo = cargos.get(i);

            // Obtener el código del cargo actual
            String codeCargo = cargo.getCode();

            // Comparar el código del cargo actual con el código que se está buscando
            if (codeCargo.equals(cargoEmpleado)) {
                // Obtener la descripción del cargo actual
                cargoConsultado = cargo;
            }
        }


        // Imprimir descripción del cargo
        JOptionPane.showMessageDialog(null, "Cargo: " + cargoConsultado.getDescription());

        // Desplegar select con los turnos
        String turnoEmpleado = ChooseTurn();

        boolean isTurnoEmpleado = turnoEmpleado.equals("Activo");


        // Imprimir descripción del turno
        JOptionPane.showMessageDialog(null, "Turno: " + turnoEmpleado);

        // Crear el objeto empleado
        Empleado empleado = null;

        // (int, String, String, Date, String, String, boolean)
    //     (int, String , String , Date , String ,Cargo , boolean )

    // {
    //     descripcion: 'Cargo 11',
    //     code: '1'
    // }

        try {
            empleado = new Empleado(Integer.parseInt(identificacionEmpleado), nombreEmpleado, apellidoEmpleado,
                    fechaNacimientoEmpleado, direccionEmpleado, cargoConsultado, isTurnoEmpleado);

            System.out.println("Empleado creado: " + empleado.toString());
        } catch (NumberFormatException e) {
            // Manejo de excepción en caso de que identificacionEmpleado no sea un número
            // válido
            e.printStackTrace(); // Imprime la traza de la pila para depuración
        } catch (Exception e) {
            // Otras excepciones generales
            JOptionPane.showMessageDialog(null, "Error al crear el empleado");
            return null;
        }


        // Empleado empleado = new Empleado(Integer.parseInt(identificacionEmpleado), nombreEmpleado, apellidoEmpleado,
        // fechaNacimientoEmpleado, direccionEmpleado, cargoEmpleado, isTurnoEmpleado);

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

    // Generar cargos
    private static Cargo[] generateCargos() {
        Cargo[] cargos = {
                new Cargo("1", "Asesor de ventas"),
                new Cargo("2", "Cajero"),
        };

        return cargos;
    }

    private static Cliente[] generateClientes() {
        Cliente[] clientes = {
                new Cliente(1234567890, "Cliente 1", "Apellido 1", new Date(), "Dirección 1", "1",
                        (int) (Math.random() * 90000) + 10000, true, 1000000),
                new Cliente(1234567891, "Cliente 2", "Apellido 2", new Date(), "Dirección 2", "2",
                        (int) (Math.random() * 90000) + 10000, true, 2000000),
                new Cliente(1234567892, "Cliente 3", "Apellido 3", new Date(), "Dirección 3", "1",
                        (int) (Math.random() * 90000) + 10000, true, 3000000),
                new Cliente(1234567893, "Cliente 4", "Apellido 4", new Date(), "Dirección 4", "2",
                        (int) (Math.random() * 90000) + 10000, true, 4000000),
        };

        return clientes;
    }

    private static Empleado[] generateEmpleados(Cargo[] cargos) {
        Empleado[] empleados = {
                new Empleado(1234567899, "Empleado 1", "Apellido 1", new Date(), "Dirección 1", cargos[0],
                        true),
                new Empleado(1234567898, "Empleado 2", "Apellido 2", new Date(), "Dirección 2", cargos[1], true),
                new Empleado(1234567897, "Empleado 3", "Apellido 3", new Date(), "Dirección 3", cargos[0],
                        true),
                new Empleado(1234567896, "Empleado 4", "Apellido 4", new Date(), "Dirección 4", cargos[1], true),
        };

        return empleados;
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
                // retornar verdadero si el código del cargo es igual al código que se está
                // buscando
                return true;
            }
        }

        // retornar falso si el código del cargo no es igual al código que se está
        // buscando
        return false;
    }

    // Choose turn
    static String ChooseTurn() {
        int opcionT;

        do {
            String[] optionT1 = {
                    "1. Activo",
                    "2. Inactivo"
            };

            opcionT = JOptionPane.showOptionDialog(null, "Seleccione un turno", "Turno", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, optionT1, optionT1[0]);

            switch (opcionT) {
                case 0:
                    return "Activo";
                case 1:
                    return "Inactivo";
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
                    break;
            }

        } while (opcionT != 0 && opcionT != 1);
        return "";
    }

    static Cliente createCliente(ArrayList<TipoCuenta> tipoCuentas) {
        // Crear clientes

        boolean isValidEmpleado = false;

        Empleado empleadoConsultar = null;

        while(!isValidEmpleado) {
            
            String identificationEmpleado = JOptionPane.showInputDialog("(Empleado Asesor de Ventas) Ingrese su numero de identificacion: ");

            boolean validIdentification = validateIdentification(identificationEmpleado);

            if(!validIdentification) {
                JOptionPane.showMessageDialog(null,
                        "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
                isValidEmpleado = false;
            } else {
                empleadoConsultar = Empleado
                        .obtenerConIdentificacionEmpleado(Integer.parseInt(identificationEmpleado), empleados);

                if (empleadoConsultar == null) {
                    JOptionPane.showMessageDialog(null,
                            "No hay un empleado con la identificacion ingresada. Por favor, cree un empleado.");
                    isValidEmpleado = false;
                } else if (!empleadoConsultar.getCargo().equals("1")) {
                    JOptionPane.showMessageDialog(null,
                            "El empleado no es Asesor de Ventas. Por favor, cree un empleado Asesor de Ventas.");
                    isValidEmpleado = false;
                } else if (!empleadoConsultar.isTurno()) {
                    JOptionPane.showMessageDialog(null,
                            "El empleado Asesor de Ventas no está en turno. Por favor, actualice el turno al empleado Asesor de Ventas.");
                    isValidEmpleado = false;
                } else {
                    isValidEmpleado = true;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "El empleado Asesor de Ventas es: " + empleadoConsultar.getNombre() + " " + empleadoConsultar.getApellido());


        String identificacionCliente;

        Cliente clienteConsultar;

        boolean isIdentificacionClienteValida = false;

        do {
            identificacionCliente = JOptionPane.showInputDialog("Ingrese el numero identificacion del cliente: ");

            // Validar identificacion del cliente
            isIdentificacionClienteValida = validateIdentification(identificacionCliente);

            // Validar que la identificacion del cliente no se repita
            clienteConsultar = Cliente.obtenerConIdentificacionCliente(Integer.parseInt(identificacionCliente),
                    clientes);

            if (clienteConsultar != null) {
                JOptionPane.showMessageDialog(null,
                        "Ya hay un cliente con la identificacion ingresada. Por favor, ingresa una identificacion válida.");
                        isIdentificacionClienteValida = false;
                
            } else if (!isIdentificacionClienteValida) {
                JOptionPane.showMessageDialog(null,
                        "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
                return null;
            }




        } while (clienteConsultar != null || !isIdentificacionClienteValida);


        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
        String apellidoCliente = JOptionPane.showInputDialog("Ingrese el apellido del cliente: ");

        // Pedir la fecha de nacimiento como string
        String fechaNacimientoClienteStr = JOptionPane
                .showInputDialog("Ingrese la fecha de nacimiento del cliente en formato (dd/MM/yyyy): ");

        // Crear un objeto SimpleDateFormat para parsear la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Parsear la fecha de nacimiento
        Date fechaNacimientoCliente = null;
        try {
            fechaNacimientoCliente = sdf.parse(fechaNacimientoClienteStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Obtener la fecha actual
        Date now = new Date();

        // Continuar con el programa si es mayor de edad, sino, pedir fecha de
        // nacimiento
        if (isAdult(fechaNacimientoCliente, now)) {
            JOptionPane.showMessageDialog(null,
                    "Fecha de nacimiento: " + fechaNacimientoCliente);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No eres mayor de edad, por favor ingresa una fecha de nacimiento válida.");
            fechaNacimientoClienteStr = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
            try {
                fechaNacimientoCliente = sdf.parse(fechaNacimientoClienteStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String direccionCliente = JOptionPane.showInputDialog("Ingrese la dirección del cliente: ");

        // Desplegar select con los tipos de cuenta
        String tipoCuentaCliente = JOptionPane.showInputDialog("Ingrese el código del tipo de cuenta del cliente: " +
                "\n1. Ahorros" +
                "\n2. Corriente");

        // validar que el tipo de cuenta exista
        boolean isTipoCuentaValido = validateTipoCuentaConCode(tipoCuentaCliente, tipoCuentas);

        // si el tipo de cuenta no es válido, mostrar un mensaje de error y volver a
        // pedir el código del tipo de cuenta
        while (!isTipoCuentaValido) {
            JOptionPane.showMessageDialog(null,
                    "El código del tipo de cuenta no es válido. Por favor, ingresa un código válido.");
            tipoCuentaCliente = JOptionPane.showInputDialog("Ingrese el código de su tipo de cuenta: " +
                    "\n1. Ahorros" +
                    "\n2. Corriente", generateTipoCuentas());
            isTipoCuentaValido = validateTipoCuentaConCode(tipoCuentaCliente, tipoCuentas);
        }

        String tipoCuentaClienteDescription = "";

        // Recorrer los tipos de cuenta
        for (int i = 0; i < tipoCuentas.size(); i++) {
            // Obtener el tipo de cuenta actual
            TipoCuenta tipoCuenta = tipoCuentas.get(i);

            // Obtener el código del tipo de cuenta actual
            String codeTipoCuenta = tipoCuenta.getCodigo();

            // Comparar el código del tipo de cuenta actual con el código que se está
            // buscando
            if (codeTipoCuenta.equals(tipoCuentaCliente)) {
                // Obtener la descripción del tipo de cuenta actual
                tipoCuentaClienteDescription = tipoCuenta.getDescripcion();
            }
        }

        // Inicializar varibles informacion adicional al seleccionar el tipo de cuenta
        // Corriente
        String nombreEmpresa = "";
        int nit = 0;
        int telefonoEmpresa = 0;
        String nombreRepresentante = "";
        int telefonoRepresentante = 0;
        String direccionEmpresa = "";

        // Condicion para imprimir informacion adicional al seleccionar el tipo de
        // cuenta Corriente
        if (tipoCuentaCliente.equals("2")) {
            JOptionPane.showMessageDialog(null, "Informacion adicional al seleccionar el tipo de cuenta Corriente");
            // Guardar nombre de la empresa
            nombreEmpresa = JOptionPane.showInputDialog("Ingrese el nombre de la empresa: ");
            // Guardar NIT de la empresa
            nit = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el NIT de la empresa: "));
            System.out.println("NIT de la empresa" + nit);
            // Guardar numero de telefono de la empresa
            telefonoEmpresa = Integer
                    .parseInt(JOptionPane.showInputDialog("Ingrese el numero de telefono de la empresa: "));
            System.out.println("Numero de telefono de la empresa" + telefonoEmpresa);
            // Guardar nombre del representante legal de la empresa
            nombreRepresentante = JOptionPane
                    .showInputDialog("Ingrese el nombre del representante legal de la empresa: ");
            System.out.println("Nombre del representante legal de la empresa" + nombreRepresentante);
            // Guardar numero de identificacion del representante legal de la empresa
            telefonoRepresentante = Integer.parseInt(JOptionPane
                    .showInputDialog("Ingrese el numero de identificacion del representante legal de la empresa: "));
            System.out
                    .println("Numero de identificacion del representante legal de la empresa" + telefonoRepresentante);
            // Guardar numero de telefono del representante legal de la empresa
            JOptionPane.showMessageDialog(null,
                    "Ingrese el numero de telefono del representante legal de la empresa: ");
            System.out.println("Numero de telefono del representante legal de la empresa" + telefonoRepresentante);
            // Guardar numero de telefono del representante legal de la empresa
            direccionEmpresa = JOptionPane
                    .showInputDialog("Ingrese el numero de telefono del representante legal de la empresa: ");
            System.out.println("Direccion de la empresa" + direccionEmpresa);
        }
        // Mostrar tipo de cuenta
        JOptionPane.showMessageDialog(null, "Tipo de cuenta: " + tipoCuentaClienteDescription);

        // Numero de cuenta del cliente aleatorio de 5 digitos e irrepetible
        int numeroCuentaCliente = (int) (Math.random() * 90000) + 10000;
        JOptionPane.showMessageDialog(null, "Numero de cuenta: " + numeroCuentaCliente);

        //

        // Desplegar select con los estados de cuenta
        String estadoCuentaCliente = ChooseEstadoCuentaCliente();
        boolean isEstadoCuentaCliente = estadoCuentaCliente.equals("Activa");
        System.out.println("Estado de cuenta");
        System.out.println(isEstadoCuentaCliente);

        // Saldo inicial del cliente
        int saldoInicialCliente = 0;
        JOptionPane.showMessageDialog(null, "Saldo inicial: " + saldoInicialCliente);

        // Imprimir datos del cliente

        // Crear el objeto cliente

        Cliente cliente = null;

        try {
            cliente = new Cliente(Integer.parseInt(identificacionCliente), nombreCliente, apellidoCliente,
                    fechaNacimientoCliente, direccionCliente, tipoCuentaCliente,
                    numeroCuentaCliente, isEstadoCuentaCliente, saldoInicialCliente);

            System.out.println("Cliente creado: " + cliente.toString());
        } catch (NumberFormatException e) {
            // Manejo de excepción en caso de que identificacionCliente no sea un número
            // válido
            e.printStackTrace(); // Imprime la traza de la pila para depuración
        } catch (Exception e) {
            // Otras excepciones generales
            JOptionPane.showMessageDialog(null, "Error al crear el cliente");
            return null;
        }

        // Imprimir datos del cliente
        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Creación Cliente\n" +
                cliente.formatClient() +
                //Imprimir informacion adicional al seleccionar el tipo de cuenta Corriente
                (tipoCuentaCliente.equals("2") ? "Nombre de la empresa: " + nombreEmpresa + "\n" +
                        "NIT de la empresa: " + nit + "\n" +
                        "Numero de telefono de la empresa: " + telefonoEmpresa + "\n" +
                        "Nombre del representante legal de la empresa: " + nombreRepresentante + "\n" +
                        "Numero de identificacion del representante legal de la empresa: " + telefonoRepresentante + "\n" +
                        "Numero de telefono del representante legal de la empresa: " + telefonoRepresentante + "\n" +
                        "Dirección de la empresa: " + direccionEmpresa + "\n" : "") +
                "...¡Tramite Creación de cliente exitoso!...\n" +
                "Atendido por el empleado: " + empleadoConsultar.getNombre() + " " + empleadoConsultar.getApellido()+ "\n" +
                "Fecha: " + now + "\n" +
                "Hora: " + now + "\n" +
                ":::::::::::::::::::::::::::::::::::::::");

        return cliente;
    }

    // Generar tipos de cuenta
    private static TipoCuenta[] generateTipoCuentas() {
        TipoCuenta[] tipoCuentas = {
                new TipoCuenta("1", "Ahorros"),
                new TipoCuenta("2", "Corriente"),
        };

        return tipoCuentas;
    }

    public static boolean validateTipoCuentaConCode(String codigo, ArrayList<TipoCuenta> tipoCuentas) {
        // Obtener todos los tipos de cuenta de la clase tipoCuenta
        // recorrer los tipos de cuenta
        for (int i = 0; i < tipoCuentas.size(); i++) {
            // obtener el tipo de cuenta actual
            TipoCuenta tipoCuenta = tipoCuentas.get(i);

            // obtener el código del tipo de cuenta actual
            String codigoTipoCuenta = tipoCuenta.getCodigo();

            // comparar el código del tipo de cuenta actual con el código que se está
            // buscando
            if (codigoTipoCuenta.equals(codigoTipoCuenta)) {
                // retornar verdadero si el código del tipo de cuenta es igual al código que se
                // está buscando
                return true;
            }
        }

        // retornar falso si el código del tipo de cuenta no es igual al código que se
        // está buscando
        return false;
    }

    // Choose turn

    // Estado cuenta Cliente
    static String ChooseEstadoCuentaCliente() {
        int opcionEC;

        do {
            String[] optionEC1 = {
                    "1. Activa",
                    "2. Inactiva"
            };

            opcionEC = JOptionPane.showOptionDialog(null, "Seleccione el estado de su cuenta", "Estado de cuenta",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionEC1, optionEC1[0]);

            switch (opcionEC) {
                case 0:
                    return "Activa";
                case 1:
                    return "Inactiva";
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
                    break;
            }
        } while (opcionEC != 0 && opcionEC != 1);
        return "";
    }

    static void seed() {
        Cargo[] cargosArray = generateCargos();
        Cliente[] clientesArray = generateClientes();
        TipoCuenta[] tipoCuentasArray = generateTipoCuentas();
        Empleado[] empleadosArray = generateEmpleados(cargosArray);


        for (int i = 0; i < empleadosArray.length; i++) {
            System.out.println(empleadosArray[i].toString());
            empleados.add(empleadosArray[i]);
        }

        for (int i = 0; i < clientesArray.length; i++) {
            System.out.println(clientesArray[i].toString());
            clientes.add(clientesArray[i]);
        }

        // Agregar cargos al arraylist
        for (int i = 0; i < cargosArray.length; i++) {
            cargos.add(cargosArray[i]);
        }



        for (int i = 0; i < tipoCuentasArray.length; i++) {
            tipoCuentas.add(tipoCuentasArray[i]);
        }
    }

    // Realizar consignación
    static Tramite createTramiteConsignacion(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados) {
        // Crear tramite

        // Buscar el empleado con la identificacion ingresada

        boolean isValidEmpleado = false;

        Empleado empleadoConsultar = null;

        while (!isValidEmpleado) {

            String identificationEmpleado = JOptionPane.showInputDialog("(Empleado) Ingrese su numero de identificacion: ");
            boolean validIdentification = validateIdentification(identificationEmpleado);

            if (!validIdentification) {
                JOptionPane.showMessageDialog(null,
                        "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
                isValidEmpleado = false;
            } else {
                Empleado empleadoConsultar2 = Empleado
                        .obtenerConIdentificacionEmpleado(Integer.parseInt(identificationEmpleado), empleados);

                if (empleadoConsultar2 != null) {
                    empleadoConsultar = empleadoConsultar2;

                    isValidEmpleado = true;
                }

                if (empleadoConsultar2 == null) {
                    JOptionPane.showMessageDialog(null,
                            "No hay un empleado con la identificacion ingresada. Por favor, cree un empleado.");

                    isValidEmpleado = false;
                } else if (!empleadoConsultar2.getCargo().equals("2")) {

                    JOptionPane.showMessageDialog(null,
                            "El empleado no es Cajero. Por favor, cree un empleado Cajero.");
                    isValidEmpleado = false;

                } else if (!empleadoConsultar2.isTurno()) {
                    JOptionPane.showMessageDialog(null,
                            "El empleado Cajero no está en turno. Por favor, asigne el turno al empleado Asesor de Ventas.");
                    isValidEmpleado = false;
                }
            }
        }
        // Obtener nombre empleado consultado

        // Buscar el empleado con la identificacion ingresada

        boolean isValidCliente = false;

        Cliente clienteConsultar = null;

        while (!isValidCliente) {
            String identificationCliente = JOptionPane.showInputDialog("(Cliente) Ingrese su numero de identificacion: ");
            System.out.println(identificationCliente);
            boolean validIdentification = validateIdentification(identificationCliente);

            if (!validIdentification) {
                JOptionPane.showMessageDialog(null,
                        "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
                isValidCliente = false;
            } else {

                Cliente getCliente = Cliente.obtenerConIdentificacionCliente(
                        Integer.parseInt(identificationCliente),
                        clientes);

                if (getCliente == null) {
                    JOptionPane.showMessageDialog(null,
                            "No hay un cliente con la identificacion ingresada. Por favor, cree un cliente.");
                    isValidCliente = false;
                } else {

                    clienteConsultar = getCliente;

                    isValidCliente = true;
                    
                    if (clienteConsultar.getEstadoCuenta() == false) {
                        JOptionPane.showMessageDialog(null,
                        "El cliente no tiene una cuenta activa. Por favor, cree una cuenta activa.");
                        isValidCliente = false;
                    }
                    
                    if (clienteConsultar.getEstadoCuenta() == false) {
                        JOptionPane.showMessageDialog(null,
                        "El cliente no tiene una cuenta activa. Por favor, actualizar su estado de cuenta.");
                        isValidCliente = false;
                    }
                }
            }

            // Buscar si existe cuenta cliente con la identificacion ingresada

        }

        // Mostrar información del cliente

        System.out.println(":::::::::::::::::::::::::::::::::::::::");

        // Buscar cliente con la identificacion o numero de cuenta4

        JOptionPane.showMessageDialog(null,
                "El cliente es " + clienteConsultar.getNombre() + " " + clienteConsultar.getApellido());

        String valorConsignacionStr = JOptionPane.showInputDialog("Ingrese el valor a consignar: ");

        // Validar que el valor a consignar sea un número
        try {
            Double.parseDouble(valorConsignacionStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "El valor a consignar no es válido. Por favor, ingresa un valor válido.");
            valorConsignacionStr = JOptionPane.showInputDialog("Ingrese el valor a consignar: ");
        }

        // Convertir el valor a consignar a double
        double valorConsignacion = Double.parseDouble(valorConsignacionStr);

        // Validar que el valor a consignar sea mayor a 0
        boolean isValorConsignacionValido = valorConsignacion > 0;

        if (!isValorConsignacionValido) {
            JOptionPane.showMessageDialog(null,
                    "El valor a consignar debe ser mayor a 0. Por favor, ingresa un valor válido.");
            valorConsignacionStr = JOptionPane.showInputDialog("Ingrese el valor a consignar: ");
            valorConsignacion = Double.parseDouble(valorConsignacionStr);
            isValorConsignacionValido = valorConsignacion > 0;
        }

        // Actualizar saldo
        double saldoActualizado = clienteConsultar.getSaldo() + valorConsignacion;

        // Actualizar saldo del cliente
        clienteConsultar.setSaldo(saldoActualizado);

        // Obtener valor tramite consignación

        double valorTramite = valorConsignacion;

        Tramite tramite = new Tramite(
                "Consignacion",
                valorTramite,
                clienteConsultar,
                empleadoConsultar
        );

        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Consignacion" + "\n" +
                "Cliente: " + clienteConsultar.getNombre() + " " + clienteConsultar.getApellido() + "\n" +
                "...¡Tramite consignación exitoso!...\n" +
                "Atendido por el empleado: " + 
                empleadoConsultar.getNombre() + " " + empleadoConsultar.getApellido() + "\n" +
                "Fecha: " + tramite.getFechaTramite() + "\n" +
                "Hora: " + tramite.getHoraTramite() + "\n" +
                ":::::::::::::::::::::::::::::::::::::::"
                );

        return tramite;

    }

    // Realizar retiro
    static Tramite createTramiteRetiro(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados) {        

        boolean isValidEmpleado = false;

        Empleado empleadoConsultar = null;

        while(!isValidEmpleado){
            String identificationEmpleado = JOptionPane.showInputDialog("(Empleado) Ingrese su numero de identificacion: ");
            boolean validIdentification = validateIdentification(identificationEmpleado);

            if (!validIdentification) {
                JOptionPane.showMessageDialog(null,
                        "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
                isValidEmpleado = false;
            } else {
                Empleado empleadoConsultar2 = Empleado
                        .obtenerConIdentificacionEmpleado(Integer.parseInt(identificationEmpleado), empleados);

                if (empleadoConsultar2 != null) {
                    empleadoConsultar = empleadoConsultar2;

                    isValidEmpleado = true;
                }

                if (empleadoConsultar2 == null) {
                    JOptionPane.showMessageDialog(null,
                            "No hay un empleado con la identificacion ingresada. Por favor, cree un empleado.");

                    isValidEmpleado = false;
                } else if (!empleadoConsultar2.getCargo().equals("2")) {

                    JOptionPane.showMessageDialog(null,
                            "El empleado no es Cajero. Por favor, cree un empleado Cajero.");
                    isValidEmpleado = false;

                } else if (!empleadoConsultar2.isTurno()) {
                    JOptionPane.showMessageDialog(null,
                            "El empleado Cajero no está en turno. Por favor, asigne el turno al empleado Cajero.");
                    isValidEmpleado = false;
                }
            }


        }

        boolean isValidCliente = false;

        Cliente clienteConsultar = null;

        while (!isValidCliente) {
            String identificationCliente = JOptionPane.showInputDialog("(Cliente) Ingrese su numero de identificacion: ");
            System.out.println(identificationCliente);
            boolean validIdentification = validateIdentification(identificationCliente);

            if (!validIdentification) {
                JOptionPane.showMessageDialog(null,
                        "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
                isValidCliente = false;
            } else {

                Cliente getCliente = Cliente.obtenerConIdentificacionCliente(
                        Integer.parseInt(identificationCliente),
                        clientes);

                if (getCliente == null) {
                    JOptionPane.showMessageDialog(null,
                            "No hay un cliente con la identificacion ingresada. Por favor, cree un cliente.");
                    isValidCliente = false;
                } else {

                    clienteConsultar = getCliente;

                    isValidCliente = true;
                    
                    if (clienteConsultar.getEstadoCuenta() == false) {
                        JOptionPane.showMessageDialog(null,
                        "El cliente no tiene una cuenta activa. Por favor, cree una cuenta activa.");
                        isValidCliente = false;
                    }
                    
                    if (clienteConsultar.getEstadoCuenta() == false) {
                        JOptionPane.showMessageDialog(null,
                        "El cliente no tiene una cuenta activa. Por favor, actualizar su estado de cuenta.");
                        isValidCliente = false;
                    }
                }
            }
        }
        // Obtener fecha actual
        Date now = new Date();

        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Retiro de Dinero\n" +
                "Atendido por el empleado: " + empleadoConsultar.getNombre() + " " + empleadoConsultar.getApellido() + "\n" +
                "Cliente: " + clienteConsultar + " " + clienteConsultar + "\n" +
                "Tipo de cuenta: " + clienteConsultar.getTipoCuenta() + "\n" +
                "Estado cuenta: " + clienteConsultar.getEstadoCuenta() + "\n" +
                "Dinero disponible: " + clienteConsultar.getSaldo() + "\n" +
                "Fecha: " + now + "\n" +
                "Hora: " + now + "\n" +
                ":::::::::::::::::::::::::::::::::::::::");

        // Obtener valor a retirar
        String valorRetiroStr = JOptionPane.showInputDialog("Ingrese el valor a retirar: ");

        // Validar que el valor a retirar sea un número
        try {
            Double.parseDouble(valorRetiroStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor a retirar no es válido. Por favor, ingresa un valor válido.");
            valorRetiroStr = JOptionPane.showInputDialog("Ingrese el valor a retirar: ");
        }

        // Convertir el valor a retirar a double
        double valorRetiro = Double.parseDouble(valorRetiroStr);

        // Validar que el valor a retirar sea mayor a 0
        boolean isValorRetiroValido = valorRetiro > 0;

        if (!isValorRetiroValido) {
            JOptionPane.showMessageDialog(null,
                    "El valor a retirar debe ser mayor a 0. Por favor, ingresa un valor válido.");
            valorRetiroStr = JOptionPane.showInputDialog("Ingrese el valor a retirar: ");
            valorRetiro = Double.parseDouble(valorRetiroStr);
            isValorRetiroValido = valorRetiro > 0;
        }

        // Validar que el valor a retirar sea menor al saldo del cliente
        boolean isValorRetiroMenorSaldo = valorRetiro <= clienteConsultar.getSaldo();

        if (!isValorRetiroMenorSaldo) {
            JOptionPane.showMessageDialog(null,
                    "El valor a retirar debe ser menor o igual al saldo del cliente. Por favor, ingresa un valor válido.");
            valorRetiroStr = JOptionPane.showInputDialog("Ingrese el valor a retirar: ");
            valorRetiro = Double.parseDouble(valorRetiroStr);
            isValorRetiroMenorSaldo = valorRetiro <= clienteConsultar.getSaldo();
        }

        // Actualizar saldo del cliente

        double saldoActualizado = clienteConsultar.getSaldo() - valorRetiro;

        double valorTramite = saldoActualizado;

        Tramite tramite = new Tramite(
                "Retiro",
                valorTramite,
                clienteConsultar,
                empleadoConsultar
        );

        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Retiro de Dinero\n" +
                "Atendido por el empleado: " + empleadoConsultar.getNombre() + " " + empleadoConsultar.getApellido() + "\n" +
                "Cliente: " + clienteConsultar + " " + clienteConsultar + "\n" +
                "Identificacion: " + clienteConsultar.getIdentificacion() + "\n" +
                "Tipo de cuenta: " + clienteConsultar.getTipoCuenta() + "\n" +
                "Estado cuenta: " + clienteConsultar.getEstadoCuenta() + "\n" +
                "Dinero disponible: " + clienteConsultar.getSaldo() + "\n"
                + "\n" +
                "Fecha: " + now + "\n" +
                "Hora: " + now + "\n" +
                ":::::::::::::::::::::::::::::::::::::::");

                return tramite;

    }

    static boolean validateIdentification(String identification) {
        // Validar que la identificacion sea un numero
        try {
            Integer.parseInt(identification);
        } catch (NumberFormatException e) {
            return false;
        }

        // Validar que la identificacion sea igual a 10
        if (identification.length() != 10) {
            return false;
        }

        return true;
    }

    // Realizar actualizar datos Cliente
    static Cliente createActualizarDatos(ArrayList<Cliente> clientes) {

        // Obtener identificacion cliente a actualizar

        String identificationCliente = JOptionPane.showInputDialog("Ingrese el numero de identificacion del cliente: ");

        // Buscar el empleado con la identificacion ingresada

        boolean isIdentificacionValida2 = validateIdentification(identificationCliente);

        while (!isIdentificacionValida2) {
            JOptionPane.showMessageDialog(null,
                    "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
            identificationCliente = JOptionPane.showInputDialog("(Cliente) Ingrese su numero de identificacion: ");
            isIdentificacionValida2 = validateIdentification(identificationCliente);
        }

        Cliente clienteConsultar = Cliente.obtenerConIdentificacionCliente(Integer.parseInt(identificationCliente),
                clientes);

        if (clienteConsultar == null) {
            JOptionPane.showMessageDialog(null,
                    "No hay un cliente con la identificacion ingresada. Por favor, cree un cliente.");
            return null;
        }

        // Actualizar nombre, apellido, fecha de nacimiento, direccion, tipo de cuenta

        String nombreCliente = JOptionPane.showInputDialog("Ingrese su nombre actualizado: ");

        String apellidoCliente = JOptionPane.showInputDialog("Ingrese su apellido actualizado: ");

        // Pedir la fecha de nacimiento como string

        String fechaNacimientoClienteStr = JOptionPane
                .showInputDialog("Ingrese su fecha de nacimiento actualizado en formato (dd/MM/yyyy): ");

        // Crear un objeto SimpleDateFormat para parsear la fecha

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Parsear la fecha de nacimiento

        Date fechaNacimientoCliente = null;

        try {
            fechaNacimientoCliente = sdf.parse(fechaNacimientoClienteStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Mostrar fecha en calendario

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fechaNacimientoCliente);

        // Obtener la fecha actual
        Date now = new Date();

        // Continuar con el programa si es mayor de edad, sino, pedir fecha de nacimiento

        if (isAdult(fechaNacimientoCliente, now)) {
            JOptionPane.showMessageDialog(null,
                    "Fecha de nacimiento: " + fechaNacimientoCliente);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No eres mayor de edad, por favor ingresa una fecha de nacimiento válida.");
            fechaNacimientoClienteStr = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
            try {
                fechaNacimientoCliente = sdf.parse(fechaNacimientoClienteStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String direccionCliente = JOptionPane.showInputDialog("Ingrese la direccion del cliente: ");

        // Desplegar select con los tipos de cuenta

        String tipoCuentaCliente = JOptionPane.showInputDialog("Ingrese el codigo del tipo de cuenta del cliente: " +
                "\n1. Ahorros" +
                "\n2. Corriente");

        // validar que el tipo de cuenta exista

        boolean isTipoCuentaValido = validateTipoCuentaConCode(tipoCuentaCliente, tipoCuentas);

        // si el tipo de cuenta no es válido, mostrar un mensaje de error y volver a pedir el código del tipo de cuenta

        while (!isTipoCuentaValido) {
            JOptionPane.showMessageDialog(null,
                    "El código del tipo de cuenta no es válido. Por favor, ingresa un código válido.");
            tipoCuentaCliente = JOptionPane.showInputDialog("Ingrese el código de su tipo de cuenta: " +
                    "\n1. Ahorros" +
                    "\n2. Corriente");
            isTipoCuentaValido = validateTipoCuentaConCode(tipoCuentaCliente, tipoCuentas);
        }

        String tipoCuentaClienteDescription = "";

        // Recorrer los tipos de cuenta

        for (int i = 0; i < tipoCuentas.size(); i++) {
            // Obtener el tipo de cuenta actual
            TipoCuenta tipoCuenta = tipoCuentas.get(i);

            // Obtener el código del tipo de cuenta actual
            String codeTipoCuenta = tipoCuenta.getCodigo();

            // Comparar el código del tipo de cuenta actual con el código que se está
            // buscando
            if (codeTipoCuenta.equals(tipoCuentaCliente)) {
                // Obtener la descripción del tipo de cuenta actual
                tipoCuentaClienteDescription = tipoCuenta.getDescripcion();
            }
        }

        // Inicializar variables información adicional al seleccionar el tipo de cuenta

        // Corriente
        String nombreEmpresa = "";
        int nit = 0;
        int telefonoEmpresa = 0;
        String nombreRepresentante = "";
        int telefonoRepresentante = 0;
        String direccionEmpresa = "";

        // Condición para imprimir información adicional al seleccionar el tipo de
        // cuenta Corriente

        if (tipoCuentaCliente.equals("2")) {
            JOptionPane.showMessageDialog(null, "Información adicional al seleccionar el tipo de cuenta Corriente");
            // Guardar nombre de la empresa
            nombreEmpresa = JOptionPane.showInputDialog("Ingrese el nombre de la empresa: ");
            // Guardar NIT de la empresa
            nit = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el NIT de la empresa: "));
            System.out.println("NIT de la empresa" + nit);
            // Guardar numero de telefono de la empresa
            telefonoEmpresa = Integer
                    .parseInt(JOptionPane.showInputDialog("Ingrese el numero de telefono de la empresa: "));
            System.out.println("Numero de telefono de la empresa" + telefonoEmpresa);
            // Guardar nombre del representante legal de la empresa
            nombreRepresentante = JOptionPane
                    .showInputDialog("Ingrese el nombre del representante legal de la empresa: ");
            System.out.println("Nombre del representante legal de la empresa" + nombreRepresentante);
            // Guardar numero de identificacion del representante legal de la empresa
            telefonoRepresentante = Integer.parseInt(JOptionPane
                    .showInputDialog("Ingrese el numero de identificacion del representante legal de la empresa: "));
            System.out
                    .println("Numero de identificacion del representante legal de la empresa" + telefonoRepresentante);
            // Guardar numero de telefono del representante legal de la empresa
            JOptionPane.showMessageDialog(null,
                    "Ingrese el numero de telefono del representante legal de la empresa: ");
            System.out.println("Numero de telefono del representante legal de la empresa" + telefonoRepresentante);
            // Guardar numero de telefono del representante legal de la empresa
            direccionEmpresa = JOptionPane
                    .showInputDialog("Ingrese el numero de telefono del representante legal de la empresa: ");
            System.out.println("Dirección de la empresa" + direccionEmpresa);
        }

        // Mostrar tipo de cuenta
        JOptionPane.showMessageDialog(null, "Tipo de cuenta: " + tipoCuentaClienteDescription);

        // Get numero de cuenta del cliente
        int numeroCuentaClienteActualizarDatos = clienteConsultar.getNumeroCuenta();

        // Desplegar select con los estados de cuenta

        String estadoCuentaCliente = ChooseEstadoCuentaCliente();

        boolean isEstadoCuentaCliente = estadoCuentaCliente.equals("Activa");

        System.out.println("Estado de cuenta");

        System.out.println(isEstadoCuentaCliente);

        // get saldo del Cliente

        double saldoInicialCliente = clienteConsultar.getSaldo();

        Cliente cliente = null;

        try {
            cliente = new Cliente(Integer.parseInt(identificationCliente), nombreCliente, apellidoCliente,
                    fechaNacimientoCliente, direccionCliente, tipoCuentaCliente,
                    numeroCuentaClienteActualizarDatos, isEstadoCuentaCliente, saldoInicialCliente);

            System.out.println("Cliente Actualizado: " + cliente.toString());
        } catch (NumberFormatException e) {
            // Manejo de excepción en caso de que identificacionCliente no sea un número
            // válido
            e.printStackTrace(); // Imprime la traza de la pila para depuración
        } catch (Exception e) {
            // Otras excepciones generales
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente");
            return null;
        }

        // Actualizar objeto cliente

        cliente.setNombre(nombreCliente);
        cliente.setApellido(apellidoCliente);
        cliente.setFechaNacimiento(fechaNacimientoCliente);
        cliente.setDireccion(direccionCliente);
        cliente.setTipoCuenta(tipoCuentaCliente);
        cliente.setNumeroCuenta(numeroCuentaClienteActualizarDatos);
        cliente.setEstadoCuenta(isEstadoCuentaCliente);

        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Montería\n" +
                "Tramite: Actualizar datos Cliente\n" +
                "Cliente: " + nombreCliente + " " + cliente.getApellido() + "\n" +
                "Identificacion: " + identificationCliente + "\n" +
                "Fecha de nacimiento: " + fechaNacimientoCliente + "\n" +
                "Dirección: " + direccionCliente + "\n" +
                "Tipo de cuenta: " + tipoCuentaClienteDescription + "\n" +
                // Condición para imprimir información adicional al seleccionar el tipo de cuenta Corriente
                        (tipoCuentaCliente.equals("2") ? "Nombre de la empresa: " + nombreEmpresa + "\n" +
                        "NIT de la empresa: " + nit + "\n" +
                        "Numero de telefono de la empresa: " + telefonoEmpresa + "\n" +
                        "Nombre del representante legal de la empresa: " + nombreRepresentante + "\n" +
                        "Numero de identificacion del representante legal de la empresa: " + telefonoRepresentante
                        + "\n" +
                        "Dirección de la empresa: " + direccionEmpresa + "\n" : "")
                + "" +
                "Numero de cuenta: " + numeroCuentaClienteActualizarDatos + "\n" +
                "Estado de cuenta: " + estadoCuentaCliente + "\n" +
                "Saldo disponible: " + saldoInicialCliente + "\n" +
                "...¡Tramite Actualizar datos Cliente exitoso!...\n" +
                "Fecha: " + now + "\n" +
                "Hora: " + now + "\n" +
                ":::::::::::::::::::::::::::::::::::::::");

        return cliente;
    }


    static void auditoriaTramite(ArrayList<Tramite> tramites) {

        // Consultar consignaciones totales realizadas
        int consignacionesTotales = 0;
        int retirosTotales = 0;

        double valorConsignacionTotal = 0;
        double valorRetiroTotal = 0;

        // obtener tipos de tramites

        ArrayList<Tramite> tramiteConsignacion = Tramite.obtenerConTipoTramite("Consignacion", tramites);

        ArrayList<Tramite> tramiteRetiro = Tramite.obtenerConTipoTramite("Retiro", tramites);

        String formatTramiteConsignacion = "";
        String formatTramiteRetiro = "";

        for (Tramite tramiteActual : tramiteConsignacion) {
            formatTramiteConsignacion += tramiteActual.formatTramite() + "\n";

            consignacionesTotales++;
            valorConsignacionTotal += tramiteActual.getValorTramite();
        }

        for (Tramite tramiteActual : tramiteRetiro) {
            formatTramiteRetiro += tramiteActual.formatTramite() + "\n";
            retirosTotales++;
            valorRetiroTotal += tramiteActual.getValorTramite();
        }

        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Auditoria de Consignaciones\n" +
                // "ID Tramite: " + tramite.getIdTramite() + "\n" +
                "ID Empleado -> ID Cliente -> Cuenta -> Valor\n" +
                formatTramiteConsignacion +

                "Consignaciones totales realizadas: " + consignacionesTotales + "\n" +
                "Dinero Consignado: " + valorConsignacionTotal + "\n" +
                // "Dinero consignado: " + tramite.getValorTramite() + "\n" +
                ":::::::::::::::::::::::::::::::::::::::\n" +
                "\n" +
                ":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Auditoria de Retiros\n" +
                // "ID Tramite: " + tramite.getIdTramite() + "\n" +
                "ID Empleado -> ID Cliente -> Cuenta -> Valor\n" +
                formatTramiteRetiro +
                "Retiros totales realizados: " + retirosTotales + "\n" +
                "Dinero Retirado: " + valorRetiroTotal + "\n" +
                "...¡Tramite Auditoria exitosa!...\n" +
                ":::::::::::::::::::::::::::::::::::::::");
    }

}

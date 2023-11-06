
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
    private static ArrayList<Empleado> empleados = new ArrayList<>();

    public static void main(String[] args) throws ParseException {

        // ArrayList Empleado
        // ArrayList Cliente
        // ArrayList Tramite
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Tramite> tramites = new ArrayList<>();
        ArrayList<Cargo> cargos = new ArrayList<>();
        ArrayList<tipoCuenta> tipoCuentas = new ArrayList<>();

        // Crear cargos
        Cargo[] cargosArray = generateCargos();

        // Agregar cargos al arraylist
        for (int i = 0; i < cargosArray.length; i++) {
            cargos.add(cargosArray[i]);
        }

        // Crear tipos de cuenta
        tipoCuenta[] tipoCuentasArray = generateTipoCuentas();

        // Agregar tipos de cuenta al arraylist
        for (int i = 0; i < tipoCuentasArray.length; i++) {
            tipoCuentas.add(tipoCuentasArray[i]);
        }

        int opcion;

        do {
            opcion = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Menú\n"
                    + "1. Crear empleado\n"
                    + "2. Crear cliente\n"
                    + "3. Consulta de saldo Disponible\n"
                    + "4. Realizar consignaciones\n"
                    + "5. Realizar retiros\n"
                    + "6. Actualizar datos de los clientes\n"
                    + "7. Auditoria\n"
                    + "8. Salir\n"
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
                case 4:
                    // Realizar consignaciones
                    JOptionPane.showMessageDialog(null, "Realizar consignaciones");

                    // Select para elegir cajero y continuar con la realizaron de la consignacion
                    // String nombreEmpleadoCajeroConsignacion = "";

                    // for (Empleado empleadoCajeroConsignacion : empleados) {
                    //     if (empleadoCajeroConsignacion.getCargo().equals("2") && empleadoCajeroConsignacion.isTurno()) {
                    //         nombreEmpleadoCajeroConsignacion = empleadoCajeroConsignacion.getNombre();
                    //     }
                    // }

                    // JOptionPane.showMessageDialog(null, "El empleado Cajero es: " + nombreEmpleadoCajeroConsignacion);

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

                case 5:
                    // Realizar retiros (realizado por el empleado cajero)
                    JOptionPane.showMessageDialog(null, "Realizar retiros");

                    // Select para elegir cajero y continuar con la realizaron del retiro
                    String nombreEmpleadoCajero = "";
                    for (Empleado empleadoCajero : empleados) {
                        if (empleadoCajero.getCargo().equals("2") && empleadoCajero.isTurno()) {
                            nombreEmpleadoCajero = empleadoCajero.getNombre();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "El empleado Cajero es: " + nombreEmpleadoCajero);

                    // Crear tramite
                    Tramite tramite = new Tramite();

                    tramite = createTramiteRetiro(clientes, empleados);

                    JOptionPane.showMessageDialog(null, "Tramite creado: \n" + tramite.toString());

                    tramites.add(tramite);

                    break;

                case 6:
                    // Actualizar datos de los clientes
                    JOptionPane.showMessageDialog(null, "Actualizar datos de los clientes");

                    // Select para elegir el cliente
                    String nombreClienteActualizarDatos = "";

                    for (Cliente clienteActualizarDatos : clientes) {
                        if (clienteActualizarDatos.getEstadoCuenta()) {
                            nombreClienteActualizarDatos = clienteActualizarDatos.getNombre();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "El cliente es: " + nombreClienteActualizarDatos);

                    // Actualizar datos del cliente
                    Cliente clienteActualizarDatos = null;

                    for (Cliente clienteActual : clientes) {
                        if (clienteActual.getEstadoCuenta()) {
                            clienteActualizarDatos = clienteActual;
                        }
                    }

                    // Validar que el cliente exista
                    boolean isClienteValidoActualizarDatos = clienteActualizarDatos != null;

                    // si el cliente no es válido, mostrar un mensaje de error y volver a pedir la
                    // identificacion del cliente
                    while (!isClienteValidoActualizarDatos) {
                        JOptionPane.showMessageDialog(null,
                                "El cliente no existe. Por favor, ingresa una identificacion válida.");
                        identificacionCliente = Integer
                                .parseInt(JOptionPane.showInputDialog("Ingrese su numero de identificacion: "));
                        isClienteValidoActualizarDatos = clienteActualizarDatos != null;
                    }

                    // Actualizar datos del cliente
                    String nombreClienteActualizarDatosNuevo = JOptionPane.showInputDialog("Ingrese su nombre: ");
                    String apellidoClienteActualizarDatosNuevo = JOptionPane.showInputDialog("Ingrese su apellido: ");
                    String fechaNacimientoClienteActualizarDatosNuevo = JOptionPane
                            .showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
                    String direccionClienteActualizarDatosNuevo = JOptionPane.showInputDialog("Ingrese su direccion: ");

                    // Crear un objeto SimpleDateFormat para parsear la fecha
                    SimpleDateFormat sdfActualizarDatos = new SimpleDateFormat("dd/MM/yyyy");

                    // Parsear la fecha de nacimiento
                    Date fechaNacimientoClienteActualizarDatos = null;

                    try {
                        fechaNacimientoClienteActualizarDatos = sdfActualizarDatos
                                .parse(fechaNacimientoClienteActualizarDatosNuevo);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // Imprimir datos del cliente
                    JOptionPane.showMessageDialog(null, "Cliente actualizado: \n" + clienteActualizarDatos.toString());

                    // Mostrar todos los Clientes creados
                    if (clientes.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Clientes: \n" + clientes.toString());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No hay clientes creados, por favor cree un cliente primero.");
                    }

                    // Actualizar datos del cliente
                    clienteActualizarDatos.actualizarDatosCliente(nombreClienteActualizarDatosNuevo,
                            apellidoClienteActualizarDatosNuevo, fechaNacimientoClienteActualizarDatos,
                            direccionClienteActualizarDatosNuevo);

                    // Imprimir datos del cliente
                    JOptionPane.showMessageDialog(null, "Cliente actualizado: \n" + clienteActualizarDatos.toString());

                    // Mostrar todos los Clientes creados
                    if (clientes.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Clientes: \n" + clientes.toString());

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No hay clientes creados, por favor cree un cliente primero.");
                    }

                    // Crear tramite
                    Tramite tramiteActualizarDatos = new Tramite();

                    tramiteActualizarDatos = createTramiteActualizarDatos(clientes, empleados);

                    JOptionPane.showMessageDialog(null, "Tramite creado: \n" + tramiteActualizarDatos.toString());

                    tramites.add(tramiteActualizarDatos);

                    // Mostrar todos los Tramites creados
                    if (tramites.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Tramites: \n" + tramites.toString());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No hay tramites creados, por favor cree un tramite primero.");
                    }

                    break;

                case 7:
                    // Auditoría

                    JOptionPane.showMessageDialog(null, "Auditoría");

                    // Mostrar todos los Tramites creados, consignaciones y retiros
                    if (tramites.size() > 0) {
                        JOptionPane.showMessageDialog(null, "Tramites: \n" + tramites.toString());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No hay tramites creados, por favor cree un tramite primero.");
                    }

                    break;

                case 8:
                    // Salir
                    JOptionPane.showMessageDialog(null, "Salir");

                    break;

                default:
                    // Opción inválida
                    javax.swing.JOptionPane.showMessageDialog(null, "Opción inválida");

                    break;
            }

        } while (opcion != 8);
    }

    // Crear Empleado
    static Empleado createEmpleado(ArrayList<Cargo> cargos) {
        String identificacionEmpleado = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");

        boolean isIdentificacionValida = validateIdentification(identificacionEmpleado);

        while (!isIdentificacionValida) {
            JOptionPane.showMessageDialog(null,
                    "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
            identificacionEmpleado = JOptionPane.showInputDialog("Ingrese su numero de identificacion: ");
            isIdentificacionValida = validateIdentification(identificacionEmpleado);
        }

        // Buscar el empleado con la identificacion ingresada
        Empleado empleadoConsultar = Empleado.obtenerConIdentificacionEmpleado(Integer.parseInt(identificacionEmpleado), empleados);

        if (empleadoConsultar != null) {
            JOptionPane.showMessageDialog(null,
                    "Ya hay un empleado con la identificacion ingresada. Por favor, ingresa una identificacion válida.");
            return null;
        }

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

        // Mostrar fecha en calendario
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaNacimientoEmpleado);
        // Mostrar edad en años
        int edad = calendar.get(Calendar.YEAR);
        // Mostrar edad en meses
        int edadMeses = calendar.get(Calendar.MONTH);
        // Mostrar edad en dias
        int edadDias = calendar.get(Calendar.DAY_OF_MONTH);
        // Mostrar edad en horas
        int edadHoras = calendar.get(Calendar.HOUR_OF_DAY);
        // Mostrar edad en minutos
        int edadMinutos = calendar.get(Calendar.MINUTE);
        // Mostrar edad en segundos
        int edadSegundos = calendar.get(Calendar.SECOND);

        // Obtener la fecha actual
        Date now = new Date();

        // Continuar con el programa si es mayor de edad, sino, pedir fecha de
        // nacimiento
        if (isAdult(fechaNacimientoEmpleado, now)) {
            JOptionPane.showMessageDialog(null,
                    "Fecha de nacimiento: " + fechaNacimientoEmpleado + "\nEdad: " + edad + " años, " + edadMeses
                            + " meses, " + edadDias + " dias, " + edadHoras + " horas, " + edadMinutos + " minutos, "
                            + edadSegundos + " segundos");
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
                "\n2. Cajero", generateCargos());

        // validar que el cargo exista
        boolean isCargoValido = validateCargoConCode(cargoEmpleado, cargos);

        // si el cargo no es válido, mostrar un mensaje de error y volver a pedir el
        // código del cargo
        while (!isCargoValido) {
            JOptionPane.showMessageDialog(null,
                    "El código del cargo no es válido. Por favor, ingresa un código válido.");
            cargoEmpleado = JOptionPane.showInputDialog("Ingrese el código de su cargo: " +
                    "\n1. Asesor de ventas" +
                    "\n2. Cajero", generateCargos());
            isCargoValido = validateCargoConCode(cargoEmpleado, cargos);
        }

        String cargoEmpleadoDescription = "";

        // Recorrer los cargos
        for (int i = 0; i < cargos.size(); i++) {
            // Obtener el cargo actual
            Cargo cargo = cargos.get(i);

            // Obtener el código del cargo actual
            String codeCargo = cargo.getCode();

            // Comparar el código del cargo actual con el código que se está buscando
            if (codeCargo.equals(cargoEmpleado)) {
                // Obtener la descripción del cargo actual
                cargoEmpleadoDescription = cargo.getDescription();
            }
        }

        // Imprimir descripcion del cargo
        JOptionPane.showMessageDialog(null, "Cargo: " + cargoEmpleadoDescription);

        // Desplegar select con los turnos
        String turnoEmpleado = ChooseTurn();

        System.out.println(turnoEmpleado);

        boolean isTurnoEmpleado = turnoEmpleado.equals("Activo");

        System.out.println("Turno");

        System.out.println(isTurnoEmpleado);

        // Imprimir descripcion del turno
        JOptionPane.showMessageDialog(null, "Turno: " + turnoEmpleado);

        // isAsesorVentas es true si el empleado es Asesor de Ventas y está en turno
        boolean isAsesorVentas = cargoEmpleado.equals("1") && isTurnoEmpleado;

        System.out.println("Asesor de ventas");

        System.out.println(isAsesorVentas);

        // isCajero es true si el empleado es Cajero y está en turno
        boolean isCajero = cargoEmpleado.equals("2") && isTurnoEmpleado;

        System.out.println("Cajero");

        System.out.println(isCajero);

        // Crear el objeto empleado
        Empleado empleado = new Empleado(Integer.parseInt(identificacionEmpleado), nombreEmpleado, apellidoEmpleado,
                fechaNacimientoEmpleado, direccionEmpleado, cargoEmpleado, cargoEmpleadoDescription, isTurnoEmpleado);

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

    // Crear cliente
    static Cliente createCliente(ArrayList<tipoCuenta> tipoCuentas) {
        // Crear clientes

        // Verificar que exista un empleado Asesor de Ventas en turno
        // boolean isAsesorVentas = false;
        // for (Empleado empleado : empleados) {
        //     if (empleado.getCargo().equals("1") && empleado.isTurno()) {
        //         isAsesorVentas = true;
        //     }
        // }

        // // Desplegar select para elegir el empleado Asesor de Ventas
        // String nombreEmpleadoAsesorVentas = "";
        // for (Empleado empleadoAsesorVentas : empleados) {
        //     if (empleadoAsesorVentas.getCargo().equals("1") && empleadoAsesorVentas.isTurno()) {
        //         nombreEmpleadoAsesorVentas = empleadoAsesorVentas.getNombre();
        //     }
        // }
        String identificationEmpleado = JOptionPane.showInputDialog("(Empleado) Ingrese su numero de identificacion: ");

        // Buscar el empleado con la identificacion ingresada

        boolean isIdentificacionValida = validateIdentification(identificationEmpleado);

        while (!isIdentificacionValida) {
            JOptionPane.showMessageDialog(null,
                    "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
            identificationEmpleado = JOptionPane.showInputDialog("(Empleado) Ingrese su numero de identificacion: ");
            isIdentificacionValida = validateIdentification(identificationEmpleado);
        }

        Empleado empleadoConsultar2 = Empleado.obtenerConIdentificacionEmpleado(Integer.parseInt(identificationEmpleado), empleados);

        if (empleadoConsultar2 == null) {
            JOptionPane.showMessageDialog(null,
                    "No hay un empleado con la identificacion ingresada. Por favor, cree un empleado.");
            return null;
        }

        if (!empleadoConsultar2.getCargo().equals("1")) {
            JOptionPane.showMessageDialog(null,
                    "El empleado no es Asesor de Ventas. Por favor, cree un empleado Asesor de Ventas.");
            return null;
        }

        if (!empleadoConsultar2.isTurno()) {
            JOptionPane.showMessageDialog(null,
                    "El empleado Asesor de Ventas no está en turno. Por favor, asigne el turno al empleado Asesor de Ventas.");
            return null;
        }

        JOptionPane.showMessageDialog(null, "El empleado Asesor de Ventas es: " + empleadoConsultar2.toString());

        // Crear Clientes
        String identificacionCliente = JOptionPane.showInputDialog("Ingrese el numero identificacion del cliente: ");

        //Validar identificacion del cliente 
        boolean isIdentificacionClienteValida = validateIdentification(identificacionCliente);

        while (!isIdentificacionClienteValida) {
            JOptionPane.showMessageDialog(null,
                    "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
            identificacionCliente = JOptionPane.showInputDialog("Ingrese el numero identificacion del cliente: ");
            isIdentificacionClienteValida = validateIdentification(identificacionCliente);
        }

        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
        String apellidoCliente = JOptionPane.showInputDialog("Ingrese el apellido del : ");

        // Pedir la fecha de nacimiento como string
        String fechaNacimientoClienteStr = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del cliente en formato (dd/MM/yyyy): ");

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
        // Mostrar edad en años
        int edad = calendar.get(Calendar.YEAR);
        // Mostrar edad en meses
        int edadMeses = calendar.get(Calendar.MONTH);
        // Mostrar edad en dias
        int edadDias = calendar.get(Calendar.DAY_OF_MONTH);
        // Mostrar edad en horas
        int edadHoras = calendar.get(Calendar.HOUR_OF_DAY);
        // Mostrar edad en minutos
        int edadMinutos = calendar.get(Calendar.MINUTE);
        // Mostrar edad en segundos
        int edadSegundos = calendar.get(Calendar.SECOND);

        // Obtener la fecha actual
        Date now = new Date();

        // Continuar con el programa si es mayor de edad, sino, pedir fecha de
        // nacimiento
        if (isAdult(fechaNacimientoCliente, now)) {
            JOptionPane.showMessageDialog(null,
                    "Fecha de nacimiento: " + fechaNacimientoCliente + "\nEdad: " + edad + " años, " + edadMeses
                            + " meses, " + edadDias + " dias, " + edadHoras + " horas, " + edadMinutos + " minutos, "
                            + edadSegundos + " segundos");
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
                "\n2. Corriente", generateTipoCuentas());

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
            tipoCuenta tipoCuenta = tipoCuentas.get(i);

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
        double saldoInicialCliente = 0;
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
            // Manejo de excepción en caso de que identificacionCliente no sea un número válido
            e.printStackTrace(); // Imprime la traza de la pila para depuración
        } catch (Exception e) {
            // Otras excepciones generales
            JOptionPane.showMessageDialog(null, "Error al crear el cliente");
            return null;
        }

        // System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
        //         "First Bank of Spring field - Monteria\n" +
        //         "Tramite: Creacion Cliente\n" +
        //         "Cliente: " + nombreCliente + " " + cliente.getApellido() + "\n" +
        //         "Identificacion: " + identificacionCliente + "\n" +
        //         "Fecha de nacimiento: " + fechaNacimientoCliente + "\n" +
        //         "Edad: " + edad + " años, " + edadMeses + " meses, " + edadDias + " dias, " + edadHoras + " horas, "
        //         + edadMinutos + " minutos, " + edadSegundos + " segundos\n" +
        //         "Direccion: " + direccionCliente + "\n" +
        //         "Tipo de cuenta: " + tipoCuentaClienteDescription + "\n" +
        //         // Condicion para imprimir informacion adicional al seleccionar el tipo de
        //         // cuenta Corriente
        //         (tipoCuentaCliente.equals("2") ? "Nombre de la empresa: " + nombreEmpresa + "\n" +
        //                 "NIT de la empresa: " + nit + "\n" +
        //                 "Numero de telefono de la empresa: " + telefonoEmpresa + "\n" +
        //                 "Nombre del representante legal de la empresa: " + nombreRepresentante + "\n" +
        //                 "Numero de identificacion del representante legal de la empresa: " + telefonoRepresentante
        //                 + "\n" +
        //                 "Direccion de la empresa: " + direccionEmpresa + "\n" : "")
        //         +
        //         "" +
        //         "Numero de cuenta: " + numeroCuentaCliente + "\n" +
        //         "Estado de cuenta: " + estadoCuentaCliente + "\n" +
        //         "Saldo disponible: " + saldoInicialCliente + "\n" +
        //         "...¡Tramite Creación de cliente exitoso!...\n" +
        //         "Atendido por el empleado: " + empleadoConsultar2.toString() + "\n" +
        //         "Fecha: " + now + "\n" +
        //         "Hora: " + now + "\n" +
        //         ":::::::::::::::::::::::::::::::::::::::"
        // );

        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Creacion Cliente\n" +
                cliente.formatClient() +
                "...¡Tramite Creación de cliente exitoso!...\n" +
                "Atendido por el empleado: " + empleadoConsultar2.toString() + "\n" +
                "Fecha: " + now + "\n" +
                "Hora: " + now + "\n" +
                ":::::::::::::::::::::::::::::::::::::::"
        );



        return cliente;
    }

    // Generar tipos de cuenta
    private static tipoCuenta[] generateTipoCuentas() {
        tipoCuenta[] tipoCuentas = {
                new tipoCuenta("1", "Ahorros"),
                new tipoCuenta("2", "Corriente"),
        };

        return tipoCuentas;
    }

    public static boolean validateTipoCuentaConCode(String codigo, ArrayList<tipoCuenta> tipoCuentas) {
        // Obtener todos los tipos de cuenta de la clase tipoCuenta
        // recorrer los tipos de cuenta
        for (int i = 0; i < tipoCuentas.size(); i++) {
            // obtener el tipo de cuenta actual
            tipoCuenta tipoCuenta = tipoCuentas.get(i);

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

    // Realizar consignacion
    static Tramite createTramiteConsignacion(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados) {
        // Crear tramite
        Tramite tramite = new Tramite();

        String identificationEmpleado = JOptionPane.showInputDialog("(Empleado) Ingrese su numero de identificacion: ");

        // Buscar el empleado con la identificacion ingresada

        boolean isIdentificacionValida = validateIdentification(identificationEmpleado);

        while (!isIdentificacionValida) {
            JOptionPane.showMessageDialog(null,
                    "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
            identificationEmpleado = JOptionPane.showInputDialog("(Empleado) Ingrese su numero de identificacion: ");
            isIdentificacionValida = validateIdentification(identificationEmpleado);
        }

        Empleado empleadoConsultar2 = Empleado.obtenerConIdentificacionEmpleado(Integer.parseInt(identificationEmpleado), empleados);

        if (empleadoConsultar2 == null) {
            JOptionPane.showMessageDialog(null,
                    "No hay un empleado con la identificacion ingresada. Por favor, cree un empleado.");
            return null;
        }

        if (!empleadoConsultar2.getCargo().equals("2")) {
            JOptionPane.showMessageDialog(null,
                    "El empleado no es Cajero. Por favor, cree un empleado Cajero.");
            return null;
        }

        if (!empleadoConsultar2.isTurno()) {
            JOptionPane.showMessageDialog(null,
                    "El empleado Asesor de Ventas no está en turno. Por favor, asigne el turno al empleado Asesor de Ventas.");
            return null;
        }

        JOptionPane.showMessageDialog(null, "El empleado Cajero es: " + empleadoConsultar2.toString());

        String identificationCliente = JOptionPane.showInputDialog("Ingrese el numero de identificacion del cliente: ");

        // Buscar el empleado con la identificacion ingresada

        boolean isIdentificacionValida2 = validateIdentification(identificationEmpleado);

        while (!isIdentificacionValida2) {
            JOptionPane.showMessageDialog(null,
                    "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
            identificationCliente = JOptionPane.showInputDialog("(Cliente) Ingrese su numero de identificacion: ");
            isIdentificacionValida2 = validateIdentification(identificationCliente);
        } 

        Cliente clienteConsultar = Cliente.obtenerConIdentificacionCliente(Integer.parseInt(identificationCliente), clientes);

        if (clienteConsultar == null) {
            JOptionPane.showMessageDialog(null,
                    "No hay un cliente con la identificacion ingresada. Por favor, cree un cliente.");
            return null;
        }

        //Buscar si existe cuenta cliente con la identificacion ingresada

        if (clienteConsultar.getEstadoCuenta() == false) {
            JOptionPane.showMessageDialog(null,
                    "El cliente no tiene una cuenta activa. Por favor, cree una cuenta activa.");
            return null;
        }

        //Mostrar informacion del cliente

        System.out.println(":::::::::::::::::::::::::::::::::::::::" );



        if (clienteConsultar.getEstadoCuenta() == false) {
            JOptionPane.showMessageDialog(null,
                    "El cliente no tiene una cuenta activa. Por favor, cree una cuenta activa.");
            return null;
        }

        // Buscar cliente con la identificacion o numero de cuenta

        JOptionPane.showMessageDialog(null,
                "El cliente es " + clienteConsultar.getNombre() + " " + clienteConsultar.getApellido());

        String valorConsignacionStr = JOptionPane.showInputDialog("Ingrese el valor a consignar: ");

        // Validar que el valor a consignar sea un número
        try {
            Double.parseDouble(valorConsignacionStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor a consignar no es válido. Por favor, ingresa un valor válido.");
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

        //Actualizar saldo
        double saldoActualizado = clienteConsultar.getSaldo() + valorConsignacion;

        // Actualizar saldo del cliente
        clienteConsultar.setSaldo(saldoActualizado);

        //Obtener la fecha actual
        Date now = new Date();

        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Consignacion" + "\n" + 
                "Cliente: " + clienteConsultar.getNombre() + " " + clienteConsultar.getApellido() + "\n" +
                "...¡Tramite consignación exitoso!...\n" +
                "Atendido por el empleado: " + empleadoConsultar2.toString() + "\n" +
                "Fecha: " + now + "\n" +
                "Hora: " + now + "\n" +
                ":::::::::::::::::::::::::::::::::::::::"
        );

        return tramite;

    }

    // Realizar retiro
    static Tramite createTramiteRetiro(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados) {
        // Crear tramite
        Tramite tramite = new Tramite();

        String identificationEmpleado = JOptionPane.showInputDialog("(Empleado) Ingrese su numero de identificacion: ");

        // Buscar el empleado con la identificacion ingresada

        boolean isIdentificacionValida = validateIdentification(identificationEmpleado);

        while (!isIdentificacionValida) {
            JOptionPane.showMessageDialog(null,
                    "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
            identificationEmpleado = JOptionPane.showInputDialog("(Empleado) Ingrese su numero de identificacion: ");
            isIdentificacionValida = validateIdentification(identificationEmpleado);
        }

        Empleado empleadoConsultar2 = Empleado.obtenerConIdentificacionEmpleado(Integer.parseInt(identificationEmpleado), empleados);

        if (empleadoConsultar2 == null) {
            JOptionPane.showMessageDialog(null,
                    "No hay un empleado con la identificacion ingresada. Por favor, cree un empleado.");
            return null;
        }

        if (!empleadoConsultar2.getCargo().equals("2")) {
            JOptionPane.showMessageDialog(null,
                    "El empleado no es Cajero. Por favor, cree un empleado Cajero.");
            return null;
        }

        if (!empleadoConsultar2.isTurno()) {
            JOptionPane.showMessageDialog(null,
                    "El empleado Cajero no está en turno. Por favor, asigne el turno al empleado Asesor de Ventas.");
            return null;
        }

        JOptionPane.showMessageDialog(null, "El empleado Cajero es: " + empleadoConsultar2.toString());

        String identificationCliente = JOptionPane.showInputDialog("Ingrese el numero de identificacion del cliente: ");

        // Buscar el empleado con la identificacion ingresada

        boolean isIdentificacionValida2 = validateIdentification(identificationEmpleado);

        while (!isIdentificacionValida2) {
            JOptionPane.showMessageDialog(null,
                    "La identificacion no es válida. Por favor, ingresa una identificacion válida.");
            identificationCliente = JOptionPane.showInputDialog("(Empleado) Ingrese su numero de identificacion: ");
            isIdentificacionValida2 = validateIdentification(identificationCliente);
        } 



        Cliente clienteConsultar = Cliente.obtenerConIdentificacionCliente(Integer.parseInt(identificationCliente), clientes);

        if (clienteConsultar == null) {
            JOptionPane.showMessageDialog(null,
                    "No hay un cliente con la identificacion ingresada. Por favor, cree un cliente.");
            return null;
        }

        //Obtener fecha actual
        Date now = new Date();

        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Retiro de Dinero\n" + 
                "Atendido por el empleado: " + empleadoConsultar2.toString() + "\n" +
                "Cliente: " + clienteConsultar + " " + clienteConsultar + "\n" + 
                "Tipo de cuenta: " + clienteConsultar.getTipoCuenta() + "\n" +
                "Estado cuenta: " + clienteConsultar.getEstadoCuenta() + "\n" +
                "Dinero disponible: " + clienteConsultar.getSaldo() + "\n" +
                "Fecha: " + now + "\n" +
                "Hora: " + now + "\n" +
                ":::::::::::::::::::::::::::::::::::::::"
        );

        



        // Desplegar select para elegir el cliente
        String nombreClienteRetiro = "";
        String apellidoClienteRetiro = "";
        int identificacionCliente = 0;
        int numeroCuentaCliente = 0;
        double valorRetiro = 0;
        String tipoCuenta = "";
        for (Cliente clienteRetiro : clientes) {
            if (clienteRetiro.getEstadoCuenta()) {
                nombreClienteRetiro = clienteRetiro.getNombre();
                apellidoClienteRetiro = clienteRetiro.getApellido();
                identificacionCliente = clienteRetiro.getIdentificacion();
                numeroCuentaCliente = clienteRetiro.getNumeroCuenta();
                valorRetiro = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor a retirar: "));
                tipoCuenta = clienteRetiro.getTipoCuenta();
            }
        }

        JOptionPane.showMessageDialog(null, "El cliente es: " + nombreClienteRetiro + " " + apellidoClienteRetiro);

        // Buscar el cliente con la identificacion ingresada
        Cliente clienteRetiro = null;
        for (Cliente clienteActual : clientes) {
            if (clienteActual.getIdentificacion() == identificacionCliente) {
                clienteRetiro = clienteActual;
            }
        }

        // Buscar el cliente con el numero de cuenta ingresado
        Cliente clienteRetiroNumeroCuenta = null;
        for (Cliente clienteActualNumeroCuenta : clientes) {
            if (clienteActualNumeroCuenta.getNumeroCuenta() == numeroCuentaCliente) {
                clienteRetiroNumeroCuenta = clienteActualNumeroCuenta;
            }
        }

        // Validar que el cliente exista
        boolean isClienteValido = clienteRetiro != null && clienteRetiroNumeroCuenta != null;

        // si el cliente no es válido, mostrar un mensaje de error y volver a pedir la
        // identificacion del cliente
        while (!isClienteValido) {
            JOptionPane.showMessageDialog(null, "El cliente no existe. Por favor, ingresa una identificacion válida.");
            identificacionCliente = Integer
                    .parseInt(JOptionPane.showInputDialog("Ingrese su numero de identificacion: "));
            numeroCuentaCliente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su numero de cuenta: "));
            isClienteValido = clienteRetiro != null && clienteRetiroNumeroCuenta != null;
        }

        // Validar que el valor a retirar sea mayor a 0
        boolean isValorRetiroValido = valorRetiro > 0;

        // si el valor a retirar no es válido, mostrar un mensaje de error y volver a
        // pedir el valor a retirar
        while (!isValorRetiroValido) {
            JOptionPane.showMessageDialog(null,
                    "El valor a retirar debe ser mayor a 0. Por favor, ingresa un valor válido.");
            valorRetiro = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor a retirar: "));
            isValorRetiroValido = valorRetiro > 0;
        }

        // Validar que el valor a retirar sea menor o igual al saldo disponible
        boolean isValorRetiroValidoSaldo = valorRetiro <= clienteRetiro.getSaldo();

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
    static Tramite createTramiteActualizarDatos(ArrayList<Cliente> clientes, ArrayList<Empleado> empleados) {
        // Crear tramite
        Tramite tramite = new Tramite();

        // Desplegar select para elegir el cliente
        String nombreClienteActualizarDatos = "";
        String apellidoClienteActualizarDatos = "";
        int identificacionCliente = 0;
        int numeroCuentaCliente = 0;
        String tipoCuenta = "";
        for (Cliente clienteActualizarDatos : clientes) {
            if (clienteActualizarDatos.getEstadoCuenta()) {
                nombreClienteActualizarDatos = clienteActualizarDatos.getNombre();
                apellidoClienteActualizarDatos = clienteActualizarDatos.getApellido();
                identificacionCliente = clienteActualizarDatos.getIdentificacion();
                numeroCuentaCliente = clienteActualizarDatos.getNumeroCuenta();
                tipoCuenta = clienteActualizarDatos.getTipoCuenta();
            }
        }

        JOptionPane.showMessageDialog(null,
                "El cliente es: " + nombreClienteActualizarDatos + " " + apellidoClienteActualizarDatos);

        // Buscar el cliente con la identificacion ingresada
        Cliente clienteActualizarDatos = null;
        for (Cliente clienteActual : clientes) {
            if (clienteActual.getIdentificacion() == identificacionCliente) {
                clienteActualizarDatos = clienteActual;
            }
        }

        // Buscar el cliente con el numero de cuenta ingresado
        Cliente clienteActualizarDatosNumeroCuenta = null;
        for (Cliente clienteActualNumeroCuenta : clientes) {
            if (clienteActualNumeroCuenta.getNumeroCuenta() == numeroCuentaCliente) {
                clienteActualizarDatosNumeroCuenta = clienteActualNumeroCuenta;
            }
        }

        // Validar que el cliente exista
        boolean isClienteValido = clienteActualizarDatos != null && clienteActualizarDatosNumeroCuenta != null;

        // si el cliente no es válido, mostrar un mensaje de error y volver a pedir la
        // identificacion del cliente
        while (!isClienteValido) {
            JOptionPane.showMessageDialog(null, "El cliente no existe. Por favor, ingresa una identificacion válida.");
            identificacionCliente = Integer
                    .parseInt(JOptionPane.showInputDialog("Ingrese su numero de identificacion: "));
            numeroCuentaCliente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su numero de cuenta: "));
            isClienteValido = clienteActualizarDatos != null && clienteActualizarDatosNumeroCuenta != null;
        }

        // Desplegar select para elegir el cliente
        String nombreClienteActualizarDatosNuevo = JOptionPane.showInputDialog("Ingrese su nombre: ");
        String apellidoClienteActualizarDatosNuevo = JOptionPane.showInputDialog("Ingrese su apellido: ");

        // Pedir la fecha de nacimiento como string
        String fechaNacimientoClienteActualizarDatosStr = JOptionPane
                .showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");

        // Crear un objeto SimpleDateFormat para parsear la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Parsear la fecha de nacimiento
        Date fechaNacimientoClienteActualizarDatos = null;

        // Validar que la fecha de nacimiento sea válida
        boolean isFechaNacimientoClienteActualizarDatosValida = false;

        // si la fecha de nacimiento no es válida, mostrar un mensaje de error y volver
        // a pedir la fecha de nacimiento
        while (!isFechaNacimientoClienteActualizarDatosValida) {
            try {
                fechaNacimientoClienteActualizarDatos = sdf.parse(fechaNacimientoClienteActualizarDatosStr);
                isFechaNacimientoClienteActualizarDatosValida = true;
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null,
                        "La fecha de nacimiento no es válida. Por favor, ingresa una fecha de nacimiento válida.");
                fechaNacimientoClienteActualizarDatosStr = JOptionPane
                        .showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
            }
        }

        // Mostrar fecha en calendario
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaNacimientoClienteActualizarDatos);
        // Mostrar edad en años
        int edad = calendar.get(Calendar.YEAR);
        // Mostrar edad en meses
        int edadMeses = calendar.get(Calendar.MONTH);
        // Mostrar edad en dias
        int edadDias = calendar.get(Calendar.DAY_OF_MONTH);
        // Mostrar edad en horas
        int edadHoras = calendar.get(Calendar.HOUR_OF_DAY);
        // Mostrar edad en minutos
        int edadMinutos = calendar.get(Calendar.MINUTE);
        // Mostrar edad en segundos
        int edadSegundos = calendar.get(Calendar.SECOND);

        // Obtener la fecha actual
        Date now = new Date();

        // Continuar con el programa si es mayor de edad, sino, pedir fecha de
        // nacimiento
        if (isAdult(fechaNacimientoClienteActualizarDatos, now)) {
            JOptionPane.showMessageDialog(null,
                    "Fecha de nacimiento: " + fechaNacimientoClienteActualizarDatos + "\nEdad: " + edad + " años, "
                            + edadMeses + " meses, " + edadDias + " dias, " + edadHoras + " horas, " + edadMinutos
                            + " minutos, " + edadSegundos + " segundos");
        } else {
            JOptionPane.showMessageDialog(null,
                    "No eres mayor de edad, por favor ingresa una fecha de nacimiento válida.");
            fechaNacimientoClienteActualizarDatosStr = JOptionPane
                    .showInputDialog("Ingrese su fecha de nacimiento (dd/MM/yyyy): ");
            try {
                fechaNacimientoClienteActualizarDatos = sdf.parse(fechaNacimientoClienteActualizarDatosStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String direccionClienteActualizarDatos = JOptionPane.showInputDialog("Ingrese su direccion: ");

        // Desplegar select con los tipos de cuenta
        String tipoCuentaClienteActualizarDatos = JOptionPane
                .showInputDialog("Ingrese el codigo de su tipo de cuenta: " +
                        "\n1. Ahorros" +
                        "\n2. Corriente", generateTipoCuentas());

        // validar que el tipo de cuenta exista
        // boolean isTipoCuentaValido =
        // validateTipoCuentaConCode(tipoCuentaClienteActualizarDatos, tipoCuentas);

        // si el tipo de cuenta no es válido, mostrar un mensaje de error y volver a
        // pedir el código del tipo de cuenta
        /*
         * while (!isTipoCuentaValido) {
         * JOptionPane.showMessageDialog(null,
         * "El código del tipo de cuenta no es válido. Por favor, ingresa un código válido."
         * );
         * tipoCuentaClienteActualizarDatos =
         * JOptionPane.showInputDialog("Ingrese el codigo de su tipo de cuenta: " +
         * "\n1. Ahorros" +
         * "\n2. Corriente", generateTipoCuentas());
         * isTipoCuentaValido =
         * validateTipoCuentaConCode(tipoCuentaClienteActualizarDatos, tipoCuentas);
         * }
         */

        String tipoCuentaClienteActualizarDatosDescription = "";

        // Recorrer los tipos de cuenta
        /*
         * for (int i = 0; i < tipoCuentasA.size(); i++) {
         * //Obtener el tipo de cuenta actual
         * tipoCuenta tipoCuenta = tipoCuentasA.get(i);
         * 
         * //Obtener el código del tipo de cuenta actual
         * String codeTipoCuenta = tipoCuenta.getCodigo();
         * 
         * //Comparar el código del tipo de cuenta actual con el código que se está
         * buscando
         * if (codeTipoCuenta.equals(tipoCuentaClienteActualizarDatos)) {
         * //Obtener la descripción del tipo de cuenta actual
         * tipoCuentaClienteActualizarDatosDescription = tipoCuenta.getDescripcion();
         * }
         * }
         */

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
        if (tipoCuentaClienteActualizarDatos.equals("2")) {
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
        JOptionPane.showMessageDialog(null, "Tipo de cuenta: " + tipoCuentaClienteActualizarDatosDescription);

        // Numero de cuenta del cliente aleatorio de 5 digitos e irrepetible
        int numeroCuentaClienteActualizarDatos = (int) (Math.random() * 90000) + 10000;
        JOptionPane.showMessageDialog(null, "Numero de cuenta: " + numeroCuentaClienteActualizarDatos);

        // Desplegar select con los estados de cuenta
        String estadoCuentaClienteActualizarDatos = ChooseEstadoCuentaCliente();
        boolean isEstadoCuentaClienteActualizarDatos = estadoCuentaClienteActualizarDatos.equals("Activa");
        System.out.println("Estado de cuenta");
        System.out.println(isEstadoCuentaClienteActualizarDatos);

        // Saldo inicial del cliente
        double saldoInicialClienteActualizarDatos = 0;
        JOptionPane.showMessageDialog(null, "Saldo inicial: " + saldoInicialClienteActualizarDatos);

        // Imprimir datos del cliente

        // Crear el objeto cliente
        Cliente cliente = new Cliente();

        System.out.println(":::::::::::::::::::::::::::::::::::::::\n" +
                "First Bank of Spring field - Monteria\n" +
                "Tramite: Actualizar datos Cliente\n" +
                "Cliente: " + cliente.getNombre() + " " + cliente.getApellido() + "\n" +
                "Fecha de nacimiento: " + fechaNacimientoClienteActualizarDatos + "\n" +
                "Edad: " + edad + " años, " + edadMeses + " meses, " + edadDias + " dias, " + edadHoras + " horas, "
                + edadMinutos + " minutos, " + edadSegundos + " segundos\n" +
                "Direccion: " + direccionClienteActualizarDatos + "\n" +
                "Tipo de cuenta: " + tipoCuentaClienteActualizarDatosDescription + "\n" +
                // Condicion para imprimir informacion adicional al seleccionar el tipo de
                // cuenta Corriente
                (tipoCuentaClienteActualizarDatos.equals("2") ? "Nombre de la empresa: " + nombreEmpresa + "\n" +
                        "NIT de la empresa: " + nit + "\n" +
                        "Numero de telefono de la empresa: " + telefonoEmpresa + "\n" +
                        "Nombre del representante legal de la empresa: " + nombreRepresentante + "\n" +
                        "Numero de identificacion del representante legal de la empresa: " + telefonoRepresentante
                        + "\n" +
                        "Direccion de la empresa: " + direccionEmpresa + "\n" : "")
                +
                "" +
                "Numero de cuenta: " + numeroCuentaClienteActualizarDatos + "\n" +
                "Estado de cuenta: " + estadoCuentaClienteActualizarDatos + "\n" +
                "Saldo disponible: " + saldoInicialClienteActualizarDatos + "\n" +
                "...¡Tramite Actualizar datos Cliente exitoso!...\n" +
                // "Atendido por el empleado: " + nombreEmpleadoAsesorVentas + "\n" +
                "Fecha: " + now + "\n" +
                "Hora: " + now + "\n" +
                ":::::::::::::::::::::::::::::::::::::::"

        );

        return tramite;
    }

    // Mostrar todos los tramites creados
    static void mostrarTramites(ArrayList<Tramite> tramites) {
        if (tramites.size() > 0) {
            JOptionPane.showMessageDialog(null, "Tramites: \n" + tramites.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No hay tramites creados, por favor cree un tramite primero.");
        }
    }

    /*
     * /Informacion adicional al seleccionar el tipo de cuenta Corriente
     * static void InformacionAdicionalCorriente() {
     * JOptionPane.showMessageDialog(null,
     * "Informacion adicional al seleccionar el tipo de cuenta Corriente");
     * //Guardar nombre de la empresa
     * String nombreEmpresa =
     * JOptionPane.showInputDialog("Ingrese el nombre de la empresa: ");
     * //Guardar NIT de la empresa
     * int nit =
     * Integer.parseInt(JOptionPane.showInputDialog("Ingrese el NIT de la empresa: "
     * ));
     * //Guardar numero de telefono de la empresa
     * int telefonoEmpresa = Integer.parseInt(JOptionPane.
     * showInputDialog("Ingrese el numero de telefono de la empresa: "));
     * //Guardar nombre del representante legal de la empresa
     * String representanteLegal = JOptionPane.
     * showInputDialog("Ingrese el nombre del representante legal de la empresa: ");
     * //Guardar numero de identificacion del representante legal de la empresa
     * int telefonoRepresentante = Integer.parseInt(JOptionPane.
     * showInputDialog("Ingrese el numero de identificacion del representante legal de la empresa: "
     * ));
     * JOptionPane.showMessageDialog(null,
     * "Ingrese el numero de telefono del representante legal de la empresa: ");
     * //Guardar numero de telefono del representante legal de la empresa
     * String direccionEmpresa = JOptionPane.
     * showInputDialog("Ingrese el numero de telefono del representante legal de la empresa: "
     * );
     * 
     * 
     * }
     * 
     * 
     * 
     */

}

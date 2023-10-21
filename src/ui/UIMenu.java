
package ui;

import javax.swing.JOptionPane;

/**
 *
 * @author sergio
 */
public class UIMenu {

    //Menú con JOption
    public static void showMenu() {

        int opcion = 0;

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
                    JOptionPane.showMessageDialog(null, "Crear Empleados");
                    

                    

                    break;
                case 2:
                    //Crear Clientes
                    JOptionPane.showMessageDialog(null, "Crear Clientes");




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








    
}

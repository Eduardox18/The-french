package datos;

import logica.Bitacora;

/**
 * Interface para la clase Bitacora del proyecto. Se declaran los métodos definidos en el diagrama
 * de clases. Algunos métodos fueron comentados porque no se utilizarán en la parte funcional a
 * presentar del programa.
 */
public interface BitacoraDAO {

    /**
     *
     * Método declarado que permite agregar una nueva bitácora a la base de datos del Sistema.
     * Habrá un registro único de bitácoras y sólo se podrá añadir una bitácora por curso que tenga
     * el alumno.
     *
     * @param bitacora Objeto de tipo Bitacora de donde se obtienen todos los datos que se
     * registrarán.
     * @return Regresará verdadero (true) si la operación de registro fue exitosa o falso (false) si
     * la operación de registro falló.
     */
    public boolean registrarBitacora(Bitacora bitacora);

    /**
     * Lista de métodos declarados en el diagrama de clases pero que no se utilizarán en la versión
     * actual del programa.
     */
    //public ObservableList<Bitacora> consultarBitacora(int noBitacora);
}

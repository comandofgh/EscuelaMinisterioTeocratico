package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coman.escuelaministerioteocratico.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Esta clase es utilizada para administrar la creacion y actualizacion de la base de datos.
 * en la misma utilizamos DAO(Patron de dise;o Data Acces Objet)
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "emt.db";
    private static final int DATABASE_VERSION = 1;

    //Objetos DAO que se utilizaran para acceder a la tabla Estudiante
    private Dao<Estudiante, Integer> estudianteDAO = null;
    private RuntimeExceptionDao<Estudiante, Integer> estudianteRuntimeDAO = null;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * Metodo invocado cuando la base de datos es creada, Usualmente se hacen llamadas a los metodos
     * createTable para crear las tablas que almacenaran los datos.
     *
     * @param db
     * @param source
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource source) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onCreate()");
            TableUtils.createTable(source, Estudiante.class);
        } catch (SQLException ex) {
            Log.e(DatabaseHelper.class.getSimpleName(), "Imposible crear la base de datos", ex);
            throw new RuntimeException(ex);
        }
    }

    /**
     *Este metodo es invocado cuando la aplicacion es actualizada y tiene un numero de version
     * superior. Permite el ajuste a los mdatos para alinearse con la nueva version
     * @param db
     * @param source
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onUpgrade()");
            TableUtils.dropTable(source, Estudiante.class, true);
            //Después de eliminar las tablas anteriores, creamos nuevamente la base de datos
            onCreate(db, source);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getSimpleName(), "Imposible eliminar la base de datos", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * obtiene el objero Data Access Objet (DAO) para la entidad Estudiante
     *
     * @return
     * @throws SQLException
     */
    public Dao<Estudiante, Integer> getEstudianteDAO() throws SQLException {
        if (estudianteDAO == null) estudianteDAO = getDao(Estudiante.class);
        return estudianteDAO;
    }

    /**
     * Obtiene la version RuntimeException del objeto DAO para la entidad Estudiante.
     * Los objetos RuntimeExceptionDao únicamente arrojan excepciones de tipo RuntimeException
     * @return
     */
    public RuntimeExceptionDao<Estudiante, Integer> getEstudianteRuntimeDAO() {
        if (estudianteRuntimeDAO == null)
            estudianteRuntimeDAO = getRuntimeExceptionDao(Estudiante.class);
        return estudianteRuntimeDAO;
    }

    @Override
    public void close() {
        super.close();
        estudianteDAO = null; //nos aseguramos de cerrar la conexiones de los objetos Dao
        estudianteRuntimeDAO = null;
    }
}


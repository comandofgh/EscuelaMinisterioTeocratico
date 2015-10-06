package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;

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
        //TODO: Al finalizar el helper crear archivo de configuragion ORMlite
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource source) {
        log.i(DatabaseHelper.class.getSimpleName(), "onCreate()");
        //TODO; AQUI QUEDE

    }
}


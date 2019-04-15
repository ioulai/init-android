package fr.eni.ecole.androkado.dal.contracts;

/**
 *  private String titre;
 *     private String description;
 *     private String url;
 *     private double prix = 0;
 *     private float envie = 0;
 *     private boolean active = true;
 *     private boolean acheter = false;
 */
public abstract class ArticleContract {
    public static final String TABLE_NAME = "ARTICLE";
    public static final String COL_ID = "ID";
    public static final String COL_NOM = "NOM";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_URL = "URL";
    public static final String COL_PRIX = "PRIX";
    public static final String COL_ENVIE = "ENVIE";
    public static final String COL_ACTIVE = "ACTIVE";
    public static final String COL_ACHETER = "ACHETER";
    public static final String COL_DATE_CREATION = "DATE_CREATION";


    public static final String SQL_CREATE_TABLE =
            " CREATE TABLE IF NOT EXISTS "
                    + TABLE_NAME + " ( "
                    + COL_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                    + COL_NOM
                    + " TEXT , "
                    + COL_DESCRIPTION
                    + " TEXT ,"
                    + COL_URL
                    + " TEXT , "
                    + COL_PRIX
                    + " REAL ,"
                    + COL_ENVIE
                    + " REAL ,"
                    + COL_ACTIVE
                    + " INTEGER ,"
                    + COL_ACHETER
                    + " INTEGER, "
                    + COL_DATE_CREATION
                    + " INTEGER "
                    + " )";

    public static final String SQL_DROP_TABLE =
            " DROP TABLE IF EXISTS "
                    + TABLE_NAME;

}

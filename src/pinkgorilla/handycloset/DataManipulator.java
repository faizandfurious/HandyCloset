package pinkgorilla.handycloset;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * This class structures and stores data for the HandyCloset application. It contains methods that translate java queries into
 * SQL queries, which in turn return java objects
 * @author Faiz
 *
 */
public class DataManipulator
{
	
    private static final  String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 2;
    static final String TABLE_NAME = "newtable";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PICTURE = "picture";
    public static final String KEY_TYPE = "type";
    public static final String KEY_RATING = "rating";
    private static Context context;
    static SQLiteDatabase db;
    private SQLiteStatement insertStmt;
        
    private static final String INSERT = "insert into " + TABLE_NAME + " (name,picture,type,rating) values (?,?,?,?)";
    public DataManipulator(Context context) {
        DataManipulator.context = context;
        OpenHelper openHelper = new OpenHelper(DataManipulator.context);
        DataManipulator.db = openHelper.getWritableDatabase();
        this.insertStmt = DataManipulator.db.compileStatement(INSERT);
    }
    
    //Performs an insertion into the database, given all the attributes of the Article variable.
    public long insert(String name, byte[] picture, String type, int rating) {
        this.insertStmt.bindString(1, name);
        this.insertStmt.bindBlob(2, picture);
        this.insertStmt.bindString(3, type);
        this.insertStmt.bindDouble(4, rating);
        return this.insertStmt.executeInsert();
    }
    
    /**
     * This method returns a List containing Hashtable objects that each hold information about a specific article object. It takes in a 
     * string that is used to find objects. Currently matches the string with the name of each object.
     * @param search A string that contains information about the object to search with.
     * 
     * @return
     */
    //TODO Return Article objects instead of a Hashtable
    public List<Hashtable> searchQuery(String search){
        List<Hashtable> list = new ArrayList<Hashtable>();
    	String q = "SELECT * FROM " + TABLE_NAME + " WHERE name LIKE '%" + search + "%';";
     	Cursor cursor = db.rawQuery(q, null);

        if (cursor.moveToFirst()) {
            do {
            	Hashtable values = new Hashtable();
            	values.put("id", cursor.getString(0));
            	values.put("name", cursor.getString(1));
            	values.put("picture", cursor.getBlob(2));
            	values.put("type", cursor.getString(3));
            	values.put("rating", cursor.getInt(4));
            	list.add(values);
            } while (cursor.moveToNext());
         }
         if (cursor != null && !cursor.isClosed()) {
            cursor.close();
         } 
         cursor.close();
         
         return list;
    }
    
    //Returns the picture of the 
    public Drawable getPicture(int id){
    	byte[] bytes = null;
    	
    	String q = "SELECT " + KEY_PICTURE + " FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = '" + id +"';";
    	
    	Cursor cursor = db.rawQuery(q, null);
    	
    	if(cursor.moveToFirst()){
    		bytes = cursor.getBlob(0);
    	}
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        } 
        cursor.close();
        if(bytes != null){
           	int h = 60; // height in pixels
           	int w = 60; // width in pixels    
            Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
           	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
           	Drawable picture = new BitmapDrawable(scaled);
        	return picture;
        }
    
        else{
        	return null;
        }
    }
    
    public List<Hashtable> selectTops(){
    	
    	List<Hashtable> tops = new ArrayList<Hashtable>();
    	String q = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_TYPE + " = 'Top';";
    	
    	Cursor cursor = db.rawQuery(q, null);
    	
        if (cursor.moveToFirst()) {
            do {
            	Hashtable values = new Hashtable();
            	values.put("id", cursor.getString(0));
            	values.put("name", cursor.getString(1));
            	values.put("picture", cursor.getBlob(2));
            	values.put("type", cursor.getString(3));
            	values.put("rating", cursor.getInt(4));
            	tops.add(values);
            } while (cursor.moveToNext());
         }
         if (cursor != null && !cursor.isClosed()) {
            cursor.close();
         } 
         cursor.close();
    	
    	return tops;
    }
    
    public List<Hashtable> selectBottoms(){
    	
    	List<Hashtable> bottoms = new ArrayList<Hashtable>();
    	String q = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_TYPE + " = 'Bottom';";
    	
    	Cursor cursor = db.rawQuery(q, null);
    	
        if (cursor.moveToFirst()) {
            do {
            	Hashtable values = new Hashtable();
            	values.put("id", cursor.getString(0));
            	values.put("name", cursor.getString(1));
            	values.put("picture", cursor.getBlob(2));
            	values.put("type", cursor.getString(3));
            	values.put("rating", cursor.getInt(4));
            	bottoms.add(values);
            } while (cursor.moveToNext());
         }
         if (cursor != null && !cursor.isClosed()) {
            cursor.close();
         } 
         cursor.close();
    	
    	return bottoms;
    }
    
    public List<Integer> getIds(){
    	
    	List<Integer> ids = new ArrayList<Integer>();
    	String q = "SELECT id FROM " + TABLE_NAME +";";
    	Cursor cursor = db.rawQuery(q, null);
    	if(cursor.moveToFirst()){
    		do{
    			ids.add(cursor.getInt(0));
    		} while (cursor.moveToNext());
    	}
    	
    	return ids;
    }
    
    public List<Integer> getTopIds(){
    	
    	List<Integer> ids = new ArrayList<Integer>();
    	String q = "SELECT id FROM " + TABLE_NAME +" WHERE " + KEY_TYPE + " = 'Top';";
    	Cursor cursor = db.rawQuery(q, null);
    	if(cursor.moveToFirst()){
    		do{
    			ids.add(cursor.getInt(0));
    		} while (cursor.moveToNext());
    	}
    	
    	return ids;
    }
    
    public List<Integer> getBottomIds(){
    	
    	List<Integer> ids = new ArrayList<Integer>();
    	String q = "SELECT id FROM " + TABLE_NAME +" WHERE " + KEY_TYPE + " = 'Bottom';";
    	Cursor cursor = db.rawQuery(q, null);
    	if(cursor.moveToFirst()){
    		do{
    			ids.add(cursor.getInt(0));
    		} while (cursor.moveToNext());
    	}
    	
    	return ids;
    }
    
    public void deleteAll() {
        db.delete(TABLE_NAME, null, null);
    }
    public List<String[]> selectAll()
    {
        List<String[]> list = new ArrayList<String[]>();
        Cursor cursor = db.query(TABLE_NAME, new String[] { "id","name","picture" }, null, null, null, null, "name asc"); 
        int x=0;
        if (cursor.moveToFirst()) {
           do {
                String[] b1=new String[]{cursor.getString(0),cursor.getString(1),cursor.getBlob(2).toString()};//Have this be a dictionary
                list.add(b1);
                x=x+1;
                
           } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
           cursor.close();
        } 
        cursor.close();
        return list;
    }
    
    public List<Hashtable> selectHashtable()
    {
        List<Hashtable> list = new ArrayList<Hashtable>();
        Cursor cursor = db.query(TABLE_NAME, new String[] { "id","name","picture", "type", "rating" }, null, null, null, null, "name asc"); 
        if (cursor.moveToFirst()) {
           do {
           	Hashtable values = new Hashtable();
           	values.put("id", cursor.getString(0));
           	values.put("name", cursor.getString(1));
           	values.put("picture", cursor.getBlob(2));
           	values.put("type", cursor.getString(3));
           	values.put("rating", cursor.getInt(4));
           	list.add(values);
           } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
           cursor.close();
        } 
        cursor.close();
        
        return list;
    }
    
    //---updates an article of clothing---
    public boolean update(long rowId, String name, String type, int rating) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_TYPE, type);
        args.put(KEY_RATING, rating);
        return db.update(TABLE_NAME, args, 
                         KEY_ID + "=" + rowId, null) > 0;
    }
    
    
   public void delete(int rowId) {
        db.delete(TABLE_NAME, KEY_ID + "=" + rowId, null); 
   }
   
   private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
             super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
             db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, name TEXT, picture BLOB, type TEXT, rating INTEGER)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
             db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
             onCreate(db);
        }
   }

}
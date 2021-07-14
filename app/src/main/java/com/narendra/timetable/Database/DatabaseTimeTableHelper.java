package com.narendra.timetable.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.Model.TimeTableModel;
import com.narendra.timetable.exampleDemo.GenerateModelData;

import java.sql.Time;
import java.util.ArrayList;

public class DatabaseTimeTableHelper extends SQLiteOpenHelper {

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "TimeTable.db";
    static final String[] days= {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    public TimeTableModel getTheModelFortheLoadingOfData(String timetableName) {
        //Connection con=DatabaseConnection.getConnection("jdbc:sqlite:E:\\6th_sem\\ADP\\trailDb2.db");
        SQLiteDatabase db=this.getWritableDatabase();
        TimeTableModel model1=null;
        if(db!=null) {
            try {
                String myQuery="select * from "+TimeTableContract.tableName+" where "+TimeTableContract.timeTableName+" = '"+timetableName+"' ;";
                //stmt=con.createStatement();
                Cursor rs=db.rawQuery(myQuery,null);
                if(rs.moveToNext()) {
                    model1= GenerateModelData.generateData(timetableName, rs.getInt(rs.getColumnIndex(TimeTableContract.numberOfRows)),rs.getInt(rs.getColumnIndex(TimeTableContract.numberOfperiods)));
                    model1.setTimeTableId(rs.getInt(rs.getColumnIndex(TimeTableContract.timeTableId)));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else {
            System.out.println("Sorru coulkd not establish connection");
        }
        return model1;
    }

    public TimeTableModel getTheModelFortheLoadingOfData(int timetableId) {
        SQLiteDatabase db=this.getWritableDatabase();
        TimeTableModel model1=null;
        if(db!=null) {
            try {
                String statement="select * from "+TimeTableContract.tableName+" where "+TimeTableContract.timeTableId+" = "+timetableId+" ;";
                Cursor rs=db.rawQuery(statement,null);
                if(rs.moveToNext()) {
                    model1=GenerateModelData.generateData(rs.getString(rs.getColumnIndex(TimeTableContract.timeTableName)), rs.getInt(rs.getColumnIndex(TimeTableContract.numberOfRows)),rs.getInt(rs.getColumnIndex(TimeTableContract.numberOfperiods)));
                    model1.setTimeTableId(rs.getInt(rs.getColumnIndex(TimeTableContract.timeTableId)));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else {
            System.out.println("Sorru coulkd not establish connection");
        }


        return model1;
    }

    public void loadTheDataForATimeTable(String timetableName, TimeTableModel model) {
        SQLiteDatabase db=this.getWritableDatabase();
        try {
            ArrayList<String> rowValues=model.getRowNames();
            //Statement stmt=con.createStatement();
            //PreparedStatement psmt=con.prepareStatement("SELECT * FROM "+TimeTableRowsContract.tablename +" WHERE "+TimeTableRowsContract.timeTableId +" = ? ORDER BY "+TimeTableRowsContract.rowNumber +";");
            //System.out.println(model.getTimeTableId());
            //psmt.clearParameters();
            //psmt.setInt(1, model.getTimeTableId());
            String sql="SELECT * FROM "+TimeTableRowsContract.tablename +" WHERE "+TimeTableRowsContract.timeTableId +" = "+model.getTimeTableId()+" ORDER BY "+TimeTableRowsContract.rowNumber +";";
            Cursor rs=db.rawQuery(sql,null);
            int counter=0;
            while(rs.moveToNext()) {
                rowValues.set(counter, rs.getString(rs.getColumnIndex(TimeTableRowsContract.rowName)));
                counter++;
            }
            //loading the period values from and to
            sql="SELECT * FROM "+TimeTableperiodsContract.tablename+" WHERE "+TimeTableperiodsContract.timeTableId+"="+model.getTimeTableId()+" ORDER BY "+TimeTableperiodsContract.periodNumber+";";
            ArrayList<PeriodTimeModel> periods=model.getPeriodTimes();
            rs=db.rawQuery(sql,null);
            counter=0;
            while(rs.moveToNext()) {
                //System.out.println(new Time(1,1,1));
                periods.get(counter).setFrom(new Time(rs.getLong(rs.getColumnIndex(TimeTableperiodsContract.fromTime))));
                periods.get(counter).setTo(new Time(rs.getLong(rs.getColumnIndex(TimeTableperiodsContract.toTime))));
                //System.out.println(rs.getTime("FROMTIME")+"::"+rs.getTime("TOTIME"));
                counter++;
            }
            // loading the values of the days periods know
            for(int i=0;i<6;i++) {
                sql="SELECT * FROM "+TimeTableValuesContract.tableName+" WHERE "+TimeTableValuesContract.timeTableId+"="+model.getTimeTableId()+" AND "+TimeTableValuesContract.dayId+"="+(i+1)+" ;";
                ArrayList<RowModel> rowvalues=model.getTimeTableValues().get(days[i]);
                //rs=stmt.executeQuery(sql);
                //System.out.println(days[i]+":"+rs);
                rs=db.rawQuery(sql,null);
                while(rs.moveToNext()) {
                    int rowNumber=rs.getInt(rs.getColumnIndex(TimeTableValuesContract.rowNumber));
                    int periodNumber=rs.getInt(rs.getColumnIndex(TimeTableValuesContract.periodNumber));
                    String rvalue=rs.getString(rs.getColumnIndex(TimeTableValuesContract.value));
                    rowvalues.get(periodNumber-1).getRowValues().set(rowNumber-1, rvalue);
                    //Sy//stem.out.println(days[i]+" period"+periodNumber+" rowvalue"+rowNumber+" value="+rvalue);
                }
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void insertNewTimeTable(String timetableName,int numberOfPeriods,int numberOfRows,SQLiteDatabase db) throws Exception {
        String sql="INSERT INTO "+TimeTableContract.tableName+"("+TimeTableContract.timeTableName+","+TimeTableContract.numberOfperiods+","+TimeTableContract.numberOfRows+") VALUES('"+timetableName+"',"+numberOfPeriods+","+numberOfRows+");";
        //PreparedStatement psmt=con.prepareStatement(sql);
        //psmt.clearParameters();
        //psmt.setString(1, timetableName);
        //psmt.setInt(2, numberOfPeriods);
        //psmt.setInt(3, numberOfRows);
        db.rawQuery(sql,null);
    }

    public int getTimeTableId(String timetableName,SQLiteDatabase db) {
        String sql="SELECT * FROM "+TimeTableContract.tableName+" WHERE "+TimeTableContract.timeTableName+"='"+timetableName+"' ;";
        //Statement stmt;
        try {
            //stmt=con.createStatement();
            //psmt = con.prepareStatement(sql);
            //psmt.clearParameters();
            //psmt.setString(1, timetableName);
            Cursor rs=db.rawQuery(sql,null);
            if(rs.moveToNext()) {
                return rs.getInt(rs.getColumnIndex(TimeTableContract.timeTableId));
            }
            return -1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    public int getTimeTableId2(String timetableName) {
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="SELECT * FROM "+TimeTableContract.tableName+" WHERE "+TimeTableContract.timeTableName+"='"+timetableName+"' ;";
        //Statement stmt;
        try {
            //stmt=con.createStatement();
            //psmt = con.prepareStatement(sql);
            //psmt.clearParameters();
            //psmt.setString(1, timetableName);
            Cursor rs=db.rawQuery(sql,null);
            if(rs.moveToNext()) {
                return rs.getInt(rs.getColumnIndex(TimeTableContract.timeTableId));
            }
            return -1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    public static void insertperiodTimes(int timetableid,ArrayList<PeriodTimeModel> periodTimes,SQLiteDatabase db) throws Exception {
        String sql="INSERT INTO "+TimeTableperiodsContract.tablename+" VALUES(?,?,?,?);";
        //PreparedStatement psmt=con.prepareStatement(sql);
        //Statement stmt=con.createStatement();
        int count=1;
        for(PeriodTimeModel p: periodTimes) {
            sql="INSERT INTO "+TimeTableperiodsContract.tablename+" VALUES("+timetableid+","+count+","+p.getFrom().getTime()+","+p.getTo().getTime()+");";
			/*psmt.clearParameters();
			psmt.setInt(1, timetableid);
			psmt.setInt(2, count);
			psmt.setTime(3, p.getFrom());
			psmt.setTime(4, p.getTo());
			psmt.execute();*/
            db.execSQL(sql);
            count++;
        }
    }

    public static void insertRownames(int timetableid,ArrayList<String> rownames,SQLiteDatabase db) throws Exception {
        String sql="INSERT INTO "+TimeTableRowsContract.tablename +" VALUES(?,?,?);";
        //Statement stmt=con.createStatement();
        int count=1;
        for(String p: rownames) {
            sql="INSERT INTO "+TimeTableRowsContract.tablename +" VALUES("+timetableid+","+count+",'"+p+"');";
			/*psmt.clearParameters();
			psmt.setInt(1, timetableid);
			psmt.setInt(2, count);
			psmt.setString(3, p);
			psmt.execute();*/
            db.execSQL(sql);
            count++;
        }
    }

    public static void insertRowvalues(int timetableid,ArrayList<RowModel> rowValues,int dayid,SQLiteDatabase db) throws Exception {
        String sql="INSERT INTO "+TimeTableValuesContract.tableName+"("+TimeTableValuesContract.timeTableId+","+TimeTableValuesContract.dayId+","+TimeTableValuesContract.periodNumber+","+TimeTableValuesContract.rowNumber+","+TimeTableValuesContract.value+") VALUES(?,?,?,?,?);";
        //PreparedStatement psmt=con.prepareStatement(sql);
        //Statement stmt=con.createStatement();
        int periodNumber=1;
        for(RowModel rowValue:rowValues) {
            int rowNumber=1;
            for(String eachRowValue:rowValue.getRowValues()) {
                sql="INSERT INTO "+TimeTableValuesContract.tableName+"("+TimeTableValuesContract.timeTableId+","+TimeTableValuesContract.dayId+","+TimeTableValuesContract.periodNumber+","+TimeTableValuesContract.rowNumber+","+TimeTableValuesContract.value+") VALUES("+timetableid+","+dayid+","+periodNumber+","+rowNumber+",'"+eachRowValue+"');";
				/*psmt.clearParameters();
				psmt.setInt(1, timetableid);
				psmt.setInt(2, dayid);
				psmt.setInt(3, periodNumber);
				psmt.setInt(4, rowNumber);
				psmt.setString(5, eachRowValue);
				psmt.executeUpdate();*/
                db.execSQL(sql);
                System.out.println(dayid+":"+periodNumber+":"+rowNumber+": done");
                rowNumber++;
            }
            periodNumber++;
        }
    }

    public int createTable(TimeTableModel model) {
        String url = "jdbc:sqlite:E:\\6th_sem\\ADP\\trailDb2.db";
        SQLiteDatabase db=this.getWritableDatabase();
        int timetableId=-1;
        try {
            db.beginTransaction();
            //con.getTransactionIsolation();
            insertNewTimeTable(model.getTimeTableName(),model.getNumberOfperiods(),model.getNumberOfRows(),db);
            timetableId=getTimeTableId(model.getTimeTableName(),db);
            System.out.println("TIMETABLEID="+timetableId+" name="+model.getTimeTableName());
            insertperiodTimes(timetableId,model.getPeriodTimes(),db);
            System.out.println("Successfullt inserted period times");
            insertRownames(timetableId,model.getRowNames(),db);
            //String sql="INSERT INTO TIMETABLEROWS VALUES(?,?,?);";
            //PreparedStatement psmt=con.prepareStatement(sql);
            System.out.println("Successfully inserted rownames");
            for(int i=0;i<6;i++) {
                insertRowvalues(timetableId,model.getTimeTableValues().get(days[i]),i+1,db);
                System.out.println("Successfullt inserted values of day="+days[i]);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
            //con.setAutoCommit(true);
            System.out.println("Successfully added the timetable data to dataabse");

        }catch(Exception e) {
            try {
                db.endTransaction();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            if(db!=null) {
                try {
                    db.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return timetableId;
    }

    public static void updateTheTableName(String newTimetablename,int timetableId,SQLiteDatabase db) throws Exception {
        String sql="UPDATE "+TimeTableContract.tableName+" SET "+TimeTableContract.tableName+"=? WHERE "+TimeTableContract.timeTableId+"=? ; ";
        try {
            //Statement stmt=con.createStatement();
            sql="UPDATE "+TimeTableContract.tableName+" SET "+TimeTableContract.tableName+"='"+newTimetablename+"' WHERE "+TimeTableContract.timeTableId+"="+timetableId+" ; ";
			/*PreparedStatement psmt=con.prepareStatement(sql);
			psmt.clearParameters();
			psmt.setString(1, newTimetablename);
			psmt.setInt(2, timetableId);
			psmt.executeUpdate();
			*/
            db.execSQL(sql);
        }catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public static void updateTheRowName(int timetableId,int rowNumber,String updatedValue,SQLiteDatabase db) throws Exception {
        String sql="update "+TimeTableRowsContract.tablename+" set "+TimeTableRowsContract.rowName+"=? where "+TimeTableRowsContract.timeTableId+"=? and "+TimeTableValuesContract.rowNumber+"=? ; ";
        try {
            sql="update "+TimeTableRowsContract.tablename+" set "+TimeTableRowsContract.rowName+"='"+updatedValue+"' where "+TimeTableRowsContract.timeTableId+"="+timetableId+" and "+TimeTableValuesContract.rowNumber+"="+rowNumber+" ; ";
			/*PreparedStatement psmt=con.prepareStatement(sql);
			psmt.clearParameters();
			psmt.setString(1, updatedValue);
			psmt.setInt(2, timetableId);
			psmt.setInt(3, rowNumber);
			psmt.executeUpdate();
			*/
            db.execSQL(sql);
        }catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public static void updateTheDayPeriodRowRowValue(int timetableId,int dayId,int periodNumber,int rowNumber,String updatedValue,SQLiteDatabase db) throws Exception {
        String sql="UPDATE "+TimeTableValuesContract.tableName+" SET "+TimeTableValuesContract.value+"=? WHERE "+TimeTableValuesContract.timeTableId+"=? AND "+TimeTableValuesContract.dayId+"=? AND "+TimeTableValuesContract.periodNumber+"=? AND "+TimeTableValuesContract.rowNumber+"=?;";
        try {
            //Statement stmt=con.createStatement();
            sql="UPDATE "+TimeTableValuesContract.tableName+" SET "+TimeTableValuesContract.value+"='"+updatedValue+"' WHERE "+TimeTableValuesContract.timeTableId+"="+timetableId+" AND "+TimeTableValuesContract.dayId+"="+dayId+" AND "+TimeTableValuesContract.periodNumber+"="+periodNumber+" AND "+TimeTableValuesContract.rowNumber+"="+rowNumber+";";
			/*PreparedStatement psmt=con.prepareStatement(sql);
			psmt.clearParameters();
			psmt.setString(1, updatedValue);
			psmt.setInt(2, timetableId);
			psmt.setInt(3, dayId);
			psmt.setInt(4, periodNumber);
			psmt.setInt(5, rowNumber);
			psmt.execute();
			*/
            //stmt.execute(sql);
            db.execSQL(sql);
        }catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public static void updateTheperiodTimes(int timetableId,int periodNumber,PeriodTimeModel updatedValue,SQLiteDatabase db) throws Exception {
        String sql="UPDATE "+TimeTableperiodsContract.tablename+" SET "+TimeTableperiodsContract.fromTime+"=?,"+TimeTableperiodsContract.toTime+"=? WHERE "+TimeTableperiodsContract.timeTableId+"=? AND "+TimeTableperiodsContract.periodNumber+"=?; ";
        try {
            sql="UPDATE "+TimeTableperiodsContract.tablename+" SET "+TimeTableperiodsContract.fromTime+"="+updatedValue.getFrom().getTime()+","+TimeTableperiodsContract.toTime+"="+updatedValue.getTo().getTime()+" WHERE "+TimeTableperiodsContract.timeTableId+"="+timetableId+" AND "+TimeTableperiodsContract.periodNumber+"="+periodNumber+"; ";
			/*PreparedStatement psmt=con.prepareStatement(sql);
			psmt.clearParameters();
			psmt.setTime(1, updatedValue.getFrom());
			psmt.setTime(2, updatedValue.getTo());
			psmt.setInt(3, timetableId);
			psmt.setInt(4, periodNumber);
			psmt.executeUpdate();
			*/
            //Statement stmt=con.createStatement();
            //stmt.execute(sql);
            db.execSQL(sql);
        }catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public void updateTheTimeTable(TimeTableModel updateTimeTable) throws Exception {
        SQLiteDatabase db=null;
        try {
            //String url="jdbc:sqlite:E:\\6th_sem\\ADP\\trailDb2.db";
            db=this.getWritableDatabase();
            //con.setAutoCommit(false);
            db.beginTransaction();
            int newTimeTableid=this.getTimeTableId(updateTimeTable.getTimeTableName(),db);
            if(newTimeTableid!=-1 && updateTimeTable.getTimeTableId()!=newTimeTableid) {
                throw new Exception("Timetable with timetable name "+updateTimeTable.getTimeTableName()+" already present");
            }
            TimeTableModel existingmodel=this.getTheModelFortheLoadingOfData(updateTimeTable.getTimeTableId());
            loadTheDataForATimeTable(existingmodel.getTimeTableName(),existingmodel);
            if(!existingmodel.getTimeTableName().equals(updateTimeTable.getTimeTableName())) {

                updateTheTableName(updateTimeTable.getTimeTableName(),existingmodel.getTimeTableId(),db);
            }
            // checking for the row names
            int count=1;
            ArrayList<String> existingRowNames=existingmodel.getRowNames();
            //System.out.println(existingRowNames.size()+":"+updateTimeTable.getRowNames().size());
            for(String updatedRowName: updateTimeTable.getRowNames()) {
                //System.out.println(count-1+":"+existingRowNames.get(count-1)+":"+updatedRowName);
                if(!existingRowNames.get(count-1).equals(updatedRowName)) {
                    updateTheRowName(existingmodel.getTimeTableId(),count,updatedRowName,db);
                }
                count++;
            }

            // checking the periodtimes and updating respectively
            ArrayList<PeriodTimeModel> existingPeriodTimes=existingmodel.getPeriodTimes();
            count=1;
            for(PeriodTimeModel updatedPeriodModel:updateTimeTable.getPeriodTimes()) {
                //System.out.println("P"+count+" "+existingPeriodTimes.get(count-1)+" :: "+updatedPeriodModel);
                if(!existingPeriodTimes.get(count-1).equals(updatedPeriodModel)) {
                    updateTheperiodTimes(existingmodel.getTimeTableId(),count,updatedPeriodModel,db);
                }
                count++;
            }
            // check for the row values for each day and respectively updating
            for(int i=0;i<6;i++) {
                ArrayList<RowModel> updatedRowValues=updateTimeTable.getTimeTableValues().get(days[i]);
                ArrayList<RowModel> existingRowValues=existingmodel.getTimeTableValues().get(days[i]);
                for(int j=0;j<existingmodel.getNumberOfperiods();j++) {
                    RowModel existingRowValue=existingRowValues.get(j);
                    RowModel updatedRowValue=updatedRowValues.get(j);
                    for(int k=0;k<existingmodel.getNumberOfRows();k++) {
                        if(!existingRowValue.getRowValues().get(k).equals(updatedRowValue.getRowValues().get(k))) {
                            updateTheDayPeriodRowRowValue(existingmodel.getTimeTableId(),i+1,j+1,k+1,updatedRowValue.getRowValues().get(k),db);
                            System.out.println("changed "+i+":"+j+":"+k);
                        }else {
                            //System.out.println("no changed "+i+":"+j+":"+k);
                        }
                    }
                }
            }
            db.endTransaction();
            db.setTransactionSuccessful();
            System.out.println("Successfully updated");
        }catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }finally {
            if(db!=null) {
                db.close();
            }
        }
    }


    public DatabaseTimeTableHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS TIMETABLE(TIMETABLEID INTEGER PRIMARY KEY AUTOINCREMENT,TIMETABLENAME VARCHAR(40),NUMBEROFPERIODAPERDAY INTEGER,NUMBEROFROWS INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS DAYS(DAYID INTEGER PRIMARY KEY,DAYNAME VARCHAR(20));");
        db.execSQL("CREATE TABLE IF NOT EXISTS TIMETABLEROWS(TIMETABLEID INTEGER,ROWNUMBER INTEGER,ROWNAME VARCHAR(20),CONSTRAINT PKTR PRIMARY KEY (TIMETABLEID,ROWNUMBER), " +
                "CONSTRAINT FKTR FOREIGN KEY (TIMETABLEID) REFERENCES TIMETABLE(TIMETABLEID) ON DELETE CASCADE ) ;");
        db.execSQL("CREATE TABLE IF NOT EXISTS TIMETABLEPERIODS(TIMETABLEID INTEGER,PERIODNUMBER INTEGER,FROMTIME TIME,TOTIME TIME,CONSTRAINT PKTP PRIMARY KEY (TIMETABLEID,PERIODNUMBER), " +
                "CONSTRAINT FKTPT FOREIGN KEY (TIMETABLEID) REFERENCES TIMETABLE(TIMETABLEID) ON DELETE CASCADE) ;");
        db.execSQL("CREATE TABLE IF NOT EXISTS TIMETABLEVALUES(TIMETABLEID INTEGER,DAYID INTEGER,PERIODNUMBER INTEGER,ROWNUMBER INTEGER,VALUE VARCHAR(20),CONSTRAINT PKTV PRIMARY KEY (TIMETABLEID,DAYID,PERIODNUMBER,ROWNUMBER), " +
                "CONSTRAINT FKTVT FOREIGN KEY (TIMETABLEID) REFERENCES TIMETABLE(TIMETABLEID) ON DELETE CASCADE, CONSTRAINT FKTVD FOREIGN KEY (DAYID) REFERENCES DAYS(DAYID) ON DELETE CASCADE);");
        Cursor rs=db.rawQuery("SELECT * FROM DAYS",null);
        if(rs.moveToNext()){
            return ;
        }
        db.execSQL("INSERT INTO DAYS VALUES(1,'MONDAY')");
        db.execSQL("INSERT INTO DAYS VALUES(2,'TUESDAY')");
        db.execSQL("INSERT INTO DAYS VALUES(3,'WENDESDAY')");
        db.execSQL("INSERT INTO DAYS VALUES(4,'THURSDAY')");
        db.execSQL("INSERT INTO DAYS VALUES(5,'FRIDAY')");
        db.execSQL("INSERT INTO DAYS VALUES(6,'SATURDAY')");
        System.out.println("Database cretaed success");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.example.onlinetestv3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "dbQuiz";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tblQuestions";

    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_OPT_A = "optA";
    private static final String COLUMN_OPT_B = "optB";
    private static final String COLUMN_OPT_C = "optC";
    private static final String COLUMN_OPT_D = "optD";
    private static final String COLUMN_ANSWER = "ans";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_QUESTION + " TEXT, " +
                COLUMN_OPT_A + " TEXT, " +
                COLUMN_OPT_B + " TEXT, " +
                COLUMN_OPT_C + " TEXT, " +
                COLUMN_OPT_D + " TEXT, " +
                COLUMN_ANSWER + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Function to insert data into the table
    public void insertData(String category,String question, String optA, String optB, String optC, String optD, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_CATEGORY + ", " +
                COLUMN_QUESTION + ", " +
                COLUMN_OPT_A + ", " +
                COLUMN_OPT_B + ", " +
                COLUMN_OPT_C + ", " +
                COLUMN_OPT_D + ", " +
                COLUMN_ANSWER + ") VALUES (?,?, ?, ?, ?, ?, ?)";
        db.execSQL(insertQuery, new String[]{category,question, optA, optB, optC, optD, answer});
        db.close();
    }

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies the columns from the table you care about
        String[] projection = {
                COLUMN_QUESTION,
                COLUMN_OPT_A,
                COLUMN_OPT_B,
                COLUMN_OPT_C,
                COLUMN_OPT_D,
                COLUMN_ANSWER
        };

        // Filter results WHERE "category" = ?
        String selection = COLUMN_CATEGORY + " = ?";
        String[] selectionArgs = {category};
        String limitAndOffset = "1, 20";

        Cursor cursor = db.query(
                TABLE_NAME,             // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null,                    // The sort order
                limitAndOffset
        );

        // Iterate through the cursor and add questions to the list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String questionText = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION));
                String optionA = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPT_A));
                String optionB = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPT_B));
                String optionC = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPT_C));
                String optionD = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPT_D));
                String answer = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ANSWER));

                // Create a Question object and add it to the list
                Question question = new Question(questionText, optionA, optionB, optionC, optionD, answer);
                questionList.add(question);
            } while (cursor.moveToNext());

            // Close the cursor after use
            cursor.close();
        }

        // Close the database connection
        db.close();

        HashSet<Question> noDuplicate = new HashSet<Question>(questionList);
        List<Question> noDuplicateQuestion = new ArrayList<Question>(noDuplicate);

        // Return the list of questions for the specified category
        return noDuplicateQuestion;
    }
}

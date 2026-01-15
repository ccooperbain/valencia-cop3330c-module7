package DBHelper;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class EngineeringStudents extends DBHelper {
	private final String TABLE_NAME = "EngineeringStudents";
	public static final String Student_ID = "Student_ID";
	public static final String Department = "Department";
	public static final String First_Name = "First_Name";
	public static final String Last_Name = "Last_Name";
	public static final String PassOutYear = "PassOutYear";
	public static final String UniversityRank = "UniversityRank";
/*
Prepares the text of the sql "select command"
fields: the fields to be displayed in the output
whatField: field to search for
whatValue: value to search for within what Field
sort: ise ASC or DESC to specify the sorting order
softField: the field that the data will be sorted by

 */
	private String prepareSQL(String fields, String whatField, String whatValue, String sortField, String sort) {
		String query = "SELECT ";
		query += fields == null ? " * FROM " + TABLE_NAME : fields + " FROM " + TABLE_NAME;
		query += whatField != null && whatValue != null ? " WHERE " + whatField + " = \"" + whatValue + "\"" : "";
		query += sort != null && sortField != null ? " order by " + sortField + " " + sort : "";
		return query;
	}

    /*
    Inserts a new record / row into the database
     */
	public void insert(Integer Student_ID, String Department, String First_Name, String Last_Name, Integer PassOutYear, Integer UniversityRank) {
		Department = Department != null ? "\"" + Department + "\"" : null;
		First_Name = First_Name != null ? "\"" + First_Name + "\"" : null;
		Last_Name = Last_Name != null ? "\"" + Last_Name + "\"" : null;
		
		Object[] values_ar = {Student_ID, Department, First_Name, Last_Name, PassOutYear, UniversityRank};
		String[] fields_ar = {EngineeringStudents.Student_ID, EngineeringStudents.Department, EngineeringStudents.First_Name, EngineeringStudents.Last_Name, EngineeringStudents.PassOutYear, EngineeringStudents.UniversityRank};
		String values = "", fields = "";
		for (int i = 0; i < values_ar.length; i++) {
			if (values_ar[i] != null) {
				values += values_ar[i] + ", ";
				fields += fields_ar[i] + ", ";
			}
		}
		if (!values.isEmpty()) {
			values = values.substring(0, values.length() - 2);
			fields = fields.substring(0, fields.length() - 2);
			super.execute("INSERT INTO " + TABLE_NAME + "(" + fields + ") values(" + values + ");");
		}
	}

    /*
    Deletes a row based on the field and value provided in the argument
     */

	public void delete(String whatField, String whatValue) {
		super.execute("DELETE from " + TABLE_NAME + " where " + whatField + " = " + whatValue + ";");
	}

    /*
        updates based on the value in the argument provided and replaces it with another value.
        that criteria that is being used to replaced does not need to be replaced
     */
	public void update(String whatField, String whatValue, String whereField, String whereValue) {
		super.execute("UPDATE " + TABLE_NAME + " set " + whatField + " = \"" + whatValue + "\" where " + whereField + " = \"" + whereValue + "\";");
	}

    /*
    fields: the fields to be displayed in the output
    what Field: field to search within
    whatValue: value to search for within whatField
    sort: use ASC or DESC to specify the sorting order
    sortField: the field that the data will be sorted by
     */
	public ArrayList<ArrayList<Object>> select(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQuery(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

    /*
    executes a sql command
     */
	public ArrayList<ArrayList<Object>> getExecuteResult(String query) {
		return super.executeQuery(query);
	}

    //executes the command without returning anything
	public void execute(String query) {
		super.execute(query);
	}

    /*
        outputs the select statement to a type that utilizes a vector
     */
	public DefaultTableModel selectToTable(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQueryToTable(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

}
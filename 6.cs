using System;
using System.Data;
using MySql.Data.MySqlClient;
//pls install the package before running the program. To install run as Install-Package MySql.Data on console.
//pls open the xampp and create the mydb1-->table1 and run the code.
class AdoNetDemo
{  static void Main(string[] args)
    {
        string connectionString = "Server=localhost;Database=mydb1;Uid=root;Pwd=;";
        using (MySqlConnection connection = new MySqlConnection(connectionString))
        {
            connection.Open();
            MySqlCommand command = new MySqlCommand("SELECT * FROM Table1", connection);
            MySqlDataReader reader = command.ExecuteReader();

            Console.WriteLine("Data from DataReader:");
            while (reader.Read())
            {
                Console.WriteLine($"{reader["Column1"]}, {reader["Column2"]}");
            }
            reader.Close();
            MySqlDataAdapter adapter = new MySqlDataAdapter("SELECT * FROM Table1", connection);
            DataSet dataSet = new DataSet();
            adapter.Fill(dataSet, "Table1");
            DataView dataView = new DataView(dataSet.Tables["Table1"]);
            Console.WriteLine("Data from DataView:");
            foreach (DataRowView row in dataView)
            {
                Console.WriteLine($"{row["Column1"]}, {row["Column2"]}");
            }
        }
        Console.WriteLine("Press any key to exit.");
        Console.ReadKey();
    }
}

package com.dev.web.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.dev.web.base.Base;

public class Utils extends Base {

    public HashMap<String, String> getTestData(String sheet, String tcid) throws IOException, FilloException {

        HashMap<String, String> data = new HashMap<String, String>();
        String testDataFilePath = loadConfig().getProperty("testDataPath");

        Fillo fillo = new Fillo();
        Connection connection = fillo.getConnection(testDataFilePath);
        String strQuery = "Select * from "+sheet+" where TCID='" + tcid + "'";
        Recordset recordset = connection.executeQuery(strQuery);

        while (recordset.next()) {
            ArrayList<String> columnNames = recordset.getFieldNames();
            for (String name : columnNames) {
                data.put(name, recordset.getField(name));
            }
        }

        recordset.close();
        connection.close();

        return data;

    }

}

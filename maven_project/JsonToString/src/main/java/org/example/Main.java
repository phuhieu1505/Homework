package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper1 = new ObjectMapper();
        ArrayList<Employee> empList1 = new ArrayList<>();
        // Tạo Object Employee
        Employee e1 = new Employee(001,"PhuHieu");
        empList1.add(e1);
        Employee e2 = new Employee(002,"P_Hieu");
        empList1.add(e2);
        // Chuyển đổi Object -> JSON
        String jsData = mapper1.writeValueAsString(empList1);
        System.out.println("Object -> JSON: " + jsData );
        mapper1.writeValue(new File("target/emp.json"),empList1);

        ObjectMapper mapper2 = new ObjectMapper();
        String js = "[{\n" +
                "    \"empID\": 1,\n" +
                "    \"empName\": \"PhuHieu\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"empID\": 2,\n" +
                "    \"empName\": \"P_Hieu\"\n" +
                "  }]";
        List<Employee> empList2 = mapper2.readValue(js, new TypeReference<List<Employee>>() {});
        System.out.println(empList2.size());
        empList2.forEach(e -> {
            System.out.println(e.getEmpID() + "," + e.getEmpName());});


    }
}

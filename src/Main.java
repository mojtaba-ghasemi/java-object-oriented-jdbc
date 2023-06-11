
import Model.Company;
import Model.Employee;

import ServiceIMPL.CompanyIMPL;

import java.sql.SQLException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


//        Company company = new Company();
//        company.setCompanyGUID(UUID.randomUUID());
//        company.setName("ISC2");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
//        LocalDate date = LocalDate.parse("2020-07-10", formatter);
//        company.setStartActivityDate(date);

        CompanyIMPL companyIMPL = new CompanyIMPL();

        //companyIMPL.insert(company);

        companyIMPL.delete(9);
         //List<Company> companyList = companyIMPL.getObject(5);


    }
}

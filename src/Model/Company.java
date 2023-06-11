package Model;

import MyDBManager.DBField;
import MyDBManager.DBTable;
import MyDBManager.ModelMethodType;

import javax.management.DescriptorKey;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@DBTable(dbTableName = "company")
public class Company implements Cloneable{
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    int companyID;
    UUID companyGUID;

    @DBField(methodType = ModelMethodType.GETTER, dbFieldName = "ID", isIdentity = true, isPrimaryKey = true, dataType = "int")
    public int getCompanyID() {
        return companyID;
    }

    @DBField(methodType = ModelMethodType.SETTER, dbFieldName = "ID", isIdentity = true, isPrimaryKey = true, dataType = "int")
    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    @DBField(methodType = ModelMethodType.GETTER, dbFieldName = "GUID", dataType = "UUID")
    public UUID getCompanyGUID() {
        return companyGUID;
    }

    @DBField(methodType = ModelMethodType.SETTER, dbFieldName = "GUID", dataType = "UUID")
    public void setCompanyGUID(UUID companyGUID) {
        this.companyGUID = companyGUID;
    }


    @DBField(methodType = ModelMethodType.GETTER, dbFieldName = "Name")
    public String getName() {
        return name;
    }

    @DBField(methodType = ModelMethodType.SETTER, dbFieldName = "Name")
    public void setName(String name) {
        this.name = name;
    }

    @DBField(methodType = ModelMethodType.GETTER, dbFieldName = "StartActivityDate", dataType = "LocalDate")
    public LocalDate getStartActivityDate() {
        return startActivityDate;
    }

    @DBField(methodType = ModelMethodType.SETTER, dbFieldName = "StartActivityDate", dataType = "LocalDate")
    public void setStartActivityDate(LocalDate startActivityDate) {
        this.startActivityDate = startActivityDate;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    String name;
    LocalDate startActivityDate;

    List<Employee> employees;

}

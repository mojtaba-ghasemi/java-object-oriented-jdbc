package ServiceIMPL;

import Model.Company;
import MyDBManager.JDBCHelper;
import Service.DBPrimaryServices;

import java.util.List;
import java.util.UUID;

public class CompanyIMPL implements DBPrimaryServices<Company> {

    @Override
    public Company getObject(UUID uuid) throws RuntimeException {
        return (Company) JDBCHelper.getObject(new Company(), uuid);
    }

    @Override
    public Company getObject(int id) throws RuntimeException {
        return (Company) JDBCHelper.getObject(new Company(), id);
    }

    @Override
    public Company getObject(String criteria) throws RuntimeException {
        return (Company) JDBCHelper.getObject(new Company(), criteria);
    }

    @Override
    public List<Company> getObjectList(String criteria) throws RuntimeException {
        return (List<Company>) (List<?>) JDBCHelper.getObjectList(new Company(), criteria);
    }

    @Override
    public Company insert(Company object) throws RuntimeException {
        JDBCHelper.insert(object);
        object = getObject(object.getCompanyGUID().toString());
        return object;
    }

    @Override
    public Company update(Company object) {
        return null;
    }

    @Override
    public void delete(String criteria) {
        JDBCHelper.delete(new Company(), criteria);
    }
    @Override
    public void delete(int id) {
        JDBCHelper.delete(new Company(), id);
    }
    @Override
    public void delete(UUID uuid) {
        JDBCHelper.delete(new Company(), uuid);
    }

}
